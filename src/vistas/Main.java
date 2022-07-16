package vistas;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Ctrlejecutivo;
import controlador.CtrlemPleado;
import empresas11.clases.clsEjecutivo;
import empresas11.clases.clsEmpleado;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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

@SuppressWarnings("serial")
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
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JLabel lblRegistroDeEmpleados_1;

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

				} else if (tpEmpleado == "") {

				} else {

				}
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(56, 526, 154, 26);
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
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String tpEmpleado = (String) comboBox.getSelectedItem();

				if (tpEmpleado == "ejecutivo") {

					textundCargo.setEnabled(true);
					btnAsociar.setEnabled(true);
					btnDesasociar.setEnabled(true);

				} else if (tpEmpleado == "gerente") {
					texttarjeta.setEnabled(true);
				}

				else {
					textundCargo.setEnabled(false);
				}

			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "empleado", "ejecutivo", "gerente" }));
		comboBox.setBounds(266, 312, 174, 26);
		Empleado.add(comboBox);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				clsEmpleado empleado = new clsEmpleado();
				clsEjecutivo Ejecutivo = new clsEjecutivo();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();

				System.out.println(tpEmpleado);
				if (tpEmpleado == "empleado") {
					empleado = emPleado.Consultar(identificacion,table);
					System.out.println(empleado.getNombre());

				} else if (tpEmpleado == "ejecutivo") {

					Ejecutivo = ejecutivo.ConsultarEjecutivo(identificacion, table);

					textundCargo.setText(Ejecutivo.getUnidadACargo());
					JOptionPane.showInternalMessageDialog(null, "Ejecutivo Consultado Con Exito");

				} else if (tpEmpleado == "") {

				} else {

				}
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar.setBounds(276, 526, 154, 26);
		Empleado.add(btnConsultar);

		JButton btnEditar = new JButton("Eliminar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String identificacion = textidentificacion.getText();
				String tpEmpleado = (String) comboBox.getSelectedItem();
				CtrlemPleado emPleado = new CtrlemPleado();
				Ctrlejecutivo ejecutivo = new Ctrlejecutivo();
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

				} else if (tpEmpleado == "") {

				} else {

				}
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar.setBounds(276, 589, 154, 26);
		Empleado.add(btnEditar);

		JButton btnEditar_2 = new JButton("Editar");
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

				} else if (tpEmpleado == "") {

				} else {

				}
				limpiar(textundCargo, textoficio, textNombre, textpApellido, textsApellido, textidentificacion);
			}

		});
		btnEditar_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_2.setBounds(56, 589, 154, 26);
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
				colocardatosCodigo(table, textundCargo, textoficio, textNombre, textpApellido, textsApellido,
						textidentificacion);
			}
		});
		scrollPane.setViewportView(table);

		btnDesasociar = new JButton("Desasociar");
		btnDesasociar.setEnabled(false);
		btnDesasociar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDesasociar.setBounds(799, 589, 154, 26);
		Empleado.add(btnDesasociar);

		btnAsociar = new JButton("Asociar");
		btnAsociar.setEnabled(false);
		btnAsociar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAsociar.setBounds(579, 589, 154, 26);
		Empleado.add(btnAsociar);

		JLabel lblNewLabel_1 = new JLabel("Registrar Empresa");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cambiopanel(Empresa);
			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/buildings.png")));
		lblNewLabel_1.setBounds(831, 22, 215, 74);
		Empleado.add(lblNewLabel_1);

		Empresa = new JPanel();
		contentPane.add(Empresa, "name_99648583903800");
		Empresa.setBackground(new Color(255, 204, 153));
		Empresa.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registrar Empresa");
		lblNewLabel_1_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/buildings.png")));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(347, 37, 215, 74);
		Empresa.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(84, 206, 164, 26);
		Empresa.add(textField);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre Comersial");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(84, 170, 164, 26);
		Empresa.add(lblNewLabel_2);
		
		JLabel lblIdentificacion_1 = new JLabel("Direccion");
		lblIdentificacion_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblIdentificacion_1.setBounds(304, 170, 164, 26);
		Empresa.add(lblIdentificacion_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(304, 206, 164, 26);
		Empresa.add(textField_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setBounds(188, 325, 174, 26);
		Empresa.add(comboBox_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 1, true));
		panel.setBackground(new Color(255, 204, 153));
		panel.setBounds(558, 346, 488, 246);
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
		btnContratar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContratar.setBounds(69, 129, 154, 26);
		panel.add(btnContratar);
		
		JButton btnConsultar_1 = new JButton("Consultar");
		btnConsultar_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar_1.setBounds(267, 129, 154, 26);
		panel.add(btnConsultar_1);
		
		JButton btnDespedir = new JButton("Despedir");
		btnDespedir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDespedir.setBounds(267, 192, 154, 26);
		panel.add(btnDespedir);
		
		JButton btnEditar_2_1 = new JButton("Atualizar");
		btnEditar_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_2_1.setBounds(69, 192, 154, 26);
		panel.add(btnEditar_2_1);
		
		JLabel lblTipoDeEmpleado_1 = new JLabel("Empleado");
		lblTipoDeEmpleado_1.setBounds(69, 65, 103, 26);
		panel.add(lblTipoDeEmpleado_1);
		lblTipoDeEmpleado_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		
		JButton btnEditar_2_1_1 = new JButton("Editar");
		btnEditar_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_2_1_1.setBounds(88, 466, 154, 26);
		Empresa.add(btnEditar_2_1_1);
		
		JButton btnNewButton_1_1 = new JButton("Crear");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(88, 403, 154, 26);
		Empresa.add(btnNewButton_1_1);
		
		JButton btnConsultar_1_1 = new JButton("Consultar");
		btnConsultar_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar_1_1.setBounds(286, 403, 154, 26);
		Empresa.add(btnConsultar_1_1);
		
		JButton btnEditar_1_1 = new JButton("Eliminar");
		btnEditar_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar_1_1.setBounds(286, 466, 154, 26);
		Empresa.add(btnEditar_1_1);
		
		JLabel lblTipoDeEmpleado_1_1 = new JLabel("Gerente");
		lblTipoDeEmpleado_1_1.setBounds(188, 289, 164, 26);
		Empresa.add(lblTipoDeEmpleado_1_1);
		lblTipoDeEmpleado_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(558, 170, 488, 145);
		Empresa.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		lblRegistroDeEmpleados_1 = new JLabel("Registro De Empleados");
		lblRegistroDeEmpleados_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistroDeEmpleados_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cambiopanel(Empleado);
			}
		});
		lblRegistroDeEmpleados_1.setIcon(new ImageIcon(Main.class.getResource("/vistas/leader.png")));
		lblRegistroDeEmpleados_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRegistroDeEmpleados_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeEmpleados_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblRegistroDeEmpleados_1.setBounds(660, 37, 312, 74);
		Empresa.add(lblRegistroDeEmpleados_1);
	}

	public void colocardatosCodigo(JTable table, JTextField unidad, JTextField oficio, JTextField nombre,
			JTextField primerA, JTextField segundoA, JTextField ident) {

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

	public void limpiar(JTextField unidad, JTextField oficio, JTextField nombre, JTextField primerA,
			JTextField segundoA, JTextField ident) {

		unidad.setText("");
		oficio.setText("");
		nombre.setText("");
		primerA.setText("");
		segundoA.setText("");
		ident.setText("");

	}
}
