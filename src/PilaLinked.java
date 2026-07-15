import java.util.Iterator;
import java.util.NoSuchElementException;
public class PilaLinked <E> implements Pila<E> {

    private final Lista<E> elementos;

    public PilaLinked() {
        elementos = new LinkedList<>();
    } //LO UNICO QEU DIFIERE ES COMO SE ARMA LA LISTA... AQUI ES LINKED Y EL OTRO UN ARRAYLIS, 
    // ES LO UNICO EN LO QUE DIFIERE.(UNA LINEA DE COGISO, 
    // TODO LO DEMAS ES UNA GENERALIZACION DE LA INTERFAZ PILA, QUE ES LO QUE SE ESTA IMPLEMENTANDO).
    //EN LAS VENTAJS Y DESVENTAJAS DEPENDE DE QUE SE NECESITE. ARAYLISTA TODAS ESTAN CONTINUAS, PERO CUANDO CRECE 
    // EL SISTEMA TIENE QUE REASIGNAR TODA LA MEMORIA,
    //  EN LINKEDLIST NO, PERO SIEMPRE OCUPA MAS MEMORIA PORQUE 
    // TIENE QUE GUARDAR EL SIGUIENTE Y EL ANTERIOR.

    /**
     * Agrega un elemento a la cima de la pila.
     *
     * La cima corresponde a la última posición de la lista:
     *
     * numElementos() - 1
     *
     * @param elemento elemento que se agregará a la pila
     */
    @Override
    public void apilar(E elemento) {
        elementos.agregarFinal(elemento);
    }

    @Override
    public E desapilar() {
        if (esVacia()) {
            throw new NoSuchElementException(
                "La pila está vacía"
            );
        }

        return elementos.eliminarElementoFinal();
    }

    @Override
    public E consultarCima() {
        if (esVacia()) {
            throw new NoSuchElementException(
                "La pila está vacía"
            );
        }

        int ultimaPosicion = elementos.numElementos() - 1;
        return elementos.consultar(ultimaPosicion);
    }

    @Override
    public boolean esVacia() {
        return elementos.esVacia();
    }

    @Override
    public int numElementos() {
        return elementos.numElementos();
    }   

    @Override
    public void limpiar() {
        elementos.limpiarLista();
    }

    @Override
    public Iterator<E> iterator() {
        return elementos.iterator();
    }




    
}
