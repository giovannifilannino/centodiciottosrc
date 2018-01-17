package com.gianni.webservice;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.gianni.dao.DAOUtenti;
import com.gianni.model.Utente;
import com.google.gson.JsonObject;

@Path("utenti")
public class GestioneUtenti {

	@GET
	@RolesAllowed("ADMIN")
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUtenti() {

		DAOUtenti daoUtenti = new DAOUtenti();
		List<Utente> utenti = daoUtenti.getUtenti();

		return Response.status(200).entity(utenti).build();

	}

	@POST
	@Path("/inserisciNuovoUtente")
	public Response inserisciUtente(Utente utente) {

		DAOUtenti dao = new DAOUtenti();
		JSONObject esito = new JSONObject();
		try {
			dao.insertisciUtente(utente);
			esito.put("esito", "OK");
			return Response.status(200).entity(esito.toString()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			esito.put("esito", "KO");
			return Response.status(400).entity(esito.toString()).build();
		}

		
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("login")
	public Response login(Utente u) {
		DAOUtenti dao = new DAOUtenti();
		JsonObject esito = new JsonObject();
		Utente user = dao.login(u.getNome(), u.getCognome());
		if (null != u && null != u.getNome() && null != u.getCognome() && null != user) {
			return Response.status(200).entity(user).build();
		} else {
			esito.addProperty("esito", "false");
			return Response.status(204).entity(esito.toString()).build();
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("getUtente")
	public Response getUtente(@QueryParam("idUtente") int idUtente) {
		DAOUtenti dao = new DAOUtenti();
		JsonObject esito = new JsonObject();
		Utente user = dao.getUtenteById(idUtente);
		if (null != user) {
			return Response.status(200).entity(user).build();
		} else {
			esito.addProperty("esito", "false");
			return Response.status(201).entity(esito.toString()).build();
		}

	}

}
