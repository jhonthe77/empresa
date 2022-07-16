/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

import empresas11.Interfaces.ICamaraComercio;
import empresas11.Interfaces.ISociedadEmpresarial;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author samur
 */
public class clsEmpresa implements ICamaraComercio, ISociedadEmpresarial{
    // Atributos
    private String nombreComercial;
    private String direccion;
    private clsGerente gerente;
    ArrayList<clsEmpleado> empleados = new ArrayList<>();
    
    // Método
    // Constructor

    public clsEmpresa(String nombreComercial, String direccion, clsGerente gerente) {
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.gerente = gerente;
    }

    /**
     * @return the nombreComercial
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the gerente
     */
    public clsGerente getGerente() {
        return gerente;
    }

    /**
     * @param gerente the gerente to set
     */
    public void setGerente(clsGerente gerente) {
        this.gerente = gerente;
    }
    
    // Métodos propios
    
    public ArrayList<clsEmpleado> ContratarEmpleados(clsEmpleado nuevoEmpleado){
        empleados.add(nuevoEmpleado);
        return empleados;
    }
    
    public ArrayList<clsEmpleado> DespedirEmpleados(clsEmpleado empleadoDespedido){
        empleados.remove(empleadoDespedido);
        return  empleados;
    }
    
    public ArrayList<clsEmpleado> ConsultarEmpleados(){
        return empleados;
    }

    @Override
    public String DefinirRegistro() {
        Random numeroAleatorio = new Random();
        int valor = numeroAleatorio.nextInt(100000);
        return String.valueOf(valor);
    }

    @Override
    public float CalcularImpuestoMercantil(float ingresos) {
        return ingresos *0.1f;
    }

    @Override
    public void AfiliarEmpresa() {
        System.out.println("La empresa " + this.nombreComercial + " ha sido afiliada a la sociedad.");
    }  
}
