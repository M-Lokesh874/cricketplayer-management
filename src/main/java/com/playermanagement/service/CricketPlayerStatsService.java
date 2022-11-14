package com.playermanagement.service;

import java.util.List;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketPlayerStatsService interface contains only the method declaration
 * of the CricketPlayerstats class.
 * </p>
 *
 * @author Lokesh
 */
public interface CricketPlayerStatsService {

	/**
	 * <p>
	 * This method is used to store the objects of cricketTeam.
	 * </p>
	 *
	 */
	public CricketPlayerStats createStats(int noOfMatch, int totalRun, double battingAverage, int topScore,
			int noOfBallsFaced, double strikeRate) throws PlayerManagementException;

	public CricketPlayerStats displayStatsById(int playerId) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to delete particular player detail.
	 * </p>
	 *
	 * @param id - player stats id given by user
	 */
	public boolean deleteCricketPlayerStatsById(int id) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to update particular player detail.
	 * </p>
	 *
	 * @param statsId - player stats id given by user to update
	 */
	public CricketPlayerStats updatePlayerStatsById(CricketPlayerStats cricketPlayerStats)
			throws PlayerManagementException;


	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException;

	public boolean assignPlayer(CricketPlayerStats cricketPlayerStats, CricketPlayer cricketPlayer)
			throws PlayerManagementException;
}