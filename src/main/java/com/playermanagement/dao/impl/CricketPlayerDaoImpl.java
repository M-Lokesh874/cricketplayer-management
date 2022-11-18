package com.playermanagement.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.playermanagement.dao.CricketPlayerDao;
import com.playermanagement.model.CricketPlayer;
import com.playermanagement.model.CricketTeam;
import com.playermanagement.util.connection.HibernateConnection;
import com.playermanagement.util.exception.PlayerManagementException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class CricketPlayerDaoImpl implements CricketPlayerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/*
	 * @PersistenceContext private EntityManager entityManager;
	 */

	/**
	 * {@inheritDoc}
	 */

	@Transactional
	public CricketPlayer insertPlayer(CricketPlayer cricketPlayer) throws PlayerManagementException {
		try {
			this.hibernateTemplate.save(cricketPlayer);
			return cricketPlayer;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CricketPlayer> retrievePlayers() throws PlayerManagementException {
		try {
			List<CricketPlayer> cricketPlayers = hibernateTemplate.loadAll(CricketPlayer.class);
			return cricketPlayers;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public CricketPlayer retrievePlayerById(int id) throws PlayerManagementException {
		try {
			CricketPlayer cricketPlayer = this.hibernateTemplate.get(CricketPlayer.class, id);
			return cricketPlayer;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws PlayerManagementException
	 */
	@Transactional
	public boolean deletePlayerById(int id) throws PlayerManagementException {
		try {
			CricketPlayer cricketPlayer = this.hibernateTemplate.load(CricketPlayer.class, id);
			this.hibernateTemplate.delete(cricketPlayer);
			return true;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CricketPlayer updatePlayer(CricketPlayer cricketPlayer) throws PlayerManagementException {
		try {
			System.out.println(cricketPlayer);
			this.hibernateTemplate.saveOrUpdate(cricketPlayer);
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		}
		return cricketPlayer;
	}

	/**
	 * {@inheritDoc}
	 */
	public CricketPlayer retrievePlayerAndTeams(int playerId) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<CricketTeam> cricketTeams = new ArrayList<CricketTeam>();
		CricketPlayer cricketPlayer = new CricketPlayer();
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CricketPlayer.class, "id");
			criteria.add(Restrictions.eq("id", playerId));
			cricketPlayer = (CricketPlayer) criteria.uniqueResult();
			cricketTeams = session.createCriteria(CricketTeam.class).list();
			return cricketPlayer;
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCount() {
		long c = 0;
		int noOfRows;
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("select count(id) from CricketPlayer");
			c = (long) query.uniqueResult();
			session.close();
		} catch (HibernateException hibernateException) {
			System.out.println(hibernateException.getMessage());
		}
		noOfRows = (int) c;
		return noOfRows;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CricketPlayer> searchPlayerByName(String value) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		List<CricketPlayer> cricketPlayers = new ArrayList<>();
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CricketPlayer> criteriaQuery = criteriaBuilder.createQuery(CricketPlayer.class);
			Root<CricketPlayer> root = criteriaQuery.from(CricketPlayer.class);
			criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), '%' + value + '%'));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CricketPlayer> retrievePlayersBetweenDate(Date dateOne, Date dateTwo) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<CricketPlayer> cricketPlayers = new ArrayList<>();
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CricketPlayer> criteriaQuery = criteriaBuilder.createQuery(CricketPlayer.class);
			Root<CricketPlayer> root = criteriaQuery.from(CricketPlayer.class);
			criteriaQuery.select(root).where(criteriaBuilder.between(root.get("dateOfBirth"), dateOne, dateTwo));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean assignTeam(CricketPlayer cricketPlayer) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			System.out.println(cricketPlayer.getCricketTeam());
			session.saveOrUpdate(cricketPlayer);
			transaction.commit();
			return session.contains(cricketPlayer);
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public List<CricketPlayer> retrievePlayersByMultipleIds(List<Integer> playerIds) throws PlayerManagementException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		List<CricketPlayer> cricketPlayers = new ArrayList<>();
		System.out.println(playerIds);
		try {
			sessionFactory = HibernateConnection.getSessionFactory();
			session = sessionFactory.openSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<CricketPlayer> criteriaQuery = criteriaBuilder.createQuery(CricketPlayer.class);
			Root<CricketPlayer> root = criteriaQuery.from(CricketPlayer.class);
			criteriaQuery.select(root).where(root.get("id").in(playerIds),
					criteriaBuilder.equal(root.get("deleted"), false));
			return session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw new PlayerManagementException(hibernateException.getMessage());
		} finally {
			session.close();
		}
	}

}