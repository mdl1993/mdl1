package it.sincrono.dao;

import java.util.List;


import it.sincrono.domain.*;

public interface ContrattoDao {
	
	public List<Contratto> getListaContratti() throws Exception;

	public Contratto getContratto(Integer id) throws Exception;

	public Integer insert(Contratto contratto) throws Exception;

	public void update(Contratto contratto) throws Exception;

	public void delete(Integer id) throws Exception;
	
	public List<TipoContratto> caricaTipi() throws Exception;

}
