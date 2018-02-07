package com.snowlink.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.snowlink.bean.TsDocOption;
@Repository
public interface TsDocOptionMapper {
    
	/**
	 * ��ѯȫ��Doc Name
	 * @return
	 */
	public List<TsDocOption> QueryDocNameAll();
	
	/**
	 * ����DocName
	 * @return
	 */
	public int InsertDocName(TsDocOption option);
	
	/**
	 * ����id ����doc�������
	 * @param option
	 * @return
	 */
	public int amendDocNameById(TsDocOption option);
	/**
	 *  ����idɾ��Doc
	 * @param id
	 * @return
	 */
	public int delDoc(Integer id);
	
	/**
	 * ��ѯdoc�����
	 * @return
	 */
	public int DocCount();
}