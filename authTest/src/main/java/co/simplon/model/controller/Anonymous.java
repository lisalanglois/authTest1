package co.simplon.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anonymous")

public class Anonymous {

	@GetMapping("/ping")
	public String ping() {
		return "anonymous";
	}
}
