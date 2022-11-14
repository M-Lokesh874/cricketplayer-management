package com.playermanagement.service.impl;

import java.util.List;

import com.playermanagement.dao.CricketPlayerStatsDao;
import com.playermanagement.dao.impl.CricketPlayerStatsDaoImpl;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.service.CricketPlayerStatsService;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketPlayerStatsServiceImpl contains all method definitions of the
 * cricketPlayerStats service class.
 * </p>
 *
 * @author Lokesh
 */
public class CricketPlayerStatsServiceImpl implements CricketPlayerStatsService {

	private CricketPlayerStatsDao cricketPlayerStatsDao = new CricketPlayerStatsDaoImpl();

	public CricketPlayerStats createStats(int noOfMatch, int totalRun, double battingAverage, int topScore,
			int noOfBallsFaced, double strikeRate) throws PlayerManagementException {
		CricketPlayerStats cricketPlayerStats = new CricketPlayerStats(noOfMatch, totalRun, battingAverage, topScore,
				noOfBallsFaced, strikeRate);
		return cricketPlayerStatsDao.insertStats(cricketPlayerStats);
	}

	public CricketPlayerStats displayStatsById(int statsId) throws PlayerManagementException {
		CricketPlayerStats cricketPlayerStats = cricketPlayerStatsDao.displayStatsById(statsId);
		if (null != cricketPlayerStats) {
			return cricketPlayerStats;
		} else {
			throw new PlayerManagementException("Record not found");
		}
	}

	/**
	 * {@inheritdoc}
	 *
	 * @param player stats Id - team id given by user
	 * @return true if removed or false.
	 */
	public boolean deleteCricketPlayerStatsById(int id) throws PlayerManagementException {
		return cricketPlayerStatsDao.deleteCricketPlayerStatsById(id);
	}

	public CricketPlayerStats updatePlayerStatsById(CricketPlayerStats cricketPlayerStats)
			throws PlayerManagementException {
		return cricketPlayerStatsDao.updatePlayerStatsById(cricketPlayerStats);
	}

	@Override
	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException {

		return cricketPlayerStatsDao.getCricketPlayers();
	}

	public boolean assignPlayer(CricketPlayerStats cricketPlayerStats, CricketPlayer cricketPlayer)
			throws PlayerManagementException {

		return cricketPlayerStatsDao.assignPlayer(cricketPlayerStats, cricketPlayer);
	}

}
