package healers.data.solutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import healers.data.solutions.R;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
Button btn;
CountryCodePicker ccp;
EditText t1;
FirebaseAuth AUTH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AUTH=FirebaseAuth.getInstance();
        if(AUTH.getCurrentUser()!=null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();

        }

     t1=(EditText)findViewById(R.id.mobile);
     ccp=(CountryCodePicker)findViewById(R.id.ccp);
     ccp.registerCarrierNumberEditText(t1);
     btn=(Button)findViewById(R.id.getotp);
     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             if(t1.getText().toString().isEmpty()&&t1.getText().toString().length()!=10){
                 Toast.makeText(Login.this, "Enter correct number", Toast.LENGTH_SHORT).show();
             }
             else{
                 Intent getotp=new Intent(Login.this, validation.class);
                 getotp.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                 startActivity(getotp);

             }


         }
     });
    }
}