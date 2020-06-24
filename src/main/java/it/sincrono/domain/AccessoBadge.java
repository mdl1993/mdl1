package it.sincrono.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessoBadge {
	
	protected Integer idAccesso;
	protected String matricola;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "CET")
	protected Date dataOrario;
	protected Integer tipo;
	
	
	

}
