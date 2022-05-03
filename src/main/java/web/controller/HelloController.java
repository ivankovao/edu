package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@Autowired
	CarService carService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

	/**
	 * Метод создает таблицу с машинами.
	 * Возвращает количество машин, равное числу, указанному в строке запроса
	 * Возвращает весь список машин, если в строке указано больше чем есть в списке машин
	 * Возвращает весь список машин
	 *
	 * @param count содержит количество машин
	 * @param model содержит объект модели
	 * @return представление
	 */
	@GetMapping(value = "/cars")
	public String showCountCars(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {
		List<Car> carList = carService.createCarList();
		if (count == null) {
			model.addAttribute("cars", carList);
		} else {
			List<Car> newListCars = carService.findCountCars(carList, count);
			model.addAttribute("cars", newListCars);
		}
		return "/cars";
	}
}