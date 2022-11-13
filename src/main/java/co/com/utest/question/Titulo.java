package co.com.utest.question;


import co.com.utest.models.UtestDatosRegistro;
import co.com.utest.userinterfaces.PaginainfoFinal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class Titulo implements Question<Boolean> {

    private List<UtestDatosRegistro> datos;

    public Titulo(List<UtestDatosRegistro> datos) {
        this.datos = datos;
    }

    public static Titulo is(List<UtestDatosRegistro> datos) {
        return new Titulo(datos);
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        boolean resultado;
        String tituloPagina = Text.of(PaginainfoFinal.LABEL_REGISTRO_EXITOSO).viewedBy(actor).asString();
        if((datos.get(0).getStrMensajeFinal()).equals(tituloPagina)){
            resultado = true;
        }
        else {
            resultado = false;
        }

        return resultado;
    }
}
