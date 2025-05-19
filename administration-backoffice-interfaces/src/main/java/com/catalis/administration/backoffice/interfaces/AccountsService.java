package com.catalis.administration.backoffice.interfaces;

import com.catalis.administration.backoffice.interfaces.model.BankAccountDTO;
import java.util.Collection;

public interface AccountsService {

    /**
     * Filter accounts from the accounts service
     * @return Collection of bank accounts
     */
    Collection<BankAccountDTO> filterAccounts();
}
