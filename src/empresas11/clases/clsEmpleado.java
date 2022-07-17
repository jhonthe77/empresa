/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

public class clsEmpleado extends clsPersona {
	
	

	protected String oficioProfesion;
	private String tipoEmpleado;

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public clsEmpleado(String oficioProfesion, String nombre, String primerApellido, String segundoApellido,
			String identificacion) {
		super(nombre, primerApellido, segundoApellido, identificacion);
		this.oficioProfesion = oficioProfesion;
		

	}

	public clsEmpleado() {
		
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public String getOficioProfesion() {
		return oficioProfesion;
	}

	public void setOficioProfesion(String oficioProfesion) {
		this.oficioProfesion = oficioProfesion;
	}

	public String HacerProducto() {
		System.out.println(this.nombre + ", quien es: " + this.oficioProfesion + ", elaboró un producto");
		return "produto";
	}

	@Override
	public void PrestarServicio() {
		System.out.println(this.nombre + ", quien es: " + this.oficioProfesion + ", está prestando el servicio");
		;
	}

}
