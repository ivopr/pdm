package com.pdm.rivaldalocaliza.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pdm.rivaldalocaliza.R;

public class CarDetailsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_details);

		Bundle bundle = getIntent().getExtras();

		String carId = bundle.getString("carId");

		Log.d("carId", carId);
	}
}