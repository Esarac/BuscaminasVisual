/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solución laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package model;
import java.lang.Math;

public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel){
		
		
		if(nivel<1 || nivel>3){
			this.nivel=PRINCIPIANTE;
			try {throw new ImpossibleLevelException("Ese nivel no existe. Se ha colocado en principiantes.");}
			catch(ImpossibleLevelException e) {e.printStackTrace();}
		}
		else{
			this.nivel = nivel;
		}
		perdio = false;
		inicializarPartida();
		generarMinas();
		inicializarCasillasLibres();
		for(int x=0; x<casillas.length; x++){
			for(int y=0; y<casillas[0].length;y++){
				casillas[x][y].modificarValor(cantidadMinasAlrededor(x, y));
			}
		}
		
	}
	
	public Buscaminas(int nivel, int option){
		
		switch (option){
			
			case 1:
				
				this.nivel = nivel;
				perdio = false;
				casillas=new Casilla[3][3];
				casillas[0][1]=new Casilla(Casilla.MINA);
				casillas[1][0]=new Casilla(Casilla.MINA);
				casillas[1][2]=new Casilla(Casilla.MINA);
				casillas[2][1]=new Casilla(Casilla.MINA);
				inicializarCasillasLibres();
				
			break;
			
			case 2:
				
				this.nivel = nivel;
				perdio = false;
				inicializarPartida();
				
			break;
			
			case 3:
				
				this.nivel = nivel;
				perdio = false;
				casillas=new Casilla[3][3];
				casillas[0][1]=new Casilla(Casilla.MINA);
				casillas[1][0]=new Casilla(Casilla.MINA);
				casillas[1][2]=new Casilla(Casilla.MINA);
				casillas[2][1]=new Casilla(Casilla.MINA);
				inicializarCasillasLibres();
				for(int x=0; x<casillas.length; x++){
					for(int y=0; y<casillas[0].length;y++){
						
						casillas[x][y].modificarValor(cantidadMinasAlrededor(x, y));
						
					}
				}
				
			break;
			
		}
		
		
	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {
		
		switch(nivel){
			case 1:
				casillas=new Casilla[Buscaminas.FILAS_PRINCIPIANTE][Buscaminas.COLUMNAS_PRINCIPIANTE];
				cantidadMinas=Buscaminas.CANTIDAD_MINAS_PRINCIPANTE;
			break;
			
			case 2:
				casillas=new Casilla[Buscaminas.FILAS_INTERMEDIO][Buscaminas.COLUMNAS_INTERMEDIO];
				cantidadMinas=Buscaminas.CANTIDAD_MINAS_INTERMEDIO;
			break;
				
			case 3:
				casillas=new Casilla[Buscaminas.FILAS_EXPERTO][Buscaminas.COLUMNAS_EXPERTO];
				cantidadMinas=Buscaminas.CANTIDAD_MINAS_EXPERTO;
			break;
		}

	}


	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {

		for(int x=0; x<casillas.length; x++){
			for(int y=0; y<casillas[0].length; y++){
				
				if(casillas[x][y]==null){
					
					casillas[x][y]=new Casilla(Casilla.LIBRE);
					
				}
				
			}
		}

	}


	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j) {

		int quantity=0;

		//Up
		quantity+=countBorderMine(i-1, j-1);
		quantity+=countBorderMine(i-1, j);
		quantity+=countBorderMine(i-1, j+1);
		//Middle
		quantity+=countBorderMine(i, j-1);
		quantity+=countBorderMine(i, j+1);
		//Down
		quantity+=countBorderMine(i+1, j-1);
		quantity+=countBorderMine(i+1, j);
		quantity+=countBorderMine(i+1, j+1);
		
		return quantity;
	}

	private int countBorderMine(int i, int j){
		
		int found=0;
		
		try{
			if(casillas[i][j].esMina()){
				found=1;
			}
		}
		catch(IndexOutOfBoundsException e){
			found=0;
		}
		
		return found;
		
	}
	
	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {

		for(int i=0; i<cantidadMinas; i++){
			
			boolean verifier=true;
			
			while(verifier){
				
				int x=(int)(Math.random()*casillas.length);
				int y=(int)(Math.random()*casillas[0].length);
				
				if(casillas[x][y]==null){
					
					casillas[x][y]=new Casilla(Casilla.MINA);
					verifier=false;
					
				}
				
			}
			
		}
		
	}


	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {

		String board="  ";
		for(int y=0; y<casillas[0].length; y++){
			if(y<10){board+="  "+(y+1);}
			else{board+=" "+(y+1);}
			
		}
		board+="\n";
		
		for(int x=0; x<casillas.length;x++){
			if(x<9){board+=(x+1)+" ";}
			else{board+=(x+1)+"";}
			for(int y=0; y<casillas[0].length; y++){
				board+="  "+casillas[x][y].mostrarValorCasilla();
			}
			board+="\n";
		}
		
		return board;
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {

		for(int x=0; x<casillas.length; x++){
			for(int y=0; y<casillas[0].length; y++){
				casillas[x][y].modificarMarcada(false);
				casillas[x][y].destapar();
			}
		}

	}

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {

		boolean possible=false;
		
		if((i>=0 && i<casillas.length) && (j>=0 && j<casillas[0].length)){
			
			if(casillas[i][j].darSeleccionada() || casillas[i][j].darMarcada()){
				possible=false;
			}
			else{
				
				casillas[i][j].destapar();
				possible=true;
				
				if(casillas[i][j].esMina()){
					perdio=true;
				}
				
			}
			
		}
		else{
			try {throw new ImpossibleCoordinateException("La casilla seleccionada esta fuera de rango. No se puede abrir.");}
			catch (ImpossibleCoordinateException e){e.printStackTrace();}
		}
		
		return possible;
	}

	public boolean marcar(int i,int j){
		boolean possible=casillas[i][j].modificarMarcada(!casillas[i][j].darMarcada());
		return possible;
	}

	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {

		boolean win=true;
		
		for(int x=0; (x<casillas.length) && win; x++){
			for(int y=0; (y<casillas[0].length) && win; y++){
				
				if(!casillas[x][y].esMina() && !casillas[x][y].darSeleccionada()){
					win=false;
				}
				
			}
		}
		
		return win;
	}


	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 */
	public String darPista() {

		String message="Casilla Abierta: ";
		boolean run=true;
		
		for(int x=0; (x<casillas.length) && run; x++){
			for(int y=0; (y<casillas[0].length) && run; y++){
				
				if(!casillas[x][y].esMina() && !casillas[x][y].darSeleccionada() && (casillas[x][y].darValor()>0)){
					casillas[x][y].modificarMarcada(false);
					casillas[x][y].destapar();
					message+="("+x+","+y+")";
					run=false;
				}
				
			}
		}
		
		if(run){
			
			message="No hay pistas para dar";
			
		}
		
		return message;
	}
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}
	
	public int getCantidadMinas(){
		return cantidadMinas;
	}
	
}