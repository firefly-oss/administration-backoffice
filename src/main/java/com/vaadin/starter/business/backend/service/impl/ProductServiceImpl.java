package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.dummy.Contract;
import com.vaadin.starter.business.dummy.LendingConfig;
import com.vaadin.starter.business.dummy.Product;
import com.vaadin.starter.business.dummy.RateFee;
import com.vaadin.starter.business.backend.dto.products.ContractDTO;
import com.vaadin.starter.business.backend.dto.products.LendingConfigDTO;
import com.vaadin.starter.business.backend.dto.products.ProductDTO;
import com.vaadin.starter.business.backend.dto.products.RateFeeDTO;
import com.vaadin.starter.business.backend.mapper.products.ContractMapper;
import com.vaadin.starter.business.backend.mapper.products.LendingConfigMapper;
import com.vaadin.starter.business.backend.mapper.products.ProductMapper;
import com.vaadin.starter.business.backend.mapper.products.RateFeeMapper;
import com.vaadin.starter.business.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, RateFee> ratesFees = new HashMap<>();
    private final Map<String, Contract> contracts = new HashMap<>();
    private final Map<String, LendingConfig> lendingConfigs = new HashMap<>();

    private final ProductMapper productMapper;
    private final RateFeeMapper rateFeeMapper;
    private final ContractMapper contractMapper;
    private final LendingConfigMapper lendingConfigMapper;

    /**
     * Constructor that initializes the mock data and injects the mappers.
     *
     * @param productMapper      the mapper for Product objects
     * @param rateFeeMapper      the mapper for RateFee objects
     * @param contractMapper     the mapper for Contract objects
     * @param lendingConfigMapper the mapper for LendingConfig objects
     */
    @Autowired
    public ProductServiceImpl(ProductMapper productMapper,
                             RateFeeMapper rateFeeMapper,
                             ContractMapper contractMapper,
                             LendingConfigMapper lendingConfigMapper) {
        this.productMapper = productMapper;
        this.rateFeeMapper = rateFeeMapper;
        this.contractMapper = contractMapper;
        this.lendingConfigMapper = lendingConfigMapper;
        initProducts();
        initRatesFees();
        initContracts();
        initLendingConfigs();
    }

    @Override
    public Collection<Product> getProducts() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<ProductDTO> dtos = productMapper.toDtoList(products.values());
        return productMapper.toEntityList(dtos);
    }

    @Override
    public Product getProduct(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ProductDTO dto = productMapper.toDto(products.get(id));
        return productMapper.toEntity(dto);
    }

    @Override
    public Collection<RateFee> getRatesFees() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<RateFeeDTO> dtos = rateFeeMapper.toDtoList(ratesFees.values());
        return rateFeeMapper.toEntityList(dtos);
    }

    @Override
    public RateFee getRateFee(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        RateFeeDTO dto = rateFeeMapper.toDto(ratesFees.get(id));
        return rateFeeMapper.toEntity(dto);
    }

    @Override
    public Collection<Contract> getContracts() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<ContractDTO> dtos = contractMapper.toDtoList(contracts.values());
        return contractMapper.toEntityList(dtos);
    }

    @Override
    public Contract getContract(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ContractDTO dto = contractMapper.toDto(contracts.get(id));
        return contractMapper.toEntity(dto);
    }

    @Override
    public Collection<LendingConfig> getLendingConfigs() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<LendingConfigDTO> dtos = lendingConfigMapper.toDtoList(lendingConfigs.values());
        return lendingConfigMapper.toEntityList(dtos);
    }

    @Override
    public LendingConfig getLendingConfig(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        LendingConfigDTO dto = lendingConfigMapper.toDto(lendingConfigs.get(id));
        return lendingConfigMapper.toEntity(dto);
    }

    /**
     * Initialize product data.
     */
    private void initProducts() {
        Arrays.asList(
            new Product("P001", "Savings Account", "Accounts", 
                "Basic savings account with competitive interest rates", 
                0.0, true, LocalDate.now().minusDays(15)),
            new Product("P002", "Checking Account", "Accounts", 
                "Everyday banking account with no monthly fees", 
                0.0, true, LocalDate.now().minusDays(30)),
            new Product("P003", "Personal Loan", "Loans", 
                "Unsecured personal loan with flexible repayment options", 
                5.99, true, LocalDate.now().minusDays(45)),
            new Product("P004", "Mortgage", "Loans", 
                "Home loan with competitive rates and terms", 
                3.49, true, LocalDate.now().minusDays(60)),
            new Product("P005", "Credit Card", "Cards", 
                "Rewards credit card with cashback on purchases", 
                19.99, true, LocalDate.now().minusDays(90)),
            new Product("P006", "Investment Account", "Investments", 
                "Managed investment portfolio with diversified assets", 
                0.0, false, LocalDate.now().minusDays(120))
        ).forEach(product -> products.put(product.getId(), product));
    }

    /**
     * Initialize rates and fees data.
     */
    private void initRatesFees() {
        LocalDate now = LocalDate.now();
        Arrays.asList(
            new RateFee("RF001", "Savings Interest Rate", "Accounts", "Interest Rate", 
                1.25, "Annual Percentage Yield", true, 
                now.minusDays(30), now.plusMonths(6)),
            new RateFee("RF002", "Checking Monthly Fee", "Accounts", "Fee", 
                5.00, "Fixed Amount", false, 
                now.minusDays(60), now.plusMonths(12)),
            new RateFee("RF003", "Personal Loan Interest", "Loans", "Interest Rate", 
                5.99, "Annual Percentage Rate", true, 
                now.minusDays(45), now.plusMonths(3)),
            new RateFee("RF004", "Mortgage Rate", "Loans", "Interest Rate", 
                3.49, "Annual Percentage Rate", true, 
                now.minusDays(15), now.plusMonths(6)),
            new RateFee("RF005", "Credit Card APR", "Cards", "Interest Rate", 
                19.99, "Annual Percentage Rate", true, 
                now.minusDays(90), now.plusMonths(12)),
            new RateFee("RF006", "Late Payment Fee", "General", "Fee", 
                25.00, "Fixed Amount", true, 
                now.minusDays(120), now.plusMonths(24)),
            new RateFee("RF007", "Wire Transfer Fee", "Services", "Fee", 
                15.00, "Fixed Amount", true, 
                now.minusDays(30), now.plusMonths(12))
        ).forEach(rateFee -> ratesFees.put(rateFee.getId(), rateFee));
    }

    /**
     * Initialize contract data.
     */
    private void initContracts() {
        LocalDate now = LocalDate.now();
        Arrays.asList(
            new Contract("C001", "Personal Loan Agreement", "Loan", "John Smith", 
                "Active", now.minusDays(30), now.plusYears(5), 
                "Sarah Johnson", "/documents/loans/C001.pdf"),
            new Contract("C002", "Mortgage Contract", "Mortgage", "Emily Davis", 
                "Active", now.minusDays(60), now.plusYears(30), 
                "Michael Brown", "/documents/mortgages/C002.pdf"),
            new Contract("C003", "Credit Card Agreement", "Credit Card", "Robert Wilson", 
                "Active", now.minusDays(45), now.plusYears(2), 
                "Jennifer Lee", "/documents/cards/C003.pdf"),
            new Contract("C004", "Business Loan Contract", "Business Loan", "Acme Corporation", 
                "Pending", now.plusDays(15), now.plusYears(7), 
                "David Miller", "/documents/business/C004.pdf"),
            new Contract("C005", "Savings Account Terms", "Account", "Lisa Taylor", 
                "Active", now.minusDays(90), null, 
                "James Anderson", "/documents/accounts/C005.pdf"),
            new Contract("C006", "Investment Portfolio Agreement", "Investment", "Thomas Moore", 
                "Expired", now.minusYears(3), now.minusMonths(1), 
                "Patricia White", "/documents/investments/C006.pdf"),
            new Contract("C007", "Insurance Policy", "Insurance", "Karen Martinez", 
                "Draft", null, null, 
                "Robert Johnson", "/documents/insurance/C007.pdf")
        ).forEach(contract -> contracts.put(contract.getId(), contract));
    }

    /**
     * Initialize lending configuration data.
     */
    private void initLendingConfigs() {
        LocalDate now = LocalDate.now();
        Arrays.asList(
            new LendingConfig("LC001", "Personal Loan - Standard", "Personal",
                1000.0, 25000.0, 5.99, 12, 60, "Standard", true, now.minusDays(15)),
            new LendingConfig("LC002", "Personal Loan - Premium", "Personal",
                5000.0, 50000.0, 4.99, 12, 84, "Premium", true, now.minusDays(30)),
            new LendingConfig("LC003", "Mortgage - Fixed Rate", "Mortgage",
                50000.0, 1000000.0, 3.49, 120, 360, "Standard", true, now.minusDays(45)),
            new LendingConfig("LC004", "Mortgage - Adjustable Rate", "Mortgage",
                50000.0, 1500000.0, 3.25, 120, 360, "Standard", true, now.minusDays(60)),
            new LendingConfig("LC005", "Auto Loan - New Vehicle", "Auto",
                5000.0, 100000.0, 4.25, 12, 84, "Standard", true, now.minusDays(75)),
            new LendingConfig("LC006", "Auto Loan - Used Vehicle", "Auto",
                3000.0, 50000.0, 5.25, 12, 60, "Standard", true, now.minusDays(90)),
            new LendingConfig("LC007", "Business Loan - Startup", "Business",
                10000.0, 250000.0, 7.99, 12, 120, "High Risk", false, now.minusDays(120))
        ).forEach(lendingConfig -> lendingConfigs.put(lendingConfig.getId(), lendingConfig));
    }
}