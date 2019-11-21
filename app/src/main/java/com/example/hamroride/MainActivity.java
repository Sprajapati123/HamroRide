package com.example.hamroride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_driver,btn_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_driver=findViewById(R.id.btndriver);
        btn_customer=findViewById(R.id.btncustomer);

        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DriverLoginActivity.class));
//                Toast.makeText(MainActivity.this, "Driver", Toast.LENGTH_SHORT).show();
            }
        });

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CustomerLoginActivity.class));
//                Toast.makeText(MainActivity.this, "customer", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
