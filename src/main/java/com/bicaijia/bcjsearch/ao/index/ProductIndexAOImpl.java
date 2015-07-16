package com.bicaijia.bcjsearch.ao.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bicaijia.bcjsearch.config.ResultConfig;
import com.bicaijia.bcjsearch.config.SolrConfig;
import com.bicaijia.bcjsearch.domain.ProductDO;
import com.bicaijia.bcjsearch.factory.SolrServerFactory;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.redis.BcjRedis;
import com.bicaijia.bcjsearch.request.Page;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.Assert;
import com.bicaijia.bcjsearch.util.OrArrayList;
import com.bicaijia.bcjsearch.util.SolrQueryUtil;

@Service("productIndexAO")
public class ProductIndexAOImpl implements ProductIndexAO {
    
    @Autowired
    private BcjRedis bcjRedis;
    
    @Autowired
    private SolrConfig solrConfig;
    
    @Autowired
    private SupplierIndexAO supplierIndexAO;
    
    /* (non-Javadoc)
     * @see com.bicaijia.bcjsearch.ao.query.IndexAO#search(com.bicaijia.bcjsearch.ao.query.IndexQuery)
     */
    public Result search(IndexQuery indexQuery) {

        Result result = new Result();
        OrArrayList<String> supplierIds = null;
        try {
            supplierIds = (OrArrayList<String>) bcjRedis.get(indexQuery.getCafe().getClass().getName() + indexQuery.getCafe().getCafeId());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            if (supplierIds == null) {
                supplierIds  = supplierIndexAO.selectRegionSuppliers(indexQuery);
            }
            
            indexQuery.getFilterField().put("supplierId", supplierIds);
            SolrQuery query = SolrQueryUtil.convertQuery(indexQuery, solrConfig);
            SolrServer solrServer = SolrServerFactory.generateSolrServer("product");
            QueryResponse response = solrServer.query(query);
            SolrDocumentList docList = response.getResults();
            
            result.setIsSuccess(true);
            
            //打印测试
            for (SolrDocument doc : docList) {
                for (String fieldName : doc.getFieldNames()) {
                    System.out.println(fieldName + ": " + doc.getFieldValue(fieldName));
                }
            }
            
            int numFound = (int)docList.getNumFound();
            Page page = new Page(indexQuery.getPageNo(), indexQuery.getPageRows(), numFound);
            result.getModules().put(ResultConfig.PAGE, page);
            
            //bean列表
            List<ProductDO> products = response.getBeans(ProductDO.class);
            result.getModules().put(ResultConfig.BEAN_LIST, products);
            
            //分组统计
            List<FacetField> facetFieldList = response.getFacetFields();
            if (Assert.isNotEmptyList(facetFieldList)) {
                Map<String, Map<String, Integer>> facetFieldCountMap = new HashMap<String, Map<String,Integer>>(facetFieldList.size());
                for (FacetField facetField : facetFieldList) {
                    String facetFieldName = facetField.getName();
                    List<Count> countList = facetField.getValues();
                    HashMap<String, Integer> countMap = new HashMap<String, Integer>(countList.size());
                    for (Count count : countList) {
                        countMap.put(count.getName(), (int)count.getCount());
                    }
                    facetFieldCountMap.put(facetFieldName, countMap);
                }
                result.getModules().put(ResultConfig.FACET_FIELD_COUNT_MAP, facetFieldCountMap);
            }
            
            if (Assert.isNotEmptyMap(response.getFacetQuery())) {
                result.getModules().put(ResultConfig.FACET_QUERY_COUNT_MAP, response.getFacetQuery());
            }
            
         // 高亮显示
            if (null != indexQuery.isHighlightEnable() && indexQuery.isHighlightEnable()) {
                result.getModules().put(ResultConfig.HIGHLIGHT_MAP,
                        response.getHighlighting());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setIsSuccess(false);
        }
        return result;
    }

    public void optimizeProductIndex() {
        
    }
    

}
