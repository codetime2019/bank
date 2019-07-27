package cn.web.pack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.web.pack.object.BankUser;
import cn.web.pack.service.impl.BankServiceImpl;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");

		// utf-查询是否存在
		if (code.equals("1")) {

			String account = req.getParameter("account");

			resp.getWriter().write(new Gson().toJson(new BankServiceImpl().truthAc(account)));

			return;
		}

		// utf-根据账户密码核对账户
		if (code.equals("2")) {

			String account = req.getParameter("account");
			String password = req.getParameter("password");

			resp.getWriter().write(new Gson().toJson(new BankServiceImpl().truthUser(account, password)));

			return;
		}

		// utf-开始转账业务
		if (code.equals("3")) {

			String account01 = req.getParameter("account01");
			String account02 = req.getParameter("account02");
			String amount = req.getParameter("amount");

			if (new BankServiceImpl().transcation(account01, account02, amount)) {

				BankUser user1 = new BankServiceImpl().getUser(account01);

				BankUser user2 = new BankServiceImpl().getUser(account02);

				resp.sendRedirect("/bank/Show");

			} else {

				resp.sendRedirect("/bank/error.jsp");

			}

			return;
		}
	}

}
