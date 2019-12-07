package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class AddNameActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private Button buttonAddName;

    EditText txtname;
    Button btnsave;
    com.google.firebase.database.DatabaseReference reff;
    Member member;

    //save name input by user to firebase and link to question page once it click enter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);

        editTextUsername=findViewById(R.id.userName);
        buttonAddName=findViewById(R.id.button2);

        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String usernameInput = editTextUsername.getText().toString().trim();

                buttonAddName.setEnabled(!usernameInput.isEmpty());

                reff= FirebaseDatabase.getInstance().getReference().child("member");

                member=new Member();
                buttonAddName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent myIntent = new Intent(AddNameActivity.this, choose_quiz.class);
                        startActivity(myIntent);

                        member.setUsername(editTextUsername.getText().toString().trim());

                        reff.push().setValue(member);
                        Toast.makeText(AddNameActivity.this, "Name inserted successfully", Toast.LENGTH_LONG).show();

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
