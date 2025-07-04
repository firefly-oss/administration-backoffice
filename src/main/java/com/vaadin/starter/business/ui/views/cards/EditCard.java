package com.vaadin.starter.business.ui.views.cards;

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

import java.util.Arrays;
import java.util.Objects;

/**
 * Dialog for editing an existing account.
 */
public class EditCard extends Dialog {

    private final AccountsService accountsService;
    private final Runnable onSuccess;
    private final UI ui;
    private final AccountDTO account;

    // Form fields
    private TextField accountNumberField;
    private ComboBox<CreateCard.AccountType> accountTypeField;
    private TextField currencyField;
    private NumberField balanceField;
    private ComboBox<CreateCard.AccountStatus> statusField;
    private TextField customerIdField;
    private TextField descriptionField;
    private TextField contractIdField;
    private TextField branchIdField;
    private ComboBox<CreateCard.AccountSubType> accountSubTypeField;
    private ComboBox<CreateCard.TaxReportingStatus> taxReportingStatusField;
    private ComboBox<CreateCard.RegulatoryStatus> regulatoryStatusField;

    private Binder<AccountRequest> binder;

    /**
     * Constructor for the EditAccount dialog.
     *
     * @param accountsService The service to use for updating accounts
     * @param account The account to edit
     * @param onSuccess A callback to run when an account is successfully updated
     */
    public EditCard(AccountsService accountsService, AccountDTO account, Runnable onSuccess) {
        this.accountsService = accountsService;
        this.account = account;
        this.onSuccess = onSuccess;
        this.ui = UI.getCurrent();

        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
        setWidth("600px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        H3 title = new H3("Edit Account");
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
        accountTypeField.setItems(Arrays.asList(CreateCard.AccountType.values()));
        accountTypeField.setRequired(true);
        accountTypeField.setAllowCustomValue(false);
        binder.forField(accountTypeField)
                .asRequired("Account Type is required")
                .withConverter(
                        CreateCard.AccountType::getValue,
                        value -> Arrays.stream(CreateCard.AccountType.values())
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
        statusField.setItems(Arrays.asList(CreateCard.AccountStatus.values()));
        statusField.setRequired(true);
        statusField.setAllowCustomValue(false);
        binder.forField(statusField)
                .asRequired("Status is required")
                .withConverter(
                        CreateCard.AccountStatus::getValue,
                        value -> Arrays.stream(CreateCard.AccountStatus.values())
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
        accountSubTypeField.setItems(Arrays.asList(CreateCard.AccountSubType.values()));
        accountSubTypeField.setAllowCustomValue(false);
        binder.forField(accountSubTypeField)
                .withConverter(
                        CreateCard.AccountSubType::getValue,
                        value -> Arrays.stream(CreateCard.AccountSubType.values())
                                .filter(type -> type.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid account sub type"
                )
                .bind(AccountRequest::getAccountSubType, AccountRequest::setAccountSubType);

        taxReportingStatusField = new ComboBox<>("Tax Reporting Status");
        taxReportingStatusField.setItems(Arrays.asList(CreateCard.TaxReportingStatus.values()));
        taxReportingStatusField.setAllowCustomValue(false);
        binder.forField(taxReportingStatusField)
                .withConverter(
                        CreateCard.TaxReportingStatus::getValue,
                        value -> Arrays.stream(CreateCard.TaxReportingStatus.values())
                                .filter(status -> status.getValue().equals(value))
                                .findFirst()
                                .orElse(null),
                        "Invalid tax reporting status"
                )
                .bind(AccountRequest::getTaxReportingStatus, AccountRequest::setTaxReportingStatus);

        regulatoryStatusField = new ComboBox<>("Regulatory Status");
        regulatoryStatusField.setItems(Arrays.asList(CreateCard.RegulatoryStatus.values()));
        regulatoryStatusField.setAllowCustomValue(false);
        binder.forField(regulatoryStatusField)
                .withConverter(
                        CreateCard.RegulatoryStatus::getValue,
                        value -> Arrays.stream(CreateCard.RegulatoryStatus.values())
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

        // Pre-fill form with account data
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber(account.getAccountNumber());
        if (account.getAccountType() != null) {
            accountRequest.setAccountType(account.getAccountType().name());
        }
        accountRequest.setCurrency(account.getCurrency());
        // AccountDTO doesn't have getBalance method, it's likely managed differently
        if (account.getAccountStatus() != null) {
            accountRequest.setAccountStatus(account.getAccountStatus().name());
        }
        // AccountDTO doesn't have getCustomerId method, it might be in a different field
        accountRequest.setDescription(account.getDescription());
        accountRequest.setContractId(account.getContractId());
        accountRequest.setBranchId(account.getBranchId());
        if (account.getAccountSubType() != null) {
            accountRequest.setAccountSubType(account.getAccountSubType().name());
        }
        if (account.getTaxReportingStatus() != null) {
            accountRequest.setTaxReportingStatus(account.getTaxReportingStatus().name());
        }
        if (account.getRegulatoryStatus() != null) {
            accountRequest.setRegulatoryStatus(account.getRegulatoryStatus().name());
        }
        accountRequest.setOpenDate(account.getOpenDate());
        accountRequest.setBalance(Objects.requireNonNull(account.getMinimumBalance()).doubleValue());

        binder.readBean(accountRequest);

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
            accountRequest.setOpenDate(account.getOpenDate()); // Preserve the original open date

            // Call the service to update the account
            Mono<ResponseEntity<AccountDTO>> result = accountsService.updateAccount(account.getAccountId(), accountRequest);

            result.subscribe(
                response -> {
                    // Use UI.access() to safely update the UI from the async callback
                    ui.access(() -> {
                        if (response.getStatusCode().is2xxSuccessful()) {
                            Notification.show("Account updated successfully", 3000, Notification.Position.MIDDLE)
                                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                            close();
                            if (onSuccess != null) {
                                onSuccess.run();
                            }
                        } else {
                            Notification.show("Failed to update account: " + response.getStatusCode(), 
                                    3000, Notification.Position.MIDDLE)
                                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }
                    });
                },
                error -> {
                    System.out.println("[DEBUG_LOG] Error updating account: " + error.getMessage());
                    error.printStackTrace();

                    // Handle error in UI thread
                    ui.access(() -> {
                        Notification.show("Error updating account: " + error.getMessage(), 
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
