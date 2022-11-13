package co.com.utest.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

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
