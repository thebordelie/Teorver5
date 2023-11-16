import model.DataSet;
import model.Graph;
import model.Histogramm;
import model.Table;
import org.jfree.ui.RefineryUtilities;

import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        Double[] values = new Double[]{1.07, 1.59, -1.49, -0.10, 0.11, 1.18, 0.35, -0.73, 1.07, 0.31, -0.26, -1.2, -0.35, 0.73, 1.01, -0.12, 0.28, -1.32, -1.1, -0.26};
        Table table = new Table(values);
        table.sortValues();
        System.out.println("Вариационный ряд");
        System.out.println(table.printTable(20, values));
        System.out.println("Минимальный элемент = " + table.getMinValue() + "\nМаксимальные элемент = " + table.getMaxValue());
        System.out.println("Размах выборки = " + table.calculateScope());

        System.out.println(table.printTable(18, (new LinkedHashSet<>(table.getValues())).toArray(), table.getDistribution()));
        Object[] setValues = (new LinkedHashSet<>(table.getValues())).toArray();
        Long[] distribution = table.getDistribution();
        Double expectation = 0d;
        for (int counter = 0; counter < distribution.length; counter++) {
            expectation += ((Double) setValues[counter]) * distribution[counter];
        }
        expectation /= values.length;
        System.out.printf("Математическое ожидание: %f\n", expectation);
        double variance = 0d;
        for (int counter = 0; counter < distribution.length; counter++) {
            variance += Math.pow(((Double) setValues[counter] - expectation), 2) * distribution[counter];
        }
        variance /= values.length;
        System.out.printf("Дисперсия: %f\n", variance);
        System.out.printf("Отклонение: %f\n", Math.pow(variance * values.length / (values.length - 1), 0.5));


        double probability = 0;
        System.out.printf("%7s---\n", " ");
        System.out.printf("%7s| %f, x < %s\n", " ", probability, setValues[0]);
        for (int counter = 1; counter < distribution.length; counter++) {
            probability += Double.parseDouble(distribution[counter - 1].toString()) / values.length;
            if (counter == distribution.length / 2)
                System.out.printf("F(x) = | %f, %s < x <= %s\n", probability, setValues[counter - 1], setValues[counter]);
            else
                System.out.printf("%7s| %f, %s < x <= %s\n", " ", probability, setValues[counter - 1], setValues[counter]);
        }
        probability += Double.parseDouble(distribution[distribution.length - 1].toString()) / values.length;
        System.out.printf("%7s| %f, x > %s\n", " ", probability, setValues[distribution.length - 1]);
        System.out.printf("%7s---\n", " ");

        DataSet dataSet = new DataSet();

        Graph graph = new Graph("График", "Функция распределения", dataSet.createDatasetForTask1(setValues, distribution));
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);

        Graph graph1 = new Graph("График", "Полигон частот", dataSet.createDatasetForTask3(setValues, distribution));
        graph1.pack();
        RefineryUtilities.centerFrameOnScreen(graph1);
        graph1.setVisible(true);

        Histogramm histogramm = new Histogramm("График", "Гистограмма", setValues, distribution);
        histogramm.pack();
        RefineryUtilities.centerFrameOnScreen(histogramm);
        histogramm.setVisible(true);

    }
}
