package util;

import java.util.ArrayList;
import java.util.List;

public class ResultadoPersona {

	
	
	private static final long serialVersionUID = 2L;

	protected PersonaFisica personaAnalizada;
	
	//protected PersonaFisica personaPropuesta;
	
	protected PersonaFisica personaComprobada;
	
	protected List<String> listaAlgoritmos;
	
	protected List<CalculoSimilitud> resultadosDistancias;
	
	protected List<Integer> idCandidatosPropuestosPorAlg; // candidatos propuestos por cada algoritmo
	
	protected List<Long> tiemposPorAlg; // candidatos propuestos por cada algoritmo
	
	protected String condiciones;
	

	public ResultadoPersona(){
		inicializar();
	}
		
	private void inicializar() {
		personaAnalizada = new PersonaFisica();
		personaComprobada = new PersonaFisica();
		
		listaAlgoritmos = new ArrayList<String>();
		resultadosDistancias = new ArrayList<CalculoSimilitud>();
		
		idCandidatosPropuestosPorAlg = new ArrayList<Integer>();
		tiemposPorAlg = new ArrayList<Long>();
	}
	
	
    public PersonaFisica getPersonaAnalizada() {
        return personaAnalizada;
    }

    public void setPersonaAnalizada(PersonaFisica _personaAnalizada) {
    	personaAnalizada = _personaAnalizada;
    }
    
    public List<Integer> getCandidatosPropuestosPorAlg() {
        return idCandidatosPropuestosPorAlg;
    }

    public void setCandidatosPropuestosPorAlg(List<Integer> _idCandidatosPropuestosPorAlg) {
    	idCandidatosPropuestosPorAlg = _idCandidatosPropuestosPorAlg;
    }
    
    public List<Long> getTiemposPorAlg() {
        return tiemposPorAlg;
    }

    public void setTiemposPorAlg(List<Long> _tiemposPorAlg) {
    	tiemposPorAlg = _tiemposPorAlg;
    }
    
    
    public PersonaFisica getPersonaComprobada() {
        return personaComprobada;
    }

    public void setPersonaComprobada(PersonaFisica _personaComprobada) {
    	personaComprobada = _personaComprobada;
    }
	
    public List<String> getListaAlgoritmos() {
        return listaAlgoritmos;
    }

    public void setListaAlgoritmos(List<String> _listaAlgoritmos) {
    	listaAlgoritmos = _listaAlgoritmos;
    }
    
    public List<CalculoSimilitud> getResultadosDistancias() {
        return resultadosDistancias;
    }

    public void setResultadosDistancias(List<CalculoSimilitud> _resultadosDistancias) {
    	resultadosDistancias = _resultadosDistancias;
    }
    
    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String _condiciones) {
    	condiciones = _condiciones;
    }
    
    public void calcularCandidatos() {
    	//recorro los algoritmos y busco en resultadosDistancias, el que menos tenga por cada uno de ellos.
    	
    	
    	String alg;
    	int candidatoId;
    	Long tiempo;
    	double similitud_mayor;
    	double similitud;
    	
    	for (int i=0; i<listaAlgoritmos.size(); i++) {
    		alg=listaAlgoritmos.get(i);
    		
    		//recorro los resultados Distancias
    		candidatoId=0;
    		tiempo=0L;
    		similitud=0; 
    		similitud_mayor=-1;// la inicializo muy bajo para que el primero ya se ponga para comparar
    		
    		for (int j=0; j<resultadosDistancias.size(); j++) {
    			
    			if (alg.equals(resultadosDistancias.get(j).algoritmo)){
    				
        			tiempo+= resultadosDistancias.get(j).tiempo;
        			similitud=resultadosDistancias.get(j).similitud;
        			if (similitud>similitud_mayor) {
        				similitud_mayor=similitud;
        				candidatoId=resultadosDistancias.get(j).identificador2;
        			}
        			
    			}
    		}

    		idCandidatosPropuestosPorAlg.add(candidatoId);
    		tiemposPorAlg.add(tiempo);
    		
    		
    	}
    	
    	
    }
    
    
}
