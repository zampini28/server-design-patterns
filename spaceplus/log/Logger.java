package spaceplus.log;

public interface Logger {
    void error(String message);
    void warn(String message);
    void info(String message);
    void log(String level, String message);
}