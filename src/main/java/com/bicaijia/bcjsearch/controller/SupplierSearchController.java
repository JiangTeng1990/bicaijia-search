package com.bicaijia.bcjsearch.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bicaijia.bcjsearch.ao.index.SupplierIndexAO;
import com.bicaijia.bcjsearch.config.Constant;
import com.bicaijia.bcjsearch.config.SortOrder;
import com.bicaijia.bcjsearch.domain.Pair;
import com.bicaijia.bcjsearch.query.IndexQuery;
import com.bicaijia.bcjsearch.result.Result;
import com.bicaijia.bcjsearch.util.JsonUtils;
import com.bicaijia.bcjsearch.util.ParamKeyMap;
import com.bicaijia.bcjsearch.util.StringUtil;
import com.bicaijia.bcjsearch.util.ParamKeyMap.ParamType;

@Controller
public class SupplierSearchController {
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private SupplierIndexAO supplierIndexAO;
    
    @RequestMapping("/suppliers")
    @ResponseBody
    public String select() {
        Result result = null;
        try {
            IndexQuery indexQuery = createIndexQuery(request);
            result = supplierIndexAO.search(indexQuery);
            result.setMsg("查询成功");
        } catch (Exception e) {
            result = new Result();
            result.setIsSuccess(false);
            result.setMsg("查询失败");
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
 
}
