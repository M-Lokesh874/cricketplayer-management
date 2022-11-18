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

	public List<CricketTeam> getTeams() throws PlayerManagementException;

	public CricketTeam getTeamAndPlayers(CricketTeam cricketTeam, int teamId) throws PlayerManagementException;
	
	public boolean deleteCricketTeamById(int id) throws PlayerManagementException;

	public CricketTeam getTeamById(int id) throws PlayerManagementException;
	
	public CricketTeam updateTeamById(CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean assignPlayer(List<CricketPlayer> cricketPlayers, CricketTeam cricketTeam) throws PlayerManagementException;

	public boolean assignCaptain(CricketTeam cricketTeam, CricketPlayer cricketPlayer) throws PlayerManagementException;

	public boolean assignWicketKeeper(CricketTeam cricketTeam, CricketPlayer cricketPlayer) throws PlayerManagementException;

	public CricketTeam createTeam(CricketTeam cricketTeam) throws PlayerManagementException;

}