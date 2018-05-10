package uk.co.mruoc.app.model;

import org.modelmapper.ModelMapper;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerDto.CustomerDtoBuilder;

public class DefaultCustomerConverter implements CustomerConverter {

    private final ModelMapper modelMapper = new ModelMapper();
    private final DefaultAddressConverter addressConverter = new DefaultAddressConverter();

    @Override
    public Customer toModel(CustomerDto dto) {
        return new DefaultCustomer(dto.getAccountNumber(),
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
