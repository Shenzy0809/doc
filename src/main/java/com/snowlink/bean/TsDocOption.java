package com.snowlink.bean;

import java.sql.Date;

public class TsDocOption {
    private Integer id;

    private String doc_name;
    
    private Date create_time;
    
    private String creator;
    
    private Date update_time;
    
    private String updator;

	public Integer getId() {
		return id;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public String getCreator() {
		return creator;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public String getUpdator() {
		return updator;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@Override
	public String toString() {
		return "TsDocOption [id=" + id + ", doc_name=" + doc_name + ", create_time=" + create_time + ", creator="
				+ creator + ", update_time=" + update_time + ", updator=" + updator + "]";
	}



    
    
}