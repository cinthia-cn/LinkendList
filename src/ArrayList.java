import java.util.Iterator;

// Clase ArrayList generica.   //Recuerden implementar todos los métodos en ArrayList.
// E representa el tipo de dato que guardara la lista.
// Implementa la interfaz Lista<E>.
public class ArrayList<E> implements Lista<E> {

    // Tamanio maximo inicial por defecto del arreglo
    private static final int MAX = 5;

    // Indica cuantos elementos tiene actualmente la lista
    private int indice = 0;

    // Arreglo donde se almacenan los datos
    private Object[] datos = null; 

    // Constructor por defecto.
    // Crea una lista con tamanio inicial MAX.
    public ArrayList() {
        this(MAX);
    }

    // Constructor que permite indicar el tamanio inicial del arreglo.
    public ArrayList(int tam) {

        // Si el tamanio es negativo, se lanza una excepcion.
        if (tam < 0) {
            throw new IllegalArgumentException();
        }

        // Se crea el arreglo con el tamanio indicado.
        datos = new Object[tam];
    }

    // Metodo privado que limpia las referencias del arreglo.
    // Esto ayuda a que los objetos puedan ser eliminados por el Garbage Collector.
    private void asegurarGC() {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = null;
        }
    }

    // Agrega un elemento al final de la lista.
    @Override
    public void agregarFinal(E e) {

        Object[] aux = null;

        // Si el arreglo ya casi esta lleno, se crea uno mas grande.
        if (!(indice < datos.length - 1)) {

            // Nuevo arreglo con crecimiento del 50%.
            aux = new Object[datos.length + datos.length / 2];

            // Copia los datos del arreglo viejo al nuevo.
            System.arraycopy(datos, 0, aux, 0, datos.length);

            // Limpia las referencias del arreglo anterior.
            asegurarGC();

            // El arreglo principal ahora apunta al arreglo nuevo.
            datos = aux;
        }

        // Se guarda el elemento en la posicion actual.
        datos[indice] = e;

        // Se incrementa el numero de elementos.
        indice++;
    }

    // Agrega un elemento al inicio de la lista.
    @Override
    public void agregarInicio(E e) {

        Object[] auxobj = null;

        // Imprime el numero actual de elementos.
        System.out.println(indice);

        // Si todavia hay espacio, recorre los elementos una posicion a la derecha.
        if (indice < datos.length - 1) {
            System.arraycopy(datos, 0, datos, 1, indice + 1);
        } else {

            // Si no hay espacio, crea un arreglo mas grande.
            auxobj = new Object[datos.length + datos.length / 2];

            // Copia los elementos al nuevo arreglo desde la posicion 1.
            System.arraycopy(datos, 0, auxobj, 1, datos.length);

            // Limpia las referencias del arreglo anterior.
            asegurarGC();

            // El arreglo principal ahora apunta al arreglo nuevo.
            datos = auxobj;
        }

        // Coloca el nuevo elemento al inicio.
        datos[0] = e;

        // Aumenta el contador de elementos.
        indice++;
    }
    



    //PARTE DE LA PRACTICA PASADA
    //IMPLEMENTE EL METODO DE AGEGAR UN ELEMENTO A LA LISTA
    @Override 
    public void agregarElemento(E e){
        agregarFinal(e);
     } 

    // METODO QUE AGREGA A UNA POSICION ESPECIFICA
    @Override
    public void agregarPosicion(E e, int posicion){
        //AQUI VALIDA QUE LA POSICION SEA VALIDA
        if(posicion < 0 || posicion > indice){
            throw new IndexOutOfBoundsException(" La posicion es valida" + posicion);
        }

    // otros casos
    if (posicion == indice){
        agregarFinal(e);
        return;
    }
    if (posicion == 0){
        agregarInicio(e);
        return;
    }

    //SI EL ARREGLO ESTA LLENO CREA UNO MAS GRANDE
    if (indice == datos.length){
        Object[] aux = new Object[datos.length + datos.length / 2];
        System.arraycopy(datos, 0, aux, 0,datos.length);
        asegurarGC();
        datos = aux;
    }
    //LO QUE PASA ES QUE RECORRE LOS ELEMENTOS UNA POSICION A LA DERECHA
    // PARA QUE PUEDA INSERTAR EL NUEVO ELEMENTO.
    System.arraycopy(datos,posicion,datos,posicion +1, indice - posicion);
    datos[posicion] = e;//AQUI PONE EL ELEMENTO EN LA POSICION INIDCADA
    indice ++; // Y AUMENTA EL CONTADOR DE ELEMENTOS


    }
    

    // Indica si la lista esta vacia.
    @Override
    public boolean esVacia() {
        return indice == 0;
    }

    // Devuelve cuantos elementos tiene la lista.
    @Override
    public int numElementos() {
        return indice;
    }

    // Limpia la lista.
    @Override
    public void limpiarLista() {

        // Reinicia el contador de elementos.
        indice = 0;

        // Borra las referencias de los objetos almacenados.
        asegurarGC();
    }

    //DE LA PRACTICA PASADA
     // METODO DE ELIMINAR UN ELEMENTO DE LA LISTA
    @Override
    public E eliminarElemento(){
        return eliminarElementoFinal();
    }

    //METODO ELIMINAR UN ELEMENTO DEL INICIO
    @Override
    public E eliminarElementoInicio(){
     //PRIMERO CHECAMOS QUE NO ESTE VACIO, SI NO, QUE VAMOS A ELIMINAR :v
     if (esVacia()) {
            throw new java.util.NoSuchElementException("La lista esta vacia");
        }

     //GUARDAMOS EL ELEMENTO QUE VA A ELIMINARSE PARA PODER REGRESARLO
         @SuppressWarnings("unchecked")
        E elemento = (E) datos[0];

        //RECORRE LOS ELEMENTOS UNA POSICION A LA IZQUIERDA
         System.arraycopy(datos, 1, datos, 0, indice - 1);

         //POR LO TANTO DISMINUYE EL CONTADOR
         indice --;

         //LIMPIA LA ULTIMA REFERENCIA PARA QUE NO SE DUPLIQUE
         datos[indice] = null;
         return elemento;

    }
     // METODO ELIMINAR ELEMENTO FINAL
    @Override
    public E eliminarElementoFinal(){
        //TENEMOS QUE VERIFICAR QUE LA LISTA ESTE VACIA
        if(esVacia()){
            throw new java.util.NoSuchElementException("La lista esta vacia");
        }
        indice --;
        @SuppressWarnings("unchecked")
        E elemento = (E) datos[indice];

        datos[indice]= null; //AQUI LIMPIA LA REFERENCIAS
        return elemento; //AQUI TAMBIEN VA A DEVOLVER EL ULTIMO ELEMENTO 
    }
     //METODO DE ELIMINAR UNA POSICION ESPECIFICA
    @Override
    public E eliminarElementoPosicion(int posicion){

        //PRIMERO VALIDAMOS QUE LA POSICION EXISTA
         if (posicion < 0 || posicion >= indice) {
            throw new IndexOutOfBoundsException("Posicion invalida: " + posicion);
        }

        // OTROS CASOS
        if (posicion == 0) {
            return eliminarElementoInicio();
        }
        if (posicion == indice - 1) {
            return eliminarElementoFinal();
        }

        @SuppressWarnings("unchecked")
        E elemento = (E) datos[posicion];

        //RECORRE LOS ELEMENTOS POSTERIORES UNA CASILLA A LA IZQUIERDA
        System.arraycopy(datos, posicion + 1, datos, posicion, indice - posicion - 1);
        indice --;
        datos[indice]=null;//LIMPIA REFERENCIAS
        return elemento;
    }



   // Indica al compilador que no muestre la advertencia
// por la conversión (cast) de Object[] a E[].
// Esta advertencia aparece porque Java no puede verificar
// completamente el tipo genérico durante la compilación.
@SuppressWarnings("unchecked")

// Indica que este método sobrescribe uno definido
// en una interfaz o clase padre.
@Override
public E[] convertirArreglo() {

    // Se crea un nuevo arreglo de tipo Object
    // cuyo tamaño es exactamente igual al número
    // de elementos almacenados en la lista (indice).
    //
    // No se utiliza datos.length porque ese valor
    // representa la capacidad total del arreglo y
    // puede contener posiciones vacías.
    Object[] aux = new Object[indice];

    // Copia los elementos del arreglo interno "datos"
    // hacia el nuevo arreglo "aux".
    //
    // Parámetros:
    // datos   -> arreglo origen.
    // 0       -> posición inicial desde donde copiar.
    // aux     -> arreglo destino.
    // 0       -> posición inicial donde se pegarán.
    // indice  -> cantidad de elementos que se copiarán.
    //
    // Solo se copian los elementos válidos de la lista,
    // ignorando las posiciones vacías del arreglo original.
    System.arraycopy(datos, 0, aux, 0, indice);

    // El arreglo "aux" es de tipo Object[].
    // Sin embargo, el método debe regresar un arreglo
    // del tipo genérico E[].
    //
    // Por ello se realiza una conversión (cast)
    // de Object[] a E[] antes de devolverlo.
    return (E[]) aux;
}

//DE LA PRACTICA PASADA
//METODO CONSULTAR
    // ESTE REGRESA EL ELEMENTO QUE HAY EN UNA POSICION ESPECIFICA, SIN ELIMINARLO
     @Override
    public E consultar(int posicion) {

        // Valida que la posicion exista dentro de la lista.
        if (posicion < 0 || posicion >= indice) {
            throw new IndexOutOfBoundsException("Posicion invalida: " + posicion);
        }

        @SuppressWarnings("unchecked")
        E elemento = (E) datos[posicion];

        return elemento;
    }



    //Un Iterator : Permite recorrer la lista usando un Iterator.
        // Un Iterator es un objeto que permite recorrer una colección
    // elemento por elemento, sin necesidad de acceder directamente
    // a las posiciones del arreglo.
    // 
    // En este caso, el Iterator permitirá recorrer los elementos
    // guardados en nuestra lista ArrayList usando un ciclo for-each,
    // por ejemplo:
    //
    // for(E elemento : lista) {
    //     System.out.println(elemento);
    // }
    //
    // Para que esto funcione, la clase debe proporcionar el método iterator().

    // Este método permite recorrer la lista usando un Iterator.
    @Override
    public Iterator<E> iterator() {

        // Se crea y se regresa un nuevo Iterator.
        // Este Iterator sabe cómo recorrer los datos guardados
        // dentro del arreglo interno llamado datos.
        //
        // new Iterator<E>() { ... }
        // significa que estamos creando una clase anónima,
        // es decir, una clase sin nombre que implementa Iterator.
        return new Iterator<E>() {

            // Esta variable indica la posición actual del recorrido.
            // Inicia en 0 porque los arreglos en Java comienzan
            // en la posición 0.
            //
            // Ejemplo:
            // datos[0], datos[1], datos[2], ...
            int i = 0;

            // Este método pregunta si todavía quedan elementos
            // por recorrer en la lista.
            @Override
            public boolean hasNext() {

                // indice representa la cantidad de elementos reales
                // que tiene la lista.
                //
                // i representa la posición actual del Iterator.
                //
                // Mientras i sea menor que indice, significa que
                // todavía hay elementos disponibles.
                //
                // Ejemplo:
                // Si indice = 3, hay elementos en:
                // datos[0], datos[1] y datos[2]
                //
                // Si i = 0: 0 < 3 → true
                // Si i = 1: 1 < 3 → true
                // Si i = 2: 2 < 3 → true
                // Si i = 3: 3 < 3 → false
                //
                // Cuando devuelve false, el recorrido termina.
                return i < indice;
            }

            // Este método devuelve el siguiente elemento de la lista.
            @Override
            public E next() {

                // Como el arreglo datos fue declarado como Object[],
                // puede guardar objetos de cualquier tipo.
                //
                // Sin embargo, la lista es genérica: ArrayList<E>.
                // Eso significa que debe regresar elementos del tipo E.
                //
                // Por eso se hace una conversión:
                // de Object a E.
                //
                // Ejemplo:
                // Si la lista es ArrayList<String>,
                // entonces E representa String.
                @SuppressWarnings("unchecked")
                E aux = (E) datos[i];

                // Después de guardar el elemento actual en aux,
                // se aumenta i para avanzar a la siguiente posición.
                //
                // Ejemplo:
                // Si i valía 0, ahora valdrá 1.
                // Si i valía 1, ahora valdrá 2.
                i++;

                // Se regresa el elemento que se obtuvo antes de avanzar.
                //
                // Aunque i ya cambió, aux conserva el dato anterior.
                //
                // Ejemplo:
                // Si datos[0] era "Ana", se regresa "Ana".
                // En la siguiente llamada a next(), se regresará datos[1].
                return aux;
            }
        };
    }
}