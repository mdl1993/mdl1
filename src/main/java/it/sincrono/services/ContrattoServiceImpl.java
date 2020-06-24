package it.sincrono.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.sincrono.dao.ContrattoDao;
import it.sincrono.domain.Contratto;
import it.sincrono.domain.TipoContratto;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class ContrattoServiceImpl implements ContrattoService {

	@Autowired
	private ContrattoDao contrattoDao = null;

	@Override
	public List<Contratto> getListaContratti() throws ServiceException {

		List<Contratto> contratti = null;

		try {
			contratti = contrattoDao.getListaContratti();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contratti;
	}

	@Override
	public Contratto getContratto(Integer id) throws ServiceException {

		Contratto contratto = null;

		try {
			contratto = contrattoDao.getContratto(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contratto;
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public Integer insert(Contratto contratto) throws ServiceException {

		Integer id = null;

		try {
			id = contrattoDao.insert(contratto);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return id;
	}

	@Override
	public void update(Contratto contratto) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void delete(Integer id) throws ServiceException {

		try {
			contrattoDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<TipoContratto> caricaTipi() throws ServiceException {
		List<TipoContratto> tipi = null;

		try {
			tipi = contrattoDao.caricaTipi();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tipi;
	}

}
