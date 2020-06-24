package it.sincrono.controllers.beans.responses;

import lombok.*;

import it.sincrono.controllers.beans.a.Esito;

@Data
public class GenericResponse {
	private Esito esito = null;
}