package com.playermanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketTeamServiceImpl;
import com.playermanagement.util.DateUtil;
import com.playermanagement.util.PlayerManagementLogger;
import com.playermanagement.util.constant.CricketPlayerConstants;
import com.playermanagement.util.constant.Gender;
import com.playermanagement.util.exception.PlayerManagementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns={"/CricketPlayerServlet","/deleteplayerbyid", "/updateplayerbyid", 
		    "/getplayerbyid", "/searchplayerbyid", "/displayplayersbetweendateofbirth", "/displayplayersbymultipleids",
		    "/assignteam"})
public class CricketPlayerServlet extends HttpServlet {

	private final Scanner scanner = new Scanner(System.in);
	CricketPlayerService cricketPlayerService;
	
	public void init() {
		cricketPlayerService = new CricketPlayerServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/createplayer":
                    createPlayer(request, response);
                    break;
                    
			    case "/displayplayers": 
			    	displayPlayers(request, response);
				    break;		 
                    
                case "/displayplayerbyid":
                	displayPlayerById(request, response);
                	break;
                	
                case "/deleteplayerbyid":
                	deletePlayerById(request, response);
                	break;
                	
                case "/updateplayerbyid":
                	updatePlayerById(request, response);
                	break;
                	
                case "/searchplayerbyid":
                	searchPlayerById(request, response);
                	break;
                	
                case "/displayplayersbetweendateofbirth":
                	displayPlayersBetweenDateOfBirth(request, response);
                	break;
                	
                case "/displayplayersbymultipleids":
                	displayPlayersByMultipleIds(request, response);
                	break;
                	
                case "/assignteam":
                	assignTeam(request, response);
                	break;
                	
            }
        } catch (HibernateException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void assignTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CricketTeamService  cricketTeamService = new CricketTeamServiceImpl();	
		try {
			PrintWriter pw = response.getWriter();
			int playerId = Integer.parseInt(request.getParameter("playerid"));
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(playerId);
			List<CricketTeam> cricketTeams = cricketTeamService.getTeams();
			int teamId = Integer.parseInt(request.getParameter("teamid"));
			CricketTeam cricketTeam = cricketTeams.get(teamId-1);
			cricketTeams.clear();
			cricketTeams.add(cricketTeam);
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayer", cricketPlayer);
			session.setAttribute("cricketTeam", cricketTeam);
			response.sendRedirect("assignTeam.jsp");
			boolean  found;
			found = cricketPlayerService.assignTeam(cricketPlayer, cricketTeams);
			if(found) {
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
            switch (action) {
                case "/getplayerbyid":
                    getPlayerById(request, response);
                    break;
                    
                case "/displayplayers":
                    displayPlayers(request, response);
                   break;
            }
        } catch (HibernateException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void getPlayerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("cricketplayer", cricketPlayer);
			response.sendRedirect("updatePlayer.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}

	private void createPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		Gender gender = Gender.valueOf(request.getParameter("gender"));
		String email = request.getParameter("email");
		try {
			Date dateOfBirth = DateUtil.convertStringToDate(request.getParameter("dateofbirth"));
			CricketPlayer cricketPlayer = cricketPlayerService.createPlayer(name, country, gender, dateOfBirth, email);
			if(null != cricketPlayer) {
				pw.println("inserted successfully");
			} else {
				pw.println("Error occurred while inserting ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void displayPlayers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayers", cricketPlayers);
			response.sendRedirect("displayPlayers.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void displayPlayerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayer", cricketPlayer);
			response.sendRedirect("getPlayer.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void deletePlayerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Boolean found = cricketPlayerService.deletePlayerById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("found",found);
			response.sendRedirect("deletePlayer.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePlayerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(Integer.parseInt(request.getParameter("id")));
			cricketPlayer.setName(request.getParameter("name"));
			cricketPlayer.setCountry(request.getParameter("country"));
			cricketPlayer.setDateOfBirth(DateUtil.convertStringToDate(request.getParameter("dateofbirth")));
			cricketPlayer.setGender(Gender.valueOf(request.getParameter("gender")));
			cricketPlayer.setEmail(request.getParameter("email"));
		    cricketPlayer = cricketPlayerService.updatePlayerById(cricketPlayer);
		    HttpSession session = request.getSession();	
			session.setAttribute("cricketPlayer", cricketPlayer);
			response.sendRedirect("updatePlayer.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void searchPlayerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			PrintWriter pw = response.getWriter();
			List<CricketPlayer> cricketPlayers = cricketPlayerService.searchPlayerByName(request.getParameter("name"));
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayers", cricketPlayers);
			response.sendRedirect("searchPlayer.jsp");
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}
	
	private void displayPlayersBetweenDateOfBirth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<CricketPlayer> cricketPlayers = cricketPlayerService.retrievePlayersBetweenDate(DateUtil.convertStringToDate(request.getParameter("startdate")), DateUtil.convertStringToDate(request.getParameter("enddate")));
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayers", cricketPlayers);
			response.sendRedirect("displayPlayersBetweenDateOfBirth.jsp");

		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(CricketPlayerConstants.INVALID);
		}
		
	}
	
	private void displayPlayersByMultipleIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CricketPlayer> cricketPlayers = null;
		try {
			int i = 0;
			int b = 0;
			int noOfIds = Integer.parseInt(request.getParameter("noofids"));
			List<Integer> playerIds = new ArrayList<Integer>();
			do {
				b = Integer.parseInt(request.getParameter("playerids "+ (i + 1)));
				playerIds.add(b);
				i++;
			} while(--noOfIds > 0);
		    cricketPlayers = cricketPlayerService.getPlayersByMultipleIds(playerIds);
			HttpSession session = request.getSession();
			session.setAttribute("cricketPlayers", cricketPlayers);
			response.sendRedirect("displayMultiplePlayers.jsp");
		} catch (PlayerManagementException playerManagementException) {
			System.out.println(playerManagementException);
		}
		
	}
	
}
