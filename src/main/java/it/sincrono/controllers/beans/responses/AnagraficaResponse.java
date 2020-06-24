package it.sincrono.controllers.beans.responses;

import it.sincrono.controllers.beans.a.Esito;
import it.sincrono.domain.Anagrafica;

public class AnagraficaResponse {

	protected Esito esito;
	protected Anagrafica anagrafica;

	public AnagraficaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnagraficaResponse(Esito esito, Anagrafica anagrafica) {
		super();
		this.esito = esito;
		this.anagrafica = anagrafica;
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

}
