package com.playermanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketPlayerStatsService;
import com.playermanagement.util.exception.PlayerManagementException;

@Controller
public class CricketPlayerStatsSpringController {

	@Autowired
	private CricketPlayerStatsService cricketPlayerStatsService;

	@Autowired
	CricketPlayerService cricketPlayerService;

	@RequestMapping(value = { "/getstats", "/get-player-for-assign" })
	public String getStatsbyId(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		String action = request.getServletPath();
		try {
			if (null != id) {
				if (action.equalsIgnoreCase("/getStats")) {
					CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(id);
					model.addAttribute("cricketPlayerStats", cricketPlayerStats);
					return "getStats";
				} else {
					System.out.println(id);
					CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(id);
					if (null != cricketPlayer) {
						model.addAttribute("cricketPlayer", cricketPlayer);
						model.addAttribute("status", "verified successfully");
						model.addAttribute("stats", new CricketPlayerStats());
					}
					System.out.println(cricketPlayer);
					return "createStats";
				}
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return action;
	}

	@PostMapping("/create-stats")
	public String createStats(@ModelAttribute CricketPlayerStats cricketPlayerStats,
			@ModelAttribute CricketPlayer cricketPlayer, Model model) {
		try {
			System.out.println(cricketPlayer);
			if (0 != cricketPlayerStats.getId()) {
				CricketPlayerStats player = cricketPlayerStatsService.displayStatsById(cricketPlayerStats.getId());
				cricketPlayerStats.setCreatedAt(player.getCreatedAt());
				cricketPlayerStats.setCricketPlayer(player.getCricketPlayer());
				cricketPlayerStatsService.updatePlayerStatsById(cricketPlayerStats);
			} else {
				cricketPlayerStatsService.createStats(cricketPlayerStats, cricketPlayer);
				model.addAttribute("created", "inserted successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "createStats";
	}

	@PostMapping("/createstats")
	public String createStats(@RequestParam(value = "id", required = false) Integer id, Model model) {

		return "createStats";
	}

	@GetMapping("/playerstats")
	public String playerStats() {
		return "playerStats";
	}

	@GetMapping("/getplayerforassign")
	public String create() {
		return "createStats";
	}

}
