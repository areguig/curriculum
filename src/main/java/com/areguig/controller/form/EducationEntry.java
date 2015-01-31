package com.areguig.controller.form;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EducationEntry {

	@JsonProperty("Institution")
	private String			institution;

	@JsonProperty("Area")
	private String			area;

	@JsonProperty("Study type")
	private String			studyType;

	@JsonProperty("Start date")
	private String			startDate;

	@JsonProperty("End date")
	private String			endDate;

	@JsonProperty("Diploma")
	private String			diploma;

	@JsonProperty("Courses")
	private List<String>	courses;

}
