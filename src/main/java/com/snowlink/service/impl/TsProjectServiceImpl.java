package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.dao.TsProjectMapper;
import com.snowlink.service.TsProjectService;

@Service
public class TsProjectServiceImpl implements TsProjectService {
	
	@Autowired
	private TsProjectMapper mapper;
	
	//查询所有项目
	public JSONObject queryAll(TsProject tsProject) {
		JSONObject obj = new JSONObject();
		List<TsProject> tsList = mapper.queryAll(tsProject);
		int count = mapper.queryCount(tsProject);
		obj.put("rows", tsList); 
		obj.put("total", count); 
		return obj;
	}

	public int InsertProject(TsProject project) {
		// TODO Auto-generated method stub
		return mapper.InsertProject(project);
	}

	public int delProject(String projectId) {
		// TODO Auto-generated method stub
		return mapper.delProject(projectId);
	}

	public String getProjectName(String projectId) {
		// TODO Auto-generated method stub
		return mapper.getProjectName(projectId);
	}

	public int ChangeProject(TsProject project) {
		// TODO Auto-generated method stub
		return mapper.ChangeProject(project);
	}

	public JSONObject SelectSubordinateDoc(TsProjectDocType project) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		List<TsProjectDocType> tsList= mapper.SelectSubordinateDoc(project);
		int count = mapper.selectSubordinateDocCount(project.getProjectId());
		obj.put("rows", tsList);
		obj.put("total", count);
		
		return obj;
	}

	public int queryProjectAllCountDoc(int project_id) {
		// TODO Auto-generated method stub
		return mapper.queryProjectAllCountDoc(project_id);
	}


	
}
