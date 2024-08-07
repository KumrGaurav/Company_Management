package com.action;

import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // Your default action logic goes here
        return SUCCESS;
    }
}
