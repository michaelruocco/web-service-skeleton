package uk.co.mruoc.app.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new Customer()).isNotNull();
    }

}
