package application.presentationLayer.screens.statScreens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.CategoryAxisBuilder;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxisBuilder;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import application.presentationLayer.ScreensController;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.daoLayer.DAOBorrow;

/**
 * 
 * @author Thomas
 *
 */
public class StatisticScreen implements Screen {
	
	@SuppressWarnings("unused")
	private ScreensController screenController;
	
	public StatisticScreen(ScreensController screenController) {
		this.screenController = screenController;
	}
	
	
	@Override
	@SuppressWarnings("all")
	public Pane getPane() {
        FlowPane root = new FlowPane();
        FlowPane background = new FlowPane();
        background.getChildren().add(root);
        background.getStyleClass().add("background-style");
        root.setStyle("-fx-background-color: #fff");
        String[] years = {"2012", "2013", "2014"};
        
		DAOBorrow daoB = new DAOBorrow();
		
		int year2012 = daoB.countByYear(2012);
		int year2013 = daoB.countByYear(2013);
		int year2014 = daoB.countByYear(2014);
        
        CategoryAxis xAxis = CategoryAxisBuilder.create()
                .categories(FXCollections.<String>observableArrayList(years)).build();
        NumberAxis yAxis = NumberAxisBuilder.create()
                           .label("Books borrowed")
                           .lowerBound(0.0d)
                           .upperBound(10000.0d)
                           .tickUnit(1000.0d).build();
        ObservableList<StackedBarChart.Series> barChartData = FXCollections.observableArrayList(
            new StackedBarChart.Series("Number of books", FXCollections.observableArrayList(
               new StackedBarChart.Data(years[0], year2012),
               new StackedBarChart.Data(years[1], year2013),
               new StackedBarChart.Data(years[2], year2014)
            ))
        );

        StackedBarChart chart = new StackedBarChart(xAxis, yAxis, barChartData, 25.0d);
        root.getChildren().add(chart);
        root.setAlignment(Pos.CENTER);
        background.setAlignment(Pos.CENTER);
		return background;
	}

}
