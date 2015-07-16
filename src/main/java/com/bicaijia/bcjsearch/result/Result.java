package com.bicaijia.bcjsearch.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JiangTeng
 *
 */
public class Result implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Boolean isSuccess; //

    private String msg; //

    private Map<String, Object> modules = new HashMap<String, Object>();

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getModules() {
        return modules;
    }

    public void setModules(Map<String, Object> modules) {
        this.modules = modules;
    }

}
