package kr.sharenshare.helper;

public class Logger {

    private static Logger instance;

    private static void init(String loggerTag, boolean debugMode) {
        init(loggerTag, debugMode, LEVEL.INFO, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    private static void init(String loggerTag, boolean debugMode, LEVEL showLevel) {
        init(loggerTag, debugMode, showLevel, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    private static void init(String loggerTag, boolean debugMode, LEVEL showLevel, String dateTimeFormat) {
        instance = new Logger();
        instance.showLevel = showLevel;
        instance.dateTimeFormat = dateTimeFormat;
        instance.loggerTag = loggerTag;
        instance.debugMode = debugMode;
    }

    private static Logger getInstance() {
        if (instance == null) throw new IllegalArgumentException("you must first Logger init");
        return instance;
    }

    public enum LEVEL {INFO, DEBUG, ERROR, TRHOW}

    private LEVEL showLevel;
    private String dateTimeFormat;
    private String loggerTag;
    private boolean debugMode;
}
