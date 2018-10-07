package com.piccy.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
	
	private static final String template = "Hello %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping
	public Dummy greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Dummy(counter.incrementAndGet(), String.format(template, name));
	}
	
	@RequestMapping
	public void saving(@RequestParam(value="name", defaultValue="World") String something) {
		
	}
	

}
