package uk.co.mruoc.app.model;

import org.junit.Test;
import uk.co.mruoc.app.mongo.MongoCustomer;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new MongoCustomer()).isNotNull();
    }

}
