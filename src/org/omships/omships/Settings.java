package org.omships.omships;

import java.util.List;

import android.content.res.Resources;

public class Settings {
	private static int selected=0;
	private static List<Ship> ships=null;
	
	public static void loadConfig(Resources r){
		SettingsReader reader = new SettingsReader(r.openRawResource(R.raw.config));
		try {
			ships = reader.getItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end loadConfig
	
	public static List<Ship> getShips(){
		return ships;
	}
	
	public static Ship getShip(){
		return ships.get(selected);
	}
}//end Settings
