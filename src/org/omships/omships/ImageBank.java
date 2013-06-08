package org.omships.omships;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Methods for providing/caching images.
 * @author jimtahu
 *
 */
public class ImageBank {
	static Map<String,Bitmap> memo= new HashMap<String,Bitmap>();
	
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
	
	static Bitmap getImage(String url){
		if(memo.containsKey(url))
			return memo.get(url);
		try {
			Bitmap image = BitmapFactory.decodeStream(
					new BufferedInputStream(new URL(url).openStream()));
			memo.put(url,image);
			return image;
		} catch (MalformedURLException ex) {
			Log.e("IMAGE", ex.toString(), ex);
		} catch (IOException ex) {
			Log.e("IMAGE", ex.toString(), ex);
		}
		return null;	
	}
}//end ImageBank
