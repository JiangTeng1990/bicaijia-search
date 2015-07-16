package com.bicaijia.bcjsearch.config;

public interface Constant {
    
    String CHAR_DOU = ",";
    String CHAR_GANG = "-";
    String CHAR_GANG_XIA = "_";
    String CHAR_MAO = ":";
    String CHAR_GANG_XIE = "/";
    String CHAR_TAB = "\t";

    String SPLIT_LIST_CHAR = CHAR_DOU;
    String SPLIT_RANGE_CHAR = CHAR_GANG;
    String SPLIT_SORTORDER_CHAR = CHAR_MAO;
    String SPLIT_LOG_COL_CHAR = CHAR_TAB;

    long MILLIS_ONE_DAY = 1000 * 60 * 60 * 24L;// 1天的毫秒数
}
