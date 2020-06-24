package it.sincrono.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.sincrono.dao.AccessoBadgeDao;
import it.sincrono.domain.AccessoBadge;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class AccessoBadgeServiceImpl implements AccessoBadgeService {
	
	@Autowired
	private AccessoBadgeDao accessoBadgeDao = null;

	@Override
	public List<AccessoBadge> getListaAccessi(AccessoBadge accessoBadge) throws ServiceException {
		List<AccessoBadge> listaAccessi= null;
		
		
		try {
			listaAccessi = accessoBadgeDao.getListaAccessi(accessoBadge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaAccessi;
	}
	
	
	

}
