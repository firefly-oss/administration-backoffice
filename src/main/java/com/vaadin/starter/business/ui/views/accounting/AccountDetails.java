package com.vaadin.starter.business.ui.views.accounting;

import com.catalis.core.banking.accounts.sdk.model.AccountDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.sdks.services.AccountsService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.navigation.bar.AppBar;
import com.vaadin.starter.business.ui.layout.size.Bottom;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.BoxShadowBorders;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.TextColor;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BorderRadius;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.vaadin.starter.business.dummy.DummyData.getImageSource;

@PageTitle("Account Details")
@Route(value = "account-details", layout = MainLayout.class)
public class AccountDetails extends ViewFrame implements HasUrlParameter<Long> {

	public int RECENT_TRANSACTIONS = 4;

	private ListItem availability;
	private ListItem bankAccount;
	private ListItem updated;

	private AccountDTO account;
	private final AccountsService accountsService;
	private boolean dataLoaded = false;
	private Long currentAccountId;

	@Autowired
	public AccountDetails(AccountsService accountsService) {
		this.accountsService = accountsService;
		System.out.println("[DEBUG_LOG] AccountDetails constructor called");
	}

	@Override
	public void setParameter(BeforeEvent beforeEvent, Long id) {
		System.out.println("[DEBUG_LOG] setParameter called with ID: " + id);

		if (id == null) {
			System.out.println("[DEBUG_LOG] ID is null, showing error");
			setViewContent(createErrorView("Invalid account ID"));
			return;
		}

		this.currentAccountId = id;
		loadAccountData(id);
	}

	private void loadAccountData(Long id) {
		System.out.println("[DEBUG_LOG] Starting to load account data for ID: " + id);

		// Show loading indicator
		setViewContent(createLoadingView());

		try {
			// Add timeout and better error handling
			accountsService.getAccount(id)
					.doOnSubscribe(subscription -> {
						System.out.println("[DEBUG_LOG] API call started for account ID: " + id);
					})
					.doOnNext(response -> {
						System.out.println("[DEBUG_LOG] Received response with status: " + response.getStatusCode());
						System.out.println("[DEBUG_LOG] Response body is null: " + (response.getBody() == null));
					})
					.subscribe(
							response -> {
								System.out.println("[DEBUG_LOG] In subscribe success callback");
								handleSuccessResponse(response);
							},
							error -> {
								System.out.println("[DEBUG_LOG] In subscribe error callback: " + error.getClass().getSimpleName());
								System.out.println("[DEBUG_LOG] Error message: " + error.getMessage());
								handleErrorResponse(error);
							}
					);
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Exception in loadAccountData: " + e.getMessage());
			handleErrorResponse(e);
		}
	}

	private void handleSuccessResponse(org.springframework.http.ResponseEntity<AccountDTO> response) {
		System.out.println("[DEBUG_LOG] Handling success response");

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			System.out.println("[DEBUG_LOG] Response is successful with body");
			account = response.getBody();
			dataLoaded = true;

			// Use UI.access to ensure thread safety
			getUI().ifPresent(ui -> ui.access(() -> {
				System.out.println("[DEBUG_LOG] Updating UI with account data");
				setViewContent(createContent());
				updateUIWithAccountData();
			}));
		} else {
			System.out.println("[DEBUG_LOG] Response not successful or body is null");
			String errorMsg = "Error loading account data: " + response.getStatusCode();
			getUI().ifPresent(ui -> ui.access(() -> {
				setViewContent(createErrorView(errorMsg));
			}));
		}
	}

	private void handleErrorResponse(Throwable error) {
		System.out.println("[DEBUG_LOG] Handling error response");

		String errorMessage;
		if (error instanceof java.util.concurrent.TimeoutException) {
			errorMessage = "Request timed out. The account service is not responding.";
			System.out.println("[DEBUG_LOG] Timeout exception occurred");
		} else if (error instanceof java.net.ConnectException) {
			errorMessage = "Cannot connect to account service. Please check your connection.";
			System.out.println("[DEBUG_LOG] Connection exception occurred");
		} else {
			errorMessage = "Error loading account data: " + error.getMessage();
			System.out.println("[DEBUG_LOG] Generic error occurred: " + error.getMessage());
		}

		getUI().ifPresent(ui -> ui.access(() -> {
			setViewContent(createErrorView(errorMessage));
		}));
	}

	private Component createLoadingView() {
		System.out.println("[DEBUG_LOG] Creating loading view");

		FlexBoxLayout loadingLayout = new FlexBoxLayout();
		loadingLayout.setFlexDirection(FlexDirection.COLUMN);
		loadingLayout.setAlignItems(FlexComponent.Alignment.CENTER);
		loadingLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		loadingLayout.setHeightFull();
		loadingLayout.setWidthFull();

		H3 loadingText = new H3("Loading account details...");
		ProgressBar progressBar = new ProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setWidth("50%");

		// Add debug info to loading view
		Span debugInfo = new Span("Account ID: " + currentAccountId);
		debugInfo.getStyle().set("margin-top", "1rem");
		debugInfo.getStyle().set("font-size", "0.8rem");
		debugInfo.getStyle().set("color", "var(--lumo-secondary-text-color)");

		loadingLayout.add(loadingText, progressBar, debugInfo);
		return loadingLayout;
	}

	private Component createErrorView(String errorMessage) {
		System.out.println("[DEBUG_LOG] Creating error view with message: " + errorMessage);

		FlexBoxLayout errorLayout = new FlexBoxLayout();
		errorLayout.setFlexDirection(FlexDirection.COLUMN);
		errorLayout.setAlignItems(FlexComponent.Alignment.CENTER);
		errorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		errorLayout.setHeightFull();
		errorLayout.setWidthFull();

		H3 errorTitle = new H3("Error");
		Span errorText = new Span(errorMessage);
		UIUtils.setTextColor(TextColor.ERROR, errorText);

		Button retryButton = UIUtils.createPrimaryButton("Retry");
		retryButton.addClickListener(e -> {
			System.out.println("[DEBUG_LOG] Retry button clicked");
			if (currentAccountId != null) {
				loadAccountData(currentAccountId);
			} else {
				System.out.println("[DEBUG_LOG] Cannot retry: currentAccountId is null");
			}
		});

		Button backButton = UIUtils.createTertiaryButton("Back to Accounts");
		backButton.addClickListener(e -> {
			System.out.println("[DEBUG_LOG] Back button clicked");
			getUI().ifPresent(ui -> ui.navigate(Accounts.class));
		});

		// Add debug information
		Div debugInfo = new Div();
		debugInfo.add(new Span("Account ID: " + currentAccountId));
		debugInfo.add(new Span("Data loaded: " + dataLoaded));
		debugInfo.getStyle().set("margin-top", "1rem");
		debugInfo.getStyle().set("font-size", "0.8rem");
		debugInfo.getStyle().set("color", "var(--lumo-secondary-text-color)");
		debugInfo.getStyle().set("text-align", "center");

		FlexBoxLayout buttonLayout = new FlexBoxLayout(retryButton, backButton);
		buttonLayout.setFlexDirection(FlexDirection.ROW);
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		buttonLayout.getStyle().set("gap", "1rem");
		buttonLayout.getStyle().set("margin-top", "1rem");

		errorLayout.add(errorTitle, errorText, buttonLayout, debugInfo);
		return errorLayout;
	}

	private Component createContent() {
		System.out.println("[DEBUG_LOG] Creating content view");

		if (account == null) {
			System.out.println("[DEBUG_LOG] Account is null when creating content!");
			return createErrorView("Account data is not available");
		}

		FlexBoxLayout content = new FlexBoxLayout(
				createLogoSection(),
				createRecentTransactionsHeader(),
				createRecentTransactionsList(),
				createMonthlyOverviewHeader(),
				createMonthlyOverviewChart()
		);
		content.setFlexDirection(FlexDirection.COLUMN);
		content.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
		content.setMaxWidth("840px");
		return content;
	}

	// ... [resto de métodos sin cambios - createLogoSection, createRecentTransactionsHeader, etc.] ...

	private FlexBoxLayout createLogoSection() {
		// Create a smaller logo image
		Image image = new Image(getImageSource(), "");
		UIUtils.setBorderRadius(BorderRadius._50, image);
		// Make logo smaller as requested
		image.setHeight("70px");
		image.setWidth("70px");
		image.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");

		// Create header with account description as owner
		Span ownerName = new Span(account.getDescription() != null ? account.getDescription() : "Account");
		ownerName.addClassName(LumoStyles.Heading.H2);

		// Create a container for logo and account name
		FlexBoxLayout logoAndName = new FlexBoxLayout(image, ownerName);
		logoAndName.setFlexDirection(FlexDirection.ROW);
		logoAndName.setAlignItems(FlexComponent.Alignment.CENTER);
		logoAndName.getStyle().set("gap", "16px");

		// Create a prominent balance display
		availability = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "", "Balance");
		availability.getPrimary().addClassName(LumoStyles.Heading.H1);
		availability.getPrimary().getStyle().set("font-weight", "bold");
		availability.getPrimary().getStyle().set("color", "var(--lumo-primary-color)");
		availability.setId("availability");
		availability.setReverse(true);
		availability.setDividerVisible(false);

		// Create a container for the balance with a background to make it stand out
		Div balanceContainer = new Div(availability);
		balanceContainer.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		balanceContainer.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		balanceContainer.getStyle().set("padding", "16px");
		balanceContainer.getStyle().set("margin-top", "16px");
		balanceContainer.getStyle().set("margin-bottom", "16px");
		balanceContainer.setWidth("100%");

		// Group 1: Account Identification
		Div accountIdentificationGroup = new Div();
		accountIdentificationGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		accountIdentificationGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		accountIdentificationGroup.getStyle().set("padding", "16px");
		accountIdentificationGroup.setWidth("100%");

		Span accountIdGroupTitle = new Span("Account Identification");
		accountIdGroupTitle.getStyle().set("font-weight", "bold");
		accountIdGroupTitle.getStyle().set("margin-bottom", "8px");
		accountIdGroupTitle.getStyle().set("display", "block");

		bankAccount = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CREDIT_CARD), account.getAccountNumber(), "Account Number");
		bankAccount.setReverse(true);

		ListItem accountId = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.KEY), String.valueOf(account.getAccountId()), "Account ID");
		accountId.setReverse(true);

		ListItem accountType = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.WALLET),
				account.getAccountType() != null ? account.getAccountType().toString() : "Unknown", "Account Type");
		accountType.setReverse(true);

		FlexBoxLayout accountIdContent = new FlexBoxLayout(bankAccount, accountId, accountType);
		accountIdContent.setFlexDirection(FlexDirection.COLUMN);
		accountIdContent.getStyle().set("gap", "8px");

		accountIdentificationGroup.add(accountIdGroupTitle, accountIdContent);

		// Group 2: Account Status
		Div accountStatusGroup = new Div();
		accountStatusGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		accountStatusGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		accountStatusGroup.getStyle().set("padding", "16px");
		accountStatusGroup.setWidth("100%");

		Span accountStatusGroupTitle = new Span("Account Status");
		accountStatusGroupTitle.getStyle().set("font-weight", "bold");
		accountStatusGroupTitle.getStyle().set("margin-bottom", "8px");
		accountStatusGroupTitle.getStyle().set("display", "block");

		ListItem status = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CHECK), "Active", "Account Status");
		status.setReverse(true);

		updated = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), "", "Last Updated");
		updated.setReverse(true);

		FlexBoxLayout accountStatusContent = new FlexBoxLayout(status, updated);
		accountStatusContent.setFlexDirection(FlexDirection.COLUMN);
		accountStatusContent.getStyle().set("gap", "8px");

		accountStatusGroup.add(accountStatusGroupTitle, accountStatusContent);

		// Group 3: Financial Details
		Div financialDetailsGroup = new Div();
		financialDetailsGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		financialDetailsGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		financialDetailsGroup.getStyle().set("padding", "16px");
		financialDetailsGroup.setWidth("100%");

		Span financialDetailsGroupTitle = new Span("Financial Details");
		financialDetailsGroupTitle.getStyle().set("font-weight", "bold");
		financialDetailsGroupTitle.getStyle().set("margin-bottom", "8px");
		financialDetailsGroupTitle.getStyle().set("display", "block");

		ListItem currency = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.MONEY), "USD", "Currency");
		currency.setReverse(true);

		ListItem minimumBalance = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "$100.00", "Minimum Balance");
		minimumBalance.setReverse(true);

		ListItem overdraftLimit = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "$500.00", "Overdraft Limit");
		overdraftLimit.setReverse(true);

		FlexBoxLayout financialDetailsContent = new FlexBoxLayout(currency, minimumBalance, overdraftLimit);
		financialDetailsContent.setFlexDirection(FlexDirection.COLUMN);
		financialDetailsContent.getStyle().set("gap", "8px");

		financialDetailsGroup.add(financialDetailsGroupTitle, financialDetailsContent);

		// Create main content layout with all groups
		FlexBoxLayout mainContent = new FlexBoxLayout(
				logoAndName,
				balanceContainer,
				accountIdentificationGroup,
				accountStatusGroup,
				financialDetailsGroup
		);
		mainContent.setFlexDirection(FlexDirection.COLUMN);
		mainContent.setWidth("100%");
		mainContent.setPadding(Vertical.M, Horizontal.L);
		mainContent.getStyle().set("gap", "16px");

		// Create the main section layout with a card-like appearance
		FlexBoxLayout section = new FlexBoxLayout(mainContent);
		section.addClassName(BoxShadowBorders.BOTTOM);
		section.setAlignItems(FlexComponent.Alignment.CENTER);
		section.setFlexDirection(FlexDirection.COLUMN);
		section.setPadding(Vertical.L, Horizontal.M);
		section.getStyle().set("background-color", "var(--lumo-base-color)");
		section.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		return section;
	}

	private Component createRecentTransactionsHeader() {
		Span title = UIUtils.createH3Label("Recent Transactions");

		Button viewAll = UIUtils.createSmallButton("View All");
		viewAll.addClickListener(
				e -> UIUtils.showNotification("Not implemented yet."));
		viewAll.addClassName(LumoStyles.Margin.Left.AUTO);

		FlexBoxLayout header = new FlexBoxLayout(title, viewAll);
		header.setAlignItems(FlexComponent.Alignment.CENTER);
		header.setMargin(Bottom.M, Horizontal.RESPONSIVE_L, Top.L);
		return header;
	}

	private Component createRecentTransactionsList() {
		Div items = new Div();
		items.addClassNames(BoxShadowBorders.BOTTOM, LumoStyles.Padding.Bottom.L);

		// Sample transaction data
		String[] companies = {"Amazon", "Netflix", "Utility Bill", "Salary Deposit"};
		Double[] amounts = {-29.99, -14.99, -85.50, 1250.00};

		for (int i = 0; i < RECENT_TRANSACTIONS; i++) {
			// Use sample data or fallback to index-based values if we run out of samples
			int index = i % companies.length;
			Double amount = amounts[index];

			Span amountLabel = UIUtils.createAmountLabel(amount);
			if (amount > 0) {
				UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
			} else {
				UIUtils.setTextColor(TextColor.ERROR, amountLabel);
			}

			// Create a sample icon for the transaction
			Image logo = new Image("images/logos/payment.png", "Transaction");
			logo.setHeight("32px");
			logo.setWidth("32px");

			ListItem item = new ListItem(
					logo,
					companies[index],
					UIUtils.formatDate(LocalDate.now().minusDays(i)),
					amountLabel
			);
			// Dividers for all but the last item
			item.setDividerVisible(i < RECENT_TRANSACTIONS - 1);
			items.add(item);
		}

		return items;
	}

	private Component createMonthlyOverviewHeader() {
		Span header = UIUtils.createH3Label("Monthly Overview");
		header.addClassNames(LumoStyles.Margin.Vertical.L, LumoStyles.Margin.Responsive.Horizontal.L);
		return header;
	}

	private Component createMonthlyOverviewChart() {
		Chart chart = new Chart(ChartType.COLUMN);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("");
		conf.getLegend().setEnabled(true);

		XAxis xAxis = new XAxis();
		xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(xAxis);

		conf.getyAxis().setTitle("Amount ($)");

		// Sample data for withdrawals and deposits
		int[] withdrawalData = {7500, 8200, 7800, 9100, 8600, 7900, 8300, 9500, 8800, 7700, 8100, 9200};
		int[] depositData = {8000, 8700, 8200, 9600, 9100, 8400, 8800, 10000, 9300, 8200, 8600, 9700};

		// Withdrawals and deposits
		ListSeries withDrawals = new ListSeries("Withdrawals");
		ListSeries deposits = new ListSeries("Deposits");

		// Add sample data for all 12 months
		for (int i = 0; i < 12; i++) {
			withDrawals.addData(withdrawalData[i]);
			deposits.addData(depositData[i]);
		}

		conf.addSeries(withDrawals);
		conf.addSeries(deposits);

		FlexBoxLayout card = new FlexBoxLayout(chart);
		card.setHeight("400px");
		return card;
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		System.out.println("[DEBUG_LOG] Component attached, dataLoaded: " + dataLoaded);

		// Only update UI if data is already loaded
		if (dataLoaded && account != null) {
			updateUIWithAccountData();
		}
	}

	private void updateUIWithAccountData() {
		System.out.println("[DEBUG_LOG] Updating UI with account data");

		if (account == null) {
			System.out.println("[DEBUG_LOG] Cannot update UI: account is null");
			return;
		}

		try {
			initAppBar();
			getUI().ifPresent(ui -> ui.getPage().setTitle(account.getDescription() != null ?
					account.getDescription() : "Account Details"));

			// Update other UI components
			if (availability != null) {
				availability.setPrimaryText(UIUtils.formatAmount(account.getMinimumBalance() != null ?
						account.getMinimumBalance().doubleValue() : 0.0));
			}
			if (bankAccount != null) {
				bankAccount.setPrimaryText(account.getAccountNumber());
				bankAccount.setSecondaryText(account.getAccountType() != null ?
						account.getAccountType().toString() : "Unknown");
			}
			if (updated != null) {
				updated.setPrimaryText(UIUtils.formatDate(account.getOpenDate()));
			}

			System.out.println("[DEBUG_LOG] UI updated successfully");
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Error updating UI: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private AppBar initAppBar() {
		try {
			AppBar appBar = MainLayout.get().getAppBar();
			appBar.setNaviMode(AppBar.NaviMode.CONTEXTUAL);
			appBar.getContextIcon().addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(Accounts.class)));
			appBar.setTitle(account.getDescription() != null ? account.getDescription() : "Account Details");
			return appBar;
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Error initializing AppBar: " + e.getMessage());
			return null;
		}
	}
}
