<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="true" session="false"%>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template'%>

<html>
<head>
<title><template:get name="title" /></title>
<template:get name="header"></template:get>
</head>
<body>
	<template:get name="topbar"></template:get>
	<div class="container">
		<div class="row">
			<div class="span8">
				<template:get name="dashboard"></template:get>
				<template:get name="howtoplay"></template:get>
				<template:get name="signup"></template:get>
				<template:get name="portfolio"></template:get>
			</div>
			<div class="span4">
				<template:get name="balance"></template:get>
				<template:get name="yourtransactions"></template:get>
				<template:get name="latesttransactions"></template:get>
				<template:get name="recentTweets"></template:get>
			</div>
			<div class="span4">
				<template:get name="topranks"></template:get>
			</div>
			<!-- Don't remove spacer div. Solve an issue about container height -->
			<div class="spacer"></div>
		</div>
		<div id="footer">
			<template:get name="footer" />
		</div>
	</div>
</body>
</html>

