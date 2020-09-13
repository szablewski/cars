package bartosz.szablewski.cars.service;

import bartosz.szablewski.cars.model.Car;
import bartosz.szablewski.cars.repository.CarRepository;
import bartosz.szablewski.cars.util.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CarService {
    private static final String TYPE = "text/csv";
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public List<Car> save(MultipartFile file) {
        List<Car> result;
        try {
            List<Car> cars = Mapper.carsMapper(file.getInputStream());
            result = carRepository.saveAll(cars);

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        return result;
    }

    public List<Car> findAllCarsByColor(String color) {
        return carRepository.findAllCarsByColor(color);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}