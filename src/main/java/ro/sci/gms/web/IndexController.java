package ro.sci.gms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.sci.gms.service.AppointmentService;
import ro.sci.gms.service.ValidationException;

@Controller
public class IndexController {

	@Autowired
	private AppointmentService aptService;
	
	@RequestMapping(value={"/", "index.html"})
	String index() throws ValidationException {
//		aptService.generateSome();
		return "index";
	}
}
