package it.sincrono.controllers.beans.responses;

import java.util.List;
import it.sincrono.domain.AccessoBadge;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BadgeListResponse extends GenericResponse{
	
	protected List<AccessoBadge> listaAccessi = null;
	
	

}
