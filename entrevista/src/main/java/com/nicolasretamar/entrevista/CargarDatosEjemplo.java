package com.nicolasretamar.entrevista;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.nicolasretamar.entrevista.models.*;
import com.nicolasretamar.entrevista.repositories.*;
import com.nicolasretamar.entrevista.services.CarritoService;

//Cuando se ejecuta el proyecto, luego se ejecuta esta clase
//Esto es a modo testing, no se va a implementar en la versión final
@Component
public class CargarDatosEjemplo implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private FechaPromocionalRepository fechaPromocionalRepository;
	
	@Autowired
	private CarritoService carritoService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		Long cantidadUsuarios = userRepository.count();
		if(cantidadUsuarios == 0) {
			cargarUsers();
			cargarProductos();
			cargarFechasPromocionales();
			//cargarCarritos();
		}
		
	}

	private void cargarUsers() {
		User user = new User();
		user.setDni(123456789);
		user.setVip(true);
		userRepository.save(user);
		
		user = new User();
		user.setDni(987654321);
		user.setVip(false);
		userRepository.save(user);
	}
	
	private void cargarProductos() {
		Producto p = new Producto();
		p.setNombre("Leche");
		p.setPrecio(new BigDecimal(300));
		productoRepository.save(p);

		p = new Producto();
		p.setNombre("Galletitas");
		p.setPrecio(new BigDecimal(500));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("coca");
		p.setPrecio(new BigDecimal(600));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("fideos");
		p.setPrecio(new BigDecimal(1000));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("crema");
		p.setPrecio(new BigDecimal(400));
		productoRepository.save(p);
		
		
		p = new Producto();
		p.setNombre("celular");
		p.setPrecio(new BigDecimal(300000));
		productoRepository.save(p);

		p = new Producto();
		p.setNombre("mate");
		p.setPrecio(new BigDecimal(550));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("Sprite");
		p.setPrecio(new BigDecimal(610));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("tuco");
		p.setPrecio(new BigDecimal(440));
		productoRepository.save(p);
		

		p = new Producto();
		p.setNombre("yogurt");
		p.setPrecio(new BigDecimal(320));
		productoRepository.save(p);
		
		
	}
	
	private void cargarFechasPromocionales() {
		FechaPromocional fecha = new FechaPromocional();
		fecha.setFecha(LocalDate.of(2022, Month.OCTOBER, 13));
		fechaPromocionalRepository.save(fecha);
		
		fecha = new FechaPromocional();
		fecha.setFecha(LocalDate.of(2022, Month.OCTOBER, 16));
		fechaPromocionalRepository.save(fecha);
		
		fecha = new FechaPromocional();
		fecha.setFecha(LocalDate.of(2022, Month.DECEMBER, 24));
		fechaPromocionalRepository.save(fecha);
		
		fecha = new FechaPromocional();
		fecha.setFecha(LocalDate.of(2022, Month.DECEMBER, 31));
		fechaPromocionalRepository.save(fecha);
	}
	
	private void cargarCarritos() {
		carritoService.altaCarrito(123456789);
	}
	
	private void cargarProductosACarrito() {
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(1));
		
		carritoService.agregarProductoAlCarrito(Long.valueOf(1), Long.valueOf(2));
		carritoService.agregarProductoAlCarrito(Long.valueOf(2), Long.valueOf(2));
		carritoService.agregarProductoAlCarrito(Long.valueOf(3), Long.valueOf(2));
		carritoService.agregarProductoAlCarrito(Long.valueOf(4), Long.valueOf(2));
		carritoService.agregarProductoAlCarrito(Long.valueOf(5), Long.valueOf(2));
		
	}
	
	
	
}
