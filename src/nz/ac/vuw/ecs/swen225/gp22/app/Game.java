package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.MazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Main class for running game
 * @author CarloC
 *
 */
public class Game extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runnable stop = ()->{};
	private MazeView mv;
	private JPanel container;
	private Controller controller;
	private Maze maze;

	private Timer timer;
	private long startTime = -1;
	private long duration = 100000;

	/**
	 * Constructor for a new blank level 1
	 */
	public Game() {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		XMLLoader loader = new XMLLoader();
		loader.loadFile(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/1.xml"));
		mv = new MazeView(loader.getMaze());
		controller = new Controller(loader.getMaze());
		mv.addKeyListener(controller);
		gui();
		setVisible(true);
	}
	
	/**
	 * Constructor for loading a particular level - Not working atm?
	 * @param file specified level to be loaded
	 */
	
	public Game(File file) {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		XMLLoader loader = new XMLLoader();
		loader.loadFile(file);
		mv = new MazeView(loader.getMaze());
		gui();
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creates window for the main GUI
	 */
	public void gui() {
			
		JMenuBar tools = new JMenuBar();
		
		container = new JPanel();
		JPanel camera = new JPanel();
		JPanel info = new JPanel();
		
		info.setLayout(new GridLayout(10, 1));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		setResizable(false);
		setSize(new Dimension(650, 450));
		setMinimumSize(new Dimension(650, 450));		
		
		stop  = ()->{
			this.dispose();
			new Setup();
		};
		
		JMenuItem stopbutton = new JMenuItem("stop");
		stopbutton.addActionListener(e->stop.run());

		JLabel cl = new JLabel("TIME");
		JLabel cd = new JLabel("bruh");

		JLabel pl = new JLabel("Patties Left");
		JLabel pc = new JLabel("100");		
		
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(startTime < 0) {
					startTime = System.currentTimeMillis();
				}
				long now = System.currentTimeMillis();
				long clockTime = now - startTime;
				if (clockTime >= duration) {
					clockTime = duration;
					timer.stop();
				}
				cd.setText(" " + (int)((duration-clockTime)/1000));
				
				mv.repaint();
				
			}
			
		});
		
		if(!timer.isRunning()) {
			timer.start();
		}
		
		tools.add(stopbutton);
		add(tools);
		
		info.add(cl);
		info.add(cd);
		info.add(pl);
		info.add(pc);

		getContentPane().add(tools, BorderLayout.PAGE_START);
		camera.add(mv);	
		container.add(camera);
		container.add(info);
		add(container);
		this.setPreferredSize(new Dimension(300,300));
		
		pack();
      	mv.requestFocus();
		
	}
	
	
	
	
	
}

