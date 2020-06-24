package it.sincrono.dao;

import java.util.List;

import it.sincrono.domain.*;

public interface ManagerDao {
    public List<Anagrafica> getListaAnagrafica() throws Exception;
    public Anagrafica getAnagrafica(Integer id) throws Exception;
    public Integer insert(Anagrafica anagrafica) throws Exception;
    public void update(Anagrafica anagrafica) throws Exception;
    public void delete(Integer id) throws Exception;
    public List<Provincia> caricaProvince() throws Exception;
}