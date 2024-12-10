package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.Address;

public interface IAddressService extends ICrudService<Address, Long>{
    Address addAddressToAuthenticatedUser(Address address);
    void removeAddressFromAuthenticatedUser(Long addressId);
    String searchByCep(String cep);
}
