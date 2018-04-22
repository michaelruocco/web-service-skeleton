package uk.co.mruoc.app.mongo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDto.CustomerDtoBuilder;
import uk.co.mruoc.app.model.Customer;
import uk.co.mruoc.app.model.CustomerConverter;

@Component
public class MongoCustomerConverter implements CustomerConverter {

    private final ModelMapper modelMapper = new ModelMapper();
    private final MongoAddressConverter addressConverter = new MongoAddressConverter();

    @Override
    public Customer toModel(CustomerDto dto) {
        return new MongoCustomer(dto.getAccountNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                addressConverter.toModels(dto.getAddresses()));
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDtoBuilder builder = modelMapper.map(customer, CustomerDtoBuilder.class);
        builder.setAddresses(addressConverter.toDtos(customer.getAddresses()));
        return builder.build();
    }

}
