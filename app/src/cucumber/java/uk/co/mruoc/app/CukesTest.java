package uk.co.mruoc.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lv.ctco.cukes.core.extension.CukesPlugin;
import lv.ctco.cukes.http.facade.HttpRequestFacade;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SocketUtils;

import javax.inject.Inject;
import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = "lv.ctco.cukes"
)
public class CukesTest implements CukesPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(CukesTest.class);

    private String baseUri;
    private HttpRequestFacade requestFacade;

    @Inject
    public CukesTest(HttpRequestFacade requestFacade) {
        this.requestFacade = requestFacade;
    }

    private String initialise() {
        return getProvidedBaseUrl().orElse(startApplication());
    }

    private Optional<String> getProvidedBaseUrl() {
        return Optional.ofNullable(System.getProperty("baseUri"));
    }

    private String startApplication() {
        int port = SocketUtils.findAvailableTcpPort();
        return startApplication(port);
    }

    private String startApplication(int port) {
        LOGGER.info(String.format("starting application on port %d", port));
        Application.main(new String[] {String.format("--server.port=%d", port)});
        return String.format("http://localhost:%d", port);
    }

    @Override
    public void beforeAllTests() {
        if (baseUri == null) {
            baseUri = initialise();
        }
        LOGGER.info(String.format("setting cukes rest base uri %s", baseUri));
        requestFacade.baseUri(baseUri);
    }

    @Override
    public void afterAllTests() {
        // intentionally blank
    }

    @Override
    public void beforeScenario() {
        // intentionally blank
    }

    @Override
    public void afterScenario() {
        // intentionally blank
    }

}
