
<%@ page import="com.twitstreet.db.data.User"%>
<%
User sessionUser = (User)request.getSession().getAttribute(User.USER);
%>
<<<<<<< HEAD

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
=======
<div id="topbar">
	<a id="home" href="/">TwitStreet</a>
	<% if(sessionUser == null){ %>
	<div id="loginbox">
		<a href="/signin"><img src="/images/twitter-small.png"></img>
		</a>
	</div>
	<% } else { %>
		<div id="logoutbox">
			<table>
				<tr>
					<td rowspan="2"><img class="twuser" src="<%= sessionUser.getPictureUrl() %>" /></td>
					<td>@<span id="username"><%= sessionUser.getUserName() %></span></td>
				</tr>
				<tr>
					<td><a href="/?signout=1">Sign out >></a></td>
				</tr>
			</table>
		</div>
	<% } %>
</div>
>>>>>>> master
