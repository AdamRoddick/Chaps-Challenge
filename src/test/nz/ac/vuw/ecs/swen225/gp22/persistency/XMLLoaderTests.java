package test.nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.File;

import org.junit.Test;

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;

public class XMLLoaderTests {
	
	String test1 = "src/test/nz/ac/vuw/ecs/swen225/gp22/persistency/test1.xml";
	
	@Test
	public void Test_01(){
		XMLLoader loader = new XMLLoader();
		File testFile = new File(test1);
		loader.loadFile(testFile);
		Maze testMaze = loader.getMaze();
	}
}
