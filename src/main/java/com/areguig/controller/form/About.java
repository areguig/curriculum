package com.areguig.controller.form;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"firstName", "lastName", "email", "phone", "linkedIn"})
public class About {

	@JsonProperty("First name")
	private String		firstName;

	@JsonProperty("Last name")
	private String		lastName;

	@JsonProperty("Location")
	private Location	location;

	@JsonProperty("Email")
	private String		email;

	@JsonProperty("Phone")
	private String		phone;

	@JsonProperty("LinkedIn")
	private String		linkedIn;

	@JsonProperty("Github")
	private String		github;

}
