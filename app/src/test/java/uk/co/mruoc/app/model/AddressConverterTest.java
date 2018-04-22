package uk.co.mruoc.app.model;

import org.junit.Test;
import uk.co.mruoc.api.AddressDto;
import uk.co.mruoc.api.examples.StubbedAddressDto1;
import uk.co.mruoc.api.examples.StubbedAddressDto2;
import uk.co.mruoc.app.mongo.MongoAddressConverter;
import uk.co.mruoc.app.mongo.StubbedMongoAddress;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressConverterTest {


    private final MongoAddressConverter converter = new MongoAddressConverter();

    @Test
    public void shouldConvertDto() {
        AddressDto addressDto = new StubbedAddressDto1();

        Address addressModel = converter.toModel(addressDto);

        assertSameValues(addressDto, addressModel);
    }

    @Test
    public void shouldConvertMultipleDtos() {
        List<AddressDto> addressDtos = Arrays.asList(new StubbedAddressDto1(), new StubbedAddressDto2());

        List<Address> addressModels = converter.toModels(addressDtos);

        for(int a = 0; a < addressDtos.size(); a++) {
            AddressDto addressDto = addressDtos.get(a);
            Address addressModel = addressModels.get(a);
            assertSameValues(addressDto, addressModel);
        }
    }

    @Test
    public void shouldConvertModel() {
        Address addressModel = new StubbedMongoAddress();

        AddressDto addressDto = converter.toDto(addressModel);

        assertSameValues(addressDto, addressModel);
    }

    private static void assertSameValues(AddressDto dto, Address model) {
        assertThat(dto.getLine1()).isEqualTo(model.getLine1());
        assertThat(dto.getLine2()).isEqualTo(model.getLine2());
        assertThat(dto.getTown()).isEqualTo(model.getTown());
        assertThat(dto.getCounty()).isEqualTo(model.getCounty());
        assertThat(dto.getPostcode()).isEqualTo(model.getPostcode());
        assertThat(dto.getCountry()).isEqualTo(model.getCountry());
    }

}
