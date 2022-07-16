package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import empresas11.clases.clsEjecutivo;

public class Ctrlejecutivo {

	clsEjecutivo ejecutivo = null;
	DBConecction DBConecction = null;

	public Ctrlejecutivo() {

		ejecutivo = new clsEjecutivo();
		DBConecction = new DBConecction();
	}

	public boolean crearEjecutivo(String unidadACargo, String oficioProfesion, String nombre, String primerApellido,
			String segundoApellido, String identificacion) {
		ejecutivo.setUnidadACargo(unidadACargo);
		ejecutivo.setOficioProfesion(oficioProfesion);
		ejecutivo.setNombre(nombre);
		ejecutivo.setPrimerApellido(primerApellido);
		ejecutivo.setSegundoApellido(segundoApellido);
		ejecutivo.setIdentificacion(identificacion);

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "INSERT INTO tb_persona(Nombre,primer_apellido,segundo_apellido,identificacion) VALUES(?,?,?,?)";
			PreparedStatement statementPersona = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statementPersona.setString(1, ejecutivo.getNombre());
			statementPersona.setString(2, ejecutivo.getPrimerApellido());
			statementPersona.setString(3, ejecutivo.getSegundoApellido());
			statementPersona.setString(4, ejecutivo.getIdentificacion());
			int ids = statementPersona.executeUpdate();
			if (ids > 0) {
				ResultSet llavePrimaria = statementPersona.getGeneratedKeys();
				if (llavePrimaria.next()) {
					int id_persona = llavePrimaria.getInt(1);
					query = "INSERT INTO tb_empleado(id_persona,oficio) VALUES(?,?)";
					PreparedStatement statementEmpleado = connection.prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);
					statementEmpleado.setInt(1, id_persona);
					statementEmpleado.setString(2, ejecutivo.getOficioProfesion());
					ids = statementEmpleado.executeUpdate();
					if (ids > 0) {
						ResultSet llavePrimaria2 = statementEmpleado.getGeneratedKeys();
						if (llavePrimaria2.next()) {
							int id_empleado = llavePrimaria2.getInt(ids);
							query = "INSERT INTO tb_ejecutivo(id_empleado,unidad_cargo) VALUES(?,?)";
							statementEmpleado = connection.prepareStatement(query);
							statementEmpleado.setInt(1, id_empleado);
							statementEmpleado.setString(2, ejecutivo.getUnidadACargo());
							statementEmpleado.executeUpdate();

							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return false;
		}

	}

	public clsEjecutivo ConsultarEjecutivo(String identificacion, JTable table) {

		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Unidad");
		modelo.addColumn("Oficio");
		modelo.addColumn("Nombre");
		modelo.addColumn("Primer Apellido");
		modelo.addColumn("Segundo Apellido");
		modelo.addColumn("Identificacion");
		table.setModel(modelo);

		String[] datos_usuario = new String[6];
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona INNER JOIN tb_ejecutivo as ejecutivo ON persona.id = ejecutivo.id_empleado WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, identificacion);
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				String oficio = resultado.getString(8);
				String unidad_cargo = resultado.getString(11);
				String nombre = resultado.getString(2);
				String primer_apellido = resultado.getString(3);
				String segundo_apellido = resultado.getString(4);
				String identificacion1 = resultado.getString(5);

				datos_usuario[0] = unidad_cargo;
				datos_usuario[1] = oficio;
				datos_usuario[2] = nombre;
				datos_usuario[3] = primer_apellido;
				datos_usuario[4] = segundo_apellido;
				datos_usuario[5] = identificacion;
				modelo.addRow(datos_usuario);
				ejecutivo = new clsEjecutivo(unidad_cargo, oficio, nombre, primer_apellido, segundo_apellido,
						identificacion1);
				return ejecutivo;

			}

			System.out.println("No se encontró el empleado");
			return null;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return null;
		}
	}

	public boolean EliminarEjecutivo(String identificacion) {

		ejecutivo.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String identificacion1 = ejecutivo.getIdentificacion();
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, identificacion1);
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				int id_persona = resultado.getInt(1);
				int id_empleado = resultado.getInt(6);

				query = "DELETE FROM tb_empleado WHERE id = ?";
				PreparedStatement statementEmpleado = connection.prepareStatement(query);
				statementEmpleado.setInt(1, id_empleado);
				int filasEliminadas = statementEmpleado.executeUpdate();
				if (filasEliminadas == 1) {
					System.out.println("Se eliminó el registro de la tabla empleado");
					query = "DELETE FROM tb_persona WHERE id = ?";
					statementPersona = connection.prepareStatement(query);
					statementPersona.setInt(1, id_persona);
					filasEliminadas = statementPersona.executeUpdate();
					if (filasEliminadas == 1) {

						return true;
					}
				}
			}

			return false;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return false;
		}

	}

	public boolean EditarEjecutivo(String unidadACargo, String oficioProfesion, String nombre, String primerApellido,
			String segundoApellido, String identificacion) {

		ejecutivo.setOficioProfesion(oficioProfesion);
		ejecutivo.setNombre(nombre);
		ejecutivo.setPrimerApellido(primerApellido);
		ejecutivo.setSegundoApellido(segundoApellido);
		ejecutivo.setIdentificacion(identificacion);
		ejecutivo.setUnidadACargo(unidadACargo);

		ejecutivo.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona INNER JOIN tb_ejecutivo as ejecutivo ON persona.id = ejecutivo.id_empleado WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, ejecutivo.getIdentificacion());
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				int id_persona = resultado.getInt(1);
				int id_empleado = resultado.getInt(6);
				int id_ejecutivo = resultado.getInt(9);

				query = "UPDATE tb_persona SET nombre=?,primer_apellido=?,segundo_apellido=? WHERE id=?";
				statementPersona = connection.prepareStatement(query);
				statementPersona.setString(1, ejecutivo.getNombre());
				statementPersona.setString(2, ejecutivo.getPrimerApellido());
				statementPersona.setString(3, ejecutivo.getSegundoApellido());
				statementPersona.setInt(4, id_persona);
				statementPersona.executeUpdate();
				query = "UPDATE tb_empleado SET oficio=? WHERE id=?";
				PreparedStatement statementEmpleado = connection.prepareStatement(query);
				statementEmpleado.setString(1, ejecutivo.getOficioProfesion());
				statementEmpleado.setInt(2, id_empleado);
				statementEmpleado.executeUpdate();

				query = "UPDATE tb_ejecutivo SET unidad_cargo=? WHERE id=?";
				PreparedStatement statementEjecutivo = connection.prepareStatement(query);
				statementEjecutivo.setString(1, ejecutivo.getUnidadACargo());
				statementEjecutivo.setInt(2, id_ejecutivo);
				statementEjecutivo.executeUpdate();

				return true;
			} else {

				return false;
			}

		} catch (Exception e) {

			return false;
		}
	}

}
