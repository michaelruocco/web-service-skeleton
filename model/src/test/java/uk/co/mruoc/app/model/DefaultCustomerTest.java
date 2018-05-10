package uk.co.mruoc.app.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultCustomerTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new DefaultCustomer()).isNotNull();
    }

}