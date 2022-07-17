package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Ctrlejecutivo;
import controlador.CtrlemPleado;
import controlador.Ctrlempresa;
import controlador.Ctrlgerente;
import empresas11.clases.clsEjecutivo;
import empresas11.clases.clsEmpleado;
import empresas11.clases.clsGerente;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.LineBorder;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textpApellido;
	private JTextField textsApellido;
	private JTextField textidentificacion;
	private JTextField textoficio;
	private JComboBox<?> comboBox;
	private JTextField textundCargo;
	private JLabel lblUnidadACargo;
	private JTextField texttarjeta;
	private JButton btnAsociar;
	private JButton btnDesasociar;
	private JTable table;
	private JPanel Empresa;
	private JTextField textnComercial;
	private JTextField Direccion;
	private JLabel lblRegistroDeEmpleados_1;
	private JTable table_gerentes;
	private JLabel lblgerente;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cambiopanel(JPanel panel) {

		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.repaint();
		contentPane.revalidate();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Main() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel Empleado = new JPanel();
		Empleado.setBackground(new Color(255, 204, 153));
		contentPane.add(Empleado, "name_12279398462500");
		Empleado.setLayout(null);

		textNombre = new JTextField();
		textNombre.setBounds(56, 140, 164, 26);
		Empleado.add(textNombre);
		textNombre.setColumns(10);

		textpApellido = new JTextField();
		textpApellido.setColumns(10);
		textpApellido.setBounds(56, 225, 164, 26);
		Empleado.add(textpApellido);

		textsApellido = new JTextField();
		textsApellido.setColumns(10);
		textsApellido.setBounds(56, 312, 164, 26);
		Empleado.add(textsApellido);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(56, 104, 122, 26);
		Empleado.add(lblNewLabel);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido");
		lblPrimerApellido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPrimerApellido.setBounds(56, 188, 186, 26);
		Empleado.add(lblPrimerApellido);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
		lblSegundoApellido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblSegundoApellido.setBounds(56, 276, 164, 26);
		Empleado.add(lblSegundoApellido);

		JLabel lblRegistroDeEmpleados = new JLabel("Registro De Empleados");
		lblRegistroDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeEmpleados.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRegistroDeEmpleados.setIcon(new ImageIcon(Main.class.getResource("/vistas/leader.png")));
		lblRegistroDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblRegistroDeEmpleados.setBounds(401, 20, 312, 74);
		Empleado.add(lblRegistroDeEmpleados);

		JButton btnNewButton = new JButton("Crear");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(153, 102, 255));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/vistas/agregar-usuario.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = textNombre.getText();
				String pApellido = textpApellido.getText();
				String sApellido = textsApellido.getText();
				String oficio = textoficio.getText();
				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();
				Ctrlgerente gerente = new Ctrlgerente();
				boolean rejistrado = false;
				System.out.println(tpEmpleado);
				if (tpEmpleado == "empleado") {
					rejistrado = emPleado.crearEmpleado(oficio, nombre, pApellido, sApellido, identificacion);
					if (rejistrado) {

						JOptionPane.showInternalMessageDialog(null, "Empleado Creado");
					}

				} else if (tpEmpleado == "ejecutivo") {
					String unidad_cargo = textundCargo.getText();
					rejistrado = ejecutivo.crearEjecutivo(unidad_cargo, oficio, nombre, pApellido, sApellido,
							identificacion);

					if (rejistrado) {

						JOptionPane.showInternalMessageDialog(null, "Ejecutivo Creado");
					}

				} else if (tpEmpleado == "gerente") {
					String tarjetaProfecuional = texttarjeta.getText();
					gerente.crearEjecutivo(oficio, tarjetaProfecuional, nombre, pApellido, sApellido, identificacion);
					JOptionPane.showInternalMessageDialog(null, "gerente Creado");

				} else {

				}
				texttarjeta.setText("");
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(56, 526, 154, 33);
		Empleado.add(btnNewButton);

		textidentificacion = new JTextField();
		textidentificacion.setColumns(10);
		textidentificacion.setBounds(276, 140, 164, 26);
		Empleado.add(textidentificacion);

		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblIdentificacion.setBounds(276, 104, 164, 26);
		Empleado.add(lblIdentificacion);

		textoficio = new JTextField();
		textoficio.setColumns(10);
		textoficio.setBounds(276, 225, 164, 26);
		Empleado.add(textoficio);

		JLabel lblOficio = new JLabel("Oficio");
		lblOficio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblOficio.setBounds(286, 188, 99, 26);
		Empleado.add(lblOficio);

		JLabel lblTipoDeEmpleado = new JLabel("Tipo De Empleado");
		lblTipoDeEmpleado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblTipoDeEmpleado.setBounds(266, 277, 164, 26);
		Empleado.add(lblTipoDeEmpleado);

		comboBox = new JComboBox();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String tpEmpleado = (String) comboBox.getSelectedItem();

				if (tpEmpleado == "ejecutivo") {

					textundCargo.setEnabled(true);
					btnAsociar.setEnabled(true);
					btnDesasociar.setEnabled(true);
					btnAsociar.setBackground(new Color(0, 102, 255));
					btnDesasociar.setBackground(new Color(255, 0, 51));
					btnAsociar.setIcon(new ImageIcon(Main.class.getResource("/vistas/agregar-usuario.png")));
					btnDesasociar.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario1.png")));
					textundCargo.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
					textundCargo.setBackground(Color.LIGHT_GRAY);

				} else if (tpEmpleado == "gerente") {
					texttarjeta.setEnabled(true);
					textundCargo.setEnabled(false);
					btnAsociar.setEnabled(false);
					btnDesasociar.setEnabled(false);
					btnAsociar.setBackground(new Color(240, 240, 240));
					btnDesasociar.setBackground(new Color(240, 240, 240));
					textundCargo.setBackground(new Color(255, 255, 255));
					btnAsociar.setIcon(null);
					btnDesasociar.setIcon(null);
				}

				else {
					textundCargo.setEnabled(false);
					btnAsociar.setEnabled(false);
					btnDesasociar.setEnabled(false);
					btnAsociar.setBackground(new Color(240, 240, 240));
					btnDesasociar.setBackground(new Color(240, 240, 240));
					textundCargo.setBackground(new Color(255, 255, 255));
					btnAsociar.setIcon(null);
					btnDesasociar.setIcon(null);
				}

			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "empleado", "ejecutivo", "gerente" }));
		comboBox.setBounds(266, 312, 174, 26);
		Empleado.add(comboBox);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.setBackground(new Color(0, 153, 255));
		btnConsultar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnConsultar.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				clsEmpleado empleado = new clsEmpleado();
				clsEjecutivo Ejecutivo = new clsEjecutivo();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();
				Ctrlgerente gerente = new Ctrlgerente();
				System.out.println(tpEmpleado);
				if (tpEmpleado == "empleado") {
					empleado = emPleado.Consultar(identificacion, table);
					System.out.println(empleado.getNombre());

				} else if (tpEmpleado == "ejecutivo") {

					Ejecutivo = ejecutivo.ConsultarEjecutivo(identificacion, table);

					textundCargo.setText(Ejecutivo.getUnidadACargo());
					JOptionPane.showInternalMessageDialog(null, "Ejecutivo Consultado Con Exito");

				} else if (tpEmpleado == "gerente") {

					clsGerente Gerente = gerente.ConsultarEgerente(identificacion, table);
					texttarjeta.setText(Gerente.getTarjetaProfesional());
					textundCargo.setText("");
					JOptionPane.showInternalMessageDialog(null, "Gerente Consultado Con Exito");

				} else {

				}
				texttarjeta.setText("");
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar.setBounds(276, 526, 154, 33);
		Empleado.add(btnConsultar);

		JButton btnEditar = new JButton("Eliminar");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBackground(new Color(255, 102, 102));
		btnEditar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEditar.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario1.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();
				Ctrlgerente gerente = new Ctrlgerente();
				boolean eliminado = false;
				System.out.println(tpEmpleado);
				if (tpEmpleado == "empleado") {
					eliminado = emPleado.Eliminar(identificacion);
					if (eliminado) {
						JOptionPane.showInternalMessageDialog(null, "Empleado eliminado");
					}

				} else if (tpEmpleado == "ejecutivo") {
					eliminado = ejecutivo.EliminarEjecutivo(identificacion);
					if (eliminado) {
						JOptionPane.showInternalMessageDialog(null, "Ejecutivo eliminado");
					}

				} else if (tpEmpleado == "gerente") {
					gerente.EliminarEjecutivo(identificacion);
					if (eliminado) {
						JOptionPane.showInternalMessageDialog(null, "gerente eliminado");
					}

				} else {

				}
				texttarjeta.setText("");
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar.setBounds(276, 589, 154, 33);
		Empleado.add(btnEditar);

		JButton btnEditar_2 = new JButton("Editar");
		btnEditar_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar_2.setBackground(new Color(153, 255, 204));
		btnEditar_2.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEditar_2.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario (1).png")));
		btnEditar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = textNombre.getText();
				String pApellido = textpApellido.getText();
				String sApellido = textsApellido.getText();
				String oficio = textoficio.getText();
				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();
				Ctrlgerente gerente = new Ctrlgerente();

				System.out.println(tpEmpleado);
				if (tpEmpleado == "empleado") {
					boolean eliminado = emPleado.Editar(oficio, nombre, pApellido, sApellido, identificacion);
					if (eliminado) {
						JOptionPane.showInternalMessageDialog(null, "Empleado Editado");
					}

				} else if (tpEmpleado == "ejecutivo") {
					String unidad_cargo = textundCargo.getText();

					ejecutivo.EditarEjecutivo(unidad_cargo, oficio, nombre, pApellido, sApellido, identificacion);
					JOptionPane.showInternalMessageDialog(null, "Ejecutivo Editado");

				} else if (tpEmpleado == "gerente") {
					String tarjetaProfecuional = texttarjeta.getText();
					gerente.EditarGerente(tarjetaProfecuional, oficio, nombre, pApellido, sApellido, identificacion);
					JOptionPane.showInternalMessageDialog(null, "gerente Editado");

				} else {

				}
				texttarjeta.setText("");
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}

		});
		btnEditar_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_2.setBounds(56, 589, 154, 33);
		Empleado.add(btnEditar_2);

		textundCargo = new JTextField();

		textundCargo.setEnabled(false);
		textundCargo.setColumns(10);
		textundCargo.setBounds(56, 479, 384, 26);
		Empleado.add(textundCargo);

		lblUnidadACargo = new JLabel("Unidad A Cargo");
		lblUnidadACargo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUnidadACargo.setBounds(56, 443, 384, 26);
		Empleado.add(lblUnidadACargo);

		texttarjeta = new JTextField();
		texttarjeta.setEnabled(false);
		texttarjeta.setColumns(10);
		texttarjeta.setBounds(56, 407, 384, 26);
		Empleado.add(texttarjeta);

		JLabel TARJETA = new JLabel("Tarjeta Profesional");
		TARJETA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		TARJETA.setBounds(56, 371, 384, 26);
		Empleado.add(TARJETA);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(204, 204, 204));
		scrollPane.setBounds(479, 142, 619, 417);
		Empleado.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 204, 153));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				colocardatos(table, textundCargo, textoficio, textNombre, textpApellido, textsApellido,
						textidentificacion);
			}
		});
		scrollPane.setViewportView(table);

		btnDesasociar = new JButton("Desasociar");

		btnDesasociar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnDesasociar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDesasociar.setEnabled(false);
		btnDesasociar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDesasociar.setBounds(799, 589, 154, 33);
		Empleado.add(btnDesasociar);

		btnAsociar = new JButton("Asociar");
		btnAsociar.setBackground(new Color(240, 240, 240));

		btnAsociar.setHorizontalTextPosition(SwingConstants.LEFT);

		btnAsociar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAsociar.setEnabled(false);
		btnAsociar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAsociar.setBounds(579, 589, 154, 33);
		Empleado.add(btnAsociar);

		JLabel lblNewLabel_1 = new JLabel("Registrar Empresa");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Ctrlempresa empresa = new Ctrlempresa();
				clsGerente gerente = empresa.consultarEgerente(table_gerentes);

				if (gerente != null) {
					JOptionPane.showMessageDialog(null, "Encontramos un Gerente");
					cambiopanel(Empresa);
				} else {
					JOptionPane.showMessageDialog(null, "NO puedes crear una empresa sin tener un gerente Antes¡");
				}

			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/buildings.png")));
		lblNewLabel_1.setBounds(831, 22, 215, 74);
		Empleado.add(lblNewLabel_1);

		Empresa = new JPanel();
		Empresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ctrlempresa empresa = new Ctrlempresa();
				lblgerente.setText("Gerentes");
				scrollPane_1.setBounds(201, 184, 686, 148);
				empresa.consultarEgerente(table_gerentes);

			}
		});

		contentPane.add(Empresa, "name_99648583903800");
		Empresa.setBackground(new Color(255, 204, 153));
		Empresa.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Registrar Empresa");
		lblNewLabel_1_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/buildings.png")));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(347, 37, 215, 74);
		Empresa.add(lblNewLabel_1_1);

		textnComercial = new JTextField();
		textnComercial.setColumns(10);
		textnComercial.setBounds(52, 440, 193, 26);
		Empresa.add(textnComercial);

		JLabel lblNewLabel_2 = new JLabel("Nombre Comersial");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(52, 404, 164, 26);
		Empresa.add(lblNewLabel_2);

		JLabel lblIdentificacion_1 = new JLabel("Direccion");
		lblIdentificacion_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblIdentificacion_1.setBounds(301, 404, 164, 26);
		Empresa.add(lblIdentificacion_1);

		Direccion = new JTextField();
		Direccion.setColumns(10);
		Direccion.setBounds(301, 440, 193, 26);
		Empresa.add(Direccion);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 1, true));
		panel.setBackground(new Color(255, 204, 153));
		panel.setBounds(571, 406, 488, 246);
		Empresa.add(panel);
		panel.setLayout(null);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(214, 65, 174, 26);
		panel.add(comboBox_1_1);
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTipoDeEmpleado_1_1_1 = new JLabel("Tipo De Empleado");
		lblTipoDeEmpleado_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblTipoDeEmpleado_1_1_1.setBounds(167, 25, 164, 26);
		panel.add(lblTipoDeEmpleado_1_1_1);

		JButton btnContratar = new JButton("Contratar");
		btnContratar.setBackground(new Color(204, 153, 255));
		btnContratar.setIcon(new ImageIcon(Main.class.getResource("/vistas/agregar-usuario.png")));
		btnContratar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContratar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContratar.setBounds(69, 129, 154, 33);
		panel.add(btnContratar);

		JButton btnConsultar_1 = new JButton("Consultar");
		btnConsultar_1.setBackground(new Color(204, 153, 153));
		btnConsultar_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario.png")));
		btnConsultar_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar_1.setBounds(267, 129, 154, 33);
		panel.add(btnConsultar_1);

		JButton btnDespedir = new JButton("Despedir");
		btnDespedir.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario1.png")));
		btnDespedir.setBackground(new Color(255, 102, 102));
		btnDespedir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDespedir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDespedir.setBounds(267, 192, 154, 33);
		panel.add(btnDespedir);

		JButton btnEditar_2_1 = new JButton("Atualizar");
		btnEditar_2_1.setBackground(new Color(102, 153, 255));
		btnEditar_2_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/usuario (1).png")));
		btnEditar_2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_2_1.setBounds(69, 192, 154, 33);
		panel.add(btnEditar_2_1);

		JLabel lblTipoDeEmpleado_1 = new JLabel("Empleado");
		lblTipoDeEmpleado_1.setBounds(69, 65, 103, 26);
		panel.add(lblTipoDeEmpleado_1);
		lblTipoDeEmpleado_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));

		JButton btnEditar_empresa = new JButton("Editar");
		btnEditar_empresa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar_empresa.setBackground(new Color(255, 255, 153));
		btnEditar_empresa.setIcon(new ImageIcon(Main.class.getResource("/vistas/edificios.png")));
		btnEditar_empresa.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEditar_empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre_comrecial = textnComercial.getText();
				String direccion = Direccion.getText();
				Ctrlempresa empresa = new Ctrlempresa();
				clsGerente gerente = empresa.consultarEgerente(new JTable());
				boolean Empresa = empresa.EditarEmpresa(nombre_comrecial, direccion, gerente.getIdentificacion());
				if (Empresa) {

					JOptionPane.showMessageDialog(contentPane, "empresa editada con exito");
				} else {
					JOptionPane.showMessageDialog(contentPane, "No se edito la empresa");
				}
			}
		});
		btnEditar_empresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_empresa.setBounds(52, 566, 154, 37);
		Empresa.add(btnEditar_empresa);

		JButton btn_CrearEmp = new JButton("Crear");
		btn_CrearEmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_CrearEmp.setBackground(new Color(51, 153, 255));
		btn_CrearEmp.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_CrearEmp.setIcon(new ImageIcon(Main.class.getResource("/vistas/edificios.png")));
		btn_CrearEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre_comrecial = textnComercial.getText();
				String direccion = Direccion.getText();
				Ctrlempresa empresa = new Ctrlempresa();
				clsGerente gerente = empresa.consultarEgerente(table_gerentes);
				boolean Empresa = empresa.crearEmpresa(nombre_comrecial, direccion, gerente.getIdentificacion());
				if (Empresa) {

					JOptionPane.showMessageDialog(contentPane, "empresa Creada");
				} else {
					JOptionPane.showMessageDialog(contentPane, "No se creo la Empresa");
				}

				textnComercial.setText("");
				Direccion.setText("");
			}
		});
		btn_CrearEmp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_CrearEmp.setBounds(52, 503, 154, 37);
		Empresa.add(btn_CrearEmp);

		JButton btnConsultar_empresa = new JButton("Consultar");
		btnConsultar_empresa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar_empresa.setBackground(new Color(204, 204, 255));
		btnConsultar_empresa.setIcon(new ImageIcon(Main.class.getResource("/vistas/edificios.png")));
		btnConsultar_empresa.setHorizontalTextPosition(SwingConstants.LEFT);
		btnConsultar_empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ctrlempresa empresa = new Ctrlempresa();

				String id = empresa.ConsultarEmpresa(table_gerentes);
				lblgerente.setText("Empresas");
				scrollPane_1.setBounds(330, 184, 367, 148);
				if (id != "") {
					JOptionPane.showMessageDialog(null, "empresa consultada con exito");
				} else {
					JOptionPane.showMessageDialog(null, "consultada fallida no hay empresas crea una porfavor¡");
				}

				textnComercial.setText("");
				Direccion.setText("");
			}
		});
		btnConsultar_empresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar_empresa.setBounds(340, 503, 154, 37);
		Empresa.add(btnConsultar_empresa);

		JButton btn_Empresa_eliminar = new JButton("Eliminar");
		btn_Empresa_eliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Empresa_eliminar.setBackground(new Color(255, 102, 102));
		btn_Empresa_eliminar.setIcon(new ImageIcon(Main.class.getResource("/vistas/edificios.png")));
		btn_Empresa_eliminar.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_Empresa_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ctrlempresa empresa = new Ctrlempresa();
				String nombre = textnComercial.getText();
				boolean entrar = empresa.EliminaEmpresa(nombre);
				if (entrar) {

					JOptionPane.showMessageDialog(contentPane, "empresa eliminada");
				} else {
					JOptionPane.showMessageDialog(contentPane, "No se elimino la empresa");
				}
				textnComercial.setText("");
				Direccion.setText("");
			}
		});
		btn_Empresa_eliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Empresa_eliminar.setBounds(340, 566, 154, 37);
		Empresa.add(btn_Empresa_eliminar);

		lblgerente = new JLabel("Gerentes");
		lblgerente.setBounds(472, 148, 164, 26);
		Empresa.add(lblgerente);
		lblgerente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(255, 255, 204));
		scrollPane_1.setBounds(201, 184, 686, 148);
		Empresa.add(scrollPane_1);

		table_gerentes = new JTable();
		table_gerentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				colocardatosempresa(table_gerentes, textnComercial, Direccion);
			}
		});
		scrollPane_1.setViewportView(table_gerentes);

		lblRegistroDeEmpleados_1 = new JLabel("Registro De Empleados");
		lblRegistroDeEmpleados_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistroDeEmpleados_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cambiopanel(Empleado);
				lblgerente.setText("Gerentes");
				scrollPane_1.setBounds(201, 184, 686, 148);

			}
		});
		lblRegistroDeEmpleados_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/leader.png")));
		lblRegistroDeEmpleados_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRegistroDeEmpleados_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeEmpleados_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblRegistroDeEmpleados_1.setBounds(660, 37, 312, 74);
		Empresa.add(lblRegistroDeEmpleados_1);
	}

	public void colocardatos(JTable table, JTextField unidad, JTextField oficio, JTextField nombre, JTextField primerA,
			JTextField segundoA, JTextField ident) {

		int fila = table.getSelectedRow();

		String unidad1 = table.getValueAt(fila, 0).toString();
		String oficio1 = table.getValueAt(fila, 1).toString();
		String nombre1 = table.getValueAt(fila, 2).toString();
		String primerA1 = table.getValueAt(fila, 3).toString();
		String segundoA1 = table.getValueAt(fila, 4).toString();
		String ident1 = table.getValueAt(fila, 5).toString();

		unidad.setText(unidad1);
		oficio.setText(oficio1);
		nombre.setText(nombre1);
		primerA.setText(primerA1);
		segundoA.setText(segundoA1);
		ident.setText(ident1);

	}

	public void colocardatosempresa(JTable table, JTextField nombre_c, JTextField direccion) {
		int fila = table.getSelectedRow();
		String nombre_c1 = table.getValueAt(fila, 0).toString();
		String direccion1 = table.getValueAt(fila, 1).toString();
		nombre_c.setText(nombre_c1);
		direccion.setText(direccion1);
	}

	public void limpiar(JTextField unidad, JTextField oficio, JTextField nombre, JTextField primerA,
			JTextField segundoA, JTextField ident) {

		unidad.setText("");
		oficio.setText("");
		nombre.setText("");
		primerA.setText("");
		segundoA.setText("");
		ident.setText("");

	}

	class fondo2 extends JPanel {
		private Image img;

		public void paint(Graphics g) {

			img = new ImageIcon(getClass().getResource("/vistas/bg.jpg")).getImage();

			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	}
}
