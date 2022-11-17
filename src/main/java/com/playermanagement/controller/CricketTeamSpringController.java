package com.playermanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.util.PlayerManagementLogger;
import com.playermanagement.util.exception.PlayerManagementException;

@Controller
public class CricketTeamSpringController {

	@Autowired
	private CricketTeamService cricketTeamService;

	@GetMapping("/displayteams")
	public String getTeams(Model model) {
		try {
			List<CricketTeam> cricketTeams = cricketTeamService.getTeams();
			for(CricketTeam cricketTeam: cricketTeams) {
				System.out.println(cricketTeam);
			}
			model.addAttribute("cricketTeams", cricketTeams);
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
		return "displayTeam";
	}
	
	@RequestMapping("/getteam")
	public String getTeamById(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		try {
			if (null != id) {
				CricketTeam cricketTeam = cricketTeamService.getTeamById(id);
				System.out.println(cricketTeam);
				model.addAttribute("cricketTeam", cricketTeam);
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "getTeam";
	}
	
	/*
	 * @RequestMapping("/index") public String index() { return "/index"; }
	 */
	
	@RequestMapping("/cricketteam")
	public String cricketTeam() {
		return "cricketTeams";
	}
}
