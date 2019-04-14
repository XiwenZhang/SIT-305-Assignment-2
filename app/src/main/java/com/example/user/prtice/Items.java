package com.example.user.prtice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Items extends Activity {

    private ListView sell;
    private ListView buy;
    private ArrayList<ItemAdapter> items1;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);



    }


    @Override
    protected void onResume() {
        super.onResume();

        sell =findViewById(R.id.marketboard);
        buy = findViewById(R.id.itemsboard);

    }
}
