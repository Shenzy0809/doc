package com.snowlink.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsBasicDocContent;


public interface TsBasicDocContentService {

	/**
	 * 根据doc_id 查询内容表内容
	 * @param Doc_id
	 * @return
	 */
	public JSONObject QueryDocContent(TsBasicDocContent content);
	
	/**
	 *  根据doc_id 和title_no 查询内容表
	 * @param content
	 * @return
	 */
	public TsBasicDocContent SelectAllByidno(TsBasicDocContent content);
	
	/**
	 *  更改信息时插入
	 * @param content
	 * @return
	 */
	public int alertContent(TsBasicDocContent content);
	
	/**
	 * 根据id删除文档中的某条记录
	 * @param id
	 * @return
	 */
	public int delDocContent(Integer id);
	
	/**
	 *  向文档中添加一条
	 * @param content
	 * @return
	 */
	public int AddNewContent(TsBasicDocContent content);
	
	/**
	 *  根据docid查询全部
	 * @param doc_id
	 * @return
	 */
	public List<TsBasicDocContent> SelectAllByDocId(String doc_id);
	/**
	 *  根据docid 查询表中是否有记录
	 * @param doc_id
	 * @return
	 */
	public int CheckBasicCount(Integer doc_id);
	
	/**
	 * 查询现在的order数
	 * @param doc_id
	 * @return
	 */
	public int selectOrder(Integer doc_id);
}
