package training.weather;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WeatherForecastTest {

	private final static String CITY = "Barcelona";

	@Test
	public void today_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = assertDoesNotThrow(() -> weatherForecast.getCityWeather(CITY, LocalDate.now()));
		System.out.println(forecast);
	}

	@Test
	public void between_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = assertDoesNotThrow(() -> weatherForecast.getCityWeather(CITY, LocalDate.now().plusDays(3)));
		System.out.println(forecast);
	}

	@Test
	public void before_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = assertDoesNotThrow(() -> weatherForecast.getCityWeather(CITY, LocalDate.parse("2022-09-12")));
		System.out.println(forecast);
	}

	@Test
	public void after_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = assertDoesNotThrow(() -> weatherForecast.getCityWeather(CITY, LocalDate.parse("2025-09-30")));
		System.out.println(forecast);
	}
}