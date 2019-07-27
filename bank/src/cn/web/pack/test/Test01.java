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
 * #- ����MyBatis����
 * 
 * @author 12589
 *
 */
public class Test01 {

	public static void main(String[] args) {

		try {
			// o����XML�ļ���
			InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");
			// o����sqlsessionfactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
			// o����sqlsession����
			SqlSession session = factory.openSession();

			List<BankUser> list = session.selectList("a.b.all");

			for (BankUser bankUser : list) {

				bankUser.println();

			}

			session.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
