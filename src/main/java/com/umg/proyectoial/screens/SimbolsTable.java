package com.umg.proyectoial.screens;

import Services.DatabaseConnection;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SimbolsTable extends JPanel {

    public SimbolsTable() {
        setLayout(new BorderLayout());
        add(createTable(), BorderLayout.CENTER);
    }

    private JScrollPane createTable() {

        // Componentes de la pantalla 2
        JLabel label = new JLabel("aqui va la tabla de simbolos");
        String[] columnNames = {"ID", "Lexeme", "Type", "Pattern"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {

            ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM symbolsTable");
            while (rs.next()) {
                int id = rs.getInt("id");
                String lexeme = rs.getString("lexeme");
                String type = rs.getString("type");
                String pattern = rs.getString("pattern");
                model.addRow(new Object[]{id, lexeme, type, pattern});
            }

        } catch (Exception e) {
        }

        JTable table = new JTable(model);
        return new JScrollPane(table);

    }
}
