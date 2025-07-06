import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InterfazEditar extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField titulo;
    private JTextArea contenido;
    private JButton guardar, cancelar, leer;

    public InterfazEditar() {
        iniciar();
        setTitle("Editar Archivo");
        setSize(400, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void iniciar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        // Inicialización de componentes
        titulo = new JTextField();
        titulo.setBounds(50, 20, 300, 30);
        titulo.setText("Título del Archivo");

        contenido = new JTextArea(9, 30);
        contenido.setBounds(50, 70, 300, 150);
        contenido.setLineWrap(true);
        contenido.setWrapStyleWord(true);
        contenido.setEditable(false);

        leer = new JButton("Leer");
        leer.setBounds(50, 240, 80, 30);

        guardar = new JButton("Guardar");
        guardar.setBounds(150, 240, 90, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(260, 240, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(titulo);
        contenedor.add(contenido);
        contenedor.add(guardar);
        contenedor.add(cancelar);
        contenedor.add(leer);

        guardar.addActionListener(this);
        cancelar.addActionListener(this);
        leer.addActionListener(this);
    }

    //Funcion que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leer) {
            if (titulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
                return;
            }
            try {
                String nombreArchivo = titulo.getText().trim();
                File archivo = new File("Archivos/" + nombreArchivo + ".txt");
                if (archivo.exists()) {
                    FileReader lector = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(lector);
                    StringBuilder sb = new StringBuilder();
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        sb.append(linea).append("\n");
                    }
                    contenido.setText(sb.toString());
                    br.close();
                    contenido.setEditable(true); 
                    titulo.setEditable(false); 
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.");
                    return;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelar) {
            dispose(); 
        }else if (e.getSource() == guardar) {
            if (titulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
                return;
            }
            try {
                String nombreArchivo = titulo.getText().trim();
                File archivo = new File("Archivos/" + nombreArchivo + ".txt");
                if (archivo.exists()) {
                    String contenidoModificado = contenido.getText();
                    Files.write(Paths.get(archivo.getPath()), contenidoModificado.getBytes());
                    JOptionPane.showMessageDialog(this, "Archivo guardado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.");
                    return;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    
    }
}


