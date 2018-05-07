package uk.co.mruoc.app.mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;
import java.io.UncheckedIOException;

public class FakeMongo {

    private static final int DEFAULT_PORT = 27017;

    private MongodExecutable mongodExecutable;

    public void start() {
        start(DEFAULT_PORT);
    }

    public void start(int port) {
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

    public void stop() {
        mongodExecutable.stop();
    }

}
