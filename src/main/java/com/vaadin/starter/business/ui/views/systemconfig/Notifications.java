package com.vaadin.starter.business.ui.views.systemconfig;

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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.Person;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
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

@Route(value = "notifications", layout = MainLayout.class)
@PageTitle("Notifications")
public class Notifications extends SplitViewFrame {

    private Grid<Person> grid;
    private ListDataProvider<Person> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    public Notifications() {
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);

        filter();
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
        dataProvider = DataProvider.ofCollection(DummyData.getPersons());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Person::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createNotificationInfo))
                .setAutoWidth(true)
                .setHeader("Notification");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createChannel))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Channel")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastModified))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createNotificationInfo(Person person) {
        String[] notifications = {
            "Account Activity Alert", 
            "Transaction Confirmation", 
            "Security Alert", 
            "Payment Due Reminder", 
            "New Feature Announcement", 
            "System Maintenance Notice"
        };
        
        String notification = notifications[(int)(Math.abs(person.getId()) % notifications.length)];
        
        ListItem item = new ListItem(
                new Initials(person.getInitials()), 
                notification,
                "Notification template for " + person.getEmail());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Person person) {
        boolean isActive = person.getRandomBoolean();
        Icon icon;
        String text;

        if (isActive) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Active";
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
            text = "Inactive";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createChannel(Person person) {
        String[] channels = {
            "Email", 
            "SMS", 
            "Push Notification", 
            "In-App", 
            "All Channels"
        };
        
        String channel = channels[(int)(Math.abs(person.getId()) % channels.length)];
        return new Span(channel);
    }

    private Component createLastModified(Person person) {
        return new Span(UIUtils.formatDate(person.getLastModified()));
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
            UIUtils.showNotification("Notification template updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Person person) {
        String[] notifications = {
            "Account Activity Alert", 
            "Transaction Confirmation", 
            "Security Alert", 
            "Payment Due Reminder", 
            "New Feature Announcement", 
            "System Maintenance Notice"
        };
        
        String notification = notifications[(int)(Math.abs(person.getId()) % notifications.length)];
        
        detailsDrawerHeader.setTitle("Notification: " + notification);
        detailsDrawer.setContent(createDetails(person, notification));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Person person, String notification) {
        TextField notificationName = new TextField();
        notificationName.setValue(notification);
        notificationName.setWidthFull();

        TextArea template = new TextArea();
        template.setValue("Dear {{customer.name}},\n\nThis is a notification about {{notification.subject}}.\n\nRegards,\nThe System");
        template.setWidthFull();
        template.setMinHeight("150px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive", "Draft");
        status.setValue(person.getRandomBoolean() ? "Active" : "Inactive");

        ComboBox<String> channel = new ComboBox<>();
        String[] channels = {"Email", "SMS", "Push Notification", "In-App", "All Channels"};
        channel.setItems(channels);
        channel.setValue(channels[(int)(Math.abs(person.getId()) % channels.length)]);
        channel.setWidthFull();

        ComboBox<String> frequency = new ComboBox<>();
        frequency.setItems("Immediate", "Daily", "Weekly", "Monthly", "On Demand");
        frequency.setValue("Immediate");
        frequency.setWidthFull();

        ComboBox<String> audience = new ComboBox<>();
        audience.setItems("All Users", "Customers", "Employees", "Administrators", "Custom Group");
        audience.setValue("All Users");
        audience.setWidthFull();

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
        form.addFormItem(notificationName, "Notification Name");
        form.addFormItem(template, "Template");
        form.addFormItem(status, "Status");
        form.addFormItem(channel, "Channel");
        form.addFormItem(frequency, "Frequency");
        form.addFormItem(audience, "Target Audience");

        return form;
    }

    private void filter() {
        // We're using the same data source but could filter differently if needed
        dataProvider.setFilterByValue(Person::getRole, Person.Role.MANAGER);
    }
}