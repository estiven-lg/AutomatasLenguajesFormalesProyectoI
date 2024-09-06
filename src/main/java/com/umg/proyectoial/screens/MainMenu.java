/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.proyectoial.screens;

import javax.swing.*;


public class MainMenu extends JFrame {

    public MainMenu() {
  
        setTitle("Automatas y Lenguajes Formales - Proyecto I");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTabbedPane tabbedPane = new JTabbedPane();

 
        tabbedPane.addTab("Codigo", new MainScreen());
        tabbedPane.addTab("Tabla de símbolos", new SimbolsTable());
        tabbedPane.addTab("Tablas de transiciones", new TransitionsTable());


        add(tabbedPane);


        setVisible(true);
    }

}

