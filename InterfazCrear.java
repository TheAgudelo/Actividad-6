import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class InterfazCrear extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField nombre, numero;
    private JLabel nombreT, numeroT;
    private JButton guardar, cancelar;

    public InterfazCrear() {
        iniciar();
        setTitle("Crear Contacto");
        setSize(400, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void iniciar() {
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
        
        
        guardar = new JButton("Guardar");
        guardar.setBounds(80, 150, 80, 30);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 150, 90, 30);

        
        contenedor.add(nombre);
        contenedor.add(numero);
        contenedor.add(guardar);
        contenedor.add(cancelar);
        contenedor.add(nombreT);
        contenedor.add(numeroT);

        guardar.addActionListener(this);
        cancelar.addActionListener(this);
    }
    private void guardar(String nombre, String numero, File archivo) {
        try {
            
            if (archivo.exists()) {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.startsWith(nombre + "&")) {
                        JOptionPane.showMessageDialog(this, "Ese contacto ya existe.");
                        lector.close();
                        return;
                    }
                }
                lector.close();
            }

            
            FileWriter escritor = new FileWriter(archivo, true); 
            escritor.write(nombre + "&" + numero);
            escritor.write("\n");
            escritor.close();

            JOptionPane.showMessageDialog(this, "Contacto guardado exitosamente.");
            dispose(); 

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el contacto: " + ex.getMessage());
        }

        // Método para guardar el contacto
        // Aquí se implementará la lógica de guardado

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardar) {
            if (nombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                return;
            }
            if (numero.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El número no puede estar vacío.");
                return;
            }
            File archivo = new File("Archivos/contactos.txt");
            String nombreContacto = nombre.getText().trim();
            String numeroContacto = numero.getText().trim();
            guardar(nombreContacto, numeroContacto, archivo);
    } else if (e.getSource() == cancelar) {
            dispose(); 
        }
    }
    
}
