package com.bicaijia.bcjsearch.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BaseEntity implements Serializable{
    private String sortProperty;//排序字段
    private String sortOrder;//排序方式ASC升序DESC降序
    private Long createId;// 创建人
    private Date createTime;// 创建日期
    private Long lastUpdateId;// 最后修改人
    private Date lastUpdateTime;// 最后修改日期
    private Integer isDeleted;// 是否删除
    public String getSortProperty() {
        return sortProperty;
    }
    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }
    public String getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Long getCreateId() {
        return createId;
    }
    public void setCreateId(Long createId) {
        this.createId = createId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getLastUpdateId() {
        return lastUpdateId;
    }
    public void setLastUpdateId(Long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
