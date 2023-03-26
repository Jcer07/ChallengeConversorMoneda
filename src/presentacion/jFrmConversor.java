package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conversor.ApiConversorMoneda;
import conversor.ConversorLongitud;
import conversor.ConversorTemperatura;
import entidades.Longitud;
import entidades.Moneda;
import entidades.Temperatura;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class jFrmConversor extends JFrame {

	private JPanel contentPane;
	JButton btnRegresar = new JButton("Regresar");
	JButton btnConvertir = new JButton("Convertir");
	JLabel lblConvertirDe = new JLabel("Convertir De");
	JLabel lblConvertirA = new JLabel("Convertir A");
	JLabel lblMonto = new JLabel("Monto");
	private JTextField txtCantidad = new JTextField();;
	
	private int op;
	JComboBox<String> cboConvertirDe = new JComboBox<>();
	JComboBox<String> cboConvertirA = new JComboBox<>();
	
	private ArrayList<Moneda> monedas;
	private ArrayList<Longitud> longitudes;
	private ArrayList<Temperatura> temperaturas;
	private DefaultComboBoxModel modeloDe;
    private DefaultComboBoxModel modeloA;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFrmConversor frame = new jFrmConversor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	
	/**
	 * Create the frame.
	 */
	public jFrmConversor() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				jFrmInicial frm = new jFrmInicial();
				frm.setVisible(true);
				dispose();
			}
		});
		this.inicializarComponentes();
		//setUndecorated(true);
		setLocationRelativeTo(null);
	}
	
	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 622, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegresar.setBackground(new Color(75, 75, 75));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegresar.setBackground(new Color(128, 128, 128));
			}
		});
		
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrmInicial frm = new jFrmInicial();
				frm.setVisible(true);
				dispose(); 
			}
		});
		btnRegresar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRegresar.setBorder(null);
		btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegresar.setBounds(416, 258, 100, 30);
		btnRegresar.setBackground(new Color(128, 128, 128));
		btnRegresar.setForeground(Color.WHITE);
		contentPane.add(btnRegresar);
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConvertir.setBackground(new Color(0, 117, 166));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnConvertir.setBackground(new Color(0, 134, 190));
			}
		});
		
		
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valor = Double.parseDouble(txtCantidad.getText().trim());
				if(validarTxtCantidad(valor)) { 
					switch (op) {
			            case 1 -> {
			                ConvertirMonedas(valor);
			            }
			            case 2 -> {
			                ConvertirLongitudes(valor);
			            }
			            case 3 -> {
			                convertirTemperaturas(valor);
			            }
			        }
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Debe ingresar un monto Válido", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConvertir.setFont(new Font("Arial", Font.PLAIN, 14));
		btnConvertir.setBounds(91, 258, 100, 30);
		btnConvertir.setBorder(null); 
		btnConvertir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConvertir.setBackground(new Color(0, 134, 190));
		btnConvertir.setForeground(Color.WHITE);
		contentPane.add(btnConvertir);
		
		
		lblConvertirDe.setFont(new Font("Arial", Font.PLAIN, 14));
		lblConvertirDe.setBounds(33, 39, 90, 20);
		contentPane.add(lblConvertirDe);
		
		
		cboConvertirDe.setMaximumRowCount(10);
		cboConvertirDe.setFont(new Font("Arial", Font.PLAIN, 14));
		cboConvertirDe.setBounds(33, 60, 225, 25);
		contentPane.add(cboConvertirDe);
		
		
		lblConvertirA.setFont(new Font("Arial", Font.PLAIN, 14));
		lblConvertirA.setBounds(356, 39, 90, 20);
		contentPane.add(lblConvertirA);
		
		
		cboConvertirA.setMaximumRowCount(10);
		cboConvertirA.setFont(new Font("Arial", Font.PLAIN, 14));
		cboConvertirA.setBounds(356, 60, 225, 25);
		contentPane.add(cboConvertirA);
		
		
		lblMonto.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMonto.setBounds(180, 142, 90, 20);
		contentPane.add(lblMonto);
		
		
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
		        boolean numbers = key >= 48 && key <=57;
		        boolean puntoDecimal = key == 46;
		        
		        if(!(numbers || puntoDecimal)){
		            e.consume();
		        }
			}
		});
		
		txtCantidad.setText("0");
		txtCantidad.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCantidad.setBounds(180, 163, 254, 25);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
	}
	
	public void cargarCombo(int opcion) {
		this.op = opcion;
		if(opcion == 1){
            this.llenarListadoMonedas();
            modeloDe = new DefaultComboBoxModel<Moneda>();
            modeloA = new DefaultComboBoxModel<Moneda>();
            this.cargarModeloCombo(modeloDe, modeloA, monedas);
        }
            
        if(opcion == 2){
            this.llenarListadoLongitudes();
            modeloDe = new DefaultComboBoxModel<Longitud>();
            modeloA = new DefaultComboBoxModel<Longitud>();
            this.cargarModeloCombo(modeloDe, modeloA, longitudes);
        }
        
        if(opcion == 3){
            this.llenarListadoTemperaturas();
            modeloDe = new DefaultComboBoxModel<Temperatura>();
            modeloA = new DefaultComboBoxModel<Temperatura>();
            this.cargarModeloCombo(modeloDe, modeloA, temperaturas);
        }  
	}
	
	private void cargarModeloCombo(DefaultComboBoxModel modeloDe, DefaultComboBoxModel modeloA, ArrayList listado) {
        modeloDe.addAll(listado); 
        modeloA.addAll(listado); 
        
        this.cboConvertirDe.setModel(modeloDe);
        this.cboConvertirA.setModel(modeloA);
        this.cboConvertirDe.setSelectedIndex(0);
        this.cboConvertirA.setSelectedIndex(this.cboConvertirA.getItemCount()-1);
    }
	
	private void llenarListadoMonedas() {
        monedas = new ArrayList<>();
        
        monedas.add(new Moneda("Soles", "PEN"));
        monedas.add(new Moneda("Dólares", "USD"));
        monedas.add(new Moneda("Euros", "EUR"));
        monedas.add(new Moneda("Libra Esterlina", "GBP"));
        monedas.add(new Moneda("Yen Japonés", "JPY"));
        monedas.add(new Moneda("Won Sul Coreano", "KRW"));
    }
	
	private void llenarListadoLongitudes() {
        longitudes = new ArrayList<>();
        
        longitudes.add(new Longitud("Metros", "m"));
        longitudes.add(new Longitud("Kilómetros", "km"));
        longitudes.add(new Longitud("Yardas", "yd"));
    }
	
	private void llenarListadoTemperaturas() {
        temperaturas = new ArrayList<>();
        temperaturas.add(new Temperatura("Celsius", "C"));
        temperaturas.add(new Temperatura("Kelvin", "K"));
        temperaturas.add(new Temperatura("Farenheit", "F"));
    }
	
	private boolean validarTxtCantidad(double valor) {
		if(this.txtCantidad.getText().isEmpty() || valor <= 0) {
			return false;
		}
		return true;
	}
	
	private void ConvertirMonedas(double valor) {
        Moneda monedaDe = ((Moneda)this.cboConvertirDe.getSelectedItem());
        Moneda monedaA = ((Moneda)this.cboConvertirA.getSelectedItem());
        
        if(monedaDe.getSiglas().equals(monedaA.getSiglas())){
            JOptionPane.showMessageDialog(rootPane, "Debe elegir monedas diferentes", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else{
            try {
                double montoConvertido = ApiConversorMoneda.getConversion(monedaDe.getSiglas(), monedaA.getSiglas(), valor);
                JOptionPane.showMessageDialog(rootPane, valor + " " + monedaDe.getNombre() + " - Equivale a " + montoConvertido + " " + monedaA.getNombre(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (URISyntaxException | IOException | InterruptedException ex) {
                
            }
        }
    }
	
	private void ConvertirLongitudes(double valor) {
        Longitud longitudDe = ((Longitud)cboConvertirDe.getSelectedItem());
        Longitud longitudA = ((Longitud)cboConvertirA.getSelectedItem());
        
        
        if(longitudDe.getSiglas().equals(longitudA.getSiglas())){
            JOptionPane.showMessageDialog(rootPane, "Debe elegir longitudes diferentes", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else{
            double monto = ConversorLongitud.convertirLongitudes(longitudDe.getSiglas(), longitudA.getSiglas(), valor);
            double montoConvertido = redondearValor(monto);
            JOptionPane.showMessageDialog(rootPane, valor + " " + longitudDe.getNombre() + " - Equivale a " + montoConvertido + " " + longitudA.getNombre(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
	
	private void convertirTemperaturas(double valor){
        Temperatura temperaturaDe = ((Temperatura)this.cboConvertirDe.getSelectedItem());
        Temperatura temperaturaA = ((Temperatura)this.cboConvertirA.getSelectedItem());
        
        if(temperaturaDe.getSigla().equals(temperaturaA.getSigla())){
            JOptionPane.showMessageDialog(rootPane, "Debe elegir longitudes diferentes", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else{
            double montoConvertido = redondearValor(ConversorTemperatura.convertirTemperaturas(temperaturaDe.getSigla(), temperaturaA.getSigla(), valor));
            JOptionPane.showMessageDialog(rootPane, valor + " " + temperaturaDe.getNombre() + " - Equivale a " + montoConvertido + " " + temperaturaA.getNombre(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
           
    }
	
	private double redondearValor(double valor){
        DecimalFormatSymbols separador = new DecimalFormatSymbols();
        separador.setDecimalSeparator('.');
        
        DecimalFormat df = new DecimalFormat("#.###", separador);
        
        return Double.parseDouble(df.format(valor));
    }
}
