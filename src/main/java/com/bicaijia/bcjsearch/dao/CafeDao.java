package com.bicaijia.bcjsearch.dao;

import org.springframework.stereotype.Component;

import com.bicaijia.bcjsearch.domain.Cafe;

@Component("cafeDao")
public interface CafeDao {
    
    public Cafe selectByCafeId(Integer cafeId);
}
