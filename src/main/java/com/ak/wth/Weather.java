package com.ak.wth;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Weather {

	@Id
	@GenericGenerator(name = "wthgen", strategy = "sequence")
	@GeneratedValue(generator = "wthgen")
	private int id;
	
	private String city;
	private String country;
	private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;
    
    
    public Weather(String description,BigDecimal temperature,BigDecimal feelsLike,BigDecimal windSpeed) {
    	this.description=description;
    	this.feelsLike=feelsLike;
    	this.temperature=temperature;
    	this.windSpeed=windSpeed;
    }
}
