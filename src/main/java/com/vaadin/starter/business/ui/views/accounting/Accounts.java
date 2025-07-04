package com.vaadin.starter.business.ui.views.accounting;

import com.catalis.core.banking.accounts.sdk.model.AccountDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.mapper.accounting.BankAccountMapper;
import com.vaadin.starter.business.backend.sdks.services.AccountsService;
import com.vaadin.starter.business.backend.sdks.services.rest.accounts.AccountFilterRequest;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@PageTitle("Accounts")
@Route(value = "accounts", layout = MainLayout.class)
public class Accounts extends ViewFrame {

	public static final int MOBILE_BREAKPOINT = 480;
	private Grid<AccountDTO> grid;
	private Registration resizeListener;
	private ListDataProvider<AccountDTO> dataProvider;
	private UI ui;

	// Filter form fields
	private TextField accountNumberFilter;
	private TextField accountTypeFilter;
	private TextField currencyFilter;
	private NumberField minBalanceFilter;
	private NumberField maxBalanceFilter;
	private TextField statusFilter;
	private TextField customerIdFilter;
	private TextField descriptionFilter;
	private DatePicker dateCreatedFilter;
	private DatePicker dateCreatedFromFilter;
	private DatePicker dateCreatedToFilter;
	private DatePicker dateUpdatedFilter;
	private DatePicker dateUpdatedFromFilter;
	private DatePicker dateUpdatedToFilter;

	private final AccountsService accountsService;
	private final BankAccountMapper bankAccountMapper;

	@Autowired
	public Accounts(AccountsService accountsService, BankAccountMapper bankAccountMapper) {
		this.accountsService = accountsService;
		this.bankAccountMapper = bankAccountMapper;
		setViewContent(createContent());
	}

	private Component createContent() {
		FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
		content.setBoxSizing(BoxSizing.BORDER_BOX);
		content.setHeightFull();
		content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
		content.setFlexDirection(FlexDirection.COLUMN);
		return content;
	}

	private Component createFilterForm() {
		// Initialize filter fields
		accountNumberFilter = new TextField();
		accountNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
		accountNumberFilter.setClearButtonVisible(true);

		accountTypeFilter = new TextField();
		accountTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
		accountTypeFilter.setClearButtonVisible(true);

		currencyFilter = new TextField();
		currencyFilter.setValueChangeMode(ValueChangeMode.EAGER);
		currencyFilter.setClearButtonVisible(true);

		minBalanceFilter = new NumberField();
		minBalanceFilter.setClearButtonVisible(true);

		maxBalanceFilter = new NumberField();
		maxBalanceFilter.setClearButtonVisible(true);

		statusFilter = new TextField();
		statusFilter.setValueChangeMode(ValueChangeMode.EAGER);
		statusFilter.setClearButtonVisible(true);

		customerIdFilter = new TextField();
		customerIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
		customerIdFilter.setClearButtonVisible(true);

		descriptionFilter = new TextField();
		descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
		descriptionFilter.setClearButtonVisible(true);

		dateCreatedFilter = new DatePicker();
		dateCreatedFilter.setClearButtonVisible(true);

		dateCreatedFromFilter = new DatePicker();
		dateCreatedFromFilter.setClearButtonVisible(true);

		dateCreatedToFilter = new DatePicker();
		dateCreatedToFilter.setClearButtonVisible(true);

		dateUpdatedFilter = new DatePicker();
		dateUpdatedFilter.setClearButtonVisible(true);

		dateUpdatedFromFilter = new DatePicker();
		dateUpdatedFromFilter.setClearButtonVisible(true);

		dateUpdatedToFilter = new DatePicker();
		dateUpdatedToFilter.setClearButtonVisible(true);

		// Create buttons
		Button searchButton = UIUtils.createPrimaryButton("Search");
		searchButton.addClickListener(e -> applyFilters());

		Button clearButton = UIUtils.createTertiaryButton("Clear");
		clearButton.addClickListener(e -> clearFilters());

		Button createAccountButton = UIUtils.createSuccessButton("Create Account");
		createAccountButton.addClickListener(e -> openCreateAccountDialog());

		// Create a wrapper for search and clear buttons (right side)
		HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
		rightButtons.setSpacing(true);

		// Create button layout with Create Account on left and search/clear on right
		HorizontalLayout buttonLayout = new HorizontalLayout(createAccountButton, rightButtons);
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

		// Create form layout
		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(accountNumberFilter, "Account Number");
		formLayout.addFormItem(accountTypeFilter, "Account Type");
		formLayout.addFormItem(currencyFilter, "Currency");
		formLayout.addFormItem(statusFilter, "Status");
		formLayout.addFormItem(customerIdFilter, "Customer ID");
		formLayout.addFormItem(descriptionFilter, "Description");
		formLayout.addFormItem(minBalanceFilter, "Min Balance");
		formLayout.addFormItem(maxBalanceFilter, "Max Balance");
		formLayout.addFormItem(dateCreatedFromFilter, "Created From");
		formLayout.addFormItem(dateCreatedToFilter, "Created To");
		formLayout.addFormItem(dateUpdatedFromFilter, "Updated From");
		formLayout.addFormItem(dateUpdatedToFilter, "Updated To");

		formLayout.setResponsiveSteps(
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
		);

		// Create a container for the form and buttons
		Div formContainer = new Div(formLayout, buttonLayout);
		formContainer.getStyle().set("padding", "1em");
		formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
		formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
		formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
		formContainer.getStyle().set("margin-bottom", "1em");

		return formContainer;
	}

	private Grid<AccountDTO> createGrid() {
		grid = new Grid<>();
		grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
		grid.addThemeName("mobile");

		grid.setId("accounts");
		grid.setSizeFull();

		// Configure grid columns
		grid.addColumn(AccountDTO::getAccountId)
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setFrozen(true)
				.setHeader("Account ID")
				.setSortable(true);
		grid.addColumn(AccountDTO::getAccountNumber)
				.setAutoWidth(true)
				.setHeader("Account Number")
				.setSortable(true);
		grid.addColumn(account -> account.getAccountType() != null ? account.getAccountType().toString() : "")
				.setAutoWidth(true)
				.setHeader("Account Type")
				.setSortable(true);
		grid.addColumn(AccountDTO::getCurrency)
				.setAutoWidth(true)
				.setHeader("Currency")
				.setSortable(true);
		grid.addColumn(account -> account.getAccountStatus() != null ? account.getAccountStatus().toString() : "")
				.setAutoWidth(true)
				.setHeader("Status")
				.setSortable(true);
		grid.addColumn(account -> account.getMinimumBalance() != null ? account.getMinimumBalance().toString() : "N/A")
				.setAutoWidth(true)
				.setFlexGrow(0)
				.setHeader("Minimum Balance")
				.setTextAlign(ColumnTextAlign.END);
		grid.addColumn(new LocalDateRenderer<>(AccountDTO::getOpenDate, "MMM dd, YYYY"))
				.setAutoWidth(true)
				.setComparator(AccountDTO::getOpenDate)
				.setFlexGrow(0)
				.setHeader("Open Date");
		grid.addColumn(AccountDTO::getDescription)
				.setAutoWidth(true)
				.setHeader("Description")
				.setSortable(true);

		// Initialize with empty data provider
		dataProvider = new ListDataProvider<>(List.of());
		grid.setDataProvider(dataProvider);

		return grid;
	}

	private void loadAccountsData() {
		AccountFilterRequest filterRequest = new AccountFilterRequest();
		filterRequest.setPaginationPageNumber(0);
		filterRequest.setPaginationPageSize(10);
		filterRequest.setPaginationSortBy("account_id");
		filterRequest.setPaginationSortDirection("DESC");

		System.out.println("[DEBUG_LOG] Starting to load accounts data");

		accountsService.filterAccounts(filterRequest)
				.subscribe(response -> {
					System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
					if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
						List<AccountDTO> sdkAccounts = response.getBody().getContent();
						System.out.println("[DEBUG_LOG] Received accounts: " + (sdkAccounts != null ? sdkAccounts.size() : "null"));

						if (sdkAccounts != null && !sdkAccounts.isEmpty()) {
							System.out.println("[DEBUG_LOG] First account ID: " + sdkAccounts.get(0).getAccountId());
						}

						if (sdkAccounts != null) {
							// Use UI.access() to safely update the UI from the async callback
							ui.access(() -> {
								try {
									dataProvider = new ListDataProvider<>(sdkAccounts);
									grid.setDataProvider(dataProvider);
									System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + sdkAccounts.size() + " items");
									ui.push(); // Force UI update to the client
								} catch (Exception e) {
									System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
								}
							});
						}
					} else {
						System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
					}
				}, error -> {
					System.out.println("[DEBUG_LOG] Error in filterAccounts: " + error.getMessage());

					// Handle error in UI thread
					ui.access(() -> {
						// You could show a notification here
						System.out.println("[DEBUG_LOG] Error handled in UI thread");
					});
				});
	}

	private void viewDetails(AccountDTO account) {
		UI.getCurrent().navigate(AccountDetails.class, account.getAccountId());
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getUI().ifPresent(currentUI -> {
			this.ui = currentUI;
			Page page = currentUI.getPage();
			resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
			page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));

			// Load data after UI is completely initialized
			System.out.println("[DEBUG_LOG] UI attached, loading accounts data");
			loadAccountsData();
		});
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		if (resizeListener != null) {
			resizeListener.remove();
		}
		super.onDetach(detachEvent);
	}

	private void updateVisibleColumns(int width) {
		boolean mobile = width < MOBILE_BREAKPOINT;
		List<Grid.Column<AccountDTO>> columns = grid.getColumns();

		// "Desktop" columns
		for (int i = 0; i < columns.size(); i++) {
			columns.get(i).setVisible(!mobile);
		}
	}

	private void applyFilters() {
		if (dataProvider == null) {
			System.out.println("[DEBUG_LOG] DataProvider is null, cannot apply filters");
			return;
		}

		dataProvider.clearFilters();

		// Apply account number filter
		if (accountNumberFilter.getValue() != null && !accountNumberFilter.getValue().isEmpty()) {
			String accountNumberValue = accountNumberFilter.getValue().toLowerCase();
			dataProvider.addFilter(account ->
					account.getAccountNumber() != null &&
							account.getAccountNumber().toLowerCase().contains(accountNumberValue));
		}

		// Apply account type filter
		if (accountTypeFilter.getValue() != null && !accountTypeFilter.getValue().isEmpty()) {
			String accountTypeValue = accountTypeFilter.getValue().toLowerCase();
			dataProvider.addFilter(account ->
					account.getAccountType() != null &&
							account.getAccountType().toString().toLowerCase().contains(accountTypeValue));
		}

		// Apply currency filter
		if (currencyFilter.getValue() != null && !currencyFilter.getValue().isEmpty()) {
			String currencyValue = currencyFilter.getValue().toLowerCase();
			dataProvider.addFilter(account ->
					account.getCurrency() != null &&
							account.getCurrency().toLowerCase().contains(currencyValue));
		}

		// Apply min balance filter
		if (minBalanceFilter.getValue() != null) {
			Double minValue = minBalanceFilter.getValue();
			dataProvider.addFilter(account ->
					account.getMinimumBalance() != null &&
							account.getMinimumBalance().doubleValue() >= minValue);
		}

		// Apply max balance filter
		if (maxBalanceFilter.getValue() != null) {
			Double maxValue = maxBalanceFilter.getValue();
			dataProvider.addFilter(account ->
					account.getMinimumBalance() != null &&
							account.getMinimumBalance().doubleValue() <= maxValue);
		}

		// Apply status filter
		if (statusFilter.getValue() != null && !statusFilter.getValue().isEmpty()) {
			String statusValue = statusFilter.getValue().toLowerCase();
			dataProvider.addFilter(account ->
					account.getAccountStatus() != null &&
							account.getAccountStatus().toString().toLowerCase().contains(statusValue));
		}

		// Apply customer ID filter
		if (customerIdFilter.getValue() != null && !customerIdFilter.getValue().isEmpty()) {
			try {
				Long customerId = Long.parseLong(customerIdFilter.getValue());
				dataProvider.addFilter(account -> account.getAccountId() != null && account.getAccountId().equals(customerId));
			} catch (NumberFormatException e) {
				// Invalid ID format, ignore this filter
				System.out.println("[DEBUG_LOG] Invalid customer ID format: " + customerIdFilter.getValue());
			}
		}

		// Apply description filter
		if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
			String descriptionValue = descriptionFilter.getValue().toLowerCase();
			dataProvider.addFilter(account ->
					account.getDescription() != null &&
							account.getDescription().toLowerCase().contains(descriptionValue));
		}

		// Apply date created from filter
		if (dateCreatedFromFilter.getValue() != null) {
			LocalDate fromDate = dateCreatedFromFilter.getValue();
			dataProvider.addFilter(account ->
					account.getOpenDate() != null &&
							!account.getOpenDate().isBefore(fromDate));
		}

		// Apply date created to filter
		if (dateCreatedToFilter.getValue() != null) {
			LocalDate toDate = dateCreatedToFilter.getValue();
			dataProvider.addFilter(account ->
					account.getOpenDate() != null &&
							!account.getOpenDate().isAfter(toDate));
		}

		// Apply date updated from filter
		if (dateUpdatedFromFilter.getValue() != null) {
			LocalDate fromDate = dateUpdatedFromFilter.getValue();
			dataProvider.addFilter(account ->
					account.getDateUpdated() != null &&
							!account.getDateUpdated().toLocalDate().isBefore(fromDate));
		}

		// Apply date updated to filter
		if (dateUpdatedToFilter.getValue() != null) {
			LocalDate toDate = dateUpdatedToFilter.getValue();
			dataProvider.addFilter(account ->
					account.getDateUpdated() != null &&
							!account.getDateUpdated().toLocalDate().isAfter(toDate));
		}

		System.out.println("[DEBUG_LOG] Filters applied successfully");
	}

	private void clearFilters() {
		// Clear all filter fields
		accountNumberFilter.clear();
		accountTypeFilter.clear();
		currencyFilter.clear();
		minBalanceFilter.clear();
		maxBalanceFilter.clear();
		statusFilter.clear();
		customerIdFilter.clear();
		descriptionFilter.clear();
		dateCreatedFilter.clear();
		dateCreatedFromFilter.clear();
		dateCreatedToFilter.clear();
		dateUpdatedFilter.clear();
		dateUpdatedFromFilter.clear();
		dateUpdatedToFilter.clear();

		// Clear all filters from data provider
		if (dataProvider != null) {
			dataProvider.clearFilters();
			System.out.println("[DEBUG_LOG] All filters cleared");
		}
	}

	private void openCreateAccountDialog() {
		CreateAccount createAccountDialog = new CreateAccount(accountsService, this::loadAccountsData);
		createAccountDialog.open();
	}
}
