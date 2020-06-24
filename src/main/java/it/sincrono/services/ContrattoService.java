package it.sincrono.services;

import java.util.List;

import it.sincrono.domain.Contratto;
import it.sincrono.domain.TipoContratto;
import it.sincrono.services.exceptions.ServiceException;

public interface ContrattoService {

	public List<Contratto> getListaContratti() throws ServiceException;

	public Contratto getContratto(Integer id) throws ServiceException;

	public Integer insert(Contratto contratto) throws ServiceException;

	public void update(Contratto contratto) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

	public List<TipoContratto> caricaTipi() throws ServiceException;

}
