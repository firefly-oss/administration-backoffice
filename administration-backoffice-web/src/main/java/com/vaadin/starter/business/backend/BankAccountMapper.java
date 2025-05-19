package com.vaadin.starter.business.backend;

import com.catalis.administration.backoffice.interfaces.model.BankAccountDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class BankAccountMapper {

    /**
     * Convert a BankAccount from the interfaces module to a BankAccount from the web module
     * @param interfaceBankAccountDTO BankAccount from the interfaces module
     * @return BankAccount from the web module
     */
    public BankAccount toWebBankAccount(BankAccountDTO interfaceBankAccountDTO) {
        return new BankAccount(
                interfaceBankAccountDTO.getId(),
                interfaceBankAccountDTO.getBank(),
                interfaceBankAccountDTO.getAccount(),
                interfaceBankAccountDTO.getOwner(),
                interfaceBankAccountDTO.getAvailability(),
                interfaceBankAccountDTO.getUpdated(),
                interfaceBankAccountDTO.getLogoPath()
        );
    }

    /**
     * Convert a collection of BankAccounts from the interfaces module to a collection of BankAccounts from the web module
     * @param interfaceBankAccountDTOS Collection of BankAccounts from the interfaces module
     * @return Collection of BankAccounts from the web module
     */
    public Collection<BankAccount> toWebBankAccounts(Collection<BankAccountDTO> interfaceBankAccountDTOS) {
        return interfaceBankAccountDTOS.stream()
                .map(this::toWebBankAccount)
                .collect(Collectors.toList());
    }
}