package vn.edu.greenwich.cw_1_001244924;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class IntroActivity extends AppCompatActivity {

    private ConstraintLayout btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(view -> startActivity( new Intent(IntroActivity.this,MainActivity.class)));
    }
}