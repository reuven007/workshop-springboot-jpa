package com.educandoweb.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.curso.entities.Category;
import com.educandoweb.curso.entities.Order;
import com.educandoweb.curso.entities.OrderItem;
import com.educandoweb.curso.entities.Payment;
import com.educandoweb.curso.entities.Product;
import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.repositories.CategoryRepository;
import com.educandoweb.curso.repositories.OrderItemRepository;
import com.educandoweb.curso.repositories.OrderRepository;
import com.educandoweb.curso.repositories.ProductRepository;
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
	
	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired	
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Eletronics", "Artigos eletronicos");
		Category cat2 = new Category(null, "Books", "Livros gerais");
		Category cat3 = new Category(null, "Computers", "Pcs, Notebooks, Macs");
		Category cat4 = new Category(null, "Clothes", "Roupas em geral");
		Category cat5 = new Category(null, "Instruments", "Classicos, Cordas e Metais");
		
		Product p1 = new Product(null,"Violino", 1200.00, "instrumento classico", "");
		Product p2 = new Product(null,"Mouse", 200.00, "Hardware", "");
		Product p3 = new Product(null,"Esteira", 500.00, "Eletronics", "");
		Product p4 = new Product(null,"Regata", 50.00, "Clothes", "");
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		productRepository.saveAll(Arrays.asList(p1,p2));
		
		
		//produtos e categorias juntos
		p1.getCategories().add(cat5);
		p2.getCategories().add(cat3);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat1);
		p4.getCategories().add(cat4);
		productRepository.saveAll(Arrays.asList(p1,p2));
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Fred Cardoso", "Fredimm@gmail.com", "98225-5525", "123456");
		
		Order o1 = new Order(null, Instant.parse("2023-05-20T20:53:07Z"), u1) ;
		Order o2 = new Order(null, Instant.parse("2023-05-20T19:53:07Z"), u2);
	
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p2, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p2, 2, p2.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
		
		Payment pay1 = new Payment(null,Instant.parse("2023-05-20T22:53:07Z"), o1 );
		o1.setPayment(pay1);
		
		orderRepository.save(o2);
	}
}