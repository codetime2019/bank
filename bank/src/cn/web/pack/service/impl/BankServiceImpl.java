package cn.web.pack.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.web.pack.exception.MyException;
import cn.web.pack.object.BankUser;
import cn.web.pack.object.Journal;
import cn.web.pack.service.BankService;

public class BankServiceImpl implements BankService {

	private SqlSessionFactory factory = null;

	private SqlSession session = null;

	public BankServiceImpl() {

		try {
			InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");
			this.factory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@Override
	public boolean truthAc(String account) {

		try {

			this.session = this.factory.openSession();

			Map<String, String> map = new HashMap<String, String>();
			map.put("account", account);

			int num = this.session.selectOne("a.b.getAB", map);

			this.session.close();

			return num > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean truthUser(String account, String password) {

		try {

			this.session = this.factory.openSession();

			Map<String, String> map = new HashMap<String, String>();
			map.put("account", account);
			map.put("password", password);

			int num = this.session.selectOne("a.b.truthU", map);

			this.session.close();

			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean transcation(String account01, String account02, String amount) {

		boolean b1 = false;

		double amounts = new Double(amount);

		try {

			this.session = this.factory.openSession();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", account01);
			BankUser user1 = this.session.selectOne("a.b.seUser", map);
			map.put("id", account02);
			BankUser user2 = this.session.selectOne("a.b.seUser", map);
			// utf- 将账户1余额减去转账金额
			// utf- 如果转账金额大于账户余额则抛出异常
			if (user1.getAccount_balance() < amounts) {

				throw new MyException("转账金额大于账户余额");

			}
			map.put("id", account01);
			new String();
			map.put("amount", String.valueOf(user1.getAccount_balance() - amounts));
			int num01 = this.session.update("a.b.updateM", map);

			// utf- 账户二的钱=账户二+转账金额
			map.put("id", account02);
			new String();
			map.put("amount", String.valueOf(user2.getAccount_balance() + amounts));
			int num02 = this.session.update("a.b.updateM", map);

			if (num01 > 0 && num02 > 0)
				b1 = true;

			// utf- 记录日志（journal/periodical）
			new Journal(account01, account02, new Double(amount));
			this.session.commit();
		} catch (Exception e) {

			this.session.rollback();
			System.err.println(e.getMessage());

		} finally {
			this.session.close();
		}

		return b1;
	}

	@Override
	public BankUser getUser(String account) {

		BankUser user = null;

		try {

			this.session = this.factory.openSession();

			Map<String, String> map = new HashMap<String, String>();

			map.put("id", account);

			user = this.session.selectOne("a.b.seUser", map);

			this.session.close();

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

		return user;
	}

}
