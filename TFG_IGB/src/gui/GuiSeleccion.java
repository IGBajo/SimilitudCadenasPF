package gui;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameListener;

import util.DatosAplicacion;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GuiSeleccion extends JInternalFrame implements InternalFrameListener, ActionListener {

	
	private static final long serialVersionUID = 1L;
	public static String sel;
	
	// Registros a tratar
	private JPanel panelRegistros;
	private JRadioButton rbIndividual;
	private JRadioButton rbTotal;
	private JRadioButton rbSeleccionados;
	
	// Algoritmo
	private JPanel panelAlgoritmo;
	private JCheckBox chAlgLevenshtein;
	private JCheckBox chAlgJaro;
	private JCheckBox chAlgJaroWinkler;
	private JCheckBox chAlgSmithWaterman;
	private JCheckBox chAlgNeddlemanWunch;
	private JCheckBox chAlgBlockDistance;
	private JCheckBox chAlgSolapamiento;
	private JCheckBox chAlgJaccard;
	private JCheckBox chAlgTfIdf;
	private JCheckBox chAlgMongeElkan;
	private JCheckBox chAlgSoundex;
	
	//Campos Secundarios
	private JPanel panelCamposSecundarios;
	private JCheckBox chSecunDni;
	private JCheckBox chSecunDireccion;
	private JCheckBox chSecunCivitas;
	private JCheckBox chSecunProMun;

	//Condiciones
	private JPanel panel_Condiciones;
	private JRadioButton chCondSeparados;
	private JRadioButton chCondTodos;
	private JRadioButton chCondNomApe1Ape2;
	private JRadioButton chCondApe1Ape2Nom;
	
	//private static DatosAplicacion datos;
	private static String tipoRegistros;
	private static String tipoCondicion;
	
	private List<String> listaAlgoritmos;
	private List<String> listaSecundarios;
	private JPanel panelPreselección;
	private JCheckBox chNormalizar;
	private JCheckBox chSoloErroneos;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GuiSeleccion frame = new GuiSeleccion();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public GuiSeleccion(){
		
		//datos= new DatosAplicacion();

		tipoRegistros= new String();
		tipoCondicion= new String();
		listaAlgoritmos=new ArrayList<String>();
		listaSecundarios=new ArrayList<String>();
		
		tipoRegistros="S";
		tipoCondicion="CON";
		
		addInternalFrameListener(this);
		
		sel="sel"; 
		
		setTitle("Seleccion Datos y Variables");
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		pack();
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(true);
		setBounds(100, 100, 1600, 900);
		getContentPane().setLayout(null);
		
		panelRegistros = new JPanel();
		panelRegistros.setBorder(new TitledBorder(null, "Registros a analizar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRegistros.setBounds(540, 47, 483, 61);
		getContentPane().add(panelRegistros);
		panelRegistros.setLayout(null);
		
		rbIndividual = new JRadioButton("Individual");
		rbIndividual.addActionListener(this);
		rbIndividual.setBounds(62, 24, 141, 23);
		panelRegistros.add(rbIndividual);
		
		rbTotal = new JRadioButton("Total");
		rbTotal.addActionListener(this);
		rbTotal.setBounds(215, 24, 141, 23);
		panelRegistros.add(rbTotal);
		
		rbSeleccionados = new JRadioButton("Seleccionados");
		rbSeleccionados.addActionListener(this);
		rbSeleccionados.setSelected(true);
		rbSeleccionados.setBounds(343, 24, 141, 23);
		panelRegistros.add(rbSeleccionados);
		
		panelAlgoritmo = new JPanel();
		panelAlgoritmo.setBorder(new TitledBorder(null, "Algoritmos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAlgoritmo.setBounds(326, 247, 424, 243);
		getContentPane().add(panelAlgoritmo);
		panelAlgoritmo.setLayout(null);
		
		chAlgLevenshtein = new JCheckBox("Levenshtein");
		chAlgLevenshtein.setBounds(34, 31, 163, 23);
		panelAlgoritmo.add(chAlgLevenshtein);
		
		chAlgJaro = new JCheckBox("Jaro");
		chAlgJaro.setBounds(34, 66, 163, 23);
		panelAlgoritmo.add(chAlgJaro);
		
		chAlgJaroWinkler = new JCheckBox("Jaro-Winkler");
		chAlgJaroWinkler.setBounds(34, 101, 163, 23);
		panelAlgoritmo.add(chAlgJaroWinkler);
		
		chAlgSmithWaterman = new JCheckBox("Smith-Waterman");
		chAlgSmithWaterman.setBounds(34, 136, 163, 23);
		panelAlgoritmo.add(chAlgSmithWaterman);
		
		chAlgNeddlemanWunch = new JCheckBox("Needleman-Wunsch");
		chAlgNeddlemanWunch.setBounds(34, 171, 163, 23);
		panelAlgoritmo.add(chAlgNeddlemanWunch);
		
		chAlgBlockDistance = new JCheckBox("BlockDistance");
		chAlgBlockDistance.setBounds(34, 206, 163, 23);
		panelAlgoritmo.add(chAlgBlockDistance);
		
		chAlgSolapamiento = new JCheckBox("Solapamiento");
		chAlgSolapamiento.setBounds(255, 31, 163, 23);
		panelAlgoritmo.add(chAlgSolapamiento);
		
		chAlgJaccard = new JCheckBox("Jaccard");
		chAlgJaccard.setBounds(255, 66, 163, 23);
		panelAlgoritmo.add(chAlgJaccard);
		
		chAlgTfIdf = new JCheckBox("Coseno");
		chAlgTfIdf.setBounds(255, 101, 163, 23);
		panelAlgoritmo.add(chAlgTfIdf);
		
		chAlgMongeElkan = new JCheckBox("Monge-Elkan");
		chAlgMongeElkan.setBounds(255, 154, 128, 23);
		panelAlgoritmo.add(chAlgMongeElkan);
		
		chAlgSoundex = new JCheckBox("Soundex");
		chAlgSoundex.setBounds(255, 206, 128, 23);
		panelAlgoritmo.add(chAlgSoundex);
		
		
		chAlgLevenshtein.setSelected(true);
		chAlgJaro.setSelected(true);
		chAlgJaroWinkler.setSelected(true);
		chAlgSmithWaterman.setSelected(true);
		chAlgNeddlemanWunch.setSelected(true);
		chAlgBlockDistance.setSelected(true);
		chAlgSolapamiento.setSelected(true);
		chAlgJaccard.setSelected(true);
		chAlgTfIdf.setSelected(true);
		chAlgMongeElkan.setSelected(true);
		chAlgSoundex.setSelected(true);
		
		
		panelCamposSecundarios = new JPanel();
		panelCamposSecundarios.setLayout(null);
		panelCamposSecundarios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "A\u00F1adir Campos Secundarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCamposSecundarios.setBounds(834, 374, 395, 116);
		getContentPane().add(panelCamposSecundarios);
		
		chSecunDni = new JCheckBox("DNI NIE");
		chSecunDni.addActionListener(this);
		chSecunDni.setBounds(34, 38, 125, 23);
		panelCamposSecundarios.add(chSecunDni);
		
		chSecunDireccion = new JCheckBox("Dirección");
		chSecunDireccion.addActionListener(this);
		chSecunDireccion.setBounds(34, 73, 125, 23);
		panelCamposSecundarios.add(chSecunDireccion);
		
		chSecunCivitas = new JCheckBox("Tarjeta Sanitaria");
		chSecunCivitas.addActionListener(this);
		chSecunCivitas.setBounds(171, 38, 149, 23);
		panelCamposSecundarios.add(chSecunCivitas);
		
		chSecunProMun = new JCheckBox("Provincia + Municipio");
		chSecunProMun.addActionListener(this);
		chSecunProMun.setBounds(171, 73, 191, 23);
		panelCamposSecundarios.add(chSecunProMun);
		
		panel_Condiciones = new JPanel();
		panel_Condiciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Condiciones a Analizar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Condiciones.setBounds(834, 154, 395, 170);
		getContentPane().add(panel_Condiciones);
		panel_Condiciones.setLayout(null);
		
		chCondSeparados = new JRadioButton("Campos por Separado");
		chCondSeparados.addActionListener(this);
		chCondSeparados.setBounds(39, 23, 311, 23);
		panel_Condiciones.add(chCondSeparados);
		
		chCondTodos = new JRadioButton("Concatenar Campos Principales");
		chCondTodos.addActionListener(this);
		chCondTodos.setSelected(true);
		chCondTodos.setBounds(39, 58, 311, 23);
		panel_Condiciones.add(chCondTodos);
		
		chCondNomApe1Ape2 = new JRadioButton("Concatenar Nombre ( Nom+Ape1+Ape2)");
		chCondNomApe1Ape2.addActionListener(this);
		chCondNomApe1Ape2.setBounds(39, 93, 311, 23);
		panel_Condiciones.add(chCondNomApe1Ape2);
		
		chCondApe1Ape2Nom = new JRadioButton("Concatenar Nombre ( Ape1+Ape2+Nom)");
		chCondApe1Ape2Nom.addActionListener(this);
		chCondApe1Ape2Nom.setBounds(39, 128, 311, 23);
		panel_Condiciones.add(chCondApe1Ape2Nom);
		
		panelPreselección = new JPanel();
		panelPreselección.setLayout(null);
		panelPreselección.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Casu\u00EDsticas a Analizar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPreselección.setBounds(326, 154, 424, 61);
		getContentPane().add(panelPreselección);
		
		chNormalizar = new JCheckBox("Normalizar");
		chNormalizar.setBounds(70, 24, 125, 23);
		panelPreselección.add(chNormalizar);
		chNormalizar.setSelected(true);
		
		chSoloErroneos = new JCheckBox("Solo Registros Erroneos");
		chSoloErroneos.setBounds(222, 24, 181, 23);
		panelPreselección.add(chSoloErroneos);
		chSoloErroneos.setSelected(true);

	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}
	public void internalFrameClosed(InternalFrameEvent e) {
		if (e.getSource() == this) {
			internalFrameClosedThis(e);
		}
	}
	public void internalFrameClosing(InternalFrameEvent e) {
	}
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}
	public void internalFrameIconified(InternalFrameEvent e) {
	}
	public void internalFrameOpened(InternalFrameEvent e) {
	}
	protected void internalFrameClosedThis(InternalFrameEvent e) {
		setVisible(false);
		sel=null;
	}
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == chCondApe1Ape2Nom) {
			actionPerformedChCondApe1Ape2Nom(e);
		}
		if (e.getSource() == chCondNomApe1Ape2) {
			actionPerformedChCondNomApe1Ape2(e);
		}
		if (e.getSource() == chCondTodos) {
			actionPerformedChCondTodos(e);
		}
		if (e.getSource() == chCondSeparados) {
			actionPerformedChCondSeparados(e);
		}
		if (e.getSource() == rbSeleccionados) {
			actionPerformedRbSeleccionados(e);
		}
		if (e.getSource() == rbTotal) {
			actionPerformedRbTotal(e);
		}
		if (e.getSource() == rbIndividual) {
			actionPerformedRbIndividual(e);
		}
	}
	protected void actionPerformedRbIndividual(ActionEvent e) {
		if (rbIndividual.isSelected()){
			rbTotal.setSelected(false);
			rbSeleccionados.setSelected(false);
			
			tipoRegistros="I";
		}
	}
	protected void actionPerformedRbTotal(ActionEvent e) {
		if (rbTotal.isSelected()){
			rbIndividual.setSelected(false);
			rbSeleccionados.setSelected(false);
			
			tipoRegistros="T";
		}
	}
	protected void actionPerformedRbSeleccionados(ActionEvent e) {
		if (rbSeleccionados.isSelected()){
			rbTotal.setSelected(false);
			rbIndividual.setSelected(false);
			
			tipoRegistros="S";
		}
	}
	protected void actionPerformedChCondSeparados(ActionEvent e) {
		if (chCondSeparados.isSelected()){
			chCondTodos.setSelected(false);
			chCondNomApe1Ape2.setSelected(false);
			chCondApe1Ape2Nom.setSelected(false);
			
			tipoCondicion="SEP";
		}
	}
	protected void actionPerformedChCondTodos(ActionEvent e) {
		if (chCondTodos.isSelected()){
			chCondSeparados.setSelected(false);
			chCondNomApe1Ape2.setSelected(false);
			chCondApe1Ape2Nom.setSelected(false);
			tipoCondicion="CON";
		}
	}
	protected void actionPerformedChCondNomApe1Ape2(ActionEvent e) {
		if (chCondNomApe1Ape2.isSelected()){
			chCondTodos.setSelected(false);
			chCondSeparados.setSelected(false);
			chCondApe1Ape2Nom.setSelected(false);
			
			tipoCondicion="NAA";
		}
	}
	protected void actionPerformedChCondApe1Ape2Nom(ActionEvent e) {
		if (chCondApe1Ape2Nom.isSelected()){
			chCondTodos.setSelected(false);
			chCondNomApe1Ape2.setSelected(false);
			chCondSeparados.setSelected(false);
			
			tipoCondicion="AAN";
		}
	}
	
	
	public String getTipoRegistros() {
		return tipoRegistros;
	}
	
	public String getTipoCondicion() {
		return tipoCondicion;
	}
	
	
	public Boolean getNormalizar() {
		if (chNormalizar.isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean getSoloErroneos() {
		if (chSoloErroneos.isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public List<String> getListaAlgoritmos() {
		
		listaAlgoritmos.clear();
		
		if (chAlgLevenshtein.isSelected())
			listaAlgoritmos.add(chAlgLevenshtein.getText());
		if (chAlgJaro.isSelected())
			listaAlgoritmos.add(chAlgJaro.getText());
		if (chAlgJaroWinkler.isSelected())
			listaAlgoritmos.add(chAlgJaroWinkler.getText());
		if (chAlgSmithWaterman.isSelected())
			listaAlgoritmos.add(chAlgSmithWaterman.getText());
		if (chAlgNeddlemanWunch.isSelected())
			listaAlgoritmos.add(chAlgNeddlemanWunch.getText());
		if (chAlgBlockDistance.isSelected())
			listaAlgoritmos.add(chAlgBlockDistance.getText());
		if (chAlgSolapamiento.isSelected())
			listaAlgoritmos.add(chAlgSolapamiento.getText());
		if (chAlgJaccard.isSelected())
			listaAlgoritmos.add(chAlgJaccard.getText());
		if (chAlgTfIdf.isSelected())
			listaAlgoritmos.add(chAlgTfIdf.getText());
		if (chAlgMongeElkan.isSelected())
			listaAlgoritmos.add(chAlgMongeElkan.getText());
		if (chAlgSoundex.isSelected())
			listaAlgoritmos.add(chAlgSoundex.getText());

		return listaAlgoritmos;
		
	}
	
	public List<String> getListaSecundarios() {
		
		listaSecundarios.clear();
		
		if (chSecunDni.isSelected())
			listaSecundarios.add(chSecunDni.getText());
		if (chSecunDireccion.isSelected())
			listaSecundarios.add(chSecunDireccion.getText());
		if (chSecunCivitas.isSelected())
			listaSecundarios.add(chSecunCivitas.getText());
		if (chSecunProMun.isSelected())
			listaSecundarios.add(chSecunProMun.getText());

		return listaSecundarios;
		
	}
}
