import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Graph {

    public double f(double x) {
        return -1.8 * Math.pow(x, 3) - 2.94 * Math.pow(x, 2) + 10.37 * x + 5.38;
    }

    public void build() {
        String name = "-1.8x^3 -2.94x^2 + 10.37x + 5.38";

        XYSeries series = new XYSeries(name);

        for (double i = -4; i < 3; i += 0.2) {
            series.add(i, f(i));
        }

        XYDataset dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart("", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);

        JFrame frame = new JFrame("График");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(1300, 600);
        frame.show();
    }
}