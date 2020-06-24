package it.sincrono.controllers;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import it.sincrono.controllers.exceptions.ControllerException;
import it.sincrono.controllers.forms.AnagraficaForm;
import it.sincrono.controllers.validations.AnagraficaValidator;
import it.sincrono.domain.Anagrafica;
import it.sincrono.domain.Provincia;
import it.sincrono.services.ManagerService;
import it.sincrono.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/anagrafica")
public class AnagraficaController {

	
	  @Autowired ManagerService managerservice;
	 
	
	@Autowired
	private AnagraficaValidator validator;
	
	@RequestMapping("/list")
	public String list(Model model) throws ControllerException {

		try {
			log.info("START invocation getAll() of controller layer");

			
			List<Anagrafica> anagrafiche = managerservice.getListaAnagrafica();

			model.addAttribute("anagrafiche", anagrafiche);

		} catch (ServiceException e) {

			throw new ControllerException(e);

		}

		return "lista-anagrafica";
	}

	@RequestMapping("/get/{id}")
	public String get(@PathVariable("id") Integer id, Model model) throws ControllerException {

		try {
			Anagrafica anagrafica = managerservice.getAnagrafica(id);

			model.addAttribute("anagrafica", anagrafica);

		} catch (ServiceException e) {

			throw new ControllerException(e);

		}

		return "dettaglio-anagrafica";
	}

	@RequestMapping("/openInsert")

	public String openInsert(Model model, @ModelAttribute AnagraficaForm anagraficaForm) throws ControllerException {

		Map<Integer, String> province = new HashMap<Integer, String>();

		try {
			List<Provincia> prov = managerservice.caricaProvince();
			for (Provincia ele : prov) {
				province.put(ele.getIdProvincia(), ele.getDescrizione());
			}

			model.addAttribute("anagraficaForm", anagraficaForm);
			model.addAttribute("province", province);
		} catch (ServiceException e) { //
			e.printStackTrace();
		}
		return "nuova-anagrafica";
	}

	/*
	 * public String openInsert(Model model, @ModelAttribute AnagraficaForm
	 * anagraficaForm) throws ControllerException {
	 * 
	 * try { List<Provincia> province = managerservice.caricaProvincie();
	 * model.addAttribute("anagraficaForm", new AnagraficaForm());
	 * model.addAttribute("province", province); } catch (ServiceException e) {
	 * throw new ControllerException(e); }
	 * 
	 * return "nuova-anagrafica"; }
	 */
	@RequestMapping("/insert")
	public String insert(Model model, @ModelAttribute AnagraficaForm anagraficaForm, BindingResult result)
			throws ControllerException {

		String returnValue = "redirect:/anagrafica/list";

		try {
			validator.validate(anagraficaForm.getAnagrafica(), result);

			if (result.hasErrors()) {
				Map<Integer, String> province = new HashMap<Integer, String>();
				List<Provincia> prov = managerservice.caricaProvince();
				for (Provincia ele : prov) {
					province.put(ele.getIdProvincia(), ele.getDescrizione());
				}

				model.addAttribute("errors", result);
				model.addAttribute("province", province);

				return "nuova-anagrafica";
			}

			managerservice.insert(anagraficaForm.getAnagrafica());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

	@RequestMapping("/openUpdate/{id}")
	public String openUpdate(@PathVariable("id") Integer id, Model model) throws ControllerException {

		Map<Integer, String> province = new HashMap<Integer, String>();

		try {
			List<Provincia> prov = managerservice.caricaProvince();
			for (Provincia ele : prov) {
				province.put(ele.getIdProvincia(), ele.getDescrizione());
			}
			AnagraficaForm anagraficaForm = new AnagraficaForm(managerservice.getAnagrafica(id));

			model.addAttribute("anagraficaForm", anagraficaForm);
			model.addAttribute("province", province);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "modifica-anagrafica";
	}

	@RequestMapping("/openUpdate/update")
	public String update(Model model, @ModelAttribute AnagraficaForm anagraficaForm, BindingResult result)
			throws ControllerException {

		String returnValue = "redirect:/anagrafica/get/"+ anagraficaForm.getAnagrafica().getId();

		try {
			validator.validate(anagraficaForm.getAnagrafica(), result);

			if (result.hasErrors()) {
				Map<Integer, String> province = new HashMap<Integer, String>();
				List<Provincia> prov = managerservice.caricaProvince();
				for (Provincia ele : prov) {
					province.put(ele.getIdProvincia(), ele.getDescrizione());
				}

				model.addAttribute("errors", result);
				model.addAttribute("province", province);

				return "modifica-anagrafica";
			}

			managerservice.update(anagraficaForm.getAnagrafica());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) throws ControllerException {

		String returnValue = "redirect:/anagrafica/list";

		try {
			managerservice.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}
	/*
	 * @RequestMapping(value= "/anagrafica", method = RequestMethod.POST, consumes =
	 * "application/json") public @ResponseBody HttpEntity<GenericResponse>
	 * insert(@RequestBody AnagraficaRequest anagraficaRequest) {
	 * HttpEntity<GenericResponse> httpEntity = null;
	 * 
	 * GenericResponse genericResponse = new GenericResponse();
	 * 
	 * 
	 * 
	 * 
	 * return httpEntity; }
	 */
	
}