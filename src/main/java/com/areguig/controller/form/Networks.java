package com.areguig.controller.form;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Networks {

	@JsonProperty("LinkedIn")
	private String	linkedIn;

	@JsonProperty("Twitter")
	private String	twitter;

	@JsonProperty("Viadeo")
	private String	viadeo;

	@JsonProperty("Github")
	private String	github;
}
