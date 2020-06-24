package it.sincrono.controllers.beans.requests;

import it.sincrono.domain.Contratto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ContrattoRequest {
	
	protected Contratto contratto;

	public ContrattoRequest(Contratto contratto) {
		super();
		this.contratto = contratto;
	}
	
	

}
