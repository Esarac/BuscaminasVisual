/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solución laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package model;

public class Casilla {
	
	
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
     * Es una constante utilizada para indicar que la casilla es de tipo Mina
     */
	public static final int MINA = 100;
	
	 /**
     * Es una constante utilizada para indicar que la casilla no es de tipo Mina
     */
	public static final int LIBRE = 50;

	// -----------------------------------------------------------------
    // Atributos y relaciones
    // -----------------------------------------------------------------

	/**
	 * Es el tipo de la casilla <Solo puede ser tipo MINA o LIBRE>
	 */
	private int tipo;
	
	/**
	 * Atributo que indica si la casilla ya fue seleccionada
	 */
	private boolean seleccionada;
	
	/**
	 * Atributo que indica si la casilla ya fue marcada
	 */
	private boolean marcada;
	
	/**
	 * Atributo que indica la cantidad de minas que tiene alrededor una casilla.
	 */
	private int valor;

	 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

	/**
	 * Constructor de la clase Casilla
	 * @param tipo - El tipo de la casilla
	 */
	public Casilla(int tipo) {
		if(tipo!=MINA && tipo!=LIBRE){
			try {
				this.tipo=LIBRE;
				throw new ImpossibleTypeException("El tipo de casilla no es valido. Se ha puesto a la casilla como libre.");
			}
			catch (ImpossibleTypeException e) {e.printStackTrace();}
		}
		else{this.tipo = tipo;}
		seleccionada = false;
		marcada=false;
		valor = -1;
	}
	

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------
    
	/**
	 * Metodo modificar del atributo valor
	 * @param valor - nuevo valor
	 */
	public void modificarValor(int valor){
		this.valor = valor;
	}
	
	/**
	 * Metodo modificar del atributo marcada
	 * @param marcada - nuevo marcada
	 */
	public boolean modificarMarcada(boolean marcada){
		boolean possible=true;
		if(marcada && seleccionada){
			try {
				throw new SelectedMarkedException("No se pude marcar, porque ya esta seleccionnada");
			} catch (SelectedMarkedException e) {
				possible=false;
			}
		}
		else{
			this.marcada=marcada;
		}
		return possible;
	}
	
	/**
	 * Retorna true si una casilla es de tipo Mina, false en caso contrario
	 * @return
	 */
	public boolean esMina(){
		return tipo == MINA;
	}
	
	/**
	 * Genera un String que representa el valor que se debe mostrar de la casilla
	 * @return El String con la representación actual de la casilla
	 */
	public String mostrarValorCasilla(){
		String valor = "";
		
		if(marcada){
			valor = "+";
		}
		else if(!seleccionada){
			valor = "-";
		}else if(esMina()) {
			valor = "*";
		}else {
			valor = this.valor+"";
		}
		
		return valor;
	}
	
	/**
	 * Marca la casilla como que ya fue selecciona
	 */
	public boolean destapar(){
		boolean possible=true;
		if(!marcada){
			seleccionada = true;
		}
		else{
			try {
				throw new SelectedMarkedException("No se pude seleccionar, porque esta marcada");
			}
			catch (SelectedMarkedException e) {
				possible=false;
			}
		}
		return possible;
	}
	
	/**
	 * Metodo dar del atributo seleccionda
	 * @return el atributo
	 */
	public boolean darSeleccionada(){
		return seleccionada;
	}
	
	/**
	 * Metodo dar del atributo marcada
	 * @return el atributo
	 */
	public boolean darMarcada(){
		return marcada;
	}
	
	/**
	 *  Metodo dar del atributo valor
	 * @return el valor
	 */
	public int darValor(){
		return valor;
	}
	
}
