package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snowlink.bean.TsDocOption;
import com.snowlink.dao.TsDocOptionMapper;
import com.snowlink.service.TsDocOptionService;
import com.snowlink.service.impl.TsDocOptionServiceImpl;

@Service
public class TsDocOptionServiceImpl implements TsDocOptionService{
 
	@Autowired
	private TsDocOptionMapper mapper;
	
	public List<TsDocOption> QueryDocNameAll() {
		// TODO Auto-generated method stub
		return mapper.QueryDocNameAll();
	}

	public int InsertDocName(TsDocOption option) {
		// TODO Auto-generated method stub
		return mapper.InsertDocName(option);
	}

	public int amendDocNameById(TsDocOption option) {
		// TODO Auto-generated method stub
		return mapper.amendDocNameById(option);
	}

	public int delDoc(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delDoc(id);
	}

	public int DocCount() {
		// TODO Auto-generated method stub
		return mapper.DocCount();
	}
	
	
	

	
}
