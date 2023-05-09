package controller;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ActionForward implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean isRedirect = false;
    private String path = "";
    private String urlName = null;
    private String className = null;
    private Object returnData = null;
    private String title = null;
    private boolean isJson = false;
}