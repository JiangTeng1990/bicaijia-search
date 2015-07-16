package com.bicaijia.bcjsearch.domain;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

public class ProductDO {
    @Field
    private String id;
    @Field
    private String productName;
    @Field
    private String productCode;
    @Field
    private String measurementMethod;
    @Field
    private String productRemark;
    @Field
    private String guidAttr;//导购属性
    
    //产品分类信息
    @Field
    private Long productCategoryId;
    @Field
    private String categoryName;
    @Field
    private Integer levelId;//分类级别
    @Field
    private Long parentId;//父一级分类
    @Field
    private String icon;//图标
    @Field
    private Integer showOrder;//展现顺序
    
//  属性字典 集合    
    @Field
    private List<Long> productAttrDictId;
    @Field
    private List<Integer> productCategoryDictId;
    @Field
    private List<String> productCategoryDictName;
      
    //供应商id + name
    @Field
    private List<Integer> supplierId;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the measurementMethod
     */
    public String getMeasurementMethod() {
        return measurementMethod;
    }

    /**
     * @param measurementMethod the measurementMethod to set
     */
    public void setMeasurementMethod(String measurementMethod) {
        this.measurementMethod = measurementMethod;
    }

    /**
     * @return the productRemark
     */
    public String getProductRemark() {
        return productRemark;
    }

    /**
     * @param productRemark the productRemark to set
     */
    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    /**
     * @return the guidAttr
     */
    public String getGuidAttr() {
        return guidAttr;
    }

    /**
     * @param guidAttr the guidAttr to set
     */
    public void setGuidAttr(String guidAttr) {
        this.guidAttr = guidAttr;
    }

    /**
     * @return the productCategoryId
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * @param productCategoryId the productCategoryId to set
     */
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the levelId
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * @param levelId the levelId to set
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the showOrder
     */
    public Integer getShowOrder() {
        return showOrder;
    }

    /**
     * @param showOrder the showOrder to set
     */
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * @return the productAttrDictId
     */
    public List<Long> getProductAttrDictId() {
        return productAttrDictId;
    }

    /**
     * @param productAttrDictId the productAttrDictId to set
     */
    public void setProductAttrDictId(List<Long> productAttrDictId) {
        this.productAttrDictId = productAttrDictId;
    }

    /**
     * @return the productCategoryDictId
     */
    public List<Integer> getProductCategoryDictId() {
        return productCategoryDictId;
    }

    /**
     * @param productCategoryDictId the productCategoryDictId to set
     */
    public void setProductCategoryDictId(List<Integer> productCategoryDictId) {
        this.productCategoryDictId = productCategoryDictId;
    }

    /**
     * @return the productCategoryDictName
     */
    public List<String> getProductCategoryDictName() {
        return productCategoryDictName;
    }

    /**
     * @param productCategoryDictName the productCategoryDictName to set
     */
    public void setProductCategoryDictName(List<String> productCategoryDictName) {
        this.productCategoryDictName = productCategoryDictName;
    }

    /**
     * @return the supplier
     */
    public List<Integer> getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(List<Integer> supplierId) {
        this.supplierId = supplierId;
    }

    
}
