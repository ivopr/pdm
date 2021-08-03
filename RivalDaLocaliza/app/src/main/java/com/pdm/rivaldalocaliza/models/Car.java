package com.pdm.rivaldalocaliza.models;

import java.util.List;

import lombok.Getter;

@Getter
public class Car {
	Category category;
	List<Specification> specifications;
	String id, name, description, daily_rate, license_plate, fine_amount, brand;
}
