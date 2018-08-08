package com.example.administrator.commonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        List<Book>books=new ArrayList<>();
        List<App>apps=new ArrayList<>();

        apps.add(new App(R.mipmap.iv_icon_baidu,"百度"));
        apps.add(new App(R.mipmap.iv_icon_douban,"豆瓣"));
        apps.add(new App(R.mipmap.iv_icon_zhifubao,"支付宝"));

        books.add(new Book("《第一行代码Android》","郭霖"));
        books.add(new Book("《Android群英传》","徐宜生"));
        books.add(new Book("《Android开发艺术探索》","任玉刚"));

        CommonAdapter<App>appAdapter=new CommonAdapter<App>(apps,R.layout.app_item_list) {
            @Override
            public void bindView(ViewHolder holder, App itemObj) {
                holder.setImageSource(R.id.app_image_view,itemObj.imageSourceId);
                holder.setText(R.id.app_name,itemObj.name);
            }
        };

        CommonAdapter<Book>bookAdapter=new CommonAdapter<Book>(books,R.layout.book_item_list) {
            @Override
            public void bindView(ViewHolder holder, Book itemObj) {
                holder.setText(R.id.book_name,itemObj.name);
                holder.setText(R.id.book_author,itemObj.author);
            }
        };

        ListView listViewApp=findViewById(R.id.listViewApp);
        ListView listViewBook=findViewById(R.id.listViewBook);
        listViewApp.setAdapter(appAdapter);
        listViewBook.setAdapter(bookAdapter);
    }
}
