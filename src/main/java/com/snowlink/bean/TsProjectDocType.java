package com.snowlink.bean;

import java.util.Date;

import com.snowlink.utils.Pagination;

public class TsProjectDocType extends Pagination{
    private Integer docTypeId;

    private String docTypeName;

    private Integer projectId;

    private Integer ordercode;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updator;

    public Integer getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName == null ? null : docTypeName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

	@Override
	public String toString() {
		return "TsProjectDocType [docTypeId=" + docTypeId + ", docTypeName=" + docTypeName + ", projectId=" + projectId
				+ ", ordercode=" + ordercode + ", createTime=" + createTime + ", creator=" + creator + ", updateTime="
				+ updateTime + ", updator=" + updator + "]";
	}
    
}