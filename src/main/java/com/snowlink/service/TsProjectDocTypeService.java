package com.snowlink.service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;

public interface TsProjectDocTypeService {

	/**
	 * 跟新查询条件查询
	 * @param project
	 * @return
	 */
	public JSONObject QueryAllBycondition(TsProjectDocType project);
	/**
	 * 新增插入
	 * @param project
	 * @return
	 */
	public int InsertProjectDocTypeName(TsProjectDocType project);
	
	/**
	 * 删除
	 */
	public int delProjectDocTypeName(int docTypeId);
	/**
	 * 根据id查询name
	 * @param docTypeId
	 * @return
	 */
	public String SelectNameByid(int docTypeId);
	
	/**
	 * 更改name
	 * @param project
	 * @return
	 */
	public int ChangeProjectDocTypeName(TsProjectDocType project);
	
	/**
	 * 	判断文件夹下是否有文件存在
	 * @param docTypeId
	 * @return
	 */
	public int queryDocCountById(int  docTypeId);
	
	
	
}
