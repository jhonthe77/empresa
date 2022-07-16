/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas11.clases;

import java.util.ArrayList;

/**
 *
 * @author samur
 */
public abstract class absAgenciaEmpleo{
    // Atributos    
    private String hojaVida;
    ArrayList<String> competencias = new ArrayList<>();
    
    // Métodos
    
    // Setter y Getter
    /**
     * @return the hojaVida
     */
    public String getHojaVida() {
        return hojaVida;
    }

    /**
     * @param hojaVida the hojaVida to set
     */
    public void setHojaVida(String hojaVida) {
        this.hojaVida = hojaVida;
    }
    
    // propios de la clase
    public String PostularseVacante(){
        return "La persona se postuló a una vacante";
    }
    
    public ArrayList<String> RegistrarCompetencia(String competencia){
        this.competencias.add(competencia);
        return competencias;
    }
    
    public abstract String GenerarHojaVida();
}
