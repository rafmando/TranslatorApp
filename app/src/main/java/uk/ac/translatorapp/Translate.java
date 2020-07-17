package uk.ac.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;



import android.view.View;
import android.widget.Toast;

import com.ibm.cloud.sdk.core.http.HttpMediaType;

import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class Translate extends AppCompatActivity {
    private LanguageTranslator translationService;
    TextView tv;
    private StreamPlayer player = new StreamPlayer();
    private TextToSpeech textService;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);






        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getData();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }


        listView = findViewById(R.id.listView);
        translationService = initLanguageTranslatorService();
        textService = initTextToSpeechService();
        new TranslationTask().execute("Hello World and my friend");

    }


    private LanguageTranslator initLanguageTranslatorService() {

        String APIKey="OZS-vbEYjND8QYXFM0Bs9gBS2O7gednSfwVmXPFfG2pN";
                String ibmurl ="https://api.eu-gb.language-translator.watson.cloud.ibm.com/instances/7c58b2bb-e7c4-4ba5-ac01-18359d82d3c4";
        Authenticator authenticator
                = new IamAuthenticator(APIKey);
        LanguageTranslator service = new LanguageTranslator("2018-05-01", authenticator);
        service.setServiceUrl(ibmurl);
        return service;
    }


    private class TranslationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            TranslateOptions translateOptions = new TranslateOptions.Builder()
                    .addText(params[0])
                    .source(Language.ENGLISH)
                    .target("es")
                    .build();
            TranslationResult result
                    = translationService.translate(translateOptions).execute().getResult();
            String firstTranslation = result.getTranslations().get(0).getTranslation();
            return firstTranslation;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }

    public void buttonClick(View view) {
        Calendar cal = new GregorianCalendar();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        String prefix = "";
        if (minutes < 9 && minutes >= 1)
            prefix = " O ";
        String suffix = " am ";
        if (hours > 12) {
            hours = hours - 12;
            suffix = " pm ";
        }
        new SynthesisTask().execute("Hello world. Good morning my friend. How is the weather today? The time is " +
                + hours + " " + prefix + minutes + suffix);
    }


    private TextToSpeech initTextToSpeechService() {
        String APIKeyt="q9Bk_g8SNC7vm6sFJMS8g7_qCbbrnzjEWMoQz4DEo0Lc";
        String ibmurlt ="https://api.eu-gb.text-to-speech.watson.cloud.ibm.com/instances/280e3f5a-facc-4dc4-9fc3-00f32e41913d";
        Authenticator authenticator
                = new IamAuthenticator(APIKeyt);
        TextToSpeech service = new TextToSpeech(authenticator);
        service.setServiceUrl(ibmurlt);
        return service;
    }


    private class SynthesisTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text(params[0])
                    .voice(SynthesizeOptions.Voice.EN_US_LISAVOICE)
                    .accept(HttpMediaType.AUDIO_WAV)
                    .build();
            player.playStream(textService.synthesize(synthesizeOptions).execute().getResult());
            return "Did synthesize";
        }
    }



    }
