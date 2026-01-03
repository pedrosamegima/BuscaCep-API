package com.buscaCep.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BuscaCepResponse {

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    public BuscaCepResponse(String cep, String state, String city, String neighborhood, String street) {

		this.cep = cep;
		this.state = state;
		this.city = city;
		this.neighborhood = neighborhood;
		this.street = street;
	}
    public String getCep() { return cep; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getNeighborhood() { return neighborhood; }
    public String getStreet() { return street; }
	
}