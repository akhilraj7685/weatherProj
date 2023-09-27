package com.ak.wth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<Weather, Integer> {
	
	
	
	
	
	Weather findByCityAndCountry(String city,String country);

}
