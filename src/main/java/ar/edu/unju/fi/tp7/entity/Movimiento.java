package ar.edu.unju.fi.tp7.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate fechaMovimiento;
	
	@ManyToOne()
	@JoinColumn(name = "id_cuenta")
	private CuentaBancaria cuentaBancaria;
	
	@Column
	private String tipo;
	
	@Column
	private Double importe;

	public Movimiento() {
		// TODO Auto-generated constructor stub
	}
	
	public Movimiento(LocalDate fechaMovimiento, CuentaBancaria cuentaBancaria, String tipo, Double importe) {
//		super();
		this.fechaMovimiento = fechaMovimiento;
		this.cuentaBancaria = cuentaBancaria;
		this.tipo = tipo;
		this.importe = importe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDate fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	
}
