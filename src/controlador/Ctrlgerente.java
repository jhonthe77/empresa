package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import empresas11.clases.clsEjecutivo;
import empresas11.clases.clsGerente;

public class Ctrlgerente {
	clsGerente gerente = null;
	DBConecction DBConecction = null;
	public Ctrlgerente() {
		// TODO Auto-generated constructor stub
		gerente = new clsGerente();
		DBConecction = new DBConecction();
	}
	
	
	public boolean crearEjecutivo( String oficioProfesion,String tarjetaProfesional, String nombre, String primerApellido,
			String segundoApellido, String identificacion) {
	    gerente.setTarjetaProfesional(tarjetaProfesional);
		gerente.setOficioProfesion(oficioProfesion);
		gerente.setNombre(nombre);
		gerente.setPrimerApellido(primerApellido);
		gerente.setSegundoApellido(segundoApellido);
		gerente.setIdentificacion(identificacion);

		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "INSERT INTO tb_persona(Nombre,primer_apellido,segundo_apellido,identificacion) VALUES(?,?,?,?)";
			PreparedStatement statementPersona = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statementPersona.setString(1, gerente.getNombre());
			statementPersona.setString(2, gerente.getPrimerApellido());
			statementPersona.setString(3, gerente.getSegundoApellido());
			statementPersona.setString(4, gerente.getIdentificacion());
			int ids = statementPersona.executeUpdate();
			if (ids > 0) {
				ResultSet llavePrimaria = statementPersona.getGeneratedKeys();
				if (llavePrimaria.next()) {
					int id_persona = llavePrimaria.getInt(1);
					query = "INSERT INTO tb_empleado(id_persona,oficio) VALUES(?,?)";
					PreparedStatement statementEmpleado = connection.prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);
					statementEmpleado.setInt(1, id_persona);
					statementEmpleado.setString(2, gerente.getOficioProfesion());
					ids = statementEmpleado.executeUpdate();
					if (ids > 0) {
						ResultSet llavePrimaria2 = statementEmpleado.getGeneratedKeys();
						if (llavePrimaria2.next()) {
							int id_empleado = llavePrimaria2.getInt(ids);
							query = "INSERT INTO tb_gerente(id_empleado,tarjeta_profecional) VALUES(?,?)";
							statementEmpleado = connection.prepareStatement(query);
							statementEmpleado.setInt(1, id_empleado);
							statementEmpleado.setString(2, gerente.getTarjetaProfesional());
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
	
	
	
	public clsGerente ConsultarEgerente(String identificacion, JTable table) {

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
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona INNER JOIN tb_gerente as gerente ON persona.id = gerente.id_empleado WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, identificacion);
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				String oficio = resultado.getString(8);
				String tarjeta_profecional = resultado.getString(11);
				String nombre = resultado.getString(2);
				String primer_apellido = resultado.getString(3);
				String segundo_apellido = resultado.getString(4);
				String identificacion1 = resultado.getString(5);

				datos_usuario[0] = tarjeta_profecional;
				datos_usuario[1] = oficio;
				datos_usuario[2] = nombre;
				datos_usuario[3] = primer_apellido;
				datos_usuario[4] = segundo_apellido;
				datos_usuario[5] = identificacion;
				modelo.addRow(datos_usuario);
				gerente = new clsGerente(tarjeta_profecional, oficio, nombre, primer_apellido, segundo_apellido,
						identificacion1);
				return gerente;

			}

			System.out.println("No se encontró el empleado");
			return null;
		} catch (Exception e) {
			System.out.println("La excepción: " + e);
			return null;
		}
	}
	
	public boolean EliminarEjecutivo(String identificacion) {

		gerente.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String identificacion1 = gerente.getIdentificacion();
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
	
	public boolean EditarGerente(String tarjetaProfesional, String oficioProfesion, String nombre, String primerApellido,
			String segundoApellido, String identificacion) {

		gerente.setOficioProfesion(oficioProfesion);
		gerente.setNombre(nombre);
		gerente.setPrimerApellido(primerApellido);
		gerente.setSegundoApellido(segundoApellido);
		gerente.setIdentificacion(identificacion);
		gerente.setTarjetaProfesional(tarjetaProfesional);

		gerente.setIdentificacion(identificacion);
		try (Connection connection = DriverManager.getConnection(DBConecction.getUrl(), DBConecction.getUser(),
				DBConecction.getPassword())) {
			String query = "SELECT * FROM tb_persona AS persona INNER JOIN tb_empleado AS empleado ON persona.id = empleado.id_persona INNER JOIN tb_gerente as gerente ON persona.id = gerente.id_empleado WHERE identificacion = ?";
			PreparedStatement statementPersona = connection.prepareStatement(query);
			statementPersona.setString(1, gerente.getIdentificacion());
			ResultSet resultado = statementPersona.executeQuery();
			if (resultado.next()) {
				int id_persona = resultado.getInt(1);
				int id_empleado = resultado.getInt(6);
				int id_ejecutivo = resultado.getInt(9);

				query = "UPDATE tb_persona SET nombre=?,primer_apellido=?,segundo_apellido=? WHERE id=?";
				statementPersona = connection.prepareStatement(query);
				statementPersona.setString(1, gerente.getNombre());
				statementPersona.setString(2, gerente.getPrimerApellido());
				statementPersona.setString(3, gerente.getSegundoApellido());
				statementPersona.setInt(4, id_persona);
				statementPersona.executeUpdate();
				query = "UPDATE tb_empleado SET oficio=? WHERE id=?";
				PreparedStatement statementEmpleado = connection.prepareStatement(query);
				statementEmpleado.setString(1, gerente.getOficioProfesion());
				statementEmpleado.setInt(2, id_empleado);
				statementEmpleado.executeUpdate();

				query = "UPDATE tb_gerente SET tarjeta_profecional=? WHERE id=?";
				PreparedStatement statementEjecutivo = connection.prepareStatement(query);
				statementEjecutivo.setString(1, gerente.getTarjetaProfesional());
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
