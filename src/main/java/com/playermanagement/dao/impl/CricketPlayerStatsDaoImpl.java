package com.playermanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.playermanagement.dao.CricketPlayerStatsDao;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketPlayerStats;
import com.playermanagement.util.connection.HibernateConnection;
import com.playermanagement.util.exception.PlayerManagementException;

@Component
public class CricketPlayerStatsDaoImpl implements CricketPlayerStatsDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * {@inheritdoc}
	 *
	 * @return cricketPlayerStats.
	 */
	@Transactional
	public CricketPlayerStats insertStats(CricketPlayerStats cricketPlayerStats) throws PlayerManagementException {
		try {
			this.hibernateTemplate.save(cricketPlayerStats);
		}catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
		return cricketPlayerStats;
	}

	/**
	 * {@inheritdoc}
	 *
	 * @return cricketPlayer.
	 */
	public CricketPlayerStats displayStatsById(int statsId) throws PlayerManagementException {
		try {
			CricketPlayerStats cricketPlayerStats = this.hibernateTemplate.get(CricketPlayerStats.class, statsId);
			return cricketPlayerStats;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} 
	}

	/**
	 * {@inheritdoc}
	 *
	 * @return deleted message.
	 */
	public boolean deleteCricketPlayerStatsById(int id) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String qryString = "update CricketPlayerStats set deleted = 1 where id = :id ";
			Query query = session.createQuery(qryString);
			query.setParameter("id", id);
			int count = query.executeUpdate();
			tx.commit();
			return count == 1;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritdoc}
	 *
	 * @return updated message.
	 */
	public CricketPlayerStats updatePlayerStatsById(CricketPlayerStats cricketPlayerStats)
			throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(cricketPlayerStats);
			tx.commit();
			return cricketPlayerStats;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	public List<CricketPlayer> getCricketPlayers() throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<CricketPlayer> cricketPlayers = new ArrayList();
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(CricketPlayer.class);
			cricketPlayers = criteria.list();
			tx.commit();
			return cricketPlayers;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	public boolean assignPlayer(CricketPlayerStats cricketPlayerStats, CricketPlayer cricketPlayer)
			throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			cricketPlayerStats.setCricketPlayer(cricketPlayer);
			session.saveOrUpdate(cricketPlayerStats);
			tx.commit();
			return session.contains(cricketPlayerStats);
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

}
