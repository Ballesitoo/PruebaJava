package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void today_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Barcelona", LocalDate.now());
		System.out.println(forecast);
	}

	@Test
	public void between_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Barcelona", LocalDate.parse("2022-09-17"));
		System.out.println(forecast);
	}

	@Test
	public void before_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Barcelona", LocalDate.parse("2022-09-12"));
		System.out.println(forecast);
	}

	@Test
	public void after_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Barcelona", LocalDate.parse("2022-09-30"));
		System.out.println(forecast);
	}
}