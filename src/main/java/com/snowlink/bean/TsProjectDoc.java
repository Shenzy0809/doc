package com.snowlink.bean;

import java.util.Date;

import com.snowlink.utils.Pagination;

public class TsProjectDoc extends Pagination{
    private Integer docId;

    private String docName;

    private String docUrl;

    private Integer docTypeId;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updator;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    public Integer getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
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
		return "TsProjectDoc [docId=" + docId + ", docName=" + docName + ", docUrl=" + docUrl + ", docTypeId="
				+ docTypeId + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime
				+ ", updator=" + updator + "]";
	}
    
}