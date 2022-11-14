package com.playermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.util.exception.PlayerManagementException;

@Controller
public class CricketPlayerSpringController {

	@Autowired
	CricketPlayerService cricketPlayerService;

	@RequestMapping("/displayplayers")
	public String displayPlayers(Model model) {
		try {
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();

			for (CricketPlayer cricketPlayer : cricketPlayers) {
				System.out.println(cricketPlayer);
			}

			model.addAttribute("cricketPlayers", cricketPlayers);
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		return "displayPlayers";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/cricketPlayer")
	public String cricketPlayer() {
		return "cricketPlayer";
	}
}
