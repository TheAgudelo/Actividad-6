import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class InterfazLeer extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField titulo;
    private JTextArea contenido;
    private JButton abrir, cancelar;

    public InterfazLeer() {
        iniciar();
        setTitle("Leer Archivo");
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

        abrir = new JButton("Abrir");
        abrir.setBounds(80, 240, 80, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 240, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(titulo);
        contenedor.add(contenido);
        contenedor.add(abrir);
        contenedor.add(cancelar);

        abrir.addActionListener(this);
        cancelar.addActionListener(this);
    }

    // Función que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abrir) {
            if (titulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
                return;
            }
            try {
                String nombreArchivo = titulo.getText().trim();
                File archivo = new File("/C:\\Users\\Usuario\\Documents\\GitHub\\Actividad-6\\Archivos/" + nombreArchivo + ".txt");
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
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.");
                    return;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelar) {
            dispose(); 
        }
    }

}
