package uk.ac.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LanguageSubscription extends AppCompatActivity {

    ArrayList<String> selectedItems;
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_subscription);
        //create an ArrayList object to store selected items
        selectedItems=new ArrayList<String>();


    }



    public void onStart(){
        super.onStart();
        //create an instance of ListView
        ListView chl=(ListView) findViewById(R.id.checkable_list);
        //set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] items={"English",
                "Afrikaans",
                "Albanian",
                "Arabic",
                "Armenian",
                "Azerbaijani",
                "Bashkir",
                "Basque",
                "Belarusian",
                "Bengali",
                "Bosnian",
                "Bulgarian",
                "Catalan",
                "Central Khmer",
                "Chinese",
                "Chinese (Traditional)",
                "Chuvash",
                "Croatian",
                "Czech",
                "Danish",
                "Dutch",
               "English",
                "Esperanto",
                "Estonian",
                "Finnish",
                "French",
                "Georgian",
                "German",
                "Greek",
                "Gujarati",
                "Haitian",
                "Hebrew",
                "Hindi",
                "Hungarian",
                "Icelandic",
                "Indonesian",
                "Irish",
                "Italian",
                "Japanese",
                "Kazakh",
                "Kirghiz",
                "Korean",
                "Kurdish",
                "Latvian",
                "Lithuanian",
                "Malay",
                "Malayalam",
                "Maltese",
                "Mongolian",
                "Norwegian Bokmal",
                "Norwegian Nynorsk",
                "Panjabi",
                "Persian",
                "Polish",
                "Portuguese",
                "Pushto",
                "Romanian",
                "Russian",
                "Serbian",
                "Slovakian",
                "Slovenian",
                "Somali",
                "Spanish",
                "Swedish",
                "Tamil",
                "Telugu",
                "Thai",
                "Turkish",
                "Ukrainian",
                "Urdu",
                "Vietnamese"};

        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,items);
        chl.setAdapter(aa);
        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem);
                else
                    selectedItems.add(selectedItem);
            }

        });
    }

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }

}
