package com.nicolasretamar.entrevista.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasretamar.entrevista.payload.AgregarProductoAlCarritoPayload;
import com.nicolasretamar.entrevista.payload.CarritoPayload;
import com.nicolasretamar.entrevista.payload.ProductoPayload;
import com.nicolasretamar.entrevista.services.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {
	
	@Autowired
	CarritoService carritoService;
	
	@PostMapping("/{dni}")
	public Long altaCarrito(@PathVariable int dni) {
		return carritoService.altaCarrito(dni);
	}
	
	@DeleteMapping("/{idcarrito}")
	public void eliminarUnCarrito(@PathVariable long idcarrito) {
		carritoService.eliminarUnCarrito(idcarrito);
	}
	
	@PostMapping("/agregar")
	public CarritoPayload agregarProductoAlCarrito(@RequestBody AgregarProductoAlCarritoPayload p) {
		System.out.println(p.getIdCarrito() + " "+ p.getIdProducto());
		return carritoService.agregarProductoAlCarrito(p.getIdProducto(), p.getIdCarrito());
	}
	
	@DeleteMapping("/producto")
	public CarritoPayload eliminarProductoDelCarrito(@RequestBody AgregarProductoAlCarritoPayload p) {
		return carritoService.eliminarProductoDelCarrito(p.getIdProducto(), p.getIdCarrito());
	}
	
	@GetMapping("/productosmascaros/{dni}")
	public List<ProductoPayload> obtenerCuatroProductosMasCaros(@PathVariable int dni) {
		return carritoService.obtenerCuatroProductosMasCaros(dni);
	}
	
	
	//Testing
	
	@GetMapping("/ejemplo")
	public AgregarProductoAlCarritoPayload ejemploAltaProductoACarrito() {
		return new AgregarProductoAlCarritoPayload(Long.valueOf(1), Long.valueOf(123));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
