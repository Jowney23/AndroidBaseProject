package com.jowney.common.util.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jowney.common.BuildConfig;

import static com.jowney.common.util.logger.LoggerUtils.checkNotNull;

/**
 * L2类是直接拷贝L类，但是做了简单的修改
 * L类只将调试日志输出在控制平台
 * L2类既可以输出在控制平台，也可以输出到本地日志
 */
public final class L2 {

    protected static final int VERBOSE = 2;
    protected static final int DEBUG = 3;
    protected static final int INFO = 4;
    protected static final int WARN = 5;
    protected static final int ERROR = 6;
    protected static final int ASSERT = 7;

    @NonNull
    private static Printer printer = new LoggerPrinter();

    static {
        L2.addLogAdapter(new AndroidLogAdapter(
                PrettyFormatStrategy.newBuilder()
                        .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                        .methodCount(2)         // (Optional) How many method line to show. Default 2
                        .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                        .tag("L2")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                        .build()) {
            @Override
            public boolean isLoggable(int priority, @Nullable @org.jetbrains.annotations.Nullable String tag) {
                return true;
            }
        });
        L2.addLogAdapter(new DiskLogAdapter( CsvFormatStrategy.newBuilder()
                .tag("L2")
                .build()){
            @Override
            public boolean isLoggable(int priority, @Nullable @org.jetbrains.annotations.Nullable String tag) {
                return true;
            }
        });
    }

    private L2() {
        //no instance
    }

    public static void printer(@NonNull Printer printer) {
        L2.printer = checkNotNull(printer);
    }

    public static void addLogAdapter(@NonNull LogAdapter adapter) {
        printer.addAdapter(checkNotNull(adapter));
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    /**
     * Given tag will be used as tag only once for this method call regardless of the tag that's been
     * set during initialization. After this invocation, the general tag that's been set will
     * be used for the subsequent log calls
     */
    public static Printer t(@Nullable String tag) {
        return printer.t(tag);
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        printer.d(message, args);
    }

    public static void d(@Nullable Object object) {
        printer.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        printer.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        printer.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        printer.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        printer.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        printer.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        printer.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        printer.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        printer.xml(xml);
    }

}
