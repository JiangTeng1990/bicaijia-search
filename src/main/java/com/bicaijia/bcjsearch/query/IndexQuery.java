package com.bicaijia.bcjsearch.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bicaijia.bcjsearch.domain.Cafe;

/**
 * 索引用的query
 * @author JiangTeng
 *
 */
public class IndexQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final int DefaultPageNo = 1;
    private static final int DefaultPageRows = 20;
    
    
    private Cafe cafe;//餐厅
    
    
    private Double x;// 经度
    private Double y;// 纬度
    
    private Map<String, List<String>> keywords = new HashMap<String, List<String>>();
    private String query; // 关键词
    
    private Map<String, Object> filterField = new HashMap<String, Object>();
    
    private List<String> fields = new ArrayList<String>();//要查询出的字段
    
    private Integer pageNo;
    private Integer pageRows;
    
    private String distanceSort; // 按距离排序 asc 或 desc
    private String sortField; // 排序 "id:desc,sell:desc"
    
    private Boolean facet; // 是否要分组
    private Integer facetLimit; // 返回facet的条数
    private Integer facetMincount; // 统计数量的最小值
    private String facetField; // 分组统计的字段 filed1,field2
    private String facetQuery; // field1:[0 TO 40],field2:[20 TO 30]
    
    private Boolean highlightEnable; // 是否高亮
    private String highlightField; // 高亮字段
    private String highlightPre; //高亮前缀
    private String highlightPost; //高亮后缀
    
    private Boolean isReplenish; // 是否开启扩展, 表示在结果比较少的情况下是否需要扩展查询
    
    
    public int getStartRow() {
        int pageRows = getPageRows(DefaultPageRows);
        int pageNo = getPageNo(DefaultPageNo);
        return (pageNo - 1) * pageRows;
    }
    
    public int getEndRow() {
        int pageRows = getPageRows(DefaultPageRows);
        int pageNo = getPageNo(DefaultPageNo);
        return pageNo * pageRows;
    }


    /**
     * @return the x
     */
    public Double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public Double getY() {
        return y;
    }

    /**
     * @return the keywords
     */
    public Map<String, List<String>> getKeywords() {
        return keywords;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @return the filterField
     */
    public Map<String, Object> getFilterField() {
        return filterField;
    }

    /**
     * @return the pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }
    
    public Integer getPageNo(Integer defaultPageNo) {
        if (null == pageNo || 0 >= pageNo.intValue()) {
            pageNo = defaultPageNo;
        }
        return pageNo;
    }

    /**
     * @return the pageRows
     */
    public Integer getPageRows() {
        return pageRows;
    }
    
    public Integer getPageRows(int defaultPageRows) {
        if (null == pageRows || 0 >= pageRows.intValue()) {
            pageRows = defaultPageRows;
        }
        return pageRows;
    }

    /**
     * @return the distanceSort
     */
    public String getDistanceSort() {
        return distanceSort;
    }

    /**
     * @return the sortField
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * @return the facet
     */
    public Boolean isFacet() {
        return facet;
    }

    /**
     * @return the facetLimit
     */
    public Integer getFacetLimit() {
        return facetLimit;
    }

    /**
     * @return the facetMincount
     */
    public Integer getFacetMincount() {
        return facetMincount;
    }
    
    public Integer getFacetMincount(int defaultFacetMincount) {
        if (null == facetMincount || 0 >= facetMincount.intValue()) {
            facetMincount = defaultFacetMincount;
        }
        return facetMincount;
    }
    
    /**
     * @return the facetField
     */
    public String getFacetField() {
        return facetField;
    }

    /**
     * @return the facetQuery
     */
    public String getFacetQuery() {
        return facetQuery;
    }

    /**
     * @return the highlightEnable
     */
    public Boolean isHighlightEnable() {
        return highlightEnable;
    }

    /**
     * @return the highlightField
     */
    public String getHighlightField() {
        return highlightField;
    }

    /**
     * @return the highlightPre
     */
    public String getHighlightPre() {
        return highlightPre;
    }

    /**
     * @return the highlightPost
     */
    public String getHighlightPost() {
        return highlightPost;
    }

    /**
     * @return the isReplenish
     */
    public Boolean getIsReplenish() {
        return isReplenish;
    }


    /**
     * @param x the x to set
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(Map<String, List<String>> keywords) {
        this.keywords = keywords;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @param filterField the filterField to set
     */
    public void setFilterField(Map<String, Object> filterField) {
        this.filterField = filterField;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @param pageRows the pageRows to set
     */
    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    /**
     * @param distanceSort the distanceSort to set
     */
    public void setDistanceSort(String distanceSort) {
        this.distanceSort = distanceSort;
    }

    /**
     * @param sortField the sortField to set
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * @param facet the facet to set
     */
    public void setFacet(Boolean facet) {
        this.facet = facet;
    }

    /**
     * @param facetLimit the facetLimit to set
     */
    public void setFacetLimit(Integer facetLimit) {
        this.facetLimit = facetLimit;
    }

    /**
     * @param facetMincount the facetMincount to set
     */
    public void setFacetMincount(Integer facetMincount) {
        this.facetMincount = facetMincount;
    }

    /**
     * @param facetField the facetField to set
     */
    public void setFacetField(String facetField) {
        this.facetField = facetField;
    }

    /**
     * @param facetQuery the facetQuery to set
     */
    public void setFacetQuery(String facetQuery) {
        this.facetQuery = facetQuery;
    }

    /**
     * @param highlightEnable the highlightEnable to set
     */
    public void setHighlightEnable(Boolean highlightEnable) {
        this.highlightEnable = highlightEnable;
    }

    /**
     * @param highlightField the highlightField to set
     */
    public void setHighlightField(String highlightField) {
        this.highlightField = highlightField;
    }

    /**
     * @param highlightPre the highlightPre to set
     */
    public void setHighlightPre(String highlightPre) {
        this.highlightPre = highlightPre;
    }

    /**
     * @param highlightPost the highlightPost to set
     */
    public void setHighlightPost(String highlightPost) {
        this.highlightPost = highlightPost;
    }

    /**
     * @param isReplenish the isReplenish to set
     */
    public void setIsReplenish(Boolean isReplenish) {
        this.isReplenish = isReplenish;
    }

    /**
     * @return the cafe
     */
    public Cafe getCafe() {
        return cafe;
    }

    /**
     * @param cafe the cafe to set
     */
    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
    

}
