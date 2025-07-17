import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfazInicial extends JFrame implements ActionListener {
    private Container contenedor;
    private JTextField bienvenida;
    private JButton crear, leer, editar, eliminar;

    public InterfazInicial() {
        iniciar();
        setTitle("Contactos");
        setSize(400, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    private void iniciar() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        // Inicialización de componentes
        bienvenida = new JTextField();
        bienvenida.setBounds(100, 20, 200, 30);
        bienvenida.setText("Bienvenido al gestor de contactos");
        bienvenida.setEditable(false);
        crear = new JButton("Crear");
        crear.setBounds(20, 80, 70, 30);
        leer = new JButton("Leer");
        leer.setBounds(110, 80, 70, 30);
        editar = new JButton("Editar");
        editar.setBounds(200, 80, 70, 30);
        eliminar = new JButton("Eliminar");
        eliminar.setBounds(290, 80, 80, 30);

        // Agregar componentes al contenedor
        contenedor.add(bienvenida);
        contenedor.add(crear);
        contenedor.add(leer);
        contenedor.add(editar);
        contenedor.add(eliminar);

        crear.addActionListener(this);
        leer.addActionListener(this);
        editar.addActionListener(this);
        eliminar.addActionListener(this);


        
        
    }

    //Funcion para invocar las diferentes ventanas según el botón presionado
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crear) {
            InterfazCrear ventanaCrear = new InterfazCrear();
            ventanaCrear.setVisible(true);
        } else if (e.getSource() == leer) {
            InterfazLeer ventanaLeer = new InterfazLeer();
            ventanaLeer.setVisible(true);
        } else if (e.getSource() == editar) {
            InterfazEditar ventanaEditar = new InterfazEditar();
            ventanaEditar.setVisible(true);
        } else if (e.getSource() == eliminar) {
            InterfazEliminar ventanaEliminar = new InterfazEliminar();
            ventanaEliminar.setVisible(true);
        }
    }

}