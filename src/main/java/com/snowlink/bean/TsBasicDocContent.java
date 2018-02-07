package com.snowlink.bean;

public class TsBasicDocContent {
	
	private Integer id;
	
	private String doc_id;
	
	private String title_no;
	
    private String title;

    private Integer title_type;

    private Integer ordercode;

    private String content;

    private int pageNumber = 0;
	private int pageSize = 30;
	public Integer getId() {
		return id;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public String getTitle_no() {
		return title_no;
	}
	public String getTitle() {
		return title;
	}
	public Integer getTitle_type() {
		return title_type;
	}
	public Integer getOrdercode() {
		return ordercode;
	}
	public String getContent() {
		return content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public void setTitle_no(String title_no) {
		this.title_no = title_no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setTitle_type(Integer title_type) {
		this.title_type = title_type;
	}
	public void setOrdercode(Integer ordercode) {
		this.ordercode = ordercode;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "TsBasicDocContent [id=" + id + ", doc_id=" + doc_id + ", title_no=" + title_no + ", title=" + title
				+ ", title_type=" + title_type + ", ordercode=" + ordercode + ", content=" + content + ", pageNumber="
				+ pageNumber + ", pageSize=" + pageSize + "]";
	}
	

	




  

   
}