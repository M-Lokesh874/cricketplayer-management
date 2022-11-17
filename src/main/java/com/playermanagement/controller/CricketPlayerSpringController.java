package com.playermanagement.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.service.impl.CricketTeamServiceImpl;
import com.playermanagement.util.exception.PlayerManagementException;

@Controller
public class CricketPlayerSpringController {

	@Autowired
	CricketPlayerService cricketPlayerService;

	@Autowired
	CricketTeamService cricketTeamService;

	@RequestMapping("/createplayer")
	public String createPlayer(Model model) {
		model.addAttribute("cricketPlayer", new CricketPlayer());
		return "createPlayer";
	}

	@RequestMapping(value = "/create-player", method = RequestMethod.POST)
	public RedirectView addPlayer(@ModelAttribute CricketPlayer cricketPlayer, Model model) {
		RedirectView redirectView = new RedirectView();
		try {
			if (0 != cricketPlayer.getId()) {
				cricketPlayer.setCreatedAt(cricketPlayerService.getPlayerById(cricketPlayer.getId()).getCreatedAt());
				cricketPlayerService.updatePlayerById(cricketPlayer);
			} else {
				cricketPlayerService.createPlayer(cricketPlayer);
			}
			redirectView.setUrl("displayplayers");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return redirectView;
	}

	@RequestMapping("/displayplayers")
	public String getPlayers(Model model) {
		try {
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			model.addAttribute("cricketPlayers", cricketPlayers);
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "displayPlayers";
	}

	@RequestMapping(value = { "/getplayer", "/getplayerbyid" })
	public String getPlayerByid(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		String action = request.getServletPath();
		CricketPlayer cricketPlayer = new CricketPlayer();
		try {
			if (null != id) {
				cricketPlayer = cricketPlayerService.getPlayerById(id);
				model.addAttribute("cricketPlayer", cricketPlayer);
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		if (action.equals("/getplayer")) {
			return "getPlayer";
		} else
			return "createPlayer";
	}

	@RequestMapping(value = "/delete-player", method = RequestMethod.POST)
	public String deletePlayer(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		boolean isDeleted;
		if (null != id) {
			try {
				isDeleted = cricketPlayerService.deletePlayerById(id);
				if (isDeleted) {
					model.addAttribute("isDeleted", "Deleted successfully");
				}
			} catch (PlayerManagementException e) {
				e.printStackTrace();
			}
		}
		return "deletePlayer";
	}

	@GetMapping("/search-player")
	public String searchPlayer(HttpServletRequest request, @RequestParam(value = "name", required = false) String name,
			Model model) {
		try {
			if (null != name) {
				List<CricketPlayer> cricketPlayerss = cricketPlayerService.getCricketPlayers();
				List<CricketPlayer> cricketPlayers = cricketPlayerss.stream()
						.filter(player -> player.getName().contains(name)).collect(Collectors.toList());
				model.addAttribute("cricketPlayers", cricketPlayers);
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "searchPlayer";
	}

	@RequestMapping(value = "/assign-team", method = RequestMethod.POST)
	public String assignTeam(@RequestParam(value = "playerid", required = false) Integer playerid,
			@RequestParam(value = "teamid", required = false) Integer teamid, Model model) {
		try {
			System.out.println(playerid);
			System.out.println(teamid);
			if (null != playerid && null != teamid) {
				CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(playerid);
				List<CricketTeam> cricketTeams = cricketTeamService.getTeams();
				cricketTeams.get(teamid - 1);
				System.out.println(cricketPlayer);
				System.out.println(cricketTeams);
				cricketPlayerService.assignTeam(cricketPlayer, cricketTeams);
				model.addAttribute("cricketPlayer", cricketPlayer);
				model.addAttribute("cricketTeams", cricketTeams);
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "assignTeam";
	}

	@RequestMapping("/assignteam")
	public String assignTeam() {
		return "assignTeam";
	}

	@RequestMapping("/searchplayer")
	public String searchPlayer() {
		return "searchPlayer";
	}

	@RequestMapping("/index")
	public String home() {
		return "index";
	}

	@RequestMapping("/cricketplayer")
	public String cricketPlayer() {
		return "cricketPlayer";
	}

	@RequestMapping("/updateplayer")
	public String updatePlayer() {
		return "updatePlayer";
	}

	@RequestMapping("/deleteplayer")
	public String deletePlayer() {
		return "deletePlayer";
	}

}
