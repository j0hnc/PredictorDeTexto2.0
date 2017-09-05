package predictordetexto;

import java.util.LinkedList;

/**
 *
 * @author Jhon
 */
public class Lista {

    public LinkedList<Nodo> lista_letras;

    public Lista() {
        lista_letras = new LinkedList();
    }

    public Boolean letraYaExiste(char letra) {
        return lista_letras.stream().anyMatch((p) -> (letra == p.letra));
    }

    public Nodo buscarNodo(char letra) {
        for (Nodo p : lista_letras) {
            if (p.letra == letra) {
                return p;
            }
        }
        return null;
    }

    public void insertar(char letra, Boolean esPalabra) {
        if (!letraYaExiste(letra)) {
            Nodo nuevoNodo = new Nodo(letra);
            nuevoNodo.esPalabra = esPalabra;
            lista_letras.add(nuevoNodo);
        }
    }

    public Boolean estaVacia() {
        return lista_letras.isEmpty();
    }

    public void mostrarSugerencias(Nodo nodo, String historia) {
        for (Nodo n : nodo.lista.lista_letras) {
            
            if (n.esPalabra) {
                System.out.println(historia + n.letra);
            }

            if (!n.lista.estaVacia()) {
                mostrarSugerencias(n, historia + n.letra);
            }
        }        
    }

    public Nodo getNodo(int i) {
        return lista_letras.get(i);
    }

    public Nodo getNodo(char letra) {
        for (Nodo p : lista_letras) {
            if (letra == p.letra) {
                return p;
            }
        }
        return null;
    }

    public void imprimirElems() {
        lista_letras.forEach((n) -> {
            System.out.println(n.letra + " hijos -> " + n.getElems());
        });
    }

    public String elems() {
        String g = "";
        g = lista_letras.stream().map((n) -> n.letra + ", ").reduce(g, String::concat);
        return g;
    }

}
