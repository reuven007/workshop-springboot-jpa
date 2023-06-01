package com.educandoweb.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.curso.entities.Category;
import com.educandoweb.curso.entities.Order;
import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.repositories.CategoryRepository;
import com.educandoweb.curso.repositories.OrderRepository;
import com.educandoweb.curso.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Eletronics", "Artigos eletronicos");
		Category cat2 = new Category(null, "Books", "Livros gerais");
		Category cat3 = new Category(null, "Computers", "Pcs, Notebooks, Macs");
		Category cat4 = new Category(null, "Clothes", "Roupas em geral");
		Category cat5 = new Category(null, "Instruments", "Classicos, Cordas e Metais");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
	
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Fred Cardoso", "Fredimm@gmail.com", "98225-5525", "123456");
		
		Order o1 = new Order(null, Instant.parse("2023-05-20T20:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2023-05-20T19:53:07Z"), u2);
	
 
		
	
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		orderRepository.saveAll(Arrays.asList(o1,o2));

	}
}