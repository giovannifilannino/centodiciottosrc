package com.gianni.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gianni.model.Faccenda;

public class DAOFaccende {

	Logger log = Logger.getLogger(DAOFaccende.class.getSimpleName());

	private TimeZone italia;

	public DAOFaccende() {
		italia = TimeZone.getTimeZone("Europe/Rome");
	}

	public Faccenda getFaccende(int idUtente) {

		Faccenda fa = null;
		
		try(Session s = DBHibernateUtils.currentSession()){
			Query<Faccenda> query = s.createQuery("Select f from Faccenda f where f.idUtente = :idUtente", Faccenda.class);
			query.setParameter("idUtente", idUtente);
			fa = query.getSingleResult();
		}
		
		return fa;
	}

	/**
	 * Resistuisce il numero di settimana dell'anno corrente
	 * 
	 * @return
	 */
	public int valoreSettimana() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(italia);
		cal.setTime(new Date());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * Resistuisce il numero di settimana dell'anno corrente
	 * 
	 * @return
	 */
	public int valoreGiornoSettimana(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(italia);
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
	}

}
