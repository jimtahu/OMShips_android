package org.omships.omships;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

class FetchImage extends AsyncTask<String, Void, Bitmap>{
	static Map<String,Bitmap> memo= new HashMap<String,Bitmap>();
	ImageView photo;
	
	public static void invalidate(String url){
		if(memo.containsKey(url))
			memo.remove(url);
	}//end invalidate
	
	public FetchImage(ImageView photo) {
		this.photo=photo;
	}
	
	@Override
	protected Bitmap doInBackground(String...urls) {
		if(memo.containsKey(urls[0]))
			return memo.get(urls[0]);
		try {
			Bitmap image = BitmapFactory.decodeStream(new URL(urls[0]).openStream());
			memo.put(urls[0],image);
			return image;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}//end doInBackground
	
	protected void onPostExecute(Bitmap result){
		if(result != null && this.photo != null)
			this.photo.setImageBitmap(result);
	}
}