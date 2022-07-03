package com.example.shopie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormBillActivity extends FragmentActivity {
    EditText edtName, edtEmail, edtPhone, edtAddress;
    TextView noti;
    Button checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bill);

        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        noti = (TextView) findViewById(R.id.noti);
        checkoutBtn = (Button) findViewById(R.id.checkoutBtn);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().matches("")) {
                    noti.setText("Name cannot be empty");
                } else if (edtEmail.getText().toString().matches("")) {
                    noti.setText("Email address cannot be empty");
                } else if (edtPhone.getText().toString().matches("")) {
                    noti.setText("Phone number cannot be empty");
                } else if (edtAddress.getText().toString().matches("")) {
                    noti.setText("Address cannot be empty");
                } else {
                    Toast.makeText(getApplicationContext(), "Thank you for your order", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FormBillActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}