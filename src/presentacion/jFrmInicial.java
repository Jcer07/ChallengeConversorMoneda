package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class jFrmInicial extends JFrame {

	private JPanel contentPane;;
	
	/*
	 * Componentes de la App
	*/
	//private JPanel pnlBarraTitulo = new JPanel();
	//private JLabel lblTitulo = new JLabel();
	private JButton btnAceptar = new JButton("Aceptar");
	private JButton btnSalir = new JButton("Salir");
	private JLabel lblTituloComboBox = new JLabel("Tipo de Conversor");
	private JComboBox<String> cboConversor = new JComboBox<>();
	private DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
	

	
	/*
	 * Constructor
	*/
	public jFrmInicial() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Cerrando Programa, Gracias");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jcer\\eclipse-workspace\\ChallengeConversor\\Images\\ImgConversores.jpg"));
		this.inicializarComponentes();
		this.CargarModeloCombo();
		this.setLocationRelativeTo(null);
		this.setTitle("Conversor");
		this.setResizable(false); 
		this.setUndecorated(false);
	}
	
	private void CargarModeloCombo() {
		modelo.addElement("-- Seleccione --");
		modelo.addElement("Conversor de Monedas");
		modelo.addElement("Conversor de Longitudes");
		modelo.addElement("Conversor de Temperatura");
		
		cboConversor.setModel(modelo);
		cboConversor.setSelectedIndex(0);
	}
	
	/**
	 * Inicializando componentes y creando eventos
	*/
	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setBackground(new Color(75, 75, 75));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setBackground(new Color(128, 128, 128));
			}
		});
		
		
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalir.setBounds(290, 184, 80, 30);
		btnSalir.setBorder(null);
		btnSalir.setBackground(new Color(128, 128, 128));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, "Cerrando Programa, Gracias");
				System.exit(0);
			}
		});
		contentPane.add(btnSalir);
		contentPane.setLayout(null);
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboConversor.getSelectedIndex() > 0) {
					crearForm(cboConversor.getSelectedItem().toString(), cboConversor.getSelectedIndex());
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Debe elegir un conversor", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAceptar.setBackground(new Color(0, 117, 166));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAceptar.setBackground(new Color(0, 134, 190));
			}
		});
		
		
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAceptar.setBounds(68, 184, 100, 30);
		btnAceptar.setBorder(null);
		btnAceptar.setBackground(new Color(0, 134, 190));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnAceptar);
		lblTituloComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTituloComboBox.setBounds(68, 44, 139, 18);
		
		contentPane.add(lblTituloComboBox);
		
		cboConversor.setFont(new Font("Arial", Font.PLAIN, 14));
		cboConversor.setBounds(66, 74, 304, 21);
		cboConversor.setBorder(null); 
		contentPane.add(cboConversor);
		
	}
	
	private void crearForm(String titulo, int opcion) {
		jFrmConversor frm = new jFrmConversor();
		frm.setTitle(titulo);
		frm.cargarCombo(opcion);
		frm.setVisible(true);
		this.cargarIcono(frm, opcion);
		this.dispose();
	}
	
	private void cargarIcono(jFrmConversor frm, int opcion) {
		if(opcion == 1) {
			frm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jcer\\eclipse-workspace\\ChallengeConversor\\Images\\ImgConversorMoneda.jpg"));
		}
		if(opcion == 2) {
			frm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jcer\\eclipse-workspace\\ChallengeConversor\\Images\\ImgConversorLongitud.jpg"));
		}
		if(opcion == 3) {
			frm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jcer\\eclipse-workspace\\ChallengeConversor\\Images\\ImgConversorTemperatura.png"));
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrmInicial frame = new jFrmInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
