package healers.data.solutions;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import healers.data.solutions.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class medicaldetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;


    EditText editText;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicaldetails);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
editText=findViewById(R.id.editText);
floatingActionButton=findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(new ArrayList<>());
        recyclerView.setAdapter(dataAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(medicaldetails.this, "Enter Dcoument ID", Toast.LENGTH_SHORT).show();
                }

                else{
                    String abc=editText.getText().toString();
                    System.out.println(abc);
                fetchDataFromFirestore(abc);
                }


            }
        });


    }

    private void fetchDataFromFirestore(String iddoc) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("patients").document(iddoc).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Patient patient = task.getResult().toObject(Patient.class);

                        if (patient != null) {
                            List<Patient> patientList = new ArrayList<>();
                            patientList.add(patient);

                            dataAdapter.updateData(patientList);
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "Error getting document: ", task.getException());
                    }
                });
    }

}