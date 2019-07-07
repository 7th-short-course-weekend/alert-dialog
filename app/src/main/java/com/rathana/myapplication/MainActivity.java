package com.rathana.myapplication;

import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rathana.myapplication.utils.MyDialog;
import com.rathana.myapplication.utils.dialog.MyDialogFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog= new MyDialog(this);
    }

    public void onShowDialogMessage(View view){
        dialog.showMessage("my First dialog",
                "Hello! all. Enjoy this application.");
    }

    public void onShowListDialog(View view){
        dialog.createListDialog("List dialog", new MyDialog.DialogCallback() {
            @Override
            public void onItemClicked(CharSequence item) {
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onShowSingleChoiceDialog(View view){
        dialog.singleChoiceListDialog("Single Choice", new MyDialog.DialogThemeCallback() {
            @Override
            public void onItemClicked(int which) {
//                if(which ==0){
//                    changeTheme(android.R.style.Theme_Light);
//                }else{
//                    changeTheme(android.R.style.ThemeOverlay_Material_Dark);
//                }

               Toast.makeText(MainActivity.this, ""+which, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onShowMultiChoiceDialog(View v){
        dialog.checkboxListDialog("Multi choice Dialog", new MyDialog.DialogMultiChoiceCallback() {
            @Override
            public void onItemClicked(List<CharSequence> items) {
                Toast.makeText(MainActivity.this, ""+items,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onShowCustomDialog(View v){
        dialog.customDialog("Custom Loading", new MyDialog.AuthCallback() {
            @Override
            public void getUser(String name, String password) {
                Toast.makeText(MainActivity.this,
                        "user name= "+name+"\n password="+password,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onShowDialogFragment(View v){
        //call dialog fragment
        MyDialogFragment dialog=new MyDialogFragment();
        dialog.setCallback(new MyDialog.DialogCallback() {
            @Override
            public void onItemClicked(CharSequence item) {
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show(getSupportFragmentManager(),"Tag");
    }

    private void changeTheme(@StyleRes int style){
        getApplication().setTheme(style);
        recreate();
    }
}
