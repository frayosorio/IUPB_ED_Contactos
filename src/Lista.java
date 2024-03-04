import javax.swing.JTable;
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
            apuntador = apuntador.siguiente;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

    // ****** atributos estaticos *****/

    public static String[] encabezados = new String[] { "Nombre", "Telefono", "Celular", "Direccion", "Correo" };

}
