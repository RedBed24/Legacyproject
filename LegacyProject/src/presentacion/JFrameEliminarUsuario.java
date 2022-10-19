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

public class JFrameEliminarUsuario extends JFrame {

	private JPanel contentPane; // Formulario 
	private JTextField textFieldLogin; // Campo de texto del login
	private JTextField textFieldPassword; // Campo de texto de la password
	private JTextPane textPaneEstado; // Panel de texto

	/**
	 * Creación del formulario
	 */

	public JFrameEliminarUsuario() {
		
		// Ventana emergente "Eliminar un usuario registrado"
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);  /* Nos permite colocar los componentes del formulario exactamente donde queramos
									   * aunque no nos permita redimensionar la ventana. */
		setContentPane(contentPane);
		setTitle("Eliminar un usuario registrado");
		setResizable(false); // Nuestra ventana no dispone de mecanismo de redimensión por lo que hacemos que no se pueda maximizar.
		setBounds(200, 200, 437, 300); // Dimensiones fijas del formulario al abrirlo.
		setLocationRelativeTo(null); // Tras fijar las dimensiones, hacemos que el formulario se abra en el centro de la pantalla.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solamente la ventana emergente
		
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
		textPaneEstado.setToolTipText("Panel para mostrar el restultado de la comprobación de login o las excepciones lanzadas");
		textPaneEstado.setEditable(false);
		textPaneEstado.setBounds(6, 150, 407, 102);
		contentPane.add(textPaneEstado);
		
		// Botón Eliminar usuario
		
		JButton btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) { // Elimina al usuario (o no) e informa de la situación.
				try {
					// Se crea un objeto Usuario con lo escrito en las casillas correspondientes al login y al password.
					Usuario u = new Usuario(textFieldLogin.getText(), textFieldPassword.getText());
					
					// Elimina al usuario (o no) de la base de datos e informa en el panel de texto la situación que se dé.
					if(u.eliminar())
						textPaneEstado.setText("Usuario eliminado correctamente");
					else
						textPaneEstado.setText("No se ha podido eliminar el usuario ya que no se encuentra registrado o no tiene esa contraseña.");
							
				} catch (InvalidLoginException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el login, debe tener al menos 4 caracteres.");
				} catch (InvalidPasswordException e) {
					textPaneEstado.setText("No se cumple el mínimo de caracteres en el password, debe tener al menos 4 caracteres.");
				} catch (Exception e) {
					textPaneEstado.setText("No se ha podido eliminar el usuario por una razón inesperada.");
				}
				
			}
		});
		btnEliminarUsuario.setBounds(260, 31, 141, 29); // Dimensiones fijas
		contentPane.add(btnEliminarUsuario);
		
		// Botón Limpiar
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Se limpian los campos de texto y el panel.
				textPaneEstado.setText("");
				textFieldLogin.setText("");
				textFieldPassword.setText("");
			}
		});
		btnLimpiar.setBounds(260, 75, 141, 29); // Dimensiones fijas
		contentPane.add(btnLimpiar);
	}
}
