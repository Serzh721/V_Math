import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Graph {

    public double linear(double a, double b, double x) {
        return a * x + b;
    }

    public double pow(double a, double b, double x) {
        return a * Math.pow(x, b);
    }

    public double exp(double a, double b, double x) {
        return a * Math.exp(b * x);
    }

    public double log(double a, double b, double x) {
        return a * Math.log(x) + b;
    }

    public double quadratic(double a, double b, double c, double x) {
        return a * Math.pow(x, 2) + b * x + c;
    }

    public void build(double[] AB, double[] y) {
        double a1 = AB[0];
        double b1 = AB[1];
        double a2 = AB[2];
        double b2 = AB[3];
        double a3 = AB[4];
        double b3 = AB[5];
        double a4 = AB[6];
        double b4 = AB[7];
        double a5 = AB[8];
        double b5 = AB[9];
        double c5 = AB[10];

        String linear = "f = a*x + b";
        String pow = "f = a*x^b";
        String exp = "f = a*e^(bx)";
        String log = "f = a*lnx + b";
        String quadratic = "f = a*x^2 + b*x + c";
        String original = "Original";

        XYSeries LinSeries = new XYSeries(linear);
        XYSeries PowSeries = new XYSeries(pow);
        XYSeries ExpSeries = new XYSeries(exp);
        XYSeries LogSeries = new XYSeries(log);
        XYSeries QSeries = new XYSeries(quadratic);
        XYSeries OrSeries = new XYSeries(original);

        for (double i = 1; i < 4; i += 0.01) {
            LinSeries.add(i, linear(a1, b1, i));
            PowSeries.add(i, pow(a2, b2, i));
            ExpSeries.add(i, exp(a3, b3, i));
            LogSeries.add(i, log(a4, b4, i));
            QSeries.add(i, quadratic(a5, b5, c5, i));
        }
        int iter = 0;
        for (double i = 1; i <= 3.52; i += 0.21) {
            OrSeries.add(i, y[iter]);
            iter++;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(LinSeries);
        dataset.addSeries(PowSeries);
        dataset.addSeries(ExpSeries);
        dataset.addSeries(LogSeries);
        dataset.addSeries(QSeries);
        dataset.addSeries(OrSeries);

        JFreeChart chart = ChartFactory.createXYLineChart("", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);

        JFrame frame = new JFrame("График");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(1300, 600);
        frame.show();
    }
}