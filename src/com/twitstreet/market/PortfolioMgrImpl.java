package com.twitstreet.market;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.twitstreet.db.base.DBMgr;
import com.twitstreet.db.data.Portfolio;
import com.twitstreet.db.data.StockInPortfolio;
import com.twitstreet.db.data.User;
import com.twitstreet.db.data.UserStock;
import com.twitstreet.db.data.Stock;
import com.twitstreet.servlet.BuySellResponse;
import com.twitstreet.session.UserMgr;

public class PortfolioMgrImpl implements PortfolioMgr {
	private static Logger logger = Logger.getLogger(PortfolioMgrImpl.class);
	@Inject
	DBMgr dbMgr;
	@Inject
	private StockMgr stockMgr;
	@Inject
	private UserMgr userMgr;

	@Override
	public BuySellResponse buy(long buyer, long stock, int amount)
			throws SQLException {
		User user = userMgr.getUserById(buyer);
		int amount2Buy = user.getCash() < amount ? user.getCash() : amount;
		Stock stockObj = stockMgr.getStockById(stock);
		double sold = (double) amount2Buy / (double) stockObj.getTotal();
		stockObj.setSold(stockObj.getSold() + sold);
		UserStock userStock = getStockInPortfolio(buyer, stock);

		if (userStock == null) {
			addStock2Portfolio(buyer, stock, sold);
		} else {
			updateStockInPortfolio(buyer, stock, sold);
		}
		stockMgr.updateSold(stock, sold);
		userMgr.updateCashAndPortfolio(buyer, amount2Buy);
		user.setCash(user.getCash() - amount2Buy);
		user.setPortfolio(user.getPortfolio() + amount2Buy);
		return new BuySellResponse(user, stockObj);

	}

	private void updateStockInPortfolio(long buyer, long stock, double sold)
			throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		connection = dbMgr.getConnection();
		ps = connection
				.prepareStatement("update portfolio set percentage = (percentage + ?) where user_id = ? and stock = ?");
		ps.setDouble(1, sold);
		ps.setLong(2, buyer);
		ps.setLong(3, stock);
		ps.execute();
		if (!ps.isClosed()) {
			ps.close();
		}
		if (!connection.isClosed()) {
			connection.close();
		}
	}

	private void addStock2Portfolio(long buyer, long stock, double sold)
			throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		connection = dbMgr.getConnection();
		ps = connection
				.prepareStatement("insert into portfolio(user_id, stock, percentage) values(?, ?, ?)");
		ps.setLong(1, buyer);
		ps.setLong(2, stock);
		ps.setDouble(3, sold);
		ps.execute();
		if (!ps.isClosed()) {
			ps.close();
		}
		if (!connection.isClosed()) {
			connection.close();
		}
	}

	@Override
	public UserStock getStockInPortfolio(long userId, long stockId)
			throws SQLException {
		UserStock userStock = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		connection = dbMgr.getConnection();
		ps = connection
				.prepareStatement("select id, percentage from portfolio where user_id = ? and stock = ?");
		ps.setLong(1, userId);
		ps.setLong(2, stockId);
		rs = ps.executeQuery();

		if (rs.next()) {
			userStock = new UserStock();
			userStock.setId(rs.getLong("id"));
			userStock.setPercent(rs.getDouble("percentage"));
		}
		if (!rs.isClosed()) {
			rs.close();
		}
		if (!ps.isClosed()) {
			ps.close();
		}
		if (!connection.isClosed()) {
			connection.close();
		}
		return userStock;
	}

	@Override
	public double getStockSoldPercentage(long userId, long stockId)
			throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		connection = dbMgr.getConnection();
		ps = connection
				.prepareStatement("select percentage from portfolio where user_id = ? and stock = ?");
		ps.setLong(1, userId);
		ps.setLong(2, stockId);
		try {
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("percentage");

			}
			logger.debug("DB: Query executed successfully - " + ps.toString());
		} catch (SQLException ex) {
			logger.debug("DB: Query failed - " + ps.toString(), ex);
			throw ex;
		} finally {
			if (!rs.isClosed()) {
				rs.close();
			}
			if (!ps.isClosed()) {
				ps.close();
			}
			if (!connection.isClosed()) {
				connection.close();
			}
		}
		return 0.0;
	}

	@Override
	public BuySellResponse sell(long seller, long stock, int amount)
			throws SQLException {
		User user = userMgr.getUserById(seller);
		int amount2Buy = user.getCash() < amount ? user.getCash() : amount;
		Stock stockObj = stockMgr.getStockById(stock);
		double sold = (double) amount2Buy / (double) stockObj.getTotal();
		stockObj.setSold(stockObj.getSold() - sold);
		UserStock userStock = getStockInPortfolio(seller, stock);

		if (userStock != null) {
			updateStockInPortfolio(seller, stock, -sold);
		}

		stockMgr.updateSold(stock, -sold);
		userMgr.updateCashAndPortfolio(seller, -amount2Buy);
		user.setCash(user.getCash() + amount2Buy);
		user.setPortfolio(user.getPortfolio() - amount2Buy);
		return new BuySellResponse(user, stockObj);
	}

	@Override
	public void rerank() {
		Connection connection = null;
		CallableStatement cs = null;
		try {
			connection = dbMgr.getConnection();
			cs = connection.prepareCall("{call rerank()}");
			cs.execute();
			logger.debug("DB: Query executed successfully - " + cs.toString());
		} catch (SQLException ex) {
			logger.error("DB: Query failed - " + cs.toString(), ex);
		} finally {
			try {
				if (!cs.isClosed()) {
					cs.close();
				}
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("DB: Resources could not be closed properly", e);
			}
		}
	}

	@Override
	public Portfolio getUserPortfolio(long userId) {
		User user = null;
		Portfolio portfolio = null;
		try {
			user = userMgr.getUserById(userId);
		} catch (SQLException e1) {
			logger.error("DB: Query user failed while retrieving user portfolio", e1);
		}
		
		if (user != null) {
			portfolio = new Portfolio(user);
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				connection = dbMgr.getConnection();
				ps = connection
						.prepareStatement("select stock.name as stockName, stock.id as stockId, (stock.total * portfolio.percentage) as amount from portfolio, stock where portfolio.stock = stock.id and portfolio.user_id = ?");
				ps.setLong(1, userId);
				rs = ps.executeQuery();

				while (rs.next()) {
					StockInPortfolio stockInPortfolio = new StockInPortfolio(rs.getLong("stockId"), rs.getString("stockName"), (int) Math.rint(rs.getDouble("amount")));
					portfolio.add(stockInPortfolio);
				}
				
				logger.debug("DB: Query executed successfully - " + ps.toString());
			} catch (SQLException ex) {
				logger.error("DB: Query failed - " + ps.toString(), ex);
			} finally {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
					if (!ps.isClosed()) {
						ps.close();
					}
					if (!connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					logger.error("DB: Releasing resources failed.", e);
				}
			
			}
		}
		return portfolio;
	}
}
