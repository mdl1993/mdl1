package it.sincrono.controllers;
import java.util.Date;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class BaseController {

    @InitBinder
    public void initBinderAll(WebDataBinder binder)
    {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		/*
		 * binder.registerCustomEditor(Date.class, new DateEditor());
		 * binder.registerCustomEditor(Double.class, new DoubleEditor());
		 */
    }
}