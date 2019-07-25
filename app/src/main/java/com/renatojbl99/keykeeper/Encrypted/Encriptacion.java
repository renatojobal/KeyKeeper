package com.renatojbl99.keykeeper.Encrypted;


import java.util.HashMap;
import java.util.Scanner;

public class Encriptacion {

    // Instanciando objetos
    Scanner read = new Scanner(System.in);
    HashMap<String, String> dicCifrado;
    HashMap<String, String> dicDescifrado;

    public Encriptacion() {
        dicCifrado = new HashMap<String, String>();
        dicDescifrado = new HashMap<String, String>();
        getDicCifrado();
        getDicDescifrado();
    }



    // Métodos para presentar el menú
    private int menu() {
        int answer;
        System.out.println("\n\n    **********************************\n"
                + "Elija una de las siguientes opciones:\n\n"
                + "1  = Encriptar\n"
                + "2  = Desencriptar\n"
                + "0  = Salir\n"
                + "\n    **********************************\n"
                + "Respuesta: ");
        answer = read.nextInt();
        answer = validate(answer, 0, 2);
        return answer;
    }

    // Metodo para validar una respuesta entera dentro de un rango
    private int validate(int answer, int minimum, int maximum) {
        while (answer > maximum || answer < minimum) {
            System.out.println("ERROR. Respuesta fuera de rango, por favor ingrese de nuevo una respuesta válida: ");
            answer = read.nextInt();
        }
        return answer;
    }

    // Metodo para encriptar
    public String encriptar(String cadena) {
        //System.out.printf("*La cadena es: %s\n", cadena);
        String[] sentencia = cadena.split("");
        //System.out.println("*Eliminamos el primero elemento de la cadena");
        sentencia = deleteFirst(sentencia);
        //presentArray(sentencia);
        String[] sentenciaEncriptada = new String[sentencia.length];
        String clave, cadenaEncriptada = "";
        for (int i = 0; i < sentencia.length; i++) {
            //System.out.println("**Entramos al for");
            clave = sentencia[i];
            // System.out.println("**Obtenemos la clave");
            //System.out.printf("La clave es de tipo: %s\n", clave.getClass());
            //System.out.printf("La clave es: %s\n", clave);

            if (dicCifrado.containsKey(clave)){
                sentenciaEncriptada[i] = dicCifrado.get(clave).toString();
            }else{
                sentenciaEncriptada[i] = sentencia[i];
            }

            //System.out.println("**Agregamos la letra al arreglo de sentencia encriptada");
            cadenaEncriptada = String.format("%s%s", cadenaEncriptada, sentenciaEncriptada[i]);
            //System.out.println("**Agregamos la letra a la cadena final");
        }
        return cadenaEncriptada;
    }

    // Metodo para desencriptar
    public String desencriptar(String cadena) {
        //System.out.printf("*La cadena es: %s\n", cadena);
        String[] sentencia = cadena.split("");
        //System.out.println("*Eliminamos el primero elemento de la cadena");
        sentencia = deleteFirst(sentencia);
        //presentArray(sentencia);
        String[] sentenciaDesencriptada = new String[sentencia.length];
        String clave, cadenaDesencriptada = "";
        for (int i = 0; i < sentencia.length; i++) {
            //System.out.println("**Entramos al for");
            clave = sentencia[i];
            //System.out.println("**Obtenemos la clave");
            //System.out.printf("La clave es de tipo: %s\n", clave.getClass());
            //System.out.printf("La clave es: %s\n", clave);

            if (dicDescifrado.containsKey(clave)){
                sentenciaDesencriptada[i] = dicDescifrado.get(clave).toString();
            }else{
                sentenciaDesencriptada[i] = sentencia[i];
            }

            //System.out.println("**Agregamos la letra al arreglo de sentencia encriptada");
            cadenaDesencriptada = String.format("%s%s", cadenaDesencriptada, sentenciaDesencriptada[i]);
            //System.out.println("**Agregamos la letra a la cadena final");
        }
        return cadenaDesencriptada;
    }

    // Metodo para obtener el diccionario de cifrar
    private void getDicCifrado() {
        dicCifrado.put("q", "W");
        dicCifrado.put("w", "E");
        dicCifrado.put("e", "R");
        dicCifrado.put("r", "T");
        dicCifrado.put("t", "Y");
        dicCifrado.put("y", "U");
        dicCifrado.put("u", "I");
        dicCifrado.put("i", "O");
        dicCifrado.put("o", "P");
        dicCifrado.put("p", "Q");
        dicCifrado.put("a", "S");
        dicCifrado.put("s", "D");
        dicCifrado.put("d", "F");
        dicCifrado.put("f", "G");
        dicCifrado.put("g", "H");
        dicCifrado.put("h", "J");
        dicCifrado.put("j", "K");
        dicCifrado.put("k", "L");
        dicCifrado.put("l", "A");
        dicCifrado.put("z", "X");
        dicCifrado.put("x", "C");
        dicCifrado.put("c", "V");
        dicCifrado.put("v", "B");
        dicCifrado.put("b", "N");
        dicCifrado.put("n", "M");
        dicCifrado.put("m", "Z");
        dicCifrado.put("1", "3");
        dicCifrado.put("2", "4");
        dicCifrado.put("3", "5");
        dicCifrado.put("4", "6");
        dicCifrado.put("5", "7");
        dicCifrado.put("6", "8");
        dicCifrado.put("7", "9");
        dicCifrado.put("8", "0");
        dicCifrado.put("9", "1");
        dicCifrado.put("0", "2");
        dicCifrado.put("@", "~");
        dicCifrado.put("_", "(");
        dicCifrado.put(".", "/");
        dicCifrado.put(" ", ".");
        dicCifrado.put("Q", "w");
        dicCifrado.put("W", "e");
        dicCifrado.put("E", "r");
        dicCifrado.put("R", "t");
        dicCifrado.put("T", "y");
        dicCifrado.put("Y", "u");
        dicCifrado.put("U", "i");
        dicCifrado.put("I", "o");
        dicCifrado.put("O", "p");
        dicCifrado.put("P", "q");
        dicCifrado.put("A", "s");
        dicCifrado.put("S", "d");
        dicCifrado.put("D", "f");
        dicCifrado.put("F", "g");
        dicCifrado.put("G", "h");
        dicCifrado.put("H", "j");
        dicCifrado.put("J", "k");
        dicCifrado.put("K", "l");
        dicCifrado.put("L", "a");
        dicCifrado.put("Z", "x");
        dicCifrado.put("X", "c");
        dicCifrado.put("C", "v");
        dicCifrado.put("V", "b");
        dicCifrado.put("B", "n");
        dicCifrado.put("N", "m");
        dicCifrado.put("M", "z");
        dicCifrado.put("'", "'");
        dicCifrado.put("-", "-");
        dicCifrado.put("#", "!");

    }

    // Metodo para obtener el diccionario de descifrar
    private void getDicDescifrado() {
        dicDescifrado.put("w", "Q");
        dicDescifrado.put("e", "W");
        dicDescifrado.put("r", "E");
        dicDescifrado.put("t", "R");
        dicDescifrado.put("y", "T");
        dicDescifrado.put("u", "Y");
        dicDescifrado.put("i", "U");
        dicDescifrado.put("o", "I");
        dicDescifrado.put("p", "O");
        dicDescifrado.put("q", "P");
        dicDescifrado.put("s", "A");
        dicDescifrado.put("d", "S");
        dicDescifrado.put("f", "D");
        dicDescifrado.put("g", "F");
        dicDescifrado.put("h", "G");
        dicDescifrado.put("j", "H");
        dicDescifrado.put("k", "J");
        dicDescifrado.put("l", "K");
        dicDescifrado.put("a", "L");
        dicDescifrado.put("x", "Z");
        dicDescifrado.put("c", "X");
        dicDescifrado.put("v", "C");
        dicDescifrado.put("b", "V");
        dicDescifrado.put("n", "B");
        dicDescifrado.put("m", "N");
        dicDescifrado.put("z", "M");
        dicDescifrado.put("3", "1");
        dicDescifrado.put("4", "2");
        dicDescifrado.put("5", "3");
        dicDescifrado.put("6", "4");
        dicDescifrado.put("7", "5");
        dicDescifrado.put("8", "6");
        dicDescifrado.put("9", "7");
        dicDescifrado.put("0", "8");
        dicDescifrado.put("1", "9");
        dicDescifrado.put("2", "0");
        dicDescifrado.put("~", "@");
        dicDescifrado.put("(", "_");
        dicDescifrado.put("/", ".");
        dicDescifrado.put(".", " ");
        dicDescifrado.put("W", "q");
        dicDescifrado.put("E", "w");
        dicDescifrado.put("R", "e");
        dicDescifrado.put("T", "r");
        dicDescifrado.put("Y", "t");
        dicDescifrado.put("U", "y");
        dicDescifrado.put("I", "u");
        dicDescifrado.put("O", "i");
        dicDescifrado.put("P", "o");
        dicDescifrado.put("Q", "p");
        dicDescifrado.put("S", "a");
        dicDescifrado.put("D", "s");
        dicDescifrado.put("F", "d");
        dicDescifrado.put("G", "f");
        dicDescifrado.put("H", "g");
        dicDescifrado.put("J", "h");
        dicDescifrado.put("K", "j");
        dicDescifrado.put("L", "k");
        dicDescifrado.put("A", "l");
        dicDescifrado.put("X", "z");
        dicDescifrado.put("C", "x");
        dicDescifrado.put("V", "c");
        dicDescifrado.put("B", "v");
        dicDescifrado.put("N", "b");
        dicDescifrado.put("M", "n");
        dicDescifrado.put("Z", "m");
        dicDescifrado.put("'", "'");
        dicDescifrado.put("-", "-");
        dicDescifrado.put("!", "#");
        dicDescifrado.put(" ", " ");


    }

    // Metodo para presentar un arreglo String []
    private void presentArray(String [] array){
        for(int i = 0; i < array.length; i++){
            System.out.printf("-[%s]-", i, array[i]);
        }

    }


    // Metodo para eliminar el primer elemento de un arreglo
    private String [] deleteFirst(String [] arrray){
        String [] newArray = new String[arrray.length-1];
        for (int i = 0; i < newArray.length; i++){
            newArray[i] = arrray[i+1];
        }
        return newArray;

    }
}
