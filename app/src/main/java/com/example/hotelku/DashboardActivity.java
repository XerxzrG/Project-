package com.example.hotelku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button LogOUT ;
    CardView Booking,Listbooking;
    public static final String UserEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        Booking = (CardView)findViewById(R.id.booking);
        Listbooking = (CardView)findViewById(R.id.listbooking);

        Intent intent = getIntent();

        // Receiving User Email Send By LoginActivity.
        EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);



        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(DashboardActivity.this,ActivityTambahBooking.class);
                intentB.putExtra(UserEmail, EmailHolder);
                startActivity(intentB);
            }
        });

        Listbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(DashboardActivity.this,ActivityDataBooking.class);
                intentB.putExtra(UserEmail, EmailHolder);

                startActivity(intentB);
            }
        });

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();
                Intent intentB = new Intent(DashboardActivity.this,LoginActivity.class);
                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

    }



}
