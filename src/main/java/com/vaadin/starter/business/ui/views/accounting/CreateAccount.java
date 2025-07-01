package com.vaadin.starter.business.ui.views.accounting;

import com.catalis.core.banking.accounts.sdk.model.AccountDTO;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.starter.business.backend.sdks.services.AccountsService;
import com.vaadin.starter.business.backend.sdks.services.rest.accounts.AccountRequest;
import com.vaadin.starter.business.ui.util.UIUtils;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Dialog for creating a new account.
 */
public class CreateAccount extends Dialog {

    private UI ui;

    // Account type enum
    public enum AccountType {
        CHECKING("CHECKING"),
        SAVINGS("SAVINGS"),
        TERM_DEPOSIT("TERM_DEPOSIT"),
        LOAN("LOAN"),
        CREDIT_CARD("CREDIT_CARD"),
        INVESTMENT("INVESTMENT"),
        MORTGAGE("MORTGAGE"),
        BUSINESS_CHECKING("BUSINESS_CHECKING"),
        BUSINESS_SAVINGS("BUSINESS_SAVINGS"),
        FOREIGN_CURRENCY("FOREIGN_CURRENCY"),
        PREMIUM("PREMIUM"),
        YOUTH("YOUTH"),
        SENIOR("SENIOR"),
        PENSION("PENSION"),
        GOVERNMENT_BENEFITS("GOVERNMENT_BENEFITS");

        private final String value;

        AccountType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Account status enum
    public enum AccountStatus {
        OPEN("OPEN"),
        CLOSED("CLOSED"),
        SUSPENDED("SUSPENDED"),
        DORMANT("DORMANT");

        private final String value;

        AccountStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Account sub-type enum
    public enum AccountSubType {
        STANDARD_CHECKING("STANDARD_CHECKING"),
        INTEREST_CHECKING("INTEREST_CHECKING"),
        REWARDS_CHECKING("REWARDS_CHECKING"),
        FREE_CHECKING("FREE_CHECKING"),
        PREMIUM_CHECKING("PREMIUM_CHECKING"),
        STANDARD_SAVINGS("STANDARD_SAVINGS"),
        HIGH_YIELD_SAVINGS("HIGH_YIELD_SAVINGS"),
        MONEY_MARKET("MONEY_MARKET"),
        CERTIFICATE_OF_DEPOSIT("CERTIFICATE_OF_DEPOSIT"),
        IRA("IRA"),
        FIXED_TERM_DEPOSIT("FIXED_TERM_DEPOSIT"),
        VARIABLE_TERM_DEPOSIT("VARIABLE_TERM_DEPOSIT"),
        PERSONAL_LOAN("PERSONAL_LOAN"),
        AUTO_LOAN("AUTO_LOAN"),
        HELOC("HELOC"),
        STANDARD_CREDIT_CARD("STANDARD_CREDIT_CARD"),
        REWARDS_CREDIT_CARD("REWARDS_CREDIT_CARD"),
        CASHBACK_CREDIT_CARD("CASHBACK_CREDIT_CARD"),
        TRAVEL_CREDIT_CARD("TRAVEL_CREDIT_CARD"),
        BUSINESS_CREDIT_CARD("BUSINESS_CREDIT_CARD"),
        FIXED_RATE_MORTGAGE("FIXED_RATE_MORTGAGE"),
        ADJUSTABLE_RATE_MORTGAGE("ADJUSTABLE_RATE_MORTGAGE"),
        INTEREST_ONLY_MORTGAGE("INTEREST_ONLY_MORTGAGE"),
        STANDARD_BUSINESS_CHECKING("STANDARD_BUSINESS_CHECKING"),
        PREMIUM_BUSINESS_CHECKING("PREMIUM_BUSINESS_CHECKING"),
        STANDARD_BUSINESS_SAVINGS("STANDARD_BUSINESS_SAVINGS"),
        PREMIUM_BUSINESS_SAVINGS("PREMIUM_BUSINESS_SAVINGS"),
        MULTI_CURRENCY("MULTI_CURRENCY"),
        SINGLE_CURRENCY_FOREIGN("SINGLE_CURRENCY_FOREIGN"),
        RELATIONSHIP_PREMIUM("RELATIONSHIP_PREMIUM"),
        WEALTH_MANAGEMENT("WEALTH_MANAGEMENT"),
        YOUTH_SAVINGS("YOUTH_SAVINGS"),
        STUDENT_CHECKING("STUDENT_CHECKING"),
        SENIOR_CHECKING("SENIOR_CHECKING"),
        SENIOR_SAVINGS("SENIOR_SAVINGS"),
        PENSION_DISTRIBUTION("PENSION_DISTRIBUTION"),
        SOCIAL_SECURITY_BENEFITS("SOCIAL_SECURITY_BENEFITS"),
        GOVERNMENT_ASSISTANCE("GOVERNMENT_ASSISTANCE");

        private final String value;

        AccountSubType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Tax reporting status enum
    public enum TaxReportingStatus {
        REPORTABLE("REPORTABLE"),
        EXEMPT("EXEMPT"),
        BACKUP_WITHHOLDING("BACKUP_WITHHOLDING"),
        FATCA_REPORTABLE("FATCA_REPORTABLE"),
        CRS_REPORTABLE("CRS_REPORTABLE"),
        FATCA_CRS_REPORTABLE("FATCA_CRS_REPORTABLE"),
        PENDING_DOCUMENTATION("PENDING_DOCUMENTATION"),
        INCOMPLETE_INFORMATION("INCOMPLETE_INFORMATION"),
        NON_REPORTABLE("NON_REPORTABLE");

        private final String value;

        TaxReportingStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    // Regulatory status enum
    public enum RegulatoryStatus {
        COMPLIANT("COMPLIANT"),
        PENDING_DOCUMENTATION("PENDING_DOCUMENTATION"),
        ENHANCED_DUE_DILIGENCE("ENHANCED_DUE_DILIGENCE"),
        UNDER_REVIEW("UNDER_REVIEW"),
        SPECIAL_MONITORING("SPECIAL_MONITORING"),
        SANCTIONS_SCREENING("SANCTIONS_SCREENING"),
        PEP_MONITORING("PEP_MONITORING"),
        HIGH_RISK_MONITORING("HIGH_RISK_MONITORING"),
        REGULATORY_EXEMPTION("REGULATORY_EXEMPTION"),
        NON_COMPLIANT("NON_COMPLIANT"),
        REGULATORY_RESTRICTED("REGULATORY_RESTRICTED");

        private final String value;

        RegulatoryStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private final AccountsService accountsService;
    private final Runnable onSuccess;

    // Form fields
    private TextField accountNumberField;
    private ComboBox<AccountType> accountTypeField;
    private TextField currencyField;
    private NumberField balanceField;
    private ComboBox<AccountStatus> statusField;
    private TextField customerIdField;
    private TextField descriptionField;
    private TextField contractIdField;
    private TextField branchIdField;
    private ComboBox<AccountSubType> accountSubTypeField;
    private ComboBox<TaxReportingStatus> taxReportingStatusField;
    private ComboBox<RegulatoryStatus> regulatoryStatusField;

    private Binder<AccountRequest> binder;

    /**
     * Constructor for the CreateAccount dialog.
     *
     * @param accountsService The service to use for creating accounts
     * @param onSuccess A callback to run when an account is successfully created
     */
    public CreateAccount(AccountsService accountsService, Runnable onSuccess) {
        this.accountsService = accountsService;
        this.onSuccess = onSuccess;
        this.ui = UI.getCurrent();

        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
        setWidth("600px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        H3 title = new H3("Create New Account");
        layout.add(title);

        FormLayout formLayout = createForm();
        layout.add(formLayout);

        HorizontalLayout buttonLayout = createButtonLayout();
        layout.add(buttonLayout);

        add(layout);
    }

    private FormLayout createForm() {
        binder = new Binder<>(AccountRequest.class);

        // Initialize form fields
        accountNumberField = new TextField("Account Number");
        accountNumberField.setRequired(true);
        binder.forField(accountNumberField)
                .asRequired("Account Number is required")
                .bind(AccountRequest::getAccountNumber, AccountRequest::setAccountNumber);

        accountTypeField = new ComboBox<>("Account Type");
        accountTypeField.setItems(Arrays.asList(AccountType.values()));
        accountTypeField.setRequired(true);
        accountTypeField.setAllowCustomValue(false);
        binder.forField(accountTypeField)
                .asRequired("Account Type is required")
                .withConverter(
                        AccountType::getValue,
                        value -> Arrays.stream(AccountType.values())
                                .filter(type -> type.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid account type"
                )
                .bind(AccountRequest::getAccountType, AccountRequest::setAccountType);

        currencyField = new TextField("Currency");
        currencyField.setRequired(true);
        binder.forField(currencyField)
                .asRequired("Currency is required")
                .bind(AccountRequest::getCurrency, AccountRequest::setCurrency);

        balanceField = new NumberField("Balance");
        balanceField.setRequiredIndicatorVisible(true);
        binder.forField(balanceField)
                .asRequired("Balance is required")
                .bind(AccountRequest::getBalance, AccountRequest::setBalance);

        statusField = new ComboBox<>("Status");
        statusField.setItems(Arrays.asList(AccountStatus.values()));
        statusField.setRequired(true);
        statusField.setAllowCustomValue(false);
        binder.forField(statusField)
                .asRequired("Status is required")
                .withConverter(
                        AccountStatus::getValue,
                        value -> Arrays.stream(AccountStatus.values())
                                .filter(status -> status.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid account status"
                )
                .bind(AccountRequest::getAccountStatus, AccountRequest::setAccountStatus);

        customerIdField = new TextField("Customer ID");
        customerIdField.setRequired(true);
        binder.forField(customerIdField)
                .asRequired("Customer ID is required")
                .withConverter(
                        Long::valueOf,
                        String::valueOf,
                        "Customer ID must be a number"
                )
                .bind(AccountRequest::getCustomerId, AccountRequest::setCustomerId);

        descriptionField = new TextField("Description");
        binder.forField(descriptionField)
                .bind(AccountRequest::getDescription, AccountRequest::setDescription);

        // Initialize new fields
        contractIdField = new TextField("Contract ID");
        binder.forField(contractIdField)
                .withConverter(
                        Long::valueOf,
                        String::valueOf,
                        "Contract ID must be a number"
                )
                .bind(AccountRequest::getContractId, AccountRequest::setContractId);

        branchIdField = new TextField("Branch ID");
        binder.forField(branchIdField)
                .withConverter(
                        Long::valueOf,
                        String::valueOf,
                        "Branch ID must be a number"
                )
                .bind(AccountRequest::getBranchId, AccountRequest::setBranchId);

        accountSubTypeField = new ComboBox<>("Account Sub Type");
        accountSubTypeField.setItems(Arrays.asList(AccountSubType.values()));
        accountSubTypeField.setAllowCustomValue(false);
        binder.forField(accountSubTypeField)
                .withConverter(
                        AccountSubType::getValue,
                        value -> Arrays.stream(AccountSubType.values())
                                .filter(type -> type.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid account sub type"
                )
                .bind(AccountRequest::getAccountSubType, AccountRequest::setAccountSubType);

        taxReportingStatusField = new ComboBox<>("Tax Reporting Status");
        taxReportingStatusField.setItems(Arrays.asList(TaxReportingStatus.values()));
        taxReportingStatusField.setAllowCustomValue(false);
        binder.forField(taxReportingStatusField)
                .withConverter(
                        TaxReportingStatus::getValue,
                        value -> Arrays.stream(TaxReportingStatus.values())
                                .filter(status -> status.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid tax reporting status"
                )
                .bind(AccountRequest::getTaxReportingStatus, AccountRequest::setTaxReportingStatus);

        regulatoryStatusField = new ComboBox<>("Regulatory Status");
        regulatoryStatusField.setItems(Arrays.asList(RegulatoryStatus.values()));
        regulatoryStatusField.setAllowCustomValue(false);
        binder.forField(regulatoryStatusField)
                .withConverter(
                        RegulatoryStatus::getValue,
                        value -> Arrays.stream(RegulatoryStatus.values())
                                .filter(status -> status.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid regulatory status"
                )
                .bind(AccountRequest::getRegulatoryStatus, AccountRequest::setRegulatoryStatus);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.add(
                accountNumberField,
                accountTypeField,
                currencyField,
                balanceField,
                statusField,
                customerIdField,
                descriptionField,
                contractIdField,
                branchIdField,
                accountSubTypeField,
                taxReportingStatusField,
                regulatoryStatusField
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("500px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        return formLayout;
    }

    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(e -> saveAccount());

        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> close());

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setSpacing(true);
        return buttonLayout;
    }

    private void saveAccount() {
        try {
            AccountRequest accountRequest = new AccountRequest();
            binder.writeBean(accountRequest);
            accountRequest.setOpenDate(LocalDate.now());
            // Call the service to create the account
            Mono<ResponseEntity<AccountDTO>> result = accountsService.createAccount(accountRequest);

            result.subscribe(
                response -> {
                    // Use UI.access() to safely update the UI from the async callback
                    ui.access(() -> {
                        if (response.getStatusCode().is2xxSuccessful()) {
                            Notification.show("Account created successfully", 3000, Notification.Position.MIDDLE)
                                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                            close();
                            if (onSuccess != null) {
                                onSuccess.run();
                            }
                        } else {
                            Notification.show("Failed to create account: " + response.getStatusCode(), 
                                    3000, Notification.Position.MIDDLE)
                                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }
                    });
                },
                error -> {
                    System.out.println("[DEBUG_LOG] Error creating account: " + error.getMessage());
                    error.printStackTrace();

                    // Handle error in UI thread
                    ui.access(() -> {
                        Notification.show("Error creating account: " + error.getMessage(), 
                                3000, Notification.Position.MIDDLE)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    });
                }
            );
        } catch (ValidationException validationException) {
            // This is already in the UI thread, but for consistency, we'll use UI.access() here too
            ui.access(() -> {
                Notification.show("Please correct the errors in the form", 
                        3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            });
        }
    }
}
