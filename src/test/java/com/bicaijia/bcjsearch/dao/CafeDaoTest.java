package com.bicaijia.bcjsearch.dao;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bicaijia.bcjsearch.domain.Cafe;

public class CafeDaoTest {
    
    @Test
    public void testSelectByCafeId() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context/springContext-*.xml");
        CafeDao cafeDao = (CafeDao) context.getBean("cafeDao");
        Cafe cafe = cafeDao.selectByCafeId(1);
        System.out.println(cafe.getCafeId() + ":" + cafe.getRegionId());
    }

}
