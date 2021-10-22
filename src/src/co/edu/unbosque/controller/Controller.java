package src.co.edu.unbosque.controller;

 import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import src.co.edu.unbosque.model.Letra;
import src.co.edu.unbosque.view.View;



public class Controller {

    private View view;
    private Letra[] lista;
    private String[] m;
    private String palabra;
    private String palabraS;
    private String[] s;
    private String clave;
    private ArrayList<String> r;



    public  Controller() {
    	lista = new Letra[32];
        view = new View();
        run();
    }
    public void run(){
    	palabra = view.ingresarInformacion("Ingrese la palabra a cifrar").toUpperCase();
    	palabra = palabra.replace(" ", "");
    	do {
    	palabraS = view.ingresarInformacion("Ingrese la clave secreta"
    			+ "(Debe ser larga que la palabra a cifrar)").toUpperCase();
    	palabraS = palabraS.replace(" ", "");
    	}
    	while(palabraS.length() < palabra.length());
    	
    	
    	m = new String[palabra.length()];
    	s =  new String[palabraS.length()];
    	inicializarArreglo();
    	asignarBinarios();
    	
    

    	
    	definirClave(15,25, regla30());
    	String[] s = comparar(0,null);
    	view.mostrarInformacion("Palabra Cifrada: " + devolverPalabra(s) + 
    			"\nUsando la Clave: " + clave);
    	String[] n = comparar(1,s);
    	view.mostrarInformacion("Palabra Descifrada: " + devolverPalabra(n));
    }
    
    public void inicializarArreglo() {
    	String[] s = {"#","A","B","C","D","E","F","G","H"
    			,"I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U"
    			,"V","W","X","Y","Z","1","2","3","4"};
    	
    	for (int i = 0; i < lista.length; i++) {
			lista[i] = new Letra(Integer.toBinaryString(i), s[i]);
			//System.out.println(lista[i].getSimbolo() + " valor: " + lista[i].getValor());
		}
    }
    
    public void asignarBinarios() {
    	String bin;
    	for (int i = 0; i < m.length; i++) {
    		if(i != palabra.length()-1) {
			bin = buscarLetra(palabra.substring(i, i+1));
    		}else {
    			bin = buscarLetra(palabra.substring(i));
    		}
    		m[i] = bin;
    		
		}
    	
    	for (int i = 0; i < s.length; i++) {
    		if(i != palabraS.length()-1) {
			bin = buscarLetra(palabraS.substring(i, i+1));
    		}else {
    			bin = buscarLetra(palabraS.substring(i));
    		}
    		s[i] = bin;
    		
		}
    }
    
    public String buscarLetra(String n) {
    	for (int i = 0; i < lista.length; i++) {
			if(n.equals(lista[i].getSimbolo())) {
				return lista[i].getValor();
			}
		}
    	return "";
    }
    
    public ArrayList<String> regla30() {
    	String vecinos = "";
    	String cadena = "";
    	String sigCadena = "";
    	ArrayList<String> arr = new ArrayList<>();
    	
    	for (int i = 0; i < s.length; i++) {
			cadena = cadena+ s[i];
		}
    	int n = cadena.length();
    	arr.add(cadena);
    	for (int i = 0; i < 200; i++) {
    		sigCadena = "";
			for (int j = 0; j < n; j++) {
				vecinos = definirVecinos(j, cadena);
				if(vecinos.equalsIgnoreCase("100") ||
						vecinos.equalsIgnoreCase("011") ||
						vecinos.equalsIgnoreCase("010") ||
						vecinos.equalsIgnoreCase("001") ) {
					sigCadena = sigCadena + "1";
				}else {
					sigCadena = sigCadena + "0";
				}
				
			}
			cadena = sigCadena;
			arr.add(sigCadena);
		}
    	return arr;
    }
    public String definirVecinos(int i, String cadena) {
    	String v = "";
    	if(i == 0) {
    		v= String.valueOf(""+cadena.charAt(cadena.length()-1) 
    				+cadena.charAt(i) + cadena.charAt(i+1));

    	}else if(i == cadena.length()-1) {
    		v= ""+cadena.charAt(i-1) 
    				+cadena.charAt(i) + cadena.charAt(0);

    	}else {
    		v=""+cadena.charAt(i-1) 
    				+cadena.charAt(i) + cadena.charAt(i+1);

    	}
    	return v;
    }
    public void definirClave(int i, int j, ArrayList<String> s) {
    	clave = "";
    	String cadena = "";

    	
    	for (int l = 0; l < this.s.length; l++) {
			cadena = cadena+ this.s[l];
		}
    	for (int k = 0; k < cadena.length(); k++) {
    		if(j < s.size()) {
		clave = clave + ""+ s.get(j).substring(i,i+1); 	
    		}else {
    			clave = clave + ""+ s.get(j).substring(i); 	
    		}
		j++;
		}

    }

    public String[] comparar(int opcion, String[] sw) {

    	String cadenaM = "";
    	String aux = "";
    	String [] binCifrado = new String[m.length];
    	
    	for (int i = 0; i < m.length; i++) {
    		if(opcion == 0) {
    			cadenaM = cadenaM + "" + m[i];
    		}else {
    			cadenaM = cadenaM + "" + sw[i];
    		}
		}
    	System.out.println(cadenaM);
    	System.out.println(clave);
    	for (int i = 0; i < cadenaM.length(); i++) {
    			char m = cadenaM.charAt(i);
        		char c = clave.charAt(i);
			if(m == c) {
				aux = aux + "" + 0;
			}else {
				aux = aux + "" + 1;
			}
		}
    	int n = 0;
    	for (int i = 0; i < m.length; i++) {
    		String aux2 = "";
    		for (int j = n; j < n+5; j++) {
				aux2 = aux2 + "" + aux.charAt(j);
			}
    		binCifrado[i] = aux2;
			n+=5;
		}
    	
    	return binCifrado;
    }
    
   
    
    public String devolverPalabra(String [] s) {
    	String resultado = "";
    	for (int i = 0; i < s.length; i++) {
    		for (int j = 0; j < lista.length; j++) {
    			if(s[i].equalsIgnoreCase(lista[j].getValor())) {
    				resultado = resultado + lista[j].getSimbolo().toLowerCase();
    			}
    		}
		}
    	return resultado;
    }
    
}

