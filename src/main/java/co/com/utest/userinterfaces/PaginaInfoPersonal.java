package co.com.utest.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaInfoPersonal {

    public static final Target TXT_NOMBRE = Target.the("Campo para nombre de usuario").located(By.id("firstName"));
    public static final Target TXT_APELLIDO = Target.the("Campo para apellido de usuario").located(By.id("lastName"));
    public static final Target TXT_CORREO = Target.the("Campo para correo de usuario").located(By.id("email"));
    public static final Target DIA_NACIMIENTO = Target.the("Campo para dia de nacimiento de usuario").located(By.id("birthDay"));
    public static final Target MES_NACIMIENTO = Target.the("Campo para mes de nacimiento de usuario").located(By.id("birthMonth"));
    public static final Target ANO_NACIMIENTO = Target.the("Campo para año de usuario").located(By.id("birthYear"));
    public static final Target BOTON_DIRECCION = Target.the("Boton para ir a seccion de direccion").located(By.xpath("//a[@class=\"btn btn-blue\"]"));




}
