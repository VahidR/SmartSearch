package se.eniro.search.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestEntity {
	
	@JsonProperty
    @NotNull
    private List<String> searchWords;
	
	@JsonProperty
    @NotNull
    private List<String> filters;
	
	public RequestEntity() {
		
	}
	
	public RequestEntity(List<String> searchWords, List<String> filters) {
		this.searchWords = searchWords;
		this.filters = filters;
	}
	
	public List<String> getSearchWords() {
		return searchWords;
	}
	
	public List<String> getFilters() {
		return filters;
	}

}
