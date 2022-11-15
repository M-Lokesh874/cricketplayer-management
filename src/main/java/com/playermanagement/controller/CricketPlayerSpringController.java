package com.playermanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/getplayer")
	public String getPlayerById(HttpServletRequest request, @RequestParam(value = "id", required = false) Integer id,
			Model model) {
		try {
			if (null != id) {
				CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(id);
				model.addAttribute("cricketPlayer", cricketPlayer);
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "getPlayer";
	}

	@RequestMapping("/createplayer")
	public String createPlayer(Model model) {
		model.addAttribute("cricketPlayer", new CricketPlayer());
		return "createPlayer";
	}
	
	@RequestMapping(value = "/create-player", method = RequestMethod.POST)
	public RedirectView addPlayer(@ModelAttribute CricketPlayer cricketPlayer, Model model) {
		RedirectView redirectView = new RedirectView();
	    try {
			cricketPlayerService.createPlayer(cricketPlayer);
			redirectView.setUrl("displayplayers");
			//model.addAttribute("cricketPlayers", cricketPlayer);
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return redirectView;//getPlayers(model)
	}

	@RequestMapping("/index")
	public String home() {
		return "index";
	}

	@RequestMapping("/cricketPlayer")
	public String cricketPlayer() {
		return "cricketPlayer";
	}
}
