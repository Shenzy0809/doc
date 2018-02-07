package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.dao.TsProjectDocMapper;
import com.snowlink.service.TsProjectDocService;

@Service
public class TsProjectDocServiceImpl implements TsProjectDocService {
	@Autowired
	private TsProjectDocMapper mapper;

	public JSONObject BlurryQuery(TsProjectDoc project) {
		// TODO Auto-generated method s
		JSONObject obj = new JSONObject();
		List<TsProjectDoc> list = mapper.BlurryQuery(project);
		int count = mapper.QueryCount(project);
		obj.put("rows", list); 
		obj.put("total", count); 
		return obj;
	}

	public int insertUpload(TsProjectDoc project) {
		// TODO Auto-generated method stub
		return mapper.insertUpload(project);
	}

	public String querydocName(String url) {
		return mapper.querydocName(url);
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}
	
}
