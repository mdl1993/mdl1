package it.sincrono.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import it.sincrono.domain.*;

@Repository
public class ManagerMySqlDaoImpl implements ManagerDao {
	
	
	@Autowired
	DataSource dataSource = null;

	@Override
	public List<Anagrafica> getListaAnagrafica() throws Exception {
		List<Anagrafica> anagrafiche = null;

		String query = "SELECT * FROM anagrafiche a INNER JOIN province b ON a.id_provincia_nascita = b.id_provincia "
				+ "ORDER BY a.cognome";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			anagrafiche = jdbcTemplate.query(query, new RowMapper<Anagrafica>() {
				@Override
				public Anagrafica mapRow(ResultSet rs, int row) throws SQLException {
					Anagrafica anagrafica = new Anagrafica();
					anagrafica.setId(rs.getInt("id"));
					anagrafica.setNome(rs.getString("nome"));
					anagrafica.setCognome(rs.getString("cognome"));
					anagrafica.setCodiceFiscale(rs.getString("codice_fiscale"));
					anagrafica.setProvinciaNascita(new Provincia(rs.getInt("id_provincia_nascita")));
					anagrafica.setEta(rs.getInt("eta"));
					anagrafica.setSesso(rs.getString("sesso"));
					anagrafica.setCategoriaProtetta(rs.getBoolean("categoria_protetta"));
					anagrafica.setCurriculum(rs.getString("curriculum"));
					return anagrafica;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return anagrafiche;
	}

	@Override
	public Anagrafica getAnagrafica(Integer id) throws Exception {
		Anagrafica anagrafica = null;

		String query = "SELECT * FROM anagrafiche a INNER JOIN province b ON a.id_provincia_nascita = b.id_provincia WHERE a.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			anagrafica = jdbcTemplate.queryForObject(query, new Object[] { id }, new RowMapper<Anagrafica>() {
				@Override
				public Anagrafica mapRow(ResultSet rs, int row) throws SQLException {
					Anagrafica anagrafica = new Anagrafica();
					anagrafica.setId(rs.getInt("id"));
					anagrafica.setNome(rs.getString("nome"));
					anagrafica.setCognome(rs.getString("cognome"));
					anagrafica.setCodiceFiscale(rs.getString("codice_fiscale"));
					anagrafica.setProvinciaNascita(
							new Provincia(rs.getInt("id_provincia_nascita"), rs.getString("nome")));
					anagrafica.setEta(rs.getInt("eta"));
					anagrafica.setSesso(rs.getString("sesso"));
					anagrafica.setCategoriaProtetta(rs.getBoolean("categoria_protetta"));
					anagrafica.setCurriculum(rs.getString("curriculum"));
					return anagrafica;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return anagrafica;
	}

	@Override
	public Integer insert(Anagrafica anagrafica) throws Exception {
		String query = "INSERT INTO anagrafiche(nome, cognome, codice_fiscale, id_provincia_nascita, eta, sesso, categoria_protetta, curriculum) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
					ps.setString(1, anagrafica.getNome());
					ps.setString(2, anagrafica.getCognome());
					ps.setString(3, anagrafica.getCodiceFiscale());
					ps.setInt(4, anagrafica.getProvinciaNascita().getIdProvincia());
					ps.setInt(5, anagrafica.getEta());
					ps.setString(6, anagrafica.getSesso());
					ps.setBoolean(7, anagrafica.getCategoriaProtetta());
					ps.setString(8, anagrafica.getCurriculum());
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
	public void update(Anagrafica anagrafica) throws Exception {

		String query = "UPDATE anagrafiche SET nome = ?, cognome = ?, codice_fiscale = ?, id_provincia_nascita = ?, eta = ?, sesso = ?, categoria_protetta = ?, curriculum = ? WHERE id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.update(query,
					new Object[] { anagrafica.getNome(), anagrafica.getCognome(), anagrafica.getCodiceFiscale(),
							anagrafica.getProvinciaNascita().getIdProvincia(), anagrafica.getEta(),
							anagrafica.getSesso(), anagrafica.getCategoriaProtetta(), anagrafica.getCurriculum(),
							anagrafica.getId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(Integer id) throws Exception {
		String query = "DELETE FROM anagrafiche WHERE id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.update(query, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Provincia> caricaProvince() throws Exception {
		List<Provincia> province = new ArrayList<Provincia>();

		String query = "SELECT id_provincia, descrizione " + "FROM province " + "ORDER BY descrizione";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			province = jdbcTemplate.query(query, new RowMapper<Provincia>() {

				@Override
				public Provincia mapRow(ResultSet rs, int row) throws SQLException {
					Provincia provincia = new Provincia();
					provincia.setIdProvincia(rs.getInt("id_provincia"));
					provincia.setDescrizione(rs.getString("descrizione"));
					return provincia;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return province;
	}
}