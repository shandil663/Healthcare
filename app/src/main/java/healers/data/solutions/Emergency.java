package healers.data.solutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import healers.data.solutions.R;

public class Emergency extends AppCompatActivity {
CardView cardemer,emerpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
emerpage=findViewById(R.id.cardfromemer);
emerpage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(Emergency.this,MainActivity.class));
        finish();
    }
});
        cardemer=findViewById(R.id.emergencybutton);
        cardemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast toast= Toast.makeText(Emergency.this, "Emergency alert has been sent to\n hospital and will be arriving soon", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}