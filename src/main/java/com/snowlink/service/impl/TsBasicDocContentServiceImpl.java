package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsBasicDocContent;
import com.snowlink.dao.TsBasicDocContentMapper;
import com.snowlink.dao.TsDocOptionMapper;
import com.snowlink.service.TsBasicDocContentService;
import com.snowlink.service.impl.TsBasicDocContentServiceImpl;


@Service
public class TsBasicDocContentServiceImpl implements TsBasicDocContentService{
	
	@Autowired
	private TsBasicDocContentMapper mapper;

	public JSONObject QueryDocContent(TsBasicDocContent content) {
		JSONObject obj = new JSONObject();
		List<TsBasicDocContent> bList = mapper.QueryDocContent(content);
		
		int count = mapper.QueryCountDocBasic(content.getDoc_id());
		obj.put("rows", bList);
		obj.put("total", count);
		return obj;
	}

	public TsBasicDocContent SelectAllByidno(TsBasicDocContent content) {
		// TODO Auto-generated method stub
		return mapper.SelectAllByidno(content);
	}

	public int alertContent(TsBasicDocContent content) {
		// TODO Auto-generated method stub
		return mapper.alertContent(content);
	}

	public int delDocContent(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delDocContent(id);
	}

	public int AddNewContent(TsBasicDocContent content) {
		// TODO Auto-generated method stub
		return mapper.AddNewContent(content);
	}

	public List<TsBasicDocContent> SelectAllByDocId(String doc_id) {
		// TODO Auto-generated method stub
		return mapper.SelectAllByDocId(doc_id);
	}

	public int CheckBasicCount(Integer doc_id) {
		// TODO Auto-generated method stub
		return mapper.CheckBasicCount(doc_id);
	}

	public int selectOrder(Integer doc_id) {
		// TODO Auto-generated method stub
		return mapper.selectOrder(doc_id);
	}
}
