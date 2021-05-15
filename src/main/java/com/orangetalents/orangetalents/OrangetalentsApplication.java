package com.orangetalents.orangetalents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.orangetalents.orangetalents.Models.Params;
import com.orangetalents.orangetalents.Repository.ParamsRepository;

@SpringBootApplication
public class OrangetalentsApplication implements CommandLineRunner {
	
	@Autowired
	private ParamsRepository params;
	
	public static void main(String[] args) {
		SpringApplication.run(OrangetalentsApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Params initParams = new Params(6,59);
		System.out.println(initParams);
		params.save(initParams);
	}
	
}
