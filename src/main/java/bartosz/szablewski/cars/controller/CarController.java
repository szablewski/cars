package bartosz.szablewski.cars.controller;

import bartosz.szablewski.cars.model.Car;
import bartosz.szablewski.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAll());
    }

    @PostMapping
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (carService.hasCSVFormat(file)) {
                List<Car> result = carService.save(file);
                return ResponseEntity.status(HttpStatus.CREATED).body((long) result.size());
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getStackTrace());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
    }

    @GetMapping(value = "/filter", params = "color")
    ResponseEntity<List<Car>> findAllCarsByColor(@RequestParam("color") String color) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAllCarsByColor(color));
    }
}