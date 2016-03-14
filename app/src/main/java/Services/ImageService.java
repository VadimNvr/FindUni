package Services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yudzh_000 on 13.03.2016.
 */
public class ImageService {
    static int i = 0;
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    // TODO: 14.03.2016
    public static String saveToFileFromUrl(Context context, String address) throws IOException {

        String resultPath = null;
        String subPath = null;
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            Bitmap bm = BitmapFactory.decodeStream(is);
            subPath =  System.currentTimeMillis()+".jpeg";
            resultPath = context.getFilesDir().getAbsolutePath() + subPath;
            FileOutputStream fos = context.openFileOutput(subPath, Context.MODE_PRIVATE);

            ByteArrayOutputStream outstream = new ByteArrayOutputStream();

            bm.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            byte[] byteArray = outstream.toByteArray();

            fos.write(byteArray);
            fos.close();
        } catch(Exception e) {
            Log.i("SHIITT", e.toString());
        }
        Log.i("Path",resultPath);
        File file = new File(context.getFilesDir().getAbsolutePath(), subPath);
        Log.i("Path11", file.getPath());
        Log.i("Exists", Boolean.toString(file.exists()));
        return resultPath;
    }



}
