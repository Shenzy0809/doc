package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.dao.TsProjectDocTypeMapper;
import com.snowlink.service.TsProjectDocTypeService;

@Service
public class TsProjectDocTypeServiceImpl implements TsProjectDocTypeService {

	@Autowired
	private TsProjectDocTypeMapper mapper;

	public JSONObject QueryAllBycondition(TsProjectDocType project) {
		JSONObject obj = new JSONObject();
		List<TsProjectDocType> list = mapper.QueryAllBycondition(project);
		int count = mapper.QueryCount(project);
		obj.put("rows", list); 
		obj.put("total", count); 
		return obj;
	}

	public int InsertProjectDocTypeName(TsProjectDocType project) {
		// TODO Auto-generated method stub
		return mapper.InsertProjectDocTypeName(project);
	}

	public int delProjectDocTypeName(int docTypeId) {
		// TODO Auto-generated method stub
		return mapper.delProjectDocTypeName(docTypeId);
	}

	public String SelectNameByid(int docTypeId) {
		// TODO Auto-generated method stub
		return mapper.SelectNameByid(docTypeId);
	}

	public int ChangeProjectDocTypeName(TsProjectDocType project) {
		// TODO Auto-generated method stub
		return mapper.ChangeProjectDocTypeName(project);
	}

	public int queryDocCountById(int docTypeId) {
		// TODO Auto-generated method stub
		return mapper.queryDocCountById(docTypeId);
	}
}
