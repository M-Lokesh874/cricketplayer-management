package com.playermanagement.service;

import java.util.Date;
import java.util.List;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketPlayerService interface contains only the method declaration of
 * the cricket player.
 * </p>
 *
 * @author Lokesh
 */

public interface CricketPlayerService {

	/**
	 * <p>
	 * This method is used to display all player details.
	 * </p>
	 *
	 */
	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get particular player detail.
	 * </p>
	 *
	 * @param id - player id given by user
	 */
	public CricketPlayer getPlayerById(int id) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to delete particular player detail.
	 * </p>
	 *
	 * @param id - player id given by user
	 */
	public boolean deletePlayerById(int id) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to search player by id.
	 * </p>
	 *
	 * @param name - name to search player
	 */
	public List<CricketPlayer> searchPlayerByName(String name) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get certain players by their ids .
	 * </p>
	 *
	 * @param playerId - player id given by user
	 */
	public CricketPlayer displayExistingPlayer(int playerId) throws PlayerManagementException;

	public int getCount();

	public boolean assignTeam(CricketPlayer cricketPlayer, List<CricketTeam> cricketTeam) throws PlayerManagementException;

	List<CricketPlayer> getPlayersByMultipleIds(List<Integer> playerIds) throws PlayerManagementException;

	public CricketPlayer updatePlayerById(CricketPlayer cricketPlayer) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get certain players by their ids .
	 * </p>
	 *
	 * @param dateOne - first dateOfBirth given by user
	 * @param dateTwo - second dateOfBirth given by user
	 */
	public List<CricketPlayer> retrievePlayersBetweenDate(Date startDate, Date endDate) throws PlayerManagementException;

	public CricketPlayer createPlayer(CricketPlayer cricketPlayer) throws PlayerManagementException;
	;
}