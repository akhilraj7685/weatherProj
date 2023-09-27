package com.ak.wth;

import java.math.BigDecimal;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WeatherController {

	
	@Autowired
	private WeatherService weatherService;
	
	
	@GetMapping("current-weather")
    public Weather getCurrentWeather(@PathParam("city")String city,@PathParam("country")String country) {
		return weatherService.getCurrentWeather(city, country);    
    }
	
}
