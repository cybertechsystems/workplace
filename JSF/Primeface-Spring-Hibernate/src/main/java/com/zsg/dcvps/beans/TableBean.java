package com.zsg.dcvps.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import com.zsg.dcvps.entity.Car;

@Component("tableBean")
@RequestScoped
public class TableBean implements Serializable {
	private final static String[] colors;

	private final static String[] manufacturers;

	private List<Car> carsSmall;
	
	private Car selectedCar;

	public TableBean() {
		carsSmall = new ArrayList<Car>();
		
		carsSmall = populateRandomCars(carsSmall, 2);
	}
    static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";

		manufacturers = new String[10];
		manufacturers[0] = "Mercedes";
		manufacturers[1] = "BMW";
		manufacturers[2] = "Volvo";
		manufacturers[3] = "Audi";
		manufacturers[4] = "Renault";
		manufacturers[5] = "Opel";
		manufacturers[6] = "Volkswagen";
		manufacturers[7] = "Chrysler";
		manufacturers[8] = "Ferrari";
		manufacturers[9] = "Ford";
	}

    private List<Car> populateRandomCars(List<Car> carsSmall, int size) {
		for(int i = 0 ; i < size ; i++) {
			carsSmall.add(new Car("58001", "2013", manufacturers[0], colors[0]));
			carsSmall.add(new Car("58002", "2013", manufacturers[1], colors[1]));
			carsSmall.add(new Car("58003", "2013", manufacturers[2], colors[2]));
			carsSmall.add(new Car("58004", "2013", manufacturers[3], colors[3]));
			carsSmall.add(new Car("58005", "2013", manufacturers[4], colors[4]));
			carsSmall.add(new Car("58006", "2013", manufacturers[5], colors[5]));
			carsSmall.add(new Car("58007", "2013", manufacturers[6], colors[6]));
		}
		return carsSmall;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

    public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

    public List<Car> getCarsSmall() {
        return this.carsSmall;
    }
}
                    