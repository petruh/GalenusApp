package ro.sci.gms.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.sci.gms.domain.User;

@RestController
public class CurrentUserController {
	
	@RequestMapping("/rest/currentuser")
	public User getCurrentUsername(@AuthenticationPrincipal User currentUser){
//		if (user==null) {
//			return "false";
//		}
//		else {
//			return user.getUsername();
//		}
		return currentUser;
	}

}
