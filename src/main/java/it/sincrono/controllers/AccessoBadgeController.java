package it.sincrono.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import it.sincrono.controllers.beans.a.Esito;
import it.sincrono.controllers.beans.requests.AccessoBadgeRequest;
import it.sincrono.controllers.beans.responses.BadgeListResponse;
import it.sincrono.domain.AccessoBadge;
import it.sincrono.services.AccessoBadgeService;
import it.sincrono.services.exceptions.ServiceException;


public class AccessoBadgeController {
	
	@Autowired 
	AccessoBadgeService badgeService;
	
	
	/*
	 * @RequestMapping(value = "/accessoBadge/matricola/{matricola}", method =
	 * RequestMethod.GET, produces = "application/json") public @ResponseBody
	 * HttpEntity<AccessoBadgeListResponse>
	 * getByMatricola(@PathVariable("matricola") Integer matricola) {
	 * 
	 * HttpEntity<AccessoBadgeListResponse> httpEntity = null;
	 * 
	 * AccessoBadgeListResponse accessoBadgeListResponse = new
	 * AccessoBadgeListResponse();
	 * 
	 * try { List<AccessoBadge> accessiBadge =
	 * managerservice.getListaAccessiBadgeByMatricola(matricola);
	 * 
	 * accessoBadgeListResponse.setList(accessiBadge);
	 * accessoBadgeListResponse.setEsito(new Esito());
	 * 
	 * httpEntity = new
	 * HttpEntity<AccessoBadgeListResponse>(accessoBadgeListResponse);
	 * 
	 * } catch (ServiceException e) {
	 * 
	 * badgeListResponse.setEsito(new Esito(e.getMessage())); httpEntity = new
	 * HttpEntity<AccessoBadgeListResponse>(accessoBadgeListResponse); }
	 * 
	 * return httpEntity; }
	 */

	@RequestMapping(value = "/badge", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody HttpEntity<BadgeListResponse> getListaAccessi(@RequestBody AccessoBadgeRequest accessoBadgeRequest) {
		
		HttpEntity<BadgeListResponse> httpEntity = null;

		BadgeListResponse badgeListResponse = new BadgeListResponse();

		try {
			List<AccessoBadge> badges = badgeService.getListaAccessi(accessoBadgeRequest.getAccessoBadge());

			badgeListResponse.setListaAccessi(badges);
			badgeListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<BadgeListResponse>(badgeListResponse);

		} catch (ServiceException e) {

			badgeListResponse.setEsito(new Esito(e.getMessage()));
			httpEntity = new HttpEntity<BadgeListResponse>(badgeListResponse);
		}
		
		return httpEntity;
	}
	

}
