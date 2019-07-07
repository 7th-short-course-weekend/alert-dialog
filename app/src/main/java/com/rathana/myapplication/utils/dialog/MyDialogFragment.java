package com.rathana.myapplication.utils.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.rathana.myapplication.utils.MyDialog;

public class MyDialogFragment  extends DialogFragment {

    String message;
    CharSequence[] items= {"Apple","Dell","Asus","Toshiba","MSI","Lenovo"};

    MyDialog.DialogCallback callback;
    public void setCallback(MyDialog.DialogCallback dialogCallback){
        this.callback=dialogCallback;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder.setTitle("Dialog Fragment");
        //builder.setMessage("My alert Dialog message");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(callback!=null){
                    callback.onItemClicked(items[which]);
                }
            }
        });
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

        return builder.create();

    }


}
