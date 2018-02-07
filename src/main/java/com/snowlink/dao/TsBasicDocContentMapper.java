package com.snowlink.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.snowlink.bean.TsBasicDocContent;


public interface TsBasicDocContentMapper {
   
	
	/**
	 * ����doc_id ��ѯ���ݱ�����
	 * @param Doc_id
	 * @return
	 */
	public List<TsBasicDocContent> QueryDocContent(TsBasicDocContent content);
	
	/**
	 * ����doc_id ��ѯ���ݱ�����
	 * @param doc_id
	 * @return
	 */
	public int QueryCountDocBasic(String doc_id);
	
	
	/**
	 *  ����doc_id ��title_no ��ѯ���ݱ�
	 * @param content
	 * @return
	 */
	public TsBasicDocContent SelectAllByidno(TsBasicDocContent content);
	
	/**
	 *  ������Ϣʱ����
	 * @param content
	 * @return
	 */
	public int alertContent(TsBasicDocContent content);
	
	/**
	 * ����idɾ���ĵ��е�ĳ����¼
	 * @param id
	 * @return
	 */
	public int delDocContent(Integer id);
	
	/**
	 *  ���ĵ�������һ��
	 * @param content
	 * @return
	 */
	public int AddNewContent(TsBasicDocContent content);
	
	
	/**
	 *  ����docid��ѯȫ��
	 * @param doc_id
	 * @return
	 */
	public List<TsBasicDocContent> SelectAllByDocId(String doc_id);
	/**
	 *  ����docid ��ѯ�����Ƿ��м�¼
	 * @param doc_id
	 * @return
	 */
	public int CheckBasicCount(Integer doc_id);
	/**
	 * ��ѯ���ڵ�order��
	 * @param doc_id
	 * @return
	 */
	public int selectOrder(Integer doc_id);
}