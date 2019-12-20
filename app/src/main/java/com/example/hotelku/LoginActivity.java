package com.example.hotelku;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    Button LogInButton, RegisterButton ;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DatabaseHelper dataBaseHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PasswordPattern = "\"^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{4,}$\";";
    public static final String UserEmail = "";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.buttonLogin);



        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);


        dataBaseHelper = new DatabaseHelper(this);

        //Validate
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (Email.getText().toString().trim().matches(EmailPattern)) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





        //Adding click listener to log in button.
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();


            }
        });



    }

    // Login function starts from here.
    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = dataBaseHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Column_4_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(LoginActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(LoginActivity.this,"Login Sukses",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);

            startActivity(intent);


        }
        else {

            Toast.makeText(LoginActivity.this,"Email atau Password Salah, Silahkan Coba lagi.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }




}
