package me.dio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.model.Order;
import me.dio.service.OrderService;

@RestController()
@RequestMapping("/order")
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {		
		
		this.orderService = orderService; 
	}
	
	
	@PostMapping
	private ResponseEntity<Order> create(@RequestBody Order order) throws Exception{
		
		Order orderCreated = orderService.create(order);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(orderCreated.getId())
	                .toUri();
		
		return ResponseEntity.created(location).body(orderCreated);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Order> findById(@PathVariable(value = "id") Long id){
		
		Order order = orderService.findById(id);
		
		return ResponseEntity.ok().body(order);
	}
	
	@GetMapping
	private ResponseEntity<List<Order>> findByAll(){
		
		List<Order> òrderList = orderService.findAll();
		
		return ResponseEntity.ok().body(òrderList);
	}

}
