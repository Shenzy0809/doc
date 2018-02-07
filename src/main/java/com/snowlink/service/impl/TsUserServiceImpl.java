package com.snowlink.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsBasicDocContent;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsSysUser;
import com.snowlink.dao.TsUserMapper;
import com.snowlink.service.TsUserService;
@Service
public class TsUserServiceImpl implements TsUserService {
	@Autowired
	private TsUserMapper mapper;

	public String CheckLogin(String username) {
		// TODO Auto-generated method stub
		return mapper.CheckLogin(username);
	}

	public TsSysUser QueryIdName(String username) {
		// TODO Auto-generated method stub
		return mapper.QueryIdName(username);
	}


}
