import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class InterfazCrear extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField titulo ;
    private JTextArea contenido;
    private JButton guardar, cancelar;

    public InterfazCrear() {
        iniciar();
        setTitle("Crear Archivo");
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
        
        guardar = new JButton("Guardar");
        guardar.setBounds(80, 240, 80, 30);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 240, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(titulo);
        contenedor.add(contenido);
        contenedor.add(guardar);
        contenedor.add(cancelar);

        guardar.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardar) {
            if (titulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
                return;
            }
            try {
                String nombreArchivo = titulo.getText().trim();
                File archivo = new File("/C:\\Users\\Usuario\\Documents\\GitHub\\Actividad-6\\Archivos/"+nombreArchivo + ".txt");
                if (archivo.createNewFile()) {
                     FileWriter escritor = new FileWriter(archivo);
                      escritor.write(contenido.getText());
                      escritor.close();
                    System.out.println("Archivo creado: " + archivo.getName());
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo ya existe.");
                    return;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + ex.getMessage());
            }
            dispose(); // Cerrar la ventana después de guardar
        } else if (e.getSource() == cancelar) {
            dispose(); // Cerrar la ventana sin guardar
        }
    }
    
}
