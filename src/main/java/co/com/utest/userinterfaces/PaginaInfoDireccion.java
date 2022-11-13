package co.com.utest.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaInfoDireccion {
    public static final Target CIUDAD = Target.the("Campo ciudad del usuario").located(By.id("city"));
    public static final Target CODIGO_POSTAL = Target.the("Campo Codigo Postal del usuario").located(By.id("zip"));
    public static final Target CONTENEDOR_PAIS = Target.the("Contenedor pais").located(By.name("countryId"));
    public static final Target PAIS = Target.the("campo pais del usuario").located(By.xpath("//input[@placeholder=\"Select a country\"]"));
    public static final Target BOTON_DISPOSITVOS = Target.the("Boton paso siguiente").located(By.xpath("//a[@class=\"btn btn-blue pull-right\"]"));

}
