package uk.co.mruoc.app.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.CustomerDto;
import uk.co.mruoc.api.CustomerNotFoundErrorDto;
import uk.co.mruoc.api.ErrorDto;
import uk.co.mruoc.app.facade.CustomerFacade;

import javax.servlet.http.HttpServletRequest;

@RestController
@Validated
public class PostCustomersController {

    @Autowired
    private final CustomerFacade facade;

    public PostCustomersController(CustomerFacade facade) {
        this.facade = facade;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created customer", response = CustomerDto.class),
            @ApiResponse(code = 400, message = "Invalid customer", response = ErrorDto.class),
            @ApiResponse(code = 409, message = "Customer already exists", response = CustomerNotFoundErrorDto.class)
    })
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer, HttpServletRequest request) {
        CustomerDto createdCustomer = facade.createCustomer(customer);
        HttpHeaders headers = buildCreatedResourceHeader(request, createdCustomer.getAccountNumber());
        return new ResponseEntity<>(createdCustomer, headers, HttpStatus.CREATED);
    }

    private HttpHeaders buildCreatedResourceHeader(HttpServletRequest request, String id) {
        String url = buildCreatedResourceUrl(request, id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return headers;
    }

    private String buildCreatedResourceUrl(HttpServletRequest request, String id) {
        return request.getRequestURL().toString() + "/" + id;
    }

}
