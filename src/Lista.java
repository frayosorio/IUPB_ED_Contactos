import java.io.BufferedReader;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Lista {

    private Nodo cabeza;

    public Lista() {
        cabeza = null;
    }

    public void agregar(Nodo n) {
        if (cabeza == null) {
            cabeza = n;
        } else {
            Nodo apuntador = cabeza;
            while (apuntador.siguiente != null) {
                apuntador = apuntador.siguiente;
            }
            apuntador.siguiente = n;
        }
        n.siguiente = null;
    }

    public int getLongitud() {
        int totalNodos = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            apuntador = apuntador.siguiente;
            totalNodos++;
        }
        return totalNodos;
    }

    public Nodo getNodo(int posicion) {
        int p = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null && p != posicion) {
            apuntador = apuntador.siguiente;
            p++;
        }
        if (apuntador != null && p == posicion) {
            return apuntador;
        }
        return null;
    }

    public void actualizar(int posicion, String nombre, String telefono, String celular, String direccion,
            String correo) {
        Nodo n = getNodo(posicion);
        if (n != null) {
            n.actualizar(nombre, telefono, celular, direccion, correo);
        }
    }

    public void mostrar(JTable tbl) {

        String[][] datos = new String[getLongitud()][encabezados.length];
        Nodo apuntador = cabeza;
        int fila = 0;
        while (apuntador != null) {
            datos[fila][0] = apuntador.nombre;
            datos[fila][1] = apuntador.telefono;
            datos[fila][2] = apuntador.celular;
            datos[fila][3] = apuntador.direccion;
            datos[fila][4] = apuntador.correo;
            fila++;
            apuntador = apuntador.siguiente;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);

        dtm.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int fila = e.getFirstRow();
                DefaultTableModel dtm = (DefaultTableModel) e.getSource();
                actualizar(fila,
                        (String) dtm.getValueAt(fila, 0),
                        (String) dtm.getValueAt(fila, 1),
                        (String) dtm.getValueAt(fila, 2),
                        (String) dtm.getValueAt(fila, 3),
                        (String) dtm.getValueAt(fila, 4));

            }

        });

        tbl.setModel(dtm);
    }

    public boolean guardar(String nombreArchivo) {
        int totalLineas = getLongitud();
        if (totalLineas > 0) {
            String[] lineas = new String[totalLineas];
            Nodo apuntador = cabeza;
            int fila = 0;
            while (apuntador != null) {
                lineas[fila] = (apuntador.nombre.equals("") ? " " : apuntador.nombre) + "\t" +
                        (apuntador.telefono.equals("") ? " " : apuntador.telefono) + "\t" +
                        (apuntador.celular.equals("") ? " " : apuntador.celular) + "\t" +
                        (apuntador.direccion.equals("") ? " " : apuntador.direccion) + "\t" +
                        (apuntador.correo.equals("") ? " " : apuntador.correo);
                apuntador = apuntador.siguiente;
                fila++;
            }
            return Archivo.guardarArchivo(nombreArchivo, lineas);
        }
        return false;
    }

    public void desdeArchivo(String nombreArchivo) {
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        cabeza = null;
        if (br != null) {
            try {
                String linea = br.readLine();

                while (linea != null) {
                    String[] textos = linea.split("\t");
                    if (textos.length >= 5) {
                        Nodo n = new Nodo(textos[0],
                                textos[1],
                                textos[2],
                                textos[3],
                                textos[4]);
                        agregar(n);
                    }

                    linea = br.readLine();
                }
            } catch (Exception ex) {
                cabeza = null;
            }
        }
    }

 //Elimina un nodo de la lista
 public void eliminar(Nodo n) {
    if (n != null && cabeza != null) {
        //Buscar el nodo
        boolean encontrado = false;
        Nodo apuntador = cabeza;
        Nodo anterior = null;
        while (apuntador != null && !encontrado) {
            if (apuntador == n) {
                encontrado = true;
            } else {
                anterior = apuntador;
                apuntador = apuntador.siguiente;
            }
        }
        if (encontrado) {
            if (anterior == null) {
                cabeza = apuntador.siguiente;
            } else {
                anterior.siguiente = apuntador.siguiente;
            }
        }
    }
}


    // ****** atributos estaticos *****/

    public static String[] encabezados = new String[] { "Nombre", "Telefono", "Celular", "Direccion", "Correo" };

}
