package spaceplus.controller;

import spaceplus.log.LoggerDecorator;
import spaceplus.log.Logger;

public class ControllerLog extends LoggerDecorator {
    public ControllerLog(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String level, String message) {
        super.log(level, "Controller | " + message);
    }
}
