package it.sincrono.domain;

public class Provincia {

	private Integer idProvincia = null;
	private String descrizione = null;

	public Provincia() {
		super();
	}

	public Provincia(Integer idProvincia) {
		super();
		this.idProvincia = idProvincia;
	}

	public Provincia(Integer idProvincia, String descrizione) {
		super();
		this.idProvincia = idProvincia;
		this.descrizione = descrizione;
	}
	
	

	public Provincia(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Integer getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}