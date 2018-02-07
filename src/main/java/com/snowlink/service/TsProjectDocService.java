package com.snowlink.service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDoc;

public interface TsProjectDocService {

	/**
	 * ģ����ѯ
	 * @param project
	 * @return
	 */
	public JSONObject BlurryQuery(TsProjectDoc project);
	
	/**
	 * 添加上传文件记录
	 */
	public int insertUpload(TsProjectDoc project);
	
	public String querydocName(String url);
	
	public int delete(int id);
}
