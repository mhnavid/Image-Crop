package nvd.hasan.imagecrop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultShow extends AppCompatActivity {

    TextView rsltText;
    Button rsltBackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

        String resp = getIntent().getStringExtra("resp");

        rsltText = findViewById(R.id.rsltText);
        rsltBackbtn = findViewById(R.id.rsltBackbtn);

        rsltText.setText(resp);
        rsltBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ResultShow.this, cameraShow.class);
                startActivity(in);
            }
        });


    }
}
