package it.sincrono.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anagrafica {

	private Integer id = null;
	private String nome = null;
	private String cognome = null;
	private String codiceFiscale = null;
	private Provincia provinciaNascita = null;
	private Integer eta = null;
	private String sesso = null;
	private Boolean categoriaProtetta = null;
	private String curriculum = null;

}