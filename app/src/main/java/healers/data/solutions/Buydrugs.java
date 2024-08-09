package healers.data.solutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import healers.data.solutions.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Buydrugs extends AppCompatActivity {
    RecyclerView medview;
    CardView card;
    adpt adp;
    FirebaseDatabase db;
    FirebaseAuth oth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buydrugs);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Search medicine here");

card=findViewById(R.id.cart);
card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        oth=FirebaseAuth.getInstance();
        Toast.makeText(Buydrugs.this, "Loading cart", Toast.LENGTH_SHORT).show();
        ref= FirebaseDatabase.getInstance().getReference("Cartitems");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(oth.getCurrentUser().getUid())){

                    startActivity(new Intent(getApplicationContext(),cart.class));
                    finish();
                }

                else{
                    Toast.makeText(Buydrugs.this, "Add items First", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
});
        FirebaseRecyclerOptions<Modal> options =
                new FirebaseRecyclerOptions.Builder<Modal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medicines"), Modal.class)
                        .build();
        medview=findViewById(R.id.medview);
        medview.setLayoutManager(new LinearLayoutManager(this));
        adp=new adpt(options,getApplicationContext());
        medview.setAdapter(adp);

    }



    @Override
    protected void onStart() {
        super.onStart();
        adp.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adp.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processsearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
    private void  processsearch(String s){

        FirebaseRecyclerOptions<Modal> options =
                new FirebaseRecyclerOptions.Builder<Modal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medicines").orderByChild("Medname").startAt(s).endAt(s+"\uf8ff"), Modal.class)
                        .build();

        adp=new adpt(options,getApplicationContext());
        adp.startListening();
        medview.setAdapter(adp);

    }
}