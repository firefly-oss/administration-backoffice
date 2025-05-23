package com.vaadin.starter.business.ui.views.products;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Route(value = "lending-configuration", layout = MainLayout.class)
@PageTitle("Lending Configuration")
public class LendingConfiguration extends SplitViewFrame {

    private Grid<LendingConfig> grid;
    private ListDataProvider<LendingConfig> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample LendingConfig class for demonstration
    private static class LendingConfig {
        private String id;
        private String name;
        private String loanType;
        private double minAmount;
        private double maxAmount;
        private double baseInterestRate;
        private int minTerm;
        private int maxTerm;
        private String riskCategory;
        private boolean isActive;
        private LocalDate lastUpdated;

        public LendingConfig(String id, String name, String loanType, double minAmount, 
                           double maxAmount, double baseInterestRate, int minTerm, 
                           int maxTerm, String riskCategory, boolean isActive, 
                           LocalDate lastUpdated) {
            this.id = id;
            this.name = name;
            this.loanType = loanType;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
            this.baseInterestRate = baseInterestRate;
            this.minTerm = minTerm;
            this.maxTerm = maxTerm;
            this.riskCategory = riskCategory;
            this.isActive = isActive;
            this.lastUpdated = lastUpdated;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLoanType() {
            return loanType;
        }

        public double getMinAmount() {
            return minAmount;
        }

        public double getMaxAmount() {
            return maxAmount;
        }

        public double getBaseInterestRate() {
            return baseInterestRate;
        }

        public int getMinTerm() {
            return minTerm;
        }

        public int getMaxTerm() {
            return maxTerm;
        }

        public String getRiskCategory() {
            return riskCategory;
        }

        public boolean isActive() {
            return isActive;
        }

        public LocalDate getLastUpdated() {
            return lastUpdated;
        }

        public String getAmountRange() {
            return String.format("$%.0f - $%.0f", minAmount, maxAmount);
        }

        public String getTermRange() {
            return String.format("%d - %d months", minTerm, maxTerm);
        }
    }

    // Sample data
    private List<LendingConfig> getLendingConfigs() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
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
        );
    }

    public LendingConfiguration() {
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        return content;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(getLendingConfigs());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(LendingConfig::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(LendingConfig::getName)
                .setAutoWidth(true)
                .setHeader("Configuration Name")
                .setSortable(true);
        grid.addColumn(LendingConfig::getLoanType)
                .setAutoWidth(true)
                .setHeader("Loan Type")
                .setSortable(true);
        grid.addColumn(LendingConfig::getAmountRange)
                .setAutoWidth(true)
                .setHeader("Amount Range")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createInterestRateInfo))
                .setAutoWidth(true)
                .setHeader("Base Rate")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(LendingConfig::getTermRange)
                .setAutoWidth(true)
                .setHeader("Term Range")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createInterestRateInfo(LendingConfig config) {
        return new Span(config.getBaseInterestRate() + "%");
    }

    private Component createActiveStatus(LendingConfig config) {
        Icon icon;
        if (config.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private DetailsDrawer createDetailsDrawer() {
        detailsDrawer = new DetailsDrawer(DetailsDrawer.Position.BOTTOM);

        // Header
        detailsDrawerHeader = new DetailsDrawerHeader("");
        detailsDrawerHeader.addCloseListener(buttonClickEvent -> detailsDrawer.hide());
        detailsDrawer.setHeader(detailsDrawerHeader);

        // Footer
        DetailsDrawerFooter footer = new DetailsDrawerFooter();
        footer.addSaveListener(e -> {
            detailsDrawer.hide();
            UIUtils.showNotification("Changes saved.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(LendingConfig config) {
        detailsDrawerHeader.setTitle(config.getName());
        detailsDrawer.setContent(createDetails(config));
        detailsDrawer.show();
    }

    private FormLayout createDetails(LendingConfig config) {
        TextField id = new TextField();
        id.setValue(config.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(config.getName());
        name.setWidthFull();

        ComboBox<String> loanType = new ComboBox<>();
        loanType.setItems("Personal", "Mortgage", "Auto", "Business", "Student", "Credit Card");
        loanType.setValue(config.getLoanType());
        loanType.setWidthFull();

        NumberField minAmount = new NumberField();
        minAmount.setValue(config.getMinAmount());
        minAmount.setWidthFull();

        NumberField maxAmount = new NumberField();
        maxAmount.setValue(config.getMaxAmount());
        maxAmount.setWidthFull();

        NumberField baseInterestRate = new NumberField();
        baseInterestRate.setValue(config.getBaseInterestRate());
        baseInterestRate.setWidthFull();
        baseInterestRate.setSuffixComponent(new Span("%"));

        NumberField minTerm = new NumberField();
        minTerm.setValue((double) config.getMinTerm());
        minTerm.setWidthFull();

        NumberField maxTerm = new NumberField();
        maxTerm.setValue((double) config.getMaxTerm());
        maxTerm.setWidthFull();

        ComboBox<String> riskCategory = new ComboBox<>();
        riskCategory.setItems("Low Risk", "Standard", "Premium", "High Risk");
        riskCategory.setValue(config.getRiskCategory());
        riskCategory.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(config.isActive() ? "Active" : "Inactive");

        TextArea description = new TextArea();
        description.setPlaceholder("Enter configuration description...");
        description.setWidthFull();
        description.setHeight("100px");

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("1024px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(id, "Configuration ID");
        form.addFormItem(name, "Configuration Name");
        form.addFormItem(loanType, "Loan Type");
        form.addFormItem(minAmount, "Minimum Amount ($)");
        form.addFormItem(maxAmount, "Maximum Amount ($)");
        form.addFormItem(baseInterestRate, "Base Interest Rate");
        form.addFormItem(minTerm, "Minimum Term (months)");
        form.addFormItem(maxTerm, "Maximum Term (months)");
        form.addFormItem(riskCategory, "Risk Category");
        form.addFormItem(status, "Status");
        form.addFormItem(description, "Description");
        
        return form;
    }
}