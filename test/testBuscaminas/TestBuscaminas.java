package testBuscaminas;

import java.util.Arrays;
import java.lang.NullPointerException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Buscaminas;
import model.Casilla;

class TestBuscaminas {

private Buscaminas buscaminas;
	
	//Scenes
	private void setUpSceneLevel4(){
		buscaminas=new Buscaminas(4);
	}

	private void setUpScene3x3board(){
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE, 1);
	}

	private void setUpSceneInicializarPartidaPrincipiantes(){
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE);
	}
	
	private void setUpSceneInicializarPartidaIntermedio(){
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO);
	}
	
	private void setUpSceneInicializarPartidaExperto(){
		buscaminas=new Buscaminas(Buscaminas.EXPERTO);
	}
	
	private void setUpSceneInicializarCasillasLibresPrincipiantesEmpty(){
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE, 2);
		buscaminas.inicializarCasillasLibres();
	}
	
	private void setUpSceneInicializarCasillasLibresIntermedioEmpty(){
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO, 2);
		buscaminas.inicializarCasillasLibres();
	}
	
	private void setUpSceneInicializarCasillasLibresExpertoEmpty(){
		buscaminas=new Buscaminas(Buscaminas.EXPERTO, 2);
		buscaminas.inicializarCasillasLibres();
	}
	
	private void setUpSceneGenerarMinasPrincipiantes(){
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE, 2);
		buscaminas.generarMinas();
	}
	
	private void setUpSceneGenerarMinasIntermedio(){
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO, 2);
		buscaminas.generarMinas();
	}
	
	private void setUpSceneGenerarMinasExperto(){
		buscaminas=new Buscaminas(Buscaminas.EXPERTO, 2);
		buscaminas.generarMinas();
	}
	
	private void setUpScenePrincipiantesSolved(){
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE);
		buscaminas.resolver();
	}
	
	private void setUpSceneIntermedioSolved(){
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO);
		buscaminas.resolver();
	}
	
	private void setUpSceneExpertoSolved(){
		buscaminas=new Buscaminas(Buscaminas.EXPERTO);
		buscaminas.resolver();
	}
	
	private void setUpScenePrincipiantesLose(){
		
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE);
		boolean run=true;
		for(int x=0; (x<buscaminas.darCasillas().length) && run; x++){
			for(int y=0; (y<buscaminas.darCasillas()[0].length) && run; y++){
				if(buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
					run=false;
				}
			}
		}
		
	}
	
	private void setUpSceneIntermedioLose(){
		
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO);
		boolean run=true;
		for(int x=0; (x<buscaminas.darCasillas().length) && run; x++){
			for(int y=0; (y<buscaminas.darCasillas()[0].length) && run; y++){
				if(buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
					run=false;
				}
			}
		}
		
	}

	private void setUpSceneExpertoLose(){
		
		buscaminas=new Buscaminas(Buscaminas.EXPERTO);
		boolean run=true;
		for(int x=0; (x<buscaminas.darCasillas().length) && run; x++){
			for(int y=0; (y<buscaminas.darCasillas()[0].length) && run; y++){
				if(buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
					run=false;
				}
			}
		}
		
	}
	
	private void setUpScenePrincipiantesWin(){
		
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE);
		for(int x=0; x<buscaminas.darCasillas().length; x++){
			for(int y=0; y<buscaminas.darCasillas()[0].length; y++){
				if(!buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
				}
			}
		}
		
	}
	
	private void setUpSceneIntermedioWin(){
		
		buscaminas=new Buscaminas(Buscaminas.INTERMEDIO);
		for(int x=0; x<buscaminas.darCasillas().length; x++){
			for(int y=0; y<buscaminas.darCasillas()[0].length; y++){
				if(!buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
				}
			}
		}
		
	}
	
	private void setUpSceneExpertoWin(){
		
		buscaminas=new Buscaminas(Buscaminas.EXPERTO);
		for(int x=0; x<buscaminas.darCasillas().length; x++){
			for(int y=0; y<buscaminas.darCasillas()[0].length; y++){
				if(!buscaminas.darCasillas()[x][y].esMina()){
					buscaminas.abrirCasilla(x, y);
				}
			}
		}
		
	}
	
	private void setUpScene3x3Clue(){
		
		buscaminas=new Buscaminas(Buscaminas.PRINCIPIANTE, 3);
		buscaminas.darPista();
		
	}
	
	//Tests
	@Test
	void testGenerarMinas() {
		
		//Random
		setUpSceneGenerarMinasPrincipiantes();
		Casilla[][] boardP1=buscaminas.darCasillas();
		setUpSceneGenerarMinasPrincipiantes();
		Casilla[][] boardP2=buscaminas.darCasillas();
		assertFalse(Arrays.equals(boardP1, boardP2));
		
		setUpSceneGenerarMinasIntermedio();
		Casilla[][] boardI1=buscaminas.darCasillas();
		setUpSceneGenerarMinasIntermedio();
		Casilla[][] boardI2=buscaminas.darCasillas();
		assertFalse(Arrays.equals(boardI1, boardI2));
		
		setUpSceneGenerarMinasExperto();
		Casilla[][] boardE1=buscaminas.darCasillas();
		setUpSceneGenerarMinasExperto();
		Casilla[][] boardE2=buscaminas.darCasillas();
		assertFalse(Arrays.equals(boardE1, boardE2));
		
		//Quantity
		setUpSceneGenerarMinasPrincipiantes();
		Casilla[][] boardP=buscaminas.darCasillas();
		int counterP=0;
		for(int x=0; x<boardP.length; x++){
			for(int y=0; y<boardP[0].length; y++){
				try{
					if(boardP[x][y].esMina()){
						counterP+=1;
					}
				}
				catch(NullPointerException e){
					counterP+=0;
				}
			}
		}
		assertEquals(counterP, Buscaminas.CANTIDAD_MINAS_PRINCIPANTE);
		
		setUpSceneGenerarMinasIntermedio();
		Casilla[][] boardI=buscaminas.darCasillas();
		int counterI=0;
		for(int x=0; x<boardI.length; x++){
			for(int y=0; y<boardI[0].length; y++){
				try{
					if(boardI[x][y].esMina()){
						counterI+=1;
					}
				}
				catch(NullPointerException e){
					counterI+=0;
				}
			}
		}
		assertEquals(counterI, Buscaminas.CANTIDAD_MINAS_INTERMEDIO);
		
		setUpSceneGenerarMinasExperto();
		Casilla[][] boardE=buscaminas.darCasillas();
		int counterE=0;
		for(int x=0; x<boardE.length; x++){
			for(int y=0; y<boardE[0].length; y++){
				try{
					if(boardE[x][y].esMina()){
						counterE+=1;
					}
				}
				catch(NullPointerException e){
					counterE+=0;
				}
			}
		}
		assertEquals(counterE, Buscaminas.CANTIDAD_MINAS_EXPERTO);
		
	}
	
	
	@Test
	void testIncializarPartida() {
		
		setUpSceneInicializarPartidaPrincipiantes();
		Casilla[][] boardP=buscaminas.darCasillas();
		assertEquals(boardP.length, Buscaminas.FILAS_PRINCIPIANTE);
		assertEquals(boardP[0].length, Buscaminas.COLUMNAS_PRINCIPIANTE);
		assertEquals(buscaminas.getCantidadMinas(), Buscaminas.CANTIDAD_MINAS_PRINCIPANTE);
		
		setUpSceneInicializarPartidaIntermedio();
		Casilla[][] boardI=buscaminas.darCasillas();
		assertEquals(boardI.length, Buscaminas.FILAS_INTERMEDIO);
		assertEquals(boardI[0].length, Buscaminas.COLUMNAS_INTERMEDIO);
		assertEquals(buscaminas.getCantidadMinas(), Buscaminas.CANTIDAD_MINAS_INTERMEDIO);
		
		setUpSceneInicializarPartidaExperto();
		Casilla[][] boardE=buscaminas.darCasillas();
		assertEquals(boardE.length, Buscaminas.FILAS_EXPERTO);
		assertEquals(boardE[0].length, Buscaminas.COLUMNAS_EXPERTO);
		assertEquals(buscaminas.getCantidadMinas(), Buscaminas.CANTIDAD_MINAS_EXPERTO);
		
		setUpSceneLevel4();
		Casilla[][] board4=buscaminas.darCasillas();
		assertEquals(board4.length, Buscaminas.FILAS_PRINCIPIANTE);
		assertEquals(board4[0].length, Buscaminas.COLUMNAS_PRINCIPIANTE);
		assertEquals(buscaminas.getCantidadMinas(), Buscaminas.CANTIDAD_MINAS_PRINCIPANTE);
		
	}
	
	@Test
	void testInicializarCasillasLibres(){
		
		//Not nulls
		setUpSceneInicializarCasillasLibresPrincipiantesEmpty();
		Casilla[][] boardP=buscaminas.darCasillas();
		for(int x=0; x<boardP.length; x++){
			for(int y=0; y<boardP[0].length; y++){
				assertNotNull(boardP[x][y]);
			}
		}
		
		setUpSceneInicializarCasillasLibresIntermedioEmpty();
		Casilla[][] boardI=buscaminas.darCasillas();
		for(int x=0; x<boardI.length; x++){
			for(int y=0; y<boardI[0].length; y++){
				assertNotNull(boardI[x][y]);
			}
		}
		
		setUpSceneInicializarCasillasLibresExpertoEmpty();
		Casilla[][] boardE=buscaminas.darCasillas();
		for(int x=0; x<boardE.length; x++){
			for(int y=0; y<boardE[0].length; y++){
				assertNotNull(boardE[x][y]);
			}
		}
		
		//Quantity
		setUpSceneInicializarPartidaPrincipiantes();
		boardP=buscaminas.darCasillas();
		int counterP=0;
		for(int x=0; x<boardP.length; x++){
			for(int y=0; y<boardP[0].length; y++){
				if(!boardP[x][y].esMina()){
					counterP+=1;
				}
			}
		}
		assertEquals(counterP, ((Buscaminas.COLUMNAS_PRINCIPIANTE*Buscaminas.FILAS_PRINCIPIANTE)-Buscaminas.CANTIDAD_MINAS_PRINCIPANTE));
		
		setUpSceneInicializarPartidaIntermedio();
		boardI=buscaminas.darCasillas();
		int counterI=0;
		for(int x=0; x<boardI.length; x++){
			for(int y=0; y<boardI[0].length; y++){
				if(!boardI[x][y].esMina()){
					counterI+=1;
				}
			}
		}
		assertEquals(counterI, ((Buscaminas.COLUMNAS_INTERMEDIO*Buscaminas.FILAS_INTERMEDIO)-Buscaminas.CANTIDAD_MINAS_INTERMEDIO));
		
		setUpSceneInicializarPartidaExperto();
		boardE=buscaminas.darCasillas();
		int counterE=0;
		for(int x=0; x<boardE.length; x++){
			for(int y=0; y<boardE[0].length; y++){
				if(!boardE[x][y].esMina()){
					counterE+=1;
				}
			}
		}
		assertEquals(counterE, ((Buscaminas.COLUMNAS_EXPERTO*Buscaminas.FILAS_EXPERTO)-Buscaminas.CANTIDAD_MINAS_EXPERTO));
		
	}
	
	@Test
	void testCantidadMinasAlrededor(){
		
		setUpScene3x3board();
		assertEquals(buscaminas.cantidadMinasAlrededor(1, 1), 4);
		
	}
	
	@Test
	void testMostrarTablero(){
		
		setUpSceneInicializarPartidaPrincipiantes();
		String boardP1=buscaminas.mostrarTablero();
		buscaminas.abrirCasilla(1,1);
		String boardP2=buscaminas.mostrarTablero();
		assertNotEquals(boardP1, boardP2);
		assertNotNull(boardP1);
		
		setUpSceneInicializarPartidaIntermedio();
		String boardI1=buscaminas.mostrarTablero();
		buscaminas.abrirCasilla(1,1);
		String boardI2=buscaminas.mostrarTablero();
		assertNotEquals(boardI1, boardI2);
		assertNotNull(boardI1);
		
		setUpSceneInicializarPartidaExperto();
		String boardE1=buscaminas.mostrarTablero();
		buscaminas.abrirCasilla(1,1);
		String boardE2=buscaminas.mostrarTablero();
		assertNotEquals(boardE1, boardE2);
		assertNotNull(boardE1);
		
	}
	
	@Test
	void testResolver(){
		
		setUpScenePrincipiantesSolved();
		Casilla[][] boardP=buscaminas.darCasillas();
		for(int x=0; x<boardP.length; x++){
			for(int y=0; y<boardP[0].length; y++){
				assertTrue(boardP[x][y].darSeleccionada());
			}
		}
		
		setUpSceneIntermedioSolved();
		Casilla[][] boardI=buscaminas.darCasillas();
		for(int x=0; x<boardI.length; x++){
			for(int y=0; y<boardI[0].length; y++){
				assertTrue(boardI[x][y].darSeleccionada());
			}
		}
		
		setUpSceneExpertoSolved();
		Casilla[][] boardE=buscaminas.darCasillas();
		for(int x=0; x<boardE.length; x++){
			for(int y=0; y<boardE[0].length; y++){
				assertTrue(boardE[x][y].darSeleccionada());
			}
		}
		
	}
	
	@Test
	void testDarCasillas(){
		
		setUpSceneInicializarPartidaPrincipiantes();
		assertNotNull(buscaminas.darCasillas());
		
		setUpSceneInicializarPartidaIntermedio();
		assertNotNull(buscaminas.darCasillas());
		
		setUpSceneInicializarPartidaExperto();
		assertNotNull(buscaminas.darCasillas());
		
	}
	
	@Test
	void testAbrirCasilla(){
		
		setUpSceneInicializarPartidaPrincipiantes();
		assertTrue(buscaminas.abrirCasilla(1, 1));
		assertFalse(buscaminas.abrirCasilla(1, 1));
		
		setUpSceneInicializarPartidaIntermedio();
		assertTrue(buscaminas.abrirCasilla(1, 1));
		assertFalse(buscaminas.abrirCasilla(1, 1));
		
		setUpSceneInicializarPartidaExperto();
		assertTrue(buscaminas.abrirCasilla(1, 1));
		assertFalse(buscaminas.abrirCasilla(1, 1));
		
		//Lose
		setUpScenePrincipiantesLose();
		assertTrue(buscaminas.darPerdio());
		
		setUpSceneIntermedioLose();
		assertTrue(buscaminas.darPerdio());
		
		setUpSceneExpertoLose();
		assertTrue(buscaminas.darPerdio());
		
		setUpSceneInicializarPartidaPrincipiantes();
		assertFalse(buscaminas.abrirCasilla(9, 9));
		
	}
	
	@Test
	void testGano(){
		
		//Win
		setUpScenePrincipiantesWin();
		assertTrue(buscaminas.gano());
		
		setUpSceneIntermedioWin();
		assertTrue(buscaminas.gano());
		
		setUpSceneExpertoWin();
		assertTrue(buscaminas.gano());
		
		//Not
		setUpSceneInicializarPartidaPrincipiantes();
		assertFalse(buscaminas.gano());
		
		setUpSceneInicializarPartidaIntermedio();
		assertFalse(buscaminas.gano());
		
		setUpSceneInicializarPartidaExperto();
		assertFalse(buscaminas.gano());
		
	}
	
	@Test
	void testDarPista(){
		
		setUpScene3x3Clue();
		assertTrue(buscaminas.darCasillas()[0][0].darSeleccionada());
		buscaminas.darPista();
		assertTrue(buscaminas.darCasillas()[0][2].darSeleccionada());
		buscaminas.darPista();
		assertTrue(buscaminas.darCasillas()[1][1].darSeleccionada());
		buscaminas.darPista();
		assertTrue(buscaminas.darCasillas()[2][0].darSeleccionada());
		buscaminas.darPista();
		assertTrue(buscaminas.darCasillas()[2][2].darSeleccionada());
		assertEquals(buscaminas.darPista(), "No hay pistas para dar");
		
	}
	
	@Test
	void testDarPerdio(){
		
		setUpSceneInicializarPartidaPrincipiantes();
		assertNotNull(buscaminas.darPerdio());
		
		setUpSceneInicializarPartidaIntermedio();
		assertNotNull(buscaminas.darPerdio());
		
		setUpSceneInicializarPartidaExperto();
		assertNotNull(buscaminas.darPerdio());
		
	}
	
	@Test
	void testDarCantidadMinas(){
		
		setUpSceneInicializarPartidaPrincipiantes();
		assertNotNull(buscaminas.getCantidadMinas());
		
		setUpSceneInicializarPartidaIntermedio();
		assertNotNull(buscaminas.getCantidadMinas());
		
		setUpSceneInicializarPartidaExperto();
		assertNotNull(buscaminas.getCantidadMinas());
		
	}

}
