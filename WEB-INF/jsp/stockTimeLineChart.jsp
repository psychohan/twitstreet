
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.twitstreet.twitter.SimpleTwitterUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.twitstreet.db.data.UserStock"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.twitstreet.market.StockMgr"%>
<%@ page import="com.google.inject.Injector"%>
<%@ page import="com.google.inject.Guice"%>
<%@ page import="com.twitstreet.db.data.User"%>
<%@ page import="com.twitstreet.db.data.Stock"%>
<%@ page import="com.twitstreet.util.Util"%>
<%@page import="com.twitstreet.db.data.Portfolio"%>
<%@page import="com.twitstreet.market.PortfolioMgr"%>
<%@page import="com.twitstreet.config.ConfigMgr"%>
<%@page import="com.twitstreet.session.UserMgr"%>
<%@ page import="com.twitstreet.servlet.HomePageServlet"%>

<%@ page import="com.twitstreet.servlet.HomePageServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.twitstreet.db.data.StockHistoryData"%>
<%@page import="com.twitstreet.db.data.UserStock"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.twitstreet.market.StockMgr"%>
<%@ page import="com.google.inject.Injector"%>
<%@ page import="com.twitstreet.db.data.User"%>
<%@ page import="com.google.inject.Guice"%>
<%@ page import="com.twitstreet.util.Util"%>
<%@ page import="com.twitstreet.db.data.Portfolio"%>
<%@page import="com.twitstreet.config.ConfigMgr"%>
<%@ page import="com.twitstreet.market.PortfolioMgr"%>
<%@page import="com.twitstreet.session.UserMgr"%>
<%@ page import="com.twitstreet.db.data.UserStockDetail"%>
<%@ page import="java.util.List"%>
<%@ page import="com.twitstreet.util.Util"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="com.twitstreet.market.StockMgr"%>
<%@ page import="com.twitstreet.db.data.Stock"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="com.twitstreet.localization.LocalizationUtil" %>
<%@page import="com.twitstreet.util.GUIUtil"%>
	<%
	Injector inj = (Injector) pageContext.getServletContext().getAttribute(Injector.class.getName());
	StockMgr stockMgr = inj.getInstance(StockMgr.class);
	User user = (User) request.getAttribute(User.USER);
	
	String divId = request.getParameter("divId");
	String format = request.getParameter("format");
	
	
	
	Stock stock = (Stock) request.getAttribute("chartStock");

	LocalizationUtil lutil = LocalizationUtil.getInstance();
	String lang = (String)request.getSession().getAttribute(LocalizationUtil.LANGUAGE);
	%>


	<script type="text/javascript">
		var dateArrayStock<%=stock.getId()%> = new Array();
		var valueArrayStock<%=stock.getId()%> = new Array();
		var stockNameStock<%=stock.getId()%> =
		<%
	
	
			StockHistoryData shd = null;

			shd = stockMgr.getStockHistory(stock.getId());

			out.write("'" + shd.getName() + "';\n");

			LinkedHashMap<Date, Integer> dvm = shd.getDateValueMap();

			out.write("dateArrayStock"+stock.getId()+".push(new Date(" + stock.getLastUpdate().getTime() + "));\n");

			out.write("valueArrayStock"+stock.getId()+".push(" + stock.getTotal() + ");\n");

			for (Date date : dvm.keySet()) {
				out.write("dateArrayStock"+stock.getId()+".push(new Date(" + date.getTime() + "));\n");

				out.write("valueArrayStock"+stock.getId()+".push(" + dvm.get(date) + ");\n");
		}%>
		drawStockHistory('<%=divId+stock.getId()%>', dateArrayStock<%=stock.getId()%>, valueArrayStock<%=stock.getId()%>,
				stockNameStock<%=stock.getId()%>,'<%=format%>');
	</script>

		

		
			