package com.areguig.controller.form;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Language {

	@JsonProperty("Name")
	private String	name;

	@JsonProperty("Level")
	private String	level;
}
