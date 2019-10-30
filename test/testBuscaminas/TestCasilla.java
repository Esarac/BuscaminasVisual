package testBuscaminas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Casilla;

class TestCasilla {

	private Casilla casilla;
	
	//Scenes
	private void setUpSceneModificarValor1(){
		
		casilla=new Casilla(Casilla.LIBRE);
		casilla.modificarValor(2);
		
	}
	
	private void setUpSceneModificarValor2(){
		
		casilla=new Casilla(Casilla.LIBRE);
		casilla.modificarValor(9);
		
	}
	
	private void setUpSceneCasillaLibre(){
		
		casilla=new Casilla(Casilla.LIBRE);
		
	}
	
	private void setUpSceneCasillaMina(){
		
		casilla=new Casilla(Casilla.MINA);
		
	}
	
	private void setUpSceneCasillaLibreDestapada(){
		
		casilla=new Casilla(Casilla.LIBRE);
		casilla.destapar();
		
	}
	
	private void setUpSceneCasillaMinaDestapada(){
		
		casilla=new Casilla(Casilla.MINA);
		casilla.destapar();
		
	}
	
	private void setUpSceneCasillaInvalid(){
		
		casilla=new Casilla(1234);
		
	}
	
	//Tests
	@Test
	void testModificarValor() {
		
		setUpSceneCasillaLibre();
		int value1=casilla.darValor();
		assertEquals(value1, -1);
		
		casilla.modificarValor(2);
		int value2=casilla.darValor();
		assertEquals(value2, 2);
		
	}
	
	@Test
	void testEsMina() {
		
		setUpSceneCasillaLibre();
		assertFalse(casilla.esMina());
		
		setUpSceneCasillaMina();
		assertTrue(casilla.esMina());
		
		setUpSceneCasillaInvalid();
		assertFalse(casilla.esMina());
		
	}

	@Test
	void testMostrarValorCasilla() {
		
		setUpSceneCasillaLibre();
		assertEquals(casilla.mostrarValorCasilla(), "-");
		
		setUpSceneCasillaMina();
		assertEquals(casilla.mostrarValorCasilla(), "-");
		
		setUpSceneCasillaLibreDestapada();
		assertEquals(casilla.mostrarValorCasilla(), (-1)+"");
		
		setUpSceneCasillaMinaDestapada();
		assertEquals(casilla.mostrarValorCasilla(), "*");
		
	}
	
	@Test
	void testDestapar(){
		
		setUpSceneCasillaLibre();
		assertFalse(casilla.darSeleccionada());
		
		casilla.destapar();
		assertTrue(casilla.darSeleccionada());
		
	}
	
	@Test
	void testDarSeleccionada() {
		
		setUpSceneCasillaLibre();
		assertFalse(casilla.darSeleccionada());
		
		setUpSceneCasillaMina();
		assertFalse(casilla.darSeleccionada());
		
		setUpSceneCasillaLibreDestapada();
		assertTrue(casilla.darSeleccionada());
		
		setUpSceneCasillaMinaDestapada();
		assertTrue(casilla.darSeleccionada());
		
	}
	
	@Test
	void testDarValor(){
		
		setUpSceneModificarValor1();
		assertEquals(casilla.darValor(), 2);
		
		setUpSceneModificarValor2();
		assertEquals(casilla.darValor(), 9);
		
	}
	
}
