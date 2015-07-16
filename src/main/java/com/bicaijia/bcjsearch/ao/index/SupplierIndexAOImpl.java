package com.bicaijia.bcjsearch.ao.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.bicaijia.bcjsearch.domain.SupplierDO;
import com.bicaijia.bcjsearch.factory.SolrServerFactory;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.redis.BcjRedis;
import com.bicaijia.bcjsearch.util.OrArrayList;

@Component("supplierIndexAO")
public class SupplierIndexAOImpl implements SupplierIndexAO {

    
    int everyPageRows = 200;//暂定每次搜索200条
    
    @Autowired
    private BcjRedis bcjRedis;
    
    @Override
    public OrArrayList<String> selectRegionSuppliers(IndexQuery indexQuery) {
        
        SolrQuery solrQuery = toSupplierQuery(indexQuery);
        
        SolrServer server = SolrServerFactory.generateSolrServer("supplier");
        
        OrArrayList<String> supplierIds = new OrArrayList<String>();
        try {
            long totalCount = 0;
            
             
            QueryResponse response = server.query(solrQuery);
            
            totalCount = response.getResults().getNumFound();
            if (totalCount == 0) return null;
            List<String> Ids = response.getBeans(String.class);
            supplierIds.addAll(Ids);
            for (int i = 0; i < totalCount/everyPageRows; i++) {
                solrQuery.setStart(everyPageRows * (i + 1));
                Ids = server.query(solrQuery).getBeans(String.class);
                supplierIds.addAll(Ids);
            }
            
            //把supplierIds 放入缓存中
            try {
                bcjRedis.set(indexQuery.getCafe().getCafeName() + indexQuery.getCafe().getCafeId(), supplierIds);
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
            
        } catch (SolrServerException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return supplierIds;
    }


    /**
     * 生成查询附近供应商的SolrQuery
     * @param indexQuery
     * @return
     */
    public SolrQuery toSupplierQuery(IndexQuery indexQuery) {
        
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder queryBuilder = new StringBuilder();
        //设置regionId的值
        queryBuilder.append("supplyRegionId:" + indexQuery.getCafe().getRegionId());
        queryBuilder.append(" AND ");
        queryBuilder.append("arroundCafeId:" + indexQuery.getCafe().getCafeId());
        solrQuery.setQuery("supplyRegionId:" + indexQuery.getCafe().getRegionId());       
        /*根据距离排序
        solrQuery.setFilterQueries("{!geofilt}");
        solrQuery.set("sfield", "store");
        solrQuery.set("pt", indexQuery.getX() + "," + indexQuery.getY());
        */
        
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(everyPageRows);
        
        //只搜索id
        solrQuery.setFields("id");
        return solrQuery;
    }
}
