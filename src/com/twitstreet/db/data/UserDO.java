package com.twitstreet.db.data;

import java.util.Date;

public class UserDO{
    long id;
    String userName;
    Date firstLogin;
    Date lastLogin;
    int cash;
    int portfolio;
    String lastIp;
    String oauthToken;
    String oauthTokenSecret;
    
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(int portfolio) {
        this.portfolio = portfolio;
    }

    public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}

	public void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}
}