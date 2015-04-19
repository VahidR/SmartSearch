package se.eniro.search.conf;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchConfiguration extends Configuration {
	
	@NotNull
	@JsonProperty
	private String startMessage;
	
	public String getVersion() {
		return startMessage;
	}

}
