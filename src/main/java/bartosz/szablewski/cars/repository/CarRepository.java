package bartosz.szablewski.cars.repository;

import bartosz.szablewski.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllCarsByColor(String color);
}