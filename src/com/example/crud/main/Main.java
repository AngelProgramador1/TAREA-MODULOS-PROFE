package com.example.crud.main;

import com.example.crud.controller.Controlador;
import com.example.crud.model.ProductoDAO;
import com.example.crud.view.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoDAO modelo = new ProductoDAO();
            VentanaPrincipal vista = new VentanaPrincipal();
            Controlador controlador = new Controlador(modelo, vista);
            controlador.iniciar();
        });
    }
}
