import java.util.Iterator;
import java.util.NoSuchElementException;

/** Practica
 * Implementación de una pila utilizando internamente un ArrayList.
 *
 * La clase reutiliza los métodos definidos en la interfaz Lista,
 * evitando volver a implementar la lógica de almacenamiento.
 *
 * La pila sigue el principio LIFO:
 * Last In, First Out.
 * El último elemento que entra es el primero que sale.
 *
 * @param <E> tipo de elementos almacenados en la pila
 */
public class PilaArray<E> implements Pila<E> {

    /*
     * La pila utiliza internamente una Lista.
     *
     * Se declara con el tipo Lista y no directamente como ArrayList
     * para trabajar mediante la interfaz.
     *
     * El modificador final indica que, una vez asignada la referencia,
     * no podrá apuntar a otra lista distinta.
     */
    private final Lista<E> elementos;

    /**
     * Construye una pila utilizando una implementación ArrayList.
     */
    public PilaArray() {
        elementos = new ArrayList<E>();
    }

    /**
     * Agrega un elemento en la cima de la pila.
     *
     * Se reutiliza el método agregarFinal() de Lista, debido a que
     * la cima de la pila se representa mediante el último elemento.
     *
     * @param elemento elemento que se agregará a la pila
     */
    @Override
    public void apilar(E elemento) {
        elementos.agregarFinal(elemento);
    }

    /**
     * Elimina y devuelve el elemento que se encuentra en la cima.
     *
     * Debido al comportamiento LIFO, se elimina el último elemento
     * almacenado en la lista.
     *
     * @return elemento eliminado de la cima
     * @throws NoSuchElementException si la pila está vacía
     */
    @Override
    public E desapilar() {

        if (esVacia()) {
            throw new NoSuchElementException(
                "La pila está vacía"
            );
        }

        return elementos.eliminarElementoFinal();
    }

    /**
     * Devuelve el elemento de la cima sin eliminarlo.
     *
     * La cima corresponde a la última posición de la lista:
     *
     * numElementos() - 1
     *
     * @return elemento ubicado en la cima
     * @throws NoSuchElementException si la pila está vacía
     */
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

    /**
     * Indica si la pila no contiene elementos.
     *
     * @return true si la pila está vacía; false en caso contrario
     */
    @Override
    public boolean esVacia() {
        return elementos.esVacia();
    }

    /**
     * Devuelve el número de elementos almacenados en la pila.
     *
     * @return cantidad de elementos
     */
    @Override
    public int numElementos() {
        return elementos.numElementos();
    }

    /**
     * Elimina todos los elementos de la pila.
     */
    @Override
    public void limpiar() {
        elementos.limpiarLista();
    }

    /**
     * Devuelve un iterador para recorrer los elementos de la pila.
     *
     * El recorrido se delega al iterador de la lista interna.
     *
     * @return iterador de los elementos
     */
    @Override
    public Iterator<E> iterator() {
        return elementos.iterator();
    }
}