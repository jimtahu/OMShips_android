package org.omships.omships;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
 * This class Async fetches an image and posts it to a Bitmap.
 * Once fetched, images are cached in a static memo based on url
 *  and not redownloaded unless they are invalidated.
 * This means that images will be fetched only once per application run.
 */
class FetchImage extends AsyncTask<String, Void, Bitmap>{
	static Map<String,Bitmap> memo= new HashMap<String,Bitmap>();
	ImageView photo;
	
	/**
	 * Removes an image from the cache.
	 * @param url
	 * This removes an image from the local cache.
	 * This should not have any negative side effects on existing
	 *  usages of the image, but forces the image to be redownloaded
	 *  the next time it is called for.
	 */
	public static void invalidate(String url){
		if(memo.containsKey(url))
			memo.remove(url);
	}//end invalidate
	
	/**
	 * Creates a new image fetcher for the given Image View.
	 * @param photo
	 */
	public FetchImage(ImageView photo) {
		this.photo=photo;
	}
	
	/**
	 * Fetches the image given as a url string.
	 */
	@Override
	protected Bitmap doInBackground(String...urls) {
		if(memo.containsKey(urls[0]))
			return memo.get(urls[0]);
		try {
			Bitmap image = BitmapFactory.decodeStream(
					new BufferedInputStream(new URL(urls[0]).openStream()));
			memo.put(urls[0],image);
			return image;
		} catch (MalformedURLException e) {
			Log.e("IMAGE", e.toString());
		} catch (IOException e) {
			Log.e("IMAGE", e.toString());
		}
		return null;
	}//end doInBackground
	
	protected void onPostExecute(Bitmap result){
		if(result != null && this.photo != null)
			this.photo.setImageBitmap(result);
	}
}