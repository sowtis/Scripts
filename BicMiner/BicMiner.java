package scripts.BicMiner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSModel;
import org.tribot.api2007.types.RSObject;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.BicMiner.Node;
import scripts.BicMiner.Actions.*;
import scripts.BicMiner.Utils.Constants;
import scripts.BicMiner.Utils.Utils;
import scripts.BicMiner.Utils.Variables;


@ScriptManifest(authors = { "Bic" }, category = "Mining", name = "Bic Miner", version = 1.00, description = "Mines ore in the motherlode mine.", gameMode = 1)
public class BicMiner extends Script implements Painting, MessageListening07 {

	public static ABCUtil abc = new ABCUtil();
	private final List<Node> nodes = new ArrayList<>();
	
	private static final long startTime = System.currentTimeMillis();
    public static long mineTimer;
    Font font = new Font("Verdana", Font.BOLD, 12);
	 
	@Override
	public void run() {
		Collections.addAll(nodes, new Bank(), new CleanOre(), new FixStrut(), new Mine(), new Navigate());
		
		General.println("Bic's Miner has started!");

		//while(true){General.sleep(75,125);}
		
		loop(20, 40);
	}

	private void loop(int min, int max) {
		while (true) {
			for (final Node node : nodes) {
				if (node.validate()) {
					node.execute();
					sleep(General.random(min, max));	//time in between executing nodes
				}
			}
		}
	}
	

	@Override
	public void serverMessageReceived(String arg0) {
		// TODO Auto-generate method sub
		if (arg0.contains("Some ore is ready"))
			 Variables.lastMessage = "ready";
		
		
	}
	
	@Override
	public void onPaint(Graphics g) {
		long timeRan = System.currentTimeMillis() - startTime;
		
		Point pModel = Player.getRSPlayer().getModel().getCentrePoint();
		RSObject[] struts = Objects.findNearest(15, Constants.STRUT_ID);
		
		g.setFont(font);
	    g.setColor(Color.WHITE);
	    g.drawString("Bic's Miner", 279, 360);
	    g.drawString("Run time: " + Timing.msToString(timeRan), 279, 375);
	    g.drawString("Status: " + Variables.status, 279, 390);
	    
	    if (Utils.isMining())
	    	g.drawString("Mining", (int)pModel.getX(), (int)pModel.getY());
	    
	    
	    for (int i = 0; i < struts.length; i++){
	    	g.drawString("BROKEN", (int)struts[i].getModel().getCentrePoint().getX(), (int)struts[i].getModel().getCentrePoint().getY());
	    }
	       

	}


	@Override
	public void duelRequestReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clanMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}