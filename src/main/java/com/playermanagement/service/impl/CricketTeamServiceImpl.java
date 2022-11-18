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

	public boolean deleteCricketTeamById(int id) throws PlayerManagementException {
		return cricketTeamDao.deleteCricketTeamById(id);
	}

	public CricketTeam getTeamById(int id) throws PlayerManagementException {
		return cricketTeamDao.getTeamById(id);
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

	@Override
	public CricketTeam createTeam(CricketTeam cricketTeam) throws PlayerManagementException {
		return cricketTeamDao.insertTeam(cricketTeam);	
	}


}