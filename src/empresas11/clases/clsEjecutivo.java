/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

public class clsEjecutivo extends clsEmpleado {
public clsEjecutivo() {
	
}
	private String unidadACargo;

	public clsEjecutivo(String unidadACargo,String oficioProfesion, String nombre, String primerApellido, String segundoApellido,
			String identificacion) {
		super(nombre, primerApellido, segundoApellido, segundoApellido, identificacion);
		this.unidadACargo = unidadACargo;

	}

	public String getUnidadACargo() {
		return unidadACargo;
	}

	public void setUnidadACargo(String unidadACargo) {
		this.unidadACargo = unidadACargo;
	}

	// Métodos propios de la clase
	public void programarProduccion() {
		System.out.println("Se programó la producción semanal");
	}

	public void programarServicios() {
		System.out.println("Se programaron los servicios para la semana");
	}

	@Override
	public void PrestarServicio() {
		System.out.println(this.nombre + " está prestando el servicio de coordinador de unidad");
	}

	public float CalcularSalario(int viaticos) {
		float bonificacion = 1000000 / 30 / 8 * 2.2f;
		float salarioMinimo = 1000000;
		float salario = salarioMinimo + viaticos * bonificacion;
		return salario;
	}

	public float CalcularSalario(int viaticos, int horasExtras) {
		float bonificacionH = 1000000 / 30 / 8 * 1.5f;
		float bonificacionV = 1000000 / 30 / 8 * 2.2f;
		float salarioMinimo = 1000000;
		float salario = salarioMinimo + horasExtras * bonificacionH + viaticos * bonificacionV;
		return salario;
	}

	public void AsociarEmpleado(clsEmpleado empleado) {

	}

	public void DesAsociarEmpleado(clsEmpleado empleado) {

	}
}
