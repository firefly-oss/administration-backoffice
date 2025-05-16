package com.catalis.backoffice.accounts.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Route(value = "accounts", layout = MainLayout.class)
@PageTitle("Accounts | Bank Backoffice")
public class AccountsView extends VerticalLayout implements HasUrlParameter<String> {

    private final Random random = new Random();
    private final Map<Tab, Component> tabsToPages = new HashMap<>();
    private final Div pages = new Div();
    private final Grid<Account> accountGrid = new Grid<>(Account.class, false);
    private final Grid<Transaction> transactionGrid = new Grid<>(Transaction.class, false);
    private Tabs tabs;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (tabs != null) {
            int selectedTab = 0;
            if ("transactions".equals(parameter)) {
                selectedTab = 1;
            }
            // Select the corresponding tab
            tabs.setSelectedIndex(selectedTab);
        }
    }

    public AccountsView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button createButton = new Button("New Account", VaadinIcon.PLUS.create());
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        ComboBox<String> clientFilter = new ComboBox<>("Client");
        clientFilter.setPlaceholder("Filter by client");
        clientFilter.setItems("All Clients", "John Smith", "Jane Doe", "Robert Johnson", "Emily Williams");
        clientFilter.setValue("All Clients");

        HorizontalLayout actions = new HorizontalLayout(createButton, clientFilter);
        actions.setSpacing(true);
        actions.setAlignItems(FlexComponent.Alignment.BASELINE);

        add(new ViewToolbar("Accounts", actions));

        // Create tabs
        Tab accountsTab = new Tab(VaadinIcon.CREDIT_CARD.create(), new Span("Accounts"));
        Tab transactionsTab = new Tab(VaadinIcon.EXCHANGE.create(), new Span("Transactions"));

        this.tabs = new Tabs(accountsTab, transactionsTab);
        this.tabs.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM);

        // Create pages for tabs
        Component accountsPage = createAccountsPage();
        Component transactionsPage = createTransactionsPage();

        tabsToPages.put(accountsTab, accountsPage);
        tabsToPages.put(transactionsTab, transactionsPage);

        // Configure pages
        pages.setSizeFull();
        pages.add(accountsPage, transactionsPage);

        // Show only active page
        for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
            component.setVisible(false);
        }
        accountsPage.setVisible(true);

        // Add tab change listener
        this.tabs.addSelectedChangeListener(event -> {
            for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
                component.setVisible(false);
            }
            Component selectedPage = tabsToPages.get(this.tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });

        add(this.tabs, pages);
    }

    private Component createAccountsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Configure accounts grid
        configureAccountGrid();

        // Add search field
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidthFull();
        searchLayout.setPadding(true);
        searchLayout.addClassNames(LumoUtility.Background.CONTRAST_5);

        com.vaadin.flow.component.textfield.TextField searchField = new com.vaadin.flow.component.textfield.TextField();
        searchField.setPlaceholder("Search accounts...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();

        searchLayout.add(searchField);

        // Add account summary cards
        HorizontalLayout summaryLayout = new HorizontalLayout();
        summaryLayout.setWidthFull();
        summaryLayout.setPadding(true);
        summaryLayout.setSpacing(true);

        summaryLayout.add(
            createSummaryCard("Total Accounts", "156", VaadinIcon.CREDIT_CARD),
            createSummaryCard("Active Accounts", "142", VaadinIcon.CHECK_CIRCLE),
            createSummaryCard("Blocked Accounts", "14", VaadinIcon.CLOSE_CIRCLE),
            createSummaryCard("Total Balance", "$12,458,932.45", VaadinIcon.DOLLAR)
        );

        layout.add(summaryLayout, searchLayout, accountGrid);

        // Load mock data
        loadMockAccountData();

        return layout;
    }

    private Component createTransactionsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Configure transactions grid
        configureTransactionGrid();

        // Add filter controls
        HorizontalLayout filterLayout = new HorizontalLayout();
        filterLayout.setWidthFull();
        filterLayout.setPadding(true);
        filterLayout.setSpacing(true);
        filterLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        filterLayout.addClassNames(LumoUtility.Background.CONTRAST_5);

        ComboBox<String> accountFilter = new ComboBox<>("Account");
        accountFilter.setPlaceholder("Filter by account");
        accountFilter.setItems("All Accounts", "Checking", "Savings", "Credit Card", "Investment");
        accountFilter.setValue("All Accounts");

        ComboBox<String> typeFilter = new ComboBox<>("Type");
        typeFilter.setPlaceholder("Filter by type");
        typeFilter.setItems("All Types", "Deposit", "Withdrawal", "Transfer", "Payment");
        typeFilter.setValue("All Types");

        com.vaadin.flow.component.datepicker.DatePicker startDate = new com.vaadin.flow.component.datepicker.DatePicker("Start Date");
        com.vaadin.flow.component.datepicker.DatePicker endDate = new com.vaadin.flow.component.datepicker.DatePicker("End Date");

        Button applyButton = new Button("Apply Filters");
        applyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        filterLayout.add(accountFilter, typeFilter, startDate, endDate, applyButton);

        // Add transaction chart
        Component transactionChart = createTransactionChart();

        layout.add(transactionChart, filterLayout, transactionGrid);

        // Load mock data
        loadMockTransactionData();

        return layout;
    }

    private Component createTransactionChart() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(false);

        H3 chartTitle = new H3("Transaction Volume");
        chartTitle.addClassNames(LumoUtility.Margin.Bottom.NONE);

        Chart chart = new Chart(ChartType.COLUMN);
        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setBackgroundColor(null);

        XAxis xAxis = new XAxis();
        String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep" };
        xAxis.setCategories(months);
        configuration.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle("Amount ($)"));
        configuration.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setValuePrefix("$");
        tooltip.setValueSuffix(".00");
        configuration.setTooltip(tooltip);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setPointPadding(0.2);
        plotOptions.setBorderWidth(0);
        configuration.setPlotOptions(plotOptions);

        ListSeries deposits = new ListSeries("Deposits");
        deposits.setData(49000, 71000, 106000, 129000, 144000, 176000, 135000, 148000, 216000);

        ListSeries withdrawals = new ListSeries("Withdrawals");
        withdrawals.setData(83000, 78000, 98000, 93000, 106000, 84000, 105000, 104000, 91000);

        ListSeries transfers = new ListSeries("Transfers");
        transfers.setData(48000, 38000, 39000, 41000, 47000, 48000, 59000, 59000, 52000);

        configuration.addSeries(deposits);
        configuration.addSeries(withdrawals);
        configuration.addSeries(transfers);

        chart.setHeight("300px");

        layout.add(chartTitle, chart);
        return layout;
    }

    private Component createSummaryCard(String title, String value, VaadinIcon icon) {
        VerticalLayout card = new VerticalLayout();
        card.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.SMALL
        );
        card.setSpacing(false);
        card.setWidth("25%");

        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        com.vaadin.flow.component.icon.Icon cardIcon = icon.create();
        cardIcon.setColor("var(--lumo-primary-color)");

        Span titleSpan = new Span(title);
        titleSpan.addClassNames(
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.TextColor.SECONDARY
        );

        header.add(cardIcon, titleSpan);

        Span valueSpan = new Span(value);
        valueSpan.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.FontWeight.BOLD,
                LumoUtility.TextColor.PRIMARY
        );

        card.add(header, valueSpan);
        return card;
    }

    private void configureAccountGrid() {
        accountGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        accountGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        accountGrid.setSizeFull();

        accountGrid.addColumn(Account::getId).setHeader("Account Number").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(Account::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(Account::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(Account::getBalance).setHeader("Balance").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(Account::getCurrency).setHeader("Currency").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(Account::getOpenDate).setHeader("Open Date").setAutoWidth(true).setSortable(true);
        accountGrid.addColumn(this::createStatusComponent).setHeader("Status").setAutoWidth(true);

        // Add action column
        accountGrid.addComponentColumn(account -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.getElement().setAttribute("aria-label", "View");

            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.getElement().setAttribute("aria-label", "Edit");

            Button blockButton = new Button(VaadinIcon.BAN.create());
            blockButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            blockButton.getElement().setAttribute("aria-label", "Block");

            actions.add(viewButton, editButton, blockButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void configureTransactionGrid() {
        transactionGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        transactionGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        transactionGrid.setSizeFull();

        transactionGrid.addColumn(Transaction::getId).setHeader("Transaction ID").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getAccountId).setHeader("Account").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getDateTime).setHeader("Date & Time").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getDescription).setHeader("Description").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getAmount).setHeader("Amount").setAutoWidth(true).setSortable(true);
        transactionGrid.addColumn(Transaction::getBalance).setHeader("Balance").setAutoWidth(true).setSortable(true);

        // Add action column
        transactionGrid.addComponentColumn(transaction -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.getElement().setAttribute("aria-label", "View Details");

            Button receiptButton = new Button(VaadinIcon.FILE_TEXT.create());
            receiptButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            receiptButton.getElement().setAttribute("aria-label", "Receipt");

            actions.add(viewButton, receiptButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }

    private Span createStatusComponent(Account account) {
        Span status = new Span(account.getStatus());
        status.getElement().getThemeList().clear();

        switch (account.getStatus()) {
            case "Active":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Blocked":
                status.getElement().getThemeList().add("badge error");
                break;
            case "Pending":
                status.getElement().getThemeList().add("badge");
                break;
            default:
                status.getElement().getThemeList().add("badge contrast");
        }

        return status;
    }

    private void loadMockAccountData() {
        List<Account> accounts = new ArrayList<>();

        // Generate mock accounts
        String[] clientNames = {"John Smith", "Jane Doe", "Robert Johnson", "Emily Williams", 
                               "Michael Brown", "Sarah Davis", "David Miller", "Lisa Wilson"};

        String[] accountTypes = {"Checking", "Savings", "Credit Card", "Investment", "Loan"};
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "CAD"};
        String[] statuses = {"Active", "Blocked", "Pending"};

        for (int i = 1; i <= 50; i++) {
            String id = String.format("%010d", 1000000000 + random.nextInt(9000000));
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String type = accountTypes[random.nextInt(accountTypes.length)];

            double balance = 1000 + random.nextInt(100000) + random.nextDouble();
            String formattedBalance = String.format("$%,.2f", balance);

            String currency = currencies[random.nextInt(currencies.length)];
            String openDate = LocalDate.now().minusDays(random.nextInt(1000)).toString();
            String status = statuses[random.nextInt(statuses.length)];

            accounts.add(new Account(id, clientName, type, formattedBalance, currency, openDate, status));
        }

        accountGrid.setItems(accounts);
    }

    private void loadMockTransactionData() {
        List<Transaction> transactions = new ArrayList<>();

        // Generate mock transactions
        String[] accountIds = {"1234567890", "2345678901", "3456789012", "4567890123", "5678901234"};
        String[] descriptions = {"Salary Payment", "ATM Withdrawal", "Online Purchase", "Bill Payment", 
                                "Transfer to Savings", "Grocery Store", "Restaurant", "Subscription Fee",
                                "Refund", "Interest Credit"};
        String[] types = {"Deposit", "Withdrawal", "Transfer", "Payment"};

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int i = 1; i <= 100; i++) {
            String id = String.format("TXN-%09d", i);
            String accountId = accountIds[random.nextInt(accountIds.length)];

            LocalDateTime dateTime = LocalDateTime.now().minusDays(random.nextInt(30)).minusHours(random.nextInt(24));
            String formattedDateTime = dateTime.format(formatter);

            String description = descriptions[random.nextInt(descriptions.length)];
            String type = types[random.nextInt(types.length)];

            double amount;
            if (type.equals("Deposit") || type.equals("Refund") || type.equals("Interest Credit")) {
                amount = 10 + random.nextInt(5000) + random.nextDouble();
            } else {
                amount = -(10 + random.nextInt(2000) + random.nextDouble());
            }
            String formattedAmount = String.format("$%,.2f", amount);

            double balance = 1000 + random.nextInt(10000) + random.nextDouble();
            String formattedBalance = String.format("$%,.2f", balance);

            transactions.add(new Transaction(id, accountId, formattedDateTime, description, type, formattedAmount, formattedBalance));
        }

        // Sort by date (newest first)
        transactions.sort((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime()));

        transactionGrid.setItems(transactions);
    }

    // Account data class
    public static class Account {
        private final String id;
        private final String clientName;
        private final String type;
        private final String balance;
        private final String currency;
        private final String openDate;
        private final String status;

        public Account(String id, String clientName, String type, String balance, String currency, String openDate, String status) {
            this.id = id;
            this.clientName = clientName;
            this.type = type;
            this.balance = balance;
            this.currency = currency;
            this.openDate = openDate;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getClientName() {
            return clientName;
        }

        public String getType() {
            return type;
        }

        public String getBalance() {
            return balance;
        }

        public String getCurrency() {
            return currency;
        }

        public String getOpenDate() {
            return openDate;
        }

        public String getStatus() {
            return status;
        }
    }

    // Transaction data class
    public static class Transaction {
        private final String id;
        private final String accountId;
        private final String dateTime;
        private final String description;
        private final String type;
        private final String amount;
        private final String balance;

        public Transaction(String id, String accountId, String dateTime, String description, String type, String amount, String balance) {
            this.id = id;
            this.accountId = accountId;
            this.dateTime = dateTime;
            this.description = description;
            this.type = type;
            this.amount = amount;
            this.balance = balance;
        }

        public String getId() {
            return id;
        }

        public String getAccountId() {
            return accountId;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getDescription() {
            return description;
        }

        public String getType() {
            return type;
        }

        public String getAmount() {
            return amount;
        }

        public String getBalance() {
            return balance;
        }
    }
}
