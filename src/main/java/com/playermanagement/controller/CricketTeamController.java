package com.playermanagement.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.playermanagement.controller.CricketTeamController;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketTeamServiceImpl;
import com.playermanagement.util.constant.CricketPlayerConstants;
import com.playermanagement.util.CricketPlayerValidation;
import com.playermanagement.util.DateUtil;
import com.playermanagement.util.PlayerManagementLogger;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketTeamController program simply create, display, and delete the
 * cricket team.
 * </p>
 *
 * @author Lokesh
 */
public class CricketTeamController {

	public CricketTeamService cricketTeamService = new CricketTeamServiceImpl();
	public CricketTeam cricketTeam = new CricketTeam();
	private Scanner scanner = new Scanner(System.in);

	/**
	 * <p>
	 * This method is used to show the menu of cricket team.
	 * </p>
	 */
	public void showMenu() {
		int choice = 0;
		do {
			try {
				choice = getChoice(
						"\n1.Create team\n2.Display teams\n3.get team\n4.Display team member\n5.Delete team\n6.update\n7.assign player\n8.assign captain\n9.assign wicketkeeper\n10.Exit");
				switch (choice) {
				case 1:
					createTeam();
					break;

				case 2:
					displayTeams();
					break;

				case 3:
					getTeamById(getChoice("Enter team id"));
					break;

				case 4:
					displayTeamAndPlayers(getChoice("Enter team id"));
					break;

				case 5:
					deleteTeam();
					break;
					
				case 6:
					updateTeam();
					break;

				case 7:
					assignPlayer();
					break;
					
				case 8:
					assignCaptain();
					break;
					
				case 9:
					assignWicketKeeper();
					break;

				case 10:
					break;

				default:
					System.out.println(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(CricketPlayerConstants.INVALID);
				scanner.nextLine();
			}
		} while (10 != choice);
	}

	/**
	 * <p>
	 * This method is used to display all cricket team.
	 * </p>
	 */
	public void displayTeams() {
		System.out.println("All cricket teams");
		List<CricketTeam> cricketTeams = cricketTeamService.getTeams();
		cricketTeams.forEach(team -> System.out.println(team));
	}

	/**
	 * <p>
	 * This method is used to get cricket team by its id.
	 * </p>
	 */
	public void displayTeamAndPlayers(int teamId) {
		try {
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			cricketTeam = cricketTeamService.getTeamAndPlayers(cricketTeam, teamId);
			System.out.println(cricketTeam);
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	public void getTeamById(int teamId) {
		try {
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			if (null != cricketTeam) {
				System.out.println(cricketTeam);
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to create cricket team.
	 * </p>
	 */
	public void createTeam() {
		try {
			String name = getName();
			int totalMatch = getCricketTeam("Enter total match played by team");
			int won = getCricketTeam("Enter total match won");
			int lost = getCricketTeam("Enter total match lost");
			CricketTeam cricketTeam = cricketTeamService.createTeam(name, totalMatch, won,
					lost);
			System.out.println(cricketTeam);
		} catch (PlayerManagementException playerManagementException) {
			System.out.println(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid name of the cricket team.
	 * </p>
	 *
	 * @return valid name
	 */
	private String getName() {
		while (true) {
			try {
				String name = getString("Enter team name");
				if (CricketPlayerValidation.isValidName(name)) {
					return name;
				} else {
					System.out.println(CricketPlayerConstants.INVALID_NAME);
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(inputMismatchException);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid cricket teams from the user.
	 * </p>
	 *
	 * @return valid team.
	 */
	public int getCricketTeam(String message) {
		System.out.println(message);
		while (true) {
			try {
				int teamAttributes = getInteger();
				if (CricketPlayerValidation.isValidNumber(teamAttributes)) {
					return teamAttributes;
				} else {
					System.out.println("Invalid number");
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(inputMismatchException);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to delete cricket team.
	 * </p>
	 */
	public void deleteTeam() {
		try {
			int id = getChoice("Enter team id to delete");
			if (cricketTeamService.deleteCricketTeamById(id)) {
				System.out.println(CricketPlayerConstants.DELETED);
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			System.out.println(CricketPlayerConstants.MESSAGE);
		}
	}

	/**
	 * <p>
	 * This method is used to get integer input from user.
	 * </p>
	 */
	private int getChoice(String message) {
		System.out.println(message);
		int temporary = scanner.nextInt();
		scanner.nextLine();
		return temporary;
	}

	/**
	 * <p>
	 * This method is used to get String input from user.
	 * </p>
	 */
	private String getString(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

	/**
	 * <p>
	 * This method is used to get Integer input from user.
	 * </p>
	 */
	private int getInteger() {
		return scanner.nextInt();
	}
	
	public void updateTeam() {
		boolean check = true;
		while (check) {
			try {
				int teamId = getChoice("Enter your team id");
				CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
				if (null != cricketTeam) {
					System.out.println(
							"\n1.name\n2.captain id\n3.wicketkeeper id\n4.won\n5.lost\n6.total match\n7.exit");
					int choice = getChoice(CricketPlayerConstants.CHOICE);
					switch (choice) {
					case 1:
						cricketTeamService.updateTeamById(teamId,
								getName(), choice);
						System.out.println("updated successfully");
						break;

					/*case 2:
						cricketTeamService.updateTeamById(teamId,
								 getCricketTeam("enter captain id"), choice);
						System.out.println("updated successfully");
						break;

					case 3:
						cricketTeamService.updateTeamById(teamId,
								 getCricketTeam("enter wicketkeeper id"), choice);
						System.out.println("updated successfully");
						break;*/

					case 4:
						cricketTeamService.updateTeamById(teamId,
								 getCricketTeam("enter total won"), choice);
						System.out.println("updated successfully");
						break;

					case 5:
						cricketTeamService.updateTeamById(teamId,
								 getCricketTeam("enter total lost"), choice);
						System.out.println("updated successfully");
						break;

					case 6:
						cricketTeamService.updateTeamById(teamId,
								 getCricketTeam("enter total match played"), choice);
						break;

					case 7:
						check = false;
						break;

					default:
						System.out.println("Invalid input");
					}
					if (true) {
						break;
					}
				} else {
					System.out.println("Record not found");
				}

			} catch (PlayerManagementException playerManagementException) {
				System.out.println(playerManagementException);
			}
		}
	}
	
	
	public void assignPlayer() {
		try {
			List<CricketPlayer> cricketPlayers = new ArrayList<CricketPlayer>();
			CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();

			int teamId = getChoice("Enter team id to get their details");
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);

			cricketPlayers = cricketPlayerService.getCricketPlayers();
			cricketPlayers.forEach(player -> System.out.println(player));

			System.out.println("enter player id to be a part of this team");
			int playerId = scanner.nextInt();
			CricketPlayer cricketPlayer = cricketPlayers.get(playerId - 1);
			cricketPlayers.clear();
			cricketPlayers.add(cricketPlayer);
			
			if (cricketTeamService.assignPlayer(cricketPlayers, cricketTeam)) {
				System.out.println("assigned successfully");
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}
	
	public void assignCaptain() {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			int teamId = getChoice("enter team id");
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			System.out.println(cricketTeam);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			cricketPlayers.forEach(player -> System.out.println(player));
			int id = getChoice("enter player id from the this list");
			CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
			System.out.println(cricketPlayer);
			if (cricketTeamService.assignCaptain(cricketTeam, cricketPlayer)) {
				System.out.println("assigned successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}

	}
	
	public void assignWicketKeeper() {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			int teamId = getChoice("enter team id");
			CricketTeam cricketTeam = cricketTeamService.getTeamById(teamId);
			System.out.println(cricketTeam);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			System.out.println(cricketPlayers);
			int id = getChoice("enter player id from the this list");
			CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
			if (cricketTeamService.assignWicketKeeper(cricketTeam, cricketPlayer)) {
				System.out.println("assigned successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}

	}

}