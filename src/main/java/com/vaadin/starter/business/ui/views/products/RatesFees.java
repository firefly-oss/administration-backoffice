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

@Route(value = "rates-fees", layout = MainLayout.class)
@PageTitle("Rates and Fees")
public class RatesFees extends SplitViewFrame {

    private Grid<RateFee> grid;
    private ListDataProvider<RateFee> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample RateFee class for demonstration
    private static class RateFee {
        private String id;
        private String name;
        private String productCategory;
        private String type;
        private double value;
        private String calculationMethod;
        private boolean isActive;
        private LocalDate effectiveDate;
        private LocalDate expirationDate;

        public RateFee(String id, String name, String productCategory, String type, 
                      double value, String calculationMethod, boolean isActive, 
                      LocalDate effectiveDate, LocalDate expirationDate) {
            this.id = id;
            this.name = name;
            this.productCategory = productCategory;
            this.type = type;
            this.value = value;
            this.calculationMethod = calculationMethod;
            this.isActive = isActive;
            this.effectiveDate = effectiveDate;
            this.expirationDate = expirationDate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public String getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        public String getCalculationMethod() {
            return calculationMethod;
        }

        public boolean isActive() {
            return isActive;
        }

        public LocalDate getEffectiveDate() {
            return effectiveDate;
        }

        public LocalDate getExpirationDate() {
            return expirationDate;
        }
    }

    // Sample data
    private List<RateFee> getRatesFees() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
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
        );
    }

    public RatesFees() {
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
        dataProvider = DataProvider.ofCollection(getRatesFees());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(RateFee::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(RateFee::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        grid.addColumn(RateFee::getProductCategory)
                .setAutoWidth(true)
                .setHeader("Product Category")
                .setSortable(true);
        grid.addColumn(RateFee::getType)
                .setAutoWidth(true)
                .setHeader("Type")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createValueInfo))
                .setAutoWidth(true)
                .setHeader("Value")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createEffectiveDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Effective Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createValueInfo(RateFee rateFee) {
        if (rateFee.getType().equals("Interest Rate")) {
            return new Span(rateFee.getValue() + "%");
        } else {
            return new Span("$" + String.format("%.2f", rateFee.getValue()));
        }
    }

    private Component createActiveStatus(RateFee rateFee) {
        Icon icon;
        if (rateFee.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createEffectiveDate(RateFee rateFee) {
        return new Span(UIUtils.formatDate(rateFee.getEffectiveDate()));
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

    private void showDetails(RateFee rateFee) {
        detailsDrawerHeader.setTitle(rateFee.getName());
        detailsDrawer.setContent(createDetails(rateFee));
        detailsDrawer.show();
    }

    private FormLayout createDetails(RateFee rateFee) {
        TextField id = new TextField();
        id.setValue(rateFee.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(rateFee.getName());
        name.setWidthFull();

        ComboBox<String> productCategory = new ComboBox<>();
        productCategory.setItems("Accounts", "Loans", "Cards", "Investments", "Insurance", "Services", "General");
        productCategory.setValue(rateFee.getProductCategory());
        productCategory.setWidthFull();

        ComboBox<String> type = new ComboBox<>();
        type.setItems("Interest Rate", "Fee", "Discount", "Penalty");
        type.setValue(rateFee.getType());
        type.setWidthFull();

        NumberField value = new NumberField();
        value.setValue(rateFee.getValue());
        value.setWidthFull();

        ComboBox<String> calculationMethod = new ComboBox<>();
        calculationMethod.setItems("Fixed Amount", "Percentage", "Annual Percentage Rate", "Annual Percentage Yield");
        calculationMethod.setValue(rateFee.getCalculationMethod());
        calculationMethod.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(rateFee.isActive() ? "Active" : "Inactive");

        // Date fields would typically use DatePicker, but for simplicity using TextField
        TextField effectiveDate = new TextField();
        effectiveDate.setValue(UIUtils.formatDate(rateFee.getEffectiveDate()));
        effectiveDate.setWidthFull();

        TextField expirationDate = new TextField();
        expirationDate.setValue(UIUtils.formatDate(rateFee.getExpirationDate()));
        expirationDate.setWidthFull();

        TextArea description = new TextArea();
        description.setPlaceholder("Enter description...");
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
        form.addFormItem(id, "ID");
        form.addFormItem(name, "Name");
        form.addFormItem(productCategory, "Product Category");
        form.addFormItem(type, "Type");
        form.addFormItem(value, "Value");
        form.addFormItem(calculationMethod, "Calculation Method");
        form.addFormItem(status, "Status");
        form.addFormItem(effectiveDate, "Effective Date");
        form.addFormItem(expirationDate, "Expiration Date");
        form.addFormItem(description, "Description");
        
        return form;
    }
}