package com.snowlink.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;

public interface TsProjectService {
	/**
	 * ��ѯȫ��
	 * @param tsProject
	 * @return
	 */
	public JSONObject queryAll(TsProject tsProject);
	/**
	 * ������Ŀ
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
	 * ����id��ѯname
	 * @param projectId
	 * @return
	 */
	public String getProjectName(String projectId);
	
	/**
	 * ������Ŀ
	 * @param project
	 * @return
	 */
	public int ChangeProject(TsProject project);
	
	/**
	 * ����id��ѯ�ڶ���Ŀ¼
	 * @param projectId
	 * @return
	 */
	public JSONObject SelectSubordinateDoc(TsProjectDocType project);
	
	/**
	 * �ж���Ŀ���Ƿ����ļ�
	 * @param project_id
	 * @return
	 */
	public int queryProjectAllCountDoc(int project_id);
}
