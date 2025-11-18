package com.example.crud.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnActualizar, btnEliminar;
    private JTextField txtNombre, txtPrecio, txtId;

    public VentanaPrincipal() {
        setTitle("Gestión de Productos CRUD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");

        // Tabla
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        txtId = new JTextField();
        txtId.setEditable(false);
        txtNombre = new JTextField();
        txtPrecio = new JTextField();

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(txtPrecio);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        
        // Listeners para la tabla
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaProductos.getSelectedRow() != -1) {
                int filaSeleccionada = tablaProductos.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                txtPrecio.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
            }
        });

        // Layout principal
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener controlador) {
        btnAgregar.addActionListener(controlador);
        btnActualizar.addActionListener(controlador);
        btnEliminar.addActionListener(controlador);
        btnAgregar.setActionCommand("Agregar");
        btnActualizar.setActionCommand("Actualizar");
        btnEliminar.setActionCommand("Eliminar");
    }

    public void setProductos(Object[][] datos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    public String getId() { return txtId.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public String getPrecio() { return txtPrecio.getText(); }

    public void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        tablaProductos.clearSelection();
    }

    public int getFilaSeleccionada() {
        return tablaProductos.getSelectedRow();
    }
}
