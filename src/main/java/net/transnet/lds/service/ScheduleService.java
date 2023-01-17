package net.transnet.lds.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
 * As long as the bean of the class instance that contains a scheduled method exists
 * The scheduler will ensure that the method gets executed.
 * 
 * Essentilly @Component below can be @Service, @Bean, @ e.t.c
 */
@Component
public class ScheduleService {
	//should print hello every 5000ms or 5s
	@Scheduled(fixedRate = 5000)
	public void printHello() {
		System.out.println("Hello, World!");
	}
}
