# AndroidBaseProject
android架构搭建 功能预览 快速实现开发

## 日志模块儿

### Initialize
```java
Logger.addLogAdapter(new AndroidLogAdapter());
```
### And use
```java
Logger.d("hello");
```

### Output
<img src='https://github.com/orhanobut/logger/blob/master/art/logger_output.png'/>


### Options
```java
Logger.d("debug");
Logger.e("error");
Logger.w("warning");
Logger.v("verbose");
Logger.i("information");
Logger.wtf("What a Terrible Failure");
```

String format arguments are supported
```java
Logger.d("hello %s", "world");
```

Collections are supported (only available for debug logs)
```java
Logger.d(MAP);
Logger.d(SET);
Logger.d(LIST);
Logger.d(ARRAY);
```

Json and Xml support (output will be in debug level)
```java
Logger.json(JSON_CONTENT);
Logger.xml(XML_CONTENT);
```

### Advanced
```java
FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
  .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
  .methodCount(0)         // (Optional) How many method line to show. Default 2
  .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
  .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
  .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
  .build();

Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
```

### Loggable
Log adapter checks whether the log should be printed or not by checking this function.
If you want to disable/hide logs for output, override `isLoggable` method. 
`true` will print the log message, `false` will ignore it.
```java
Logger.addLogAdapter(new AndroidLogAdapter() {
  @Override public boolean isLoggable(int priority, String tag) {
    return BuildConfig.DEBUG;
  }
});
```

### Save logs to the file
//TODO: More information will be added later
```java
Logger.addLogAdapter(new DiskLogAdapter());
```

Add custom tag to Csv format strategy
```java
FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
  .tag("custom")
  .build();
  
Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
```

### How it works
<img src='https://github.com/orhanobut/logger/blob/master/art/how_it_works.png'/>


#### More
- Use filter for a better result. PRETTY_LOGGER or your custom tag
- Make sure that wrap option is disabled
- You can also simplify output by changing settings.

<img src='https://github.com/orhanobut/logger/blob/master/art/logcat_options.png'/>

- Timber Integration
```java
// Set methodOffset to 5 in order to hide internal method calls
Timber.plant(new Timber.DebugTree() {
  @Override protected void log(int priority, String tag, String message, Throwable t) {
    Logger.log(priority, tag, message, t);
  }
});
```

