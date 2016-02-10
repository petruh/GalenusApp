package ro.sci.gms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Patient;
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private Patient loggedPatient;

	@RequestMapping("")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.addObject("user", loggedPatient);

		return modelAndView;
	}

	@RequestMapping("/patient/profile")
	public ModelAndView editPatient() {

		ModelAndView modelAndView = new ModelAndView("patientedit");
		modelAndView.addObject("patient", loggedPatient);

		return modelAndView;
	}

	@RequestMapping("/patient")
	public String indexPatient() {
		return "index_patient";
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public String add() {
		return "appointment_edit";
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "appointment_edit";
	}
}
