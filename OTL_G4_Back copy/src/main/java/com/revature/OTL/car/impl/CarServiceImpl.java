package com.revature.OTL.car.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.OTL.car.Car;
import com.revature.OTL.car.CarRepository;
import com.revature.OTL.car.CarService;
import com.revature.OTL.car.dto.CarRequestDto;
import com.revature.OTL.util.exceptions.DataNotFoundException;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(CarRequestDto carRequestDto) {
        return carRepository.save(CarRequestDto.dtoToEntity(carRequestDto));
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Nothing in the database with ID of " + id));
    }
           
    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car updateCar(int id, CarRequestDto requestDto) {
        int carId = carRepository.findById(id).orElseThrow(()->new DataNotFoundException("Cat not found")).getId();
        Car car = CarRequestDto.dtoToEntity(requestDto);
        car.setId(carId);
        return carRepository.save(car);
    }

}
