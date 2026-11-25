import java.util.Scanner;

class ArbolesBinariosSearch {
    NodoArbol raiz;

    // 1) Crear
    public ArbolesBinariosSearch() {
        raiz = null;
    }

    // 2) Insertar
    public void insertarNodo(int dato) {
        NodoArbol nuevoNodo = new NodoArbol(dato);

        if (raiz == null) { // Arbol vacio
            raiz = nuevoNodo;
        } else { // Ya existen otros nodos

            NodoArbol nodoPadre;
            NodoArbol nodoActual = raiz;

            while (nodoActual != null) {
                nodoPadre = nodoActual;

                if (dato <= nodoActual.getDato()) { // SubArbol izquierdo
                    nodoActual = nodoActual.getNodoIzquierdo();
                    if (nodoActual == null) {
                        nodoPadre.setNodoIzquierdo(nuevoNodo);
                    }

                } else { // SubArbol derecho
                    nodoActual = nodoActual.getNodoDerecho();
                    if (nodoActual == null) {
                        nodoPadre.setNodoDerecho(nuevoNodo);
                    }
                }
            }
        }

    }// Metodo insertar (Agregar)

    // Eliminar
    public boolean eliminarElemento(int dato) {
        if (!(raiz == null)) {

            NodoArbol anterior = raiz;
            NodoArbol aux = raiz;
            String ladoArbol = "";

            // proceso de busqueda-----------------------------------
            while (aux.getDato() != dato) {
                anterior = aux;
                if (dato <= aux.getDato()) { // izquierda
                    aux = aux.getNodoIzquierdo();
                    ladoArbol = "IZQ";
                } else {// Derecha
                    aux = aux.getNodoDerecho();
                    ladoArbol = "DER";
                }

                if (aux == null) {
                    System.out.println("\n" + dato + " Buscado y no encontrado");
                    return false;
                }
            } // while

            System.out.println("\n" + dato + " encontrado");

            // proceso de eliminacion (se encontro el dato)---------------------
            // Escenario 1: El nodo a eliminar es HOJA
            if (aux.getNodoIzquierdo() == null && aux.getNodoDerecho() == null) { // verficar si es hoja
                if (aux == raiz)
                    raiz = null;
                else if (ladoArbol.equals("IZQ"))
                    anterior.setNodoIzquierdo(null);
                else
                    anterior.setNodoDerecho(null);
            } else if (aux.getNodoIzquierdo() == null) { // verificar si tiene un solo hijo a la IZQ
                if (aux == raiz)
                    raiz = aux.getNodoIzquierdo();
                else if (ladoArbol.equals("IZQ"))
                    anterior.setNodoIzquierdo(aux.getNodoIzquierdo());
                else
                    anterior.setNodoDerecho(aux.getNodoIzquierdo());
            } else if (aux.getNodoDerecho() == null) { // verificar si tiene un solo hijo a la DER
                if (aux == raiz)
                    raiz = aux.getNodoDerecho();
                else if (ladoArbol.equals("IZQ"))
                    anterior.setNodoIzquierdo(aux.getNodoDerecho());
                else
                    anterior.setNodoDerecho(aux.getNodoDerecho());
            } else { // de lo contrario TIENE DOS HIJOS -----------------------------------
                NodoArbol reemplazo = reemplazar(aux);
                if (aux == raiz)
                    raiz = reemplazo;
                else if (ladoArbol.equals("IZQ"))
                    anterior.setNodoIzquierdo(reemplazo);
                else
                    anterior.setNodoDerecho(reemplazo);
                reemplazo.setNodoIzquierdo(aux.getNodoIzquierdo());
            }
            System.out.println("\n Elimnando.....");
            return true;
            // ----------------------------------------------------------------
        } else {
            System.out.println("\n Arbol vacio");
            return false;
        }
    }// metodo eliminar

    private NodoArbol reemplazar(NodoArbol nodo) {
        NodoArbol reemplazarPadre = nodo;
        NodoArbol reemplazo = nodo;
        NodoArbol auxiliar = nodo.getNodoDerecho();
        while (auxiliar != null) {
            reemplazarPadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.getNodoIzquierdo();
        }
        if (reemplazo != nodo.getNodoDerecho()) {
            reemplazarPadre.setNodoIzquierdo(reemplazo.getNodoDerecho());
            reemplazo.setNodoDerecho(nodo.getNodoDerecho());
        }
        return reemplazo;
    }

    // RAIZ-IZQUIERDA-DERECHA PRE ORDEN
    public void recorridoPRE_Orden(NodoArbol raiz) {
        if (!(raiz == null)) {
            System.out.println(raiz.getDato());
            recorridoPRE_Orden(raiz.getNodoIzquierdo());
            recorridoPRE_Orden(raiz.getNodoDerecho());
        }

    }

    // RECORRIDO EN POSORDEN izquierda-derecha-raiz
    public void recorridoPos_Orden(NodoArbol raiz) {
        if (!(raiz == null)) {
            recorridoPos_Orden(raiz.getNodoIzquierdo());
            recorridoPos_Orden(raiz.getNodoDerecho());
            System.out.println(raiz.getDato());
        }
    }

    // Recorrido en INORDEN izquierda-raiz-derecha
    public void recorrdio_InOrden(NodoArbol raiz) {
        if (!(raiz == null)) {
            recorrdio_InOrden(raiz.getNodoIzquierdo());
            System.out.println(raiz.getDato());
            recorrdio_InOrden(raiz.getNodoDerecho());
        }

    }


    public void mostrarDatoMayor(){
        if (raiz== null) {
            System.out.println("El arbol esta vacio...");
        }else{
            NodoArbol actual=raiz;

            while (actual.getNodoDerecho() !=null) {
                actual=actual.getNodoDerecho();
            }
            System.out.println("Dato mayor: "+actual.getDato());
        }
    }
    public void mostrarDatoMenor(){
        if (raiz==null) {
            System.out.println("El arbol esta vacio... ");
        }else{
            NodoArbol actual=raiz;
            while (actual.getNodoIzquierdo() != null) {
                actual=actual.getNodoIzquierdo();
            }
            System.out.println("Dato menor: "+actual.getDato());
        }
    }

    public boolean buscarElemento(int dato){
        NodoArbol actual=raiz;

        while (actual !=null) {
            if (dato==actual.getDato()) {
                return true;
            }
            else if (dato<actual.getDato()) {
                actual=actual.getNodoIzquierdo();
            }else{
                actual=actual.getNodoDerecho();
            }
        }
        return false;
    }
}

public class TestArboles {
    public static void main(String[] args) {
        Scanner nrg=new Scanner(System.in);
        ArbolesBinariosSearch abb=new ArbolesBinariosSearch();
        
        byte op;
        do {
            System.out.println("=== MENU DE ARBOLES BINARIOS ===");
            System.out.println("1. Insertar Nodo");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Mostrar nodos");
            System.out.println("4. Mostrar Dato mayor");
            System.out.println("5. Mostrar Dato menor");
            System.out.println("6. Buscar DATO");
            op=nrg.nextByte();

            switch (op) {
                case 1:
                    System.out.println("Dime el dato a ingresar");
                    int dato=nrg.nextInt();
                    abb.insertarNodo(dato);
                    break;
                case 2:
                    System.out.println("Que dato quieres eliminar: ");
                    int datoDelete=nrg.nextInt();
                    abb.eliminarElemento(datoDelete);
                    break;
                case 3:
                    System.out.println("En que orden quieres mostrar los datos??");
                    System.out.println("1. PreOrden");
                    System.out.println("2. InOrden");
                    System.out.println("3. PosOrden");
                    byte op2=nrg.nextByte();

                    switch (op2) {
                        case 1:
                            abb.recorridoPRE_Orden(abb.raiz);
                            break;
                        case 2:
                            abb.recorrdio_InOrden(abb.raiz);
                        case 3:
                            abb.recorridoPos_Orden(abb.raiz);
                            break;
                        default:
                            System.out.println("Esa opsion es incorrecta");
                            break;
                    }
                    break;
                case 4:
                    abb.mostrarDatoMayor();
                    break;
                case 5:
                    abb.mostrarDatoMenor();
                    break;
                case 6:
                    System.out.println("Que dato quierrs buscar??");
                    byte dataFind=nrg.nextByte();
                    System.out.println("Dato encontrado??: "+abb.buscarElemento(dataFind));
                    break;
                default:
                    System.out.println("Elije una opsion correcta");
                    break;
            }

        } while (op !=0);
        nrg.close();
    }
}
