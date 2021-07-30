package com.pdm.rivaldalocaliza.api.services;

import com.pdm.rivaldalocaliza.models.Car;
import com.pdm.rivaldalocaliza.models.Category;
import com.pdm.rivaldalocaliza.models.MyLoan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RentService {
	/**
	 * Busca e retorna todos os carros que ainda não foram alugados
	 * @return List<Car>
	 */
	@GET("cars/available")
	Call<List<Car>> listAvailableCars();

	/**
	 * Busca e retorna um carro com o id igual ao fornecido
	 * @param carId identificador do carro buscado
	 * @return Car
	 */
	@GET("cars/{carId}")
	Call<Car> getCar(@Path("carId") String carId);

	/**
	 * Busca e retorna o carro alugado pelo usuário
	 * @param userToken identificador do carro buscado
	 * @return List<MyLoan>
	 */
	@GET("rentals/user")
	Call<List<MyLoan>> getMyLoanedCar(@Header("Authorization") String userToken);
}
