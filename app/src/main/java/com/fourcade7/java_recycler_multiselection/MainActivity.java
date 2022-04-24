package com.fourcade7.java_recycler_multiselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    UserAdapter userAdapter;
    EditText editText;
    RelativeLayout relativeLayout;
    TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview1);
        editText=findViewById(R.id.edittextsearch);
        relativeLayout=findViewById(R.id.relativelay2);
        textView=findViewById(R.id.textviewcounter);
        textView2=findViewById(R.id.textviewcounter2);
        userArrayList=new ArrayList<>();
        userArrayList.add(new User("Invoker",R.drawable.prr));
        userArrayList.add(new User("Axe",R.drawable.axe));
        userArrayList.add(new User("Dota",R.drawable.d5));
        userArrayList.add(new User("Juggernaut",R.drawable.jager));
        userArrayList.add(new User("Storm Spirit",R.drawable.torm));
        userArrayList.add(new User("Pudge",R.drawable.pubg));

        userAdapter=new UserAdapter(MainActivity.this,userArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(userAdapter);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            filter(editable.toString());
            }
        });

    }

    public  void filter(String text){
        ArrayList<User> users=new ArrayList<>();

        for (User item:userArrayList){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                users.add(item);
            }
        }

       userAdapter.filterlist(users);
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i < userArrayList.size() ; i++) {
            userArrayList.get(i).setSelect(false);
            userAdapter.a=0;
            userAdapter.notifyDataSetChanged();
            relativeLayout.setVisibility(View.INVISIBLE);
            textView.setText("Hi");


        }
    }
}