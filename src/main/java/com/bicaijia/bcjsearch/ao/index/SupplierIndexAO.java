package com.bicaijia.bcjsearch.ao.index;

import java.util.List;

import com.bicaijia.bcjsearch.domain.SupplierDO;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.util.OrArrayList;

public interface SupplierIndexAO {
    
//    public Result select(IndexQuery indexQuery);
    
    
    /**
     * 根据 提供服务的regionId 查询供应商时使用 
     * 每个cafe查询产品时都要使用的
     * @param solrQuery
     * @return
     */
    public OrArrayList<String> selectRegionSuppliers(IndexQuery indexQuery) ;
}
