package co.edu.unbosque.model;

public class Torneo {
	/* Definimos las variables auxiliares*/
	private int  MAX = 50;
	int encuentros[][] = new int[MAX][MAX];
	

	

 /** Función que permite verificar si un valor n es potencia de 2
  *      @param n es el valor que recibe la función para verificar si es múltiplo de 2 o no
  *      @return devuelve TRUE = 1 si el valor recibido es múltiplo de 2 o FALSE = 0 si no lo es
  */
 public  Boolean potenciaDos (int n)
 {
 	int potencia = 2;
 	while(potencia <= n) {
 		if (n == potencia)
 			return true;
 		potencia *= 2;
 	}
 	return false;
 }

 /** Función que llena la tabla de encuentros entre jugadores
  *      @param n - Es la cantidad de jugadores
  */
 public void torneo (int n)
 {
 	int jugador, dia;
 	/* Caso base */
 	if (n==2) {
 		encuentros[0][0]=2;
 		encuentros[1][0]=1;
 	}
 	/** Primero analizamos el caso cuando es potencia de 2 */
 	else if (potenciaDos(n)) {
 		/* Dividimos entre 2 hasta llegar al caso base */
 		torneo (n/2);
 		/* Una vez que el caso base ha sido llenado, se llena el cuadrante superior derecho */
 		for (jugador=0; jugador<n/2; jugador++) {
 			for (dia=n/2-1; dia<n-1; dia++) {
 				encuentros[jugador][dia] = jugador+1 + dia+1;
 				if (encuentros[jugador][dia] > n)
 					encuentros[jugador][dia] = encuentros[jugador][dia]-n/2;
 			}
 		}
 		/* Llenado del cuadrante inferior derecho */
 		for (jugador=n/2; jugador<n; jugador++) {
 			for (dia=n/2-1; dia<n-1; dia++) {
 				encuentros[jugador][dia] = jugador+1 - (dia+1);
 				if (encuentros[jugador][dia] <= 0)
 					encuentros[jugador][dia] = encuentros[jugador][dia] + n/2;
 			}
 		}
 		/* Llenado del cuadrante inferior izquierdo */
 		for (jugador=n/2; jugador<n; jugador++) {
 			for (dia=0; dia < n/2 - 1; dia++) {
 				encuentros[jugador][dia] = encuentros[jugador-n/2][dia] + n/2;
 			}
 		}
 	}
 	/** Si no es múltiplo de 2 pero el número de jugadores es par como el 6, 10, 12, 14, etc. */
 	else if (n%2 == 0) {
 		/* Dividimos hasta llegar al caso base */
 		torneo (n/2);
 		/* Llenado del cuadrante superior derecho */
 		for (jugador=0; jugador<n/2; jugador++) {
 			for (dia=n/2; dia<n-1; dia++) {
 				encuentros[jugador][dia] = jugador+1 + dia+1;
 				if (encuentros[jugador][dia] > n)
 					encuentros[jugador][dia] = encuentros[jugador][dia]-n/2;
 			}
 		}
 		/* Llenado del cuadrante inferior derecho */
 		for (jugador=n/2; jugador<n; jugador++) {
 			for (dia=n/2; dia<n-1; dia++) {
 				encuentros[jugador][dia] = jugador+1 - (dia+1);
 				if (encuentros[jugador][dia] <= 0)
 					encuentros[jugador][dia] = encuentros[jugador][dia] + n/2;
 				}
 		}
 		/* Llenado del cuadrante inferior izquierdo */
 		for (jugador=n/2; jugador<n; jugador++) {
 			for (dia=0; dia<n/2; dia++) {
 				if (encuentros[jugador-n/2][dia] != 0)
 					encuentros[jugador][dia] = encuentros[jugador - n / 2][dia] + n / 2;
 			}
 		}
 		/* Llenado del cuadrante superior izquierda, sustituimos los valores de 0 */
 		for (jugador=0; jugador<n/2; jugador++) {
 			for (dia=0; dia<n/2; dia++) {
 				if (encuentros[jugador][dia] == 0)
 					encuentros[jugador][dia] = jugador+1+ n/2 ;
 			}
 		}
 		/* Llenado del cuadrante inferior izquierda, sustituimos los valore de 0 */
 		for (jugador=n/2; jugador<n; jugador++) {
 			for (dia=0; dia<n/2; dia++) {
 				if (encuentros[jugador][dia] == 0)
 					encuentros[jugador][dia] = jugador+1 -n/2;
 			}
 		}
 	}
 	/** Si el número de jugadores es IMPAR */
 	else {
 		/* Si n es impar, le sumamos 1 y lo volvemos par, para que en la llamada recursiva lleguemos nuevamente al caso base */
 		torneo (n+1);
 		/* Eliminamos los valroes excedentes creados por llamar a la función con n+1
                  * estos valores excedentes son los días de descanso cuando la cantidad de jugadores es impar
                  */
 		for (jugador=0; jugador<n; jugador++) {
 			for (dia=0; dia<n; dia++) {
 				if (encuentros[jugador][dia]==n+1)
 					encuentros[jugador][dia] = 0;
 			}
 		}
 	}
 }
 

/* Función ImprimirTabla - Da formato a la impresión de la tabla de encuentros
 *      @param n - es la cantidad de jugadores
 */
public String imprimirTabla(int n)
{
	int dias, i, j;
	String resultado = "";

	/* Si n es par la cantidad de dias es n-1, si es impar la cantidad de dias es n y hay día de descanso para los jugadores*/
	if(n%2 == 0)
		dias = n-1;
	else
		dias = n;

         resultado = resultado + String.format("\nTABLA DE ENCUENTROS DEL TORNEO DE TENIS PARA %d JUGADORES EN %d DIAS:\n", n, dias);

         resultado = resultado +String.format("\n[ JUG ]");
	for(i=0; i<dias; i++) {
		if(i+1 < 10)
			resultado = resultado +String.format("[ Dia%d ]",i+1);
		else
			resultado = resultado +String.format("[Dia%d ]",i+1);
	}

        /* Impresion de valores */
	if(n%2 != 0) {
		for(i=0; i<dias; i++) {
			if(i+1 < 10)
				resultado = resultado +String.format("\n[  J%d ]",i+1)+ "   ";
			else
				resultado = resultado +String.format("\n[ J%d ]",i+1) + "   " ;

			for(j=0; j<dias; j++) {
				if(encuentros[i][j] == 0)
					resultado = resultado +String.format("[  -   ]" + "   ");
				else if(encuentros[i][j] < 10)
					resultado = resultado + String.format("[  %d   ]",encuentros[i][j])+ "   ";
				else
					resultado = resultado + String.format("[  %d  ]",encuentros[i][j])+ "  ";
			}
		}
	}
	else {
		for(i=0; i<dias+1; i++) {
			if(i+1 < 10)
				resultado = resultado + String.format("\n[  J%d ]",i+1) + "   ";
			else
				resultado = resultado + String.format("\n[ J%d ]",i+1) + "   ";
			for(j=0; j<dias; j++) {
				if(encuentros[i][j] == 0)
					resultado = resultado + String.format("[  -   ]" + "   ");
				else if(encuentros[i][j] < 10)
					resultado = resultado + String.format("[  %d   ]",encuentros[i][j])+ "   ";
				else
					resultado = resultado + String.format("[  %d  ]",encuentros[i][j])+ "   ";
			}
		}
	}
	return resultado;
}

 
}
