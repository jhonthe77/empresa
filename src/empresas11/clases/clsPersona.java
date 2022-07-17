/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

public class clsPersona extends absAgenciaEmpleo {
	
	public clsPersona() {
		
	}

	protected String nombre;
	protected String primerApellido;
	protected String segundoApellido;
	protected String identificacion;
	protected int edad;

	

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public clsPersona(String nombre, String primerApellido, String segundoApellido, String identificacion) {
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String HacerProducto() {
		System.out.println(this.nombre + " elaboró un producto");
		return "produto";
	}

	public void PrestarServicio() {
		System.out.println(this.nombre + " está prestando el servicio");
		;
	}

	public int CalcularSalario() {
		int salarioMinimo = 1000000;
		return salarioMinimo;
	}

	@Override
	public String GenerarHojaVida() {
	
		return null;
	}

}
