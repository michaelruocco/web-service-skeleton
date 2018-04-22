package uk.co.mruoc.app.mongo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoAddressTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new MongoAddress()).isNotNull();
    }

}
