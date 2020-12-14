package com.college.strategy.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.college.strategy.entities.Category;
import com.college.strategy.entities.Order;
import com.college.strategy.entities.OrderItem;
import com.college.strategy.entities.Payment;
import com.college.strategy.entities.Product;
import com.college.strategy.entities.User;
import com.college.strategy.entities.enums.OrderStatus;
import com.college.strategy.repositories.CategoryRepository;
import com.college.strategy.repositories.OrderItemRepository;
import com.college.strategy.repositories.OrderRepository;
import com.college.strategy.repositories.ProductRepository;
import com.college.strategy.repositories.UserRepository;

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
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "JavaScript Beginner", "Lorem ipsum dolor sit amet, consectetur." , 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Lorem ipsum dolor sit amet, consectetur." , 2190.0, "");
		Product p3 = new Product(null, "Dell G5 Pro", "Lorem ipsum dolor sit amet, consectetur." , 1250.5, "");
		Product p4 = new Product(null, "Dell G5", "Lorem ipsum dolor sit amet, consectetur." , 1000.55, "");
		Product p5 = new Product(null, "Java for Dummies", "Lorem ipsum dolor sit amet, consectetur." , 100.55, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);		
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);	
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.SHIPPED, u1);
				
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));		
		
		Payment pay1 =  new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}	
	
}
