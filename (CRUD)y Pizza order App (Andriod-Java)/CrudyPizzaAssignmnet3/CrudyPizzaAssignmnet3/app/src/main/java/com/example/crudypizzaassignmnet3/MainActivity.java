package com.example.crudypizzaassignmnet3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    //set up buttons and text fields
    Button btnCreate, btnViewOrders, btnEdit, btnDelete, btnSearch;
    Switch swtENFR;
    TextView txtWelcome;

    SharedPreferences language;
    static final String LANGUAGE_KEY = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create buttons
        btnCreate = (Button) findViewById(R.id.btnCreateOrders);
        btnViewOrders = (Button) findViewById(R.id.btnViewOrders);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        swtENFR = (Switch) findViewById(R.id.swtLanguage);
        txtWelcome = (TextView) findViewById(R.id.tvWelcomeText);

        //language and shared preferences stuff
        language = getSharedPreferences(LANGUAGE_KEY, MODE_PRIVATE);
        String currentLanguage = language.getString(LANGUAGE_KEY, "English");//sets default language to english
        //making sure the switch is set to the correct language
        if (currentLanguage.equals("French")) {
            swtENFR.setChecked(true);
        }

        //database stuff
        try {
            String destPath = "/data/data/" + getPackageName() + "/database/CruddyPizzaDB";
            //Alternate way to do destPath:
            //String destPath = Environment.getExternalStorageDirectory().getPath() +
            //getPackageName() + "/database/MyDB";
            File f = new File(destPath);
            if (!f.exists()) {
                CopyDB(getBaseContext().getAssets().open("CruddyPizzaDB"),
                        new FileOutputStream(destPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBAdapter db = new DBAdapter(this);




        //switch listener
        swtENFR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                language = getSharedPreferences(LANGUAGE_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = language.edit();
                if (isChecked) {
                    editor.putString(LANGUAGE_KEY, "French");
                    setButtonsToFrench();
                } else {
                    editor.putString(LANGUAGE_KEY, "English");
                    setButtonsToEnglish();
                }
                editor.apply();
                editor.commit(); //getting warning on commit
                //no need to give toast pop up as the user will see the language change
            }//end onClick
        });//end setOnClickListener

        //Time to set every buttons text to the correct language
        if (currentLanguage.equals("French")) {
            setButtonsToFrench();
        } else if (currentLanguage.equals("English")) {
            setButtonsToEnglish();
        }

        //create onclick listeners for buttons
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateOrEditActivity.class);
                startActivity(intent);
            }
        });

        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewOrdersActivity.class);
                intent.putExtra("order", "all");
                startActivity(intent);
            }
        });
        //these last 3 buttons all will route to the search activity with a different extra to let the button change text based on what the user pressed

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("buttonPressed", "edit");
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("buttonPressed", "search");
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("buttonPressed", "delete");
                startActivity(intent);
            }
        });
    }
    public void setButtonsToFrench(){
        String[] TextFrench = getResources().getStringArray(R.array.mainActivityButtonsFRENCH);
        btnCreate.setText(TextFrench[0]);
        btnEdit.setText(TextFrench[1]);
        btnViewOrders.setText(TextFrench[2]);
        btnDelete.setText(TextFrench[3]);
        btnSearch.setText(TextFrench[4]);
        txtWelcome.setText(TextFrench[5]);
    }
    public void setButtonsToEnglish(){
        String[] TextEnglish = getResources().getStringArray(R.array.mainActivityButtonsENGLISH);
        btnCreate.setText(TextEnglish[0]);
        btnEdit.setText(TextEnglish[1]);
        btnViewOrders.setText(TextEnglish[4]);
        btnDelete.setText(TextEnglish[3]);
        btnSearch.setText(TextEnglish[2]);
        txtWelcome.setText(TextEnglish[5]);
    }
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //copy 1k bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();

    }//end method CopyDB
}