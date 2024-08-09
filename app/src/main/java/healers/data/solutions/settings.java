package healers.data.solutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import healers.data.solutions.R;
import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn=findViewById(R.id.lgbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login=new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                finish();

            }
        });
    }
}