package uk.co.mruoc.app.model;

import uk.co.mruoc.api.CustomerDto;

public interface CustomerConverter {
    Customer toModel(CustomerDto dto);

    CustomerDto toDto(Customer customer);
}
