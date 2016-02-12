package ro.sci.gms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/register&login.html")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	@Qualifier("userService")
	UserService userService;
	@Autowired 
	PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST)
	public String save(User user, BindingResult bindingResult) throws ValidationException {
		LOGGER.debug("Saving: " + user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userService.save(user);
		} catch (Exception e) {
			bindingResult.addError(new ObjectError("user", e.getMessage()));
		}
		return "redirect:/index.html";
	}

	@RequestMapping("")
	public String register(@AuthenticationPrincipal User user) {
		if (user==null) {
			return "register&login";
		}
		else {
			LOGGER.debug("returning register and login");
			return "redirect:/index.html";
		}
	}

}
