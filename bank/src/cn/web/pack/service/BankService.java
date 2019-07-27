package cn.web.pack.service;

import cn.web.pack.object.BankUser;

public interface BankService {

	/**
	 * utf- ��ѯ�˻��Ƿ���ʵ����
	 * 
	 * @return
	 */
	boolean truthAc(String account);

	/**
	 * 
	 * utf- �ж������˻��Ƿ�������ȷ
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	boolean truthUser(String account, String password);

	/**
	 * utf- �����ṩ���˻�����ת��ҵ��
	 * 
	 * @param account01
	 * @param account02
	 * @param amount
	 * @return
	 */
	boolean transcation(String account01, String account02, String amount);

	/**
	 * 
	 * utf- �����ṩ���˻������û���Ϣ
	 * 
	 * @param account
	 * @return
	 */
	BankUser getUser(String account);
}
