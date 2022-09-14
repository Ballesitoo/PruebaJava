package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONObject;

import java.io.IOException;

public class HttpForecast {

    private static JSONObject jsonObject;

    public static JSONObject httpRequest(String city) {
        try {
            //Primera request pasando en la URL la ciudad que se ha escrito.
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("https://geocode.xyz/" + city + "?json=1"));
            String requestResult = request.execute().parseAsString();
            //Se guarda en un objeto JSONObject toda la request.
            jsonObject = new JSONObject(requestResult);

            //Se guardan en dos strings tanto la longitud como la latitud del objecto guardado de la request.
            String longt = jsonObject.get("longt").toString();
            String latt = jsonObject.get("latt").toString();

            //Segunda request pasando la latitud y longitud guardadas de antes.
            requestFactory = new NetHttpTransport().createRequestFactory();
            request = requestFactory.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" +
                    latt + "&longitude=" + longt + "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin"));
            requestResult = request.execute().parseAsString();

            //Se sobreescribe el JSONObject con el nuevo resultado dónde ya están los días y los códigos del tiempo que queremos.
            jsonObject = new JSONObject(requestResult);

        } catch (IOException ioException) {
            //Sí da algún error en la ejecución del mismo (muchas peticiones), se volverá a hacer la llamada hasta que vaya bien.
            httpRequest(city);
        }

        //Devuelve el objeto ya con los resultados para que se puedan modificar.
        return jsonObject;
    }
}