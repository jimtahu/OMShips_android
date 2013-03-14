package org.omships.omships;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.res.Resources;
import android.os.AsyncTask;

public class Settings {
	private static int selected=0;
	private static List<Ship> ships=null;
	
	static class FetchConfig extends AsyncTask<String,Integer,Integer>{

		@Override
		protected Integer doInBackground(String... urls) {
			try {
				SettingsReader reader=new SettingsReader(new URL(urls[0]).openStream());
				ships = reader.getItems();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}//end doInBackground
		
	}//end class FetchConfig
	
	public static void loadConfig(Resources r){
		if(ships==null){
			SettingsReader reader = new SettingsReader(r.openRawResource(R.raw.config));
			try {
				ships = reader.getItems();
			} catch (Exception e) {
				e.printStackTrace();
			}//end try-catch
			//new FetchConfig().execute("http://danielrothfus.com/config.xml");
		}//end if
	}//end loadConfig
	
	public static List<Ship> getShips(){
		return ships;
	}
	
	public static Ship getShip(){
		return ships.get(selected);
	}
}//end Settings
