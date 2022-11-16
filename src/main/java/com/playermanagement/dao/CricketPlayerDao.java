package com.playermanagement.dao;

import java.util.Date;
import java.util.List;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.util.exception.PlayerManagementException;

public interface CricketPlayerDao {

	/**
	 * <p>
	 * This method is used to insert the objects of cricket player.
	 * </p>
	 *
	 * @param cricketPlayer - cricketPlayer
	 *
	 */
	public CricketPlayer insertPlayer(CricketPlayer cricketPlayer) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to display all player details.
	 * </p>
	 *
	 * @return Map cricketPlayers.
	 */
	public List<CricketPlayer> retrievePlayers() throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get particular player detail.
	 * </p>
	 *
	 * @return CricketPlayer id
	 */
	public CricketPlayer retrievePlayerById(int id) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to delete particular player detail.
	 * </p>
	 *
	 * @return true if removed or false.
	 */
	public boolean deletePlayerById(int id) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to generate id based on row count.
	 * </p>
	 *
	 * @return cricketPlayers.
	 */
	public int getCount();

	/**
	 * <p>
	 * This method is used to display all player details.
	 * </p>
	 *
	 * @return cricketPlayer.
	 */
	public CricketPlayer retrievePlayerAndTeams(int playerId) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to search the player.
	 * </p>
	 *
	 * @param name name given by user to search.
	 * @return cricketPlayers
	 */
	public List<CricketPlayer> searchPlayerByName(String name) throws PlayerManagementException;



	public boolean assignTeam(CricketPlayer cricketPlayer) throws PlayerManagementException;

	public CricketPlayer updatePlayer(CricketPlayer cricketPlayer) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get players by multiple ids.
	 * </p>
	 *
	 */
	public List<CricketPlayer> retrievePlayersByMultipleIds(List<Integer> playerIds) throws PlayerManagementException;

	/**
	 * <p>
	 * This method is used to get player details between the given date of birth.
	 * </p>
	 *
	 * @return cricketPlayers.
	 */
	public List<CricketPlayer> retrievePlayersBetweenDate(Date dateOne, Date dateTwo) throws PlayerManagementException;
}