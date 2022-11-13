package co.com.utest.task;

import co.com.utest.interactions.Esperar;
import co.com.utest.userinterfaces.PaginaUtest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

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
