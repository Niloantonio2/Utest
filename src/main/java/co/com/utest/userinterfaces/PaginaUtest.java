package co.com.utest.userinterfaces;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://www.utest.com/")
public class PaginaUtest extends PageObject {

    public static final Target BOTON_JOIN_TODAY = Target.the("Boton para iniciar usuario").located(By.className("unauthenticated-nav-bar__sign-up"));
    //public static final Target BOTON_Login = Target.the("Boton para ingresar sesion").located(By.xpath("//*[contains(text(),'Log In')]"));

}
