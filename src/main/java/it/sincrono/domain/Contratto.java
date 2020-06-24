package it.sincrono.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Contratto {

	private Integer id;
	private Anagrafica anagrafica;
	private TipoContratto tipoContratto;
	private String mansione;
	private Date dataInizio;
	private Date dataFine;
	private Provincia sedeLavoro;
	private Double RAL;

	public Contratto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contratto(Integer id, Anagrafica anagrafica, TipoContratto tipoContratto, String mansione, Date dataInizio,
			Date dataFine, Provincia sedeLavoro, Double rAL) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.tipoContratto = tipoContratto;
		this.mansione = mansione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.sedeLavoro = sedeLavoro;
		RAL = rAL;
	}

	public Contratto(Integer id) {
		super();
		this.id = id;
	}

	public Contratto(Anagrafica anagrafica, TipoContratto tipoContratto, String mansione, Date dataInizio,
			Date dataFine, Provincia sedeLavoro, Double rAL) {
		super();
		this.anagrafica = anagrafica;
		this.tipoContratto = tipoContratto;
		this.mansione = mansione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.sedeLavoro = sedeLavoro;
		RAL = rAL;
	}

	public Contratto(Integer id, Anagrafica anagrafica, TipoContratto tipoContratto, Date dataInizio, Date dataFine) {
		super();
		this.id = id;
		this.anagrafica = anagrafica;
		this.tipoContratto = tipoContratto;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}

	public TipoContratto getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(TipoContratto tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public String getMansione() {
		return mansione;
	}

	public void setMansione(String mansione) {
		this.mansione = mansione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Provincia getSedeLavoro() {
		return sedeLavoro;
	}

	public void setSedeLavoro(Provincia sedeLavoro) {
		this.sedeLavoro = sedeLavoro;
	}

	public Double getRAL() {
		return RAL;
	}

	public void setRAL(Double rAL) {
		RAL = rAL;
	}

	@Override
	public String toString() {
		return "Contratto: anagrafica=" + anagrafica.getNome() + " " + anagrafica.getCognome() + ", tipoContratto="
				+ tipoContratto.getDescrizione() + ", mansione=" + mansione + ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine + ", sedeLavoro=" + sedeLavoro.getDescrizione() + ", RAL=" + RAL + "\n";
	}

	public String toStringBreve() {
		return "Contratto: id=" + id + ", anagrafica=" + anagrafica.getNome() + " " + anagrafica.getCognome()
				+ ", tipoContratto=" + tipoContratto.getDescrizione() + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + "\n";
	}
	
	 public Map<String, Object> toMap() {
	        
	    	Map<String, Object> values = new HashMap<>();
	        
	    	values.put("id", id);
	        values.put("id_anagrafica", anagrafica.getId());
	        values.put("id_tipo_contratto", tipoContratto.getId());
	        values.put("mansione", mansione);
	        values.put("data_inizio", dataInizio);
	        values.put("data_fine", dataFine);
	        values.put("id_sede_lavoro", sedeLavoro.getIdProvincia());
	        values.put("ral", RAL);
	        
	        return values;
	      }

}
