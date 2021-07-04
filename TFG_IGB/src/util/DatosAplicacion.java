package util;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JTable;


public class DatosAplicacion {

	//Atributos de la clase
	private JTable tDatos;
	private JTable tComprobacion;
	private JTable tResultados;
	
	private List<String> listaAlgoritmos;
	private List<Double> listaAlgoritmosTiempos;
	private List<Double> listaAlgoritmosPorcentajeAcierto;
	private List<String> listaSecundarios;
	
	private String tipoRegistros;
	
	private String tipoCondicion;
	
	private Boolean normalizar;
	private Boolean soloErroneos;
	
	public DatosAplicacion(){
		inicializar();
	}
	
	private void inicializar() {
		// TODO Auto-generated method stub
		tDatos= new JTable();
		tComprobacion = new JTable();
		tResultados = new JTable();
		listaAlgoritmos= new ArrayList<String>();
		listaAlgoritmosTiempos= new ArrayList<Double>();
		listaAlgoritmosPorcentajeAcierto= new ArrayList<Double>();
		listaSecundarios= new ArrayList<String>();
		tipoRegistros= new String();
		tipoCondicion=new String();
	}

	public void setTOrigen(JTable tabla) {
		tDatos=tabla;
	}
	
	public JTable getTOrigen() {
		return tDatos;
	}
	
	public void setTComprobacion(JTable tabla) {
		tComprobacion=tabla;
	}
	
	public JTable getTComprobacion() {
		return tComprobacion;
	}
	
	public void setTResultados(JTable tabla) {
		tResultados=tabla;
	}
	
	public JTable getTResultados() {
		return tResultados;
	}
	
	public void setListaAlgoritmos(List<String> lista) {
		listaAlgoritmos=lista;
	}
	
	public List<String> getListaAlgoritmos() {
		return listaAlgoritmos;
	}
	
	public void setListaAlgoritmosTiempos(List<Double> lista) {
		listaAlgoritmosTiempos=lista;
	}
	
	public List<Double> getListaAlgoritmosTiempos() {
		return listaAlgoritmosTiempos;
	}
	
	public void setListaAlgoritmosPorcentajeAcierto(List<Double> lista) {
		listaAlgoritmosPorcentajeAcierto=lista;
	}
	
	public List<Double> getListaAlgoritmosPorcentajeAcierto() {
		return listaAlgoritmosPorcentajeAcierto;
	}
	
	public void setListaSecundarios(List<String> lista) {
		listaSecundarios=lista;
	}
	
	public List<String> getListaSecundarios() {
		return listaSecundarios;
	}
	
	public void setTipoRegistros(String tipo) {
		tipoRegistros=tipo;
	}
	
	public String getTipoRegistros() {
		return tipoRegistros;
	}
	
	public void setTipoCondicion(String condicion) {
		tipoCondicion=condicion;
	}
	
	public String getTipoCondicion() {
		return tipoCondicion;
	}
	
	public void setNormalizar(Boolean _normalizar) {
		normalizar=_normalizar;
	}
	
	public Boolean getNormalizar() {
		return normalizar;
	}
	
	public void setSoloErroneos(Boolean _soloErroneos) {
		soloErroneos=_soloErroneos;
	}
	
	public Boolean getSoloErroneos() {
		return soloErroneos;
	}
  	
}
