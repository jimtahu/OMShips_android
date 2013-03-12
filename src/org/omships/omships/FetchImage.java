package org.omships.omships;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

class FetchImage extends AsyncTask<String, Void, Bitmap>{
	ImageView photo;
	
	public FetchImage(ImageView photo) {
		this.photo=photo;
	}
	
	@Override
	protected Bitmap doInBackground(String...urls) {
		try {
			Bitmap image = BitmapFactory.decodeStream(new URL(urls[0]).openStream());
			return image;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}//end doInBackground
	
	protected void onPostExecute(Bitmap result){
		this.photo.setImageBitmap(result);
	}
}