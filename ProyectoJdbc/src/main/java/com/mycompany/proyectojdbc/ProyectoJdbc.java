/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectojdbc;

/**
 *
 * @author IRDOR
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProyectoJdbc {

    // Datos de conexión (ajusta si tu BD o puerto son distintos)
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP suele ir sin contraseña

    public static void main(String[] args) {

        Connection conn = null;

        try {
            // 1. (Opcional) Cargar el driver JDBC de MySQL
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Crear la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con MySQL.\n");

            // 3. Sentencia SQL para insertar UNA fila
            String sql = "INSERT INTO tabla (nombre, email) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // --------- PRIMERA FILA ----------
            String nombre1 = "Pepito Perez";
            String email1  = "pepitoperez@email.com";

            ps.setString(1, nombre1);
            ps.setString(2, email1);

            int filas1 = ps.executeUpdate();

            if (filas1 > 0) {
                System.out.println("Fila 1 insertada correctamente:");
                System.out.println("  Nombre: " + nombre1);
                System.out.println("  Email : " + email1 + "\n");
            }

            // --------- SEGUNDA FILA ----------
            String nombre2 = "Pepita Lopez";
            String email2  = "pepita.lopez@example.com";

            ps.setString(1, nombre2); // reutilizamos el mismo PreparedStatement
            ps.setString(2, email2);

            int filas2 = ps.executeUpdate();

            if (filas2 > 0) {
                System.out.println("Fila 2 insertada correctamente:");
                System.out.println("  Nombre: " + nombre2);
                System.out.println("  Email : " + email2 + "\n");
            }

            // 4. Cerrar recursos
            ps.close();
            conn.close();
            System.out.println("Conexión cerrada.");

        } catch (SQLException e) {
            System.out.println("Error en conexión o inserción: " + e.getMessage());
        }
    }
}
