package it.sincrono.controllers.beans.requests;

import it.sincrono.domain.AccessoBadge;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class AccessoBadgeRequest extends GenericRequest{

	protected AccessoBadge accessoBadge;

	public AccessoBadgeRequest(AccessoBadge accessoBadge) {
		super();
		this.accessoBadge = accessoBadge;
	}


}
