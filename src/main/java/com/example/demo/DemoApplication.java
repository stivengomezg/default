package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@RestController
public class DemoApplication {
	@Autowired
	private EmailService emailService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC-5"));
	}
	@RequestMapping("/")
	public String hola() {
		return "Hola mundo" ;
	}

	@RequestMapping("/prueba")
	@EventListener(ApplicationReadyEvent.class)
	public String minutos(){


		int numeroInicialS=0,segundoNumeroS=0;
		var fecha= new Date();
		int minutos=fecha.getMinutes();
		int segundos= fecha.getSeconds();
		int tamaS=segundos+2;
		int serie[];
		serie = new int[tamaS];


		if (minutos<10){
			numeroInicialS=0;
			segundoNumeroS=minutos ;
			serie[0]=numeroInicialS;
			serie[1]=segundoNumeroS;
			for (int i=2;i<tamaS;i+=1){
				serie[i]=serie[i-1]+serie[i-2];

			}
			emailService.enviarEmail("estigo98@gmail.com","prueba","la hora es"+" "+fecha+" "+"por lo tanto la serie es"+" "+(Arrays.toString(serie)) );
			return "La Serie creada es" + " "+ (Arrays.toString(serie));


		}
		else {
			numeroInicialS=minutos/10 ;
			segundoNumeroS=minutos%10 ;
			serie[0]=numeroInicialS;
			serie[1]=segundoNumeroS;
			for (int i=2;i<tamaS;i=i+1){
				int dato=serie[i-1]+serie[i-2];
				serie[i]=dato;
			}
			emailService.enviarEmail("estigo98@gmail.com","prueba","la hora es"+" "+fecha+" "+"por lo tanto la serie es"+" "+(Arrays.toString(serie)) );

			return "La Serie creada es" + " "+ (Arrays.toString(serie));		}

	}

}