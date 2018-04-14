package uk.co.mruoc.mock;

import org.junit.rules.ExternalResource;

public class FakeCustomerApplicationRule extends ExternalResource {

    private final FakeCustomerApplication application;

    public FakeCustomerApplicationRule() {
        this(new FakeCustomerApplication());
    }

    private FakeCustomerApplicationRule(FakeCustomerApplication application) {
        this.application = application;
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
