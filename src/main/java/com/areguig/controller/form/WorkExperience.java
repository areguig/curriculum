package com.areguig.controller.form;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"company", "position", "website", "startDate", "endDate",
		"summary"})
public class WorkExperience {

	@JsonProperty("Company")
	private String	company;

	@JsonProperty("Position")
	private String	position;

	@JsonProperty("Website")
	private String	website;

	@JsonProperty("Start date")
	private String	startDate;

	@JsonProperty("End date")
	private String	endDate;

	@JsonProperty("Summary")
	private String	summary;

}
