package com.bicaijia.bcjsearch.ao.index;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bicaijia.bcjsearch.config.SolrConfig;
import com.bicaijia.bcjsearch.domain.ProductDO;
import com.bicaijia.bcjsearch.domain.SupplierDO;
import com.bicaijia.bcjsearch.factory.SolrServerFactory;
import com.bicaijia.bcjsearch.query.Condition;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.query.SolrQueryUtil;
import com.bicaijia.bcjsearch.redis.BcjRedis;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.OrArrayList;

@Service("productIndexAO")
public class ProductIndexAOImpl implements ProductIndexAO {
    
    @Autowired
    private BcjRedis bcjRedis;
    
    @Autowired
    private SolrConfig solrConfig;
    
    @Autowired
    private SupplierIndexAO supplierIndexAO;
    
    /* (non-Javadoc)
     * @see com.bicaijia.bcjsearch.ao.index.ProductIndexAO#search(com.bicaijia.bcjsearch.query.IndexQuery)
     */
    @SuppressWarnings("unchecked")
    public Result search(IndexQuery indexQuery) {

        Result result = new Result();
        OrArrayList<String> supplierIds =  null;
        
        try {
            supplierIds = (OrArrayList<String>) bcjRedis.get(indexQuery.getCafe().getClass().getName() + indexQuery.getCafe().getCafeId());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            if (supplierIds == null) {
                //转化成supplier的indexQuery
                IndexQuery supplierQuery = new IndexQuery();
                Condition.convertFilterField(indexQuery, supplierQuery, SupplierDO.class);
                supplierIds  = supplierIndexAO.selectRegionSuppliers(supplierQuery);
                if (supplierIds == null) {
                    result.setIsSuccess(true);
                    result.setMsg("附近没有供应商可达产品");
                    return result;
                }
                
                //把supplierIds存入缓存
                try {
                    bcjRedis.setex(indexQuery.getCafe().getClass().getName() + indexQuery.getCafe().getCafeId(), supplierIds);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            
            indexQuery.getFilterField().put("supplierId", supplierIds);
            Condition.convertFilterField(indexQuery, ProductDO.class);
            SolrQuery query = SolrQueryUtil.convertQuery(indexQuery, solrConfig);
            SolrServer solrServer = SolrServerFactory.generateSolrServer("product");
            QueryResponse response = solrServer.query(query);
            
            result = SolrQueryUtil.buildResult(indexQuery, response, ProductDO.class);
           
        } catch (Exception e) {
            e.printStackTrace();
            result.setIsSuccess(false);
            result.setMsg("查询失败");
        }
        return result;
    }

}
