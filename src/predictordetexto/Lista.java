package predictordetexto;

import java.util.LinkedList;

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
}
