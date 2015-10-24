package mobilex.demo.com.chinedutest.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by chinedu on 10/23/15.
 *
 * Helper class to return bitmap object after decoding and image uri to prevent out-of-memory problems
 *
 */
public class Utility {

    public static Bitmap getBitmap(Context context, Uri uri) throws FileNotFoundException {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        //BitmapFactory.decodeFile(uri.getPath(), options);
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 150, 150);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
    }

    /**
     * Method to sub-sample bitmaps that may be potentially too large to fit the allotted memory for bitmaps to avoid out of memory exceptions
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * Return the byte array of a bitmap object
     * @param bitmap
     * @return
     */
    public static byte [] getByteArray (Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
