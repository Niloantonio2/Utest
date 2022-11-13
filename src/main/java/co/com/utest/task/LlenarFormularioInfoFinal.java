package co.com.utest.task;

import co.com.utest.interactions.Esperar;
import co.com.utest.models.UtestDatosRegistro;
import co.com.utest.userinterfaces.PaginainfoFinal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;

import java.util.List;

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
