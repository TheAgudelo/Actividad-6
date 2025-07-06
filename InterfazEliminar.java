import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;

public class InterfazEliminar extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField titulo;
    private JButton eliminar, cancelar;

    public InterfazEliminar() {
        iniciar();
        setTitle("Eliminar Archivo");
        setSize(400, 160);
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

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(80, 70, 80, 30);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(205, 70, 90, 30);

        // Agregar componentes al contenedor
        contenedor.add(titulo);
        contenedor.add(eliminar);
        contenedor.add(cancelar);

        eliminar.addActionListener(this);
        cancelar.addActionListener(this);
    }

    //función que maneja los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eliminar) {
            if (titulo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título no puede estar vacío.");
                return;
            }
            try {
                String nombreArchivo = titulo.getText().trim();
                File archivo = new File("/C:\\Users\\Usuario\\Documents\\GitHub\\Actividad-6\\Archivos/" + nombreArchivo + ".txt");
                if (archivo.exists()) {
                    if (archivo.delete()) {
                        JOptionPane.showMessageDialog(this, "Archivo eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el archivo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el archivo: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelar) {
            dispose(); 
        }
    }
    
}
