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
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "lending", layout = MainLayout.class)
@PageTitle("Lending | Bank Backoffice")
public class LendingView extends VerticalLayout {

    private final Random random = new Random();
    private final Grid<Loan> loanGrid = new Grid<>(Loan.class, false);
    private final Grid<CreditProduct> productGrid = new Grid<>(CreditProduct.class, false);

    public LendingView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button createLoanButton = new Button("New Loan", VaadinIcon.PLUS.create());
        createLoanButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button createProductButton = new Button("New Credit Product", VaadinIcon.PLUS_CIRCLE.create());
        
        HorizontalLayout actions = new HorizontalLayout(createLoanButton, createProductButton);
        actions.setSpacing(true);

        add(new ViewToolbar("Lending", actions));
        
        // Add summary cards
        add(createSummarySection());
        
        // Add loan portfolio chart
        add(createLoanPortfolioChart());
        
        // Create tabs-like section with two grids
        H3 loansHeader = new H3("Active Loans");
        loansHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Padding.Horizontal.MEDIUM);
        
        // Configure loans grid
        configureLoanGrid();
        
        H3 productsHeader = new H3("Credit Products");
        productsHeader.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.LARGE, LumoUtility.Padding.Horizontal.MEDIUM);
        
        // Configure products grid
        configureProductGrid();
        
        add(loansHeader, loanGrid, productsHeader, productGrid);
        
        // Load mock data
        loadMockLoanData();
        loadMockProductData();
    }
    
    private Component createSummarySection() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setPadding(true);
        layout.setSpacing(true);
        
        layout.add(
            createSummaryCard("Total Loans", "1,245", "$24.5M", VaadinIcon.MONEY_DEPOSIT),
            createSummaryCard("Pending Approvals", "32", "$1.2M", VaadinIcon.CLOCK),
            createSummaryCard("Overdue Payments", "18", "$245K", VaadinIcon.EXCLAMATION_CIRCLE),
            createSummaryCard("Avg. Interest Rate", "5.2%", "+0.3% MoM", VaadinIcon.CHART_LINE)
        );
        
        return layout;
    }
    
    private Component createSummaryCard(String title, String value, String subValue, VaadinIcon icon) {
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
        
        Span subValueSpan = new Span(subValue);
        subValueSpan.addClassNames(
                LumoUtility.FontSize.SMALL,
                LumoUtility.TextColor.SECONDARY
        );
        
        card.add(header, valueSpan, subValueSpan);
        return card;
    }
    
    private Component createLoanPortfolioChart() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(false);
        
        H3 chartTitle = new H3("Loan Portfolio Distribution");
        chartTitle.addClassNames(LumoUtility.Margin.Bottom.NONE);
        
        HorizontalLayout chartLayout = new HorizontalLayout();
        chartLayout.setWidthFull();
        chartLayout.setSpacing(true);
        
        // Create pie chart for loan types
        Chart typeChart = new Chart(ChartType.PIE);
        Configuration typeConfig = typeChart.getConfiguration();
        typeConfig.getChart().setBackgroundColor(null);
        typeConfig.setTitle("By Loan Type");
        
        Tooltip typeTooltip = new Tooltip();
        typeTooltip.setValueDecimals(1);
        typeTooltip.setValueSuffix("%");
        typeConfig.setTooltip(typeTooltip);
        
        PlotOptionsPie typePlotOptions = new PlotOptionsPie();
        typePlotOptions.setAllowPointSelect(true);
        typePlotOptions.setCursor(Cursor.POINTER);
        typePlotOptions.setShowInLegend(true);
        typeConfig.setPlotOptions(typePlotOptions);
        
        DataSeries typeSeries = new DataSeries();
        typeSeries.add(new DataSeriesItem("Mortgage", 45.0));
        typeSeries.add(new DataSeriesItem("Personal", 26.8));
        typeSeries.add(new DataSeriesItem("Auto", 12.8));
        typeSeries.add(new DataSeriesItem("Business", 8.5));
        typeSeries.add(new DataSeriesItem("Education", 6.9));
        
        typeConfig.addSeries(typeSeries);
        typeChart.setWidth("50%");
        
        // Create pie chart for risk categories
        Chart riskChart = new Chart(ChartType.PIE);
        Configuration riskConfig = riskChart.getConfiguration();
        riskConfig.getChart().setBackgroundColor(null);
        riskConfig.setTitle("By Risk Category");
        
        Tooltip riskTooltip = new Tooltip();
        riskTooltip.setValueDecimals(1);
        riskTooltip.setValueSuffix("%");
        riskConfig.setTooltip(riskTooltip);
        
        PlotOptionsPie riskPlotOptions = new PlotOptionsPie();
        riskPlotOptions.setAllowPointSelect(true);
        riskPlotOptions.setCursor(Cursor.POINTER);
        riskPlotOptions.setShowInLegend(true);
        riskConfig.setPlotOptions(riskPlotOptions);
        
        DataSeries riskSeries = new DataSeries();
        riskSeries.add(new DataSeriesItem("Low Risk", 58.0));
        riskSeries.add(new DataSeriesItem("Medium Risk", 32.5));
        riskSeries.add(new DataSeriesItem("High Risk", 9.5));
        
        riskConfig.addSeries(riskSeries);
        riskChart.setWidth("50%");
        
        chartLayout.add(typeChart, riskChart);
        layout.add(chartTitle, chartLayout);
        
        return layout;
    }
    
    private void configureLoanGrid() {
        loanGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        loanGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        loanGrid.setHeight("300px");
        
        loanGrid.addColumn(Loan::getId).setHeader("Loan ID").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getAmount).setHeader("Amount").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getInterestRate).setHeader("Interest Rate").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getTerm).setHeader("Term").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getStartDate).setHeader("Start Date").setAutoWidth(true).setSortable(true);
        loanGrid.addColumn(Loan::getNextPayment).setHeader("Next Payment").setAutoWidth(true).setSortable(true);
        loanGrid.addComponentColumn(this::createProgressBar).setHeader("Progress").setAutoWidth(true);
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
            
            actions.add(viewButton, editButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }
    
    private void configureProductGrid() {
        productGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        productGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        productGrid.setHeight("300px");
        
        productGrid.addColumn(CreditProduct::getId).setHeader("Product ID").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getMinAmount).setHeader("Min Amount").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getMaxAmount).setHeader("Max Amount").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getInterestRate).setHeader("Interest Rate").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getTermRange).setHeader("Term Range").setAutoWidth(true).setSortable(true);
        productGrid.addColumn(CreditProduct::getRequirements).setHeader("Requirements").setAutoWidth(true);
        productGrid.addColumn(this::createProductStatusComponent).setHeader("Status").setAutoWidth(true);
        
        // Add action column
        productGrid.addComponentColumn(product -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);
            
            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.getElement().setAttribute("aria-label", "Edit");
            
            Button toggleButton;
            if ("Active".equals(product.getStatus())) {
                toggleButton = new Button(VaadinIcon.CLOSE_CIRCLE.create());
                toggleButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
                toggleButton.getElement().setAttribute("aria-label", "Deactivate");
            } else {
                toggleButton = new Button(VaadinIcon.CHECK_CIRCLE.create());
                toggleButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
                toggleButton.getElement().setAttribute("aria-label", "Activate");
            }
            
            actions.add(editButton, toggleButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }
    
    private Component createProgressBar(Loan loan) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMin(0);
        progressBar.setMax(100);
        progressBar.setValue(loan.getProgress());
        progressBar.setWidth("100px");
        
        return progressBar;
    }
    
    private Component createStatusComponent(Loan loan) {
        Span status = new Span(loan.getStatus());
        status.getElement().getThemeList().clear();
        
        switch (loan.getStatus()) {
            case "Current":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Late":
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
    
    private Component createProductStatusComponent(CreditProduct product) {
        Span status = new Span(product.getStatus());
        status.getElement().getThemeList().clear();
        
        switch (product.getStatus()) {
            case "Active":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Inactive":
                status.getElement().getThemeList().add("badge error");
                break;
            default:
                status.getElement().getThemeList().add("badge contrast");
        }
        
        return status;
    }
    
    private void loadMockLoanData() {
        List<Loan> loans = new ArrayList<>();
        
        // Generate mock loans
        String[] clientNames = {"John Smith", "Jane Doe", "Robert Johnson", "Emily Williams", 
                               "Michael Brown", "Sarah Davis", "David Miller", "Lisa Wilson"};
        
        String[] loanTypes = {"Mortgage", "Personal", "Auto", "Business", "Education"};
        String[] statuses = {"Current", "Late", "Pending"};
        
        for (int i = 1; i <= 50; i++) {
            String id = String.format("LN-%06d", i);
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String type = loanTypes[random.nextInt(loanTypes.length)];
            
            double amount = 5000 + random.nextInt(495000);
            String formattedAmount = String.format("$%,.2f", amount);
            
            double interestRate = 2.5 + (random.nextDouble() * 10.0);
            String formattedInterestRate = String.format("%.2f%%", interestRate);
            
            int termYears = 1 + random.nextInt(29);
            String term = termYears + " years";
            
            LocalDate startDate = LocalDate.now().minusMonths(random.nextInt(60));
            String formattedStartDate = startDate.toString();
            
            LocalDate nextPayment = LocalDate.now().plusDays(random.nextInt(30));
            String formattedNextPayment = nextPayment.toString();
            
            double progress = random.nextDouble() * 100.0;
            String status = statuses[random.nextInt(statuses.length)];
            
            loans.add(new Loan(id, clientName, type, formattedAmount, formattedInterestRate, term, 
                              formattedStartDate, formattedNextPayment, progress, status));
        }
        
        loanGrid.setItems(loans);
    }
    
    private void loadMockProductData() {
        List<CreditProduct> products = new ArrayList<>();
        
        // Add some predefined credit products
        products.add(new CreditProduct("PRD-001", "Home Buyer's Mortgage", "Mortgage", "$50,000", "$1,000,000", 
                                      "3.25% - 4.50%", "10-30 years", "Credit score 680+, Income verification, Property appraisal", "Active"));
        
        products.add(new CreditProduct("PRD-002", "Personal Loan Plus", "Personal", "$1,000", "$50,000", 
                                      "7.99% - 15.99%", "1-7 years", "Credit score 620+, Income verification", "Active"));
        
        products.add(new CreditProduct("PRD-003", "Auto Loan Standard", "Auto", "$5,000", "$100,000", 
                                      "4.50% - 7.25%", "1-7 years", "Credit score 640+, Income verification, Vehicle details", "Active"));
        
        products.add(new CreditProduct("PRD-004", "Business Expansion Loan", "Business", "$25,000", "$500,000", 
                                      "5.75% - 12.50%", "1-10 years", "Business plan, 2+ years in business, Financial statements", "Active"));
        
        products.add(new CreditProduct("PRD-005", "Education Loan", "Education", "$5,000", "$150,000", 
                                      "4.25% - 6.75%", "5-15 years", "Enrollment verification, Credit score 630+", "Active"));
        
        products.add(new CreditProduct("PRD-006", "Home Equity Line", "HELOC", "$10,000", "$250,000", 
                                      "4.50% - 8.00%", "5-20 years", "Home ownership, 70% max LTV, Credit score 700+", "Inactive"));
        
        products.add(new CreditProduct("PRD-007", "Quick Cash Loan", "Personal", "$500", "$5,000", 
                                      "18.99% - 24.99%", "6 months - 2 years", "Credit score 580+, Income verification", "Inactive"));
        
        productGrid.setItems(products);
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
        private final String nextPayment;
        private final double progress;
        private final String status;
        
        public Loan(String id, String clientName, String type, String amount, String interestRate, String term, 
                   String startDate, String nextPayment, double progress, String status) {
            this.id = id;
            this.clientName = clientName;
            this.type = type;
            this.amount = amount;
            this.interestRate = interestRate;
            this.term = term;
            this.startDate = startDate;
            this.nextPayment = nextPayment;
            this.progress = progress;
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
        
        public String getNextPayment() {
            return nextPayment;
        }
        
        public double getProgress() {
            return progress;
        }
        
        public String getStatus() {
            return status;
        }
    }
    
    // Credit Product data class
    public static class CreditProduct {
        private final String id;
        private final String name;
        private final String type;
        private final String minAmount;
        private final String maxAmount;
        private final String interestRate;
        private final String termRange;
        private final String requirements;
        private final String status;
        
        public CreditProduct(String id, String name, String type, String minAmount, String maxAmount, 
                            String interestRate, String termRange, String requirements, String status) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
            this.interestRate = interestRate;
            this.termRange = termRange;
            this.requirements = requirements;
            this.status = status;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public String getType() {
            return type;
        }
        
        public String getMinAmount() {
            return minAmount;
        }
        
        public String getMaxAmount() {
            return maxAmount;
        }
        
        public String getInterestRate() {
            return interestRate;
        }
        
        public String getTermRange() {
            return termRange;
        }
        
        public String getRequirements() {
            return requirements;
        }
        
        public String getStatus() {
            return status;
        }
    }
}