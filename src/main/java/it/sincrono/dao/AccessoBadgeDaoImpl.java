package it.sincrono.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import it.sincrono.domain.AccessoBadge;
import it.sincrono.domain.Anagrafica;
import it.sincrono.domain.Provincia;

@Repository
public class AccessoBadgeDaoImpl implements AccessoBadgeDao {
	
	@Autowired
	DataSource dataSource = null;
	

	@Override
	public AccessoBadge getAccessoBadge(Integer idAccesso) throws Exception {
		
		String query = "SELECT * FROM accessi_badge WHERE id = ?";

        AccessoBadge accesso = null;
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try { 
			accesso = jdbcTemplate.queryForObject(query, new Object[] {idAccesso}, new RowMapper<AccessoBadge>() {
				@Override
				public AccessoBadge mapRow(ResultSet rs, int row) throws SQLException {
					AccessoBadge accesso = new AccessoBadge();
					accesso.setIdAccesso(rs.getInt("id"));
					accesso.setMatricola(rs.getString("matricola_utente"));
					accesso.setDataOrario(rs.getTimestamp("data_orario"));
					accesso.setTipo(rs.getInt("tipo"));
					return accesso;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return accesso;
		}


	@Override
	public List<AccessoBadge> getListaAccessi(AccessoBadge accessoBadge) throws Exception {
		
		List<AccessoBadge> listaAccessi = null;

		String query = "SELECT * FROM accessi_badge WHERE matricola_utente = ? AND month(data_orario) = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			LocalDate localDate = accessoBadge.getDataOrario().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localDate.getMonthValue();
			
			listaAccessi = jdbcTemplate.query(query, new Object[] {accessoBadge.getMatricola(), 
					month}, new RowMapper<AccessoBadge>() {
				@Override
				public AccessoBadge mapRow(ResultSet rs, int row) throws SQLException {
					AccessoBadge accessoBadge = new AccessoBadge();
					accessoBadge.setIdAccesso(rs.getInt("id"));
					accessoBadge.setMatricola(rs.getString("matricola_utente"));
					accessoBadge.setDataOrario(rs.getTimestamp("data_orario"));
					accessoBadge.setTipo(rs.getInt("tipo"));
					return accessoBadge;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return listaAccessi;
	}


	@Override
	public Integer insert(AccessoBadge accessoBadge) throws Exception {
		
		String query = "INSERT INTO accessi_badge(matricola_utente, data_orario, tipo) VALUES(?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
					ps.setString(1, accessoBadge.getMatricola());
					Timestamp time = new Timestamp(accessoBadge.getDataOrario().getTime());
					ps.setTimestamp(2, time);
					ps.setInt(3, accessoBadge.getTipo());
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return keyHolder.getKey().intValue();
	}


	@Override
	public void update(AccessoBadge accessoBadge) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	/*
	 * @Override public List<Badge> getListaBadge(Integer matricola) throws
	 * Exception {
	 * 
	 * List<Badge> listaBadge = null;
	 * 
	 * String query = "SELECT * FROM accessi_badge a WHERE matricola_utente = ?";
	 * 
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * 
	 * try { listaBadge = jdbcTemplate.query(query, new RowMapper<Badge>() {
	 * 
	 * @Override public Badge mapRow(ResultSet rs, int row) throws SQLException {
	 * Badge badge = new Badge(); badge.setId(rs.getInt("id"));
	 * badge.setMatricolaUtente(rs.getString("matricola_utente"));
	 * badge.setDataOrario(rs.getDate("data_orario"));
	 * badge.setTipo(rs.getInt("tipo")); return badge; } }); } catch (Exception e) {
	 * e.printStackTrace(); throw e; }
	 * 
	 * return listaBadge; }
	 * 
	 * @Override public Integer insert(Badge badge) throws Exception { String query
	 * =
	 * "INSERT INTO accessi_badge(matricola_utente, data_orario, tipo) VALUES(?, ?, ?)"
	 * ;
	 * 
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * 
	 * KeyHolder keyHolder = new GeneratedKeyHolder();
	 * 
	 * try { jdbcTemplate.update(new PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection
	 * connection) throws SQLException { PreparedStatement ps =
	 * connection.prepareStatement(query, new String[] { "id" }); ps.setString(1,
	 * badge.getMatricolaUtente()); ps.setDate(2, new
	 * java.sql.Date(badge.getDataOrario().getTime())); ps.setInt(3,
	 * badge.getTipo()); return ps; } }, keyHolder); } catch (Exception e) {
	 * e.printStackTrace(); throw e; }
	 * 
	 * return keyHolder.getKey().intValue(); }
	 */
		
		
	
	
	

}
