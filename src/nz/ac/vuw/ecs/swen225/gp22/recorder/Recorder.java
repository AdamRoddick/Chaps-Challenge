package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze.direction;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;

import java.io.*;
import org.jdom2.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.*;

import java.util.List;
import java.util.Stack;

public class Recorder {
	static boolean auto = false;
	static int playbackSpeed = 10;
	
	//save the current game
	/*public static void SaveGame(Maze maze, String newFileName) {
		if(maze == null) {
			System.out.println("Nothing to be saved");
			return;
		}
		try {
			//create  root node
			Element root = new Element("save");
			root.setAttribute("level", maze.getLevel()+"");
			
			//crate each character element and add to root node
			Element character =  new Element("character");
			character.setAttribute("name", "chap");
			String str = "";
			maze.getChap().getPrevMoves().stream().forEach(m -> str += m + ", ");
			character.addContent(str);
			root.addContent(character);
			
			//Save as doc
			Document doc = new Document();
		    doc.setRootElement(root);
		    XMLOutputter xmlOutputter = new XMLOutputter();
		    xmlOutputter.setFormat(Format.getPrettyFormat());
		    xmlOutputter.output(doc, new FileOutputStream(new File(newFileName)));
		}catch(Exception e) {e.printStackTrace();}
	}
	*/
	public static Maze LoadSave(File file) throws JDOMException, IOException{
		// load XML file document
		SAXBuilder sax = new SAXBuilder();
		Document doc = sax.build(file);
		
		//create level
		Element root = doc.getRootElement();
		String level = root.getAttributeValue("level");
		XMLLoader levelLoader = new XMLLoader();
		levelLoader.loadFile(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + level + ".xml"));
		
		//update characters future movement
		Maze maze = levelLoader.getMaze();
		Element element = root.getChild("character");
		
		//find the right character
		String[] moves = element.getChild("moves").getText().split(", ");
		
		ChapTile chap = maze.getChap();
		//push moves on to characters next moves stack
		for(String str: moves) {
			switch(str) {
				case "up":
					chap.addNextMove(direction.UP);
					break;
				case "left":
					chap.addNextMove(direction.LEFT);
					break;
				case "down":
					chap.addNextMove(direction.DOWN);
					break;
				case "right":
					chap.addNextMove(direction.RIGHT);
					break;
				case "null":
					chap.addNextMove(direction.NULL);
					break;
			}
		}
		return maze;
	}
	
	//play next move 
	public void Next(Maze m) {
		auto = false;
		//m.getCharacters().stream().forEach(i->i.nextMove());
	}
	
	//auto play through all moves
	public void AutoPlay(Maze m) throws InterruptedException {
		auto = true;
		/*while(!m.getCharacters().get(0).getNextMoves().isEmpty() && auto) {
			m.getCharacters().stream().forEach(i->i.nextMove());
			wait(2000/playbackSpeed);
		}*/
	}
}
