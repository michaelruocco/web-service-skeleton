package uk.co.mruoc.app.model;

import org.junit.Test;
import uk.co.mruoc.app.mongo.MongoAddress;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new MongoAddress()).isNotNull();
    }

}
