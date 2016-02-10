package ro.sci.gms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.sci.gms.domain.User;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	@Qualifier("userService")
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String save(User user, BindingResult bindingResult) throws ValidationException {
		LOGGER.debug("Saving: " + user);
		try {
			userService.save(user);
		} catch (Exception e) {
			// result = renderEditPage(student.getId());
			bindingResult.addError(new ObjectError("user", e.getMessage()));
		}
		return "index";
	}

	@RequestMapping("")
	String register(@AuthenticationPrincipal User user) {
		if (user==null) {
			return "register";
		}
		else {
			return "index";
		}
	}
	/*
	 * public ModelAndView index() {
	 * 
	 * Collection<Employee> allEmployees = employeeService.listAll();
	 * 
	 * ModelAndView modelAndView = new ModelAndView("employee_list");
	 * modelAndView.addObject("allEmployees", allEmployees);
	 * 
	 * return modelAndView; }
	 */

	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public String add() {
		return "rest/user";
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "rest/user";
	}
}
