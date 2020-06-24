package it.sincrono.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class ContrattoDaoImpl implements ContrattoDao {

	@Autowired
	DataSource dataSource = null;

	@Override
	public List<Contratto> getListaContratti() throws Exception {
		List<Contratto> contratti = null;

		String query = "SELECT * FROM contratti a \n INNER JOIN anagrafiche b ON a.id_anagrafica = b.id \n"
				+ "INNER JOIN tipi_contratto c ON a.id_tipo_contratto = c.id \n INNER JOIN province d ON a.id_sede_lavoro = d.id_provincia";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			contratti = jdbcTemplate.query(query, new RowMapper<Contratto>() {
				@Override
				public Contratto mapRow(ResultSet rs, int row) throws SQLException {
					Contratto contratto = new Contratto();
					contratto.setId(rs.getInt("a.id"));
					contratto.setAnagrafica(new Anagrafica(rs.getInt("a.id_anagrafica"), rs.getString("b.nome"),
							rs.getString("b.cognome"), rs.getString("b.codice_fiscale"), new Provincia(rs.getInt("b.id_provincia_nascita")), 
									rs.getInt("b.eta"), rs.getString("b.sesso"), rs.getBoolean("b.categoria_protetta"), rs.getString("b.curriculum")));
					contratto.setTipoContratto(new TipoContratto(rs.getString("c.descrizione")));
					contratto.setMansione(rs.getString("a.mansione"));
					contratto.setDataInizio(rs.getDate("a.data_inizio"));
					contratto.setDataFine(rs.getDate("a.data_fine"));
					contratto.setSedeLavoro(new Provincia(rs.getString("d.descrizione")));
					contratto.setRAL(rs.getDouble("a.ral"));
					return contratto;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return contratti;
	}

	@Override
	public Contratto getContratto(Integer id) throws Exception {
		Contratto contratto = null;

		String query = "SELECT * FROM contratti a \n INNER JOIN anagrafiche b ON a.id_anagrafica = b.id \n"
				+ "INNER JOIN tipi_contratto c ON a.id_tipo_contratto = c.id \n INNER JOIN province d ON a.id_sede_lavoro = d.id_provincia \n"
				+ "WHERE a.id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			contratto = jdbcTemplate.queryForObject(query, new Object[] { id }, new RowMapper<Contratto>() {
				@Override
				public Contratto mapRow(ResultSet rs, int row) throws SQLException {
					Contratto contratto = new Contratto();
					contratto.setId(rs.getInt("a.id"));
					contratto.setAnagrafica(new Anagrafica(rs.getInt("a.id_anagrafica"), rs.getString("b.nome"),
							rs.getString("b.cognome"), rs.getString("b.codice_fiscale"), new Provincia(rs.getInt("b.id_provincia_nascita")), 
							rs.getInt("b.eta"), rs.getString("b.sesso"), rs.getBoolean("b.categoria_protetta"), rs.getString("b.curriculum")));
					contratto.setTipoContratto(new TipoContratto(rs.getString("c.descrizione")));
					contratto.setMansione(rs.getString("a.mansione"));
					contratto.setDataInizio(rs.getDate("a.data_inizio"));
					contratto.setDataFine(rs.getDate("a.data_fine"));
					contratto.setSedeLavoro(new Provincia(rs.getString("d.descrizione")));
					contratto.setRAL(rs.getDouble("a.ral"));
					return contratto;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return contratto;
	}

	@Override
	public Integer insert(Contratto contratto) throws Exception {
		String query = "INSERT INTO contratti(id_anagrafica, id_tipo_contratto, mansione, data_inizio, data_fine, id_sede_lavoro, ral) VALUES(?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
					ps.setInt(1, contratto.getAnagrafica().getId());
					ps.setInt(2, contratto.getTipoContratto().getId());
					ps.setString(3, contratto.getMansione());
					ps.setDate(4, new java.sql.Date(contratto.getDataInizio().getTime()));
					ps.setDate(5, new java.sql.Date(contratto.getDataFine().getTime()));
					ps.setInt(6, contratto.getSedeLavoro().getIdProvincia());
					ps.setDouble(7, contratto.getRAL());
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
	public void update(Contratto contratto) throws Exception {

		String query = "UPDATE contratti SET id_anagrafica = ?, id_tipo_contratto = ?, mansione = ?, data_inizio = ?, "
				+ "data_fine = ?, id_sede_lavoro = ?, ral = ? WHERE id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {

			String dataIn = new SimpleDateFormat("yyyy-MM-dd").format(contratto.getDataInizio());
			String dataFinn = new SimpleDateFormat("yyyy-MM-dd").format(contratto.getDataFine());

			jdbcTemplate.update(query,
					new Object[] { contratto.getAnagrafica().getId(), contratto.getTipoContratto().getId(),
							contratto.getMansione(), dataIn, dataFinn, contratto.getSedeLavoro().getIdProvincia(),
							contratto.getRAL() });

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void delete(Integer id) throws Exception {
		String query = "DELETE FROM contratti WHERE id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.update(query, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<TipoContratto> caricaTipi() throws Exception {
		List<TipoContratto> tipi = new ArrayList<TipoContratto>();

		String query = "SELECT id, descrizione " + "FROM tipi_contratto " + "ORDER BY descrizione";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			tipi = jdbcTemplate.query(query, new RowMapper<TipoContratto>() {

				@Override
				public TipoContratto mapRow(ResultSet rs, int row) throws SQLException {
					TipoContratto tipo = new TipoContratto();
					tipo.setId(rs.getInt("id"));
					tipo.setDescrizione(rs.getString("descrizione"));
					return tipo;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return tipi;
	}

}
