package uk.co.mruoc.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lv.ctco.cukes.core.extension.CukesPlugin;
import lv.ctco.cukes.http.facade.HttpRequestFacade;
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

    private static final int APPLICATION_PORT = 8092;
    private static final Logger LOGGER = LoggerFactory.getLogger(CukesTest.class);

    private final FakeMongo fakeMongo = new FakeMongo();

    private String baseUri;
    private HttpRequestFacade requestFacade;

    @Inject
    public CukesTest(HttpRequestFacade requestFacade) {
        this.requestFacade = requestFacade;
    }

    @Override
    public void beforeAllTests() {
        fakeMongo.start();
        if (baseUri == null) {
            baseUri = initialise();
        }
    }

    @Override
    public void afterAllTests() {
        fakeMongo.stop();
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
        return startApplication(APPLICATION_PORT);
    }

    private String startApplication(int port) {
        LOGGER.info(String.format("starting application on port %d", port));
        Application.main(new String[] {String.format("--server.port=%d", port)});
        return String.format("http://localhost:%d", port);
    }

}
