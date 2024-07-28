package me.dio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import me.dio.model.Client;
import me.dio.model.Order;
import me.dio.repository.ClientRepository;
import me.dio.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private ClientRepository clientRepository;


	public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {

		this.orderRepository = orderRepository;
		this.clientRepository = clientRepository;

	}

	public Order create(Order order) throws Exception {

		if (orderRepository.existsByOrderNumber(order.getOrderNumber())) {
			throw new IllegalArgumentException("This order number already exists.");

		}

		Client client = clientRepository.findById(order.getClient().getId()).orElseThrow(NoSuchElementException::new);

		BeanUtils.copyProperties(client, order.getClient());

		return orderRepository.save(order);
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
		
	}

	public List<Order> findAll() {
		List<Order> orderList = orderRepository.findAll();
		
		return orderList;
		
	}

}
