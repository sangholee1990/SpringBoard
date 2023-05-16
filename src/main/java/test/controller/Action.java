//package action.board;
package test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    // public abstract이 생략된 추상 메서드
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception;


}
