package com.bicaijia.bcjsearch.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.bicaijia.bcjsearch.config.Constant;
import com.bicaijia.bcjsearch.config.ResultConfig;
import com.bicaijia.bcjsearch.config.SolrConfig;
import com.bicaijia.bcjsearch.request.Page;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.AndArrayList;
import com.bicaijia.bcjsearch.util.Assert;
import com.bicaijia.bcjsearch.util.OrArrayList;
import com.bicaijia.bcjsearch.util.StringUtil;

/**
 * @author JiangTeng
 *
 */
public class SolrQueryUtil {
    
    private final static Map<String, ORDER> solrOrderMap = new ConcurrentHashMap<String, SolrQuery.ORDER>(2);
    static {
        solrOrderMap.put("asc", ORDER.asc);
        solrOrderMap.put("desc", ORDER.desc);
    }
    
    /**
     * 将字符串转化为solr中的排序方式对象ORDER
     * 
     * @param ordermark
     * @return
     * @throws Exception
     */
    private static ORDER getORDER(String ordermark) throws Exception {
        ORDER order = solrOrderMap.get(ordermark);
        if (null == order) {
            return ORDER.asc;
        }
        return order;
    }
    
    /**
     * 把 indexQuery 转换成 SolrQuery
     * @param indexQuery
     * @param solrConfig
     * @return
     * @throws Exception
     */
    public static SolrQuery convertQuery(IndexQuery indexQuery, SolrConfig solrConfig) throws Exception {
        if (null == indexQuery || null == solrConfig) {
            throw new Exception(" indexQuery is null or solrConfig is null, indexQuery=" + indexQuery + ", solrConfig=" + solrConfig);
        }
        SolrQuery solrQuery = toBaseSolrQuery(indexQuery, solrConfig);
        convertKeyword(indexQuery, solrQuery);
        convertFilterQuery(indexQuery, solrQuery);
        return solrQuery;
    }
    
    /**
     * @param indexQuery
     * @param solrQuery
     */
    private static void convertKeyword(IndexQuery indexQuery, SolrQuery solrQuery) {
        Map<String, List<String>> keywordMap = indexQuery.getKeywords();
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        if (Assert.isNotEmptyString(indexQuery.getQuery())) {
            builder.append(indexQuery.getQuery());
            isFirst = false;
        }
        for (Map.Entry<String, List<String>> entry : keywordMap.entrySet()) {
            String fieldKeyword = toAndFilterQuery(entry.getKey(), entry.getValue());
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(AND);
            }
            builder.append(fieldKeyword);
        }
        solrQuery.setQuery(isFirst ? "*:*" : builder.toString());
    }
    
    private static void convertFilterQuery(IndexQuery indexQuery, SolrQuery solrQuery) throws Exception {
        Map<String, Object> obj = indexQuery.getFilterField();
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (null == val) {
                continue;
            }
            String queryStr = null;
            if (val instanceof AndArrayList) {
                queryStr = SolrQueryUtil.toAndFilterQuery(key, (List<?>)val);
            } else if (val instanceof OrArrayList || val instanceof List) {
                queryStr = SolrQueryUtil.toOrFilterQuery(key, (List<?>)val);
            } else {
                queryStr = SolrQueryUtil.toFilterQuery(key, val);
            } 
            if (null == queryStr) {
                continue;
            }
            solrQuery.addFilterQuery(queryStr);
        }
    }
    
    /**
     * 将 IndexQuery 转化成 SolrQuery
     * @param indexQuery
     * @param solrConfig
     * @return
     * @throws Exception
     */
    private static SolrQuery toBaseSolrQuery(IndexQuery indexQuery, SolrConfig solrConfig) throws Exception {
        
        if (null == indexQuery || null == solrConfig) {
            throw new Exception(" indexQuery is null or solrConfig is null, indexQuery=" + indexQuery + ", solrConfig=" + solrConfig);
        }
        
        SolrQuery solrQuery = new SolrQuery();
        
        // 排序
        Map<String, String> sortFieldMap = StringUtil.splitToMap(indexQuery.getSortField(), Constant.SPLIT_LIST_CHAR, Constant.SPLIT_SORTORDER_CHAR);
        for (Map.Entry<String, String> entry : sortFieldMap.entrySet()) {
            solrQuery.addSort(entry.getKey(), getORDER(entry.getValue()));
        }
        
        //分页
        int start = indexQuery.getPageRows(solrConfig.getPageRows()) * (indexQuery.getPageNo(1) - 1);
        solrQuery.setStart(start);
        solrQuery.setRows(indexQuery.getPageRows(solrConfig.getPageRows()));
        
        //查询出来显示的字段
        if (indexQuery.getFields().size() > 0) {
            solrQuery.setFields(indexQuery.getFields().toArray(new String[indexQuery.getFields().size()]));
        }
        //分组统计
        if (null != indexQuery.isFacet() && indexQuery.isFacet()) {
            solrQuery.setFacet(true);
            solrQuery.setFacetLimit(indexQuery.getFacetMincount(solrConfig.getFacetLimit()));
            solrQuery.setFacetMinCount(indexQuery.getFacetMincount(solrConfig.getFacetMinCount()));
            
            if (null != indexQuery.getFacetField()) {
                solrQuery.addFacetField(StringUtil.spiltToStringArray(indexQuery.getFacetField(), Constant.SPLIT_LIST_CHAR));
            }
           
            if (null != indexQuery.getFacetQuery()) {
                String[] facetQueryArray = StringUtil.spiltToStringArray(indexQuery.getFacetQuery(), Constant.SPLIT_LIST_CHAR);
                for (String facetQuery : facetQueryArray) {
                    solrQuery.addFacetQuery(facetQuery);
                }
            }
        }
        
        //高亮显示
        if (null != indexQuery.isHighlightEnable() && indexQuery.isHighlightEnable()) {
            solrQuery.setHighlight(true);
            
            if (null != indexQuery.getHighlightField()) {
                String[] highlightFieldArray = StringUtil.spiltToStringArray(indexQuery.getHighlightField(), Constant.SPLIT_LIST_CHAR);
                for (String highlightField : highlightFieldArray) {
                    solrQuery.addHighlightField(highlightField);
                }
            }
            
            solrQuery.setHighlightSimplePre(indexQuery.getHighlightPre());
            solrQuery.setHighlightSimplePost(indexQuery.getHighlightPost());
        }
        
        return solrQuery;
    }
    
    private final static String AND = " AND ";
    private final static String OR = " OR ";
    private final static String BLANK = " ";
    
    private static String toFilterQuery(String field, Object value) {
        if (null == value) return null;
        return field + ":" + value;
    }
    
    private static String toAndFilterQuery(String field, List<?> list) {
        if (null == list || 0 >= list.size()) return null;
        return field + ":" + StringUtil.toListStatement("(", list, AND, ")");
    }
    
    private static String toOrFilterQuery(String field, List<?> list) {
        if (null == list || 0 >= list.size()) return null;
        return field + ":" + StringUtil.toListStatement("(", list, OR, ")");
    }
    
    @SuppressWarnings("unused")
    private static String toBlankSegFilterQuery(String field, List<?> list) {
        if (null == list || 0 >= list.size()) return null;
        return field + ":" + StringUtil.toListStatement("(", list, BLANK, ")");
    }
    
    /**
     * 转化查询结果
     * @param indexQuery
     * @param response
     * @return
     */
    public static <T> Result buildResult(IndexQuery indexQuery, QueryResponse response, Class<T> clazz) {
        
        //分析查询的结果
        SolrDocumentList docList = response.getResults();
        Result result = new Result();
        result.setIsSuccess(true);
        
     // 分页
        int numFound = (int) (docList.getNumFound());
        Page page = new Page(indexQuery.getPageNo(), indexQuery.getPageRows(), numFound);
        result.getModules().put(ResultConfig.PAGE, page);
        List<T> beanList = response.getBeans(clazz);
        result.getModules().put(ResultConfig.BEAN_LIST, beanList);
        
     // 分组统计
        List<FacetField> facetFieldList = response.getFacetFields();
        if (Assert.isNotEmptyList(facetFieldList)) {
            Map<String, Map<String, Integer>> facetFieldCountMap = new HashMap<String, Map<String, Integer>>(
                    facetFieldList.size());
            for (FacetField facetFieldA : facetFieldList) {
                String facetFieldName = facetFieldA.getName();
                List<Count> countList = facetFieldA.getValues();
                Map<String, Integer> countMap = new HashMap<String, Integer>(
                        countList.size());
                for (Count count : countList) {
                    countMap.put(count.getName(), (int) (count.getCount()));
                }
                facetFieldCountMap.put(facetFieldName, countMap);
            }
            result.getModules().put(ResultConfig.FACET_FIELD_COUNT_MAP,
                    facetFieldCountMap);
        }
        // 分组统计
        if (Assert.isNotEmptyMap(response.getFacetQuery())) {
            result.getModules().put(ResultConfig.FACET_QUERY_COUNT_MAP,
                    response.getFacetQuery());
        }

        // 高亮显示
        if (null != indexQuery.isHighlightEnable()
                && indexQuery.isHighlightEnable()) {
            result.getModules().put(ResultConfig.HIGHLIGHT_MAP,
                    response.getHighlighting());
        }
        return result;
        
    }
    
}
