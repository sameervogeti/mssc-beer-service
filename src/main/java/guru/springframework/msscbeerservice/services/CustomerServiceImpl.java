package guru.springframework.msscbeerservice.services;


import guru.springframework.msscbeerservice.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("Galaxy Customer")
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .customerName("Galaxy Customer")
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomerByID(UUID customerId, CustomerDTO customerDTO) {
        log.info("Update Customer Invoked");
    }

    @Override
    public void deleteCustomerbyId(UUID customerId) {
        log.info("Delete Customer Invoked");
    }
}
