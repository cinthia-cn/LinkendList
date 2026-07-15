public interface Cola<E> extends Iterable<E> {

    /**
     * Agrega un elemento al final de la cola (por donde entran).
     * @param elemento elemento que se va a encolar
     */
    void encolar(E elemento);

    /**
     * Elimina y regresa el elemento que esta al frente de la cola
     * (el que lleva mas tiempo esperando, por donde salen).
     * @return el elemento eliminado
     */
    E desencolar();

    /**
     * Regresa el elemento que esta al frente de la cola SIN eliminarlo.
     * @return el elemento al frente
     */
    E consultarFrente();

    /**
     * Regresa verdadero si la cola no tiene elementos.
     */
    boolean esVacia();

    /**
     * Regresa el numero de elementos que tiene la cola.
     */
    int numElementos();

    /**
     * Elimina todos los elementos de la cola.
     */
    void limpiar();
}



