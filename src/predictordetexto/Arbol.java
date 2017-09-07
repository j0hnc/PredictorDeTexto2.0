package predictordetexto;
/**
 *
 * @author jjcerpa
 */
public class Arbol {

    public Nodo raiz;
    
    public Arbol() {
        raiz = new Nodo(' ');
    }

    public void insertarPalabra(Nodo n, String palabra, int i) {
        if (i == palabra.length() || n == null) {
            return;
        }
        
        if (i == palabra.length() - 1) {
            n.lista.insertar(palabra.charAt(i), true); // final de una palabra
        } else {
            n.lista.insertar(palabra.charAt(i), false);
        }
        insertarPalabra(n.lista.buscarNodo(palabra.charAt(i)), palabra, i + 1);
    }
}
