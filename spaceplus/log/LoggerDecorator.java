package spaceplus.log;

public abstract class LoggerDecorator implements Logger {
    protected Logger decoratedLogger;

    public LoggerDecorator(Logger decoratedLogger) {
        this.decoratedLogger = decoratedLogger;
    }

    @Override
    public void error(String message) {
        decoratedLogger.error(message);
    }

    @Override
    public void warn(String message) {
        decoratedLogger.warn(message);
    }

    @Override
    public void info(String message) {
        decoratedLogger.info(message);
    }

    @Override
    public void log(String level, String message) {
        decoratedLogger.log(level, message);
    }
}