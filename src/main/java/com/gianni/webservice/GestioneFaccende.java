package com.gianni.webservice;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.gianni.dao.DAOFaccende;
import com.gianni.model.Faccenda;
import com.google.gson.JsonObject;

@Path("faccende")
public class GestioneFaccende {

	Logger log = Logger.getLogger(GestioneFaccende.class.getSimpleName());

	@GET
	@Path("getSettimana")
	public Response getSettimana() {
		DAOFaccende daoF = new DAOFaccende();
		JsonObject obj = new JsonObject();
		obj.addProperty("nrSettimana", daoF.valoreSettimana());
		return Response.status(200).entity(obj.toString()).build();
	}
	
	@GET
	@Path("getGiornoSettimana")
	public Response getGiornoSettimana() {
		DAOFaccende daoF = new DAOFaccende();
		JsonObject obj = new JsonObject();
		obj.addProperty("nrGiorno", daoF.valoreGiornoSettimana());
		return Response.status(200).entity(obj.toString()).build();
	}

	@GET
	@Path("faccendaCorrente")
	public Response getFaccendaCorrente(@QueryParam("idUtente") int idUtente) {
		DAOFaccende daoF = new DAOFaccende();
		int settimana = daoF.valoreSettimana();

		JsonObject jo = new JsonObject();

		Faccenda faccende = daoF.getFaccende(idUtente);
		if (null != faccende) {
			jo.addProperty("giornoDellaSettimana", faccende.getGiornoSettimana());

			if (settimana % 2 == 0) {
				jo.addProperty("faccendaDaFare", faccende.getPuliziaPari());
			} else {
				jo.addProperty("faccendaDaFare", faccende.getPuliziaDispari());
			}
		}

		return Response.status(200).entity(jo.toString()).build();
	}

}
