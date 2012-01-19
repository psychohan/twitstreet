
<%@ page import="com.twitstreet.db.data.User"%>
<%
User sessionUser = (User)request.getSession().getAttribute(User.USER);
%>

<div class="topbar">
      <div class="topbar-inner">
        <div class="container-fluid">
          <a class="brand" href="/">Twitstreet</a>
          <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
          </ul>
          <% if(sessionUser == null){ %>
          <p class="pull-right">
          	<p class="pull-right"><a href="/signin">Sign in with Twitter</a></p>
          </p>
          <% } else { %>
          <p class="pull-right">Sign out <a href="/signout">@<%= sessionUser.getUserName() %></a></p>
          <% } %>
        </div>
      </div>
    </div>