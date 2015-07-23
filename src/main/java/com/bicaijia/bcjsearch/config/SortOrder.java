package com.bicaijia.bcjsearch.config;

/*
 * 排序
 */
public enum SortOrder {

    ASC("asc"), DESC("desc");
    private String code;
    SortOrder(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public static SortOrder getSortOrder(String code) {
        if (null == code)
            return null;
        if (ASC.code.equals(code)) {
            return ASC;
        } else if (DESC.code.equals(code)) {
            return DESC;
        }
        return null;
    }

}
