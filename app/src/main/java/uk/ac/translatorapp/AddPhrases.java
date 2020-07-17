package uk.ac.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhrases extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phrases);



        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnView =(Button)findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent =  new Intent(AddPhrases.this, ViewListContents.class);
                 startActivity(intent);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");

                }else{
                    Toast.makeText(AddPhrases.this,"You must put something in text field",Toast.LENGTH_LONG).show();




                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPhrases.this, ListDataActivity.class);
                startActivity(intent);
            }
        });




    }

    public void AddData(String newEntry){
            boolean insertData = myDB.addData(newEntry);

            if(insertData==true){


                Toast.makeText(AddPhrases.this,"Successfully entered",Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(AddPhrases.this,"Something went wrong ",Toast.LENGTH_LONG).show();

            }



    }



    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
