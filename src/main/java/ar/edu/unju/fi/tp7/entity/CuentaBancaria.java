package ar.edu.unju.fi.tp7.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CuentaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Integer numeroCuenta;
	
	@OneToOne(mappedBy = "cuentaBancaria")
	private Cliente cliente;
	
	@Column
	private LocalDate fechaIngreso;
	@Column
	private Double saldoActual;
	@Column
	private boolean estado;
	
	@OneToMany(mappedBy = "cuentaBancaria", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Movimiento> movimientos;
	
	private static Double LIMITE=10000d;
	
	public CuentaBancaria() {
		// TODO Auto-generated constructor stub
	}
	
	public CuentaBancaria(Integer numeroCuenta, LocalDate fechaIngreso, Double saldoActual,
			boolean estado) {
//		super();
		this.numeroCuenta = numeroCuenta;
		this.fechaIngreso = fechaIngreso;
		this.saldoActual = saldoActual;
		this.estado = estado;
		movimientos = new ArrayList<Movimiento>();
	}

	public Movimiento extraer(Double saldo) {
		if(saldo<=LIMITE) {
			this.saldoActual=saldoActual-saldo;
			return new Movimiento(LocalDate.now(), null, "Extraccion", saldo);
			
		}
		return null;
	}
	
	public void ingresar(Double saldo) {
		this.saldoActual=saldoActual+saldo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public static Double getLIMITE() {
		return LIMITE;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
}
