package com.umg.proyectoial.screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TransitionsTable extends JPanel {
    // Clases para representar un Estado y una Transición
    private static class State {
        String name;
        int x, y;

        State(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    private static class Transition {
        State from;
        State to;

        Transition(State from, State to) {
            this.from = from;
            this.to = to;
        }
    }

    // Lista de estados y transiciones
    private List<State> states = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    public TransitionsTable() {
        // Inicializar estados
        states.add(new State("Variable", 100, 100));
        states.add(new State("Declaracion", 300, 100));
        states.add(new State("Operacion", 100, 300));
        states.add(new State("Asignacion", 300, 300));
        states.add(new State("Valor", 100, 500));
        states.add(new State("Finalizacion", 300, 500));

        // Inicializar transiciones
        transitions.add(new Transition(states.get(0), states.get(1))); // Variable -> Declaracion
        transitions.add(new Transition(states.get(1), states.get(1))); // Declaracion -> Declaracion
        transitions.add(new Transition(states.get(1), states.get(2))); // Declaracion -> Operacion
        transitions.add(new Transition(states.get(1), states.get(3))); // Declaracion -> Asignacion
        transitions.add(new Transition(states.get(2), states.get(1))); // Operacion -> Declaracion
        transitions.add(new Transition(states.get(2), states.get(4))); // Operacion -> Valor
        transitions.add(new Transition(states.get(3), states.get(1))); // Asignacion -> Declaracion
        transitions.add(new Transition(states.get(3), states.get(4))); // Asignacion -> Valor
        transitions.add(new Transition(states.get(4), states.get(2))); // Valor -> Operacion
        transitions.add(new Transition(states.get(4), states.get(3))); // Valor -> Asignacion
        transitions.add(new Transition(states.get(4), states.get(4))); // Valor -> Valor
        transitions.add(new Transition(states.get(5), states.get(4))); // Finalizacion -> Valor   
        transitions.add(new Transition(states.get(5), states.get(0))); // Finalizacion -> Valor

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAutomaton(g);
    }

    private void drawAutomaton(Graphics g) {
        // Dibujar estados
        for (State state : states) {
            drawState(g, state.x, state.y, state.name);
        }

        // Dibujar transiciones
        for (Transition transition : transitions) {
            if (transition.from == transition.to) {
                drawSelfTransition(g, transition.from.x, transition.from.y);
            } else {
                drawTransition(g, transition.from, transition.to);
            }
        }
    }

    private void drawState(Graphics g, int x, int y, String label) {
        int diameter = 60; // Tamaño del círculo
        g.drawOval(x - diameter / 2, y - diameter / 2, diameter, diameter); // Dibujar círculo
        g.drawString(label, x - 10, y + 5); // Etiqueta del estado
    }

    private void drawTransition(Graphics g, State from, State to) {
        // Calcular puntos de inicio y fin para la flecha
        int startX = from.x + 30; // Borde derecho del estado de inicio
        int startY = from.y; // Centro vertical del estado de inicio
        int endX = to.x - 30; // Borde izquierdo del estado de destino
        int endY = to.y; // Centro vertical del estado de destino

        // Dibujar línea para la transición
        g.drawLine(startX, startY, endX, endY);

        // Dibujar flecha
        drawArrow(g, startX, startY, endX, endY);
    }

    private void drawSelfTransition(Graphics g, int x, int y) {
        int arcOffset = 40; // Desplazamiento para el arco
        int arrowOffset = 15; // Desplazamiento para la flecha

        // Dibujar arco hacia arriba
        g.drawArc(x - 30, y - arcOffset, 60, 60, 0, 180); // Arco que representa la transición a sí mismo

        // Dibujar flecha apuntando hacia el arco
        drawArrow(g, x, y - arcOffset, x + arrowOffset, y - arcOffset);
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int arrowSize = 5;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int xArrow1 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));
        g.drawLine(x2, y2, xArrow1, yArrow1);
        g.drawLine(x2, y2, xArrow2, yArrow2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujo de Autómata");
        TransitionsTable panel = new TransitionsTable();
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
