package cn.web.pack.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.web.pack.object.BankUser;

/**
 * 
 * #- 测试MyBatis连接
 * 
 * @author 12589
 *
 */
public class Test01 {

	public static void main(String[] args) {

		try {
			// o加载XML文件流
			InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");
			// o加载sqlsessionfactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			// o构建sqlsession对象
			SqlSession session = factory.openSession();

			List<BankUser> list = session.selectList("a.b.all");

			for (BankUser bankUser : list) {

				bankUser.println();

			}

			session.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
