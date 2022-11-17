package com.playermanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.HibernateException;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketTeamServiceImpl;
import com.playermanagement.util.exception.PlayerManagementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CricketTeamServlet"})
public class CricketTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CricketTeamService cricketTeamService;
	
	public void init() {
		cricketTeamService = new CricketTeamServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        try {
        	switch(action) {
        	    case "/createteam":
        	    	createTeam(request, response);
        	    	break;
        	    	
        	    case "/deleteteambyid" :
        	    	deleteTeamById(request, response);
        	    	break;
        	    	
        	    case "/updateteambyid" :
        	    	updateTeamById(request, response);
        	    	break;
        	    	
        	    case "/assigncaptain" :
        	    	assignCaptain(request, response);
        	    	break;
        	    	
        	    case "/assignwicketkeeper" :
        	    	assignWicketkeeper(request, response);
        	    	break;
        	}
			
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
	}
	
	private void assignWicketkeeper(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			PrintWriter pw = response.getWriter();
			int teamId = Integer.parseInt(request.getParameter("teamid"));
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			int id = Integer.parseInt(request.getParameter("playerid"));
			CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
			HttpSession session = request.getSession();
			response.sendRedirect("assignWicketKeeper.jsp");
			boolean found;
			if (found = cricketTeamService.assignWicketKeeper(cricketTeam, cricketPlayer)) {
				session.setAttribute("found", found);
			} else {
				pw.println("not assigned successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}

	private void assignCaptain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			PrintWriter pw = response.getWriter();
			int teamId = Integer.parseInt(request.getParameter("teamid"));
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			int id = Integer.parseInt(request.getParameter("playerid"));
			CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
			HttpSession session = request.getSession();
			response.sendRedirect("assignCaptain.jsp");
			boolean found;
			if (found = cricketTeamService.assignCaptain(cricketTeam, cricketPlayer)) {
				session.setAttribute("found", found);
			} else {
				pw.println("not assigned successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
        	switch(action) {
        	    	
        	    case "/displayteams" :
        	    	displayTeams(request, response);
        	    	break;
        	}
			
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
	}

	private void updateTeamById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			PrintWriter pw = response.getWriter();
			CricketTeam cricketTeam = cricketTeamService.getTeamById(Integer.parseInt(request.getParameter("id")));	
			cricketTeam.setName(request.getParameter("name"));
			cricketTeam.setTotalMatch(Integer.parseInt(request.getParameter("totalmatch")));
			cricketTeam.setWon(Integer.parseInt(request.getParameter("won")));
			cricketTeam.setLost(Integer.parseInt(request.getParameter("lost")));
		    cricketTeam = cricketTeamService.updateTeamById(cricketTeam);
		    HttpSession session = request.getSession();	
			session.setAttribute("cricketTeam", cricketTeam);
			response.sendRedirect("updateTeam.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}

	private void deleteTeamById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Boolean found = cricketTeamService.deleteCricketTeamById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("found",found);
			response.sendRedirect("deleteTeam.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}

	private void displayTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		List<CricketTeam> cricketTeams = cricketTeamService.getTeams();
		HttpSession session = request.getSession();
		session.setAttribute("cricketTeams", cricketTeams);
		response.sendRedirect("displayTeam.jsp");	
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}

	private void createTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("name");
		int totalMatch = Integer.parseInt(request.getParameter("totalmatch"));
		int won = Integer.parseInt(request.getParameter("won"));
		int lost =  Integer.parseInt(request.getParameter("lost"));
		try {
			CricketTeam cricketTeam = cricketTeamService.createTeam(name, totalMatch, won, lost);
			if(null != cricketTeam) {
				pw.println("inserted successfully");
			} else {
				pw.println("Error occurred while inserting ");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}

}
