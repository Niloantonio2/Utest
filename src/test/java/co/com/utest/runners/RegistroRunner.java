package co.com.utest.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/RegistroUsuario.feature",
        tags = "@Historias",
        glue = "co.com.utest.stepDefinitions",
        snippets = SnippetType.CAMELCASE)
public class RegistroRunner {
}
