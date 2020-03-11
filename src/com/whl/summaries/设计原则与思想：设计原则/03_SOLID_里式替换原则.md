### 里式替换原则

里式替换原则，LSP，描述了：**子类对象能够替换程序中父类对象出现的任何地方，并能够保证原本程序的逻辑行为不变以及正确性不被破坏。**从定义上看起来，似乎里式替换原则与多态并没有什么太大的不同，但是二者并不是一个概念。

例如，下面代码中，父类Transporter使用 org.apache.http 库中的 HttpClient 类来传输网络数据，子类 SecurityTransporter 继承父类 Transporter，增加了额外的功能，支持传输 appId 和 appToken 安全认证信息：

```java
public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient client) {
        this.httpClient = client;
    }

    public HttpResponse sendRequest(HttpRequest request) {
        //TODO: use httpClient to send request
        return null;
    }
}

//子类
public class SecurityTransporter extends Transporter{
    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient client, String appId, String appToken) {
        super(client);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public HttpResponse sendRequest(HttpRequest request) {
        if (!appId.equals("") && !appToken.equals("")) {
            //TODO: request请求体中添加appId、appToken
        }
        return super.sendRequest(request);
    }
}

public class Demo {
    public void demoFunction(Transporter transporter) {    
        Reuqest request = new Request();
        //...省略设置request中数据值的代码...
        Response response = transporter.sendRequest(request);
        //...省略其他逻辑...
    }
}

// 里式替换原则
Demo demo = new Demo();
//原本的逻辑不变且正确性也没有被破坏
demo.demofunction(new SecurityTransporter(...));
```

从上述例子来看，里式替换似乎和多态没什么区别，但是二者却完全是两回事，我们可以通过下面改造前后的代码对比得出结论：

```java
public class SecurityTransporter extends Transporter {
    //...
    
    @Override
    public Response sendRequest(Request request) {
        if (appId.equals("") || appToken.equals("")) {
			throw new NoAuthorizationRuntimeException("...");
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);
        return super.sendRequest(request);
    }
}
```

改造前，如果appId或者appToken没有设置，就不进行校验；改造后，如果appId或者appToken没有设置，则直接抛出异常。很显然，改造前是符合LSP的，也符合多态的特性；而改造后，虽然代码仍然保持了多态的特性，并且父子类替换后也不会导致编译或者运行出错。但是从设计思路上来看，改造后SecurityTransporter的设计思路是不符合里式替换原则的。

从行为上来看，里式替换似乎和多态很类似，但是它们的关注点并不同。里式替换更加注重父子类的指导设计，确保子类的设计在替换父类时，不改变原有程序的逻辑以及正确性；而多态则是一种代码实现的思路。



### 哪些代码明显违背了LSP

里式替换原则还有一个更加具备指导意义的描述——Design by Contract，按照协议来设计。也就是限制了子类在设计的时候，要遵循父类的行为约束。父类定义了函数的行为约束，子类可以改变函数的内部实现逻辑，但不能改变函数原本的行为约束。例如：函数声明需要实现的功能、输入输出、异常以及注释中罗列的各种特殊约定等。

下面是几个违反里式替换原则的例子：

1. **子类违反父类声明要实现的功能**

   父类提供了 sortOrdersByAmount() 对订单进行按金额大小排序，子类重写这个方法后，如果是按照日期排序的，那么就违反里式替换原则。

2. **子类违反父类输入、输出、异常的约定**

   在父类中某个函数约定输入的参数可以是任意整数，而子类重写后只允许输入正整数，这就违反了里式替换原则。

   在父类中某个函数约定运行出错时返回null，获取数据为空的适合返回空集。子类重写后，运行出错抛出异常，获取不到数据时返回null。

   在父类中某个函数只抛出ArgumentNullException，如果子类重写后还抛出了除ArgumentNullException以外的异常，也违反。

3. **子类违背父类注释中罗列的任何特殊说明**

   父类中提供的 withDraw() 提现函数注释为：用户提现的金额不能超过账户余额。如果子类重写后，针对VIP账号可以实现透支功能，那么也不符合。

其实总的来说，里式替换也可以看作是 “开闭原则” 的子原则。当子类重写父类方法时，如果父类方法名已经明显限定了逻辑内容（比如按照金额排序），子类重写父类方法时，就不要将 “按照金额排序” 的逻辑修改为 “按照日期排序”，而是重新实现一个 “按照日期排序” 的方法。

如果看的角度更广一些，在Spring版本迭代中，里式替换原则也起到了很大的作用。可以发现Spring从1.x带4.x版本都保持着强兼容性，这就保证了项目在迭代Spring版本时，能够以更少的重构代价完成。