package it.sincrono.domain;

public class TipoContratto {

	protected Integer id;
	protected String descrizione;

	public TipoContratto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoContratto(Integer id, String descrizione) {
		super();
		this.id = id;
		this.descrizione = descrizione;
	}

	public TipoContratto(Integer id) {
		super();
		this.id = id;
	}

	public TipoContratto(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "TipoContratto [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
