package com.gianni.dao;

import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.gianni.model.Utente;

public class DAOUtenti {

	Logger log = Logger.getLogger(DAOUtenti.class.getSimpleName());


	public List<Utente> getUtenti() {
		Session s = DBHibernateUtils.currentSession();
		Query<Utente> query = s.createQuery("Select u from Utente u", Utente.class);
		List<Utente> utenti = query.getResultList();
		return utenti;
	}

	public void insertisciUtente(Utente u) {
		Transaction tx = null;
		log.info("Inizio salvataggio utente");
		try (Session s = DBHibernateUtils.currentSession()) {
			tx = s.getTransaction();
			tx.begin();
			s.save(u);
			tx.commit();
			log.info("Fine salvataggio utente");
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			log.severe("Non è stato possibile salvare il nuovo utente");
			log.severe(e.getMessage());
		}
	}

	public Utente getUtenteById(int id) {
		Utente u = null;
		try(Session s = DBHibernateUtils.currentSession()){
			u =s.find(Utente.class, id);
		}
		return u;
	}

	public Utente login(String nome, String cognome) {
		Utente u = null;
		try(Session s = DBHibernateUtils.currentSession()){
			Query<Utente> loginUtente = s.createQuery("Select u from Utente u where u.nome = :nome and u.cognome = :cognome", Utente.class);
			loginUtente.setParameter("nome", nome);
			loginUtente.setParameter("cognome", cognome);
			u = loginUtente.getSingleResult();
		}
		return u;
	}

}
