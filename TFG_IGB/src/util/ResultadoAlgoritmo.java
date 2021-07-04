package util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ResultadoAlgoritmo {

	
	protected String Nombre;
	protected Long Tiempo;
	protected int IdCandidatoPropuesto;
	protected double SimilitudCandidatoPropuesto;
	protected int Acierto;
	protected String Condicion;
	protected List<CalculoSimilitud> resultadosSimilitudes;
	protected List<String> datosAdicionales;
	protected CalculoSimilitud calculo;
	
	public ResultadoAlgoritmo(String nom, String cond, List<String> datosAdicionales){
		inicializar(nom, cond, datosAdicionales);
	}
	
	public ResultadoAlgoritmo(){
	}
	
	public void inicializar(String nom, String cond, List<String> secundarios) {
		setNombre(nom);
		setIdCandidatoPropuesto(-1);
		setCondicion(cond);
		setTiempo(0L);
		setAcierto(0);
		resultadosSimilitudes = new ArrayList<CalculoSimilitud>();
		datosAdicionales=new ArrayList<String>();
		setDatosAdicionales(secundarios);
		
	}
	
	
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String _Nombre) {
    	Nombre = _Nombre;
    }
    
    public Integer getIdCandidatoPropuesto() {
        return IdCandidatoPropuesto;
    }

    public void setIdCandidatoPropuesto( Integer _idCandidatoPropuesto) {
    	IdCandidatoPropuesto = _idCandidatoPropuesto;
    }
    
    public Double getSimilitudCandidatoPropuesto() {
        return SimilitudCandidatoPropuesto;
    }

    public void setSimilitudCandidatoPropuesto( Double _similitudCandidatoPropuesto) {
    	SimilitudCandidatoPropuesto = _similitudCandidatoPropuesto;
    }
    
    public Long getTiempo() {
        return Tiempo;
    }

    public void setTiempo(Long _tiempo) {
    	Tiempo = _tiempo;
    }
    

    
    public List<CalculoSimilitud> getresultadosSimilitudes() {
        return resultadosSimilitudes;
    }

    public void setresultadosSimilitudes(List<CalculoSimilitud> _resultadosSimilitudes) {
    	resultadosSimilitudes = _resultadosSimilitudes;
    }
    
    public List<String> getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(List<String> _datosAdicionales) {
    	datosAdicionales = _datosAdicionales;
    }
    
    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String _condicion) {
    	Condicion = _condicion;
    }
    
    public int getAcierto() {
        return Acierto;
    }

    public void setAcierto(int _acierto) {
    	Acierto = _acierto;
    }
    
    public void calcularSimilitudes(PersonaFisica p1, PersonaFisica p2) {
    	
    	/// ESTE LO PUEDO MEJORAR
		calculo=new CalculoSimilitud(p1, p2, Condicion, Nombre, datosAdicionales);
    	resultadosSimilitudes.add(calculo);
    	
    	//liberar el espacio de calculo
    	calculo=null;
    	
    }
    
    public void calcularCandidatoYTiempo(int id) {
    	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
    	
    	
    	int candidatoId;
    	Long tiempo;
    	double similitud_mayor;
    	double similitud;
    	
		
		//recorro los resultados Distancias
		candidatoId=0;
		tiempo=0L;
		similitud=0; 
		similitud_mayor=-1;// la inicializo muy bajo para que el primero ya se ponga para comparar
		for (int j=0; j<resultadosSimilitudes.size(); j++) {
			tiempo+= resultadosSimilitudes.get(j).tiempo;
			similitud=resultadosSimilitudes.get(j).similitud;
			if (similitud>similitud_mayor) {
				similitud_mayor=similitud;
				candidatoId=resultadosSimilitudes.get(j).identificador2;
			}
		}

		IdCandidatoPropuesto=candidatoId;
		
		SimilitudCandidatoPropuesto=similitud_mayor;
		
		if (candidatoId==id) {
			Acierto=1;
		}else {
			Acierto=0;
		}
		
		Tiempo=tiempo;

    	
    	
    }
    
    
}
