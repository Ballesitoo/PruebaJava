# Documentación

- ¿Qué has empezado implementando y por qué?

  Lo primero que he tenido que implementar era que saliera el resultado como tal de si era nublado, soleado, etc. Ya que cuándo no me daba el error de que ha habido muchas peticiones, 
  me daba el error de que no se podía castear de double a int al llamar al método .get(i) de la variable,
  para solucionarlo intenté hacer casting de diferentes formas, pero luego me fijé en los métodos que tenía la propia variable y me dí cuenta
  de que hay un método que te devuelve el int directamente llamado getInt(), de esta manera ya me salía el pronóstico del tiempo.


- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?

  Uno de los problemas principales que tenía era el que he comentado en la pregunta anterior, también me encontré algo que más que un problema es que era muy tedioso y era poner una 
  fecha que no fuera la de hoy, así que cambié de Date a LocalDate, ya que de esta forma no hacía falta hacer gran cosa una vez montado, solo poner la fecha respetando el formato del 
  ejemplo y ya esta.

  Otro problema que ví es que podías poner cualquier fecha y si no estaba dentro de los 7 días de la fecha de hoy te sale en blanco, ya que está montado de esta forma, lo que he
  hecho es hacer un control que debe ser desde hoy hasta dentro de una semana, si no es asi te salta un mensaje de que debes poner una fecha entre esos días.

  Para solucionar el primer problema que he comentado antes, lo que he hecho es poner toda la petición de internet en un try/catch y si da algún error vuelve a ejecutarse, así hasta que consiga los resultados.


- ¿Qué componentes has creado y por qué?
  
  He creado una clase llamada HttpForecast, en esa clase es llevado todo lo relacionado con las peticiones de internet para que así sea más legible y que quede ForecastEnum el que diga el pronóstico que es, HttpForecast el que hace las peticiones a internet y WeatherForecast el intermediario entre los dos y maneje los resultados como quiera.


- ¿Has utilizado streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?

  No he usado nada de eso. La verdad es que no sabía que era la programación funcional, he mirado un poco que es y no tiene para nada mala pinta, por lo que he visto usa mucho lambda que sirve para no usar métodos anónimos, también lo hace algo más legible haciendo que se vea más lo que hace que cómo lo hace. Por lo que he visto parece que tiene mucha "chicha" y se pueden hacen muchas virguerías con este tipo de programación.


- ¿Qué piensas del rendimiento de la aplicación?
  
  En general diría que está bien, lo que algo malo que le veo es que como la api tiene un máximo de 2 peticiones por segundo y eso hace que por cómo lo he hecho si en el momento de ejecutarlo hay muchas peticiones, tardará más la ejecución.


- ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?
  
  Si ya fueran muchas peticiones quizás se podría mirar de hacer algo con hilos para que no fuera tan cargado el sistema, así el rendimiento sería mejor seguro.


- ¿Cuánto tiempo has invertido para implementar la solución?

  En total diría que han sido unas 4-5 horas más o menos.


- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización? 
  
  Yo diría que si es para un escenario real siempre va bien tener todo lo mejor refactorizado que puedas, más que nada por si en 4-5 meses debes de mirarlo de nuevo te sea mucho mejor el entender cómo habías hecho todo.