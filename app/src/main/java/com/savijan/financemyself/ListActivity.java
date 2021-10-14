package com.savijan.financemyself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.savijan.financemyself.Database.UserItemDB;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_list)
public class ListActivity extends AppCompatActivity {

    List<UserItemDB> userItemDBList = new ArrayList<>();
    private Context context;

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void presentAlert(String name, String age, String index) {

    }
}