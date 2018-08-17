package nvd.hasan.imagecrop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CanvasDraw extends AppCompatActivity {

    ImageView drawingImageView;
    Button markBtn;
    private Canvas canvas;
    private Paint mPaint;
    private float mX, mY = 0;
    private float leftx;
    private float topy;
    private float rightx;
    private float bottomy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_draw);

        drawingImageView = findViewById(R.id.drawingImageView);
        markBtn = findViewById(R.id.markBtn);

        String path = getIntent().getStringExtra("path");
        Bitmap bmp = getImagePath(path);

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap workingBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(mutableBitmap);
        drawingImageView.setImageBitmap(mutableBitmap);

        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        markBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.drawRect(leftx, topy, rightx, bottomy, mPaint);
                String text = "x1: " + String.valueOf(leftx) + ", y1: "+ String.valueOf(topy) + ", x2: " + String.valueOf(rightx)+", y2: "+String.valueOf(bottomy);
                Log.d("ordinate", text);
                Toast.makeText(CanvasDraw.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startTouch(float x, float y){
        leftx = x;
        topy = y;
    }

    public void finishTouch(float x, float y){
        rightx = x;
        bottomy = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mX = x;
                mY = y;
                break;
            case MotionEvent.ACTION_UP:
                finishTouch(x, y);
                break;
        }
        return true;
    }

    public Bitmap getImagePath(String path) {
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        return myBitmap;
    }
}
