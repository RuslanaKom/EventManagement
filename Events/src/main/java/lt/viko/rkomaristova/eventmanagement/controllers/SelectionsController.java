package lt.viko.rkomaristova.eventmanagement.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lt.viko.rkomaristova.eventmanagement.enums.Cities;
import lt.viko.rkomaristova.eventmanagement.enums.EventCategories;

@RestController
@RequestMapping("/selections")
public class SelectionsController {
	
	@GetMapping(value = "/cities", produces = "application/json")
	public  @ResponseBody List<String> getCities() {
		List<String> result = Arrays.asList(Cities.values()).stream().map(c->c.getValue()).collect(Collectors.toList());
		return result;
	}

	@GetMapping(value = "/categories", produces = "application/json")
	public  @ResponseBody List<String> getCategories() {
		return Arrays.asList(EventCategories.values()).stream().map(c->c.getValue()).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/free", produces = "application/json")
	public  @ResponseBody List<String> getFree() {
		return Arrays.asList("Mokami", "Nemokami");
	}
	
	@GetMapping(value = "/date", produces = "application/json")
	public  @ResponseBody List<String> getDate() {
		return Arrays.asList("Busimi renginiai", "Buvę renginiai");
	}
}
