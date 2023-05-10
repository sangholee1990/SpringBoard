package controller;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public String sayHello() {
        return "Hello from UserService!";
    }
}