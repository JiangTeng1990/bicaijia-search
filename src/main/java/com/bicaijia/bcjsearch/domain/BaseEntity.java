package com.bicaijia.bcjsearch.domain;

import java.util.Date;

public class BaseEntity {
    
    private static final long serialVersionUID = 1L;
    private Long createId;// 创建人
    private Date createTime;// 创建日期
    private Long lastUpdateId;// 最后修改人
    private Date lastUpdateTime;// 最后修改日期
    private Integer isDeleted;// 是否删除
    /**
     * @return the createId
     */
    public Long getCreateId() {
        return createId;
    }
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * @return the lastUpdateId
     */
    public Long getLastUpdateId() {
        return lastUpdateId;
    }
    /**
     * @return the lastUpdateTime
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
    /**
     * @return the isDeleted
     */
    public Integer isDeleted() {
        return isDeleted;
    }
    /**
     * @param createId the createId to set
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @param lastUpdateId the lastUpdateId to set
     */
    public void setLastUpdateId(Long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }
    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
}
