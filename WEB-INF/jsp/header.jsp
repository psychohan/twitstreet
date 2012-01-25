<%@ page import="com.google.inject.Injector" %>
<%@ page import="com.google.inject.Guice" %>
<%@page import="com.twitstreet.config.ConfigMgr"%>
<%
Injector inj = (Injector) pageContext.getServletContext().getAttribute(Injector.class.getName());

ConfigMgr configMgr = inj.getInstance(ConfigMgr.class);
%>

<script src="/js/jquery-1.6.4.min.js"></script>
<script src="/js/jquery-corner.js"></script>
<script src="/js/jquery.blockUI.js"></script>
<script src="/js/twitstreet.js"></script>
<script language="javascript" type="text/javascript">

</script>

<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
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