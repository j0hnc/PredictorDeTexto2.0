package predictordetexto;

/**
 *
 * @author jjcerpa
 */
public class Nodo {

    public char letra;
    Lista lista = new Lista();
    public Boolean esPalabra = false;

    public Nodo(char letra) {
        this.letra = letra;
    }   

    public void imprimirElems() {  
        lista.imprimirElems();
    }
    
    public String getElems() {
        return lista.elems();        
    }
    
    
}
