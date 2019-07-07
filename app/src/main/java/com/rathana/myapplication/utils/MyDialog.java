package com.rathana.myapplication.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rathana.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyDialog {

    private Context context;

    public MyDialog(Context context) {
        this.context = context;
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(context);

        //header
        dialogBuilder.setTitle(title);
        dialogBuilder.setIcon(R.drawable.ic_backup_red_24dp);

        //body
        dialogBuilder.setMessage(message);
        dialogBuilder.setCancelable(false);

        //footer
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "OK button click", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Cancel button clicked", Toast.LENGTH_SHORT).show();
            }
        }).setNeutralButton("Do it later!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Neutral button",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog =  dialogBuilder.create();
       alertDialog.show();

    }

    CharSequence[] items= {"Apple","Dell","Asus","Toshiba","MSI","Lenovo"};

    public void createListDialog(String title, final DialogCallback callback){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onItemClicked(items[which]);
                //Toast.makeText(context, ""+items[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();

    }

    public interface DialogCallback{
        void onItemClicked(CharSequence item);
    }

    public interface DialogThemeCallback{
        void onItemClicked(int which);
    }
    public interface DialogMultiChoiceCallback{
        void onItemClicked(List<CharSequence> items);
    }


    CharSequence[] singleChoiceItem ={"Theme Light","Theme Dark"};
     static int isCheckedIndex =0;

    public void singleChoiceListDialog(String title, final DialogThemeCallback callback){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);

        //set Single choice list dialog
        builder.setSingleChoiceItems(singleChoiceItem, isCheckedIndex,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isCheckedIndex=which;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onItemClicked(isCheckedIndex);
            }
        });
        builder.create().show();
    }

    CharSequence[] checkboxItems = {"Boy","Girl","woman","man"};
    static boolean[]  isCheckedItems = {false,false,false,false};
    List<CharSequence> myItems=new ArrayList<>();

    public void checkboxListDialog(String title, final DialogMultiChoiceCallback callback){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);

        builder.setMultiChoiceItems(checkboxItems, isCheckedItems,
                new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    myItems.add(checkboxItems[which]);
                    isCheckedItems[which]=true;
                } else{
                    myItems.remove(checkboxItems[which]);
                    isCheckedItems[which]=false;
                }

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onItemClicked(myItems);
            }
        });

        builder.create().show();

    }



    public void customDialog(String title, final AuthCallback authCallback){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);

        //custom view
        View view = LayoutInflater.from(context)
                .inflate(
                R.layout.custom_dialog_layout,
                null);

        final EditText userName=view.findViewById(R.id.userName);
        final EditText password=view.findViewById(R.id.password);
        //ProgressBar progressBar = view.findViewById(R.id.progressBar);

        builder.setView(view);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                authCallback.getUser(userName.getText().toString(),
                        password.getText().toString()
                );
            }
        });

        builder.create().show();
    }

    public  interface AuthCallback{
        void getUser(String name,String password);
    }

}
