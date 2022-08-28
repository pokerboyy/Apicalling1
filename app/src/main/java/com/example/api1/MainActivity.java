package com.example.api1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button getData;
    ImageView ivCourseImg;
    LinearLayout llData;
    TextView tvcourseName, tvcourseMode, tvcourseTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData=findViewById(R.id.getData);

        initialize();

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llData.setVisibility(View.VISIBLE);
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if(response.isSuccessful()) {
                            response.body();

                            String courseName = response.body().getCourseName();
                            String courseimg = response.body().getCourseimg();
                            String courseMode = response.body().getCourseMode();
                            String courseTracks = response.body().getCourseTracks();

                            tvcourseName.setText(courseName);
                            tvcourseMode.setText(courseMode);
                            tvcourseTrack.setText(courseTracks);

                            Picasso.with(MainActivity.this)
                                    .load(courseimg)
                                    .into(ivCourseImg);

//                            Log.e(TAG, "courseName: "+courseName);
//                            Log.e(TAG, "courseimg: "+courseimg);
//                            Log.e(TAG, "courseMode: "+courseMode);
//                            Log.e(TAG, "courseTracks: "+courseTracks);


                        }

                     

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage() );

                    }
                });

            }
        });



        }

    private void initialize() {
        tvcourseName = findViewById(R.id.tvcourseName);
        tvcourseMode = findViewById(R.id.tvcourseMode);
        tvcourseTrack = findViewById(R.id.tvcourseTrack);
        ivCourseImg = findViewById(R.id.ivCourseImg);
        llData = findViewById(R.id.llData);
    }
}
