package com.example.assignment3crudypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import android.database.*;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    //set up buttons and text fields
    Button btnCreate, btnView, btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create buttons

        btnCreate = (Button) findViewById(R.id.btnCreateOrder);
        btnView = (Button) findViewById(R.id.btnViewOrders); //is null
        btnEdit = (Button) findViewById(R.id.btnEdit); //is null
        btnDelete = (Button) findViewById(R.id.btnDelete); //is null

//        String msg = (String) btnCreate.getText();


//        //create on click listener
//        btnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////toast popup test code
//                Toast.makeText(MainActivity.this, "Create Order", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(MainActivity.this, CreateOrEditActivity.class);
////                startActivity(intent);
//            }
//        });
//
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
////
////                startActivity(intent);
//            }
//        });
//
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                Intent intent = new Intent(MainActivity.this, ViewOrderActivity.class);
////
////                startActivity(intent);
//            }
//        });
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //we want to know if the user pressed the edit,delete, or searcht button
////                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
////
////                startActivity(intent);
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
////                startActivity(intent);
//            }
//        });


    }//end on create

//this is used in the on ceate        //connect to db
//        try{
//            String destPath = "/data/data/" + getPackageName() + "/databases/CruddyPizzaDB";
//            File f = new File(destPath);
//            if(!f.exists()){
//                CopyDB(getBaseContext().getAssets().open("CruddyPizzaDB"), //why in the sweet fuck does CopyDB not work?
//                        new FileOutputStream(destPath));
//            }
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) { //why does this warning exist?
//            e.printStackTrace();
//        }
//
//        DBAdapter db = new DBAdapter(this);
//
//        //insert
//        db.open();
//        long id = db.insertOrder("Large", "Pepperoni", "2020-04-20 12:00:00", "John Doe");
//        db.close();
//
//        //get all
//        db.open();
//        Cursor c = db.getAllOrders();
//        if(c.moveToFirst()){
//            do{
//                DisplayOrder(c);
//            }while(c.moveToNext());
//        }
//        db.close();
//
//        //get one
//        db.open();
//        c = db.getOrder(1);
//        if(c.moveToFirst()){
//            DisplayOrder(c);
//        }else{
//            Toast.makeText(this, "No order found", Toast.LENGTH_LONG).show();
//        }
//        db.close();
//
//        //update
//        db.open();
//        if(db.updateOrder(1, "Large", "Pepperoni", "2020-04-20 12:00:00", "John Doe")){
//            Toast.makeText(this, "Update successful", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
//        }
//
//        //delete
//        db.open();
//        if(db.deleteOrder(1)){
//            Toast.makeText(this, "Delete successful", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this, "Delete failed", Toast.LENGTH_LONG).show();
//        }
//        db.close();
//
//
//
//    }//commented end on create
//    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
//        //copy 1k bytes at a time
//        byte[] buffer = new byte[1024];
//        int length;
//        while((length = inputStream.read(buffer))>0){
//            outputStream.write(buffer,0,length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//    public void DisplayOrder(Cursor c){
//     //make everything display in a toast popup
//
//        Toast.makeText(this,
//                "Id:" + c.getString(0) + "\n" +
//                        "Size:" + c.getString(1) + "\n" +
//                        "Toppings:" + c.getString(2) + "\n" +
//                        "Date:" + c.getString(3) + "\n" +
//                        "Name:" + c.getString(4) + "\n",
//                Toast.LENGTH_LONG).show();
//    }

}