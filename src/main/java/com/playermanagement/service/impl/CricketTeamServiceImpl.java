package com.playermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playermanagement.dao.CricketTeamDao;
import com.playermanagement.dao.impl.CricketTeamDaoImpl;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketTeamServiceImpl contains all method definitions of the cricketTeam
 * service class.
 * </p>
 *
 * @author Lokesh
 */
@Service
public class CricketTeamServiceImpl implements CricketTeamService {

	@Autowired
	private CricketTeamDao cricketTeamDao;

	public List<CricketTeam> getTeams() throws PlayerManagementException {
		return cricketTeamDao.retrieveTeams();
	}

	public CricketTeam getTeamAndPlayers(CricketTeam cricketTeam, int teamId) throws PlayerManagementException {
		return cricketTeamDao.retrieveTeamAndPlayers(cricketTeam, teamId);
	}

	public CricketTeam createTeam(String name, int totalMatch, int won, int lost)
			throws PlayerManagementException {
		CricketTeam cricketTeam = new CricketTeam(name, totalMatch, won, lost);
		return cricketTeamDao.insertTeam(cricketTeam);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param id - team id given by user
	 * @return true if removed or false.
	 */
	public boolean deleteCricketTeamById(int id) throws PlayerManagementException {
		return cricketTeamDao.deleteCricketTeamById(id);
	}

	@Override
	/*public boolean assignPlayer(CricketTeam cricketTeam, int playerId) throws PlayerManagementException {
		return cricketTeamDao.assignPlayer(cricketTeam, playerId);
	}*/

	public CricketTeam getTeamById(int id) throws PlayerManagementException {
		return cricketTeamDao.getTeamById(id);
	}

	@Override
	public CricketTeam updateTeamById(int teamId, int value, int choice) throws PlayerManagementException {
		CricketTeam cricketTeam = getTeamById(teamId);
		switch (choice) {
		/*case 2:
			cricketTeam.setCaptainId(value);
			break;

		case 3:
			cricketTeam.setWicketKeeperId(value);
			break;*/
			
		case 4:
			cricketTeam.setWon(value);
			break;

		case 5:
			cricketTeam.setLost(value);
			break;
			
		case 6:	
			cricketTeam.setTotalMatch(value);
			break;

		}
		return cricketTeamDao.updateTeamById(cricketTeam);
	}
	
	public CricketTeam updateTeamById(int teamId, String value, int choice) throws PlayerManagementException {
		CricketTeam cricketTeam = getTeamById(teamId);
		switch (choice) {
		case 1:
			cricketTeam.setName(value);
			break;


		}
		return cricketTeamDao.updateTeamById(cricketTeam);
	}

	@Override
	public boolean assignPlayer(List<CricketPlayer> cricketPlayers, CricketTeam cricketTeam) throws PlayerManagementException {
		cricketTeam.setCricketPlayer(cricketPlayers);
		return cricketTeamDao.assignPlayer(cricketTeam);
	}
	
	public boolean assignCaptain(CricketTeam cricketTeam, CricketPlayer cricketPlayer)
			throws PlayerManagementException {
		cricketTeam.setCaptain(cricketPlayer);
		return cricketTeamDao.assignCaptain(cricketTeam);
	}

	@Override
	public boolean assignWicketKeeper(CricketTeam cricketTeam, CricketPlayer cricketPlayer)
			throws PlayerManagementException {
        cricketTeam.setWicketKeeper(cricketPlayer);
		return cricketTeamDao.assignWicketKeeper(cricketTeam);
	}

	@Override
	public CricketTeam updateTeamById(CricketTeam cricketTeam) throws PlayerManagementException {
		return cricketTeamDao.updateTeamById(cricketTeam);
	}


}