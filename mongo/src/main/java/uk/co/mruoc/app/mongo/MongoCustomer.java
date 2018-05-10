package uk.co.mruoc.app.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.mruoc.app.model.Address;
import uk.co.mruoc.app.model.DefaultCustomer;

import java.util.List;

@Document
public class MongoCustomer extends DefaultCustomer {

    public MongoCustomer() {
        super();
    }

    public MongoCustomer(String accountNumber,
                           String firstName,
                           String lastName,
                           List<Address> addresses) {
        super(accountNumber, firstName, lastName, addresses);
    }

    @Override
    @Id
    public String getAccountNumber() {
        return super.getAccountNumber();
    }

}
