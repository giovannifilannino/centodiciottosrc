package com.gianni.webservice;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

//import org.codehaus.commons.compiler.CompilerFactoryFactory;
//import org.codehaus.commons.compiler.IClassBodyEvaluator;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gianni.dao.DBHibernateUtils;
import com.gianni.model.Utente;
import com.gianni.rest.JWTTokenNeeded;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("testWS")
public class TestWebService {

	Logger log = Logger.getLogger(TestWebService.class.getSimpleName());

	@Inject
	private KeyGenerator keyGenerator;

	@Context
	private UriInfo uriInfo;

	@GET
	public Response echo(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "no message" : message).build();
	}

	@GET
	@Path("jwt")
	@JWTTokenNeeded
	public Response echoWithJWTToken(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "no message" : message).build();
	}

	@POST
	@Path("/login/{:user}")
	public Response authenticateUser(@PathParam("user") String user) {
		try {
			// Issue a token for the user
			String token = issueToken(user);
			// Return the token on the response
			return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
		} catch (Exception e) {
			return Response.status(UNAUTHORIZED).build();
		}
	}

	private String issueToken(String login) {
		Key key = keyGenerator.generateKey();
		String jwtToken = Jwts.builder().setSubject(login).setIssuer(uriInfo.getAbsolutePath().toString())
				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return jwtToken;
	}

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

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
