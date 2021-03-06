<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template'%>
<template:insert template='userProfileAuthTemplate.jsp'>
  <template:put name='header' content='header.jsp' />
  <template:put name='topbar' content='topbar.jsp' />
  <template:put name='userprofile' content='userProfile.jsp' />
  <template:put name='balance' content='balance.jsp' />
  <template:put name='portfolio' content='portfolio.jsp' />
  <template:put name='yourtransactions' content='yourtransactions.jsp' />
  <template:put name='latesttransactions' content='latesttransactions.jsp' />
  <template:put name='recentTweets' content='recentTweets.jsp' />
  <template:put name='topranks' content='topranks.jsp' />
  <template:put name='footer' content='footer.jsp' />
</template:insert>