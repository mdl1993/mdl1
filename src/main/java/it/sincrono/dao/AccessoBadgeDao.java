package it.sincrono.dao;

import java.util.List;

import it.sincrono.domain.AccessoBadge;

public interface AccessoBadgeDao {
	
	public List<AccessoBadge> getListaAccessi(AccessoBadge accessoBadge) throws Exception;
	public AccessoBadge getAccessoBadge(Integer idAccesso) throws Exception;
	public Integer insert(AccessoBadge accessoBadge) throws Exception;
    public void update(AccessoBadge accessoBadge) throws Exception;
    public void delete(Integer id) throws Exception;
	
	
}
