package com.catalis.backoffice.api.service.account;

import com.catalis.backoffice.api.client.account.AccountClient;
import com.catalis.backoffice.api.dto.account.AccountDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Real implementation of the AccountService interface.
 * This implementation uses the AccountClient to make HTTP requests to the Account microservice.
 * 
 * NOTE: This class is provided as a template for future implementation.
 * It is not currently active as the microservices are not yet available.
 * To activate this implementation, uncomment the @Service and @Primary annotations
 * and comment out the @Service annotation in MockAccountServiceImpl.
 */
// @Service
// @Primary
public class AccountServiceImpl implements AccountService {

    private final AccountClient accountClient;

    public AccountServiceImpl(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public Optional<AccountDTO> getAccountById(String id) {
        try {
            ResponseEntity<AccountDTO> response = accountClient.getAccountById(id);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Optional.of(response.getBody());
            }
            
            return Optional.empty();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving account with ID: " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<AccountDTO> getAccountByNumber(String accountNumber) {
        try {
            ResponseEntity<AccountDTO> response = accountClient.getAccountByNumber(accountNumber);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Optional.of(response.getBody());
            }
            
            return Optional.empty();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving account with number: " + accountNumber, e);
            return Optional.empty();
        }
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(String customerId) {
        try {
            ResponseEntity<List<AccountDTO>> response = accountClient.getAccountsByCustomerId(customerId);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving accounts for customer ID: " + customerId, e);
            return new ArrayList<>();
        }
    }

    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        try {
            ResponseEntity<List<AccountDTO>> response = accountClient.getAllAccounts();
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<AccountDTO> allAccounts = response.getBody();
                
                int start = (int) pageable.getOffset();
                int end = Math.min((start + pageable.getPageSize()), allAccounts.size());
                
                if (start > allAccounts.size()) {
                    return new PageImpl<>(new ArrayList<>(), pageable, allAccounts.size());
                }
                
                List<AccountDTO> pageContent = allAccounts.subList(start, end);
                return new PageImpl<>(pageContent, pageable, allAccounts.size());
            }
            
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving all accounts", e);
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        try {
            ResponseEntity<AccountDTO> response = accountClient.createAccount(account);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new RuntimeException("Failed to create account: " + response.getStatusCode());
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error creating account", e);
            throw new RuntimeException("Error creating account", e);
        }
    }

    @Override
    public AccountDTO updateAccount(String id, AccountDTO account) {
        try {
            ResponseEntity<AccountDTO> response = accountClient.updateAccount(id, account);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Account not found with ID: " + id);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error updating account with ID: " + id, e);
            throw new RuntimeException("Error updating account with ID: " + id, e);
        }
    }

    @Override
    public AccountDTO updateAccountStatus(String id, String status) {
        try {
            ResponseEntity<AccountDTO> response = accountClient.updateAccountStatus(id, status);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Account not found with ID: " + id);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error updating status for account with ID: " + id, e);
            throw new RuntimeException("Error updating status for account with ID: " + id, e);
        }
    }

    @Override
    public AccountDTO deposit(String id, BigDecimal amount) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
            
            ResponseEntity<AccountDTO> response = accountClient.deposit(id, amount);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Account not found with ID: " + id);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error depositing to account with ID: " + id, e);
            throw new RuntimeException("Error depositing to account with ID: " + id, e);
        }
    }

    @Override
    public AccountDTO withdraw(String id, BigDecimal amount) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive");
            }
            
            ResponseEntity<AccountDTO> response = accountClient.withdraw(id, amount);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Account not found with ID: " + id);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error withdrawing from account with ID: " + id, e);
            throw new RuntimeException("Error withdrawing from account with ID: " + id, e);
        }
    }

    @Override
    public AccountDTO transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Transfer amount must be positive");
            }
            
            ResponseEntity<AccountDTO> response = accountClient.transfer(fromAccountId, toAccountId, amount);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Account not found with ID: " + fromAccountId);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error transferring from account " + fromAccountId + " to account " + toAccountId, e);
            throw new RuntimeException("Error transferring from account " + fromAccountId + " to account " + toAccountId, e);
        }
    }
}