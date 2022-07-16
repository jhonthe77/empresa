package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import empresas11.clases.clsEmpleado;

public class CtrlemPleado {

	clsEmpleado empleado = null;
	DBConecction DBConecction = null;

	public CtrlemPleado() {
		empleado = new clsEmpleado();
		DBConecction = new DBConecction();
	}

	public boolean crearEmpleado(String oficioProfesion, String nombre, String primerApellido, String segundoApellido,
			String identificacion) {
		empleado.setOficioProfesion(oficioProfesion);
		empleado.setNombre(nombre);
		empleado.setPrimerApellido(primerApellido);
		empleado.setSegundoApellido(segundoApellido);
		empleado.setIdentificacion(identificacion);

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "INSERT INTO tb_persona(Nombre,primer_apellido,segundo_apellido,identificacion) VALUES(?,?,?,?)";
			PreparedStatement statementPersona = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statementPersona.setString(1, empleado.getNombre());
			statementPersona.setString(2, empleado.getPrimerApellido());
			statementPersona.setString(3, empleado.getSegundoApellido());
			statementPersona.setString(4, empleado.getIdentificacion());
			int ids = statementPersona.executeUpdate();
			if (ids > 0) {
				ResultSet llavePrimaria = statementPersona.getGeneratedKeys();
				if (llavePrimaria.next()) {
					int id_persona = llavePrimaria.getInt(1);
					query = "INSERT INTO tb_empleado(id_persona,oficio) VALUES(?,?)";
					PreparedStatement statementEmpleado = connection.prepareStatement(query);
					statementEmpleado.setInt(1, id_persona);
					statementEmpleado.setString(2, empleado.getOficioProfesion());
					ids = statementEmpleado.executeUpdate();
					if (ids > 0) {

						return true;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("La excepción: " + e);

		}
		return false;

	}

	public clsEmpleado Consultar(String identificacion, JTable table) {

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Oficio");
		modelo.addColumn("Nombre");
		modelo.addColumn("Primer Apellido");
		modelo.addColumn("Segundo Apellido");
		modelo.addColumn("Identificacion");
		table.setModel(modelo);
		String[] datos_usuario = new String[5];

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, identificacion);
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				String nombre = resultado.getString(2);
				String primer_apellido = resultado.getString(3);
				String segundo_apellido = resultado.getString(4);
				String oficio = resultado.getString(8);
				empleado.setTipoEmpleado(query);

				datos_usuario[0] = oficio;
				datos_usuario[1] = nombre;
				datos_usuario[2] = primer_apellido;
				datos_usuario[3] = segundo_apellido;
				datos_usuario[4] = identificacion;
				modelo.addRow(datos_usuario);

				empleado = new clsEmpleado(oficio, nombre, primer_apellido, segundo_apellido, identificacion);

				return empleado;

			}

			return null;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return null;
		}
	}

	public boolean Editar(String oficioProfesion, String nombre, String primerApellido, String segundoApellido,
			String identificacion) {

		empleado.setOficioProfesion(oficioProfesion);
		empleado.setNombre(nombre);
		empleado.setPrimerApellido(primerApellido);
		empleado.setSegundoApellido(segundoApellido);
		empleado.setIdentificacion(identificacion);

		empleado.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, empleado.getIdentificacion());
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				int id_persona = resultado.getInt(1);
				int id_empleado = resultado.getInt(6);
				query = "UPDATE tb_persona SET nombre=?,primer_apellido=?,segundo_apellido=? WHERE id=?";
				statementPersona = connection.prepareStatement(query);
				statementPersona.setString(1, empleado.getNombre());
				statementPersona.setString(2, empleado.getPrimerApellido());
				statementPersona.setString(3, empleado.getSegundoApellido());
				statementPersona.setInt(4, id_persona);
				statementPersona.executeUpdate();
				query = "UPDATE tb_empleado SET oficio=? WHERE id=?";
				PreparedStatement statementEmpleado = connection.prepareStatement(query);
				statementEmpleado.setString(1, empleado.getOficioProfesion());
				statementEmpleado.setInt(2, id_empleado);
				statementEmpleado.executeUpdate();
				System.out.println("Se editó el empleado");
				return true;
			} else {
				System.out.println("No se pudo editar el empleado");
				return false;
			}

		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return false;
		}
	}

	public boolean Eliminar(String identificacion) {

		empleado.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String identificacion1 = empleado.getIdentificacion();
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

}
