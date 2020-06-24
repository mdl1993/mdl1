package it.sincrono.controllers.beans.requests;

import it.sincrono.domain.Anagrafica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class AnagraficaRequest {
	
	protected Anagrafica anagrafica;

	public AnagraficaRequest(Anagrafica anagrafica) {
		super();
		this.anagrafica = anagrafica;
	}
	
	

}
