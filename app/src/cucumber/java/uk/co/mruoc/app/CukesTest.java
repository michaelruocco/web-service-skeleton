package uk.co.mruoc.app;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import lv.ctco.cukes.core.extension.CukesPlugin;
import lv.ctco.cukes.http.facade.HttpRequestFacade;
import lv.ctco.cukes.http.facade.HttpResponseFacade;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = "lv.ctco.cukes"
)
@Singleton
public class CukesTest implements CukesPlugin {

    private static final String SERVER_PORT_NAME = "SERVER_PORT";
    private static final Logger LOGGER = LoggerFactory.getLogger(CukesTest.class);

    private String baseUri;
    private HttpRequestFacade requestFacade;
    private HttpResponseFacade responseFacade;

    @Inject
    public CukesTest(HttpRequestFacade requestFacade, HttpResponseFacade responseFacade) {
        this.requestFacade = requestFacade;
        this.requestFacade = requestFacade;
    }

    @Override
    public void beforeAllTests() {
        if (baseUri == null) {
            baseUri = initialise();
        }
    }

    @Override
    public void afterAllTests() {
        // intentionally blank
    }

    @Override
    public void beforeScenario() {
        LOGGER.info(String.format("setting cukes rest base uri %s", baseUri));
        requestFacade.baseUri(baseUri);
    }

    @Override
    public void afterScenario() {
        // intentionally blank
    }

    private String initialise() {
        return getProvidedBaseUrl().orElse(startApplication());
    }

    private Optional<String> getProvidedBaseUrl() {
        return Optional.ofNullable(System.getProperty("baseUri"));
    }

    private String startApplication() {
        int serverPort = getServerPort();
        return startApplication(serverPort);
    }

    private String startApplication(int port) {
        LOGGER.info(String.format("starting application on port %d", port));
        Application.main(new String[] {String.format("--server.port=%d", port)});
        return String.format("http://localhost:%d", port);
    }

    private static int getServerPort() {
        return Integer.parseInt(System.getenv(SERVER_PORT_NAME));
    }

}
