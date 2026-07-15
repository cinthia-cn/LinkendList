import java.util.Iterator;


public class LinkedList<E> implements Lista<E> {


    private class Nodo {

        private Nodo siguiente= null;
        
        private E info=null;
    
        Nodo(Nodo siguiente, E info) {
            this.siguiente = siguiente;
            this.info = info;
        }
    
        Nodo getSiguiente() {
            return siguiente;
        }
    
        void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    
        E getInfo() {
            return info;
        }
    
        void setInfo(E info) {
            this.info = info;
        }
    }

	private Nodo primero=null; 
	private Nodo ultimo=null; 		
	private int tamanio=0;


	@Override
	public void agregarElemento(E e) {
		Nodo aux = new Nodo (null, e);
		if (esVacia()) {
			this.primero = aux;
			this.ultimo = aux;
			
		} else {
			this.ultimo.setSiguiente(aux);
			this.ultimo = aux;
		}
		    aux = null;
			this.tamanio++;
	}



	@Override
	public void agregarInicio(E e) {
		Nodo aux = new Nodo (null, e);
		if (esVacia()) {
			this.primero = aux;
			this.ultimo = aux;
			
		} else {
			aux.setSiguiente(this.primero);
			this.primero = aux;
		}
		    aux = null;
			this.tamanio++;

	}

	@Override
	public void agregarFinal(E e) { //PODEMOS COPEAR EL METODO DE LA PRACTICA PASADA. 
		Nodo aux = new Nodo (null, e);
		if (esVacia()) {
			//SE CREA EL NODO Y SE ASIGNA A PRIMERO Y ULTIMO YA QUE ES EL UNICO NODO DE LA LISTA.
			this.primero = aux;
			this.ultimo = aux; 
		} else {
			this.ultimo.setSiguiente(aux);//ASIGNAMOS EL NODO AUXILIAR COMO SIGUIENTE DEL ULTIMO NODO DE LA LISTA.
			this.ultimo = aux;
		}
		aux = null;//LIBERAMOS EL NODO AUXILIAR PARA QUE NO OCUPA ESPACIO EN MEMORIA.
		this.tamanio++;//INCREMENTAMOS EL TAMAÑO DE LA LISTA YA QUE SE AGREGO UN NUEVO ELEMENTO.
	}

	@Override
	public void agregarPosicion(E e, int posicion) {
		//VAMOS A VERIFICAR SI LA POSICION ES VALIDA, SI NO LO ES, LANZAMOS UNA EXCEPCION.
		if (posicion >= 0 && posicion < tamanio) {
			if (posicion == 0) {
				agregarInicio(e);
			}else{
				if(posicion== tamanio){
					agregarFinal(e);
				}else{
					Nodo aux = new Nodo (null, e);
					Nodo iterador = primero;
					for(int i=0; i<posicion-1; i++){
						iterador = iterador.getSiguiente();
					}
					aux.setSiguiente(iterador.getSiguiente());//ASIGNAMOS EL SIGUIENTE DEL NODO AUXILIAR COMO EL SIGUIENTE DEL NODO ITERADOR.
					iterador.setSiguiente(aux);
					aux = null; //LIBERAMOS EL NODO AUXILIAR PARA QUE NO OCUPA ESPACIO EN MEMORIA.Lo solto mama, esta con la miss(ya esta dentro del nodo)
					iterador = null;
					this.tamanio++;
				}
			}

		}else {
			throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
		}
	}

	@Override
	public E eliminarElemento() {
		
		return eliminarElementoFinal();
		
	}

	@Override
	public E eliminarElementoInicio() {
		if (esVacia()) {	
			throw new IllegalStateException("La lista está vacía");
		}
		E elementoEliminado = primero.getInfo();
		primero = primero.getSiguiente();
		tamanio--;
		return elementoEliminado;
	}

	@Override
	public E eliminarElementoFinal() {
		if (esVacia()) {
			throw new IllegalStateException("La lista está vacía");
		}
		E elementoEliminado = ultimo.getInfo();
		if (primero == ultimo) {
			primero = null;
			ultimo = null;
		} else {
			Nodo iterador = primero;
			while (iterador.getSiguiente() != ultimo) {
				iterador = iterador.getSiguiente();
			}
			iterador.setSiguiente(null);
			ultimo = iterador;
		}
		tamanio--;
		return elementoEliminado;
	}

	@Override
	public E eliminarElementoPosicion(int posicion) {
		if (esVacia()) {
			throw new IllegalStateException("La lista está vacía");
		}
		if (posicion < 0 || posicion >= tamanio) {
			throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
		}
		if (posicion == 0) {
			return eliminarElementoInicio();
		}
		if (posicion == tamanio - 1) {
			return eliminarElementoFinal();
		}
		Nodo iterador = primero;
		for (int i = 0; i < posicion - 1; i++) {
			iterador = iterador.getSiguiente();
		}
		E elementoEliminado = iterador.getSiguiente().getInfo();
		iterador.setSiguiente(iterador.getSiguiente().getSiguiente());
		tamanio--;
		return elementoEliminado;
	}
	

	@Override
	public boolean esVacia() {
		return tamanio==0;
	}

	@Override
	public int numElementos() {
		// TODO Auto-generated method stub
		return tamanio;
	}

	@Override
	public void limpiarLista() {
		primero = null;
		ultimo = null;
		tamanio = 0;


	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] convertirArreglo() {

		Object[] arreglo = new Object[tamanio];
		Nodo aux = primero;
		for(int i=0;aux!=null;i++){
			arreglo[i]=aux.getInfo();
			aux = aux.getSiguiente();
		}

		return (E[])arreglo;
	}

	@Override
	public E consultar(int posicion) {
		if (esVacia()) {
			throw new IllegalStateException("La lista está vacía");
		}
		if (posicion < 0 || posicion >= tamanio) {
			throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
		}
		Nodo iterador = primero;
		for (int i = 0; i < posicion; i++) {
			iterador = iterador.getSiguiente();
		}
		return iterador.getInfo();
	}

	// Recorre nodo por nodo comparando con equals() hasta encontrar el elemento.
    // A diferencia de un arreglo, aqui no hay acceso directo por indice,
    // por lo que hay que avanzar nodo a nodo contando la posicion.
    @Override
    public int BusquedaLineal(E elemento) {
        if (esVacia()) {
            throw new java.util.NoSuchElementException("La lista esta vacia");
        }
        Nodo iterador = primero;
        int posicion = 0;
        while (iterador != null) {
            if (iterador.getInfo().equals(elemento)) {
                return posicion;
            }
            iterador = iterador.getSiguiente();
            posicion++;
        }
        return -1; // Elemento no encontrado
    }

    // Version recursiva: en vez de un indice numerico usamos el propio
    // nodo como "puntero" que avanza en cada llamada.
    @Override
    public int BusquedaLinealRecursiva(E e) {
        return busquedaLinealRecursivaAux(e, primero, 0);
    }

    private int busquedaLinealRecursivaAux(E e, Nodo nodo, int posicion) {
        if (nodo == null) {
            return -1; // Elemento no encontrado
        }
        if (nodo.getInfo().equals(e)) {
            return posicion;
        }
        return busquedaLinealRecursivaAux(e, nodo.getSiguiente(), posicion + 1);
    }

    // La busqueda binaria clasica necesita acceso directo a la mitad del
    // arreglo (datos[medio]) en O(1). En una lista ligada eso no existe:
    // llegar a una posicion siempre cuesta recorrer nodo por nodo, es
    // decir O(n). Por eso reutilizamos consultar(medio), que ya hace ese
    // recorrido. Sigue funcionando (la lista debe estar ordenada), solo
    // que no tiene la ventaja de velocidad que sí tiene en ArrayList.
    @SuppressWarnings("unchecked")
    @Override
    public int BusquedaBinaria(E elemento) {
        if (esVacia()) {
            throw new java.util.NoSuchElementException("La lista esta vacia");
        }

        int inicio = 0;
        int fin = tamanio - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Comparable<E> elementoMedio = (Comparable<E>) consultar(medio);
            int comparacion = elementoMedio.compareTo(elemento);

            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1; // Elemento no encontrado
    }

	    // Para ordenar reutilizamos convertirArreglo(): sacamos todos los
    // valores a un Object[], los ordenamos ahi (donde SI hay acceso
    // directo por indice, igual que en ArrayList) y despues los volvemos
    // a escribir en los nodos en el mismo orden. Es mas simple y menos
    // propenso a errores que mover los nodos (los "siguiente") a mano.
    @SuppressWarnings("unchecked")
    @Override
    public void ordenarInsercion() {
        Object[] valores = convertirArreglo();

        for (int i = 1; i < valores.length; i++) {
            Comparable<E> actual = (Comparable<E>) valores[i];
            int j = i - 1;
            while (j >= 0 && ((Comparable<E>) valores[j]).compareTo((E) actual) > 0) {
                valores[j + 1] = valores[j];
                j--;
            }
            valores[j + 1] = actual;
        }

        escribirDesdeArreglo(valores);
    }

	 @SuppressWarnings("unchecked")
    @Override
    public void ordenaMerge() {
        Object[] valores = convertirArreglo();
        mergeSort(valores, 0, valores.length - 1);
        escribirDesdeArreglo(valores);
    }

    @SuppressWarnings("unchecked")
    private void mergeSort(Object[] valores, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return;
        }
        int medio = izquierda + (derecha - izquierda) / 2;
        mergeSort(valores, izquierda, medio);
        mergeSort(valores, medio + 1, derecha);

        Object[] temporal = new Object[derecha - izquierda + 1];
        int i = izquierda, j = medio + 1, k = 0;

        while (i <= medio && j <= derecha) {
            Comparable<E> a = (Comparable<E>) valores[i];
            E b = (E) valores[j];
            if (a.compareTo(b) <= 0) {
                temporal[k++] = valores[i++];
            } else {
                temporal[k++] = valores[j++];
            }
        }
        while (i <= medio) {
            temporal[k++] = valores[i++];
        }
        while (j <= derecha) {
            temporal[k++] = valores[j++];
        }

        System.arraycopy(temporal, 0, valores, izquierda, temporal.length);
    }

    // Recorre los nodos existentes en orden y les pone el valor que
    // corresponde segun el arreglo ya ordenado. Como el numero de nodos
    // no cambia, solo estamos reemplazando el "info" de cada uno.
    @SuppressWarnings("unchecked")
    private void escribirDesdeArreglo(Object[] valores) {
        Nodo iterador = primero;
        int i = 0;
        while (iterador != null) {
            iterador.setInfo((E) valores[i]);
            iterador = iterador.getSiguiente();
            i++;
        }
    }


    @Override
    public Iterator<E> iterator() { //METODO ANONIMO QUE DEVUELVE UN ITERADOR PARA RECORRER LA LISTA.
        return new Iterator<E>(){

            Nodo nodo= primero;
            @Override
            public boolean hasNext() { //TIENE SIGUIENE?
                return nodo!=null;// SI NODO ES DIFERENTE DE NULL, ENTONCES HAY SIGUIENTE.
            }

            @Override
            public E next() {
                E tmp=nodo.getInfo();// GUARDAMOS LA INFORMACION DEL NODO ACTUAL EN UNA VARIABLE TEMPORAL.
                nodo=nodo.getSiguiente();// AVANZAMOS AL SIGUIENTE NODO.
                return tmp;// DEVOLVEMOS LA INFORMACION DEL NODO ACTUAL.
            }

        };
    }

}



