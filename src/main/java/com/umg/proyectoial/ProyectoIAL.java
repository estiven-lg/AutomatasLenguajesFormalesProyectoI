package com.umg.proyectoial;

import Services.DatabaseConnection;
import com.umg.proyectoial.screens.MainMenu;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProyectoIAL {

    public static void main(String[] args) throws SQLException {

        ResultSet resultSet = DatabaseConnection.executeQuery("SELECT name from clientes");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        new MainMenu();

    }
}
