package model;

import java.util.*;
import java.util.stream.Collectors;

public class Table {
    private List<Double> values;

    public Table(Double... values) {
        this.values = Arrays.asList(values);
    }

    public void sortValues() {
        Collections.sort(values);
    }

    public List<Double> getValues() {
        return values;
    }

    public double getMinValue() {
        sortValues();
        return values.get(0);
    }

    public double getMaxValue() {
        sortValues();
        return values.get(values.size() - 1);
    }

    public double calculateScope() {
        return getMaxValue() - getMinValue();
    }

    public Long[] getDistribution() {
        Long[] probability = new Long[new LinkedHashSet<>(values).size()];
        Map<Double, Long> buffer = values.stream().collect(Collectors.groupingBy(value -> value, Collectors.counting()));
        int counter = 0;
        for (Double value : new LinkedHashSet<>(values)) {
            probability[counter] = buffer.get(value);
            counter++;
        }
        return probability;
    }

    public String printTable(int size, Object[]... objects) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (int counter = 1; counter < size + 1; counter++) {
            builder2.append(String.format("|%3s%-6s", " ", "x" + counter));
            builder1.append("=".repeat(10));
            builder.append("-".repeat(10));
        }

        builder.append("\n").append(builder2);
        for (Object[] objects1 : objects) {
            builder.append("\n").append(builder1).append("\n");
            builder1 = new StringBuilder();
            for (Object object : objects1) {

                builder.append(String.format("|%3s%-6s", " ", object));
                builder1.append("=".repeat(10));
            }
        }
        return builder.toString();
    }


}
