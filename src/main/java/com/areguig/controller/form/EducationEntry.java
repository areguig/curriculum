package com.areguig.controller.form;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"area", "institution", "city", "diploma", "courses",
		"startDate", "endDate"})
public class EducationEntry {

	@JsonProperty("Institution")
	private String			institution;

	@JsonProperty("Area")
	private String			area;

	@JsonProperty("Start date")
	private String			startDate;

	@JsonProperty("End date")
	private String			endDate;

	@JsonProperty("Diploma")
	private String			diploma;

	@JsonProperty("Courses")
	private List<String>	courses;

	@JsonProperty("City")
	private String			city;

}
