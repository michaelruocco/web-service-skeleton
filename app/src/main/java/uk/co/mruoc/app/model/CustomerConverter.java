package uk.co.mruoc.app.model;

import org.modelmapper.ModelMapper;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDto.CustomerDtoBuilder;

public class CustomerConverter {

    private final ModelMapper modelMapper = new ModelMapper();
    private final AddressConverter addressConverter = new AddressConverter();

    public Customer toModel(CustomerDto dto) {
        return new Customer(dto);
    }

    public CustomerDto toDto(Customer customer) {
        CustomerDtoBuilder builder = modelMapper.map(customer, CustomerDtoBuilder.class);
        builder.setAddresses(addressConverter.toDtos(customer.getAddresses()));
        return builder.build();
    }

}
