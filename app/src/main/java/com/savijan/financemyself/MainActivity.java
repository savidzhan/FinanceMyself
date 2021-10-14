package com.savijan.financemyself;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.savijan.financemyself.Database.DatabaseManager;
import com.savijan.financemyself.Database.UserItemDB;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.this.getClass().getSimpleName();

//    @ViewById(R.id.myInput)
//    EditText myInput;
//
//    @ViewById(R.id.myTextView)
//    TextView textView;
//
//    @Click
//    void myButton() {
//        String name = myInput.getText().toString();
//        textView.setText("Hello "+name);
//    }

    @ViewById(R.id.txt_index)
    EditText txt_index;

    @ViewById(R.id.txt_name)
    EditText txt_name;

    @ViewById(R.id.txt_age)
    EditText txt_age;

    @Click
    void btn_submit(){
        addUser();
    }

    private void addUser() {

        String name = txt_name.getText().toString().trim();
        String age = txt_age.getText().toString().trim();
        String index = txt_index.getText().toString().trim();

        if((name != null && !name.isEmpty()) && (index != null && !index.isEmpty()) && (age != null && !age.isEmpty())){
            UserItemDB userItemDB = new UserItemDB();
            userItemDB.setIndex(index);
            userItemDB.setName(name);
            userItemDB.setAge(age);
            addUserToDB(userItemDB);
            userItemDBList = getAllUsers();

            for (UserItemDB itemDB : userItemDBList){
                Log.i(TAG, "name: " + itemDB.getName());
            }
        }

    }

    private List<UserItemDB> getAllUsers() {
        return DatabaseManager.getInstance(getApplicationContext()).getAllUsers();
    }


    private int addUserToDB(UserItemDB userItemDB) {

        int isSuccess;
        isSuccess = DatabaseManager.getInstance(getApplicationContext()).insertUserItam(userItemDB, false);
        if(isSuccess == 0){
            Toast.makeText(getApplicationContext(), "Save User", Toast.LENGTH_SHORT).show();
        }else if(isSuccess == 1){
            Toast.makeText(getApplicationContext(), "User with this id exist", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "User adding failed", Toast.LENGTH_SHORT).show();
        }
        return isSuccess;
    }

    @Click
    void btn_view_list(){
        Intent intent = new Intent(MainActivity.this, ListActivity_.class);
        startActivity(intent);
    }

    List<UserItemDB> userItemDBList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}