package ar.edu.unju.fi.tp7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.tp7.entity.Cliente;
import ar.edu.unju.fi.tp7.entity.CuentaBancaria;
import ar.edu.unju.fi.tp7.entity.Movimiento;
import ar.edu.unju.fi.tp7.repository.ClienteRepository;
import ar.edu.unju.fi.tp7.repository.CuentaBancariaRepository;
import ar.edu.unju.fi.tp7.repository.MovientoRepository;

@DisplayName("TP7 - Pruebas Unitarias")
@SpringBootTest
class Tp7ApplicationTests {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CuentaBancariaRepository cuentaBancariaRepository;
	
	@Autowired
	MovientoRepository movimientoRepository;
	
	Cliente cliente = new Cliente("41265681", "Sofia Yañez", "sofi@gmail.com", "Tenerife 582", null);
	CuentaBancaria cuentaBancaria = new CuentaBancaria(1, LocalDate.now(), 80000d, true);
	CuentaBancaria cuentaBancaria1 = new CuentaBancaria(2, LocalDate.now(), 80000d, true);
	Cliente adherente = new Cliente("43634761", "Maximiliano Carrillo", "maxi@gmail.com", null, null);
	List<Cliente> adherentes = new ArrayList<Cliente>();
	
	@BeforeEach
	void setUp() {
		cliente.setCuentaBancaria(cuentaBancaria);
		cuentaBancaria.setCliente(cliente);
		adherente.setCuentaBancaria(cuentaBancaria1);
		cuentaBancaria1.setCliente(adherente);
		adherentes.add(adherente);
		
		cliente.setAdherentes(adherentes);
		
		clienteRepository.save(cliente);
		cuentaBancariaRepository.save(cuentaBancaria);
	}
	
	
	@AfterEach
	void setDown() {
		clienteRepository.deleteAll();
		movimientoRepository.deleteAll();
		cuentaBancariaRepository.deleteAll();
	}
	
	@DisplayName("CRUD - Create")
	@Test
	@Disabled
	void cargar() {
		assertEquals(clienteRepository.count(), 2);
		assertEquals(cuentaBancariaRepository.count(), 2);
		
	}
	
	@DisplayName("CRUD - Read")
	@Test
	@Disabled
	void leer() {
		assertEquals(clienteRepository.findByDni("41265681").getDni(), "41265681");
		assertEquals(clienteRepository.findByDni("43634761").getDni(), "43634761");
		assertEquals(clienteRepository.findByDni("43634761").getCuentaBancaria().getSaldoActual(), 80000d);
	}
	
	@DisplayName("CRUD - Update")
	@Test
	@Disabled
	void actualizar() {
		Cliente cliente = clienteRepository.findByDni("43634761");
		cliente.setDomicilio("Tenerife 582");
		cliente.getCuentaBancaria().setSaldoActual(80000d);
		clienteRepository.save(cliente);
		assertEquals(clienteRepository.findByDni("43634761").getCuentaBancaria().getSaldoActual(), 80000d);
		assertEquals(clienteRepository.findByDni("43634761").getDomicilio(), "Tenerife 582");
		cliente.setDomicilio("Gavazzini 119");
		cliente.getCuentaBancaria().setSaldoActual(100000d);
		clienteRepository.save(cliente);
		assertEquals(clienteRepository.findByDni("43634761").getDomicilio(), "Gavazzini 119");
		assertEquals(clienteRepository.findByDni("43634761").getCuentaBancaria().getSaldoActual(), 100000d);
		
		assertEquals(clienteRepository.count(), 2);
		assertEquals(cuentaBancariaRepository.count(), 2);
	}

	@DisplayName("CRUD - Delete")
	@Test
	@Disabled
	void borrar() {
		assertEquals(clienteRepository.count(), 2);
		
		Cliente david = new Cliente("41435647", "David Quint", "xeneize@gmail.com", "Boulogne 582", null);
		CuentaBancaria cuentaDavid = new CuentaBancaria(3, LocalDate.now(), 80000d, true);
		david.setCuentaBancaria(cuentaDavid);
		cuentaDavid.setCliente(david);
		
		clienteRepository.save(david);
		
		assertEquals(clienteRepository.findByDni("41435647").getNombre(), "David Quint");
		
		clienteRepository.delete(david);
		
		assertEquals(clienteRepository.count(), 2);
		assertEquals(cuentaBancariaRepository.count(), 2);
	}
	
	@DisplayName("Extraer")
	@Test
	@Disabled
	void extraerSaldo() {
		CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findByNumeroCuenta(2);
		
		Movimiento movimiento = cuentaBancaria.extraer(5000d);
		movimiento.setCuentaBancaria(cuentaBancaria);
		cuentaBancariaRepository.save(cuentaBancaria);
		movimientoRepository.save(movimiento);
		
		assertEquals(movimientoRepository.count(), 1);
		assertEquals(cuentaBancariaRepository.findByNumeroCuenta(2).getSaldoActual(),75000d);
		
	}
	
	@DisplayName("Ingresar")
	@Test
	void ingresarSaldo() {
		CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findByNumeroCuenta(1);
		cuentaBancaria.ingresar(5000d);
		Movimiento movimiento = new Movimiento(LocalDate.now(), cuentaBancaria, "Ingreso", 5000d);
		cuentaBancariaRepository.save(cuentaBancaria);
		movimientoRepository.save(movimiento);
		
		assertEquals(movimientoRepository.count(), 1);
		assertEquals(cuentaBancariaRepository.findByNumeroCuenta(1).getSaldoActual(),85000d);
		
		cuentaBancaria.ingresar(10000d);
		movimiento = new Movimiento(LocalDate.now(), cuentaBancaria, "Ingreso", 10000d);
		cuentaBancariaRepository.save(cuentaBancaria);
		movimientoRepository.save(movimiento);
		
		assertEquals(movimientoRepository.count(), 2);
		assertEquals(cuentaBancariaRepository.findByNumeroCuenta(1).getSaldoActual(),95000d);
	}
}
