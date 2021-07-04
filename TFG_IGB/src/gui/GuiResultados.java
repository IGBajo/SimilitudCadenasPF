package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import util.AnalizarRegistro;
import util.CargarXLS;
import util.DatosAplicacion;
import util.RenderizarTabla;
import util.exportarExcel;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GuiResultados extends JInternalFrame implements InternalFrameListener, ActionListener {

	
	private static final long serialVersionUID = 1L;
	public static String eje;
	
	private static DatosAplicacion datos;
	private static HashSet<AnalizarRegistro> registros;
	private static HashSet<String> resultados;
	private JComboBox comboTipo;
	private JComboBox comboMetrica;
	private JLabel eTipo;
	private JLabel eMetrica;
	private JButton btnExportarResultados;
	private JScrollPane scrollPaneTCasos;
	private JTable tCasos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GuiEjecutar frame = new GuiEjecutar();
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
	public GuiResultados() {
		
		datos= new DatosAplicacion();
		registros= new HashSet<AnalizarRegistro>();
		resultados= new HashSet<String>();
		
		
		addInternalFrameListener(this);
		
		eje="eje";
		setTitle("Resultados"); 
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
		setBounds(100, 100, 1660, 900);
		getContentPane().setLayout(null);
		
		
		scrollPaneTCasos = new JScrollPane();
		scrollPaneTCasos.setBounds(55, 101, 1560, 678);
		getContentPane().add(scrollPaneTCasos);
		
		tCasos = new JTable();
		scrollPaneTCasos.setViewportView(tCasos);
		tCasos.setName("tCasos");
		
		tCasos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tCasos.setCellSelectionEnabled(true);
		tCasos.setColumnSelectionAllowed(true);
		tCasos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		comboTipo = new JComboBox();
		comboTipo.setBounds(373, 34, 223, 27);
		getContentPane().add(comboTipo);
		
		comboMetrica = new JComboBox();
		comboMetrica.setBounds(742, 34, 256, 27);
		getContentPane().add(comboMetrica);
		
		eTipo = new JLabel("Tipo");
		eTipo.setBounds(250, 38, 83, 16);
		getContentPane().add(eTipo);
		
		eMetrica = new JLabel("Métrica");
		eMetrica.setBounds(688, 38, 70, 16);
		getContentPane().add(eMetrica);
		
		btnExportarResultados = new JButton("Exportar Resultados");
		btnExportarResultados.setOpaque(false);
		btnExportarResultados.setContentAreaFilled(false);
		btnExportarResultados.setBorderPainted(false);

		//tnExportarResultados.setIcon(new ImageIcon(GuiResultados.class.getResource("/imagenes/sobresalir.png")));

		btnExportarResultados.addActionListener(this);
		btnExportarResultados.setBounds(1576, 791, 39, 46);
		
		btnExportarResultados.setIcon(setIcono("/imagenes/file-excel-solid.png", btnExportarResultados));
		btnExportarResultados.setPressedIcon(setIconoPulsado("/imagenes/file-excel-solid.png", btnExportarResultados,6,6));
		
		getContentPane().add(btnExportarResultados);
		
		inicializaCombos();

	}
	
	public ImageIcon setIcono (String url, JButton boton) {
		
		ImageIcon icono_origen = new  ImageIcon(getClass().getResource(url));
		int alto = boton.getHeight();
		int ancho = boton.getWidth();
		
		ImageIcon icono = new  ImageIcon(icono_origen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		
		return icono;
	}
	
	public ImageIcon setIconoPulsado (String url, JButton boton, int alt, int anc) {
		
		ImageIcon icono_origen = new  ImageIcon(getClass().getResource(url));
		int alto = boton.getHeight()-alt;
		int ancho = boton.getWidth()-anc;
		
		ImageIcon icono = new  ImageIcon(icono_origen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		
		return icono;
	}


	private void inicializaCombos() {

		comboTipo.addItem("Totales");
		comboTipo.addItem("Tipo de registro");
		comboTipo.addItem("Individual");
			
		comboMetrica.addItem("% Aciertos");
		comboMetrica.addItem("Tiempos");
		comboMetrica.addItem("Similitud");
		
	
	}
	
	
	public void setDatos(DatosAplicacion d) {
		datos=d;
		//calcularTablas();
	}
	
	public void setRegistros(HashSet<AnalizarRegistro> r) {
		registros=r;
		//calcularTablas();
	}
	
	public void setResultados(HashSet<String> r) {
		resultados=r;
		//calcularTablas();
	}
	
	public void calcularTablas() {

		

		//tabla Casos
		DefaultTableModel modeloIndividual = new DefaultTableModel();
		//modeloIndividual=cargarResultadosIndividuales("A", 2, "A", 'A');
		modeloIndividual=cargarResultadosIndividualesCADENAS("A", 2, "A", 'A');
		tCasos.setModel(modeloIndividual);
		
			//para ponerle tamaño a las columnas
		tCasos.getColumn(tCasos.getColumnName(0)).setPreferredWidth(30);
		tCasos.getColumn(tCasos.getColumnName(0)).setPreferredWidth(200);
		tCasos.getColumn(tCasos.getColumnName(0)).setPreferredWidth(30);
		tCasos.getColumn(tCasos.getColumnName(0)).setPreferredWidth(30);
	  	for (int k = 4; k < tCasos.getColumnCount(); k++) { 
	  		tCasos.getColumn(tCasos.getColumnName(k)).setPreferredWidth(130);
	  	}
	  	
		modeloIndividual.fireTableDataChanged();
		
		tCasos.setDefaultRenderer(Object.class, new RenderizarTabla());
		//tCasos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		
	}
	

	
	
	  public DefaultTableModel cargarResultadosIndividuales(String orden, int id, String nombre, char Metrica) {
		  
		  	// orden para ordenar los resultados
		  	// id para buscar una persona en concreto
		  	// nombre .- para mostrar las personas que contengan ese nombre
		  
			DefaultTableModel tableModel = new DefaultTableModel();
			//List<Double> lista = new ArrayList<Double>();
			Object[] objeto_registro;


			
			DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000000");
			DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
			
			
			// primero las columnas
			tableModel.addColumn("ID");
			tableModel.addColumn("NOMBRE");
			tableModel.addColumn("FEC NAC");
			tableModel.addColumn("ID_DESTINO");
			tableModel.addColumn("ERROR");
		  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
		  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase()+"-a");
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase()+"-t");
		  	}
			
    		for(AnalizarRegistro reg: registros){
    			objeto_registro = new Object[2*datos.getListaAlgoritmos().size()+5];
		  		//objeto_registro = new Object[datos.getListaAlgoritmos().size()+5];
		  		objeto_registro[0]=reg.getPersonaAnalizada().getId();
		  		objeto_registro[1]=reg.getPersonaAnalizada().getApellido1()+" "+reg.getPersonaAnalizada().getApellido2()+", "+reg.getPersonaAnalizada().getNombre();
		  		objeto_registro[2]=reg.getPersonaAnalizada().getFecNac();
		  		objeto_registro[3]=reg.getPersonaComprobada().getId();
		  		objeto_registro[4]=reg.getError();
		  		
		  		for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  			if (Metrica=='A') {
		  				if (reg.getPersonaComprobada().getId()==reg.getAlgoritmos().get(i).getIdCandidatoPropuesto()) {
		  					//objeto_registro[5+i]="OK";
		  					objeto_registro[5+(i*2)]="V";
		  				}else {
		  					//objeto_registro[5+i]="X - ("+registros.get(j).getAlgoritmos().get(i).getIdCandidatoPropuesto().toString()+")";
		  					objeto_registro[5+(i*2)]="X";
		  				}
		  				objeto_registro[5+(i*2)+1]=reg.getAlgoritmos().get(i).getTiempo();
		  			}else {
		  				objeto_registro[5+i]=numeroFormateadoTiempos.format(reg.getAlgoritmos().get(i).getTiempo()/1000000000);
		  			}
		  		}
		  		
		  		tableModel.addRow(objeto_registro);
    		}
		  	
		  	
		  	return tableModel;
	    	
	  }
	  
	  public DefaultTableModel cargarResultadosIndividualesCADENAS(String orden, int id, String nombre, char Metrica) {
		  
		  	// orden para ordenar los resultados
		  	// id para buscar una persona en concreto
		  	// nombre .- para mostrar las personas que contengan ese nombre
		  
			DefaultTableModel tableModel = new DefaultTableModel();
			//List<Double> lista = new ArrayList<Double>();
			Object[] objeto_registro;


			
			DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000000");
			DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
			
			
			// primero las columnas
			tableModel.addColumn("ID");
			tableModel.addColumn("NOMBRE");
			tableModel.addColumn("FEC NAC");
			tableModel.addColumn("ID_DESTINO");
			tableModel.addColumn("ERROR");
		  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
		  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase()+"-a");
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase()+"-t");
		  	}
			
		  	for(String reg: resultados){
		  		
				String[] parts = reg.split(";");
				
  				objeto_registro = new Object[2*datos.getListaAlgoritmos().size()+5];
		  		//objeto_registro = new Object[datos.getListaAlgoritmos().size()+5];
  				
  				for (int j=0; j<parts.length; j++ ) {
  					objeto_registro[j]=parts[j];
  				}
  				
		  		tableModel.addRow(objeto_registro);
		  	}
		  	
		  	
		  	return tableModel;
	    	
	  }
	
	/*public void setTTotales(JTable j) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo=(DefaultTableModel) j.getModel();
		tListado.setModel(modelo);
		modelo.fireTableDataChanged();
	}*/
	
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
		eje=null;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExportarResultados) {
			actionPerformedBtnCargarResultados(e);
		}
	}
	
	protected void actionPerformedBtnCargarResultados(ActionEvent e) {
	    	
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
		    chooser.setFileFilter(filter);
		    chooser.setDialogTitle("Guardar archivo");
		    chooser.setAcceptAllFileFilterUsed(false);
		    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		        String ruta = chooser.getSelectedFile().toString().concat(".xls");
		        try {
			        //creardirectorio();
			        List<JTable> tb = new ArrayList<JTable>();
			        tb.add(tCasos);
			        //-------------------
			        File excel = new File(ruta);
			        exportarExcel excelExporter = new exportarExcel(tb, excel); // String con directorio
			        if (excelExporter.export()) {
			            System.out.println("TABLAS EXPORTADOS CON EXITOS!");
			        }
			    }catch (Exception ex) {
			        ex.printStackTrace();
			    }
	    
		    }
	}
}
