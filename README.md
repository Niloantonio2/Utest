# Utest

Se realiza la automatización en la pagina [Utest](https://utest.com/) de los prcesos de Registro y Login, gestionando dependencicias con [Gradle](https://gradle.org/) en su version 5.4.1, [Java](https://www.java.com/es/) jdk en su version 1.8.0_221, [SerenityBDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html), [Cucumber](https://cucumber.io/) y patron de diseño Screenplay, las clases se llaman de acuerdo a UpperCamiCase y las variables lowerCamiCase.


## Data
Se utilizan datos estaticos enviados mediante el feature hecho con  Cucumber, escrito en lenguaje [Gerking](https://cucumber.io/docs/gherkin/); para el correo se crea scrit que genera dato de manera dinamica en cada ejecucion, sin embargo se deja documentada la manera de como utilizar el dato del feature.

## Estructura Codigo Fuente

La estructura del codigo fue realizada con Screenplay de la siguiente forma:
<table>
<tr>
  <th>Tasks</th>
  <td>
    <h6>Contiene todas las tareas que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Interactions</th>
  <td>
    <h6>Contiene todas las interaciones que se ejecutaran en la automatizacion</h6> 
  </td>
</tr>
  <tr>
  <th>Models</th>
  <td>
    <h6>Contiene todos los modelos que se utilizaran para la construccion de la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>interfas de usuario</th>
  <td>
    <h6>Contiene todos los elementos de la interface usuario mapeados en la pagina</h6>
  </td>
</tr>
  <tr>
  <th>Drivers</th>
  <td>
    <h6>Contiene 3  drivers de los navegadores Chrome, Edge y firefox</h6>
  </td>
</tr>
  <tr>
  <th>Runners</th>
  <td>
    <h6>Contiene el ejecutor de la prueba automatizada</h6>
  </td>
</tr>
  <tr>
  <th>Steps Definitions</th>
  <td>
    <h6>Contiene todos los pasos de la ejecucion de la prueba automatizada</h6>
  </td>
</tr>
  <tr>
  <th>Features</th>
  <td>
    <h6>Contiene todos los esenarios codificados en lenguaje Gherking</h6>
  </td>
</tr>
</table>

#### Tasks

##### AbrirPaginaUtestRegistro

Realiza el inicio de sesion al sistema de la pagina de la Libreria Nacional, esta tarea implementa la interfaz Task y sobreescribe su metodo, tambien recibe dos parametros de tipo String y tiene dos metodos de tipo `IniciarSesionLibreriaNacional` que asignaran los valores a las variables creadas.
```java
    public class AbrirPaginaUtestRegistro implements Task {

    private PaginaUtest paginaUtest;

    public static AbrirPaginaUtestRegistro laPagina() {
        return Tasks.instrumented(AbrirPaginaUtestRegistro.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(

                Click.on(PaginaUtest.BOTON_JOIN_TODAY)
        );
        
    }
}
```
##### LlenarFormularioInfoPersonal
```java
public class LlenarFormularioInfoPersonal implements Task {
    private List<UtestDatosRegistro> datos;
    String correo;
    Faker faker = new Faker(new Locale("en-u$"));

    public LlenarFormularioInfoPersonal(List<UtestDatosRegistro> datos) {
        this.datos = datos;
        this.correo = faker.internet().emailAddress();
    }

    public static LlenarFormularioInfoPersonal lapagina(List<UtestDatosRegistro> datos) {
        return Tasks.instrumented(LlenarFormularioInfoPersonal.class, datos);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(PaginaInfoPersonal.BOTON_DIRECCION),
                Enter.theValue(datos.get(0).getStrNombre()).into(PaginaInfoPersonal.TXT_NOMBRE),
                Enter.theValue(datos.get(0).getStrAprllido()).into(PaginaInfoPersonal.TXT_APELLIDO),

                /*======================================================================================================
                * Dado que en la pagina de registro de Utest no se puede  repetir correos para registro,
                * se automatiza la generacion de este correo de manera aletoria para cumplir con los
                * principio FIRST y SOLID y tener un framework de automatizacion repetible, reutilizable y mantenible
                *=======================================================================================================
                * */

                Enter.theValue(correo).into(PaginaInfoPersonal.TXT_CORREO),

                /*======================================================================================================
                *Para recibir el dato escrito desde el feature se debe remplazar la tarea anterio por la siguiente:
                *Enter.theValue(datos.get(0).getStrCorreo()).into(PaginaInfoPersonal.TXT_CORREO),
                *teniendo en cuenta que para cada ejecucion se debera cambiar el correo
                * ======================================================================================================
                 */

                SelectFromOptions.byVisibleText(datos.get(0).getStrMesNacimiento()).from(PaginaInfoPersonal.MES_NACIMIENTO),
                Esperar.estosSegundos(1),
                SelectFromOptions.byVisibleText(datos.get(0).getStrDiaNacimiento()).from(PaginaInfoPersonal.DIA_NACIMIENTO),
                Esperar.estosSegundos(1),
                SelectFromOptions.byVisibleText(datos.get(0).getStrAnoNacimiento()).from(PaginaInfoPersonal.ANO_NACIMIENTO),
                Esperar.estosSegundos(1),
                Click.on(PaginaInfoPersonal.BOTON_DIRECCION)

                );

    }
}
```

##### LlenarFormularioInfoDireccion
```java
public class LlenarFormularioInfoDireccion implements Task {

    private List<UtestDatosRegistro> datos;

    public LlenarFormularioInfoDireccion(List<UtestDatosRegistro> datos) {
        this.datos = datos;
    }

    public static LlenarFormularioInfoDireccion lapagina(List<UtestDatosRegistro> datos) {
        return Tasks.instrumented(LlenarFormularioInfoDireccion.class, datos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(PaginaInfoDireccion.BOTON_DISPOSITVOS),
                Enter.theValue(datos.get(0).getStrCiudad()).into(PaginaInfoDireccion.CIUDAD),
                Hit.the(Keys.ARROW_DOWN).into(PaginaInfoDireccion.CIUDAD),
                Hit.the(Keys.ENTER).into(PaginaInfoDireccion.CIUDAD),
                Enter.theValue(datos.get(0).getStrCodigoPostal()).into(PaginaInfoDireccion.CODIGO_POSTAL),
                Click.on(PaginaInfoDireccion.CONTENEDOR_PAIS),
                Enter.theValue(datos.get(0).getStrPais()).into(PaginaInfoDireccion.PAIS),
                Click.on(PaginaInfoDireccion.BOTON_DISPOSITVOS));

    }
}
```

##### LlenarFormularioInfoDispositivos
```java
public class LlenarFormularioInfoDispositivos implements Task {

    private List<UtestDatosRegistro> datos;

    public LlenarFormularioInfoDispositivos(List<UtestDatosRegistro> datos) {
        this.datos = datos;
    }

    public static LlenarFormularioInfoDispositivos lapagina(List<UtestDatosRegistro> datos) {
        return Tasks.instrumented(LlenarFormularioInfoDispositivos.class, datos);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(PaginaInfoDispositivos.BOTON_PASO_FINAL),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_COMPUTADOR),
                Enter.theValue(datos.get(0).getStrComputador()).into(PaginaInfoDispositivos.COMPUTADOR).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_VERSION),
                Enter.theValue(datos.get(0).getStrVersionComputador()).into(PaginaInfoDispositivos.VERSION).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_LENGUAJE),
                Enter.theValue(datos.get(0).getStrLenguajeComputador()).into(PaginaInfoDispositivos.LENGUAJE).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_DISPOSITIVO_MOVIL),
                Enter.theValue(datos.get(0).getStrDispositivoMovil()).into(PaginaInfoDispositivos.DISPOSITIVO_MOVIL).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_MODELO),
                Enter.theValue(datos.get(0).getStrModeloDispositivoMovil()).into(PaginaInfoDispositivos.MODELO).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.CONTENEDOR_SISTEMA_OPERATIVO_MOVIL),
                Enter.theValue(datos.get(0).getStrSistemaOperativoMovil()).into(PaginaInfoDispositivos.SISTEMA_OPERATIVO_MOVIL).thenHit(ARROW_DOWN,ENTER),
                Click.on(PaginaInfoDispositivos.BOTON_PASO_FINAL)
        );
    }
}

```

##### LlenarFormularioInfoFinal
```java
public class LlenarFormularioInfoFinal implements Task {
    private List<UtestDatosRegistro> datos;

    public LlenarFormularioInfoFinal(List<UtestDatosRegistro> datos) {
        this.datos = datos;
    }

    public static LlenarFormularioInfoFinal lapagina(List<UtestDatosRegistro> datos) {
        return Tasks.instrumented(LlenarFormularioInfoFinal.class, datos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(PaginainfoFinal.BOTON_COMPLETAR_FORMULARIO),
                Enter.theValue(datos.get(0).getStrClave()).into(PaginainfoFinal.TXT_CLAVE),
                Enter.theValue(datos.get(0).getStrClave()).into(PaginainfoFinal.TXT_CONFIRMAR_CLAVE),
                Click.on(PaginainfoFinal.CHECKBOX_MANTENER_INFORMADO),
                Click.on(PaginainfoFinal.CHECKBOX_TERMINOS_USO),
                Click.on(PaginainfoFinal.CHECKBOX_PRIVACIDA),
                Click.on(PaginainfoFinal.BOTON_COMPLETAR_FORMULARIO),
                Esperar.estosSegundos(10)
        );

    }
}

```


### Interactions

#### Esperar

Realiza la espera implicita, esta tarea implementa la interfaz Interaction y sobreescribe su metodo, tambien recibe un parametro de tipo int.

```java
public class Esperar implements Interaction {

    private int seconds;

    public Esperar(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DriverRemoteBrowser.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static Performable estosSegundos(int seconds)
    {
        return Instrumented.instanceOf(Esperar.class).withProperties(seconds);
    }
}
```

### Models

#### UtestDatosRegistro

Esta clase crea un objeto de tipo usuario que contiene multiples variables de tipo string, el constructor y los getter y setter correspondiente a cada variable.

```java
public class UtestDatosRegistro {
    private String strNombre, strAprllido, strCorreo, strDiaNacimiento, strMesNacimiento, strAnoNacimiento,
            strCiudad, strCodigoPostal, strPais, strComputador, strVersionComputador, strLenguajeComputador, strDispositivoMovil,
            strModeloDispositivoMovil, strSistemaOperativoMovil, strClave, strMensajeFinal;
    private String  StrEmail, StrContrasena;

    public String getStrEmail() {
        return StrEmail;
    }

    public String getStrContrasena() {
        return StrContrasena;
    }

    public String getStrMensajeFinal() {
        return strMensajeFinal;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public String getStrAprllido() {
        return strAprllido;
    }

    public String getStrCorreo() {
        return strCorreo;
    }

    public String getStrDiaNacimiento() {
        return strDiaNacimiento;
    }

    public String getStrMesNacimiento() {
        return strMesNacimiento;
    }

    public String getStrAnoNacimiento() {
        return strAnoNacimiento;
    }

    public String getStrCiudad() {
        return strCiudad;
    }

    public String getStrCodigoPostal() {
        return strCodigoPostal;
    }

    public String getStrPais() {
        return strPais;
    }

    public String getStrComputador() {
        return strComputador;
    }

    public String getStrVersionComputador() {
        return strVersionComputador;
    }

    public String getStrLenguajeComputador() {
        return strLenguajeComputador;
    }

    public String getStrDispositivoMovil() {
        return strDispositivoMovil;
    }

    public String getStrModeloDispositivoMovil() {
        return strModeloDispositivoMovil;
    }

    public String getStrSistemaOperativoMovil() {
        return strSistemaOperativoMovil;
    }

    public String getStrClave() {
        return strClave;
    }
}
```

### Interfas de usuario

#### PaginaUtest

Esta clase contiene todos los mapeos de los elementos de la pagina de la pagina principal Utest.

```java
public class PaginaUtest extends PageObject {

    public static final Target BOTON_JOIN_TODAY = Target.the("Boton para iniciar usuario").located(By.className("unauthenticated-nav-bar__sign-up"));
    public static final Target BOTON_Login = Target.the("Boton para ingresar sesion").located(By.xpath("//*[contains(text(),'Log In')]"));

}
```

#### LlenarFormularioInfoPersonal

Esta clase contiene todos los mapeos de los elementos de la pagina del proceso de creacion de usuario paso 1  formulario de datos personales.

```java
public class PaginaInfoPersonal {

    public static final Target TXT_NOMBRE = Target.the("Campo para nombre de usuario").located(By.id("firstName"));
    public static final Target TXT_APELLIDO = Target.the("Campo para apellido de usuario").located(By.id("lastName"));
    public static final Target TXT_CORREO = Target.the("Campo para correo de usuario").located(By.id("email"));
    public static final Target DIA_NACIMIENTO = Target.the("Campo para dia de nacimiento de usuario").located(By.id("birthDay"));
    public static final Target MES_NACIMIENTO = Target.the("Campo para mes de nacimiento de usuario").located(By.id("birthMonth"));
    public static final Target ANO_NACIMIENTO = Target.the("Campo para año de usuario").located(By.id("birthYear"));
    public static final Target BOTON_DIRECCION = Target.the("Boton para ir a seccion de direccion").located(By.xpath("//a[@class=\"btn btn-blue\"]"));
    
}
```
#### LlenarFormularioInfoDireccion

Esta clase contiene todos los mapeos de los elementos de la pagina del proceso de creacion de usuario paso 2 informacion de residencia.

```java
public class PaginaInfoDireccion {
    public static final Target CIUDAD = Target.the("Campo ciudad del usuario").located(By.id("city"));
    public static final Target CODIGO_POSTAL = Target.the("Campo Codigo Postal del usuario").located(By.id("zip"));
    public static final Target CONTENEDOR_PAIS = Target.the("Contenedor pais").located(By.name("countryId"));
    public static final Target PAIS = Target.the("campo pais del usuario").located(By.xpath("//input[@placeholder=\"Select a country\"]"));
    public static final Target BOTON_DISPOSITVOS = Target.the("Boton paso siguiente").located(By.xpath("//a[@class=\"btn btn-blue pull-right\"]"));

}
```

#### LlenarFormularioInfoDispositivos

Esta clase contiene todos los mapeos de los elementos de la pagina del proceso de creacion de usuario en paso 3. Informacion de dispositivos.

```java
public class PaginaInfoDispositivos {

    public static final Target CONTENEDOR_COMPUTADOR = Target.the("Contenedor de computador del usuario").located(By.name("osId"));
    public static final Target COMPUTADOR = Target.the("computador del usuario").located(By.xpath("(//input[@type=\"search\" and @placeholder=\"Select OS\"])[1]"));
    public static final Target CONTENEDOR_VERSION = Target.the("Contenedor de version de computador del usuario ").located(By.name("osVersionId"));
    public static final Target VERSION = Target.the("version de computador del usuario").located(By.xpath("//input[@type=\"search\" and @placeholder=\"Select a Version\"]"));
    public static final Target CONTENEDOR_LENGUAJE = Target.the("Contenedor de lenguaje de computador del usuario").located(By.name("osLanguageId"));
    public static final Target LENGUAJE = Target.the("lenguaje de computador del usuario").located(By.xpath("//input[@type=\"search\" and @placeholder=\"Select OS language\"]"));
    public static final Target CONTENEDOR_DISPOSITIVO_MOVIL = Target.the("Contenedor de dispositivo movil del usuario").located(By.name("handsetMakerId"));
    public static final Target DISPOSITIVO_MOVIL = Target.the("dispositivo movil del usuario").located(By.xpath("//input[@type=\"search\" and @placeholder=\"Select Brand\"]"));
    public static final Target CONTENEDOR_MODELO = Target.the("modelo del dispositivo movil del usuario").located(By.name("handsetModelId"));
    public static final Target MODELO = Target.the("modelo de movil del usuario").located(By.xpath("//input[@type=\"search\" and @placeholder=\"Select a Model\"]"));
    public static final Target CONTENEDOR_SISTEMA_OPERATIVO_MOVIL = Target.the("Contenedor de sistema operativo de movil del usuario").located(By.name("handsetOSId"));
    public static final Target SISTEMA_OPERATIVO_MOVIL = Target.the(" sistema operativo de movil del usuario").located(By.xpath("(//input[@type=\"search\" and @placeholder=\"Select OS\"])[2]"));
    public static final Target BOTON_PASO_FINAL = Target.the(" Boton Paso final").located(By.xpath("//*[contains(text(),'Last Step')]"));
}
```

#### LlenarFormularioInfoFinal

Esta clase contiene todos los mapeos de los elementos de la pagina del proceso de creacion de usuario paso final.

```java
public class PaginainfoFinal {

    public static final Target TXT_CLAVE = Target.the("Campo contraseña").located(By.id("password"));
    public static final Target TXT_CONFIRMAR_CLAVE = Target.the("Campo confirmar contraseña").located(By.id("confirmPassword"));
    public static final Target CHECKBOX_MANTENER_INFORMADO = Target.the("CheckBox mantener informado").located(By.name("newsletterOptIn"));
    public static final Target CHECKBOX_TERMINOS_USO  = Target.the("CheckBox termino de usa").located(By.id("termOfUse"));
    public static final Target CHECKBOX_PRIVACIDA  = Target.the("CheckBox termino de privacidad").located(By.id("privacySetting"));
    public static final Target BOTON_COMPLETAR_FORMULARIO  = Target.the("Boton para finalizar registro").located(By.id("laddaBtn"));
    public static final Target LABEL_REGISTRO_EXITOSO  = Target.the("Texto de bienvenida").located(By.xpath("//div[@class=\"image-box-header\"]/h1"));

}
```



### drivers

#### DriverRemoteBrowser

Esta clase contiene 3 lanzadores de los navegadores a utilizar para la automatizacion, se inicializa una variable WebDriver quien es utilizada en los metodos para levantar cada navegador y asignarle la url, el navegador a utilizar se puede selecionar utilizando parametro tipo string chrome,firefox o Edge
chrome driver Versión 107.0.5304.107 (Build oficial) (64 bits)
firefox geckodriver-v0.32.0-win-aarch64
Edge  Versión 107.0.1418.42 (Compilación oficial) (64 bits)


```java
public class DriverRemoteBrowser {

    static String driverPathFirefox = ".\\src\\test\\resources\\drivers\\geckodriver.exe";
    static String driverPathChrome = ".\\src\\test\\resources\\drivers\\chromedriver.exe";
    static String driverPathEdge = ".\\src\\test\\resources\\drivers\\msedgedriver.exe";
    public static WebDriver driver;

    public static DriverRemoteBrowser HisBrowserWeb(String navegador) {
        if (navegador.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPathChrome);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            return new DriverRemoteBrowser();
        }
        else if (navegador.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPathFirefox);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver(capabilities);
            return new DriverRemoteBrowser();

        }
        else if (navegador.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", driverPathEdge);

            EdgeOptions options = new EdgeOptions();
            //options.addArguments("start-maximized");
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            return new DriverRemoteBrowser();

        }
        else {
            //throw new Exception("navegador incorrecto");
            System.out.println("navegador incorrecto, utiliza:");
            System.out.println("chrome");
            System.out.println("firefox");
            System.out.println("Edge");
            return new DriverRemoteBrowser();
        }
    }
    public static WebDriver on(String url) {
        driver.get(url);
        return driver;
    }
}
```
### Questions

#### ValidarExistenciaElemento

Realiza la validacion de que exista texto de bienvenida dentro de la pagina, esta tarea implementa la interfaz Question y sobreescribe su metodo, tambien recibe un parametro de tipo Target y retorna un valor `Boolean`.

```java
public class Titulo implements Question<Boolean> {

    private List<UtestDatosRegistro> datos;

    public Titulo(List<UtestDatosRegistro> datos) {
        this.datos = datos;
    }

    public static Titulo is(List<UtestDatosRegistro> datos) {
        return new Titulo(datos);
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        boolean resultado;
        String tituloPagina = Text.of(PaginainfoFinal.LABEL_REGISTRO_EXITOSO).viewedBy(actor).asString();
        if((datos.get(0).getStrMensajeFinal()).equals(tituloPagina)){
            resultado = true;
        }
        else {
            resultado = false;
        }

        return resultado;
    }
}
```

### Runners

#### RegistroRunner

Ejecuta y llama los pasos asignados en el feature `RegistroUsuario.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/RegistroUsuario.feature",
        tags = "@Historias",
        glue = "co.com.utest.stepDefinitions",
        snippets = SnippetType.CAMELCASE)
public class RegistroRunner {
}
```

### StepsDefinitions

Los steps definitions contienen todos los metodos llamados mediante el `Runner` al `Feature`. Los metodos ejecutan las `tasks`,`interactions` y `questions` mediante un `actor`.

#### RegistroStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Buscar Tema, este llama a la tarea `BuscarTemaLibreriaNacional` y le envia un String con el valor del tema y ejecuta la questions `ValidarExistenciaElemento`.

```java
public class RegistroStepDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^que el usuario quiere registrarse en la pagina de Utest$")
    public void queElUsuarioQuiereRegistrarseEnLaPaginaDeUtest() {
        theActorCalled("Nilo").can(BrowseTheWeb.with(DriverRemoteBrowser.HisBrowserWeb("chrome").on("https://www.utest.com/")));
        theActorCalled("Nilo").wasAbleTo(AbrirPaginaUtestRegistro.laPagina());

    }

    @Cuando("^el usuario completa los campos requeridos$")
    public void elUsuarioCompletaLosCamposRequeridos(List<UtestDatosRegistro> datos) throws Exception {
        theActorInTheSpotlight().attemptsTo(
                LlenarFormularioInfoPersonal.lapagina(datos),
                LlenarFormularioInfoDireccion.lapagina(datos),
                LlenarFormularioInfoDispositivos.lapagina(datos),
                LlenarFormularioInfoFinal.lapagina(datos)
        );
    }

    @Entonces("^el registro se realizo con exito$")
    public void elRegistroSeRealizoConExito(List<UtestDatosRegistro> datos) throws Exception {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(Titulo.is(datos)));
    }
}
```



### Features

#### RegistroUsuario.feature

Contiene  escenario exitoso de registrarse en Utest y los datos a utilizar en cada escenario digitado en lenguaje Gherking. A su vez realizada el llamado del escenario de registro sin el paso de `Entonces`, se utiliza el formato"Tipodevariable).

```cucumber

#language: es
#Author: Nilo Chaverra  normalista7@gmail.com
  @Historias
  Característica: Registro de usuario en Utest
    El usuario puede ingresar a la pagina web de Utest y completar el formulario de registro mediante la opción Únete hoy

    @escenario1
    Escenario: Registro exitoso
      Dado que el usuario quiere registrarse en la pagina de Utest
      Cuando el usuario completa los campos requeridos
        | strNombre | strAprllido | strCorreo      | strDiaNacimiento | strMesNacimiento | strAnoNacimiento | strCiudad | strCodigoPostal | strPais  | strComputador | strVersionComputador | strLenguajeComputador | strDispositivoMovil | strModeloDispositivoMovil | strSistemaOperativoMovil | strClave    |
        | Nilo      | Chaverra    | Nilo@utest.com | 15               | October          | 1994             | Medellin  | 123             | Colombia | Windows       | 7                    | Spanish               | Apple               | iPhone                    | iOS 11.2                 | *R3t0_2022$ |
      Entonces el registro se realizo con exito
      | strMensajeFinal |
      | Welcome to the world's largest community of freelance software testers! |


```



## Ejecucion

Al momento de ejecutar el proyecto y obtener el reporte debemos ubicarnos en la carpeta del proyecto y abrir el `CMD` para ejecutar el siguiente comando

```yml
    gradle clean test aggregate
```

Este comando ejecutara todos los escenarios implementados en el proyecto

```cmd
    BUILD SUCCESSFUL in 2m 2s
    7 actionable tasks: 7 executed
```

Al finalizar debemos ingresar y abrir el archivo `index.html` que se encuentra en la siguiente ruta

```yml
  <ProyectoName>\target\site\serenity\index.html
```
