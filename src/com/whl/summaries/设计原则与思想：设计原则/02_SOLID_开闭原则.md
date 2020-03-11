### 如何理解开闭原则

开闭原则主要是指“对扩展开放、对修改关闭”，开闭原则是SOLID原则中最难理解、也是最难掌握、同时也是最有用的一条原则。**在23种经典设计模式中，大部分设计模式都是为了解决代码的可扩展性问题而存在的，而这些设计模式背后的设计原则就是开闭原则。**因此，开闭原则是SOLID原则中最为重要的原则。

开闭原则的详细表述是，增加一个新的功能应该在已有的代码基础上扩展代码，而非修改已有代码。

下面通过一段代码示例，进一步解释开闭原则。

其中Alert类用于处理API接口监控告警逻辑，通过传入check()方法中声明的参数，校验是否需要告警：

```java
public class RebuildBeforeAlert {
    public class Alert {
        private AlertRule rule;
        private Notification notification;

        public Alert(AlertRule rule, Notification notification) {
            this.rule = rule;
            this.notification = notification;
        }

        public void check(Api api, long requestCount, long errorCount, long durationOfSeconds) {
            long tps = requestCount / durationOfSeconds;
            //当tps值大于api接口规定的最大tps值, 告警
            if (tps > rule.getMaxTps(api)) {
                notification.notify(NotificationEmergencyLevel.URGENCY, "...");
            }
            //当接口请求出错数大于api接口规定的最大允许出错数, 告警
            if (errorCount > rule.getMaxErrorCount(api)) {
                notification.notify(NotificationEmergencyLevel.SEVERE, "...");
            }
        }
    }
}
```

现在如果需要新增一个功能：当每秒钟接口请求参数超过阈值时，触发告警。那么可以进行如下更改，在check()方法中添加对应参数，并在方法体中增加新的告警逻辑：

```java
public class RebuildBeforeAlert {
    private AlertRule rule;
    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public void check(Api api, long requestCount, long errorCount, long durationOfSeconds, long timeoutCount) {
        long tps = requestCount / durationOfSeconds;//每秒接收的请求数
        long timeoutTps = timeoutCount / durationOfSeconds;//每秒请求超时的请求数
        //当tps值大于api接口规定的最大tps值, 告警
        if (tps > rule.getMaxTps(api)) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        //当接口请求出错数大于api接口规定的最大允许出错数, 告警
        if (errorCount > rule.getMaxErrorCount(api)) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
        //当timeoutTps大于api接口规定的最大timeoutTps值, 告警
        if (timeoutTps > rule.getMaxTimeoutTps(api)) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
```

上述的代码改动并不符合开闭原则，例如：

1. 内部增加新的告警逻辑时，是在原有代码的基础上进行修改，在后期告警规则增多后，check()方法将会涉及到非常多的 if 逻辑判断，这就违反了对内修改关闭原则；
2. 外部调用 Alert.check() 的代码都需要进行相应修改，这就违反了对外扩展开放原则；

为此，我们需要遵循开闭原则，将Alert代码进行重构。主要做了如下几件事：

1. 将 check() 函数的多个入参封装为ApiStatInfo类，以保证外部调用check()时采用统一规范；
2. 引入handler，将 if 的逻辑分散在各个handler中，保证对内修改关闭；

```java
public class Alert {
	private List<AlertHandler> alertHandlers;

    public Alert() {
        alertHandlers = new ArrayList<>();
    }

    /**
     * 添加告警规则
     * @param alertHandler
     */
    public void addAlertHandler(AlertHandler alertHandler) {
        alertHandlers.add(alertHandler);
    }

    //传入一个接口信息apiStatInfo实例, 执行alertHandlers中的告警校验逻辑
    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler ah : alertHandlers) {
            ah.check(apiStatInfo);
        }
    }
}

//封装check的入参
public class ApiStatInfo {
    private Api api;
    private long requestCount;
    private long durationOfSeconds;
    private long errorCount;
}

//handler抽象类
public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}

//继承AlertHander, 实现"当tps超过阈值, 告警"的check()
public class TpsAlertHandler extends AlertHandler {
    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMaxTps(apiStatInfo.getApi())) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "当前接口tps超过阈值");
        }
    }
}

//继承AlertHander, 实现"当接口请求出错数超过阈值, 告警"的check()
public class ErrorAlertHandler extends AlertHandler{
    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMaxErrorCount(apiStatInfo.getApi())) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "当前接口请求出错数超过阈值");
        }
    }
}
```

下面的ApplicationContext是一个单例类，负责Alert的创建、组装（alertRule和notification的依赖注入）、初始化（添加handlers）：

```java
public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;//执行器
    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();

    //获取alert实例
    public Alert getAlert() {
        return alert;
    }

    //私有化构造器
    private ApplicationContext() {
        //创建告警规则及告警渠道
        alertRule = new AlertRule();
        notification = new Notification();
        //创建alert实例并添加handlers
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
    }

    //公有获取单例的方法
    public static ApplicationContext getInstance() {
        return instance;
    }

    //使用示例
    public static void main(String[] args) {
        //创建ApplicationContext实例, 执行初始化工作并获取执行器
        ApplicationContext ac = ApplicationContext.getInstance();
        //模拟外部获取的ApiStatInfo实例
        //ApiStatInfo实例包含了接口, 以及接口当前的一些状态值
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        //对apiStatInfo实例进行告警校验
        ac.getAlert().check(apiStatInfo);
    }
}
```

现在，基于重构之后的代码，如果要添加之前提到的新功能就能够遵循开闭原则，主要改动如下：

1. 在ApiStatInfo类中添加新的属性——timeoutCount
2. 添加一个新的handler实现类——TimeoutAlertHander，并且实现对应的check()方法
3. 在ApplicationContext的initializeBeans()方法中，初始化新的TimeoutAlertHander实例

代码如下：

```java
public class ApiStatInfo {
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;
    private long timeoutCount; // 改动一：添加新字段
}

// 改动二：添加新的handler
public class TimeoutAlertHandler extends AlertHandler {
    //省略代码...
}

                    
public class ApplicationContext {
    //...
        
    private ApplicationContext() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        //改动三：添加AlertHandler实例
    	alert.addAlertHandler(new TimeoutTpsAlertHandler(alertRule, notification));
    }
}
```

重构之后的代码非常灵活，如果想要添加新的告警逻辑，只需要基于扩展的方式创建新的handler即可，并且也不会影响到其他调用到check()方法的类。



### 支持开闭原则的一些更加具体的方法论

在众多的设计原则、思想、模式中，最常用于提高程序可扩展性的方法有：多态、依赖注入、基于接口而非实现编程，以及大部分设计模式（例如装饰器模式、模版设计模式、策略模式等）。这也意味着设计模式、设计原则、面向对象编程思想都是想通的。

下面这段代码描述了如何利用多态、依赖注入、基于接口而非实现编程，来实现 “对扩展开放、对修改关闭” 原则：

```java
//消息队列接口：这一部分体现了抽象意识
public interface MessageQueue { //... 
}

//kafka消息队列实现类
public class KafkaMessageQueue implements MessageQueue { //... 
}

//RocketMQ消息队列实现类
public class RocketMQMessageQueue implements MessageQueue {//...
}

//消息格式化接口：抽象意识
public interface MessageFormatter { //... 
}

//Json消息格式化实现类
public class JsonMessageFormatter implements MessageFormatter {//...
}

//消息格式化实现类
public class MessageFormatter implements MessageFormatter {//...
}

//当我们需要更换消息队列时, 只需要在依赖注入时注入不同的消息队列实现即可实现多态
//当我们需要不同的消息格式化功能时, 也只需要一个sendNotification方法就能够实现不同的格式化功能
public class Demo {
    private MessageQueue msgQueue; // 基于接口而非实现编程
    
    public Demo(MessageQueue msgQueue) { // 依赖注入, 注入消息队列依赖, 实现解耦
        this.msgQueue = msgQueue;
    }

    // msgFormatter：多态, 通过接口调用不同实现获取不同的功能
    public void sendNotification(Notification notification, MessageFormatter msgFormatter) {
        //...
    }
}
```



### 开闭原则总结

总的来说，开闭原则就是将代码中可变的部分（增加新的AlertHandler）封装起来，隔离变化；将不可变的部分（外部执行入口：check()方法）提供抽象的不可变接口，给上层系统使用。当具体实现发生变化时（需要增加新的告警规则），我们只需要基于相同抽象接口（AbstractAlertHandler）扩展一个新的实现即可，上游代码几乎不需要改动。

但是开闭原则也并不是免费的，在上述例子中我们对代码进行了重构，重构之后的代码相比起原本的代码要更加复杂，可读性要更低一些。而很多时候，我们需要对可读性和可扩展性进行权衡，例如，在告警信息非常多的时候可以牺牲一些代码的可读性来换取可扩展性，但是当告警信息比较少就没有必要这样大费周章地重构代码了，可以先等到要扩展方面的需求时再考虑重构。

