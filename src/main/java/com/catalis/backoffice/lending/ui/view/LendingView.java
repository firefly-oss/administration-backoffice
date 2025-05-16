package com.catalis.backoffice.lending.ui.view;

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
import java.time.format.DateTimeFormatter;
import java.util.*;

@Route(value = "lending", layout = MainLayout.class)
@PageTitle("Lending | Bank Backoffice")
public class LendingView extends VerticalLayout implements HasUrlParameter<String> {

    private final Random random = new Random();
    private final Map<Tab, Component> tabsToPages = new HashMap<>();
    private final Div pages = new Div();
    private final Grid<Loan> loanGrid = new Grid<>(Loan.class, false);
    private final Grid<Application> applicationGrid = new Grid<>(Application.class, false);
    private Tabs tabs;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (tabs != null) {
            int selectedTab = 0;
            if ("applications".equals(parameter)) {
                selectedTab = 1;
            } else if ("reports".equals(parameter)) {
                selectedTab = 2;
            }
            // Select the corresponding tab
            tabs.setSelectedIndex(selectedTab);
        }
    }

    public LendingView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button createButton = new Button("New Loan", VaadinIcon.PLUS.create());
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        ComboBox<String> statusFilter = new ComboBox<>("Status");
        statusFilter.setPlaceholder("Filter by status");
        statusFilter.setItems("All Statuses", "Active", "Pending", "Completed", "Defaulted");
        statusFilter.setValue("All Statuses");

        HorizontalLayout actions = new HorizontalLayout(createButton, statusFilter);
        actions.setSpacing(true);
        actions.setAlignItems(FlexComponent.Alignment.BASELINE);

        add(new ViewToolbar("Lending", actions));

        // Create tabs
        Tab loansTab = new Tab(VaadinIcon.MONEY.create(), new Span("Loans"));
        Tab applicationsTab = new Tab(VaadinIcon.FILE_TEXT.create(), new Span("Applications"));
        Tab reportsTab = new Tab(VaadinIcon.CHART.create(), new Span("Reports"));

        this.tabs = new Tabs(loansTab, applicationsTab, reportsTab);
        this.tabs.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM);

        // Create pages for tabs
        Component loansPage = createLoansPage();
        Component applicationsPage = createApplicationsPage();
        Component reportsPage = createReportsPage();

        tabsToPages.put(loansTab, loansPage);
        tabsToPages.put(applicationsTab, applicationsPage);
        tabsToPages.put(reportsTab, reportsPage);

        // Configure pages
        pages.setSizeFull();
        pages.add(loansPage, applicationsPage, reportsPage);

        // Show only active page
        for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
            component.setVisible(false);
        }
        loansPage.setVisible(true);

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

    private Component createLoansPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Configure loans grid
        configureLoanGrid();

        // Add search field
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidthFull();
        searchLayout.setPadding(true);
        searchLayout.addClassNames(LumoUtility.Background.CONTRAST_5);

        com.vaadin.flow.component.textfield.TextField searchField = new com.vaadin.flow.component.textfield.TextField();
        searchField.setPlaceholder("Search loans...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();

        searchLayout.add(searchField);

        // Add loan summary cards
        HorizontalLayout summaryLayout = new HorizontalLayout();
        summaryLayout.setWidthFull();
        summaryLayout.setPadding(true);
        summaryLayout.setSpacing(true);

        summaryLayout.add(
            createSummaryCard("Total Loans", "342", VaadinIcon.MONEY),
            createSummaryCard("Active Loans", "287", VaadinIcon.CHECK_CIRCLE),
            createSummaryCard("Pending Approval", "43", VaadinIcon.CLOCK),
            createSummaryCard("Total Portfolio", "$24,856,432.78", VaadinIcon.DOLLAR)
        );

        layout.add(summaryLayout, searchLayout, loanGrid);

        // Load mock data
        loadMockLoanData();

        return layout;
    }

    private Component createApplicationsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Configure applications grid
        configureApplicationGrid();

        // Add filter controls
        HorizontalLayout filterLayout = new HorizontalLayout();
        filterLayout.setWidthFull();
        filterLayout.setPadding(true);
        filterLayout.setSpacing(true);
        filterLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        filterLayout.addClassNames(LumoUtility.Background.CONTRAST_5);

        ComboBox<String> statusFilter = new ComboBox<>("Status");
        statusFilter.setPlaceholder("Filter by status");
        statusFilter.setItems("All Statuses", "Submitted", "Under Review", "Approved", "Rejected");
        statusFilter.setValue("All Statuses");

        ComboBox<String> productFilter = new ComboBox<>("Product");
        productFilter.setPlaceholder("Filter by product");
        productFilter.setItems("All Products", "Personal Loan", "Mortgage", "Auto Loan", "Business Loan");
        productFilter.setValue("All Products");

        com.vaadin.flow.component.datepicker.DatePicker startDate = new com.vaadin.flow.component.datepicker.DatePicker("Start Date");
        com.vaadin.flow.component.datepicker.DatePicker endDate = new com.vaadin.flow.component.datepicker.DatePicker("End Date");

        Button applyButton = new Button("Apply Filters");
        applyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        filterLayout.add(statusFilter, productFilter, startDate, endDate, applyButton);

        layout.add(filterLayout, applicationGrid);

        // Load mock data
        loadMockApplicationData();

        return layout;
    }

    private Component createReportsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(true);
        layout.setSpacing(true);

        H3 pageTitle = new H3("Lending Reports");
        pageTitle.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        // Add report cards
        HorizontalLayout reportCardsLayout = new HorizontalLayout();
        reportCardsLayout.setWidthFull();
        reportCardsLayout.setSpacing(true);

        reportCardsLayout.add(
            createReportCard("Loan Performance", "View detailed performance metrics for all loans", VaadinIcon.CHART),
            createReportCard("Risk Analysis", "Analyze risk factors and default probabilities", VaadinIcon.WARNING),
            createReportCard("Portfolio Summary", "Overview of the entire loan portfolio", VaadinIcon.FOLDER)
        );

        // Add loan distribution chart
        Component loanDistributionChart = createLoanDistributionChart();

        // Add loan performance chart
        Component loanPerformanceChart = createLoanPerformanceChart();

        layout.add(pageTitle, reportCardsLayout, loanDistributionChart, loanPerformanceChart);

        return layout;
    }

    private Component createLoanDistributionChart() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(false);

        H3 chartTitle = new H3("Loan Distribution by Type");
        chartTitle.addClassNames(LumoUtility.Margin.Bottom.NONE);

        Chart chart = new Chart(ChartType.PIE);
        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setBackgroundColor(null);

        Tooltip tooltip = new Tooltip();
        tooltip.setValueDecimals(1);
        tooltip.setValueSuffix("%");
        configuration.setTooltip(tooltip);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        configuration.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Mortgages", 45.0));
        series.add(new DataSeriesItem("Personal Loans", 26.5));
        series.add(new DataSeriesItem("Auto Loans", 12.8));
        series.add(new DataSeriesItem("Business Loans", 8.5));
        series.add(new DataSeriesItem("Student Loans", 6.2));
        series.add(new DataSeriesItem("Other", 1.0));
        configuration.addSeries(series);

        chart.setHeight("300px");

        layout.add(chartTitle, chart);
        return layout;
    }

    private Component createLoanPerformanceChart() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(false);

        H3 chartTitle = new H3("Loan Performance Over Time");
        chartTitle.addClassNames(LumoUtility.Margin.Bottom.NONE);

        Chart chart = new Chart(ChartType.LINE);
        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setBackgroundColor(null);

        XAxis xAxis = new XAxis();
        String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        xAxis.setCategories(months);
        configuration.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle("Amount ($M)"));
        configuration.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setValuePrefix("$");
        tooltip.setValueSuffix("M");
        configuration.setTooltip(tooltip);

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setPointPlacement(PointPlacement.ON);
        configuration.setPlotOptions(plotOptions);

        ListSeries disbursements = new ListSeries("Disbursements");
        disbursements.setData(4.5, 5.2, 5.7, 6.1, 7.2, 8.5, 9.2, 8.7, 7.9, 8.1, 8.5, 9.2);

        ListSeries repayments = new ListSeries("Repayments");
        repayments.setData(3.2, 3.5, 3.8, 4.2, 4.5, 4.9, 5.2, 5.5, 5.8, 6.1, 6.4, 6.7);

        ListSeries defaults = new ListSeries("Defaults");
        defaults.setData(0.3, 0.4, 0.3, 0.5, 0.4, 0.3, 0.4, 0.5, 0.6, 0.5, 0.4, 0.3);

        configuration.addSeries(disbursements);
        configuration.addSeries(repayments);
        configuration.addSeries(defaults);

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

    private Component createReportCard(String title, String description, VaadinIcon icon) {
        VerticalLayout card = new VerticalLayout();
        card.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.MEDIUM
        );
        card.setSpacing(true);
        card.setWidth("33%");

        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        com.vaadin.flow.component.icon.Icon cardIcon = icon.create();
        cardIcon.setColor("var(--lumo-primary-color)");
        cardIcon.addClassNames(LumoUtility.IconSize.LARGE);

        H3 titleH3 = new H3(title);
        titleH3.addClassNames(
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.Margin.NONE
        );

        header.add(cardIcon, titleH3);

        Span descriptionSpan = new Span(description);
        descriptionSpan.addClassNames(
                LumoUtility.TextColor.SECONDARY
        );

        Button viewButton = new Button("View Report");
        viewButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        card.add(header, descriptionSpan, viewButton);
        return card;
    }

    private void configureLoanGrid() {
        loanGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        loanGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        loanGrid.setSizeFull();

        loanGrid.addColumn(Loan::getId).setHeader("Loan ID").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getAmount).setHeader("Amount").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getInterestRate).setHeader("Interest Rate").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getTerm).setHeader("Term").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getStartDate).setHeader("Start Date").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getEndDate).setHeader("End Date").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(this::createStatusComponent).setHeader("Status").setAutoWidth(true);

        // Add action column
        loanGrid.addComponentColumn(loan -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.getElement().setAttribute("aria-label", "View");

            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.getElement().setAttribute("aria-label", "Edit");

            Button paymentButton = new Button(VaadinIcon.MONEY.create());
            paymentButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
            paymentButton.getElement().setAttribute("aria-label", "Payment");

            actions.add(viewButton, editButton, paymentButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }

    private void configureApplicationGrid() {
        applicationGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        applicationGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        applicationGrid.setSizeFull();

        applicationGrid.addColumn(Application::getId).setHeader("Application ID").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getProductType).setHeader("Product").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getRequestedAmount).setHeader("Requested Amount").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getSubmissionDate).setHeader("Submission Date").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getStatus).setHeader("Status").setAutoWidth(true).setSortable(true);
        applicationGrid.addColumn(Application::getAssignedTo).setHeader("Assigned To").setAutoWidth(true).setSortable(true);

        // Add action column
        applicationGrid.addComponentColumn(application -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.getElement().setAttribute("aria-label", "View");

            Button processButton = new Button(VaadinIcon.CHECK.create());
            processButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
            processButton.getElement().setAttribute("aria-label", "Process");

            Button rejectButton = new Button(VaadinIcon.CLOSE.create());
            rejectButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            rejectButton.getElement().setAttribute("aria-label", "Reject");

            actions.add(viewButton, processButton, rejectButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }

    private Span createStatusComponent(Loan loan) {
        Span status = new Span(loan.getStatus());
        status.getElement().getThemeList().clear();

        switch (loan.getStatus()) {
            case "Active":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Completed":
                status.getElement().getThemeList().add("badge");
                break;
            case "Defaulted":
                status.getElement().getThemeList().add("badge error");
                break;
            case "Pending":
                status.getElement().getThemeList().add("badge contrast");
                break;
            default:
                status.getElement().getThemeList().add("badge");
        }

        return status;
    }

    private void loadMockLoanData() {
        List<Loan> loans = new ArrayList<>();

        // Generate mock loans
        String[] clientNames = {"John Smith", "Jane Doe", "Robert Johnson", "Emily Williams", 
                               "Michael Brown", "Sarah Davis", "David Miller", "Lisa Wilson"};

        String[] loanTypes = {"Mortgage", "Personal Loan", "Auto Loan", "Business Loan", "Student Loan"};
        String[] statuses = {"Active", "Completed", "Defaulted", "Pending"};

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 1; i <= 50; i++) {
            String id = String.format("LN-%06d", 100000 + random.nextInt(900000));
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String type = loanTypes[random.nextInt(loanTypes.length)];

            double amount = 5000 + random.nextInt(995000) + random.nextDouble();
            String formattedAmount = String.format("$%,.2f", amount);

            double interestRate = 2.5 + (random.nextDouble() * 10.0);
            String formattedInterestRate = String.format("%.2f%%", interestRate);

            int termYears = 1 + random.nextInt(30);
            String term = termYears + " years";

            LocalDate startDate = LocalDate.now().minusMonths(random.nextInt(60));
            String formattedStartDate = startDate.format(dateFormatter);

            LocalDate endDate = startDate.plusYears(termYears);
            String formattedEndDate = endDate.format(dateFormatter);

            String status = statuses[random.nextInt(statuses.length)];

            loans.add(new Loan(id, clientName, type, formattedAmount, formattedInterestRate, term, 
                              formattedStartDate, formattedEndDate, status));
        }

        loanGrid.setItems(loans);
    }

    private void loadMockApplicationData() {
        List<Application> applications = new ArrayList<>();

        // Generate mock applications
        String[] clientNames = {"John Smith", "Jane Doe", "Robert Johnson", "Emily Williams", 
                               "Michael Brown", "Sarah Davis", "David Miller", "Lisa Wilson"};

        String[] productTypes = {"Mortgage", "Personal Loan", "Auto Loan", "Business Loan", "Student Loan"};
        String[] statuses = {"Submitted", "Under Review", "Approved", "Rejected"};
        String[] officers = {"Alice Johnson", "Bob Smith", "Carol Williams", "David Brown", "Emma Davis"};

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 1; i <= 50; i++) {
            String id = String.format("APP-%06d", 100000 + random.nextInt(900000));
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String productType = productTypes[random.nextInt(productTypes.length)];

            double amount = 5000 + random.nextInt(995000) + random.nextDouble();
            String formattedAmount = String.format("$%,.2f", amount);

            LocalDate submissionDate = LocalDate.now().minusDays(random.nextInt(60));
            String formattedSubmissionDate = submissionDate.format(dateFormatter);

            String status = statuses[random.nextInt(statuses.length)];
            String assignedTo = officers[random.nextInt(officers.length)];

            applications.add(new Application(id, clientName, productType, formattedAmount, 
                                           formattedSubmissionDate, status, assignedTo));
        }

        applicationGrid.setItems(applications);
    }

    // Loan data class
    public static class Loan {
        private final String id;
        private final String clientName;
        private final String type;
        private final String amount;
        private final String interestRate;
        private final String term;
        private final String startDate;
        private final String endDate;
        private final String status;

        public Loan(String id, String clientName, String type, String amount, String interestRate, 
                   String term, String startDate, String endDate, String status) {
            this.id = id;
            this.clientName = clientName;
            this.type = type;
            this.amount = amount;
            this.interestRate = interestRate;
            this.term = term;
            this.startDate = startDate;
            this.endDate = endDate;
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

        public String getAmount() {
            return amount;
        }

        public String getInterestRate() {
            return interestRate;
        }

        public String getTerm() {
            return term;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getStatus() {
            return status;
        }
    }

    // Application data class
    public static class Application {
        private final String id;
        private final String clientName;
        private final String productType;
        private final String requestedAmount;
        private final String submissionDate;
        private final String status;
        private final String assignedTo;

        public Application(String id, String clientName, String productType, String requestedAmount, 
                          String submissionDate, String status, String assignedTo) {
            this.id = id;
            this.clientName = clientName;
            this.productType = productType;
            this.requestedAmount = requestedAmount;
            this.submissionDate = submissionDate;
            this.status = status;
            this.assignedTo = assignedTo;
        }

        public String getId() {
            return id;
        }

        public String getClientName() {
            return clientName;
        }

        public String getProductType() {
            return productType;
        }

        public String getRequestedAmount() {
            return requestedAmount;
        }

        public String getSubmissionDate() {
            return submissionDate;
        }

        public String getStatus() {
            return status;
        }

        public String getAssignedTo() {
            return assignedTo;
        }
    }
}