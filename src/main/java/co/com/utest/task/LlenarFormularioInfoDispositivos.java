package co.com.utest.task;

import co.com.utest.models.UtestDatosRegistro;
import co.com.utest.userinterfaces.PaginaInfoDispositivos;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;

import java.util.List;

import static org.openqa.selenium.Keys.*;


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
