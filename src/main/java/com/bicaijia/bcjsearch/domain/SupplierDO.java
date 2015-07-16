package com.bicaijia.bcjsearch.domain;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class SupplierDO {
    @Field
    private String id;
    @Field
    private String icon;//indexed=false
    @Field
    private String name;
    @Field
    private String alias;
    @Field
    private Integer regionId;
    @Field
    private String address;
    @Field
    private String contactName;//联系人
    @Field
    private String contactPhone;
    @Field
    private Integer dailyOrderUpperLimit;//每日接单量 false
    @Field
    private String description; //接单描述
    @Field
    private int stars; //评分
    @Field
    private int logisticScope;//配送范围
    @Field
    private String bankAccount; //银行开户人
    @Field
    private String bankName; //银行开户行
    @Field
    private String bankCardNumber; //银行卡号
    @Field
    private Integer status; //状态 0：未审核 1：审核通过
    @Field
    private Integer sales; //负责销售员
    @Field
    private Integer salesManager; //销售主管
    @Field
    private String rootCategories; // 一级分类
    @Field
    private boolean recommended; //是否推荐供应商
    @Field
    private String tags; //供应商标签
    @Field
    private String longitude; //经度
    @Field
    private String latitude; //纬度
    @Field
    private List<Integer> aroundCafeId;//服务区域的餐厅
    @Field
    private List<Double> arroundCafeDistance;//附近
    @Field
    private List<Integer> supplyRegionId;//服务区域的id

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the regionId
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactPhone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return the dailyOrderUpperLimit
     */
    public Integer getDailyOrderUpperLimit() {
        return dailyOrderUpperLimit;
    }

    /**
     * @param dailyOrderUpperLimit the dailyOrderUpperLimit to set
     */
    public void setDailyOrderUpperLimit(Integer dailyOrderUpperLimit) {
        this.dailyOrderUpperLimit = dailyOrderUpperLimit;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * @param stars the stars to set
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * @return the logisticScope
     */
    public int getLogisticScope() {
        return logisticScope;
    }

    /**
     * @param logisticScope the logisticScope to set
     */
    public void setLogisticScope(int logisticScope) {
        this.logisticScope = logisticScope;
    }

    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankCardNumber
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }

    /**
     * @param bankCardNumber the bankCardNumber to set
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the sales
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * @return the salesManager
     */
    public Integer getSalesManager() {
        return salesManager;
    }

    /**
     * @param salesManager the salesManager to set
     */
    public void setSalesManager(Integer salesManager) {
        this.salesManager = salesManager;
    }

    /**
     * @return the rootCategories
     */
    public String getRootCategories() {
        return rootCategories;
    }

    /**
     * @param rootCategories the rootCategories to set
     */
    public void setRootCategories(String rootCategories) {
        this.rootCategories = rootCategories;
    }

    /**
     * @return the recommended
     */
    public boolean isRecommended() {
        return recommended;
    }

    /**
     * @param recommended the recommended to set
     */
    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the supplyRegionId
     */
    public List<Integer> getSupplyRegionId() {
        return supplyRegionId;
    }

    /**
     * @param supplyRegionId the supplyRegionId to set
     */
    public void setSupplyRegionId(List<Integer> supplyRegionId) {
        this.supplyRegionId = supplyRegionId;
    }

    /**
     * @return the aroundCafeId
     */
    public List<Integer> getAroundCafeId() {
        return aroundCafeId;
    }

    /**
     * @param aroundCafeId the aroundCafeId to set
     */
    public void setAroundCafeId(List<Integer> aroundCafeId) {
        this.aroundCafeId = aroundCafeId;
    }

    /**
     * @return the arroundCafeDistance
     */
    public List<Double> getArroundCafeDistance() {
        return arroundCafeDistance;
    }

    /**
     * @param arroundCafeDistance the arroundCafeDistance to set
     */
    public void setArroundCafeDistance(List<Double> arroundCafeDistance) {
        this.arroundCafeDistance = arroundCafeDistance;
    }
    
    
}
