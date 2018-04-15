package uk.co.mruoc.app.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new Address()).isNotNull();
    }

}
