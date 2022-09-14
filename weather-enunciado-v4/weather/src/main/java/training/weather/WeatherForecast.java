package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String getCityWeather(String city, LocalDate datetime) throws IOException {

		//Si la fecha proporcionada es null se pondrá la fecha actual.
		if (datetime == null) {
			datetime = LocalDate.now();
		}

		//Si la fecha proporcionada esta dentro del rango de una semana desde hoy, entrara
		if (datetime.isBefore(LocalDate.now().plusDays(7)) && datetime.isAfter(LocalDate.now().minusDays(1))) {

			//Llamada a HttpForecast dónde se hacen las requests y se guardan en un JSONObject.
			JSONObject resultObject =  HttpForecast.httpRequest(city);

			//A partir de este JSONObject se guardan en dos JSONArrays tanto los días que tiene el tiempo como sus correspondientes códigos de que tiempo harán esos días.
			JSONArray dailyResults = resultObject.getJSONObject("daily").getJSONArray("time");
			JSONArray weatherCodeResults = resultObject.getJSONObject("daily").getJSONArray("weathercode");

			//For para recorrer cualquier JSONArray creado con el método anterior.
			for (int i = 0; i < dailyResults.length(); i++) {
				//A la que llegue al día que se ha puesto para la ejecución del programa,
				//pasará el código de ese día a ForecastEnum para que devuelva que tiempo hará el día pertinente.
				if (datetime.format(dateTimeFormatter).equals(dailyResults.get(i).toString())) {
					return "The weather forecast in " + city + " the day " + datetime + " is " + ForecastEnum.getEnumByCode(weatherCodeResults.getInt(i)).getDescription();
				}
			}
		}

		//Si no entra manda un mensaje de que debe poner una fecha entre hoy y dentro de una semana.
		return "Proporciona una fecha entre los días " + LocalDate.now() + " y " + LocalDate.now().plusDays(6);
	}

}