package semana10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

public class Libreria extends JFrame implements ActionListener {

	// Declaraci�n de variables
	private static final long serialVersionUID = 9206324162700448001L;
	private JPanel contentPane;
	private JLabel lblMarca;
	private JLabel lblCantidad;
	private JComboBox<String> cboMarca;
	private JTextField txtCantidad;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Lanza la aplicaci�n
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Libreria frame = new Libreria();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Libreria() {
		setTitle("Librer\u00EDa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 233);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 11, 47, 14);
		contentPane.add(lblMarca);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 36, 47, 14);
		contentPane.add(lblCantidad);

		cboMarca = new JComboBox<String>();
		cboMarca.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Standford", "Alpha", "Justus", "Loro" }));
		cboMarca.setBounds(67, 8, 100, 20);
		contentPane.add(cboMarca);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(67, 33, 100, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 7, 89, 23);
		contentPane.add(btnProcesar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(335, 32, 89, 23);
		contentPane.add(btnBorrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 61, 414, 123);
		contentPane.add(scpScroll);

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}

	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}

	// Procesa la pulsaci�n del bot�n Borrar
	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		txtCantidad.setText("");
		txtS.setText("");
		cboMarca.setSelectedIndex(0);
		txtCantidad.requestFocus();
	}
	
	void imprimir(String cad){
		txtS.append(cad + "\n");
	}
	
	// Procesa la pulsaci�n del bot�n Procesar
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		int cant = ObtenerCantidad();
		String marca = ObteneMarca();
		double pre = ObtenerPrecio(marca);
		double impC= ImporteCompra(pre,cant);
		double impD= ImporteDescuento(impC,cant);
		double impP= ImportePagar(impC,impD);
		int lap=  ObsequiLapicero(cant,impP);
		
		txtS.setText("PROGRAMA LIBRERIA \n");
		imprimir("Importe de compra :" + decimalFormat(impC));
		imprimir("Importe de descuento :" + decimalFormat(impD));
		imprimir("Importe a pagar :" + decimalFormat(impP));
		imprimir("Lapicero(s) :" + decimalFormat(lap));
}
	int ObtenerCantidad() {
		return Integer.parseInt(txtCantidad.getText());
	}
	String ObteneMarca() {
		return cboMarca.getSelectedItem().toString();
	}
	double ObtenerPrecio(String marca) {
		if(marca=="Standford") {return 4.85;}
		else if(marca=="Alpha") {return 4.35;}
		else if(marca=="Justus") {return 3.50;}
		else{return 4.55;}
	}
	double ImporteCompra(double pre,int cant) {
		return pre*cant;
	}
	double ImporteDescuento(double impC,int cant) {
		if(cant>=36) {return impC*0.135;}
		else if(cant>=24 && cant<36) {return impC*0.115;}
		else if(cant>=12 && cant<24) {return impC*0.095;}
		else{return impC*0.075;}
	}
	double ImportePagar(double impC,double impD) {
		return impC-impD;
	}
	int ObsequiLapicero(int cant,double impP) {
		if(cant>=150) {return 5;}
		else if(cant>=125 && cant<150) {return 4;}
		else if(cant>=100 && cant<125) {return 3;}
		else{return 2;}
	}
String decimalFormat(double p){
	return String.format("%.2f",p).replace(",", ".");
	}
}