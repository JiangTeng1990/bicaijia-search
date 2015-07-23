package com.bicaijia.bcjsearch.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import com.bicaijia.bcjsearch.config.Constant;
import com.bicaijia.bcjsearch.config.SortOrder;
import com.bicaijia.bcjsearch.dao.CafeDao;
import com.bicaijia.bcjsearch.domain.Cafe;
import com.bicaijia.bcjsearch.domain.Pair;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.JsonUtils;
import com.bicaijia.bcjsearch.util.ParamKeyMap;
import com.bicaijia.bcjsearch.util.ParamKeyMap.ParamType;
import com.bicaijia.bcjsearch.util.StringUtil;

@Controller
public class ProductSearchController {
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ProductIndexAO productIndexAO;
    @Autowired
    private CafeDao cafeDao;
    
    @RequestMapping("/products")
    @ResponseBody
    public String products(Integer cafeId) {
        Result result = null;
        try {
            //indexQuery 的初始化
            Cafe cafe = cafeDao.selectByCafeId(cafeId);
            if (cafe == null) {
                result = new Result();
                result.setIsSuccess(false);
                result.setMsg("没有对应餐厅");
                return JsonUtils.toJsonString(result);
            }
            IndexQuery indexQuery = createIndexQuery(request);
            prepareIndexQuery(indexQuery, cafe);
            
            result = productIndexAO.search(indexQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return JsonUtils.toJsonString(result);
        
    }
    
    
    
    private IndexQuery createIndexQuery(HttpServletRequest request) throws Exception {
        IndexQuery indexQuery = new IndexQuery();
        
        Map<String, String[]> paramMap = request.getParameterMap();
        Set<Entry<String, String[]>> paramSet = paramMap.entrySet();
        
        //遍历所有参数
        for(Entry<String, String[]> entry : paramSet) {
            String name = entry.getKey();
            String value = entry.getValue()[0];
            //将字符串的[]去掉
            value = StringUtil.trim(value, "[", "]");
            
            Pair<String, ParamType> valuePair = ParamKeyMap.paramNameMap.get(name);
            if (null == valuePair) {
                continue;
            }
            
            String pairX = valuePair.getX();
            ParamType pairY = valuePair.getY();
            
            if (ParamType.SORT.equals(pairY)) {
                if ("sortField".equals(pairX)) {
                    indexQuery.setSortField(value);
                }
                if ("distanceSort".equals(pairX)) {
                    if ("0".equals(value)) {
                        indexQuery.setDistanceSort(SortOrder.ASC.getCode());
                    } else if ("1".equals(value)) {
                        indexQuery.setDistanceSort(SortOrder.DESC.getCode());
                    }
                }
            } else if (ParamType.QUERY.equals(pairY)) {
      //TODO 修改
                if ("query".equals(pairX)) {
                    String query = URLDecoder.decode(value, "UTF-8");
                    indexQuery.setQuery(query);
                }
            } else if (ParamType.PAGE.equals(pairY)) {
                if ("pageRows".equals(pairX)) {
                    Integer pageRows = Integer.parseInt(value);
                    indexQuery.setPageRows(pageRows);
                } else if ("pageNo".equals(pairX)) {
                    Integer pageNo = Integer.parseInt(value);
                    indexQuery.setPageNo(pageNo);
                }
            } else if (ParamType.LOCATION.equals(pairY)) {
                if ("x".equals(pairX)) {
                    Double xValue = Double.parseDouble(pairX);
                    indexQuery.setX(xValue);
                } else if ("y".equals(pairX)) {
                    Double yValue = Double.parseDouble(pairX);
                    indexQuery.setY(yValue);
                }
            } else if (ParamType.FACET.equals(pairY)) {
                indexQuery.setFacet(true);
                if ("facetField".equals(pairX)) {
                    indexQuery.setFacetField(pairX);
                } else if ("facetQuery".equals(pairX)) {
                    indexQuery.setFacetQuery(value);
                }
            } else if (ParamType.FILTER.equals(pairY)) {
                indexQuery.getFilterField().put(pairX, value);
            } else if (ParamType.FILTER_LIST.equals(pairY)) {
                List<String> list = StringUtil.toList(value, Constant.SPLIT_LIST_CHAR);
                indexQuery.getFilterField().put(pairX, list);
            } else if (ParamType.FILTER_RANGE.equals(pairY)) {
                String valx = StringUtil.toRangeStr(value, Constant.SPLIT_RANGE_CHAR);
                indexQuery.getFilterField().put(pairX, valx);
            }
            
            
        }
        
        return indexQuery;
    }
    
    private void prepareIndexQuery(IndexQuery indexQuery, Cafe cafe) {
        //做一些逻辑判断
       
        indexQuery.setCafe(cafe);
        indexQuery.setX(cafe.getLatitude());
        indexQuery.setY(cafe.getLongitude());
        
        Map<String, Object> map = indexQuery.getFilterField();
        if (map != null && map.get("productCategoryId") != null && map.get("parentId") != null) {
            map.remove("parentId");
        }
    }
    
}
