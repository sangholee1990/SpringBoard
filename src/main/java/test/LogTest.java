package test;

import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class LogTest {
    private static final Logger log = Logger.getLogger(LogTest.class.getName());
//    private static final Logger log4j = (Logger) LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        log.info("This is an info log message.");
        log.warning("This is a warning log message.");
        log.severe("This is a severe log message.");

//        log4j.info("This is an info log message.");
//        log4j.warning("This is a warning log message.");
//        log4j.severe("This is a severe log message.");
    }

}
