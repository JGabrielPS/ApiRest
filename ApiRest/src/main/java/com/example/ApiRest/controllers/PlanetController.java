package com.example.ApiRest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRest.dtos.PlanetDTO;
import com.example.ApiRest.excepctions.StatusException;
import com.example.ApiRest.services.PlanetService;

@Controller
@RestController
@RequestMapping(path = "api/v1/planets")
public class PlanetController implements ObjectController<PlanetDTO>{

	private PlanetService planetService;

	public PlanetController(PlanetService planetService) {
		this.planetService = planetService;
	}

	@Override
	@CrossOrigin("*")
	@GetMapping(path = "/")
	public List<PlanetDTO> getAll() {
		return ResponseEntity.status(200).body(planetService.getAll()).getBody();
	}

	@Override
	@GetMapping("/{id}")
	public PlanetDTO getOne(@PathVariable int id) {
		
		return ResponseEntity.status(200).body(planetService.getOne(id)).getBody();
	}

	@Override
	@PostMapping("/")
	public ResponseEntity save(@RequestBody PlanetDTO t) {
	
		PlanetDTO temp = planetService.save(t);
		
		try {
			
			if(temp.getId() != 0) {
				return ResponseEntity.status(201).body(temp);
			}else {
				throw new StatusException("Bad Request. Please check the values", 400);
			}
			
		} catch (StatusException e) {
			return e.getResponseStatus();
		}
		
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody PlanetDTO t,@PathVariable int id) {
		
		PlanetDTO temp = planetService.update(t, id);
		
		try {
			
			if(temp.getId() != 0) {
				return ResponseEntity.status(201).body(temp);
			}else {
				throw new StatusException("Bad Request. Please check the values", 400);
			}
			
		} catch (StatusException e) {
			return e.getResponseStatus();
		}
		
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		
		boolean det = planetService.delete(id);
		
		try {
			
			if(det) {
				return ResponseEntity.status(200).body("{\"Succesful\":\"Correctly removed.\"}");
			}else {
				throw new StatusException("Bad Request. Please check the id", 400);
			}
			
		} catch (StatusException e) {
			return e.getResponseStatus();
		}
		
	}
	
}
