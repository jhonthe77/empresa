/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

public class clsGerente extends clsEmpleado {
public clsGerente() {
	// TODO Auto-generated constructor stub
}	

	private String tarjetaProfesional;

	public clsGerente(String oficioProfesion, String tarjetaProfesional, String tipoEmpleado, String nombre,
			String primerApellido, String segundoApellido, String identificacion) {
		super(oficioProfesion, nombre, primerApellido, segundoApellido, identificacion);
		this.tarjetaProfesional = tarjetaProfesional;

	}

	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}

	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}

	// Métodos propios
	public void ProgramaProduccion() {
		System.out.println("Se programó la producción");
	}

	public void ProgramarServicios() {
		System.out.println("Se programaron los servicios");
	}

	@Override
	public String HacerProducto() {

		return "produto";
	}

	@Override
	public void PrestarServicio() {

	}

	public float CalcularSalario(int horasExtras, int viaticos) {
		int salarioMinimo = 1000000;
		float bonificacion = 1000000 / 30 / 8 * 2.2f;
		float salario = 0.0f;
		salario = 2 * salarioMinimo + horasExtras * bonificacion + 24 * viaticos * bonificacion;
		return salario;
	}

}
