package Services;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by yudzh_000 on 13.03.2016.
 */
public class ImageService {

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
    public static String saveToFileFromUrl(Context context) {
        File dir = context.getFilesDir();
        dir.getAbsolutePath();
        return "";
    }

}
