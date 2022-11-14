package com.playermanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;

import org.hibernate.HibernateException;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketPlayerStatsService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketPlayerStatsServiceImpl;
import com.playermanagement.util.exception.PlayerManagementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CricketPlayerStatsServlet","/createstats", "/displaystatsbyid", "/deletestatsbyid", "/updatestatsbyid", "/getstatsbyid",
		"/assignplayerforstats"})
public class CricketPlayerStatsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CricketPlayerStatsService cricketPlayerStatsService;
	public void init() {
		cricketPlayerStatsService = new CricketPlayerStatsServiceImpl();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        try {
        	switch(action) {
        	    case "/createstats":
        	    	createStats(request, response);
        	    	break;
        	    	
        	    case "/displaystatsbyid" :
        	    	displayStatsById(request, response);
        	    	break;
        	    	
        	    case "/deletestatsbyid" :
        	    	deleteStatsById(request, response);
        	    	break;
        	    	
        	    case "/updatestatsbyid" :
        	    	updateStatsById(request, response);
        	    	break;
        	    	
        	    case "/assignplayerforstats" :
        	    	assignPlayerForStats(request, response);
        	    	break;
        	}
			
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
	}
	
	private void assignPlayerForStats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			PrintWriter pw = response.getWriter();
			int statsId = Integer.parseInt(request.getParameter("statsid"));
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(statsId);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			HttpSession session = request.getSession();		
			int id = Integer.parseInt(request.getParameter("enterplayerid"));
		    CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
		    session.setAttribute("cricketPlayer", cricketPlayer);
			session.setAttribute("cricketPlayerStats", cricketPlayerStats);
		    boolean found ;
		    if (found = cricketPlayerStatsService.assignPlayer(cricketPlayerStats,cricketPlayer)) {
		    	response.sendRedirect("assignPlayerForStats.jsp");
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
        	    	
        	    case "/getstatsbyid" :
        	    	getStatsById(request, response);
        	    	break;
        	}
			
		} catch (HibernateException ex) {
			System.out.println(ex);
		}
	}
	
	private void getStatsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();	
			session.setAttribute("cricketPlayer", cricketPlayerStats);
			response.sendRedirect("updateStats.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void updateStatsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw = response.getWriter();
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(Integer.parseInt(request.getParameter("id")));	
				cricketPlayerStats.setNoOfMatch(Integer.parseInt(request.getParameter("noofmatch")));
				cricketPlayerStats.setNoOfWicket(Integer.parseInt(request.getParameter("noofwickets")));
				cricketPlayerStats.setTopScore(Integer.parseInt(request.getParameter("topscore")));
				cricketPlayerStats.setTotalRun(Integer.parseInt(request.getParameter("totalrun")));
				cricketPlayerStats.setNoOfBallsFaced(Integer.parseInt(request.getParameter("noofballsfaced")));
				cricketPlayerStats.setBattingAverage(Double.parseDouble(request.getParameter("battingaverage")));
				cricketPlayerStats.setStrikeRate(Double.parseDouble(request.getParameter("strikerate")));
			    cricketPlayerStats = cricketPlayerStatsService.updatePlayerStatsById(cricketPlayerStats);
				HttpSession session = request.getSession();	
				session.setAttribute("cricketPlayerStats", cricketPlayerStats);
				response.sendRedirect("updateStats.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}

	private void createStats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		int noOfMatch = Integer.parseInt(request.getParameter("noofmatch"));
		int totalRun = Integer.parseInt(request.getParameter("totalrun"));
		double battingAverage = getBattingAverage(noOfMatch, totalRun);
		int topScore = Integer.parseInt(request.getParameter("topscore"));
		int noOfWickets = Integer.parseInt(request.getParameter("noofwickets"));
		int noOfBallsFaced = Integer.parseInt(request.getParameter("noofballsfaced"));
		double strikeRate = getStrikeRate(totalRun, noOfBallsFaced);
		try {
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.createStats(noOfMatch, totalRun, battingAverage, topScore, noOfBallsFaced, strikeRate);
			
			if(null != cricketPlayerStats) {
				pw.println("inserted successfully");
			} else {
				pw.println("Error occurred while inserting ");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}
	
	private void displayStatsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw = response.getWriter();
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();	
			session.setAttribute("cricketPlayerStats", cricketPlayerStats);
			response.sendRedirect("getStats.jsp");	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
		
	}
	
	public double getBattingAverage(int noOfMatch, int totalRun) {
		while (true) {
			try {
				if (0 != noOfMatch && 0 != totalRun) {
					return totalRun / noOfMatch;
				} else {
					System.out.println("Record not found");
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(inputMismatchException);
			}
		}
	}
	
	public double getStrikeRate(int totalRun, int noOfBallsFaced) {
		while (true) {
			try {
				if (0 != noOfBallsFaced && 0 != totalRun) {
					return (100 * totalRun / noOfBallsFaced);
				} else {
					System.out.println("Record not found");
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(inputMismatchException);
			}
		}
	}
	
	private void deleteStatsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Boolean found = cricketPlayerStatsService.deleteCricketPlayerStatsById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("found",found);
			response.sendRedirect("deleteStats.jsp");
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}
	}

}
