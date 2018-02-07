package com.snowlink.dao;

import com.snowlink.bean.TsProject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsProjectMapper {
	
	 List<TsProject> queryAll(TsProject project);
	
	 int queryCount(TsProject project);
	 
	 int InsertProject(TsProject project);
	 
	 int delProject(String projectId);
	 
	 String getProjectName(String projectId);
	 
	 int ChangeProject(TsProject project);

	 List<TsProjectDocType> SelectSubordinateDoc(TsProjectDocType project);

	 int selectSubordinateDocCount(int projectId);
	 
   int queryProjectAllCountDoc(int project_id);

}