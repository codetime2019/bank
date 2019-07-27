package cn.web.pack.object;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Table {

	// o 分页大小
	private int size;

	// o 当前页数
	private int page;

	// o 日志总数
	private int total;

	// o 显示数据
	private List<Journal> list;

	private SqlSession session = null;
	private SqlSessionFactory factory = null;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Journal> getList() {
		return list;
	}

	public void setList(List<Journal> list) {
		this.list = list;
	}

	public Table() {
		// TODO 自动生成的构造函数存根
	}

	public Table(int size, int page) {
		super();
		this.size = size;
		this.page = page;

		try {
			InputStream in = Resources.getResourceAsStream("MyBatisConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(in);
			session = factory.openSession();

			this.total = this.session.selectOne("a.c.total");

			int init = (this.page - 1) * this.size;
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("init", init);
			map.put("size", size);
			this.list = this.session.selectList("a.c.getlist", map);

			session.commit();
		} catch (IOException e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
