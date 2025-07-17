import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InterfazEditar extends JFrame implements ActionListener {
    private Container contenedor;
   private JTextField nombre, numero;
    private JLabel nombreT, numeroT;
    private JButton guardar, cancelar, leer;
    private boolean encontrado = false;

    public InterfazEditar() {
        iniciar();
        setTitle("Editar Contacto");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void guardar(){
        
            List<String> lineas = new ArrayList<>();
            boolean encontrado = false;

            try (BufferedReader lector = new BufferedReader(new FileReader("Archivos/Contactos.txt"))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.startsWith(nombre.getText().trim() + "&")) {
                        lineas.add(nombre.getText().trim() + "&" + numero.getText().trim());
                        encontrado = true;
                    } else {
                        lineas.add(linea);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al leer el archivo: " + ex.getMessage());
                return;
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "El contacto no existe.");
                return;
            }
            if (numero.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El número no puede estar vacío.");
                return;
            }
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Archivos/Contactos.txt"))) {
                for (String l : lineas) {
                    escritor.write(l);
                    escritor.newLine();
                }
                JOptionPane.showMessageDialog(this, "Contacto guardado exitosamente.");
               dispose(); 
            } catch (IOException ex) {
                System.out.println("Error al guardar el archivo: " + ex.getMessage());
            }

    }

    private void leer(){
        if (nombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                return;
            }
            try (BufferedReader lector = new BufferedReader(new FileReader("Archivos/Contactos.txt"))) {
              String linea;
              while ((linea = lector.readLine()) != null) {
                 if (linea.startsWith((nombre.getText().trim())+"&")) {
                     String[] partes = linea.split("&");
                     if (partes.length == 2) {
                         numero.setText(partes[1]);
                         encontrado=true;

                         
                        } else {
                         JOptionPane.showMessageDialog(this, "Formato de contacto incorrecto.");
                       } 
                    
                    }
               }
               if (!encontrado) {
                   JOptionPane.showMessageDialog(this, "Contacto no encontrado.");
               } 
           } catch (IOException ex) {
               System.out.println("Error al leer el archivo: " + ex.getMessage());
           }
    }

    private void iniciar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        // Inicialización de componentes
        contenedor = getContentPane();
        contenedor.setLayout(null);

        nombreT = new JLabel("Nombre del contacto:");
        nombreT.setBounds(40, 20, 135, 23);
        numeroT = new JLabel("Número del contacto:");
        numeroT.setBounds(40, 80, 135, 23);

        // Inicialización de componentes
        nombre = new JTextField();
        nombre.setBounds(50, 40, 300, 30);
        nombre.setText("");
        
        numero = new JTextField();
        numero.setText("");
        numero.setBounds(50, 100, 300, 30);

        leer = new JButton("Leer");
        leer.setBounds(50, 160, 80, 30);

        guardar = new JButton("Guardar");
        guardar.setBounds(150, 160, 90, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(260, 160, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(nombre);
        contenedor.add(numero);
        contenedor.add(guardar);
        contenedor.add(cancelar);
        contenedor.add(leer);
        contenedor.add(nombreT);
        contenedor.add(numeroT);

        guardar.addActionListener(this);
        cancelar.addActionListener(this);
        leer.addActionListener(this);
    }

    //Funcion que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        
         if (e.getSource() == leer) {
        
              leer();
       } else if (e.getSource() == cancelar) {
           dispose();
       } else if (e.getSource() == guardar) {
            if (nombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                return;
            }
           
            guardar();
        }

    }               

}



