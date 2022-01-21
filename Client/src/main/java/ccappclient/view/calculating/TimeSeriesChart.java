package ccappclient.view.calculating;

import static ccappclient.Client.receiveResultSet;
import static ccappclient.Client.sendMessage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockFrame;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesChart
        extends ApplicationFrame {

    private static ArrayList<String[]> calculatingSets;
    private static ArrayList<String[]> companySets;
    private static String productID;
    private static String productName;
    private static String companyName;

    public TimeSeriesChart(String string, String productID) {
        super(string);
        this.productID = productID;
        this.calculatingSets = getResultsFromCalculating();
        this.companySets = getResultFromCompany();
        this.productName = getNameOfProduct();
        this.companyName = companySets.get(companySets.size()-1)[1];
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        
        
        this.setContentPane((Container) chartPanel);
        this.pack();
        RefineryUtilities.centerFrameOnScreen((Window) ((Object) this));
    }

    private JFreeChart createChart(XYDataset xYDataset) {
        JFreeChart jFreeChart = ChartFactory.createTimeSeriesChart(
                (String) companyName + " : " + productName, (String) null,
                (String) "Бел. рубли", (XYDataset) xYDataset,
                (boolean) true, (boolean) true, (boolean) false
        );
        
        TimeSeriesChart tmsrch = this;
        jFreeChart.addChangeListener(new ChartChangeListener() {
            @Override
            public void chartChanged(ChartChangeEvent cce) {
                tmsrch.setVisible(false);
            }
        });
        
        XYPlot xYPlot = (XYPlot) jFreeChart.getPlot();
        xYPlot.setDomainPannable(true);
        xYPlot.setRangePannable(false);
        xYPlot.setDomainCrosshairVisible(true);
        xYPlot.setRangeCrosshairVisible(true);
        xYPlot.getDomainAxis().setLowerMargin(0.0);
        jFreeChart.getLegend().setFrame((BlockFrame) BlockBorder.NONE);
        jFreeChart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);
        XYItemRenderer xYItemRenderer = xYPlot.getRenderer();
        if (xYItemRenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xYLineAndShapeRenderer = (XYLineAndShapeRenderer) xYItemRenderer;
            xYLineAndShapeRenderer.setBaseShapesVisible(false);
            xYLineAndShapeRenderer.setDrawSeriesLineAsPath(true);
            xYLineAndShapeRenderer.setAutoPopulateSeriesStroke(false);
            xYLineAndShapeRenderer.setBaseStroke((Stroke) new BasicStroke(3.0f, 1, 2), false);
            xYLineAndShapeRenderer.setSeriesPaint(0, (Paint) Color.RED);
        }
        return jFreeChart;
    }
    
    private XYDataset createDataset() {
        TimeSeries timeSeries = new TimeSeries((Comparable) ((Object) "Изменение цены"));
        for (String[] key : calculatingSets) {
            timeSeries.addOrUpdate((RegularTimePeriod) new Month(Integer.parseInt(key[3]), 2021), Double.parseDouble(key[2]));
        }

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(timeSeries);
        return timeSeriesCollection;
    }

    public JPanel createDemoPanel() {
        JFreeChart jFreeChart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        return chartPanel;
    }

    public static ArrayList<String[]> getResultsFromCalculating() {
        ArrayList<String[]> rs = null;
        sendMessage("_get_result_from_calculating_for_id");
        sendMessage(productID);
        rs = receiveResultSet();
        return rs;
    }

    public static ArrayList<String[]> getResultFromCompany() {
        ArrayList<String[]> rs = null;
        sendMessage("_get_result_from_company");
        rs = receiveResultSet();
        return rs;
    }

    public static String getNameOfProduct() {
        ArrayList<String[]> rs = null;
        sendMessage("_get_name_of_product");
        sendMessage(productID);
        rs = receiveResultSet();

        return rs.get(0)[0];
    }
}
