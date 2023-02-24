package com.example.crudypizzaassignmnet3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SearchActivity extends AppCompatActivity {
    SharedPreferences language;
    static final String LANGUAGE_KEY = "language";
    Button btnAction;
    TextView txtSearch;
    Integer orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //set up buttons
        btnAction = (Button) findViewById(R.id.btnAction);
        //set up text views
        txtSearch = findViewById(R.id.etOrderid);

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
        db.open();
        Cursor c;

        //language and shared preferences stuff
        language = getSharedPreferences(LANGUAGE_KEY, MODE_PRIVATE);
        String currentLanguage = language.getString(LANGUAGE_KEY, "English");//sets default language to english
        //Time to set every buttons text to the correct language
        if (currentLanguage.equals("French")) {
            setButtonsToFrench();
        } else if (currentLanguage.equals("English")) {
            setButtonsToEnglish();
        }

        //take intent from main activity and set the text on the button to the correct text: "search" "delete" "edit"
        String action = getIntent().getStringExtra("buttonPressed");
        btnAction.setText(action);

        //set up listener
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the order
                String orderidstring = txtSearch.getText().toString();

                //if the orderid is empty then toast the user to enter a number
                if (!orderidstring.isEmpty()) {
                    //this checks if there is a number in the orderid field at all
                    //if there is then it will execute the code
                    if (!orderidstring.equals("")) {
                        //onclick return to main activity
                        Intent intent = null;
                        orderid = Integer.parseInt(orderidstring);
                        if (action.equals("search")) {
                            //search for the pizza
                            //if found, go to search results activity
                            //if not found, return to main activity with a message saying it was not found
                            intent = new Intent(SearchActivity.this, ViewOrdersActivity.class);
                            intent.putExtra("order", orderidstring);
                        } else if (action.equals("delete")) {
                            //search for the pizza
                            //if the orderid is not found, give a toast saying it was not found
                            //if the orderid is found, delete it and give a toast saying it was deleted
                            if (db.deleteOrder(orderid)) {
                                Toast.makeText(SearchActivity.this, "Order Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SearchActivity.this, "Order Not Found", Toast.LENGTH_SHORT).show();
                            }
                            intent = new Intent(SearchActivity.this, MainActivity.class);
                        } else if (action.equals("edit")) {
                            //search for the pizza
                            //if found, Go to the create or edit activity with the id of the pizza bundlded with the intent
                            //if not found, return to main activity with a message saying it was not found
                            intent = new Intent(SearchActivity.this, CreateOrEditActivity.class);
                            intent.putExtra("orderid", orderidstring);//given as string to interpret in the next activity
                            intent.putExtra("edit", "edit");
                        }
                        startActivity(intent);
                    }//end if order string is empty
                } else {
                    Toast.makeText(SearchActivity.this, "Please enter an order id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setButtonsToFrench() {
        String[] TextFrench = getResources().getStringArray(R.array.searchButtonsFRENCH);
        btnAction.setText(TextFrench[0]);
        txtSearch.setAutofillHints(TextFrench[1]);
    }

    public void setButtonsToEnglish() {
        String[] TextEnglish = getResources().getStringArray(R.array.searchButtonsENLISH);
        btnAction.setText(TextEnglish[0]);
        txtSearch.setAutofillHints(TextEnglish[1]);

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