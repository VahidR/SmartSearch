package se.eniro.search.resources;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.eniro.search.api.RequestEntity;
import se.eniro.search.services.SearchService;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
	
	private static final Logger log = LoggerFactory.getLogger(SearchResource.class);
	
	private final SearchService searchService;
	
	@Inject
	public SearchResource(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@POST
    @Timed
    public List<Map<Object, Object>> fetchEntites(@Valid RequestEntity searchRequest) throws JsonParseException, JsonProcessingException, IOException {
		log.info("Fetching the list of results..");
		List<Map<Object, Object>> results = searchService.fetchEntites(searchRequest);
		return results;
	}
	
}
