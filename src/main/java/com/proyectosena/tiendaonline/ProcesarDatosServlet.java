package com.proyectosena.tiendaonline;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProcesarDatosServlet", urlPatterns = "/registro_usuario")
public class ProcesarDatosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String identificacion = request.getParameter("identificacion");
        String contraseña = request.getParameter("contra");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Datos de registro</h1>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Apellido: " + apellido + "</p>");
        out.println("<p>Celular: " + celular + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<p>Identificación: " + identificacion + "</p>");
        out.println("<p>Contraseña: " + contraseña + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
