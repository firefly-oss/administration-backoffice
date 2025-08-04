package com.vaadin.starter.business.ui.views.channelsandservices;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Channel;
import com.vaadin.starter.business.backend.service.ChannelsAndServicesService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "channel-integration", layout = MainLayout.class)
@PageTitle("Channel Integration")
public class ChannelIntegration extends SplitViewFrame {

    private Grid<Channel> grid;
    private ListDataProvider<Channel> dataProvider;

    private final ChannelsAndServicesService channelsAndServicesService;

    public ChannelIntegration(ChannelsAndServicesService channelsAndServicesService) {
        this.channelsAndServicesService = channelsAndServicesService;

        setViewContent(createContent());
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
        dataProvider = DataProvider.ofCollection(channelsAndServicesService.getChannels());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Channel::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createChannelInfo))
                .setAutoWidth(true)
                .setHeader("Channel");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createIntegrationType))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Integration Type")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createLastUpdated))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Updated")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createChannelInfo(Channel channel) {
        ListItem item = new ListItem(
                new Initials(channel.getName().substring(0, 2)), 
                channel.getName(),
                channel.getDescription());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(Channel channel) {
        Icon icon;
        if (channel.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createIntegrationType(Channel channel) {
        return new Span(channel.getIntegrationType());
    }

    private Component createLastUpdated(Channel channel) {
        return new Span(UIUtils.formatDate(channel.getLastUpdated().toLocalDate()));
    }

    private void showDetails(Channel channel) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");

        // Add title
        dialog.add(new H3("Channel: " + channel.getName()));

        // Add content
        FormLayout form = createDetails(channel);

        // Add buttons
        Button closeButton = new Button("Close", e -> dialog.close());
        Button saveButton = new Button("Save", e -> {
            dialog.close();
            UIUtils.showNotification("Channel integration updated.");
        });
        saveButton.getElement().getThemeList().add("primary");

        HorizontalLayout buttons = new HorizontalLayout(closeButton, saveButton);
        buttons.setJustifyContentMode(FlexLayout.JustifyContentMode.END);
        buttons.setWidthFull();

        // Create layout for dialog content
        VerticalLayout dialogLayout = new VerticalLayout(form, buttons);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(true);
        dialogLayout.getStyle().set("padding", "0 1em 1em 1em");

        dialog.add(dialogLayout);
        dialog.open();
    }

    private FormLayout createDetails(Channel channel) {
        TextField channelName = new TextField();
        channelName.setValue(channel.getName());
        channelName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(channel.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive", "Maintenance");
        status.setValue(channel.isActive() ? "Active" : "Inactive");

        ComboBox<String> integrationType = new ComboBox<>();
        String[] types = {"API", "Webhook", "File Transfer", "Direct Database", "Message Queue"};
        integrationType.setItems(types);
        integrationType.setValue(channel.getIntegrationType());
        integrationType.setWidthFull();

        TextField endpoint = new TextField();
        endpoint.setValue(channel.getEndpoint());
        endpoint.setWidthFull();

        ComboBox<String> securityLevel = new ComboBox<>();
        securityLevel.setItems("Basic", "OAuth 2.0", "API Key", "JWT", "mTLS");
        securityLevel.setValue(channel.getSecurityLevel());
        securityLevel.setWidthFull();

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
        form.addFormItem(channelName, "Channel Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(integrationType, "Integration Type");
        form.addFormItem(endpoint, "Endpoint URL");
        form.addFormItem(securityLevel, "Security Level");

        return form;
    }

    // No need for filtering in this view
    // If filtering is needed, it can be implemented based on Channel properties
}
