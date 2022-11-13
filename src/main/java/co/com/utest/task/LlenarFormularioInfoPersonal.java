package co.com.utest.task;

import co.com.utest.interactions.Esperar;
import co.com.utest.models.UtestDatosRegistro;
import co.com.utest.userinterfaces.PaginaInfoPersonal;
import com.github.javafaker.Faker;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.List;
import java.util.Locale;

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
                Esperar.estosSegundos(5),
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
