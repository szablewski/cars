package bartosz.szablewski.cars.service;

import bartosz.szablewski.cars.repository.CarRepository;
import bartosz.szablewski.cars.util.Mapper;
import bartosz.szablewski.cars.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(MultipartFile file) {
        try {
            List<Car> cars = Mapper.carsMapper(file.getInputStream());
            carRepository.saveAll(cars);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
