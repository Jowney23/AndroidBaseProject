# AndroidBaseProject
## 开发框架
![5777290-41d63e72a0e0fa86 (1)](https://user-images.githubusercontent.com/20770336/137837830-c2cb5320-45c7-453f-82b0-8204621b00d7.png)

## 日志模块儿

### 介绍
实际开发工作中只在控制台输出日志是很难记录和定位问题，所以我直接Copy了GitHub上标星比较高的日志打印库并做了修改，让其更加灵活。

只要关注两个类L和L2，L2类是直接拷贝L类，做了简单的修改！

所需要的初始化工作在L类和L2类的静态代码块里面已经完成，所以不需要再进行初始化，直接拿来用就可以了！

 * L类只在Debug时将调试日志输出在控制平台
 
 * L2类在Debug时将调试日志输出在控制平台，Release时输出日志到本地文件中

这样做可以避免无用的调试日志也输出到本地。
### 用例
```java
L.d("debug");
L.e("error");
L.w("warning");
L.v("verbose");
L.i("information");
L.wtf("What a Terrible Failure");
```

String format arguments are supported
```java
L.d("hello %s", "world");
```

Collections are supported (only available for debug logs)
```java
L.d(MAP);
L.d(SET);
L.d(LIST);
L.d(ARRAY);
```
Json and Xml support (output will be in debug level)
```java
L.json(JSON_CONTENT);
L.xml(XML_CONTENT);
```
### 类图
![how_it_works](https://user-images.githubusercontent.com/20770336/177268748-b463244b-c736-42de-b2a7-75dd2f60f07c.png)

## Tencent bugly 模块儿

配置链接：https://bugly.qq.com/docs/user-guide/advance-features-android/#bugly_1
