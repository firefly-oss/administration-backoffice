package com.catalis.backoffice.api.service.account;

import com.catalis.backoffice.api.dto.account.AccountDTO;
import com.catalis.backoffice.common.service.MockRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mock implementation of the AccountService interface.
 * This implementation provides simulated responses for account operations.
 * It will be replaced with a real implementation when the actual microservices are available.
 */
@Service
public class MockAccountServiceImpl implements AccountService {

    private static final String ACCOUNT_API_BASE_URL = "/api/accounts";
    private final MockRestService mockRestService;
    private final List<AccountDTO> mockAccounts;

    public MockAccountServiceImpl(MockRestService mockRestService) {
        this.mockRestService = mockRestService;
        this.mockAccounts = createMockAccounts();
        registerMockResponses();
    }

    @Override
    public Optional<AccountDTO> getAccountById(String id) {
        ResponseEntity<AccountDTO> response = mockRestService.get(
                ACCOUNT_API_BASE_URL + "/" + id,
                AccountDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Optional.of(response.getBody());
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<AccountDTO> getAccountByNumber(String accountNumber) {
        ResponseEntity<AccountDTO> response = mockRestService.get(
                ACCOUNT_API_BASE_URL + "/number/" + accountNumber,
                AccountDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Optional.of(response.getBody());
        }
        
        return Optional.empty();
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(String customerId) {
        ResponseEntity<List<AccountDTO>> response = mockRestService.get(
                ACCOUNT_API_BASE_URL + "/customer/" + customerId,
                (Class<List<AccountDTO>>) (Class<?>) List.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return new ArrayList<>();
    }

    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        List<AccountDTO> allAccounts = mockAccounts;
        
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allAccounts.size());
        
        List<AccountDTO> pageContent = allAccounts.subList(start, end);
        return new PageImpl<>(pageContent, pageable, allAccounts.size());
    }

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        // Assign a new ID if not provided
        if (account.getId() == null || account.getId().isEmpty()) {
            account.setId(UUID.randomUUID().toString());
        }
        
        // Generate account number if not provided
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            account.setAccountNumber(generateAccountNumber());
        }
        
        // Set creation and update timestamps
        LocalDateTime now = LocalDateTime.now();
        account.setCreatedAt(now);
        account.setUpdatedAt(now);
        
        ResponseEntity<AccountDTO> response = mockRestService.post(
                ACCOUNT_API_BASE_URL,
                account,
                AccountDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return account; // Return the input account as fallback
    }

    @Override
    public AccountDTO updateAccount(String id, AccountDTO account) {
        // Ensure the ID in the path matches the ID in the account object
        account.setId(id);
        
        // Update the timestamp
        account.setUpdatedAt(LocalDateTime.now());
        
        ResponseEntity<AccountDTO> response = mockRestService.put(
                ACCOUNT_API_BASE_URL + "/" + id,
                account,
                AccountDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        throw new IllegalArgumentException("Account not found with ID: " + id);
    }

    @Override
    public AccountDTO updateAccountStatus(String id, String status) {
        Optional<AccountDTO> accountOpt = getAccountById(id);
        if (accountOpt.isPresent()) {
            AccountDTO account = accountOpt.get();
            account.setStatus(status);
            account.setUpdatedAt(LocalDateTime.now());
            
            return updateAccount(id, account);
        }
        
        throw new IllegalArgumentException("Account not found with ID: " + id);
    }

    @Override
    public AccountDTO deposit(String id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        Optional<AccountDTO> accountOpt = getAccountById(id);
        if (accountOpt.isPresent()) {
            AccountDTO account = accountOpt.get();
            account.setBalance(account.getBalance().add(amount));
            account.setUpdatedAt(LocalDateTime.now());
            
            return updateAccount(id, account);
        }
        
        throw new IllegalArgumentException("Account not found with ID: " + id);
    }

    @Override
    public AccountDTO withdraw(String id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        Optional<AccountDTO> accountOpt = getAccountById(id);
        if (accountOpt.isPresent()) {
            AccountDTO account = accountOpt.get();
            
            if (account.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            
            account.setBalance(account.getBalance().subtract(amount));
            account.setUpdatedAt(LocalDateTime.now());
            
            return updateAccount(id, account);
        }
        
        throw new IllegalArgumentException("Account not found with ID: " + id);
    }

    @Override
    public AccountDTO transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        // First withdraw from the source account
        AccountDTO sourceAccount = withdraw(fromAccountId, amount);
        
        try {
            // Then deposit to the destination account
            deposit(toAccountId, amount);
        } catch (Exception e) {
            // If deposit fails, refund the source account
            deposit(fromAccountId, amount);
            throw new RuntimeException("Transfer failed: " + e.getMessage(), e);
        }
        
        return sourceAccount;
    }

    /**
     * Creates a list of mock accounts for testing.
     * 
     * @return a list of mock accounts
     */
    private List<AccountDTO> createMockAccounts() {
        List<AccountDTO> accounts = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        
        accounts.add(new AccountDTO(
                "1",
                "1000000001",
                "1", // Customer ID
                "CHECKING",
                new BigDecimal("5000.00"),
                "USD",
                "ACTIVE",
                now.minusDays(100),
                now
        ));
        
        accounts.add(new AccountDTO(
                "2",
                "1000000002",
                "1", // Customer ID
                "SAVINGS",
                new BigDecimal("10000.00"),
                "USD",
                "ACTIVE",
                now.minusDays(90),
                now
        ));
        
        accounts.add(new AccountDTO(
                "3",
                "1000000003",
                "2", // Customer ID
                "CHECKING",
                new BigDecimal("2500.00"),
                "USD",
                "ACTIVE",
                now.minusDays(80),
                now
        ));
        
        accounts.add(new AccountDTO(
                "4",
                "1000000004",
                "3", // Customer ID
                "CREDIT",
                new BigDecimal("1500.00"),
                "USD",
                "ACTIVE",
                now.minusDays(70),
                now
        ));
        
        accounts.add(new AccountDTO(
                "5",
                "1000000005",
                "4", // Customer ID
                "CHECKING",
                new BigDecimal("7500.00"),
                "USD",
                "ACTIVE",
                now.minusDays(60),
                now
        ));
        
        return accounts;
    }

    /**
     * Generates a random account number.
     * 
     * @return a random account number
     */
    private String generateAccountNumber() {
        return "10" + String.format("%08d", (int) (Math.random() * 100000000));
    }

    /**
     * Registers mock responses with the MockRestService.
     */
    private void registerMockResponses() {
        // Register mock response for getting all accounts
        mockRestService.registerMockResponse(
                ACCOUNT_API_BASE_URL,
                () -> mockAccounts
        );
        
        // Register mock responses for getting individual accounts by ID
        for (AccountDTO account : mockAccounts) {
            mockRestService.registerMockResponse(
                    ACCOUNT_API_BASE_URL + "/" + account.getId(),
                    () -> account
            );
            
            // Register mock responses for getting accounts by account number
            mockRestService.registerMockResponse(
                    ACCOUNT_API_BASE_URL + "/number/" + account.getAccountNumber(),
                    () -> account
            );
        }
        
        // Register mock responses for getting accounts by customer ID
        mockRestService.registerMockResponse(
                ACCOUNT_API_BASE_URL + "/customer/1",
                () -> mockAccounts.stream()
                        .filter(a -> "1".equals(a.getCustomerId()))
                        .collect(Collectors.toList())
        );
        
        mockRestService.registerMockResponse(
                ACCOUNT_API_BASE_URL + "/customer/2",
                () -> mockAccounts.stream()
                        .filter(a -> "2".equals(a.getCustomerId()))
                        .collect(Collectors.toList())
        );
        
        mockRestService.registerMockResponse(
                ACCOUNT_API_BASE_URL + "/customer/3",
                () -> mockAccounts.stream()
                        .filter(a -> "3".equals(a.getCustomerId()))
                        .collect(Collectors.toList())
        );
        
        mockRestService.registerMockResponse(
                ACCOUNT_API_BASE_URL + "/customer/4",
                () -> mockAccounts.stream()
                        .filter(a -> "4".equals(a.getCustomerId()))
                        .collect(Collectors.toList())
        );
    }
}