package com.wordtopdf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wordtopdf.service.MyService;

@RestController
@RequestMapping("/api/word-to-pdf-converter")
public class MyController {

	@Autowired
	MyService service;
	
	Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@PostMapping(path = "/", produces = "application/json")
	public ResponseEntity<byte[]> getPdf(@RequestParam("file") MultipartFile file){
		logger.info("getPdf method running");
		return service.convertToPdf(file);
	}
	
	
	@GetMapping("/write")
	public ResponseEntity<?> getString(){
		return new ResponseEntity<>("It is running", HttpStatus.OK);
	}
}
