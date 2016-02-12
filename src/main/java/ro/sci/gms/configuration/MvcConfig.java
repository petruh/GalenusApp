package ro.sci.gms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/doctor").setViewName("doctor");
//        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register&login.html").setViewName("register&login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/about-us.html").setViewName("about-us");
        registry.addViewController("/appointments_list.html").setViewName("appointments_list");
        registry.addViewController("/blog-item1.html").setViewName("blog-item1");
        registry.addViewController("/blog-item2.html").setViewName("blog-item2");
        registry.addViewController("/blog-item3.html").setViewName("blog-item3");
        registry.addViewController("/blog.html").setViewName("blog");
        registry.addViewController("/contact-us.html").setViewName("contact-us");
        registry.addViewController("/doctor.html").setViewName("doctor");
        registry.addViewController("/doctora.html").setViewName("doctora");
        registry.addViewController("/doctorb.html").setViewName("doctorb");
        registry.addViewController("/editdoctor.html").setViewName("editdoctor");
        registry.addViewController("/edituser.html").setViewName("edituser");
        registry.addViewController("/error.html").setViewName("error");
        registry.addViewController("/services.html").setViewName("services");
        registry.addViewController("/terms&conditions.html").setViewName("terms&conditions");
        registry.addViewController("/user.html").setViewName("user");
        registry.addViewController("/login.html").setViewName("login");
    }
}
