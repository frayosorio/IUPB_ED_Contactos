import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmContactos extends JFrame {

    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnGuardar;
    private JButton btnOrdenar;
    private JToolBar tbContactos;
    private JTable tblContactos;

    public FrmContactos() {
        tbContactos = new JToolBar();
        btnAgregar = new JButton();
        btnEliminar = new JButton();
        btnGuardar = new JButton();
        btnOrdenar = new JButton();
        tblContactos = new JTable();

        setSize(600, 300);
        setTitle("Mis contactos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // getContentPane().setLayout(null);

        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Iconos/Agregar.gif")));
        btnAgregar.setToolTipText("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarClick(evt);
            }
        });
        tbContactos.add(btnAgregar);

        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Iconos/Eliminar.gif")));
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEliminarClick(evt);
            }
        });
        tbContactos.add(btnEliminar);

        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/Iconos/Guardar.gif")));
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarClick(evt);
            }
        });
        tbContactos.add(btnGuardar);

        btnOrdenar.setIcon(new ImageIcon(getClass().getResource("/Iconos/Ordenar.gif")));
        btnOrdenar.setToolTipText("Ordenar");
        btnOrdenar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarClick(evt);
            }
        });
        tbContactos.add(btnOrdenar);

        DefaultTableModel dtm = new DefaultTableModel(null, Lista.encabezados);
        tblContactos.setModel(dtm);
        JScrollPane spContactos = new JScrollPane(tblContactos);

        getContentPane().add(tbContactos, BorderLayout.NORTH);
        getContentPane().add(spContactos, BorderLayout.CENTER);
    }

    private Lista contactos = new Lista();

    private void btnAgregarClick(ActionEvent evt) {
        Nodo n = new Nodo("", "", "", "", "");
        contactos.agregar(n);
        contactos.mostrar(tblContactos);
    }

    private void btnEliminarClick(ActionEvent evt) {
    }

    private void btnGuardarClick(ActionEvent evt) {
    }

    private void btnOrdenarClick(ActionEvent evt) {
    }

}