package com.playermanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.util.exception.PlayerManagementException;

@Controller
public class CricketPlayerSpringController {

	@Autowired
	CricketPlayerService cricketPlayerService;

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
			}
			model.addAttribute("cricketPlayer", cricketPlayer);
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
