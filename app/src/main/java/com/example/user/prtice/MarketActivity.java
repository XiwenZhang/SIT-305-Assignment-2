package com.example.user.prtice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;

public class MarketActivity extends Activity {


    private ListView marketlist;
    private ListView itemlist;

    private Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        marketlist = findViewById(R.id.marketboard);
        itemlist = findViewById(R.id.itemsboard);


    }
}
