package nvd.hasan.imagecrop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;

public class ImagePreview extends AppCompatActivity {

    private ImageView img;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        img = findViewById(R.id.imagePreview);

        String path = getIntent().getStringExtra("path");
        getImagePath(path);

//        getImageAndConvert(bmp);
    }

    public void getImagePath(String path){
//        Log.d("path", String.valueOf(path));
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        img.setImageBitmap(myBitmap);
//        int heightVal = myBitmap.getHeight();
//        int weidthVal = myBitmap.getWidth();
//        Toast.makeText(ImagePreview.this, String.valueOf(heightVal)+ ", " +String.valueOf(weidthVal), Toast.LENGTH_SHORT).show();
    }

//    public void getImageAndConvert(Bitmap b){
//        img.setImageBitmap(b);
//        int heightVal = b.getHeight();
//        int weidthVal = b.getWidth();
//        Toast.makeText(ImagePreview.this, String.valueOf(heightVal)+ ", " +String.valueOf(weidthVal), Toast.LENGTH_SHORT).show();
////        Log.d("arr", String.valueOf(b));
//    }
}
