package android.howard.exp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore.Video.Thumbnails;
import android.widget.ImageView;

public class ViedioReaderTask extends AsyncTask<String, String, Bitmap> {
	private ImageCache cache;
	private ImageView mImageView;
	private int x, y;

	/**
	 * @param mContext
	 */
	public ViedioReaderTask(Context mContext, ImageView mImageView, int x, int y) {
		super();
		this.mImageView = mImageView;
		this.x = x;
		this.y = y;
		cache = ImageCache.getInstance(mContext);
	}

	@Override
	protected Bitmap doInBackground(String... path) {
		return getImageFrmSomeWhere(path[0], path[0], this.x, this.y);
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null)
			mImageView.setImageBitmap(result);
	}

	private Bitmap getImageFrmSomeWhere(String memKey, String pathString,
			int x, int y) {
		Bitmap bp = cache.get(memKey);
		if (bp == null) {
			bp = readImageFrmSd(pathString, x, y);
			cache.add(memKey, bp);
		}
		return bp;
	}

	private Bitmap readImageFrmSd(String path, int x, int y) {
		Bitmap bp = null;
		bp = ThumbnailUtils.createVideoThumbnail(path, Thumbnails.MINI_KIND);
		return bp;
	}
}
