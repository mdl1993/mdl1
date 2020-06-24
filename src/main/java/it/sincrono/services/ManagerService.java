package it.sincrono.services;

import java.util.List;

import it.sincrono.domain.Anagrafica;
import it.sincrono.domain.Provincia;
import it.sincrono.services.exceptions.ServiceException;

public interface ManagerService {
    public List<Anagrafica> getListaAnagrafica() throws ServiceException;
    public Anagrafica getAnagrafica(Integer id) throws ServiceException;
    public Integer insert(Anagrafica anagrafica) throws ServiceException;
    public void update(Anagrafica anagrafica) throws ServiceException;
    public void delete(Integer id) throws ServiceException;
public List<Provincia> caricaProvince() throws ServiceException;
}