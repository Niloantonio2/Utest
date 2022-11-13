package co.com.utest.task;

import co.com.utest.models.UtestDatosRegistro;
import co.com.utest.userinterfaces.PaginaInfoDireccion;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.*;
import org.openqa.selenium.Keys;

import java.util.List;

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
