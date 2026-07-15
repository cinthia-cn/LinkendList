import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementacion de una cola utilizando internamente un LinkedList.
 *
 * Es identica a ColaArray en su logica (misma interfaz Cola,
 * mismo comportamiento FIFO); lo unico que cambia es la
 * implementacion de Lista que se usa por dentro.
 *
 * Ventaja frente a ColaArray: eliminarElementoInicio() en una
 * lista ligada es O(1) (solo se mueve el puntero "primero"),
 * mientras que en un arreglo cuesta O(n) porque hay que recorrer
 * y recorrer todos los elementos una posicion a la izquierda.
 * Para una cola, que constantemente quita elementos del inicio,
 * esto hace que LinkedList sea, en general, mas eficiente.
 *
 * @param <E> tipo de elementos almacenados en la cola
 */
public class ColaLinked<E> implements Cola<E> {

    private final Lista<E> elementos;

    public ColaLinked() {
        elementos = new LinkedList<E>();
    }

    @Override
    public void encolar(E elemento) {
        elementos.agregarFinal(elemento);
    }

    @Override
    public E desencolar() {
        if (esVacia()) {
            throw new NoSuchElementException("La cola esta vacia");
        }
        return elementos.eliminarElementoInicio();
    }

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

    @Override
    public Iterator<E> iterator() {
        return elementos.iterator();
    }
}