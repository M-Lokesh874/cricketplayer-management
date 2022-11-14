package com.playermanagement.dao;

import java.util.List;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.util.exception.PlayerManagementException;

public interface CricketPlayerStatsDao {

	public CricketPlayerStats insertStats(CricketPlayerStats cricketPlayerStats) throws PlayerManagementException;

	public CricketPlayerStats displayStatsById(int statsId) throws PlayerManagementException;

	public boolean deleteCricketPlayerStatsById(int id) throws PlayerManagementException;

	public CricketPlayerStats updatePlayerStatsById(CricketPlayerStats cricketPlayerStats)
			throws PlayerManagementException;

	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException;

	public boolean assignPlayer(CricketPlayerStats cricketPlayerStats, CricketPlayer cricketPlayer)
			throws PlayerManagementException;
}