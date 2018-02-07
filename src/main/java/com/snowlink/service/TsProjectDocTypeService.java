package com.snowlink.service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;

public interface TsProjectDocTypeService {

	/**
	 * ���²�ѯ������ѯ
	 * @param project
	 * @return
	 */
	public JSONObject QueryAllBycondition(TsProjectDocType project);
	/**
	 * ��������
	 * @param project
	 * @return
	 */
	public int InsertProjectDocTypeName(TsProjectDocType project);
	
	/**
	 * ɾ��
	 */
	public int delProjectDocTypeName(int docTypeId);
	/**
	 * ����id��ѯname
	 * @param docTypeId
	 * @return
	 */
	public String SelectNameByid(int docTypeId);
	
	/**
	 * ����name
	 * @param project
	 * @return
	 */
	public int ChangeProjectDocTypeName(TsProjectDocType project);
	
	/**
	 * 	�ж��ļ������Ƿ����ļ�����
	 * @param docTypeId
	 * @return
	 */
	public int queryDocCountById(int  docTypeId);
	
	
	
}
