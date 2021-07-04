
package util;

import java.util.ArrayList;
import java.util.List;

public class AnalizarRegistro {


	protected PersonaFisica personaAnalizada;
	protected PersonaFisica personaComprobada;
	protected List<ResultadoAlgoritmo> algoritmos;
	protected List<String> datosAdicionales;
	protected String condicion;
	protected String error;
	

	public AnalizarRegistro(){
		inicializar();
	}
		
	private void inicializar() {
		personaAnalizada = new PersonaFisica();
		personaComprobada = new PersonaFisica();
		error="NN";
		algoritmos = new ArrayList<ResultadoAlgoritmo>();
		
		condicion = "";
	}
	
	public AnalizarRegistro(PersonaFisica p1, PersonaFisica p2, String cond){
		borrar_registro();
		inicializar(p1, p2, cond);
	}
		
	private void inicializar(PersonaFisica p1, PersonaFisica p2, String cond) {
		
		inicializar();
		
		setPersonaAnalizada(p1);
		setPersonaComprobada(p2);
		
		condicion = cond;
	}
	
	private void borrar_registro() {
		personaAnalizada = null;
		personaComprobada = null;
		algoritmos = null;
	}
	
	
    public PersonaFisica getPersonaAnalizada() {
        return personaAnalizada;
    }

    public void setPersonaAnalizada(PersonaFisica _personaAnalizada) {
    	personaAnalizada = _personaAnalizada;
    }
    
    public PersonaFisica getPersonaComprobada() {
        return personaComprobada;
    }

    public void setPersonaComprobada(PersonaFisica _personaComprobada) {
    	personaComprobada = _personaComprobada;
    }   
    
    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String _condicion) {
    	condicion = _condicion;
    }
    
    public String getError() {
        return error;
    }

    public void setError(String _error) {
    	error = _error;
    }
	
    public List<ResultadoAlgoritmo> getAlgoritmos() {
        return algoritmos;
    }

    public void setAlgoritmos(List<ResultadoAlgoritmo> _algoritmos) {
    	algoritmos = _algoritmos;
    }
    
    public List<String> getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(List<String> _datosAdicionales) {
    	datosAdicionales = _datosAdicionales;
    }
 
}

