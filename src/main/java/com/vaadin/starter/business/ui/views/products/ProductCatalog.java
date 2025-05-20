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
import com.vaadin.flow.component.upload.Upload;
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

@Route(value = "product-catalog", layout = MainLayout.class)
@PageTitle("Product Catalog")
public class ProductCatalog extends SplitViewFrame {

    private Grid<Product> grid;
    private ListDataProvider<Product> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample Product class for demonstration
    private static class Product {
        private String id;
        private String name;
        private String category;
        private String description;
        private double price;
        private boolean isActive;
        private LocalDate createdDate;

        public Product(String id, String name, String category, String description, double price, boolean isActive, LocalDate createdDate) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.description = description;
            this.price = price;
            this.isActive = isActive;
            this.createdDate = createdDate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        public double getPrice() {
            return price;
        }

        public boolean isActive() {
            return isActive;
        }

        public LocalDate getCreatedDate() {
            return createdDate;
        }
    }

    // Sample data
    private List<Product> getProducts() {
        return Arrays.asList(
            new Product("P001", "Savings Account", "Accounts", 
                "Basic savings account with competitive interest rates", 
                0.0, true, LocalDate.now().minusDays(15)),
            new Product("P002", "Checking Account", "Accounts", 
                "Everyday banking account with no monthly fees", 
                0.0, true, LocalDate.now().minusDays(30)),
            new Product("P003", "Personal Loan", "Loans", 
                "Unsecured personal loan with flexible repayment options", 
                5.99, true, LocalDate.now().minusDays(45)),
            new Product("P004", "Mortgage", "Loans", 
                "Home loan with competitive rates and terms", 
                3.49, true, LocalDate.now().minusDays(60)),
            new Product("P005", "Credit Card", "Cards", 
                "Rewards credit card with cashback on purchases", 
                19.99, true, LocalDate.now().minusDays(90)),
            new Product("P006", "Investment Account", "Investments", 
                "Managed investment portfolio with diversified assets", 
                0.0, false, LocalDate.now().minusDays(120))
        );
    }

    public ProductCatalog() {
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
        dataProvider = DataProvider.ofCollection(getProducts());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Product::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(Product::getName)
                .setAutoWidth(true)
                .setHeader("Product Name")
                .setSortable(true);
        grid.addColumn(Product::getCategory)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createPriceInfo))
                .setAutoWidth(true)
                .setHeader("Price/Rate")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Created Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createPriceInfo(Product product) {
        if (product.getPrice() == 0.0) {
            return new Span("N/A");
        } else if (product.getCategory().equals("Loans") || product.getCategory().equals("Cards")) {
            return new Span(product.getPrice() + "%");
        } else {
            return new Span("$" + product.getPrice());
        }
    }

    private Component createActiveStatus(Product product) {
        Icon icon;
        if (product.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Product product) {
        return new Span(UIUtils.formatDate(product.getCreatedDate()));
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

    private void showDetails(Product product) {
        detailsDrawerHeader.setTitle(product.getName());
        detailsDrawer.setContent(createDetails(product));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Product product) {
        TextField id = new TextField();
        id.setValue(product.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(product.getName());
        name.setWidthFull();

        ComboBox<String> category = new ComboBox<>();
        category.setItems("Accounts", "Loans", "Cards", "Investments", "Insurance", "Services");
        category.setValue(product.getCategory());
        category.setWidthFull();

        NumberField price = new NumberField();
        price.setValue(product.getPrice());
        price.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(product.getDescription());
        description.setWidthFull();
        description.setHeight("150px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(product.isActive() ? "Active" : "Inactive");

        Upload productImage = new Upload();
        productImage.setMaxFiles(1);
        productImage.setDropLabel(new Span("Upload product image (or drop here)"));

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
        form.addFormItem(id, "Product ID");
        form.addFormItem(name, "Product Name");
        form.addFormItem(category, "Category");
        form.addFormItem(price, "Price/Rate");
        form.addFormItem(status, "Status");
        form.addFormItem(description, "Description");
        form.addFormItem(productImage, "Product Image");
        
        return form;
    }
}