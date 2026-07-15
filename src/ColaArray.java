import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementacion de una cola utilizando internamente un ArrayList.
 *
 * Al igual que PilaArray, la clase reutiliza los metodos definidos
 * en la interfaz Lista, evitando volver a implementar la logica
 * de almacenamiento.
 *
 * La cola sigue el principio FIFO:
 * First In, First Out.
 * El primer elemento que entra es el primero que sale.
 *
 * Diferencia clave con la Pila:
 * - En la Pila, se agrega y se elimina por el mismo extremo (el final).
 * - En la Cola, se agrega por un extremo (el final) y se elimina
 *   por el otro extremo (el inicio).
 *
 * @param <E> tipo de elementos almacenados en la cola
 */
public class ColaArray<E> implements Cola<E> {

    private final Lista<E> elementos;

    public ColaArray() {
        elementos = new ArrayList<E>();
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * Se reutiliza agregarFinal() de Lista: los elementos nuevos
     * siempre entran por el final, nunca por el frente.
     */
    @Override
    public void encolar(E elemento) {
        elementos.agregarFinal(elemento);
    }

    /**
     * Elimina y devuelve el elemento que esta al frente de la cola.
     *
     * A diferencia de la Pila (que elimina del final), la Cola
     * elimina del inicio: el elemento que mas tiempo lleva esperando.
     *
     * @throws NoSuchElementException si la cola esta vacia
     */
    @Override
    public E desencolar() {
        if (esVacia()) {
            throw new NoSuchElementException("La cola esta vacia");
        }
        return elementos.eliminarElementoInicio();
    }

    /**
     * Devuelve el elemento al frente sin eliminarlo.
     *
     * El frente siempre corresponde a la posicion 0 de la lista,
     * ya que es el elemento mas antiguo que sigue esperando.
     *
     * @throws NoSuchElementException si la cola esta vacia
     */
    @Override
    public E consultarFrente() {
        if (esVacia()) {
            throw new NoSuchElementException("La cola esta vacia");
        }
        return elementos.consultar(0);
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

    /**
     * Devuelve un iterador para recorrer la cola del frente hacia el final.
     * El recorrido se delega al iterador de la lista interna.
     */
    @Override
    public Iterator<E> iterator() {
        return elementos.iterator();
    }
}

