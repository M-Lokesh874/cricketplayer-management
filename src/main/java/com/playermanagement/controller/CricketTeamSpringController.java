package com.playermanagement.controller;

import java.util.List;

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
	
	@RequestMapping("/createteam")
	public String createTeam(Model model) {
		model.addAttribute("cricketTeam", new CricketTeam());
		return "createTeam";
	}

	@RequestMapping(value = "/create-team", method = RequestMethod.POST)
	public RedirectView addTeam(@ModelAttribute CricketTeam cricketTeam, Model model) {
		RedirectView redirectView = new RedirectView();
		try {
			if (0 != cricketTeam.getId()) {
				CricketTeam team = cricketTeamService.getTeamById(cricketTeam.getId());
				cricketTeam.setCreatedAt(team.getCreatedAt());
				cricketTeam.setCricketPlayer(team.getCricketPlayer());
				cricketTeamService.updateTeamById(cricketTeam);
			} else {
				cricketTeamService.createTeam(cricketTeam);
			}
			redirectView.setUrl("displayteams");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return redirectView;
	}
	
	@RequestMapping(value = "/delete-team", method = RequestMethod.POST)
	public String deletePlayer(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		boolean isDeleted;
		if (null != id) {
			try {
				isDeleted = cricketTeamService.deleteCricketTeamById(id);
				if (isDeleted) {
					model.addAttribute("isDeleted", "Deleted successfully");
				}
			} catch (PlayerManagementException e) {
				e.printStackTrace();
			}
		}
		return "deleteTeam";
	}
	
	@RequestMapping("/cricketteam")
	public String cricketTeam() {
		return "cricketTeams";
	}
	
	@RequestMapping("/deleteteam")
	public String deleteTeam() {
		return "deleteTeam";
	}
}
