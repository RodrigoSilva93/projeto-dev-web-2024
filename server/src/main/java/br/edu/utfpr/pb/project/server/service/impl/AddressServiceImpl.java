package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Address;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.AddressRepository;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import br.edu.utfpr.pb.project.server.service.AuthService;
import br.edu.utfpr.pb.project.server.service.IAddressService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AddressServiceImpl extends CrudServiceImpl<Address, Long> implements IAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository, AuthService authService) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }
    @Override
    protected JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    public Address addAddressToAuthenticatedUser(Address address) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = (User) authService.loadUserByUsername(email);

        if (user != null) {
            address.setUser(user);
            user.getAddresses().add(address);
            userRepository.save(user);
            return user.getAddresses().getLast();
        }
        throw new RuntimeException("User not authenticated.");
    }

    @Override
    public void removeAddressFromAuthenticatedUser(Long addressId) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = (User) authService.loadUserByUsername(email);

        if (user != null) {
            Address address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("Address not found."));
            user.getAddresses().remove(address);
            addressRepository.delete(address);
        } else
            throw new RuntimeException("User not authenticated.");
    }


    public String searchByCep(String cep) {
        String uri = UriComponentsBuilder.fromHttpUrl("https://viacep.com.br/ws/" + cep + "/json/").toUriString();

        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(uri, String.class);
        } catch (Exception e) {
            throw new RuntimeException("CEP not found.");
        }
    }
}
