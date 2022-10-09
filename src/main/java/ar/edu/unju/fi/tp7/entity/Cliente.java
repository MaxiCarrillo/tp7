package ar.edu.unju.fi.tp7.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String dni;
	@Column
	private String nombre;
	@Column
	private String email;
	@Column
	private String domicilio;
	@Column
	private boolean estado=true;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cliente> adherentes = null;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cuenta")
	private CuentaBancaria cuentaBancaria;
	
//	private static Integer nextId = 0;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String dni, String nombre, String email, String domicilio, CuentaBancaria cuentaBancaria) {
//		super();
//		nextId++;
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.domicilio = domicilio;
		this.estado = true;
		this.cuentaBancaria = cuentaBancaria;
		adherentes = new ArrayList<Cliente>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Cliente> getAdherentes() {
		return adherentes;
	}

	public void setAdherentes(List<Cliente> adherentes) {
		this.adherentes = adherentes;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	} 
	
}
