package cn.web.pack.object;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * 
 * o 记录日志
 * 
 * @author 12589
 *
 */
public class Journal {

	private String accout;

	private String accin;

	private String dates;

	private double amount;

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getAccin() {
		return accin;
	}

	public void setAccin(String accin) {
		this.accin = accin;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Journal(String accout, String accin, double amount) {
		super();
		this.accout = accout;
		this.accin = accin;
		this.dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
		this.amount = amount;

		SqlSessionFactory factory = null;
		SqlSession session = null;

		try {
			InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
			session = factory.openSession();
			int num = session.insert("a.c.insertLog", this);
			session.commit();
		} catch (IOException e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			this.print();
		}
	}

	public Journal(String accout, String accin, String dates, double amount) {
		super();
		this.accout = accout;
		this.accin = accin;
		this.dates = dates;
		this.amount = amount;
	}

	public void print() {

		Logger logger = Logger.getLogger(Journal.class);
		logger.info("账户：" + this.accout + "给账户：" + this.accin + "在：" + this.dates + "时转账：" + this.amount + "元");

	}

}
