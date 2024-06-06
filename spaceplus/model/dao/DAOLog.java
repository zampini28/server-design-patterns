package spaceplus.model.dao;

import spaceplus.log.LoggerDecorator;
import spaceplus.log.Logger;

public class DAOLog extends LoggerDecorator {
    public DAOLog(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String level, String message) {
        super.log(level, "DAO | " + message);
    }
}
