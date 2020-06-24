package it.sincrono.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.sincrono.dao.ManagerDao;
import it.sincrono.domain.Anagrafica;
import it.sincrono.domain.Provincia;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class ManagerServiceImpl implements ManagerService
{
    @Autowired
	private ManagerDao managerDao = null;

    @Override
	public List<Anagrafica> getListaAnagrafica() throws ServiceException {
		List<Anagrafica> anagrafiche = null;

		try {
			anagrafiche = managerDao.getListaAnagrafica();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return anagrafiche;
	}

	@Override
	public Anagrafica getAnagrafica(Integer id) throws ServiceException {
		Anagrafica anagrafica = null;

		try {
			anagrafica = managerDao.getAnagrafica(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return anagrafica;
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public Integer insert(Anagrafica anagrafica) throws ServiceException {
		Integer id = null;

		try {
			id = managerDao.insert(anagrafica);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return id;
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void update(Anagrafica anagrafica) throws ServiceException {

		try {
			managerDao.update(anagrafica);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void delete(Integer id) throws ServiceException {

		try {
			managerDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Provincia> caricaProvince() throws ServiceException {
		List<Provincia> province = null;

		try {
			province = managerDao.caricaProvince();
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return province;
	}
}