package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import empresas11.clases.clsEmpresa;
import empresas11.clases.clsGerente;

public class Ctrlempresa {
	clsEmpresa Empresa = null;
	DBConecction DBConecction = null;
	clsGerente gerente = null;

	public Ctrlempresa() {
		// TODO Auto-generated constructor stub
		DBConecction = new DBConecction();
		Empresa = new clsEmpresa();
		gerente = new clsGerente();

	}

	public boolean crearEmpresa(String nombre_comreia, String direccion, String edintificacion1) {
		Empresa.setNombreComercial(nombre_comreia);
		Empresa.setDireccion(direccion);
		String edintificacion = edintificacion1;

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona as p INNER JOIN tb_empleado as e ON p.id=e.id_persona INNER JOIN tb_gerente as g ON e.id_persona=g.id_empleado WHERE identificacion=?";

			PreparedStatement statementEmpresa = connection.prepareStatement(query);
			statementEmpresa.setString(1, edintificacion);
			ResultSet resul = statementEmpresa.executeQuery();
			if (resul.next()) {

				int id = resul.getInt(9);

				query = "INSERT INTO tb_empresa(nombre_comercial,direccion,id_gerente) VALUES(?,?,?)";

				statementEmpresa = connection.prepareStatement(query);

				statementEmpresa.setInt(3, id);
				statementEmpresa.setString(1, Empresa.getNombreComercial());

				statementEmpresa.setString(2, Empresa.getDireccion());

				statementEmpresa.executeUpdate();

				System.out.println("empresa editada");
				return true;
			}

		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return false;
		}
		return false;

	}

	public String ConsultarEmpresa(JTable table) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nombre Comercial");
		modelo.addColumn("Direccion");
		table.setModel(modelo);

		String[] datos = new String[2];
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {

			String query = "SELECT * FROM tb_empresa";

			PreparedStatement statementEmpresa = connection.prepareStatement(query);

			ResultSet resul = statementEmpresa.executeQuery();

			while (resul.next()) {
				String id = resul.getString(1);
				String nombre_comercial = resul.getString(2);
				String didreccion = resul.getString(3);

				datos[0] = nombre_comercial;
				datos[1] = didreccion;
				modelo.addRow(datos);
				return id;

			}

			System.out.println("empresa consultada con exito");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return "";
	}

	public boolean EditarEmpresa(String nombre_comreia, String direccion, String identificacion1) {
		Empresa.setNombreComercial(nombre_comreia);
		Empresa.setDireccion(direccion);
		String identificacion = identificacion1;

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {

			String query = "SELECT * FROM tb_persona as p INNER JOIN tb_empleado as e ON p.id=e.id_persona INNER JOIN tb_gerente as g ON e.id_persona=g.id_empleado INNER JOIN tb_empresa as l ON g.id=l.id_gerente WHERE identificacion=?";

			PreparedStatement statementEmpresa = connection.prepareStatement(query);
			statementEmpresa.setString(1, identificacion);
			ResultSet resul = statementEmpresa.executeQuery();
			if (resul.next()) {
				int id = resul.getInt(12);
				System.out.println(id);

				query = "UPDATE tb_empresa SET nombre_comercial=?,direccion=? WHERE id=?";

				statementEmpresa = connection.prepareStatement(query);
				statementEmpresa.setInt(3, id);
				statementEmpresa.setString(1, Empresa.getNombreComercial());

				statementEmpresa.setString(2, Empresa.getDireccion());

				statementEmpresa.executeUpdate();

				System.out.println("empresa editada con exito");
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return false;
	}

	public clsGerente consultarEgerente(JTable table) {

		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Tarjeta Profecional");
		modelo.addColumn("Oficio");
		modelo.addColumn("Nombre");
		modelo.addColumn("Primer Apellido");
		modelo.addColumn("Segundo Apellido");
		modelo.addColumn("Identificacion");
		table.setModel(modelo);

		String[] datos_usuario = new String[6];
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {

			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona INNER JOIN tb_gerente as gerente ON persona.id = gerente.id_empleado";
			PreparedStatement statementPersona = connection.prepareStatement(query);

			ResultSet resultado = statementPersona.executeQuery();
			while (resultado.next()) {
				String oficio = resultado.getString(8);
				String tarjeta_profecional = resultado.getString(11);
				String nombre = resultado.getString(2);
				String primer_apellido = resultado.getString(3);
				String segundo_apellido = resultado.getString(4);
				String identificacion = resultado.getString(5);

				datos_usuario[0] = tarjeta_profecional;
				datos_usuario[1] = oficio;
				datos_usuario[2] = nombre;
				datos_usuario[3] = primer_apellido;
				datos_usuario[4] = segundo_apellido;
				datos_usuario[5] = identificacion;
				modelo.addRow(datos_usuario);

				clsGerente gerente = new clsGerente(tarjeta_profecional, oficio, nombre, primer_apellido,
						segundo_apellido, identificacion);

				return gerente;

			}
			JOptionPane.showMessageDialog(null, "No se encontró el empleado");
			System.out.println("No se encontró el empleado ");
			return null;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);

		}
		return null;

	}

	public boolean EliminaEmpresa(String nombre) {

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {

			String query = "DELETE FROM tb_empresa WHERE nombre_comercial=?";
			PreparedStatement statementEmpresa = connection.prepareStatement(query);
			statementEmpresa = connection.prepareStatement(query);
			statementEmpresa.setString(1, nombre);
			int ids = statementEmpresa.executeUpdate();
			if (ids > 0) {
				System.out.println("empresa eliminada");

				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "No fue posible eliminada la empresa ");
		return false;
	}

}
