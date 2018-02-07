package com.snowlink.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;

public interface TsProjectService {
	/**
	 * 查询全部
	 * @param tsProject
	 * @return
	 */
	public JSONObject queryAll(TsProject tsProject);
	/**
	 * 插入项目
	 * @param project
	 * @return
	 */
	public int InsertProject(TsProject project);
	
	/**
	 * del
	 * @param projectId
	 * @return
	 */
	public int delProject(String projectId);
	/**
	 * 根据id查询name
	 * @param projectId
	 * @return
	 */
	public String getProjectName(String projectId);
	
	/**
	 * 更改项目
	 * @param project
	 * @return
	 */
	public int ChangeProject(TsProject project);
	
	/**
	 * 根据id查询第二级目录
	 * @param projectId
	 * @return
	 */
	public JSONObject SelectSubordinateDoc(TsProjectDocType project);
	
	/**
	 * 判断项目下是否有文件
	 * @param project_id
	 * @return
	 */
	public int queryProjectAllCountDoc(int project_id);
}
