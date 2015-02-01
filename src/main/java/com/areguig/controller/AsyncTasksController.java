package com.areguig.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.areguig.repository.CurriculumRepository;

@Controller
@RequestMapping(value = "/tasks")
public class AsyncTasksController {

	@Autowired
	private CurriculumRepository	curriculumRepo;

	@RequestMapping("/newConsultation")
	public void creatConsultation(HttpServletRequest request,
			HttpServletResponse response) {
		String origin = request.getParameter("origin");
		String fnln = request.getParameter("fnln");
		String date = request.getParameter("date");
		String ipAddress = request.getParameter("ipAddress");
		curriculumRepo.createConsultation(fnln, ipAddress, date, origin);
	}
}
