package co.com.utest.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginainfoFinal {

    public static final Target TXT_CLAVE = Target.the("Campo contraseña").located(By.id("password"));
    public static final Target TXT_CONFIRMAR_CLAVE = Target.the("Campo confirmar contraseña").located(By.id("confirmPassword"));
    public static final Target CHECKBOX_MANTENER_INFORMADO = Target.the("CheckBox mantener informado").located(By.name("newsletterOptIn"));
    public static final Target CHECKBOX_TERMINOS_USO  = Target.the("CheckBox termino de usa").located(By.id("termOfUse"));
    public static final Target CHECKBOX_PRIVACIDA  = Target.the("CheckBox termino de privacidad").located(By.id("privacySetting"));
    public static final Target BOTON_COMPLETAR_FORMULARIO  = Target.the("Boton para finalizar registro").located(By.id("laddaBtn"));
    public static final Target LABEL_REGISTRO_EXITOSO  = Target.the("Texto de bienvenida").located(By.xpath("//div[@class=\"image-box-header\"]/h1"));

}
