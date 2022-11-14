package com.playermanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.playermanagement.util.constant.CricketPlayerConstants;
import com.playermanagement.controller.CricketPlayerController;
import com.playermanagement.controller.CricketTeamController;
import com.playermanagement.controller.CricketPlayerStatsController;

/**
 * <p>
 * The PlayerManagement simply shows all the main menu of program.
 * </p>
 *
 * @author -Lokesh
 */
public class PlayerManagement {

	private final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		PlayerManagement playerManagement = new PlayerManagement();
		playerManagement.mainMenu();
	}

	/**
	 * <p>
	 * This method is used to show the menu.
	 * </p>
	 */
	private void mainMenu() {
		CricketPlayerController cricketPlayerController = new CricketPlayerController();
		CricketTeamController cricketTeamController = new CricketTeamController();
		CricketPlayerStatsController cricketPlayerStatsController = new CricketPlayerStatsController();
		int choice = 0;
		do {
			try {
				choice = getChoice();
				switch (choice) {
				case 1:
					cricketPlayerController.showMenu();
					break;

				case 2:
					cricketTeamController.showMenu();
					break;

				case 3:
					cricketPlayerStatsController.showMenu();
					break;

				case 4:
					System.out.println("Thank you");
					break;

				default:
					System.out.println(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException inputMismatchException) {
				System.out.println(CricketPlayerConstants.INVALID);
				scanner.nextLine();
			}
		} while (4 != choice);
	}

	/**
	 * <p>
	 * This method is used to get integer input from user.
	 * </p>
	 */
	private int getChoice() {
		System.out.println("Enter your choice\n1.Cricket Players\n2.Teams\n3.stats\n4.Exit");
		int temporary = scanner.nextInt();
		scanner.nextLine();
		return temporary;
	}
}