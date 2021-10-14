package com.savijan.financemyself;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.savijan.financemyself.Adapters.MyAdapter;
import com.savijan.financemyself.Database.DatabaseManager;
import com.savijan.financemyself.Database.UserItemDB;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_list)
public class ListActivity extends AppCompatActivity {

    List<UserItemDB> userItemDBList = new ArrayList<>();
    private Context context;
    private MyAdapter mAdapter;

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userItemDBList = getAllUsers();
        mAdapter = new MyAdapter(userItemDBList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    private List<UserItemDB> getAllUsers() {
        return DatabaseManager.getInstance(getApplicationContext()).getAllUsers();
    }

    public void presentAlert(String name, String age, String index) {

        final EditText nameEditText = new EditText(this);
        final EditText ageEditText = new EditText(this);

        nameEditText.setText(name);
        ageEditText.setText(age);
        nameEditText.setWidth(100);
        ageEditText.setWidth(100);

        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(nameEditText);
        linearLayout.addView(ageEditText);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit User")
                .setView(linearLayout)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserItemDB userItemDB = new UserItemDB();
                        userItemDB.setName(nameEditText.getText().toString());
                        userItemDB.setAge(ageEditText.getText().toString());
                        userItemDB.setIndex(index);
                        DatabaseManager.getInstance(getApplicationContext()).insertUserItam(userItemDB, true);
                        mAdapter.updateUserList(getAllUsers());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

    }
}