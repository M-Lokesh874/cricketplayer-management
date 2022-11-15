package com.playermanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playermanagement.dao.impl.CricketPlayerDaoImpl;
import com.playermanagement.dao.CricketPlayerDao;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.util.DateUtil;
import com.playermanagement.util.constant.Gender;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketPlayerServiceImpl contains all method definitions of the cricket
 * player service class.
 * </p>
 *
 * @author Lokesh
 */

@Service
public class CricketPlayerServiceImpl implements CricketPlayerService {

	public int playerId = 0;
	@Autowired
	public CricketPlayerDao cricketPlayerDao;

	/**
	 * {@inheritDoc}
	 * 
	 * @param name        - validated name from the controller
	 * @param country     - validated country name from the controller
	 * @param gender      - gender from the controller
	 * @param dateOfBirth - validated date of birth from the controller
	 * @param email       - validated email from the controller
	 * @return cricketPlayer.
	 */
	public CricketPlayer createPlayer(String name, String country, Gender gender, Date dateOfBirth, String email)
			throws PlayerManagementException {
		CricketPlayer cricketPlayer = new CricketPlayer(name, country, gender, dateOfBirth, email);
		cricketPlayer.setPlayerCode(generateId());
		return cricketPlayerDao.insertPlayer(cricketPlayer);
	}

	/**
	 * <p>
	 * This method is used to generate the id by converting int to string.
	 * </p>
	 *
	 * @return player code.
	 */
	public String generateId() {
		int playerId = cricketPlayerDao.getCount();
		return "CRIC" + Integer.toString(++playerId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return cricketPlayers.
	 */
	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException {
		return cricketPlayerDao.retrievePlayers();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id - player id given by user
	 * @return cricketPlayer.
	 */
	public CricketPlayer getPlayerById(int id) throws PlayerManagementException {
		return cricketPlayerDao.retrievePlayerById(id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id - player id given by user
	 * @return true if removed or false.
	 */
	public boolean deletePlayerById(int id) throws PlayerManagementException {
		return cricketPlayerDao.deletePlayerById(id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name - name to search player
	 * @return - CricketPlayers
	 */
	public List<CricketPlayer> searchPlayerByName(String name) throws PlayerManagementException {
		return cricketPlayerDao.searchPlayerByName(name);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param dateOne - first date given by user
	 * @param dateTwo - second date given by user
	 * @return - CricketPlayers
	 */
	public List<CricketPlayer> retrievePlayersBetweenDate(Date dateOne,Date dateTwo)
			throws PlayerManagementException {
		return cricketPlayerDao.retrievePlayersBetweenDate(dateOne, dateTwo);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param playerId - player id given by user
	 * @return - crickterPlayer
	 */
	public CricketPlayer displayExistingPlayer(int playerId) throws PlayerManagementException {
		return cricketPlayerDao.retrievePlayerAndTeams(playerId);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param playerIds - player id given by user
	 * @return - cricketePlayer
	 */
	public List<CricketPlayer> getPlayersGivenIds(StringBuffer playerIds) throws PlayerManagementException {
		return cricketPlayerDao.getPlayersGivenIds(playerIds);
	}

	public int getCount() {
		return cricketPlayerDao.getCount();
	}

	public boolean assignTeam(CricketPlayer cricketPlayer, List<CricketTeam> cricketTeams) throws PlayerManagementException {
		cricketPlayer.setCricketTeam(cricketTeams);
		return cricketPlayerDao.assignTeam(cricketPlayer);
	}

	/**
	 * <p>
	 * This method is used to update particular player detail.
	 * </p>
	 *
	 * @param playerId - player id given by user.
	 * @param value    - value given by user to update.
	 * @param choice   - choice to update particular detail.
	 */

	public CricketPlayer updatePlayerById(int playerId, String value, int choice) throws PlayerManagementException {
		CricketPlayer cricketPlayer = getPlayerById(playerId);
		switch(choice) {
			case 1:
				cricketPlayer.setName(value);
				break;

			case 2:
				Date dob = DateUtil.convertStringToDate(value);
				cricketPlayer.setDateOfBirth(dob);
				break;

			case 3:
				cricketPlayer.setCountry(value);
				break;

			case 4:
				cricketPlayer.setEmail(value);
				break;

			case 5:
				cricketPlayer.setGender(Gender.valueOf(value));
				break;
		}
		return cricketPlayerDao.updatePlayer(cricketPlayer);
	}

	@Override
	public List<CricketPlayer> getPlayersByMultipleIds(List<Integer> playerIds) throws PlayerManagementException {
		return cricketPlayerDao.retrievePlayersByMultipleIds(playerIds);
	}

	@Override
	public CricketPlayer updatePlayerById(CricketPlayer cricketPlayer) throws PlayerManagementException {
		return cricketPlayerDao.updatePlayer(cricketPlayer);
	}

	@Override
	public CricketPlayer createPlayer(CricketPlayer cricketPlayer) throws PlayerManagementException {
		cricketPlayer.setPlayerCode(generateId());
		return cricketPlayerDao.insertPlayer(cricketPlayer);
	}

}