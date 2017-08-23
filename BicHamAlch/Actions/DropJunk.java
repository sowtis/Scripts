package scripts.BicHamAlch.Actions;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import scripts.BicHamAlch.Node;
import scripts.BicHamAlch.Utils.Constants;
import scripts.BicHamAlch.Utils.Utils;
import scripts.BicHamAlch.Utils.Variables;

public class DropJunk extends Node {

	@Override
	public void execute() {
		Variables.status = "Dropping Junk";
		
		if (dropItem()){
			
		}
	}

	@Override
	public boolean validate() {
		return (Utils.emptySpaces() < 4 || Utils.isStunned() || !Utils.isInPickpocketArea()) && Utils.hasJunk();
	}
	
	private static boolean dropItem(){
		for (RSItem item : Inventory.getAll()){
			for (int i = 0; i < Constants.JUNK.length; i++){
				if (item.getID() == Constants.JUNK[i]){
					Inventory.drop(item);
				}
			}
		}
		
		return true;
	}
}
