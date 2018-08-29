package nvd.hasan.imagecrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.paperdb.Paper;

public class CanvasDraw extends AppCompatActivity {

    ImageView drawingImageView;
    Bitmap workingBitmap;
    Button markBtn, backBtn;
    ImageCoordinates coordinates;
    private Canvas canvas;
    private Paint mPaint;
    private float mX = 0, mY = 0;
    private float leftx = 0;
    private float topy = 0;
    private float rightx = 0;
    private float bottomy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_draw);
        Paper.init(CanvasDraw.this);

        drawingImageView = findViewById(R.id.drawingImageView);
        markBtn = findViewById(R.id.markBtn);
        backBtn = findViewById(R.id.backBtn);
        markBtn.setText("Nam");

        String path = getIntent().getStringExtra("path");
        Bitmap bmp = getImagePath(path);

        final Matrix matrix = new Matrix();
        matrix.postRotate(90);

        workingBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
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
                String btnText = (String) markBtn.getText();
                if (btnText == "Nam"){
                    drawRect("Nam");
//                    markBtn.setText("Pita");
                    markBtn.setText("Done");
                }

//                else if (btnText == "Pita"){
//                    drawRect("Pita");
//                    markBtn.setText("Mata");
//                }
//
//                else if (btnText == "Mata"){
//                    drawRect("Mata");
//                    markBtn.setText("Id");
//                }
//
//                else if (btnText == "Id"){
//                    drawRect("Id");
//                    markBtn.setText("Done");
//                }

                else if (btnText == "Done"){
                    Intent in = new Intent(CanvasDraw.this, cameraShow.class);
                    startActivity(in);
                }

                else {
                    Toast.makeText(CanvasDraw.this, markBtn.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CanvasDraw.this, cameraShow.class);
                startActivity(in);
            }
        });
    }

    public void startTouch(float x, float y){
        leftx = x * (workingBitmap.getWidth()/drawingImageView.getWidth());
        topy = y * (workingBitmap.getHeight()/drawingImageView.getHeight());
    }

    public void moveTouch(float x, float y){
        mX = x ;
        mY = y;
    }

    public void finishTouch(float x, float y){
        rightx = x * (workingBitmap.getWidth()/drawingImageView.getWidth());
        bottomy = y * (workingBitmap.getHeight()/drawingImageView.getHeight());
    }

    public void drawRect(String btnName){
        float top_y = topy+150;
        float right_x = rightx+mX;
        canvas.drawRect(leftx, top_y, right_x, bottomy, mPaint);
//        String text = "x1: " + String.valueOf(leftx) + ", y1: "+ String.valueOf(topy) + ", x2: " + String.valueOf(rightx)+", y2: "+String.valueOf(bottomy);
//        Log.d("ordinate", text);
//        Toast.makeText(CanvasDraw.this, text, Toast.LENGTH_SHORT).show();
        coordinates = new ImageCoordinates(leftx, top_y, right_x, bottomy, btnName);
        Paper.book().write("coordinates", coordinates);
        showToast();
        leftx = topy = rightx = bottomy = mX = mY = 0;
    }

    public void showToast(){
        coordinates = Paper.book().read("coordinates");
        String text = "x1: " + coordinates.getLeftX() + ", y1: "+ coordinates.getTopY() + ", x2: " + coordinates.getRightX()+", y2: "+coordinates.getBottomY();
        Log.d("ordinate", text);
        Toast.makeText(CanvasDraw.this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int[] viewCoords = new int[2];
        drawingImageView.getLocationOnScreen(viewCoords);

        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y- viewCoords[1]);
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x - viewCoords[0], y- viewCoords[1]);
                break;
            case MotionEvent.ACTION_UP:
                finishTouch(x - viewCoords[0] , y);
                break;
        }
        return true;
    }

    public Bitmap getImagePath(String path) {
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        return myBitmap;
    }
}
