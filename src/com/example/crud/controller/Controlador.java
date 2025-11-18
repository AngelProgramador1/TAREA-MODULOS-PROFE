package com.example.crud.controller;

import com.example.crud.model.Producto;
import com.example.crud.model.ProductoDAO;
import com.example.crud.view.VentanaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controlador implements ActionListener {
    private ProductoDAO modelo;
    private VentanaPrincipal vista;

    public Controlador(ProductoDAO modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void iniciar() {
        actualizarTabla();
        vista.setVisible(true);
    }

    private void actualizarTabla() {
        List<Producto> productos = modelo.obtenerTodos();
        Object[][] datos = new Object[productos.size()][3];
        for (int i = 0; i < productos.size(); i++) {
            datos[i][0] = productos.get(i).getId();
            datos[i][1] = productos.get(i).getNombre();
            datos[i][2] = productos.get(i).getPrecio();
        }
        vista.setProductos(datos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            switch (comando) {
                case "Agregar":
                    String nombre = vista.getNombre();
                    double precio = Double.parseDouble(vista.getPrecio());
                    modelo.agregarProducto(new Producto(nombre, precio));
                    break;

                case "Actualizar":
                    int id = Integer.parseInt(vista.getId());
                    String nombreActualizado = vista.getNombre();
                    double precioActualizado = Double.parseDouble(vista.getPrecio());
                    modelo.actualizarProducto(new Producto(id, nombreActualizado, precioActualizado));
                    break;

                case "Eliminar":
                    if (vista.getFilaSeleccionada() != -1) {
                        int idEliminar = Integer.parseInt(vista.getId());
                        modelo.eliminarProducto(idEliminar);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Seleccione un producto para eliminar.");
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.");
        }

        vista.limpiarFormulario();
        actualizarTabla();
    }
}
