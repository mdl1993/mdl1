package it.sincrono.services;

import java.util.List;

import it.sincrono.domain.AccessoBadge;
import it.sincrono.services.exceptions.ServiceException;

public interface AccessoBadgeService {
	
	public List<AccessoBadge> getListaAccessi(AccessoBadge accessoBadge) throws ServiceException;

}
