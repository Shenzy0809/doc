package com.snowlink.dao;

import java.util.List;

import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsSysUser;

public interface TsUserMapper {

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
