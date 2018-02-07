package com.snowlink.bean;


import java.util.Date;

import com.snowlink.utils.Pagination;


public class TsProject  extends Pagination{
    private Integer projectId;

    private String projectName;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updator;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
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
		return "TsProject [projectId=" + projectId + ", projectName=" + projectName + ", createTime=" + createTime
				+ ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + "]";
	}

    
    
}