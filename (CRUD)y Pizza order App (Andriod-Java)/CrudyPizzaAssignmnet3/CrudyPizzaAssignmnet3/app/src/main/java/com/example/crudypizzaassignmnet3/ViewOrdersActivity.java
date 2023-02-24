package com.example.crudypizzaassignmnet3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ViewOrdersActivity extends AppCompatActivity {
    Button btnMain;
    TextView txtViewOrders;
    SharedPreferences language;
    String ordertext = "";
    static final String LANGUAGE_KEY = "language";
    String currentLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        //set up buttons
        btnMain = (Button) findViewById(R.id.btnMain);
        //set up text views
        txtViewOrders = (TextView) findViewById(R.id.tvOrders);
        //set up button listeners
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrdersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //language and shared preferences stuff
        language = getSharedPreferences(LANGUAGE_KEY, MODE_PRIVATE);
        currentLanguage = language.getString(LANGUAGE_KEY, "English");//sets default language to english
        //Time to set every buttons text to the correct language
        if (currentLanguage.equals("French")) {
            setButtonsToFrench();
        } else if (currentLanguage.equals("English")) {
            setButtonsToEnglish();
        }


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
        //orders will get retrieved from the database and displayed in a text view in the scroll view
        //after grabbing 1 entry from the database, add it to an arraylist
        //then display the arraylist in the text view
        //we might need a loop to do so (we can use /n to separate the entries)
        //davids get all orders method will be used here
        //I need to make a if statement that checks the intent that was passed to this activity
        //if the intent was to view all orders, then we will use the getAllOrders method
        //if the intent was to view a specific order, then we will use the getOrder method
        String order = getIntent().getStringExtra("order");
        if (order != null) {
            if (order.equals("all")) {
                c = db.getAllOrders();
                if (c.moveToFirst()) {
                    do {
                        ordertext += DisplayOrder(c);
                    } while (c.moveToNext());
                }
            } else {
                int orderID = Integer.parseInt(getIntent().getStringExtra("order"));
                c = db.getOrder(orderID);
                ordertext = DisplayOrder(c);
            }
            db.close();
            txtViewOrders.setText(ordertext);
        }
    }//end of onCreate

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

    public String DisplayOrder(Cursor c) {
        int topping1 = Integer.parseInt(c.getString(2));
        int topping2 = Integer.parseInt(c.getString(3));
        int topping3 = Integer.parseInt(c.getString(4));
        String toppingString = "";
        if (currentLanguage.equals("English")) {
            if (topping1 == 1) {
                toppingString += "Pineapple 1, ";
            } else if (topping1 == 2) {
                toppingString += "Pineapple 2, ";
            } else if (topping1 == 3) {
                toppingString += "Pineapple 3. ";
            }
            if (topping2 == 1) {
                toppingString += "Pork 1, ";
            } else if (topping2 == 2) {
                toppingString += "Pork 2, ";
            } else if (topping2 == 3) {
                toppingString += "Pork 3. ";
            }
            if (topping3 == 1) {
                toppingString += "Pepperoni 1, ";
            } else if (topping3 == 2) {
                toppingString += "Pepperoni 2, ";
            } else if (topping3 == 3) {
                toppingString += "Pepperoni 3. ";
            }
        }//end check if english
        else if (currentLanguage.equals("French")) {
            if (topping1 == 1) {
                toppingString += "Ananas 1, ";
            } else if (topping1 == 2) {
                toppingString += "Ananas 2, ";
            } else if (topping1 == 3) {
                toppingString += "Ananas 3. ";
            }
            if (topping2 == 1) {
                toppingString += "Porc 1, ";
            } else if (topping2 == 2) {
                toppingString += "Porc 2, ";
            } else if (topping2 == 3) {
                toppingString += "Porc 3. ";
            }
            if (topping3 == 1) {
                toppingString += "Pepperoni 1, ";
            } else if (topping3 == 2) {
                toppingString += "Pepperoni 2, ";
            } else if (topping3 == 3) {
                toppingString += "Pepperoni 3. ";
            }
        }//end check if french

        String size = "";
        if (currentLanguage.equals("English")) {
            if (c.getString(2).equals("1")) {
                size = "Small";
            } else if (c.getString(2).equals("2")) {
                size = "Medium";
            } else if (c.getString(2).equals("3")) {
                size = "Large";
            }
        } else if (currentLanguage.equals("French")) {
            if (c.getString(2).equals("1")) {
                size = "Petit";
            } else if (c.getString(2).equals("2")) {
                size = "Moyen";
            } else if (c.getString(2).equals("3")) {
                size = "Grand";
            }
        }

        String orderEN =
                "id: " + c.getString(0) + "\n" +
                        "Customer Info: " + c.getString(1) + "\n" +
                        "Size: " + size + "\n" +
                        "Toppings: " + toppingString + "\n" +
                        "Order Date and Time: " + c.getString(6) + "\n\n";
        String orderFR =
                "id: " + c.getString(0) + "\n" +
                        "Info Client: " + c.getString(1) + "\n" +
                        "Taille: " + size + "\n" +
                        "Toppings: " + toppingString + "\n" +
                        "Date et Heure de la Commande: " + c.getString(6) + "\n\n";
        if (currentLanguage.equals("English")) {
            return orderEN;
        } else if (currentLanguage.equals("French")) {
            return orderFR;
        } else {
            return "Error";
        }
    }//end method DisplayContact

    public void setButtonsToFrench() {
        String[] TextFrench = getResources().getStringArray(R.array.viewOrdersButtonsFRENCH);
        btnMain.setText(TextFrench[0]);
        txtViewOrders.setText(TextFrench[1]);

    }

    public void setButtonsToEnglish() {
        String[] TextEnglish = getResources().getStringArray(R.array.viewOrdersButtonsENGLISH);
        btnMain.setText(TextEnglish[0]);
        txtViewOrders.setText(TextEnglish[1]);

    }
}