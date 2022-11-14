package com.playermanagement.util.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.playermanagement.model.BaseModel;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.model.CricketTeam;

public class HibernateConnection {

	private static HibernateConnection hibernateConnection = null;
	private static SessionFactory sessionFactory = null;

	private HibernateConnection() {
		sessionFactory = new Configuration().configure().addAnnotatedClass(BaseModel.class)
				.addAnnotatedClass(CricketPlayerStats.class).addAnnotatedClass(CricketTeam.class)
				.addAnnotatedClass(CricketPlayer.class).buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		if (hibernateConnection == null) {
			hibernateConnection = new HibernateConnection();
		}
		return sessionFactory;
	}
}
