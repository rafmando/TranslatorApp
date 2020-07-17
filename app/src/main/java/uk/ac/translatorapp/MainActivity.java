package uk.ac.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button AddPhrasesButton;
    public Button TranslateButton;
    public Button DisplayButton;
    public Button LanguageSubscriptionButton;
    public Button EditPhrasesButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditPhrasesButton=(Button)findViewById(R.id.EditPhasesButton);
        EditPhrasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEditPhrases();
            }
        });

        LanguageSubscriptionButton=(Button)findViewById(R.id.LanguageSubscriptionButton);
        LanguageSubscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLanguageSubscription();
            }
        });


        DisplayButton =(Button)findViewById(R.id.DisplayPhrasesButton);
        DisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityDisplayPhrases();
            }
        });



        TranslateButton = (Button)findViewById(R.id.TranslateButton);
        TranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityTranslate();

            }
        });


        AddPhrasesButton = (Button)findViewById(R.id.AddPhrasesButton);
        AddPhrasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openActivityAddPhrases();
            }
        });
    }




    public void openActivityAddPhrases(){

        Intent intent = new Intent(this,AddPhrases.class);
        startActivity(intent);
    }


    public void openActivityTranslate(){

        Intent intent = new Intent(this,Translate.class);
        startActivity(intent);
    }

    public void openActivityDisplayPhrases(){

        Intent intent = new Intent(this,DisplayPhrases.class);
        startActivity(intent);
    }

    public void openActivityLanguageSubscription(){

        Intent intent = new Intent(this,LanguageSubscription.class);
        startActivity(intent);
    }

    public void openActivityEditPhrases(){

        Intent intent = new Intent(this,AddPhrases.class);
        startActivity(intent);
    }



}
