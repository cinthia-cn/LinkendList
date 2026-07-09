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



