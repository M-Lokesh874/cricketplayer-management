package com.playermanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import com.playermanagement.util.constant.Gender;
import com.playermanagement.util.DateUtil;

/**
 * <p>
 * CricketPlayer program contains the detail of the cricket players.
 * </p>
 */
@Entity
@Table(name = "players")
@SQLDelete(sql = "UPDATE players SET is_deleted = 1 WHERE id = ?",check=ResultCheckStyle.COUNT)
public class CricketPlayer extends BaseModel {

	private String playerCode;
	private String name;
	private String country;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private String email;

	@ManyToMany
	@JoinTable(name = "playersandteams", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
	private List<CricketTeam> cricketTeams;

	public CricketPlayer(String name, String country, Gender gender, Date dateOfBirth, String email) {
		this.name = name;
		this.country = country;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public CricketPlayer() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlayerCode() {
		return playerCode;
	}

	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge(Date dateOfBirth) {
		return DateUtil.getAge(dateOfBirth, DateUtil.currentDate);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CricketTeam> getCricketTeam() {
		return cricketTeams;
	}

	public void setCricketTeam(List<CricketTeam> cricketTeams) {
		this.cricketTeams = cricketTeams;
	}
    
    @Override
    public String toString() {
        return  "\n-------Player details----------"
                + "\nId          :" + getId()
                + "\nPlayer code :" + playerCode
                + "\nName        :" + name  
                + "\nCountry     :" + country 
                + "\nGender      :" + gender
                + "\nDateOfBirth :" + dateOfBirth
                + "\nAge         :" + getAge(dateOfBirth)
                + "\nEmail       :" + email
                +"\n-------------------------------";
    }
}