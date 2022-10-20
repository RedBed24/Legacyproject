package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.Usuario;
import excepciones.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameNuevoUsuario extends JFrame {

	private JPanel contentPane; // Formulario
	private JTextField textFieldLogin; // Campo de texto del login
	private JTextField textFieldPassword; // Campo de texto de la password
	private JTextPane textPaneEstado; // Panel de texto
	
	/**
	 * Creación de la estructura
	 */
	
	public JFrameNuevoUsuario() {
		
		// Ventana emergente "Dar de alta a un nuevo usuario"

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); /* Nos permite colocar los componentes del formulario exactamente donde queramos
									  * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("Dar de alta a un nuevo usuario");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensión por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 300); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solamente la ventana emergente.
		
		// Etiquetas
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(30, 37, 69, 16);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(15, 81, 69, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(170, 130, 69, 16);
		lblEstado.setFont(new Font("Arial",3, 20));
		contentPane.add(lblEstado);
		
		// Valores de los atributos correspondientes a los campos de texto y el panel
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(87, 31, 150, 28);
		textFieldLogin.setColumns(10);
		contentPane.add(textFieldLogin);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(87, 75, 150, 28);
		textFieldPassword.setColumns(10);
		contentPane.add(textFieldPassword);

		textPaneEstado = new JTextPane();
		textPaneEstado.setToolTipText("Panel para mostrar el resultado de la comprobación de login o las excepciones lanzadas");
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(6, 150, 407, 102);
		contentPane.add(textPaneEstado);
		
		// Botón Dar de alta
		
		JButton btnAltaUsuario = new JButton("Dar de alta");
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Registra al usuario introducido (o no) e informa de la situación.
				try {
					// Se crea un objeto Usuario con lo escrito en las casillas correspondientes al login y al password.
					Usuario u = new Usuario(textFieldLogin.getText(), textFieldPassword.getText());
					
					// Introduce el usuario en la base de datos.
					u.insert();
					
					// Si no ha saltado una excepción anteriormente, la cuenta del usuario se ha creado correctamente.
					textPaneEstado.setText("Usuario creado correctamente.");
					
				} catch (InvalidLoginException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el login. Debe tener al menos 4 caracteres.");
				} catch (InvalidPasswordException e) {
					textPaneEstado.setText("No se cumple el minimo de caracteres en el password. Debe tener al menos 4 caracteres.");
				} catch (Exception e) {
					textPaneEstado.setText("No se ha podido crear el usuario porque ya existe uno con ese login.");
				}

			}
		});
		btnAltaUsuario.setBounds(260, 31, 141, 29); // Dimensiones fijas
		contentPane.add(btnAltaUsuario);

		// Botón Limpiar
		
		JButton buttonLimpiar = new JButton("Limpiar");
		buttonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Se limpian los campos de texto y el panel.
				textPaneEstado.setText("");
				textFieldLogin.setText("");
				textFieldPassword.setText("");
			}
		});
		buttonLimpiar.setBounds(260, 75, 141, 29); // Dimensiones fijas
		contentPane.add(buttonLimpiar);
	}
}
