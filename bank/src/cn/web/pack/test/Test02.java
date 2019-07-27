package cn.web.pack.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.web.pack.object.Journal;

public class Test02 {

	public static void main(String[] args) throws IOException {

		Journal log = new Journal("1", "2", 500);

		InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

		SqlSession session = factory.openSession();

		List<Journal> list = session.selectList("a.c.totalL");

		session.commit();

		session.close();

		for (Journal n : list) {

			n.print();

		}

	}

}
