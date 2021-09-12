
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.GuiSeleccion;
import gui.GuiTablaOrigen;
import metodos.Programa;
import util.AnalizarRegistro;
import util.DatosAplicacion;
import gui.GuiResultados;
import gui.GuiAnalizar;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Font;

public class VentanaMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public static JDesktopPane escritorio;
	
	private GuiTablaOrigen pro;
	private GuiSeleccion sel;
	private GuiResultados eje;
	private GuiAnalizar ana;
	private JButton btnCargarOrigenDatos;
	private JButton btnSeleccion;
	private JButton btnResultados;
	private JButton btnAnalizar;
	
	private DatosAplicacion datos;
	private HashSet<AnalizarRegistro> registros;
	private HashSet<String> resultados;
	private JButton btnPlay;
	
	
	// si queremos cargar los paneles internos y volverlos a visualizar para no borrar el contenido


	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//VentanaMenu frame = new VentanaMenu();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public VentanaMenu() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("TFG Iván García Bajo");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1608, 963);
		
		CentrarJFrame();
		
		datos = new DatosAplicacion();
		registros = new HashSet<AnalizarRegistro>();
		resultados = new HashSet<String>();
		
		
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		btnCargarOrigenDatos = new JButton();
		btnCargarOrigenDatos.addActionListener(this);
		
		btnCargarOrigenDatos.setBounds(btnCargarOrigenDatos.getX(), btnCargarOrigenDatos.getY(), 50, 50);
		
		
		btnCargarOrigenDatos.setIcon(setIcono("/imagenes/bbdd_peq_azul.png", btnCargarOrigenDatos));
		btnCargarOrigenDatos.setRolloverIcon(setIcono("/imagenes/bbdd_peq_azul_osc.png", btnCargarOrigenDatos));
		btnCargarOrigenDatos.setPressedIcon(setIconoPulsado("/imagenes/bbdd_peq_azul_osc.png", btnCargarOrigenDatos,2,2));
		btnCargarOrigenDatos.setToolTipText("Datos a Analizar");
		
		menuBar.add(btnCargarOrigenDatos);
		

		
		btnSeleccion = new JButton("");
		btnSeleccion.setToolTipText("Opciones");
		btnSeleccion.addActionListener(this);

		btnSeleccion.setBounds(btnSeleccion.getX(), btnSeleccion.getY(), 50, 50);
		
		btnSeleccion.setIcon(setIcono("/imagenes/select_peq_azul.png", btnSeleccion));
		btnSeleccion.setRolloverIcon(setIcono("/imagenes/select_peq_azul_osc.png", btnSeleccion));
		btnSeleccion.setPressedIcon(setIconoPulsado("/imagenes/select_peq_azul_osc.png", btnSeleccion,2,2));
		
		
		menuBar.add(btnSeleccion);
		

		
		btnPlay = new JButton();
		btnPlay.setToolTipText("Ejecutar");
		btnPlay.addActionListener(this);

		btnPlay.setBounds(btnPlay.getX(), btnPlay.getY(), 50, 50);
		
		btnPlay.setIcon(setIcono("/imagenes/play_rojo_peq.png", btnPlay));
		btnPlay.setRolloverIcon(setIcono("/imagenes/play_rojo_peq_osc.png", btnPlay));
		btnPlay.setPressedIcon(setIconoPulsado("/imagenes/play_rojo_peq_osc.png", btnPlay,2,2));
		
		menuBar.add(btnPlay);
		
		btnResultados = new JButton();
		btnResultados.addActionListener(this);

		btnResultados.setBounds(btnResultados.getX(), btnResultados.getY(), 50, 50);
		
		btnResultados.setIcon(setIcono("/imagenes/estadistica_peq_azul.jpg", btnResultados));
		btnResultados.setRolloverIcon(setIcono("/imagenes/estadistica_peq_azul_osc.jpg", btnResultados));
		btnResultados.setPressedIcon(setIconoPulsado("/imagenes/estadistica_peq_azul_osc.jpg", btnResultados,2,2));
		
		
		menuBar.add(btnResultados);
		
		
		btnAnalizar = new JButton();
		btnAnalizar.addActionListener(this);

		btnAnalizar.setBounds(btnAnalizar.getX(), btnAnalizar.getY(), 50, 50);
		
		btnAnalizar.setIcon(setIcono("/imagenes/exponer_peq_azul.jpg", btnAnalizar));
		btnAnalizar.setRolloverIcon(setIcono("/imagenes/exponer_peq_azul_osc.jpg", btnAnalizar));
		btnAnalizar.setPressedIcon(setIconoPulsado("/imagenes/exponer_peq_azul_osc.jpg", btnAnalizar,2,2));
		
		
		menuBar.add(btnAnalizar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(23, 156, 209));
		contentPane.add(escritorio, "escritorio");
		
		
		
		setVisible(true);
		
		
	}

	public void CentrarJFrame(){

	      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	      int height = pantalla.height;
	      int width = pantalla.width;
	      setSize(1600, 900);		

	      setLocationRelativeTo(null);		
	      setVisible(true);
	  }

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			actionPerformedBtnPlay(e);
		}
		if (e.getSource() == btnAnalizar) {
			actionPerformedBtnAnalizar(e);
		}
		if (e.getSource() == btnResultados) {
			actionPerformedBtnEjecutar(e);
		}
		if (e.getSource() == btnSeleccion) {
			actionPerformedBtnSeleccion(e);
		}
		if (e.getSource() == btnCargarOrigenDatos) {
			actionPerformedBtnCargarOrigenDatos(e);
		}
	}
	

	protected void actionPerformedBtnCargarOrigenDatos(ActionEvent e) {
		String p = GuiTablaOrigen.pro;
		if (p==null) {
			pro=new GuiTablaOrigen();
			escritorio.add(pro);
		}
		pro.setVisible(true);
		try {
			pro.setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void actionPerformedBtnSeleccion(ActionEvent e) {
		String p = GuiSeleccion.sel;
		if (p==null) {
			sel=new GuiSeleccion();
			escritorio.add(sel);
		}
		sel.setVisible(true);
		try {
			sel.setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void actionPerformedBtnEjecutar(ActionEvent e) {
		// declarar variabla para llamar la var estatic pro que es de guiTablaOrigen
		
		// Cargar datos en Resultados
		String p = GuiResultados.eje;
		if (p==null) {
			eje=new GuiResultados();
			escritorio.add(eje);
		}
		eje.setVisible(true);
		
		try {
			eje.setDatos(datos);
			eje.setRegistros(registros);
			eje.setResultados(resultados);
			eje.calcularTablas();
			eje.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		

		
	}
	
	protected void actionPerformedBtnAnalizar(ActionEvent e) {
		// Cargar datos en Analizar
		String a = GuiAnalizar.ana;
		if (a==null) {
			ana=new GuiAnalizar();
			escritorio.add(ana);
		}
		ana.setVisible(true);
		
		try {
			ana.setDatos(datos);
			ana.setRegistros(registros);
			ana.setResultados(resultados);
			ana.calcularTablas();
			ana.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void setDatos(DatosAplicacion d) {
		datos=d;
	}
	
	public DatosAplicacion getDatos() {
		return datos;
	}
	
	
	/*public void actualizarDatos() {
		datos.getTOrigen(pro.setTableOrigen());
		datos.getTComprobacion(pro.setTableComprobacion());
	}*/
	
	protected void actionPerformedBtnPlay(ActionEvent e) {
		
		
		//////PLAYYYYYYYYYY
		
		
		
		//recoger los datos de los menus Tablas y Seleccion 
		try {
			
			datos.setTOrigen(pro.getTDatos());
			datos.setTComprobacion(pro.getTableComprobacion());
			datos.setTipoCondicion(sel.getTipoCondicion());
			datos.setTipoRegistros(sel.getTipoRegistros());
			
			datos.setListaAlgoritmos(sel.getListaAlgoritmos());
			datos.setListaSecundarios(sel.getListaSecundarios());
			 
			datos.setNormalizar(sel.getNormalizar());
			datos.setSoloErroneos(sel.getSoloErroneos());
			
			Programa prog=new Programa(datos);
			
			//devuelvo los datos 
			datos.setTResultados(prog.getDatos().getTResultados()); 
			
			//registros= prog.getRegistros();
			resultados=prog.getResultados();

			actionPerformedBtnEjecutar(e);

		} catch (Exception error) {
			if (pro==null || sel==null){
				JOptionPane.showMessageDialog(null, "Aun no se han inicializado los campos");
			}else {
				JOptionPane.showMessageDialog(null, "Error al leer los datos: "+error);
			}
			
		}
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
	
}
