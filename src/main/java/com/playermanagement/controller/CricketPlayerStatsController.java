package com.playermanagement.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketPlayerStatsService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketPlayerStatsServiceImpl;
import com.playermanagement.util.CricketPlayerValidation;
import com.playermanagement.util.PlayerManagementLogger;
import com.playermanagement.util.constant.CricketPlayerConstants;
import com.playermanagement.util.exception.PlayerManagementException;

/**
 * <p>
 * The CricketPlayerStatsController program simply create, display, and delete
 * the cricket player statistics.
 * </p>
 *
 * @author Lokesh
 */
public class CricketPlayerStatsController {

	private Scanner scanner = new Scanner(System.in);
	public CricketPlayerStatsService cricketPlayerStatsService = new CricketPlayerStatsServiceImpl();

	/**
	 * <p>
	 * This method shows the menu of cricket player statistics.
	 * </p>
	 */
	public void showMenu() {

		int choice = 0;
		do {
			try {
				choice = getChoice(
						"1.Create stats\n2.display stats\n3.delete stats\n4.update stats\n5.assign player to stats\n6.Exit");
				switch (choice) {
				case 1:
					createStats();
					break;

				case 2:
					displayStats(getChoice("Enter stats id"));
					break;

				case 3:
					deleteStats();
					break;

				case 4:
					//updateStats();
					break;

				case 5:
					assignPlayer();
					break;
				case 6:
					break;

				default:
					System.out.println(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException inputMismatchException) {
				PlayerManagementLogger.error("Invalid");
				scanner.nextLine();
			}
		} while (6 != choice);
	}

	/**
	 * <p>
	 * This method is used to delete cricket player statistics.
	 * </p>
	 */
	private void deleteStats() {
		try {
			int id = getChoice("Enter stats id to delete");
			if (cricketPlayerStatsService.deleteCricketPlayerStatsById(id)) {
				System.out.println(CricketPlayerConstants.DELETED);
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(CricketPlayerConstants.MESSAGE);
		}
	}

	/**
	 * <p>
	 * This method is used to display cricket player and their statistics.
	 * </p>
	 */
	private void displayStats(int statsId) {
		try {
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(statsId);
			System.out.println(cricketPlayerStats);
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(CricketPlayerConstants.MESSAGE);
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
	 * This method is used to continue the process if user press 1.
	 * </p>
	 */
	private boolean canContinue() {
		try {
			return 1 == scanner.nextInt();
		} catch (InputMismatchException inputMismatchException) {
			System.out.println("Wrong input enter valid input");
			scanner.nextLine();
			return canContinue();
		}
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
	 * This method is used to create cricket player statistics.
	 * </p>
	 */
	private void createStats() {
		try {
			int noOfMatch = getCricketPlayerStats("enter no of match played");
			int totalRun = getCricketPlayerStats("Enter total run");
			double battingAverage = getBattingAverage(noOfMatch, totalRun);
			int topScore = getCricketPlayerStats("Enter top score");
			int noOfWickets = getCricketPlayerStats("Enter total wickets you have taken");
			int noOfBallsFaced = getCricketPlayerStats("Enter no of balls you faced");
			double strikeRate = getStrikeRate(totalRun, noOfBallsFaced);
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.createStats(noOfMatch, totalRun,
					battingAverage, topScore, noOfBallsFaced, strikeRate);
			System.out.println(cricketPlayerStats);
		} catch (PlayerManagementException playerManagementException) {
			System.out.println(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid cricket player statistics from the user.
	 * </p>
	 *
	 * @return valid stats.
	 */
	public int getCricketPlayerStats(String message) {
		System.out.println(message);
		while (true) {
			try {
				int playerStats = getInteger();
				if (CricketPlayerValidation.isValidNumber(playerStats)) {
					return playerStats;
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
	 * This method is used to calculate the batting average of the cricket player.
	 * </p>
	 *
	 * @return valid battingAverage.
	 */
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

	/**
	 * <p>
	 * This method is used to calculate the strike rate of the cricket player.
	 * </p>
	 *
	 * @return valid strike rate.
	 */
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

	/**
	 * <p>
	 * This method is used to get Integer input from user.
	 * </p>
	 */
	private int getInteger() {
		return scanner.nextInt();
	}

	/**
	 * <p>
	 * This method is used to update the stats of the cricket player.
	 * </p>
	 */
	/*public void updateStats() {
		boolean check = true;
		while (check) {
			try {
				int statsId = getChoice("Enter your stats id");
				CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(statsId);
				if (null != cricketPlayerStats) {
					System.out.println(
							"\n1.noOfMatch\n2.totalRun\n3.battingAverage\n4.topScore\n5.noOfWickets\n6.strikeRate\n7.noOfBallsFaced\n8.exit");
					int choice = getChoice(CricketPlayerConstants.CHOICE);
					switch (choice) {
					case 1:
						cricketPlayerStatsService.updatePlayerStatsById(statsId,
								getCricketPlayerStats("enter no of matches"), choice);
						System.out.println("updated successfully");
						break;

					case 2:
						cricketPlayerStatsService.updatePlayerStatsById(statsId,
								getCricketPlayerStats("enter total run"), choice);
						System.out.println("updated successfully");
						break;

					case 3:
						int noOfMatch = getCricketPlayerStats("enter no of match played");
						int totalRun = getCricketPlayerStats("Enter total run");
						double battingAverage = getBattingAverage(noOfMatch, totalRun);
						cricketPlayerStatsService.updatePlayerStatsById(statsId, battingAverage, choice);
						System.out.println("updated successfully");
						break;

					case 4:
						cricketPlayerStatsService.updatePlayerStatsById(statsId,
								getCricketPlayerStats("enter top score"), choice);
						System.out.println("updated successfully");
						break;

					case 5:
						cricketPlayerStatsService.updatePlayerStatsById(statsId,
								getCricketPlayerStats("enter no of wickets"), choice);
						System.out.println("updated successfully");
						break;

					case 6:
						int noOfBallsFaced = getCricketPlayerStats("Enter no of balls you faced");
						int totalRuns = getCricketPlayerStats("Enter total run");
						double strikeRate = getStrikeRate(totalRuns, noOfBallsFaced);
						cricketPlayerStatsService.updatePlayerStatsById(statsId, strikeRate, choice);
						System.out.println("updated successfully");
						break;

					case 7:
						cricketPlayerStatsService.updatePlayerStatsById(statsId,
								getCricketPlayerStats("enter no of balls faced"), choice);
						System.out.println("updated successfully");
						break;

					case 8:
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
	}*/

	public void assignPlayer() {
		CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();
		try {
			int statsId = getChoice("enter stats id");
			CricketPlayerStats cricketPlayerStats = cricketPlayerStatsService.displayStatsById(statsId);
			System.out.println(cricketPlayerStats);
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			System.out.println(cricketPlayers);
			int id = getChoice("enter player id from the this list");
			CricketPlayer cricketPlayer = cricketPlayers.get(id-1);
			if (cricketPlayerStatsService.assignPlayer(cricketPlayerStats, cricketPlayer)) {
				System.out.println("assigned successfully");
			}
		} catch (PlayerManagementException e) {
			e.printStackTrace();
		}

	}
}
