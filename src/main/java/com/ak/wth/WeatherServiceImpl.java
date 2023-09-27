package com.ak.wth;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService{

	private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";
    private static final String API_KEY= "9078ea5eedbb0baaa93d44a2e3e16736";
	@Autowired
	private RestTemplate rt;
	

	@Autowired
	private WeatherRepo repo;
	
	    public Weather getCurrentWeather(String city, String country) {
	    	
	    	//check in db
	    	Weather weather= repo.findByCityAndCountry(city, country);
	    	if(weather!=null)
	    		return weather;
	    	
	        URI url = new UriTemplate(WEATHER_URL).expand(city, country, API_KEY);
	        ResponseEntity<String> response = rt.getForEntity(url, String.class);

	        weather = convert(response);
	        weather.setCity(city);
	        weather.setCountry(country);
	        repo.save(weather);
	        return weather;
	    }
	    
	    
	    
	    
	    private Weather convert(ResponseEntity<String> response) {
	        try {
	        	ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode root = objectMapper.readTree(response.getBody());
	            return new Weather(
	            		root.path("weather").get(0).path("main").asText(),
	                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
	                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
	                    BigDecimal.valueOf(root.path("wind").path("speed").asDouble()));
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException("Error parsing JSON", e);
	        }
	    }
}
