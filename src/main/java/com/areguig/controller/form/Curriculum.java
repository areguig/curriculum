package com.areguig.controller.form;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"comment", "personalInfo", "networks"})
public class Curriculum {

	@JsonProperty("Comment")
	private String					comment;

	@JsonProperty("About Me")
	private About					about;

	@JsonProperty("Networks")
	private Networks				networks;

	@JsonProperty("Education")
	private List<EducationEntry>	education;

	@JsonProperty("Work experience")
	private List<WorkExperience>	workExperience;

	@JsonProperty("Languages")
	private List<Language>			languages;

}
