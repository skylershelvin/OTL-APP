package com.revature.OTL.car;

import com.revature.OTL.car.dto.CarRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car>postNewCar(@RequestBody CarRequestDto carRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(carRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/{id}")
    public  ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody CarRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(id,requestDto));
    }

}
