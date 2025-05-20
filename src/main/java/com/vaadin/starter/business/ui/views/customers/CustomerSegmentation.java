package com.vaadin.starter.business.ui.views.customers;

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

@Route(value = "customer-segmentation", layout = MainLayout.class)
@PageTitle("Customer Segmentation")
public class CustomerSegmentation extends SplitViewFrame {

    private Grid<Segment> grid;
    private ListDataProvider<Segment> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample Segment class for demonstration
    private static class Segment {
        private String id;
        private String name;
        private String description;
        private int customerCount;
        private double averageBalance;
        private String riskProfile;
        private boolean isActive;
        private LocalDate createdDate;

        public Segment(String id, String name, String description, int customerCount, 
                      double averageBalance, String riskProfile, boolean isActive, 
                      LocalDate createdDate) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.customerCount = customerCount;
            this.averageBalance = averageBalance;
            this.riskProfile = riskProfile;
            this.isActive = isActive;
            this.createdDate = createdDate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getCustomerCount() {
            return customerCount;
        }

        public double getAverageBalance() {
            return averageBalance;
        }

        public String getRiskProfile() {
            return riskProfile;
        }

        public boolean isActive() {
            return isActive;
        }

        public LocalDate getCreatedDate() {
            return createdDate;
        }
    }

    // Sample data
    private List<Segment> getSegments() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
            new Segment("SEG001", "High Net Worth", 
                "Customers with assets over $1M", 
                120, 1450000.00, "Low", true, now.minusDays(180)),
            new Segment("SEG002", "Mass Affluent", 
                "Customers with assets between $100K and $1M", 
                850, 350000.00, "Medium", true, now.minusDays(180)),
            new Segment("SEG003", "Retail Banking", 
                "Regular retail banking customers", 
                5600, 15000.00, "Medium", true, now.minusDays(180)),
            new Segment("SEG004", "Small Business", 
                "Small business owners and entrepreneurs", 
                340, 75000.00, "Medium-High", true, now.minusDays(90)),
            new Segment("SEG005", "Students", 
                "College and university students", 
                1200, 2500.00, "Low", true, now.minusDays(60)),
            new Segment("SEG006", "Seniors", 
                "Retired customers over 65", 
                780, 120000.00, "Low", true, now.minusDays(120)),
            new Segment("SEG007", "New Customers", 
                "Customers who joined in the last 3 months", 
                450, 8500.00, "Medium", true, now.minusDays(30))
        );
    }

    public CustomerSegmentation() {
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
        dataProvider = DataProvider.ofCollection(getSegments());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Segment::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(Segment::getName)
                .setAutoWidth(true)
                .setHeader("Segment Name")
                .setSortable(true);
        grid.addColumn(Segment::getCustomerCount)
                .setAutoWidth(true)
                .setHeader("Customers")
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createAverageBalance))
                .setAutoWidth(true)
                .setHeader("Avg. Balance")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(Segment::getRiskProfile)
                .setAutoWidth(true)
                .setHeader("Risk Profile")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Created")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createAverageBalance(Segment segment) {
        return new Span(UIUtils.formatAmount(segment.getAverageBalance()));
    }

    private Component createActiveStatus(Segment segment) {
        Icon icon;
        if (segment.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Segment segment) {
        return new Span(UIUtils.formatDate(segment.getCreatedDate()));
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

    private void showDetails(Segment segment) {
        detailsDrawerHeader.setTitle(segment.getName());
        detailsDrawer.setContent(createDetails(segment));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Segment segment) {
        TextField id = new TextField();
        id.setValue(segment.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(segment.getName());
        name.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(segment.getDescription());
        description.setWidthFull();
        description.setHeight("100px");

        NumberField customerCount = new NumberField();
        customerCount.setValue((double) segment.getCustomerCount());
        customerCount.setWidthFull();

        NumberField averageBalance = new NumberField();
        averageBalance.setValue(segment.getAverageBalance());
        averageBalance.setWidthFull();
        averageBalance.setPrefixComponent(new Span("$"));

        ComboBox<String> riskProfile = new ComboBox<>();
        riskProfile.setItems("Low", "Medium", "Medium-High", "High");
        riskProfile.setValue(segment.getRiskProfile());
        riskProfile.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(segment.isActive() ? "Active" : "Inactive");

        // Marketing targeting options
        ComboBox<String> marketingChannel = new ComboBox<>();
        marketingChannel.setItems("Email", "SMS", "Direct Mail", "Phone", "All Channels");
        marketingChannel.setValue("Email");
        marketingChannel.setWidthFull();

        ComboBox<String> productRecommendation = new ComboBox<>();
        productRecommendation.setItems("Savings Account", "Investment Account", "Credit Card", "Personal Loan", "Mortgage", "Insurance");
        productRecommendation.setValue("Savings Account");
        productRecommendation.setWidthFull();

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
        form.addFormItem(id, "Segment ID");
        form.addFormItem(name, "Segment Name");
        form.addFormItem(description, "Description");
        form.addFormItem(customerCount, "Customer Count");
        form.addFormItem(averageBalance, "Average Balance");
        form.addFormItem(riskProfile, "Risk Profile");
        form.addFormItem(status, "Status");
        form.addFormItem(marketingChannel, "Marketing Channel");
        form.addFormItem(productRecommendation, "Product Recommendation");
        
        return form;
    }
}