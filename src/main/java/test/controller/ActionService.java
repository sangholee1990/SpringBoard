package test.controller;//package controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ActionService {
//
//    private final ApplicationContext applicationContext;
//
//    @Autowired
//    public ActionService(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    public Action getAction(String command) {
//        // Convert the command to a bean name
//        String beanName = commandToBeanName(command);
//        if (applicationContext.containsBean(beanName)) {
//            return (Action) applicationContext.getBean(beanName);
//        } else {
//            throw new IllegalArgumentException("Invalid command: " + command);
//        }
//    }
//
//    private String commandToBeanName(String command) {
//        // Implement this method to convert the command string to a bean name
//    }
//}