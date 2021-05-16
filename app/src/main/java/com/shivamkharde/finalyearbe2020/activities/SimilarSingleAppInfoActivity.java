package com.shivamkharde.finalyearbe2020.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.shivamkharde.finalyearbe2020.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SimilarSingleAppInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_single_app_info);


        getSupportActionBar().setTitle("Similar App");

        TextView t  = findViewById(R.id.similar_app_single_info_application_description);

        ImageView i = findViewById(R.id.similar_app_single_info_application_description_show_more_btn);

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i.getTag().toString() == "show_more"){
                    t.setMaxLines(Integer.MAX_VALUE);
                    i.setTag("show_less");
                    i.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }else{
                    t.setMaxLines(4);
                    i.setTag("show_more");
                    i.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });
    }
}