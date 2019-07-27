package cn.web.pack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.web.pack.object.Table;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int size = 5;
		int page = 0;
		Table table = null;

		if (req.getParameter("page") == null) {

			page = 1;

		} else {
			page = Integer.parseInt(req.getParameter("page"));
		}

		table = new Table(size, page);
		req.setAttribute("table", table);
		req.getRequestDispatcher("success.jsp").forward(req, resp);

	}

}
