package cn.web.pack.service;

import cn.web.pack.object.BankUser;

public interface BankService {

	/**
	 * utf- 查询账户是否真实存在
	 * 
	 * @return
	 */
	boolean truthAc(String account);

	/**
	 * 
	 * utf- 判断密码账户是否输入正确
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	boolean truthUser(String account, String password);

	/**
	 * utf- 根据提供的账户进行转账业务
	 * 
	 * @param account01
	 * @param account02
	 * @param amount
	 * @return
	 */
	boolean transcation(String account01, String account02, String amount);

	/**
	 * 
	 * utf- 根据提供的账户返还用户信息
	 * 
	 * @param account
	 * @return
	 */
	BankUser getUser(String account);
}
