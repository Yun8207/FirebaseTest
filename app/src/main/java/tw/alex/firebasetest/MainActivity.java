package tw.alex.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText username;
    private TextView mesg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        mesg = findViewById(R.id.message);

        // Write a message to the database
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        mDatabase = fdb.getReference("bike");
       // mDatabase.setValue("alex");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
               // Post post = dataSnapshot.getValue(Post.class);
                // ...
                //String str = (String)dataSnapshot.getValue();
                Bike b2 = dataSnapshot.getValue(Bike.class);


                //Log.v("alex", str);
                if (b2 != null) {
                    mesg.setText(b2.name + ":" + b2.speed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);
    }

    public void sendData(View view) {
        Log.v("alex","sendData");
        Bike b1 = new Bike();
        b1.speed = 12.3; b1.name = "Alex";
//        String uname = username.getText().toString();
        mDatabase.setValue(b1);


    }


}
