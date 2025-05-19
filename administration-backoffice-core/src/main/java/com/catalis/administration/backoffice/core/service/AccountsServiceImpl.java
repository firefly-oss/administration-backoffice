package com.catalis.administration.backoffice.core.service;

import com.catalis.administration.backoffice.interfaces.AccountsService;
import com.catalis.administration.backoffice.interfaces.model.BankAccountDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountsServiceImpl implements AccountsService {

    private static final Map<Long, BankAccountDTO> BANK_ACCOUNTS = new HashMap<>();
    private static final Random random = new Random(1);
    private static final String[] BANKS = new String[]{"Aegis Bank",
            "Kindred Credit Union", "Oculus Bank System", "Aspire Credit Union",
            "Meridian Bancorp", "Focus Trust", "Cloud Nine Financial Corp.",
            "Lifespark Banks", "Obelisk Corporation", "Principal Banks"};
    private static final String[] COMPANIES = new String[]{
            "Brewer Holding Century Training", "Broadcast Electric",
            "Chemical General Development", "Digital Agricultural Dynamics",
            "Demondu Semiconductors", "Cota Realisations",
            "European Photographic", "Financial Supplies",
            "Genetics Leasing Mechanical", "Henderson Publishing",
            "Innovative Cyberbank", "Prosaria Equipment Networks", "Kilobank",
            "McCray Mechanical Manufacturing", "Medical Telecommunications",
            "Navarro Horizon Gold Products", "Network Office Contractors",
            "Sharp Network Sciences", "Speciality Investments",
            "Tanner Progressive Healthcare", "Terrell Royal Vistas"};
    private static final String[] IBANS = new String[]{
            "AD12 0001 2030 2003 5910 0100", "AE07 0331 2345 6789 0123 456",
            "AL47 2121 1009 0000 0002 3569 8741", "AT61 1904 3002 3457 3201",
            "AZ21 NABZ 0000 0000 1370 1000 1944", "BA39 1290 0794 0102 8494",
            "BE68 5390 0754 7034", "BG80 BNBG 9661 1020 3456 78",
            "BH67 BMAG 0000 1299 1234 56",
            "BR18 0036 0305 0000 1000 9795 493C 1"};

    static {
        // Initialize bank accounts
        int startingPoint = 1000;
        for (int i = 0; i < 40; i++) {
            BANK_ACCOUNTS.put((long) i + startingPoint,
                    new BankAccountDTO((long) i + startingPoint, getBank(), getIBAN(),
                            getCompany(), getRandomDouble(5000, 100000),
                            getDate(), getImageSource()));
        }
    }

    public Collection<BankAccountDTO> getBankAccounts() {
        return BANK_ACCOUNTS.values();
    }

    @Override
    public Collection<BankAccountDTO> filterAccounts() {
        WebClient webClient = WebClient.builder().build();

        // Call the API using WebClient
        try {
            // Use a parameterized type reference for the response
            ParameterizedTypeReference<Map<String, Object>> responseType = 
                new ParameterizedTypeReference<>() {};

            // Make the API call
            Mono<Map<String, Object>> responseMono = webClient.get()
                .uri("http://localhost:8084/api/v1/accounts?pagination.pageNumber=0&pagination.pageSize=10&pagination.sortDirection=DESC&options.caseInsensitiveStrings=false&options.includeInheritedFields=false")
                .header("accept", "application/json")
                .retrieve()
                .bodyToMono(responseType);

            // Process the response
            Map<String, Object> response = responseMono.block();

            if (response != null && response.containsKey("content")) {
                // Extract the accounts from the response
                List<Map<String, Object>> accounts = extractAccounts(response);

                // Map each account to a BankAccount object
                List<BankAccountDTO> bankAccountDTOS = accounts.stream()
                    .map(this::mapToBankAccount)
                    .collect(Collectors.toList());

                return bankAccountDTOS;
            }

            // Fallback to static data if response is invalid
            return BANK_ACCOUNTS.values();
        } catch (Exception e) {
            // Log the error
            System.err.println("Error calling accounts API: " + e.getMessage());
            // Return fallback data
            return BANK_ACCOUNTS.values();
        }
    }

    /**
     * Extract accounts from the response
     * @param response The response from the API
     * @return List of account maps
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> extractAccounts(Map<String, Object> response) {
        try {
            // Assuming the response has a "content" field that contains the accounts
            Object content = response.get("content");
            if (content instanceof List) {
                return (List<Map<String, Object>>) content;
            }
        } catch (Exception e) {
            System.err.println("Error extracting accounts: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Map an account from the API to a BankAccount object
     * @param account The account data from the API
     * @return A BankAccount object
     */
    private BankAccountDTO mapToBankAccount(Map<String, Object> account) {
        try {
            // Extract fields from the account map
            Long id = Long.valueOf(account.getOrDefault("id", 0L).toString());
            String bank = account.getOrDefault("bankName", "Unknown Bank").toString();
            String accountNumber = account.getOrDefault("accountNumber", "").toString();
            String owner = account.getOrDefault("ownerName", "Unknown Owner").toString();

            // Parse availability as Double
            Double availability = 0.0;
            if (account.containsKey("balance")) {
                Object balanceObj = account.get("balance");
                if (balanceObj instanceof Number) {
                    availability = ((Number) balanceObj).doubleValue();
                } else if (balanceObj != null) {
                    availability = Double.parseDouble(balanceObj.toString());
                }
            }

            // Parse updated date
            LocalDate updated = LocalDate.now();
            if (account.containsKey("lastUpdated")) {
                Object dateObj = account.get("lastUpdated");
                if (dateObj instanceof String) {
                    updated = LocalDate.parse((String) dateObj);
                }
            }

            // Use a default logo path
            String logoPath = "/images/logos/" + (id % 40 + 1) + ".png";

            return new BankAccountDTO(id, bank, accountNumber, owner, availability, updated, logoPath);
        } catch (Exception e) {
            System.err.println("Error mapping account: " + e.getMessage());
            // Return a default BankAccount
            return new BankAccountDTO(0L, "Error", "Error", "Error", 0.0, LocalDate.now(), "/images/logos/1.png");
        }
    }

    private static String getIBAN() {
        return IBANS[random.nextInt(IBANS.length)];
    }

    private static String getCompany() {
        return COMPANIES[random.nextInt(COMPANIES.length)];
    }

    private static String getBank() {
        return BANKS[random.nextInt(BANKS.length)];
    }

    private static LocalDate getDate() {
        return LocalDate.now().minusDays(random.nextInt(20));
    }

    private static String getImageSource() {
        return "/images/logos/" + getRandomInt(1, 40) + ".png";
    }

    private static int getRandomInt(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    private static Double getRandomDouble(int min, int max) {
        return min + (max - min) * random.nextDouble();
    }
}
