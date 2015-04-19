package se.eniro.search.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.eniro.search.api.RequestEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchService {
	
	private static final Logger log = LoggerFactory.getLogger(SearchService.class);
	
	private final String BASE_URL = "http://api.eniro.com/cs/search/basic";
	private final String PROFILE = "[YOUR_PROFILE_NAME_HERE]";
	private final String KEY = "YOUR_kEY_HERE";
	private final String COUNTRY = "se";
	private final String VERSION = "1.1.3";
	
	private final ObjectMapper objectMapper;

	
	@Inject
	public SearchService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public List<Map<Object, Object>> fetchEntites(RequestEntity searchRequest) throws  JsonParseException, JsonProcessingException, IOException {
		List<String> words = searchRequest.getSearchWords();
		String searchWords = words.stream().collect(Collectors.joining(","));
		List<String> filters = searchRequest.getFilters();
		
		String eniroURL = makeEniroURL(BASE_URL, PROFILE, KEY, COUNTRY, VERSION, searchWords);
		URL eniroApiUrl = new URL(eniroURL);
		HttpURLConnection eniroUrlConn = (HttpURLConnection) eniroApiUrl.openConnection(); 
		if(eniroUrlConn.getResponseCode() != 200)
			return Collections.emptyList();
		BufferedReader in = new BufferedReader(new InputStreamReader(eniroUrlConn.getInputStream(), "UTF-8")); 
		StringBuilder strResult = new StringBuilder(); 
		String inputLine; 
		while ((inputLine = in.readLine()) != null) { 
			strResult.append(inputLine); 
		} 
		in.close(); 
		
		JsonNode json = objectMapper.readTree(objectMapper.getJsonFactory().createJsonParser(strResult.toString())); 
		JsonNode adverts = json.path("adverts");
		List<Map<Object, Object>> responseList = new ArrayList<>();
		for (JsonNode company: adverts) {
			Map<Object, Object> companyRecord = new HashMap<>();
			for (int i = 0; i < filters.size(); ++i) {
				companyRecord.put(filters.get(i), company.findPath(filters.get(i)));
			}
			responseList.add(companyRecord);
		}
		return responseList;
	}

	private String makeEniroURL(String baseURL, 
								String profile, 
								String key, 
								String country, 
								String version,
								String searchWords) {
		return 	baseURL + 
				"?profile=" + profile + 
				"&key=" + key + 
				"&country=" + country + 
				"&version=" + version + 
				"&search_word=" + searchWords; 
	}

}
