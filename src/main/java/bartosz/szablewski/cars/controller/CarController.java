package bartosz.szablewski.cars.controller;

import bartosz.szablewski.cars.model.Car;
import bartosz.szablewski.cars.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String showIndexPage(Model model) {
        model.addAttribute("car", new Car());
        return "index";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        try {
            carService.save(file);
            model.addAttribute("project", new Car());
            model.addAttribute("message", "Uploaded the file successfully: " + file.getOriginalFilename());
            return "index";
        } catch (Exception e) {
            model.addAttribute("message", "Could not upload the file: " + file.getOriginalFilename() + "!");
            return "index";
        }
    }
}
