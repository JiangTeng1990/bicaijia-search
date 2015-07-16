package com.bicaijia.bcjsearch.dao;

import org.springframework.stereotype.Component;

import com.bicaijia.bcjsearch.entity.Cafe;

@Component("cafeDao")
public interface CafeDao {
    
    public Cafe selectByCafeId(Integer cafeId);
}
