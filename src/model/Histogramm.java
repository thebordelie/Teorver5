package model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;

public class Histogramm extends ApplicationFrame {

    public Histogramm(String applicationTitle, String chartTitle, Object[] setValues, Long[] distribution) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "xi",
                "ni/h",
                createDataset(setValues, distribution),
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(Object[] setValues, Long[] distribution) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double h = ((Double) setValues[setValues.length - 1] - (Double) setValues[0]) / (Math.round(1 + Math.log(20) / Math.log(2)));
        for (double counter = (Double) setValues[0] - h/2; counter < (Double) setValues[setValues.length - 1] + h; counter += h) {
            int number = 0;
            for (int value = 0; value < distribution.length; value++) {
                if ((Double) setValues[value] <= counter && (Double) setValues[value] >= counter - h) {

                    number += distribution[value];
                }
            }
            dataset.addValue(number / h, "Функция распределения", String.format("%f - %f", counter - h, counter));
        }
        return dataset;
    }
}

