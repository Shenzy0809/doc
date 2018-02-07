package com.snowlink.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsSysUser;

public interface TsUserService {

	/**
	 * �����˺Ų�ѯ����
	 * @param username
	 * @return
	 */
	public String CheckLogin(String username);
	
	/**
	 * ����username ��ѯid��name
	 * @param username
	 * @return
	 */
	public TsSysUser QueryIdName(String username);

}
