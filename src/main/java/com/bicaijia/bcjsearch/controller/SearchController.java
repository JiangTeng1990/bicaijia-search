package com.bicaijia.bcjsearch.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bicaijia.bcjsearch.ao.index.ProductIndexAO;
import com.bicaijia.bcjsearch.dao.CafeDao;
import com.bicaijia.bcjsearch.entity.Cafe;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.JsonUtils;

@Controller
public class SearchController {
    
    @Autowired
    private ProductIndexAO productIndexAO;
    @Autowired
    private CafeDao cafeDao;
    
    @RequestMapping("/products")
    @ResponseBody
    public String products(@ModelAttribute IndexQuery indexQuery) {
        
        Result result = productIndexAO.search(indexQuery);
        result.setMsg("查询成功");
        return JsonUtils.toJsonString(result);
        
    }
    
    
    public void productByCafeId(IndexQuery indexQuery) {
        Cafe cafe = cafeDao.selectByCafeId(indexQuery.getCafe().getCafeId());
        indexQuery.setX(cafe.getLatitude());
        indexQuery.setY(cafe.getLongitude());
        indexQuery.setCafe(cafe);
        
    }
    
    
    
    
}
