package com.playermanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.playermanagement.dao.CricketTeamDao;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.util.connection.HibernateConnection;
import com.playermanagement.util.exception.PlayerManagementException;

public class CricketTeamDaoImpl implements CricketTeamDao {

	/**
	 * {@inheritDoc}
	 *
	 */
	public CricketTeam insertTeam(CricketTeam cricketTeam) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null; 
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(cricketTeam);
			transaction.commit();
			return cricketTeam;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	public List<CricketTeam> retrieveTeams() {
		List<CricketTeam> cricketTeams = new ArrayList<>();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CricketTeam.class);
			cricketTeams = (List<CricketTeam>) criteria.list();
		} catch (HibernateException hibernateException) {
			System.out.println(hibernateException);
		} finally {
			session.close();
		}
		return cricketTeams;

	}

	/**
	 * {@inheritDoc}
	 *
	 */
	public CricketTeam getTeamById(int id) throws PlayerManagementException {
		CricketTeam cricketTeam = new CricketTeam();
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CricketTeam.class, "id");
			criteria.add(Restrictions.eq("id", id));
			cricketTeam = (CricketTeam) criteria.uniqueResult();
			return cricketTeam;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public CricketTeam retrieveTeamAndPlayers(CricketTeam cricketTeam, int teamId) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<CricketPlayer> cricketPlayers = new ArrayList();
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			cricketTeam = session.get(CricketTeam.class, teamId);
			cricketPlayers = session.createCriteria(CricketPlayer.class).list();
			cricketTeam.setCricketPlayer(cricketPlayers);
			return cricketTeam;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());

		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteCricketTeamById(int id) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String qryString = "update CricketTeam set deleted = 1 where id = :id ";
			Query query = session.createQuery(qryString);
			query.setParameter("id", id);
			int count = query.executeUpdate();
			transaction.commit();
			return count == 1;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CricketTeam updateTeamById(CricketTeam cricketTeam) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(cricketTeam);
			transaction.commit();
			return cricketTeam;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean assignPlayer(CricketTeam cricketTeam) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(cricketTeam);
			transaction.commit();
			return session.contains(cricketTeam);
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}
	
	@Override
	public boolean assignCaptain(CricketTeam cricketTeam) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(cricketTeam);
			transaction.commit();
			return session.contains(cricketTeam);
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public boolean assignWicketKeeper(CricketTeam cricketTeam) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(cricketTeam);
			transaction.commit();
			return session.contains(cricketTeam);
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}
}