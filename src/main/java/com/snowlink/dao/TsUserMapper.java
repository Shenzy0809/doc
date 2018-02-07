package com.snowlink.dao;

import java.util.List;

import com.snowlink.bean.TsProjectDocType;
import com.snowlink.bean.TsSysUser;

public interface TsUserMapper {

	/**
	 * 根据账号查询密码
	 * @param username
	 * @return
	 */
	public String CheckLogin(String username);
	
	/**
	 * 根据username 查询id和name
	 * @param username
	 * @return
	 */
	public TsSysUser QueryIdName(String username);
}
