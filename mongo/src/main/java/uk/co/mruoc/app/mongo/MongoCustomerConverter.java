package uk.co.mruoc.app.mongo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.DefaultAddressConverter;
import uk.co.mruoc.app.model.DefaultCustomerConverter;

@Component
public class MongoCustomerConverter extends DefaultCustomerConverter {

    private final DefaultAddressConverter addressConverter = new DefaultAddressConverter();

    @Override
    public Customer toModel(CustomerDto dto) {
        return new MongoCustomer(dto.getAccountNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                addressConverter.toModels(dto.getAddresses()));
    }

}
