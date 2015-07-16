package com.bicaijia.bcjsearch.entity;

public class Cafe extends BaseEntity {
    /**
     * cafe_id  int(11) primary key auto_increment comment '餐厅信息主键',
    relate_cafe_id int(11) comment '关联餐厅信息主键',
    audit_status  tinyint comment '审核状态,1表示已审核，0表示末审核',
    longitude  double(12,7)   comment '经度',
    city_id  int comment '城市ID',
    region_id  int comment '区域ID',
    latitude   double(12,7)   comment '纬度',
    address   varchar(200)  comment '收货地址',
    contact_person  varchar(30) comment '联系人',
    contact_mobile  varchar(15) comment '联系人手机',
    principal   varchar(30)  comment '餐厅负责人',
    cafe_name  varchar(100)  comment '餐厅名称',
    sales_id   smallint  comment '业务负员编号',
    create_id    int(11) comment '创建ID',
    create_time datetime comment '创建时间',
    last_update_id  int(11) comment '最后修改人id',
    is_deleted  tinyint default 0 comment '删除标识，0末删除，1已删除',
    query_cafe_id int(11) comment '冗余字段，用来查询，保存关联餐厅与餐厅ID'，
     */
    private Integer cafeId;
    private Cafe relateCafe;
    private Integer auditStatus;
    private Double longitude;
    private Double latitude;
    private String address;
    private String contactPerson;
    private String contactMobile;
    private String principal;
    private String cafeName;
    private Integer queryCafeId;
    private Integer regionId;
    
    
    
    public Integer getRegionId() {
        return regionId;
    }
   
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public Integer getCafeId() {
        return cafeId;
    }
    public void setCafeId(Integer cafeId) {
        this.cafeId = cafeId;
    }
    public Cafe getRelateCafe() {
        return relateCafe;
    }
    public void setRelateCafe(Cafe relateCafe) {
        this.relateCafe = relateCafe;
    }
    public Integer getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getContactMobile() {
        return contactMobile;
    }
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public String getCafeName() {
        return cafeName;
    }
    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }
    public Integer getQueryCafeId() {
        return queryCafeId;
    }
    public void setQueryCafeId(Integer queryCafeId) {
        this.queryCafeId = queryCafeId;
    }
    
}
