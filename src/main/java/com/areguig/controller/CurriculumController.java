package com.areguig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.areguig.AppConstants;

@RestController
public class CurriculumController {

	@RequestMapping("")
	public String getDefaultCurriculum(@RequestParam(value = "origin",
			required = false) String origin, HttpServletResponse response,
			HttpServletRequest request) {
		return "default CV";
	}

	@RequestMapping("")
	public String getCurriculum(@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName, @RequestParam(
					value = "origin", required = false) String origin,
			HttpServletResponse response, HttpServletRequest request) {
		String ipAddress = request.getHeader(AppConstants.HTTP_XFF);
		// Sans proxy
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return "What What in the butt !!!";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request,
			HttpServletResponse response, Exception exception) {
		exception.printStackTrace();
		return "Something wrong happened :-(";
	}
}
