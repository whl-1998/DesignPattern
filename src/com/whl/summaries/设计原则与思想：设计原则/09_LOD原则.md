### 高内聚与低耦合

所谓高内聚，就是尽可能将相近的功能放在同一个类中。因为相近的功能往往会被同时修改，放在同一个类中，修改会比较集中，代码也容易维护。

低耦合，指的就是：类与类之间的依赖关系简单清晰，即使两个类有依赖关系，一个类的代码改动也不会或者很少导致依赖类的代码改动。



### 迪米特原则LOD

迪米特原则描述了：**不该有直接依赖关系的类之间不要有依赖；有依赖关系的类之间尽量只依赖必要的接口。**

对于 **不该有直接依赖关系的类之间不要有依赖** 这个原则可以通过下面这段代码体现。

下面代码实现了简化版的搜索引擎爬取网页的功能：

* NetworkTransporter 负责底层网络通信，根据请求获取数据
* HtmlDownloader 负责通过 URL 获取网页
* Document 表示网页文档，后续的网页内容抽取、分词、索引都是以此为处理对象

```JAVA
public class NetworkTransporter {
    //根据请求获取数据
    public Byte[] send(HtmlRequest htmlRequest) {
        //...
    }
}
```

```java
public class HtmlDownloader {
    private NetworkTransporter transporter;

    public Html downloadHtml(String url) {
        //根据请求获取数据
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        //将数据转换为Html对象返回
        return new Html(rawHtml);
    }
}
```

```java
public class Document {
    private Html html;
    private String url;

    public Document(String url) {
        this.url = url;
        //获取HtmlDownloader对象 用于通过url获取网页
        HtmlDownloader downloader = new HtmlDownloader();
        this.html = downloader.downloadHtml(url);
    }
    
    //...
}
```

上述代码从功能上来看是能用，但是并不够好，存在较多的设计缺陷：

* NetWorkTransporter

  **作为一个网络底层通信类，我们希望它的功能尽量通用，而不是只服务于下载HTML。**因此不应该直接依赖过于具体的 HtmlRequest 。我们应该把 HtmlRequest 里的 address、content 交给NetWorkTransport处理，而不是直接把HtmlRequest直接交给NetWorkTransporter处理。

  ```java
  public class NetworkTransporter {
      // 省略属性和其他方法...
      public Byte[] send(String address, Byte[] data) {
        //...
      }
  }
  ```
  
* HtmlDownloader

  由于修改了NetWorkTransporter类，我们也要对调用send()方法的HtmlDownloader进行修改：

  ```java
  public class HtmlDownloader {
      private NetworkTransporter transporter;//通过构造函数或IOC注入
  
      public Html downloadHtml(String url) {
          HtmlRequest htmlRequest = new HtmlRequest(url);
          Byte[] rawHtml = transporter.send(htmlRequest.getAddress(), htmlRequest.getContent().getBytes());
          return new Html(rawHtml);
      }
  }
  ```

* Document

  构造函数中的 downloader.downloadHtml() 逻辑复杂，耗时长，不应该放在构造函数中，会影响到代码的可测试性。

  其次，HtmlDownloader实例在构造函数中直接通过 new 创建，这违反了基于接口而非实现的编程思想，也影响了代码的可测试性。
  
  第三，从业务含义上来看，Document也没必要依赖HtmlDownloader类，违背了迪米特法则：
  
  ```java
  public class Document {
      private Html html;
      private String url;
  
      public Document(String url, Html html) {
          this.html = html;
          this.url = url;
      }
      //...
  }
  
  // 通过一个工厂方法来创建Document
  public class DocumentFactory {
      private HtmlDownloader downloader;
  
      public DocumentFactory(HtmlDownloader downloader) {
          this.downloader = downloader;
      }
  
      public Document createDocument(String url) {
          Html html = downloader.downloadHtml(url);
        return new Document(url, html);
      }
  }
  ```
  



