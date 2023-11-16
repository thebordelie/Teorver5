package model;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSet {

    public XYDataset createDatasetForTask3(Object[] setValues, Long[] distribution) {
        final XYSeries graph = new XYSeries("Полигон частот");
        double h = ((Double) setValues[setValues.length - 1] - (Double) setValues[0]) / (Math.round(1 + Math.log(20) / Math.log(2)));
//        for (int counter = 0; counter < distribution.length; counter++) {
//            double value = (Double) setValues[counter];
//            graph.add(value, distribution[counter]);
//        }

        for (double counter = (Double) setValues[0] - h/2; counter < (Double) setValues[setValues.length - 1] + h; counter += h) {
            int number = 0;
            for (int value = 0; value < distribution.length; value++) {
                if ((Double) setValues[value] <= counter && (Double) setValues[value] >= counter - h) {

                    number += distribution[value];
                }


            }
            graph.add(counter, number);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graph);
        return dataset;
    }

    public XYDataset createDatasetForTask1(Object[] setValues, Long[] distribution) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        double probability = 0;
        for (int counter = 1; counter < distribution.length; counter++) {
            final XYSeries graph = new XYSeries("");
            probability += Double.parseDouble(distribution[counter - 1].toString()) / 20;
            double value = (Double) setValues[counter - 1];
            double value1 = (Double) setValues[counter];
            graph.add(value, probability);
            graph.add(value1, probability);
            dataset.addSeries(graph);
        }
        final XYSeries graph = new XYSeries("");
        double value = (Double) setValues[setValues.length - 1];
        double value1 = (Double) setValues[setValues.length - 1] + 1;
        graph.add(value, 1);
        graph.add(value1, 1);
        dataset.addSeries(graph);
        return dataset;

    }
}
