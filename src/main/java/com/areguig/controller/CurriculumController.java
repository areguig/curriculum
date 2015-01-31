package com.areguig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.areguig.AppConstants;
import com.areguig.controller.form.About;
import com.areguig.controller.form.Curriculum;
import com.areguig.mapper.CurriculumMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CurriculumController {

	@Autowired
	private CurriculumMapper	mapper;

	@RequestMapping(value = "/curriculum.json", produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody Curriculum getDefaultCurriculum(@RequestParam(
			value = "origin", required = false) String origin,
			HttpServletResponse response, HttpServletRequest request) {
		Curriculum curriculum = new Curriculum();
		About personalInformation = new About();
		personalInformation.setFirstName("Akli");
		personalInformation.setLastName("REGUIG");
		personalInformation.setEmail("akli.reguig@gmail.com");
		personalInformation.setPhone("06 85 89 79 12");
		personalInformation
				.setLinkedIn("http://fr.linkedin.com/pub/akli-reguig/32/649/370/");
		curriculum.setAbout(personalInformation);
		return curriculum;
	}

	@RequestMapping("/curriculum")
	public String aklireguig(Model model) {
		Curriculum curriculum = new Curriculum();
		About personalInformation = new About();
		personalInformation.setFirstName("Akli");
		personalInformation.setLastName("REGUIG");
		personalInformation.setEmail("akli.reguig@gmail.com");
		personalInformation.setPhone("06 85 89 79 12");
		personalInformation
				.setLinkedIn("http://fr.linkedin.com/pub/akli-reguig/32/649/370/");
		curriculum.setAbout(personalInformation);
		curriculum.setComment("This is the curriculum !!!");
		ObjectMapper mapper = new ObjectMapper();
		try {
			model.addAttribute("curriculum",
					mapper.writeValueAsString(curriculum));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "curriculum";
	}

	@RequestMapping("/{fnln}")
	public String curriculum(
			@RequestParam(value = "origin", required = false) String origin,
			Model model, HttpServletResponse response,
			HttpServletRequest request) {

		return "";

	}

	@RequestMapping(value = "/old", produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody Curriculum getCurriculum(
			@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName, @RequestParam(
					value = "origin", required = false) String origin,
			HttpServletResponse response, HttpServletRequest request) {
		String ipAddress = request.getHeader(AppConstants.HTTP_XFF);
		// Sans proxy
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Curriculum curriculum = new Curriculum();
		About personalInformation = new About();
		personalInformation.setFirstName("Akli");
		personalInformation.setLastName("REGUIG");
		personalInformation.setEmail("akli.reguig@gmail.com");
		personalInformation.setPhone(ipAddress);
		personalInformation
				.setLinkedIn("fr.linkedin.com/pub/akli-reguig/32/649/370/");
		curriculum.setAbout(personalInformation);

		return curriculum;
	}

	// @ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request,
			HttpServletResponse response, Exception exception) {
		exception.printStackTrace();
		return "Something wrong happened :-(";
	}
}
