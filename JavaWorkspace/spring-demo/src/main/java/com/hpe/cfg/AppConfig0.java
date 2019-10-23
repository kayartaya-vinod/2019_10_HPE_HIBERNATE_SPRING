package com.hpe.cfg;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hpe.service.GreetingService;
import com.hpe.service.Message;

@Configuration
public class AppConfig0 {

	@Bean(name="msg1")
	public Message message2() {
		return new Message("Hello, world", "Kumar");
	}

	@Bean(name="msg1")
	public Message message1() {
		return new Message("Hello, world", "Vinod");
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public GreetingService service() {
		return new GreetingService();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig0.class);
		GreetingService service = ctx.getBean(GreetingService.class);
		service.greet();
		
		ctx.close();
	}

}
