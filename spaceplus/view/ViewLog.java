package spaceplus.view;

import spaceplus.log.Logger;
import spaceplus.log.LoggerDecorator;

public class ViewLog extends LoggerDecorator {
    public ViewLog(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String level, String message) {
        super.log(level, "View | " + message);
    }
}
