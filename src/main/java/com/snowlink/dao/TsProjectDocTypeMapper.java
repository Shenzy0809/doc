package com.snowlink.dao;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsProjectDocTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TsProjectDocTypeMapper {
  
	
	List<TsProjectDocType> QueryAllBycondition(TsProjectDocType project);
	
	int QueryCount(TsProjectDocType project);
	
	int InsertProjectDocTypeName(TsProjectDocType project);
	
	int delProjectDocTypeName(int docTypeId);
	
	String SelectNameByid(int docTypeId);
	
	int ChangeProjectDocTypeName(TsProjectDocType project);
	
	int queryDocCountById(int  docTypeId);
}