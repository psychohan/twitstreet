<%@ page import="com.google.inject.Injector" %>
<%@ page import="com.google.inject.Guice" %>
<%@page import="com.twitstreet.config.ConfigMgr"%>
<%
Injector inj = (Injector) pageContext.getServletContext().getAttribute(Injector.class.getName());

ConfigMgr configMgr = inj.getInstance(ConfigMgr.class);
%>

<link rel="shortcut icon" type="image/x-icon" href="/images/twitstreet_logo_50.png">

<title>
<%= request.getAttribute("title")%>
</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="description" content="<%=request.getAttribute("meta-desc") %>" >
<meta property="og:image" content="http://twitstreet.com/images/TwitStreet_logo1.png"/> 

<script src="/js/jquery-1.6.4.min.js"></script>
<script src="/js/jquery-corner.js"></script>
<script src="/js/jquery.blockUI.js"></script>
<script src="/js/hashchange.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>

<script src="/js/twitstreet.js"></script>
<script src="/js/ajax.js"></script>
<script src="/js/util.js"></script>
<script src="/js/stockTabs.js"></script>
<script src="/js/mainTabs.js"></script>
<script src="/js/userProfileTabs.js"></script>
<script src="/js/charts.js"></script>
<script src="/js/portfolioTab.js"></script>

<script type="text/javascript">
	google.load('visualization', '1.0', {'packages':['corechart']});
	google.load('visualization', '1', {packages: ['annotatedtimeline']});
</script>

<link rel="stylesheet" type="text/css" href="/css/cssreset-min.css" />
<link rel="stylesheet" type="text/css" href="/css/twitstreet.css" />
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', '<%=configMgr.getGaAccount()%>']);
  _gaq.push(['_setDomainName', 'twitstreet.com']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

