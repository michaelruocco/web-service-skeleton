package uk.co.mruoc.mock;

import org.junit.rules.ExternalResource;

public class FakeCustomerApplicationRule extends ExternalResource {

    private static final int DEFAULT_PORT = 8080;

    private final FakeCustomerApplication application;

    public FakeCustomerApplicationRule() {
        this(DEFAULT_PORT);
    }

    public FakeCustomerApplicationRule(int port) {
        this.application = new FakeCustomerApplication(port);
    }

    public int getPort() {
        return application.getPort();
    }

    @Override
    protected void before() {
        application.start();
    }

    @Override
    protected void after() {
        application.stop();
    }

}
