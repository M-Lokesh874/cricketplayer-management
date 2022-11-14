package com.playermanagement.service;

import java.util.List;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketTeamService interface contains only the method declaration of the
 * CricketTeam class.
 * </p>
 *
 * @author Lokesh
 */
public interface CricketTeamService {

	public List<CricketTeam> getTeams();

	public CricketTeam getTeamAndPlayers(CricketTeam cricketTeam, int teamId) throws PlayerManagementException;

	public CricketTeam createTeam(String name, int totalMatch, int won, int lost)
			throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to delete particular player stats detail.
	 * </p>
	 *
	 * @param id - team id given by user
	 */
	public boolean deleteCricketTeamById(int id) throws PlayerManagementException;

	//public boolean assignPlayer(CricketTeam cricketTeam, int playerId) throws PlayerManagementException;

	public CricketTeam getTeamById(int id) throws PlayerManagementException;

	public CricketTeam updateTeamById(int teamId, int cricketTeam, int choice) throws PlayerManagementException;
	
	public CricketTeam updateTeamById(int teamId, String value, int choice) throws PlayerManagementException;
	
	public CricketTeam updateTeamById(CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean assignPlayer(List<CricketPlayer> cricketPlayers, CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean assignCaptain(CricketTeam cricketTeam, CricketPlayer cricketPlayer) throws PlayerManagementException;

	public boolean assignWicketKeeper(CricketTeam cricketTeam, CricketPlayer cricketPlayer) throws PlayerManagementException;

}