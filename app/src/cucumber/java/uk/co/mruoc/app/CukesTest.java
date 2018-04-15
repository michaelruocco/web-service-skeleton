package uk.co.mruoc.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lv.ctco.cukes.core.extension.CukesPlugin;
import lv.ctco.cukes.http.facade.HttpRequestFacade;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SocketUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        glue = "lv.ctco.cukes"
)
@Singleton
public class CukesTest implements CukesPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(CukesTest.class);

    private String baseUri;
    private HttpRequestFacade requestFacade;
    private MongodExecutable mongodExecutable;

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
        startMongo();
        if (baseUri == null) {
            baseUri = initialise();
        }
    }

    @Override
    public void afterAllTests() {
        stopMongo();
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

    private void startMongo() {
        try {
            MongodStarter starter = MongodStarter.getDefaultInstance();

            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net("localhost", 27017, Network.localhostIsIPv6()))
                    .build();

            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void stopMongo() {
        mongodExecutable.stop();
    }

}
