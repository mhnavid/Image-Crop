package nvd.hasan.imagecrop;

import android.support.v7.app.AppCompatActivity;

import com.wonderkiln.camerakit.CameraView;

public class CameraPreview extends AppCompatActivity{

    CameraView cameraView;

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }


}

