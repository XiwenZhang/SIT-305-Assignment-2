package com.example.user.SIT305Assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutActivity extends Activity {
    TextView t1;
    TextView t2;
    Button back;


    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);


        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        //set text and hyperlink text
        t2.setText(" Images：\n 1. \tName:");
        t2.append(Html.fromHtml("<a href ='http://www.ynbidding.net/tiyu/yaowen/689580.html'> icon.png</a>"
        ));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: 风中的自由\n\n2.\tName: ");
        t2.append(Html.fromHtml("<a href ='https://m.baidu.com/tc?from=bd_graph_mm_tc&srd=1&dict=20&src=http%3A%2F%2Fwww.tuzhan.com%2Fkwong%2F6bd41b31129b343b.html&sec=1557638282&di=44368bead6f0c77f\n'> gtr.jpg</a>"));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: TuZhan\n\n3.\tName: ");
        t2.append(Html.fromHtml("<a href ='http://auto.sina.com.cn/photo/78thgmslivep/227021.shtml'> bugattiveyron.jpg</a>"));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: sina\n\n4.\tName: ");
        t2.append(Html.fromHtml("<a href ='https://www.icauto.com.cn/baike/65/659183.html?1556617671'> santana.jpg.jpg</a>"));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: ICAUTO.com.cn\n\n5.\tName: ");
        t2.append(Html.fromHtml("<a href ='https://www.icauto.com.cn/baike/65/659183.html?1556617671'> background.jpg</a>"));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: pptjiayuan.com.cn\n\n\n  Fonts:\n  Nmae: ");
        t2.append(Html.fromHtml("<a href ='http://font.chinaz.com/190424411950.htm'> ftt.otf</a>"));
        t2.append("\n\tLicense type: Public Domain\n\tAuthor: 魔幻琪琪 ");

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to page
                Intent i = new Intent(AboutActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        //set moving hyperlink
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
