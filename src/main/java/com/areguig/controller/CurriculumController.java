package com.areguig.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.areguig.AppConstants;
import com.areguig.controller.form.Curriculum;
import com.areguig.mapper.CurriculumMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Builder;

@Controller
public class CurriculumController {

	@Autowired
	private CurriculumMapper	curriculumMapper;

	@RequestMapping(value = "/curriculum.json", produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody Curriculum getDefaultCurriculum(@RequestParam(
			value = "origin", required = false) String origin,
			HttpServletResponse response, HttpServletRequest request) {
		Curriculum curriculum = new Curriculum();
		return curriculum;
	}

	@RequestMapping("/curriculum")
	public String aklireguig(Model model) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			model.addAttribute("curriculum", mapper
					.writeValueAsString(curriculumMapper.toAkliCurriculum()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "curriculum";
	}

	@RequestMapping("/{fnln}")
	public String curriculum(
			@RequestParam(value = "origin", required = false) String origin,
			@PathVariable String fnln, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		String ipAddress = request.getHeader(AppConstants.HTTP_XFF);
		// Sans proxy
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		ObjectMapper mapper = new ObjectMapper();
		if (fnln.equals("aklireguig")) {
			try {
				model.addAttribute("curriculum",
						mapper.writeValueAsString(curriculumMapper
								.toAkliCurriculum()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("curriculum",
					"{ \"message\":\"I don't know this person yet. Try linkedIn!\"}");
		}
		queueConsultationCreation(fnln, ipAddress, origin);
		return "curriculum";

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

		return curriculum;
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request,
			HttpServletResponse response, Exception exception) {
		exception.printStackTrace();
		return "Something wrong happened :-(";
	}

	private void queueConsultationCreation(String fnln, String ipAddress,
			String origin) {
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(Builder
				.withUrl("/tasks/newConsultation")
				.param("fnln", fnln)
				.param("ipAddress", ipAddress)
				.param("origin", origin != null ? origin : "unknown")
				.param("date",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())));
	}
}
