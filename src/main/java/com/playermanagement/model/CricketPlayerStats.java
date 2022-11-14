package com.playermanagement.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.playermanagement.model.CricketPlayerStats;

@Entity
@Table(name = "stats")
public class CricketPlayerStats extends BaseModel {

	private int noOfMatch;
	private int totalRun;
	private double battingAverage;
	private double strikeRate;
	private int topScore;
	private int noOfwicket;
	private int noOfBallsFaced;

	@OneToOne
	@JoinColumn(name = "player_id")
	private CricketPlayer cricketPlayer;

	public CricketPlayerStats() {

	}

	public CricketPlayerStats(int noOfMatch, int totalRun, double battingAverage, int topScore, int noOfBallsFaced,
			double strikeRate) {

		this.noOfMatch = noOfMatch;
		this.totalRun = totalRun;
		this.battingAverage = battingAverage;
		this.topScore = topScore;
		this.noOfBallsFaced = noOfBallsFaced;
		this.strikeRate = strikeRate;
	}

	public int getNoOfMatch() {
		return noOfMatch;
	}

	public void setNoOfMatch(int noOfMatch) {
		this.noOfMatch = noOfMatch;
	}

	public int getTotalRun() {
		return totalRun;
	}

	public void setTotalRun(int totalRun) {
		this.totalRun = totalRun;
	}

	public double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(double strikeRate) {
		this.strikeRate = strikeRate;
	}

	public int getTopScore() {
		return topScore;
	}

	public void setTopScore(int topScore) {
		this.topScore = topScore;
	}

	public int getNoOfWicket() {
		return noOfwicket;
	}

	public void setNoOfWicket(int noOfWicket) {
		this.noOfwicket = noOfWicket;
	}

	public int getNoOfBallsFaced() {
		return noOfBallsFaced;
	}

	public void setNoOfBallsFaced(int noOfBallsFaced) {
		this.noOfBallsFaced = noOfBallsFaced;
	}

	public CricketPlayer getCricketPlayer() {
		return cricketPlayer;
	}

	public void setCricketPlayer(CricketPlayer cricketPlayer) {
		this.cricketPlayer = cricketPlayer;
	}

    public String toString() {
	return  "\n-------------Player stats------------"
                + "\nid               :"+ getId()
                + " \nNoOfMatch       :"+ noOfMatch 
                + " \nTotalRuns       :"+ totalRun
                + " \nTopScore        :"+ topScore
		        + " \nNoOfwickets     :"+ noOfwicket 
                + " \nAverage         :"+ battingAverage
                + " \nStrikeRate      :"+ strikeRate
                + " \nNoOfBallsFaced  :"+ noOfBallsFaced
                //+ " \nDelete          :"+ isDeleted()
                //+ " \nUpdated         :"+ getUpdatedAt()
                //+ " \nCreated         :"+ getCreatedAt()
                + " \nCricketPlayer  :"+ cricketPlayer
                + "\n-----------------------------------";
    }	
}
