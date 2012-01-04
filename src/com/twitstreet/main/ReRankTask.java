package com.twitstreet.main;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twitstreet.market.PortfolioMgr;

@Singleton
public class ReRankTask implements Runnable {
	//Re-rank in every 5 minute
	private static final int interval = 5 * 60 * 1000;
	//private static final int interval = 5000;
	@Inject PortfolioMgr portfolioMgr;
	@Override
	public void run() {
		while(true){
			portfolioMgr.rerank();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}