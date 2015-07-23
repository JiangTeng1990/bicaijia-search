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
    private String productRemark;
    @Field
    private String guidAttr;//导购属性
    
    //产品分类信息
    @Field
    private Integer productCategoryId;
    @Field
    private String categoryName;
    @Field
    private Integer levelId;//分类级别
    @Field
    private Integer parentId;//父一级分类
    @Field
    private String icon;//图标
    @Field
    private Integer showOrder;//展现顺序
    @Field
    private Integer salesCount;
    @Field
    private Integer collCount;
    @Field
    private Float minPrice;
    
//  属性字典 集合    
    @Field
    private List<Integer> productAttrId;
    @Field
    private List<Integer> categoryAttrId;
    @Field
    private List<Integer> categoryAttrValueId;
     
    //供应商id + name
    @Field
    private List<Integer> supplierId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
    }

    public String getGuidAttr() {
        return guidAttr;
    }

    public void setGuidAttr(String guidAttr) {
        this.guidAttr = guidAttr;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public List<Integer> getProductAttrId() {
        return productAttrId;
    }

    public void setProductAttrId(List<Integer> productAttrId) {
        this.productAttrId = productAttrId;
    }

    public List<Integer> getCategoryAttrId() {
        return categoryAttrId;
    }

    public void setCategoryAttrId(List<Integer> categoryAttrId) {
        this.categoryAttrId = categoryAttrId;
    }

    public List<Integer> getCategoryAttrValueId() {
        return categoryAttrValueId;
    }

    public void setCategoryAttrValueId(List<Integer> categoryAttrValueId) {
        this.categoryAttrValueId = categoryAttrValueId;
    }

    public List<Integer> getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(List<Integer> supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getCollCount() {
        return collCount;
    }

    public void setCollCount(Integer collCount) {
        this.collCount = collCount;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }
   
}
