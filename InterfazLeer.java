import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class InterfazLeer extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField nombre, numero;
    private JLabel nombreT, numeroT;
    private JButton abrir, cancelar;
    private boolean encontrado = false;

    public InterfazLeer() {
        iniciar();
        setTitle("Leer Contacto");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
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
                         encontrado = true;
                         
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
        nombreT = new JLabel("Nombre del contacto:");
        nombreT.setBounds(40, 20, 135, 23);
        numeroT = new JLabel("Número del contacto:");
        numeroT.setBounds(40, 80, 135, 23);

        // Inicialización de componentes
        nombre = new JTextField();
        nombre.setBounds(50, 40, 300, 30);
        nombre.setText("");
        
        numero = new JTextField();
        numero.setEditable(false); 
        numero.setText("");
        numero.setBounds(50, 100, 300, 30);

        abrir = new JButton("Leer");
        abrir.setBounds(80, 160, 80, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 160, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(nombre);
        contenedor.add(numero);
        contenedor.add(abrir);
        contenedor.add(cancelar);
        contenedor.add(nombreT);
        contenedor.add(numeroT);

        abrir.addActionListener(this);
        cancelar.addActionListener(this);
    }

    // Función que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abrir) {
           leer();
        } else if (e.getSource() == cancelar) {
            dispose(); 
        }
    }

}
