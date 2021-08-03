package com.pdm.rivaldalocaliza.activities;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pdm.rivaldalocaliza.R;
import com.pdm.rivaldalocaliza.api.API;
import com.pdm.rivaldalocaliza.api.services.RentService;
import com.pdm.rivaldalocaliza.models.MyLoan;
import com.pdm.rivaldalocaliza.room.AppDb;
import com.pdm.rivaldalocaliza.room.Database;
import com.pdm.rivaldalocaliza.room.models.UserSession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLoanedCarActivity extends AppCompatActivity {
	AppDb database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_loaned_car);

		database = Database.getDatabase(this);

		API api = new API();
		RentService rentService = api.getRentService();
		UserSession userSession = database.getUserSessionDAO().getSessions().get(0);

		TextView category = findViewById(R.id.my_car_category);
		TextView specifications = findViewById(R.id.my_car_specs);
		TextView model = findViewById(R.id.my_car_model);
		TextView brand = findViewById(R.id.my_car_brand);
		TextView driver = findViewById(R.id.my_car_driver);
		TextView driver_license = findViewById(R.id.my_car_driver_license);
		TextView plate = findViewById(R.id.my_car_plate);
		TextView description = findViewById(R.id.my_car_description);
		TextView daily_rate = findViewById(R.id.my_car_daily_price);
		TextView fine = findViewById(R.id.my_car_daily_fine);
		TextView return_date = findViewById(R.id.my_car_return_date);
		TextView start_date = findViewById(R.id.my_car_start_date);

		(rentService.getMyLoanedCar("Bearer " + userSession.getToken()))
			.enqueue(new Callback<MyLoan>() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onResponse(Call<MyLoan> call, Response<MyLoan> response) {
				if (response.body() != null) {
					MyLoan loan = response.body();

					StringBuilder specs = new StringBuilder("Não há informações");

					if (loan.getCar().getSpecifications().size() > 0) {
						for (int i = 0; i < loan.getCar().getSpecifications().size(); i++) {
							if (i == 0) {
								specs = new StringBuilder(loan.getCar().getSpecifications().get(i).getName());
							} else {
								specs.append(", ").append(loan.getCar().getSpecifications().get(i).getName());
							}
						}
					}

					model.setText(loan.getCar().getName());
					brand.setText(loan.getCar().getBrand());
					plate.setText(loan.getCar().getLicense_plate());
					specifications.setText(specs);
					category.setText(loan.getCar().getCategory().getName());
					description.setText(loan.getCar().getDescription());
					daily_rate.setText("R$ " + loan.getCar().getDaily_rate() + "/d");
					fine.setText("R$ " + loan.getCar().getFine_amount() + "/d");
					start_date.setText(loan.getStart_date());
					return_date.setText(loan.getExpected_return_date());

					FloatingActionButton goToAvailableCarsFAB = findViewById(R.id.loan_new_car);

					goToAvailableCarsFAB.setOnClickListener(event -> {
						Intent intent = new Intent(MyLoanedCarActivity.this, AvailableCarsActivity.class);

						startActivity(intent);
					});
				} else {
					setContentView(R.layout.activity_my_loaned_car_null);

					FloatingActionButton goToAvailableCarsFAB = findViewById(R.id.loan_new_car_null);

					goToAvailableCarsFAB.setOnClickListener(event -> {
						Intent intent = new Intent(MyLoanedCarActivity.this, AvailableCarsActivity.class);

						startActivity(intent);
					});
				}
			}

			@Override
			public void onFailure(Call<MyLoan> call, Throwable t) {
				Log.d("reqErr", t.getMessage());

				Toast.makeText(MyLoanedCarActivity.this, "Não foi possível recuperar seu carro alugado", Toast.LENGTH_LONG).show();
			}
		});
	}
}