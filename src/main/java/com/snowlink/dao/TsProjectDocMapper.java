package com.snowlink.dao;

import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TsProjectDocMapper {
    
	public List<TsProjectDoc> BlurryQuery(TsProjectDoc project);
	
	public int QueryCount(TsProjectDoc project);
	
	public int insertUpload(TsProjectDoc project);
	
	public String querydocName(String url);
	
	public int delete(int id);
}