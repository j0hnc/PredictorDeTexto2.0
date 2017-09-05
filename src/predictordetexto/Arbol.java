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
            n.lista.insertar(palabra.charAt(i), true);
        } else {
            n.lista.insertar(palabra.charAt(i), false);
        }

        
        insertarPalabra(n.lista.buscarNodo(palabra.charAt(i)), palabra, i + 1);
    }

    public void imprimirArbol(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.letra);
            nodo.imprimirElems();
            nodo.lista.lista_letras.forEach((p) -> {
                imprimirArbol(p);
            });
        }
    }
    
    public Nodo buscarNodoUltimaLetra(Nodo n, String palabra, int i, char ultimaLetra) {        
        if (n == null) return null;        
        if (n.letra == ultimaLetra) return n;        
        return buscarNodoUltimaLetra(n.lista.buscarNodo(palabra.charAt(i)), palabra, i + 1, ultimaLetra);
    }
    
    public String buscarSugerencias(String palabra) {
        
        String resultado = "";        
        char ultimaLetra = palabra.charAt(palabra.length() - 1);
        
        Nodo ultimo = buscarNodoUltimaLetra(raiz, palabra, 0, ultimaLetra); 
        ultimo.lista.mostrarSugerencias(ultimo, palabra);
        
        return resultado;
    }

}
