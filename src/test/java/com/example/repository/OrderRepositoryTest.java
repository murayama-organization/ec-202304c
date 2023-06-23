package com.example.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Order;

@SpringBootTest
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindByUserIdAnd() {
		Order order = orderRepository.findByUserIdAndStatus(1111, 0);
		Order orderAnswer = new Order();
		orderAnswer.setId(2);
		System.out.println(order);
	}

}
