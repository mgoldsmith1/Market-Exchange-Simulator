package osdi.zindex.chart;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import osdi.clientui.ClientFrame;
import osdi.zindex.chart.internal.chartpart.Chart;

/**
 * A convenience class used to display a Chart in a barebones Swing application
 *
 * @author timmolter
 */
public class SwingWrapper<T extends Chart> {

  private final List<XChartPanel<T>> chartPanels = new ArrayList<XChartPanel<T>>();
  private String windowTitle = "Chart";
  private List<T> charts = new ArrayList<T>();
  private int numRows;
  private int numColumns;
  private final JFrame frame = null;
  private ClientFrame a = null;

  /**
   * Constructor
   *
   * @param chart
   */
  public SwingWrapper(T chart) {

    this.charts.add(chart);
  }

  /**
   * Constructor - The number of rows and columns will be calculated automatically Constructor
   *
   * @param charts
   */
  public SwingWrapper(List<T> charts) {

    this.charts = charts;

    this.numRows = (int) (Math.sqrt(charts.size()) + .5);
    this.numColumns = (int) ((double) charts.size() / this.numRows + 1);
  }

  /**
   * Constructor
   *
   * @param charts
   * @param numRows - the number of rows
   * @param numColumns - the number of columns
   */
  public SwingWrapper(List<T> charts, int numRows, int numColumns) {

    this.charts = charts;
    this.numRows = numRows;
    this.numColumns = numColumns;
  }

  /**
   * Display the chart in a Swing JFrame
   *
   * @param windowTitle the title of the window
   */
  public JFrame displayChart(String windowTitle) {

    this.windowTitle = windowTitle;

    return displayChart();
  }

  /** Display the chart in a Swing JFrame */
  public JFrame displayChart() {

    // Create and set up the window.
    final JFrame frame = new JFrame(windowTitle);

    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    try {
      javax.swing.SwingUtilities.invokeAndWait(
          new Runnable() {

            @Override
            public void run() {

              frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //EXIT_ON_CLOSE
              XChartPanel<T> chartPanel = new XChartPanel<T>(charts.get(0));
              chartPanels.add(chartPanel);
              frame.add(chartPanel);

              // Display the window.
              frame.pack();
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
            }
          });
      frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				windowEvent.getSource().equals(false);
				//osdi.loyola.index.LoyolaRamblerIndex a;
				//a.
				//a.stopChartWindowThread();
				//this.getClass().st
				
				//System.exit(0);
			}
		});
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return frame;
  }

  /**
   * Display the charts in a Swing JFrame
   *
   * @param windowTitle the title of the window
   * @return the JFrame
   */
  public JFrame displayChartMatrix(String windowTitle) {

    this.windowTitle = windowTitle;

    return displayChartMatrix();
  }

  /** Display the chart in a Swing JFrame */
  public JFrame displayChartMatrix() {

    // Create and set up the window.
    final JFrame frame = new JFrame(windowTitle);

    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(
        new Runnable() {

          @Override
          public void run() {

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new GridLayout(numRows, numColumns));

            for (T chart : charts) {
              if (chart != null) {
                XChartPanel<T> chartPanel = new XChartPanel<T>(chart);
                chartPanels.add(chartPanel);
                frame.add(chartPanel);
              } else {
                JPanel chartPanel = new JPanel();
                frame.getContentPane().add(chartPanel);
              }
            }

            // Display the window.
            frame.pack();
            frame.setVisible(true);
          }
        });

    return frame;
  }

  /**
   * Get the default XChartPanel. This is the only one for single panel chart displays and the first
   * panel in matrix chart displays
   *
   * @return the XChartPanel
   */
  public XChartPanel<T> getXChartPanel() {

    return getXChartPanel(0);
  }

  /**
   * Repaint the default XChartPanel. This is the only one for single panel chart displays and the
   * first panel in matrix chart displays
   */
  public void repaintChart() {

    repaintChart(0);
  }

  /**
   * Get the XChartPanel given the provided index.
   *
   * @param index
   * @return the XChartPanel
   */
  public XChartPanel<T> getXChartPanel(int index) {

    return chartPanels.get(index);
  }

  /**
   * Repaint the XChartPanel given the provided index.
   *
   * @param index
   */
  public void repaintChart(int index) {

    chartPanels.get(index).revalidate();
    chartPanels.get(index).repaint();
  }
}
