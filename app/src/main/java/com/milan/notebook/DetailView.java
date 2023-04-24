package com.milan.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailView extends AppCompatActivity {
    TextView tvTitle , tvDes, tvCategory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getExtras().getString("title");

        setContentView(R.layout.detail_view);
        tvTitle = findViewById(R.id.tit);
        tvDes = findViewById(R.id.des);
        tvCategory = findViewById(R.id.cat);

        tvTitle.setText("Title is: " + title);


    }
}
