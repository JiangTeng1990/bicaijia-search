package com.bicaijia.bcjsearch.ao.index;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bicaijia.bcjsearch.config.SolrConfig;
import com.bicaijia.bcjsearch.domain.SupplierDO;
import com.bicaijia.bcjsearch.factory.SolrServerFactory;
import com.bicaijia.bcjsearch.query.Condition;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.query.SolrQueryUtil;
import com.bicaijia.bcjsearch.redis.BcjRedis;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.OrArrayList;

@Component("supplierIndexAO")
public class SupplierIndexAOImpl implements SupplierIndexAO {

    @Autowired
    private BcjRedis bcjRedis;
    
    @Autowired
    private SolrConfig solrConfig;
    
    //只用来查询附近所有的supplierId
    public OrArrayList<String> selectRegionSuppliers(IndexQuery indexQuery) {
        
        int everyPageRows = 200;//暂定每次搜索200条
        
        indexQuery.setPageNo(1);
        indexQuery.setPageRows(everyPageRows);
        indexQuery.getFields().add("id");
        SolrServer server = SolrServerFactory.generateSolrServer("supplier");
        OrArrayList<String> supplierIds = new OrArrayList<String>();
        
        try {
            SolrQuery solrQuery = SolrQueryUtil.convertQuery(indexQuery, solrConfig);
            
            long totalCount = 0;
            QueryResponse response = server.query(solrQuery);
            totalCount = response.getResults().getNumFound();
            
            if (totalCount == 0) return null;
           
            for (SolrDocument doc : response.getResults()) {
                supplierIds.add((String) doc.getFieldValue("id"));
            }
            
            for (int i = 0; i < totalCount/everyPageRows; i++) {
                solrQuery.setStart(everyPageRows * (i + 1));
                for (SolrDocument doc : server.query(solrQuery).getResults()) {
                    supplierIds.add((String) doc.getFieldValue("id"));
                }
            }
            
        } catch (Exception e) {
            throw new RuntimeException("查询附近供应商出错");
        }
        return supplierIds;
    }


    @Override
    public Result search(IndexQuery indexQuery) throws Exception {
        
        Condition.convertFilterField(indexQuery, SupplierDO.class);
      
        SolrQuery solrQuery = SolrQueryUtil.convertQuery(indexQuery, solrConfig);
        SolrServer server = SolrServerFactory.generateSolrServer("supplier");
        QueryResponse response = server.query(solrQuery);
        
        return SolrQueryUtil.buildResult(indexQuery, response, SupplierDO.class);
            
    }
    
}
