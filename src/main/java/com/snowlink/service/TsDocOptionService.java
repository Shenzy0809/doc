package com.snowlink.service;

import java.util.List;

import com.snowlink.bean.TsDocOption;

public interface TsDocOptionService {

	/**
	 * 查询全部Doc Name
	 * @return
	 */
	public List<TsDocOption> QueryDocNameAll();
	
	/**
	 * 插入DocName
	 * @return
	 */
	public int InsertDocName(TsDocOption option);
	/**
	 * 根据id 更改doc表的内容
	 * @param option
	 * @return
	 */
	public int amendDocNameById(TsDocOption option);
	
	/**
	 *  根据id删除Doc
	 * @param id
	 * @return
	 */
	public int delDoc(Integer id);
	
	/**
	 * 查询doc表个数
	 * @return
	 */
	public int DocCount();
}
