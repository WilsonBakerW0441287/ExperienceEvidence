package com.example.crudypizzaassignmnet3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

public class CreateOrEditActivity extends AppCompatActivity {
    SharedPreferences language;
    static final String LANGUAGE_KEY = "language";

    //set up buttons
    Button btnTextViewSize, btnTextViewToppings, btnTextViewCustomer, btnSubmit, btnCancel, btnPineAMinus, btnPineAPlus, btnPorkMinus, btnPorkPlus, btnPepPlus, btnPepMinus;
    RadioButton rbSmall, rbMedium, rbLarge;
    //set up text views
    TextView txtCustomerInfo, tvpineapple, tvpepperoni, tvpork, tvporkCount, tvpepperoniCount, tvpineappleCount;
    int totalToppings = 0;
    String orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit);
        //set up buttons
        btnTextViewSize = findViewById(R.id.btnTextViewSize);
        btnTextViewToppings = findViewById(R.id.btnTextViewToppings);
        btnTextViewCustomer = findViewById(R.id.btnTextViewCustomer);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        btnPineAMinus = findViewById(R.id.btnPineAMinus);
        btnPineAPlus = findViewById(R.id.btnPineAPlus);
        btnPorkMinus = findViewById(R.id.btnPorkMinus);
        btnPorkPlus = findViewById(R.id.btnPorkPlus);
        btnPepPlus = findViewById(R.id.btnPepPlus);
        btnPepMinus = findViewById(R.id.btnPepMinus);
        //set up radio buttons
        rbSmall = findViewById(R.id.rbSizeSmall);
        rbMedium = findViewById(R.id.rbSizeMed);
        rbLarge = findViewById(R.id.rbSizeLrg);
        //set up text views
        tvpineapple = findViewById(R.id.tvPineapple);
        tvpepperoni = findViewById(R.id.tvPepperoni);
        tvpork = findViewById(R.id.tvPork);
        txtCustomerInfo = findViewById(R.id.editTextCustomerInfo);
        tvpineappleCount = findViewById(R.id.tvPineappleCount);
        tvpepperoniCount = findViewById(R.id.tvPepCount);
        tvporkCount = findViewById(R.id.tvPorkCount);

        //language and shared preferences stuff
        language = getSharedPreferences(LANGUAGE_KEY, MODE_PRIVATE);
        String currentLanguage = language.getString(LANGUAGE_KEY, "English");//sets default language to english
        //Time to set every buttons text to the correct language
        if (currentLanguage.equals("French")) {
            setButtonsToFrench();
        } else if (currentLanguage.equals("English")) {
            setButtonsToEnglish();
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
        String checkintent = getIntent().getStringExtra("edit");
        Cursor c;

        if (checkintent != null) {
            if (checkintent.equals("edit")) {
                orderID = getIntent().getStringExtra("orderid");
                long orderidlong = Long.parseLong(orderID);
                if (orderID != null) {
                    //get the information from the databse and assign the values to the text views


                    int tempid = Integer.parseInt(orderID);
                    db.open();
                    c = db.getOrder(tempid);
                    //make buttons checked based on what information from the database
                    if (c.getString(2).equals("1")) {
                        rbSmall.setChecked(true);
                    } else if (c.getString(2).equals("2")) {
                        rbMedium.setChecked(true);
                    } else if (c.getString(2).equals("3")) {
                        rbLarge.setChecked(true);
                    }

                    //set the toppings
                    if (c.getInt(2) > 0) {
                        tvpineappleCount.setText(String.valueOf(c.getInt(3)));
                        totalToppings += c.getInt(2);
                    } else if (c.getInt(3) > 0) {
                        tvpepperoniCount.setText(String.valueOf(c.getInt(4)));
                        totalToppings += c.getInt(3);
                    } else if (c.getInt(4) > 0) {
                        tvporkCount.setText(String.valueOf(c.getInt(5)));
                        totalToppings += c.getInt(4);
                    } else {
                        totalToppings = 0;
                    }
                    //set the customer info
                    txtCustomerInfo.setText(c.getString(1));
                    //close the database
                    db.close();
                }//end if
            }//end if
        }//end if null


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                Intent mainscreen = new Intent(CreateOrEditActivity.this, MainActivity.class);
                startActivity(mainscreen);
            }
        });

        //add button listeners for the toppings
        btnPineAMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvpineappleCount.getText().toString());
                setaddbuttonsON();
                if (totalToppings == 0) {
                    setminusbuttonsOFF();
                }
                if (totalToppings > 0) {
                    totalToppings--;
                    if (currentCount > 0) {
                        currentCount--;
                    }
                }
                tvpineappleCount.setText(String.valueOf(currentCount));

            }
        });

        btnPineAPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvpineappleCount.getText().toString());
                setminusbuttonsON();
                if (totalToppings == 3) {
                    setaddbuttonsOFF();
                }
                if (totalToppings < 3) {
                    totalToppings++;
                    if (currentCount < 3) {
                        currentCount++;
                    }
                }
                tvpineappleCount.setText(String.valueOf(currentCount));
            }
        });

        btnPorkMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvporkCount.getText().toString());
                setaddbuttonsON();
                if (totalToppings == 0) {
                    setminusbuttonsOFF();
                }
                if (totalToppings > 0) {
                    totalToppings--;
                    if (currentCount > 0) {
                        currentCount--;
                    }
                }
                tvporkCount.setText(String.valueOf(currentCount));

            }
        });

        btnPorkPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvporkCount.getText().toString());
                setminusbuttonsON();
                if (totalToppings == 3) {
                    setaddbuttonsOFF();
                }
                if (totalToppings < 3) {
                    totalToppings++;
                    if (currentCount < 3) {
                        currentCount++;
                    }
                }
                tvporkCount.setText(String.valueOf(currentCount));
            }
        });

        btnPepMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvpepperoniCount.getText().toString());
                setaddbuttonsON();
                if (totalToppings == 0) {
                    setminusbuttonsOFF();
                }
                if (totalToppings > 0) {
                    totalToppings--;
                    if (currentCount > 0) {
                        currentCount--;
                    }
                }
                tvpepperoniCount.setText(String.valueOf(currentCount));
            }
        });

        btnPepPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick return to main activity
                int currentCount = Integer.parseInt(tvpepperoniCount.getText().toString());
                setminusbuttonsON();
                if (totalToppings == 3) {
                    setaddbuttonsOFF();
                }
                if (totalToppings < 3) {
                    totalToppings++;
                    if (currentCount < 3) {
                        currentCount++;
                    }
                }
                tvpepperoniCount.setText(String.valueOf(currentCount));
            }
        });

        //set up the save button which will save the order to the database
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();
                //onclick return to main activity
                //get the values from the text boxes
                String customerInfo = txtCustomerInfo.getText().toString();
                if (customerInfo == null || customerInfo.equals("")) {
                    Toast.makeText(CreateOrEditActivity.this, "Please enter customer information", Toast.LENGTH_SHORT).show();
                } else if (totalToppings == 0) {
                    Toast.makeText(CreateOrEditActivity.this, "Please add at least 1 topping", Toast.LENGTH_SHORT).show();
                } else {


                    int size = 0;
                    if (rbSmall.isChecked() || rbMedium.isChecked() || rbLarge.isChecked()) {
                        if (rbSmall.isChecked()) {
                            size = 1;
                        } else if (rbMedium.isChecked()) {
                            size = 2;
                        } else if (rbLarge.isChecked()) {
                            size = 3;
                        }
                    } else {
                        size = 1;
                        Toast.makeText(CreateOrEditActivity.this, "Size automatically set to small", Toast.LENGTH_SHORT).show();
                    }

                    //get the values from the text views
                    int topping1 = Integer.parseInt(tvpineappleCount.getText().toString());
                    int topping2 = Integer.parseInt(tvporkCount.getText().toString());
                    int topping3 = Integer.parseInt(tvpepperoniCount.getText().toString());
                    //get the order time using the date class
                    Date date = new Date();
                    String orderDate = date.toString();
                    //if statement to check if the order is a new order or an edit order
                    orderID = getIntent().getStringExtra("orderid");

                    if (checkintent == null) {
                        //add the order to the database
                        long id = db.insertOrder(customerInfo, size, topping1, topping2, topping3, orderDate);
                        //see if the order was added and display a message telling the user if it was or not
                        if (id > 0) {
                            Toast.makeText(CreateOrEditActivity.this, "Order Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateOrEditActivity.this, "Order Not Added", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    } else if (checkintent.equals("edit")) {
                        //update the order in the database
                        long orderidlong = 0;
                        try {
                            orderidlong = Long.parseLong(orderID);
                            boolean query = db.updateOrder(orderidlong, customerInfo, size, topping1, topping2, topping3, orderDate);
                            //see if the order was updated and display a message telling the user if it was or not
                            if (query) {
                                Toast.makeText(CreateOrEditActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CreateOrEditActivity.this, "Order Not Updated", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(CreateOrEditActivity.this, "Order Not Updated, Could not find ID", Toast.LENGTH_SHORT).show();
                        }

                        db.close();
                    }


                    //return to the main activity
                    Intent mainscreen = new Intent(CreateOrEditActivity.this, MainActivity.class);
                    startActivity(mainscreen);
                }//ends else checking for string

            }
        });

    }//end onCreate

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


    public void setaddbuttonsON() {
        btnPineAPlus.setEnabled(true);
        btnPepPlus.setEnabled(true);
        btnPorkPlus.setEnabled(true);
    }

    public void setaddbuttonsOFF() {
        btnPineAPlus.setEnabled(false);
        btnPepPlus.setEnabled(false);
        btnPorkPlus.setEnabled(false);
    }

    public void setminusbuttonsON() {
        btnPineAMinus.setEnabled(true);
        btnPepMinus.setEnabled(true);
        btnPorkMinus.setEnabled(true);
    }

    public void setminusbuttonsOFF() {
        btnPineAMinus.setEnabled(false);
        btnPepMinus.setEnabled(false);
        btnPorkMinus.setEnabled(false);
    }

    public void setButtonsToFrench() {
        String[] TextFrench = getResources().getStringArray(R.array.createOrEditButtonsFRENCH);
        btnTextViewSize.setText(TextFrench[0]);
        btnTextViewToppings.setText(TextFrench[1]);
        btnTextViewCustomer.setText(TextFrench[2]);
        rbSmall.setText(TextFrench[3]);
        rbMedium.setText(TextFrench[4]);
        rbLarge.setText(TextFrench[5]);
        tvpepperoni.setText(TextFrench[6]);
        tvpineapple.setText(TextFrench[7]);
        tvpork.setText(TextFrench[8]);
        txtCustomerInfo.setText(TextFrench[9]);
        btnSubmit.setText(TextFrench[10]);
        btnCancel.setText(TextFrench[11]);
    }

    public void setButtonsToEnglish() {
        String[] TextEnglish = getResources().getStringArray(R.array.createOrEditButtonsENGLISH);
        btnTextViewSize.setText(TextEnglish[0]);
        btnTextViewToppings.setText(TextEnglish[1]);
        btnTextViewCustomer.setText(TextEnglish[2]);
        rbSmall.setText(TextEnglish[3]);
        rbMedium.setText(TextEnglish[4]);
        rbLarge.setText(TextEnglish[5]);
        tvpepperoni.setText(TextEnglish[6]);
        tvpineapple.setText(TextEnglish[7]);
        tvpork.setText(TextEnglish[8]);
        txtCustomerInfo.setAutofillHints(TextEnglish[9]);
        btnSubmit.setText(TextEnglish[10]);
        btnCancel.setText(TextEnglish[11]);
    }
}