package com.gianni.webservice;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.codehaus.commons.compiler.CompilerFactoryFactory;
//import org.codehaus.commons.compiler.IClassBodyEvaluator;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gianni.dao.DBHibernateUtils;
import com.gianni.model.Utente;

@Path("testWS")
public class TestWebService {

	Logger log = Logger.getLogger(TestWebService.class.getSimpleName());


	// @POST
	// @Consumes("application/json")
	// @Produces("application/json")
	// public Response executeOnlineCode(ClasseCompiled code) throws Exception {
	//
	//
	// IClassBodyEvaluator cbe =
	// CompilerFactoryFactory.getDefaultCompilerFactory().newClassBodyEvaluator();
	// cbe.cook(code.getCode());
	// Class<?> c = cbe.getClazz();
	// Object o = c.newInstance();
	//
	// Method[] methods = c.getMethods();
	//
	// JSONObject obj = new JSONObject();
	//
	// for(Method method : methods){
	// if(code.getMethodName().equals(method.getName())){
	//
	// if("String".compareToIgnoreCase(code.getReturnType())==0){
	// String risultato = (String) method.invoke(o);
	// obj.put("result", risultato);
	// }
	//
	// if("int".compareToIgnoreCase(code.getReturnType()) == 0){
	// int risultato = (int) method.invoke(o);
	// obj.put("result", risultato);
	// }
	// }
	// }
	//
	//
	//
	// o = null;
	//
	// return Response.ok().entity(obj.toString()).build();
	// }

	@GET
	@Path("xul")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Utente> getTest() {
		Session s = DBHibernateUtils.currentSession();
		Query<Utente> q = s.createQuery("Select p from Utente p", Utente.class);

		return q.list();
	}

	@GET
	@Path("xel")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getTesto() {

		StringBuilder test = new StringBuilder();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://mysql:3306/centodiciottodb", "gianni", "gianni");

			Statement s = c.createStatement();

			ResultSet rs = s.executeQuery("Select * from utente");

			while (rs.next()) {
				test.append("Nome: " + rs.getString(1));
				test.append(" Cognome: " + rs.getString(2));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.severe(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.severe(e.getMessage());
		}

		return test.toString();
	}

}
