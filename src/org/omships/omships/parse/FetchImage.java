package org.omships.omships.parse;

import org.omships.omships.FileBank;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * This class Async fetches an image and posts it to a Bitmap.
 * Once fetched, images are cached in a static memo based on url
 *  and not redownloaded unless they are invalidated.
 * This means that images will be fetched only once per application run.
 */
public class FetchImage extends AsyncTask<String, Void, Bitmap>{
	ImageView photo;
	
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
		return FileBank.getImage(urls[0]);
	}//end doInBackground
	
	protected void onPostExecute(Bitmap result){
		if(result != null && this.photo != null)
			this.photo.setImageBitmap(result);
	}
}