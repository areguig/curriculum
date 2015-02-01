package com.areguig.controller.form;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"address", "postalCode", "city", "region", "country"})
public class Location {

	@JsonProperty("Address")
	private String	address;

	@JsonProperty("Postal code")
	private String	postalCode;

	@JsonProperty("City")
	private String	city;

	@JsonProperty("Region")
	private String	region;

	@JsonProperty("Country")
	private String	country;

}
