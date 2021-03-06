package com.twitstreet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twitstreet.db.data.Stock;
import com.twitstreet.db.data.User;
import com.twitstreet.market.StockMgr;

@SuppressWarnings("serial")
@Singleton
public class StockDetailsServlet extends TwitStreetServlet {
	@Inject
	StockMgr stockMgr;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		
		String stockIdStr = request.getParameter("stock");

		loadUser(request);
		User user = (User) request.getAttribute(User.USER);
		
		if (stockIdStr == null) {
			response.sendRedirect(response.encodeRedirectURL("/"));
			return;
		}
		Stock stock = stockMgr.getStockById(Long.parseLong(stockIdStr));

		if (stock != null) {
			request.setAttribute("stock", stock);
			request.setAttribute("title", "Stock details of " + stock.getName());
			request.setAttribute(
					"meta-desc",
					"This page show details of a "
							+ stock.getName()
							+ " like available, sold and total number of a followers. Stock distribution shows who has how much "
							+ stock.getName() + ".");

			request.setAttribute(HomePageServlet.QUOTE_DISPLAY, stock.getName());
			request.setAttribute(HomePageServlet.STOCK, stock);

			request.setAttribute(HomePageServlet.STOCK_ID,
					new Long(stock.getId()));

		}

		getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp")
				.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
