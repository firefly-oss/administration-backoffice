package com.catalis.backoffice.dashboard.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.Color;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Random;

@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Dashboard | Bank Backoffice")
public class DashboardView extends VerticalLayout {

    private final Random random = new Random();

    public DashboardView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(new ViewToolbar("Dashboard"));

        Board board = new Board();
        board.addRow(createHealthCard(), createTransactionsCard(), createClientsCard());
        board.setSizeFull();

        add(board);
    }

    private Component createHealthCard() {
        VerticalLayout card = createCard("System Health");
        card.add(createHealthChart());

        // Add health status indicators
        HorizontalLayout statusLayout = new HorizontalLayout();
        statusLayout.setWidthFull();
        statusLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        statusLayout.add(createStatusBadge("API", true));
        statusLayout.add(createStatusBadge("Database", true));
        statusLayout.add(createStatusBadge("Services", true));

        card.add(statusLayout);

        return card;
    }

    private Component createTransactionsCard() {
        VerticalLayout card = createCard("Total Transactions");

        // Add transaction statistics
        H2 transactionCount = new H2("12,543");
        transactionCount.addClassNames(
                LumoUtility.Margin.Vertical.NONE,
                LumoUtility.FontSize.XXXLARGE,
                LumoUtility.TextColor.PRIMARY
        );

        Span growthIndicator = new Span("↑ 8.2%");
        growthIndicator.addClassNames(
                LumoUtility.TextColor.SUCCESS,
                LumoUtility.FontWeight.BOLD
        );

        Span timeFrame = new Span("since last month");
        timeFrame.addClassNames(
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.FontSize.XSMALL
        );

        HorizontalLayout growthLayout = new HorizontalLayout(growthIndicator, timeFrame);
        growthLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        growthLayout.setSpacing(true);

        card.add(transactionCount, growthLayout, createTransactionChart());

        return card;
    }

    private Component createClientsCard() {
        VerticalLayout card = createCard("Clients / Alerts");

        // Add client statistics
        H3 clientCount = new H3("Total Clients: 3,254");
        clientCount.addClassNames(
                LumoUtility.Margin.Vertical.SMALL,
                LumoUtility.FontWeight.MEDIUM
        );

        // Add alerts
        VerticalLayout alertsLayout = new VerticalLayout();
        alertsLayout.setPadding(false);
        alertsLayout.setSpacing(false);

        alertsLayout.add(createAlert("High risk client activity detected", VaadinIcon.EXCLAMATION_CIRCLE, "error"));
        alertsLayout.add(createAlert("5 new clients pending approval", VaadinIcon.USER_CLOCK, "warning"));
        alertsLayout.add(createAlert("Monthly compliance check completed", VaadinIcon.CHECK_CIRCLE, "success"));

        card.add(clientCount, alertsLayout);

        return card;
    }

    private VerticalLayout createCard(String title) {
        VerticalLayout card = new VerticalLayout();
        card.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.MEDIUM
        );
        card.setSpacing(false);

        H3 header = new H3(title);
        header.addClassNames(
                LumoUtility.Margin.NONE,
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.TextColor.SECONDARY
        );

        card.add(header);
        return card;
    }

    private Component createHealthChart() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setBackgroundColor(null);
        configuration.getTitle().setText(null);

        Pane pane = new Pane();
        pane.setCenter(new String[] {"50%", "50%"});
        pane.setSize("100%");
        pane.setStartAngle(-90);
        pane.setEndAngle(90);
        configuration.addPane(pane);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.getLabels().setEnabled(false);
        yAxis.setTickPosition(TickPosition.INSIDE);
        yAxis.setTickColor(new SolidColor("#FFFFFF"));
        yAxis.setTickLength(2);
        yAxis.setTickWidth(0);
        yAxis.setMinorTickInterval(null);

        Stop[] stops = new Stop[] {
                new Stop(0.1f, new SolidColor("#4CAF50")), // Green
                new Stop(0.5f, new SolidColor("#FFEB3B")), // Yellow
                new Stop(0.9f, new SolidColor("#F44336"))  // Red
        };
        yAxis.setStops(stops);

        configuration.addyAxis(yAxis);

        PlotOptionsSolidgauge plotOptions = new PlotOptionsSolidgauge();
        DataLabels dataLabels = new DataLabels();
        dataLabels.setY(5);
        dataLabels.setUseHTML(true);
        dataLabels.setFormat("<div style='text-align:center'><span style='font-size:25px;'>{y}%</span></div>");
        plotOptions.setDataLabels(dataLabels);
        configuration.setPlotOptions(plotOptions);

        ListSeries series = new ListSeries("System Health", 85);
        configuration.addSeries(series);

        chart.setHeight("200px");

        return chart;
    }

    private Component createTransactionChart() {
        Chart chart = new Chart(ChartType.AREASPLINE);

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setBackgroundColor(null);
        configuration.getTitle().setText(null);

        XAxis xAxis = new XAxis();
        String[] categories = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep" };
        xAxis.setCategories(categories);
        configuration.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle("Transactions"));
        configuration.addyAxis(yAxis);

        PlotOptionsAreaspline plotOptions = new PlotOptionsAreaspline();
        plotOptions.setFillOpacity(0.5);
        configuration.setPlotOptions(plotOptions);

        ListSeries series = new ListSeries("Transactions");
        series.setData(8000, 7500, 9000, 8500, 10000, 11500, 10500, 12000, 12500);
        configuration.addSeries(series);

        chart.setHeight("200px");

        return chart;
    }

    private Component createStatusBadge(String label, boolean isActive) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSpacing(true);

        Span statusDot = new Span();
        statusDot.getElement().getStyle().set("width", "10px");
        statusDot.getElement().getStyle().set("height", "10px");
        statusDot.getElement().getStyle().set("border-radius", "50%");
        statusDot.getElement().getStyle().set("display", "inline-block");
        statusDot.getElement().getStyle().set("background-color", isActive ? "var(--lumo-success-color)" : "var(--lumo-error-color)");

        Span statusLabel = new Span(label);
        statusLabel.addClassNames(LumoUtility.FontSize.SMALL);

        layout.add(statusDot, statusLabel);
        return layout;
    }

    private Component createAlert(String message, VaadinIcon icon, String severity) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.addClassNames(LumoUtility.Padding.Vertical.XSMALL);

        Icon alertIcon = icon.create();
        if ("error".equals(severity)) {
            alertIcon.setColor("var(--lumo-error-color)");
        } else if ("warning".equals(severity)) {
            alertIcon.setColor("var(--lumo-warning-color)");
        } else if ("success".equals(severity)) {
            alertIcon.setColor("var(--lumo-success-color)");
        }

        Span alertMessage = new Span(message);
        alertMessage.addClassNames(LumoUtility.FontSize.SMALL);

        layout.add(alertIcon, alertMessage);
        return layout;
    }
}
