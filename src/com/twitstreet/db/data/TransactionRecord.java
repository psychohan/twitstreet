package com.twitstreet.db.data;

import java.util.Date;

public class TransactionRecord {
	public static final int BUY = 1;
	public static final int SELL = 0;
	long id;
	long stockId;
	int amount;
	int transactionAction;
	Date date;
	long userId;
	String userName;
	String stockName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStockId() {
		return stockId;
	}
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTransactionAction() {
		return transactionAction;
	}
	public void setTransactionAction(int transactionAction) {
		this.transactionAction = transactionAction;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}
