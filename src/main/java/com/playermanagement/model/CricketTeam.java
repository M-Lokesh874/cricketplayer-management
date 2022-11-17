package com.playermanagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "teams")
public class CricketTeam extends BaseModel {

	private String name;
	@OneToOne
	private CricketPlayer captain;
	@OneToOne
	private CricketPlayer wicketKeeper;
	private int totalMatch;
	private int won;
	private int lost;

	@ManyToMany(mappedBy = "cricketTeams")
	private List<CricketPlayer> cricketPlayers;

	public CricketTeam() {

	}

	public CricketTeam(String name, int totalMatch, int won, int lost) {

		this.name = name;
		this.totalMatch = totalMatch;
		this.won = won;
		this.lost = lost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CricketPlayer getCaptain() {
		return captain;
	}

	public void setCaptain(CricketPlayer captain) {
		this.captain = captain;
	}

	public CricketPlayer getWicketKeeper() {
		return wicketKeeper;
	}

	public void setWicketKeeper(CricketPlayer wicketKeeper) {
		this.wicketKeeper = wicketKeeper;
	}

	public int getTotalMatch() {
		return totalMatch;
	}

	public void setTotalMatch(int totalMatch) {
		this.totalMatch = totalMatch;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public List<CricketPlayer> getCricketPlayer() {
		return cricketPlayers;
	}

	public void setCricketPlayer(List<CricketPlayer> cricketPlayers) {
		this.cricketPlayers = cricketPlayers;
	} 

    public String toString() {
	    return "\n---------Cricket Team-----------"
               + "\nid             :" +getId()
               + "\nName           :" + name 
               + "\nTotal Match    :" + totalMatch
               + "\nWon            :" + won
               + "\nLost           :" + lost
               + "\nCaptain        :" + captain
               + "\nWicketKeeper   :" + wicketKeeper
               +"\n-------------------------------";
    }   
}
