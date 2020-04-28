### DRY原则

Dry原则的定义是：Don't Repeat Yourself，不要写重复的代码。

那么一般我们常见的重复情况主要分为三种，分别是**逻辑性重复、功能语句重复、代码执行重复**。



**逻辑性重复**

例如下面这段代码的 isValidUsername()、isValidPassword() 方法就存在非常明显的重复代码片段。对username和password的校验逻辑存在相当多的重复。不仅不满足代码的复用性，也违反了DRY原则。

```java
public class UserAuthenticator {
    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            throw new RuntimeException("用户名格式不合法");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("密码格式不合法");
        }
        //...省略其他代码...
    }

    private boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        // check length: 4~64
        int length = username.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!username.equals(username.toLowerCase())) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; ++i) {
            char c = username.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // check length: 4~64
        int length = password.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!password.equals(password.toLowerCase())) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; ++i) {
            char c = password.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }
}
```

为此，我们可以对其重构，将这两个方法合并：

```java
public class UserAuthenticator {
    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            throw new RuntimeException("用户名格式不合法");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("密码格式不合法");
        }
        //...省略其他代码...
    }

    private boolean isValidUsernameOrPassword(String usernameOrPassword) {
        if (usernameOrPassword == null) {
            return false;
        }
        // check length: 4~64
        int length = usernameOrPassword.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!usernameOrPassword.equals(usernameOrPassword.toLowerCase())) {
            return false;
        }
        // contains only a~z, 0~9, dot
        for (int i = 0; i < length; ++i) {
            char c = usernameOrPassword.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }
}
```

获取上述重构之后的代码看上去已经没什么问题了，但这还存在一个隐藏的问题 —— **isValidUsernameOrPassword()这个函数名称违反了“单一职责原则”、“接口隔离原则”**。

如果我们修改了密码校验的逻辑，例如：在之前的校验逻辑基础之上，允许密码存在大写字符，那么校验用户名和密码的逻辑就不相同了。为此我们可以将校验逻辑封装为更细粒度的函数：

```java
public class UserAuthenticator {
    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            throw new RuntimeException("用户名格式不合法");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("密码格式不合法");
        }
        //...省略其他代码...
    }

    private boolean isValidUsername(String username) {
        if (!onlyContains(username)) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        // 只允许包含小写字符
        if (!password.equals(password.toLowerCase())) {
            return false;
        }
        if (!onlyContains(password)) {
            return false;
        }
        return true;
    }

    /**
     * 要求str不为空, 且字符串长度为4 ~ 64之间, 且只允许包含指定字符
     * @param str
     * @return
     */
    private boolean onlyContains(String str) {
        if (str == null) {
            return false;
        }
        // check length: 4~64
        int length = str.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only a~z, 0~9, dot
        for (int i = 0; i < length; ++i) {
            char c = str.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return false;
    }
}
```

这样，就既满足了“单一职责原则”、“接口隔离原则” 的同时，也满足了DRY原则。



**功能语义重复**

假设同一个项目代码中有如下两个函数：isValidlp() 、checkflpValid() 都是用于判断IP地址是否合法的，虽然它们功能相同，但实现逻辑不同：

```java
public class IPUtils {
    //通过正则表达式判断
    public boolean isValidIp(String ipAddress) {
        if (ipAddress == null || ipAddress.length() == 0)
            return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    //普通逻辑实现
    public boolean checkIfIpValid(String ipAddress) {
        if (ipAddress == null || ipAddress.length() == 0)
            return false;
        String[] ipUnits = ipAddress.split(".");
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            //ip地址返回必须是 0~255
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            //ip地址头不允许为0
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }
}
```

上述代码也是明显违反了DRY原则的，如果项目中不统一实现思路，那么项目中两种实现同时调用就会显得很奇怪。况且，如果后期对IP地址是否合法的逻辑变更了，也要同时修改两个方法，这就给项目埋了一个大坑。



**代码执行重复**

```JAVA
public class UserService {
    private UserRepo userRepo;//通过依赖注入或者IOC框架注入

    public User login(String email, String password) {
        boolean existed = userRepo.checkIfUserExisted(email, password);
        if (!existed) {
            throw new RuntimeException("邮箱不存在");
        }
        User user = userRepo.getUserByEmail(email);
        return user;
    }
}

```

```java
public class UserRepo {
    public boolean checkIfUserExisted(String email, String password) {
        if (!EmailValidation.validate(email)) {
            throw new RuntimeException("邮箱格式错误");
        }
        if (!PasswordValidation.validate(password)) {
            throw new RuntimeException("密码格式错误");
        }
        //TODO: 去数据库检索邮箱以及对应的密码匹配的记录是否存在
        return false;
    }

    public User getUserByEmail(String email) {
        if (!EmailValidation.validate(email)) {
            throw new RuntimeException("邮箱格式错误");
        }
        //TODO: 去数据库检索email对应的用户
        return new User();
    }
}
```

上述例子中，UserService 的 login() 用于校验登陆是否成功。

当执行 login() 操作时，email的校验逻辑执行了两次，这里就存在着 “重复执行” 的代码。这种方式不仅仅违背了DRY原则，也违背了单一职责原则。

解决方法就是：将校验逻辑在UserRepo中移除，统一放在UserService中：

```java
public class UserService {
    private UserRepo userRepo;//通过依赖注入或者IOC框架注入

    public User login(String email, String password) {
        //校验邮箱密码格式, 目的是为了避免不必要的IO读写
        if (!EmailValidation.validate(email)) {
            throw new RuntimeException("邮箱格式错误");
        }
        if (!PasswordValidation.validate(password)) {
            throw new RuntimeException("密码格式错误");
        }
        User user = userRepo.getUserByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            throw new RuntimeException("校验失败");
        }
        return user;
    }
}
```

```java
public class UserRepo {
    public boolean checkIfUserExisted(String email, String password) {
        //TODO: 去数据库检索邮箱以及对应的密码匹配的记录是否存在
        return false;
    }

    public User getUserByEmail(String email) {
        //TODO: 去数据库检索email对应的用户
        return new User();
    }
}
```



### 代码复用性

虽然思想基本相同，但是代码复用性、代码复用、DRY原则三者并不相同：

1. 代码复用：表示一种行为，指的是我们在开发新功能时，尽可能复用之前存在的代码。
2. 代码复用性：表示一段具备可复用的特性或能力的代码。
3. DRY原则：描述的是代码尽可能不重复的原则。

首先不重复并不代表可复用，在一个项目中，可能不存在任何重复的代码，但并不代表其中有可复用的代码。其次，复用与可复用性，一个面向的是代码使用者，一个面向的是代码开发者。虽然三者理解上存在区别，但是目的都是提高代码可读性、可维护性。除此以外，复用已经经过测试的代码，bug也会更少。



### 提高代码复用性方案

* **减少代码耦合**

  对于高耦合的代码，当我们希望复用代码中某个功能时，将其抽取为一个独立的模块、类或者函数时，往往会牵一发而动全身，因此高耦合严重影响了代码复用性。

* **满足单一职责原则**

  如果职责不够单一，模块、类设计得大而全，那么依赖它的代码或者它依赖的代码就会非常多，进而增加了代码的耦合。相反，更加细粒度的代码，通用性更好，复用性越高。

* **模块化**

  要善于将功能独立的代码封装成模块，使其更加易复用。

* **业务与非逻辑**

  越是跟业务无关的代码越容易复用，越是与业务相关的代码越难复用。所以为了复用与业务无关的代码，可以将业务与非业务代码分离，抽取为通用的框架、类库、组件等。

* **通用代码下沉**

  从分层的角度来看，越底层的代码越通用、越容易被更多的模块调用，因此就越应该被设计得足够可复用。一般情况下，在代码分层之后，为了避免上下层交叉调用导致的关系混乱，只允许上层调用下层代码。因此，通用代码就应该尽量放在更下层。

* **继承、封装、多态、抽象**

  继承可以将公共代码抽取到父类，子类复用父类的属性和方法。多态，可以动态替换一段代码的部分逻辑（一般是多态引用参数），使得这段代码可复用。代码可以通过封装隐藏可变细节、暴露不变的接口、使得代码更易复用。抽象，使调用者只需要关注使用而不是具体实现，越抽象、越不依赖具体实现的代码，越容易被复用。

* **应用模版设计模式等**

  运用一些设计模式也能够提高代码的可复用性，例如模版模式利用多态实现，灵活地替换以及复用代码。
  