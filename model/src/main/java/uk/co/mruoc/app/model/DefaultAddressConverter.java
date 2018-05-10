package uk.co.mruoc.app.model;

import org.modelmapper.ModelMapper;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.AddressDto.AddressDtoBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultAddressConverter {

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Address> toModels(List<AddressDto> dtos) {
        return dtos.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Address toModel(AddressDto dto) {
        return new DefaultAddress(dto.getLine1(),
                dto.getLine2(),
                dto.getTown(),
                dto.getCounty(),
                dto.getPostcode(),
                dto.getCountry());
    }

    public List<AddressDto> toDtos(List<Address> addresses) {
        return addresses.stream().map(this::toDto).collect(Collectors.toList());
    }

    public AddressDto toDto(Address address) {
        AddressDtoBuilder builder = modelMapper.map(address, AddressDtoBuilder.class);
        return builder.build();
    }

}
