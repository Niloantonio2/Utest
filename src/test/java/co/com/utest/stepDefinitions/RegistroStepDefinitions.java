package co.com.utest.stepDefinitions;

import co.com.utest.drivers.*;
import co.com.utest.models.*;
import co.com.utest.task.*;
import co.com.utest.question.*;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;


import java.io.IOException;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class RegistroStepDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^que el usuario quiere registrarse en la pagina de Utest$")
    public void queElUsuarioQuiereRegistrarseEnLaPaginaDeUtest() {
        theActorCalled("Nilo").can(BrowseTheWeb.with(DriverRemoteBrowser.HisBrowserWeb("chrome").on("https://www.utest.com/")));
        theActorCalled("Nilo").wasAbleTo(AbrirPaginaUtestRegistro.laPagina());

    }

    @Cuando("^el usuario completa los campos requeridos$")
    public void elUsuarioCompletaLosCamposRequeridos(List<UtestDatosRegistro> datos) throws Exception {
       theActorInTheSpotlight().attemptsTo(
               LlenarFormularioInfoPersonal.lapagina(datos),
               LlenarFormularioInfoDireccion.lapagina(datos),
               LlenarFormularioInfoDispositivos.lapagina(datos),
               LlenarFormularioInfoFinal.lapagina(datos)
               );
    }

    @Entonces("^el registro se realizo con exito$")
    public void elRegistroSeRealizoConExito(List<UtestDatosRegistro> datos) throws Exception {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(Titulo.is(datos)));
    }
}
