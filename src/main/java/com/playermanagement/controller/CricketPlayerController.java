package com.playermanagement.controller;

import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.service.CricketPlayerService;
import com.playermanagement.service.CricketTeamService;
import com.playermanagement.service.impl.CricketPlayerServiceImpl;
import com.playermanagement.service.impl.CricketTeamServiceImpl;
import com.playermanagement.util.CricketPlayerValidation;
import com.playermanagement.util.DateUtil;
import com.playermanagement.util.PlayerManagementLogger;
import com.playermanagement.util.constant.CricketPlayerConstants;
import com.playermanagement.util.constant.Gender;
import com.playermanagement.util.exception.PlayerManagementException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * The CricketPlayerController program simply create, display, delete, update
 * and search the cricket player.
 * </p>
 *
 * @author Lokesh
 */
public class CricketPlayerController {

	private final Scanner scanner = new Scanner(System.in);
	public CricketPlayerService cricketPlayerService = new CricketPlayerServiceImpl();

	/**
	 * <p>
	 * This method is used to show the menu.
	 * </p>
	 */
	public void showMenu() {

		int choice = 0;
		do {
			try {
				choice = getChoice(CricketPlayerConstants.MENU);
				switch (choice) {
				case CricketPlayerConstants.CREATE_PLAYER:
					 createPlayer();
					 break;

				case CricketPlayerConstants.DISPLAY_PLAYERS:
					 displayPlayers();
					 break;

				case CricketPlayerConstants.GET_PLAYER:
					 getPlayer();
					 break;

				case CricketPlayerConstants.UPDATE_PLAYER:
					 updatePlayer();
					 break;

				case CricketPlayerConstants.DELETE_PLAYER:
					 deletePlayer();
					 break;

				case CricketPlayerConstants.SEARCH_PLAYER:
					 searchPlayer();
					 break;

				case CricketPlayerConstants.GET_DETAILS_BETWEEN_IDS:
					 displayPlayersBetweenDate();
					 break;

				case 8:
					 displayPlayersByMultipleIds();
					 break;

				case 9:
					 assignTeam();
					 break;

				case 10:
					 displayExistingPlayer(getChoice("Enter player id"));
					 break;

				case 11:
					 break;

				default:
					PlayerManagementLogger.error(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException ex) {
				PlayerManagementLogger.error(CricketPlayerConstants.INVALID);
				scanner.nextLine();
			}
		} while (11 != choice);
	}

	/**
	 * <p>
	 * This method is used to create a cricket player.
	 * </p>
	 */
	private void createPlayer() {
		try {
			String name = getName();
			Date dateOfBirth = getDateOfBirth("Enter your date of birth in this format dd-mm-yyyy");
			String country = getCountry();
			Gender gender = getGender();
			String gmail = getEmail();

			CricketPlayer playerDetails = cricketPlayerService.createPlayer(name, country, gender, dateOfBirth, gmail);
			System.out.println("--------------------------------------");
			System.out.println(playerDetails);
			System.out.println(CricketPlayerConstants.CREATED);
			System.out.println("--------------------------------------");
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid name of the cricket player.
	 * </p>
	 *
	 * @return the validated name
	 */
	private String getName() {
		while (true) {
			try {
				String name = getString(CricketPlayerConstants.NAME);
				if (CricketPlayerValidation.isValidName(name)) {
					return name;
				} else {
					PlayerManagementLogger.info(CricketPlayerConstants.INVALID_NAME);
				}
			} catch (InputMismatchException inputMismatchException) {
				PlayerManagementLogger.error(inputMismatchException);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid date of birth of the cricket player.
	 * 
	 * @return dateBirth if there is no exception
	 *         </p>
	 */
	private Date getDateOfBirth(String value) {
		Date currentDate = new Date();
		int age;
		Date date = null;
		String dateOfBirth;
		boolean check = false;
		do {
			try {
				dateOfBirth = getString(value);
				check = DateUtil.isValidDate(dateOfBirth);
				if (check) {
					date = DateUtil.convertStringToDate(dateOfBirth);
					age = DateUtil.getAge(date, currentDate);
					if ((age >= 0 && age < 18) || age > 40) {
						throw new PlayerManagementException(
								"\nYour age must be between 18 years and 40 years to play this tournament");
					} else if (age < 0) {
						throw new PlayerManagementException("\nYour age must be before current date");
					}
				} else {
					PlayerManagementLogger.info(CricketPlayerConstants.INVALID);
				}
			} catch (PlayerManagementException playerManagementException) {
				PlayerManagementLogger.error(playerManagementException);
				return getDateOfBirth(value);
			}
		} while (!check);
		return date;
	}

	/**
	 * <p>
	 * This method is used to get the valid country name of the cricket player.
	 * </p>
	 *
	 * @return the validated country name
	 */
	private String getCountry() {
		while (true) {
			try {
				String country = getString(CricketPlayerConstants.COUNTRY_NAME);
				if (CricketPlayerValidation.isValidCountryName(country)) {
					return country;
				} else {
					PlayerManagementLogger.info(CricketPlayerConstants.INVALID_COUNTRY_NAME);
				}
			} catch (InputMismatchException inputMismatchException) {
				PlayerManagementLogger.error(inputMismatchException);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get the gender of the player.
	 * </p>
	 *
	 * @return the validated gender.
	 */
	private Gender getGender() {
		while (true) {
			try {
				int userChoice = getChoice("Enter your gender :\n1.Female\n2.Male\n3.Transgender");
				Gender gender = Gender.getGender(userChoice);
				if (null != gender) {
					return gender;
				} else {
					PlayerManagementLogger.info(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException inputMismatchException) {
				PlayerManagementLogger.info(inputMismatchException, CricketPlayerConstants.INVALID);
				scanner.nextLine();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid gmail of the player.
	 * </p>
	 *
	 * @return the validated email.
	 */
	private String getEmail() {
		while (true) {
			try {
				String email = getString("Enter your email");
				if (CricketPlayerValidation.isValidEmail(email)) {
					return email;
				} else {
					PlayerManagementLogger.info(CricketPlayerConstants.INVALID);
				}
			} catch (InputMismatchException inputMismatchException) {
				PlayerManagementLogger.info(inputMismatchException, CricketPlayerConstants.INVALID);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to display all player details.
	 * </p>
	 */
	private void displayPlayers() {
		try {
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getCricketPlayers();
			cricketPlayers.forEach(player -> System.out.println(player));
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.info(CricketPlayerConstants.MESSAGE);
		}
	}

	/**
	 * <p>
	 * This method is used to get the cricket player using their id.
	 * </p>
	 */
	private void getPlayer() {
		try {
			int id = getChoice("Enter player id to get their details");
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(id);
			if (null != cricketPlayer) {
				System.out.println(cricketPlayer);
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(CricketPlayerConstants.MESSAGE);
		}
	}

	/**
	 * <p>
	 * This method is used to delete the player using their id.
	 * </p>
	 */
	private void deletePlayer() {
		try {
			int id = getChoice("Enter player id to delete");
			if (cricketPlayerService.deletePlayerById(id)) {
				System.out.println(CricketPlayerConstants.DELETED);
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to update the player.
	 * </p>
	 */
	private void searchPlayer() {
		try {
			String name = getString("Enter player name to search :");
			List<CricketPlayer> cricketPlayers = cricketPlayerService.searchPlayerByName(name);
			if (!(cricketPlayers.isEmpty())) {
				cricketPlayers.forEach(crickPlayers -> System.out.println(crickPlayers));
			} else {
				System.out.println(CricketPlayerConstants.NOT_FOUND);
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
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
	 * This method is used to get player and their teams.
	 * </p>
	 */
	public void displayExistingPlayer(int playerId) {
		try {
			CricketPlayer cricketPlayer = cricketPlayerService.displayExistingPlayer(playerId);
			System.out.println(cricketPlayer);
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to get a group of players.
	 * </p>
	 */
	public void displayPlayersByGivenIds() {
		try {
			StringBuffer playerIds = new StringBuffer();
			int noOfIds = getChoice("Enter how many records you want to fetch");
			for (int noOfId = 0; noOfId < noOfIds; noOfId++) {
				playerIds.append(getChoice("Enter id no "));
				playerIds.append(",");
			}
			playerIds.delete(playerIds.length() - 1, playerIds.length());
			List<CricketPlayer> cricketPlayers = cricketPlayerService.getPlayersGivenIds(playerIds);
			if (null == cricketPlayers) {
				System.out.println("Invalid input");
			} else {
				cricketPlayers.forEach(players -> System.out.println(players));
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to get the valid date of joining of the cricket player.
	 * </p>
	 * 
	 */
	private void displayPlayersBetweenDate() {
		try {
			Date dateOne = getDateOfBirth("Enter your 1 date of joining date");
			Date dateTwo = getDateOfBirth("Enter your 2 date of joining date");
			List<CricketPlayer> cricketPlayers = cricketPlayerService.retrievePlayersBetweenDate(dateOne, dateTwo);
			cricketPlayers.forEach(players -> System.out.println(players));
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(CricketPlayerConstants.INVALID);
		}

	}

	public void assignTeam() {
		try {
			List<CricketTeam> cricketTeams = new ArrayList<CricketTeam>();
			CricketTeamService cricketTeamService = new CricketTeamServiceImpl();

			int playerId = getChoice("Enter player id to get their details");
			CricketPlayer cricketPlayer = cricketPlayerService.getPlayerById(playerId);
			System.out.println(cricketPlayer);

			cricketTeams = cricketTeamService.getTeams();
			cricketTeams.forEach(team -> System.out.println(team));

			System.out.println("enter team id to be a part of it");
			int teamId = scanner.nextInt();
			CricketTeam cricketTeam = cricketTeams.get(teamId - 1);
			cricketTeams.clear();
			cricketTeams.add(cricketTeam);

			if (cricketPlayerService.assignTeam(cricketPlayer, cricketTeams)) {
				System.out.println("assigned successfully");
			}
		} catch (PlayerManagementException playerManagementException) {
			PlayerManagementLogger.error(playerManagementException);
		}
	}

	/**
	 * <p>
	 * This method is used to update the player.
	 * </p>
	 */
	private void updatePlayer() {
		boolean updateMenu = true;
		int playerId;
		int choice;
		while(updateMenu) {
			try {
				playerId = getChoice(CricketPlayerConstants.ENTER_ID_TO_UPDATE);
				if (null != cricketPlayerService.getPlayerById(playerId)) {
					System.out.println(CricketPlayerConstants.MENU_FOR_UPDATE);
					choice = getChoice(CricketPlayerConstants.CHOICE);
					switch (choice) {
						case 1:
							cricketPlayerService.updatePlayerById(playerId, getName(), choice);
							System.out.println(CricketPlayerConstants.UPDATED);
							break;

						case 2:
							Date dateOfBirth = getDateOfBirth("");
							String dob = DateUtil.convertDateToString(dateOfBirth);
							cricketPlayerService.updatePlayerById(playerId, dob, choice);
							System.out.println(CricketPlayerConstants.UPDATED);
							break;

						case 3:
							cricketPlayerService.updatePlayerById(playerId, getCountry(), choice);
							System.out.println(CricketPlayerConstants.UPDATED);
							break;

						case 4:
							cricketPlayerService.updatePlayerById(playerId, getEmail(), choice);
							System.out.println(CricketPlayerConstants.UPDATED);
							break;
						case 5:
							cricketPlayerService.updatePlayerById(playerId, getGender().toString(), choice);
							System.out.println(CricketPlayerConstants.UPDATED);
							break;

						case 6:
							updateMenu = false;
							break;
						default:
							throw new PlayerManagementException(CricketPlayerConstants.INVALID);
					}
					if(true) {
						break;
					}
				} else {
					System.out.println(CricketPlayerConstants.NOT_FOUND);
					break;
				}
			} catch (PlayerManagementException invalidInputException) {
				System.out.println(CricketPlayerConstants.INVALID);
			} catch (InputMismatchException invalidInputException) {
				System.out.println(CricketPlayerConstants.INVALID);
				scanner.nextLine();
			}
		}
	}

	public void displayPlayersByMultipleIds() {
		List<CricketPlayer> cricketPlayers = null;
		try {
			List<Integer> playerIds = new ArrayList<Integer>();
			int noOfIds = getChoice("enter no of player you want");
			for (int id = 0; id < noOfIds; id++) {
				playerIds.add(getChoice("enter id no " + (id+1)));
			}
		    cricketPlayers = cricketPlayerService.getPlayersByMultipleIds(playerIds);
			cricketPlayers.forEach(players-> System.out.println(players));
		} catch (PlayerManagementException playerManagementException) {
			System.out.println(playerManagementException);
		}
	}
}