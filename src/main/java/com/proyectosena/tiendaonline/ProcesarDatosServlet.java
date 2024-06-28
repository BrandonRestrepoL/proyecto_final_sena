package com.proyectosena.tiendaonline;

// importando paquetes servlets
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Paquetes para manejar las setencias sql
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

//Servlet conectado al formulario
@WebServlet(name = "ProcesarDatosServlet", urlPatterns = "/registro_usuario")
public class ProcesarDatosServlet extends HttpServlet {

    //Variables para acceder a la abse de dats
    private static final String url = "jdbc:mysql://localhost:3306/centralDB";
    private static final String username = "root";
    private static final String contra = "Lizz22-17Restrepo";

    // Método doPost para manejar las solicitudes https que se reciben del navegador
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Almacenamiento de datos enviados desde el browser
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String identificacion = request.getParameter("identificacion");
        String contraseña = request.getParameter("contra");

        //Configuración inicial para las conexiones a la base de datos
        Connection connection = null;
        PreparedStatement statement = null;

        //Sentencia sql para la inserscion de datos
        String sql = "INSERT INTO usuario (nombre, apellido, email, celular, identificacion, contraseña ) VALUES (?, ?, ?, ?, ?, ?)";

        //intentando establecer conexion a la base de datos y ejecutar Querys
        try {

            //Carga dinamica de controlador y conexión a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, contra);

            if (connection != null){
                System.out.println("Conexión exitosa a la base de datos");
            }

            // Asignando los valores que se insertaran a la tabla
            statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, celular);
            statement.setString(5, identificacion);
            statement.setString(6, contraseña);

            // Ejecución de sentencia sql
            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("A new user was inserted succefully");
            }

        //Manejo de excepxiones
        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("Error en la conexión a la base de datos");
            e.printStackTrace();

        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}