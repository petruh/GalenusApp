package ro.sci.gms.web;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.service.AppointmentService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService aptService;
	@Autowired
	private Patient loggedPatient;
	
	@RequestMapping("")
	public ModelAndView index() {

		Collection<Appointment> allAppointments = aptService.getAll();

		ModelAndView modelAndView = new ModelAndView("appointment_list");
		modelAndView.addObject("allAppointments", allAppointments);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public ModelAndView createAppointmentForm() {
		ModelAndView modelAndView = new ModelAndView("appointment_create");
		
		modelAndView.addObject("appointment", new Appointment());
		return modelAndView;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(method=RequestMethod.POST)
    public String appointmentSubmit(@ModelAttribute Appointment appointment) throws ValidationException {

		// Should come from FORM or from USER contextual data - logged in
		// Will be removed
		Date date = new Date();
		Date time = new Date();
		date.setDate(15);
		time.setHours(11);
		appointment.setPatient(loggedPatient);
		appointment.setDoctor(loggedPatient.getDoctor());
		appointment.setDate(date);
		appointment.setTime(time);
		// Should come from FORM or from USER contextual data - logged in
		
		
		aptService.save(appointment);
		System.out.println(appointment.list());
		
		return "success";
    }

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public ModelAndView edit(@RequestParam("id") Long id) {
		ModelAndView result = new ModelAndView("appointment_edit");
		Appointment apt = new Appointment();
		if (id != null) {
			apt = aptService.get(id);
		}
		result.addObject("appointment", apt);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=delete")
	public String delete(@RequestParam("id") Long id) {
		aptService.delete(id);
		return "success";
	}

	



	@RequestMapping("/test")
	public String testForm(Model model) {
		model.addAttribute("appointment", new Appointment());
		return "test_edit";
	}
	
	
	
	@RequestMapping("/generate")
	public String generate() throws ValidationException {

		aptService.generateSome();

		return "success";

	}
}
