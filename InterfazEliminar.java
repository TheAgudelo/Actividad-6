import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class InterfazEliminar extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField nombre;
    private JLabel nombreT;
    private JButton eliminar, cancelar;
    private boolean encontrado = false;

    public InterfazEliminar() {
        iniciar();
        setTitle("Eliminar Contacto");
        setSize(400, 160);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
    private void eliminar() {
        if (nombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            return;
        }
        java.util.List<String> lineas = new java.util.ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader("Archivos/Contactos.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith(nombre.getText().trim() + "&")) {
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Contacto no encontrado.");
            } else {
                try (java.io.BufferedWriter escritor = new java.io.BufferedWriter(new java.io.FileWriter("Archivos/Contactos.txt"))) {
                    for (String l : lineas) {
                        escritor.write(l);
                        escritor.newLine();
                    }
                    JOptionPane.showMessageDialog(this, "Contacto eliminado exitosamente.");
                    dispose(); // cerrar la ventana
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al escribir el archivo: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
    }

    private void iniciar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);
        nombreT = new JLabel("Nombre del contacto:");
        nombreT.setBounds(40, 20, 135, 23);

        // Inicialización de componentes
         nombre = new JTextField();
        nombre.setBounds(50, 40, 300, 30);
        nombre.setText("");

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(80, 80, 80, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 80, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(nombre);
        contenedor.add(eliminar);
        contenedor.add(cancelar);
        contenedor.add(nombreT);

        eliminar.addActionListener(this);
        cancelar.addActionListener(this);
    }

    //función que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eliminar) {
            eliminar();
           
           

        }else if (e.getSource() == cancelar) {
            dispose(); 
        }
    }
    
}
