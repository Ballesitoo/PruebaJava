package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	private static JSONArray dailyResults, weatherCodeResults;
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String getCityWeather(String city, LocalDate datetime) throws IOException {

		//Si la fecha proporcionada es null se pondra la fecha actual
		if (datetime == null) {
			datetime = LocalDate.now();
		}

		//Si la fecha proporcionada esta dentro del rango de una semana desde hoy, entrara
		if (datetime.isBefore(LocalDate.now().plusDays(7))
				&& datetime.isAfter(LocalDate.now().minusDays(1))) {

			httpRequest(city);

			for (int i = 0; i < dailyResults.length(); i++) {
				if (datetime.format(dateTimeFormatter).equals(dailyResults.get(i).toString())) {
					return ForecastEnum.getEnumByCode(weatherCodeResults.getInt(i)).getDescription();
				}
			}
		}

		//Si no entra manda un mensaje de que debe poner una fecha entre hoy y dentro de una semana.
		return "Proporciona una fecha desde " + LocalDate.now() + " hasta " + LocalDate.now().plusDays(6);
	}

	private static void httpRequest(String city) {
		try {
			HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
			HttpRequest req = rf.buildGetRequest(new GenericUrl("https://geocode.xyz/" + city + "?json=1"));
			String r = req.execute().parseAsString();
			JSONObject object = new JSONObject(r);
			String longt = object.get("longt").toString();
			String latt = object.get("latt").toString();
			rf = new NetHttpTransport().createRequestFactory();
			req = rf.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" +
					latt + "&longitude=" + longt + "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin"));
			r = req.execute().parseAsString();
			dailyResults = new JSONObject(r).getJSONObject("daily").getJSONArray("time");
			weatherCodeResults = new JSONObject(r).getJSONObject("daily").getJSONArray("weathercode");

		} catch (IOException ioException) {
			httpRequest(city);
		}
	}
}
