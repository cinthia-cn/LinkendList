public interface Pila<E> extends Iterable<E> {

    void apilar(E elemento);

    E desapilar();

    E consultarCima();

    boolean esVacia();

    int numElementos();

    void limpiar();
}
