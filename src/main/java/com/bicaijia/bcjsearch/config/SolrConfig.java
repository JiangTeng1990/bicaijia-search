package com.bicaijia.bcjsearch.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author JiangTeng
 *
 */
@Component("solrConfig")
public class SolrConfig {
    
    
    /**
     * 分页 当前页码
     */
    private Integer pageNo;
    
    /**
     * 每页的记录条数
     */
    private Integer pageRows;
    
    /**
     * 排序
     */
    private String order;
    
    /**
     * 是否分组
     */
    private Boolean facet;
    
    /**
     * 分组返回的记录上限
     */
    private Integer facetLimit;
    
    
    /**
     * 每个分组返回的最少记录数量
     */
    private Integer facetMinCount;
    
    /**
     * 是否高亮
     */
    private Boolean highlightEnable;

  
    /**
     * @return the pageNo
     */
    public Integer getPageNo() {
        return null == pageNo ? DefaultSolrConfig.PageNo : pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageRows
     */
    public Integer getPageRows() {
        return (null == pageRows || 0 >= pageRows) ? DefaultSolrConfig.PageRows : pageRows;
    }

    /**
     * @param pageRows the pageRows to set
     */
    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return null == order ? DefaultSolrConfig.Order : order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the facet
     */
    public Boolean getFacet() {
        return null == facet ? DefaultSolrConfig.Facet : facet;
    }

    /**
     * @param facet the facet to set
     */
    public void setFacet(Boolean facet) {
        this.facet = facet;
    }

    /**
     * @return the facetLimit
     */
    public Integer getFacetLimit() {
        return null == facetLimit ? DefaultSolrConfig.FacetLimit : facetLimit;
    }

    /**
     * @param facetLimit the facetLimit to set
     */
    public void setFacetLimit(Integer facetLimit) {
        this.facetLimit = facetLimit;
    }

    /**
     * @return the facetMinCount
     */
    public Integer getFacetMinCount() {
        return null == facetMinCount ? DefaultSolrConfig.FacetMinCount : facetMinCount;
    }

    /**
     * @param facetMinCount the facetMinCount to set
     */
    public void setFacetMinCount(Integer facetMinCount) {
        this.facetMinCount = facetMinCount;
    }

    /**
     * @return the highlightEnable
     */
    public Boolean getHighlightEnable() {
        return null == highlightEnable ? DefaultSolrConfig.HighlightEnable : highlightEnable;
    }

    /**
     * @param highlightEnable the highlightEnable to set
     */
    public void setHighlightEnable(Boolean highlightEnable) {
        this.highlightEnable = highlightEnable;
    }
   
    
}
