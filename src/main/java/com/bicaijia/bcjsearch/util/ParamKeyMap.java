package com.bicaijia.bcjsearch.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bicaijia.bcjsearch.domain.Pair;

/**
 * 查询参数key的映射关系
 * 
 * @author lilin, daixing
 * 
 */
public class ParamKeyMap {

    public static enum ParamType {
       
        LOCATION("location") // 位置
        , QUERY("query") // 查询关键词
        , FILTER("filter") // 过滤查询
        , FILTER_LIST("filterList") // 过滤查询。列表型
        , FILTER_RANGE("filterRange") // 过滤查询。范围型
        , PAGE("page") // 分页信息
        , FACET("facet") // 分组统计
        , SORT("sort") // 排序
        , RECMD("recmd") // 推荐
        ;

        private String type;

        private ParamType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    static Pair<String, ParamType> newPair(String key, ParamType val) {
        return new Pair<String, ParamType>(key, val);
    }

    // 参数名称对应表。
    public static Map<String, Pair<String, ParamType>> paramNameMap;
    static {
        paramNameMap = new ConcurrentHashMap<String, Pair<String, ParamType>>(
                200);


        // 关键词
        paramNameMap.put("q", newPair("query", ParamType.QUERY));// 关键词字符串

        // 地址
        paramNameMap.put("x", newPair("x", ParamType.LOCATION)); // 经度
        paramNameMap.put("y", newPair("y", ParamType.LOCATION));// 纬度

        // 分页
        paramNameMap.put("pgN", newPair("pageNo", ParamType.PAGE));// 到第几页。1开始
        paramNameMap.put("pgR", newPair("pageRows", ParamType.PAGE));// 每页行数

        // 排序
        paramNameMap.put("stF", newPair("sortField", ParamType.SORT));// 排序属性
        // paramNameMap.put("stO", newPair("sortOrder", ParamType.SORT));//
        // 排序方向。0升序，1降序
        paramNameMap.put("dS", newPair("distanceSort", ParamType.SORT));// 是否按距离排序。0升序，1降序

        // 分组统计
        paramNameMap.put("fcF", newPair("facetField", ParamType.FACET));// 分组的属性
        paramNameMap.put("fcQ", newPair("facetQuery", ParamType.FACET));// 分组的查询

        // 高亮
        // paramNameMap.put("hlY", "highlightEnable");
        // paramNameMap.put("hlF", "highlightField");
        
        //推荐相关
        paramNameMap.put("tag", newPair("tag", ParamType.RECMD));// 打标的标签，不显示前端的标签
        paramNameMap.put("stag", newPair("showTag", ParamType.RECMD));// 打标的标签，需要显示前端的标签
        paramNameMap.put("rc", newPair("rcdScore", ParamType.RECMD));// 自动数据分析计算出的推荐分值
        paramNameMap.put("mrc", newPair("manualRcdScore", ParamType.RECMD));// 手动添加的推荐分值
        
        
        //商品 
        paramNameMap.put("cat", newPair("categoryName", ParamType.FILTER_LIST));// 商店id
        paramNameMap.put("catId", newPair("productCategoryId", ParamType.FILTER));
        paramNameMap.put("rootCatId", newPair("parentId", ParamType.FILTER));
        paramNameMap.put("cafeId", newPair("arroundCafeId", ParamType.FILTER));
        
        paramNameMap.put("sort", newPair("sortField", ParamType.SORT));
       
    }

}
