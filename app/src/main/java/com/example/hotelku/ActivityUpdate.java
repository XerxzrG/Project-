package com.example.hotelku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityUpdate extends Activity implements View.OnClickListener {
    private EditText emailText;
    private EditText jumlahruanganText;
    private EditText tanggalText;
    private Button updateBtn, deleteBtn;
    private EditText namaText;
    private EditText nohpText;
    private long _id;
    private DBManager dbManager;
    private LinearLayout pulang;
    public static final String UserEmail = "";
    private String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.activity_update);
        dbManager = new DBManager(this);
        dbManager.open();
        namaText = (EditText) findViewById(R.id.nama_edittext);
        emailText = (EditText) findViewById(R.id.email_edittext);
        nohpText = (EditText) findViewById(R.id.nohp_edittext);
        jumlahruanganText = (EditText) findViewById(R.id.jumlahruangan_edittext);
        tanggalText= (EditText) findViewById(R.id.tanggal_edittext);

        pulang = (LinearLayout)findViewById(R.id.pulang) ;

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        /* Membuat objek Intent dengan nilai yang dikirim objek Intent yang telah memanggil kelas ini sebelumnya */
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String nohp = intent.getStringExtra("nohp");
        String jumlahruangan = intent.getStringExtra("jumlahruangan");
        String tanggal = intent.getStringExtra("tanggal");
        _id = Long.parseLong(id);
        namaText.setText(nama);
        emailText.setText(email);
        nohpText.setText(nohp);
        jumlahruanganText.setText(jumlahruangan);
        tanggalText.setText(tanggal);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        pulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(ActivityUpdate.this,ActivityDataBooking.class);
                intentB.putExtra(UserEmail, EmailHolder);
                startActivity(intentB);
            }
        });
    }
    // Pemilihan untuk tombol yang disentuh user (update/ delete)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Pilihan Update
            case R.id.btn_update:
                String nama = namaText.getText().toString();
                String email = emailText.getText().toString();
                String nohp = nohpText.getText().toString();
                String jumlahruangan = jumlahruanganText.getText().toString();
                String tanggal = tanggalText.getText().toString();
                dbManager.update(_id,nama,email,nohp,jumlahruangan,tanggal);
                this.returnHome();
                Toast.makeText(getApplicationContext(),"Data Booking di Update", Toast.LENGTH_LONG).show();
                break;
            // Pilihan Update
            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                Toast.makeText(getApplicationContext(),"Data Booking di Hapus", Toast.LENGTH_LONG).show();
                break;


        }

    }
    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ActivityDataBooking.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.kembali){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();

        }
        return super.onOptionsItemSelected(item);
    }



}
