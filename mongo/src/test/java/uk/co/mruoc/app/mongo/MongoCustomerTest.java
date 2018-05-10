package uk.co.mruoc.app.mongo;

import org.junit.Test;
import uk.co.mruoc.app.model.DefaultCustomer;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoCustomerTest {

    @Test
    public void hasNoArgConstructorForMongoData() {
        assertThat(new MongoCustomer()).isNotNull();
    }

}