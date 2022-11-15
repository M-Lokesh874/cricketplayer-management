package com.playermanagement.dao;

import java.util.List;

import com.playermanagement.model.CricketTeam;
import com.playermanagement.util.exception.PlayerManagementException;

public interface CricketTeamDao {

	public List<CricketTeam> retrieveTeams() throws PlayerManagementException;

	public CricketTeam retrieveTeamAndPlayers(CricketTeam cricketTeam, int teamId) throws PlayerManagementException;

	public CricketTeam insertTeam(CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean deleteCricketTeamById(int id) throws PlayerManagementException;

	public boolean assignPlayer(CricketTeam cricketTeam) throws PlayerManagementException;

	public CricketTeam getTeamById(int id) throws PlayerManagementException;

	public CricketTeam updateTeamById(CricketTeam cricketTeam) throws PlayerManagementException;
	
	public boolean assignCaptain(CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean assignWicketKeeper(CricketTeam cricketTeam)  throws PlayerManagementException;
}