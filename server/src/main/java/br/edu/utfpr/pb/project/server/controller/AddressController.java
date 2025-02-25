package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AddressDto;
import br.edu.utfpr.pb.project.server.model.Address;
import br.edu.utfpr.pb.project.server.service.IAddressService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController extends CrudController<Address, AddressDto, Long>{
    private final ModelMapper modelMapper;
    private final IAddressService addressService;

    public AddressController(ModelMapper modelMapper, IAddressService addressService) {
        super(Address.class, AddressDto.class);
        this.modelMapper = modelMapper;
        this.addressService = addressService;
    }

    @Override
    protected ICrudService<Address, Long> getService() {
        return addressService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping("/auth")
    public ResponseEntity<Address> create(@RequestBody @Valid Address address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.addAddressToAuthenticatedUser(address));
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.removeAddressFromAuthenticatedUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cep/{cep}")
    public String searchByCep(@PathVariable String cep) {
        return addressService.searchByCep(cep);
    }
}
