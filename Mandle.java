import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.lang.Math;

public class Mandel extends Application {
    Scene scene;
    Pane root;
    final double POINTSIZE = 0.3; //Domain of depends on graphPrecision
    final double windowSize = 500; //Adjust the window size
    final double scale = 1.0; //Adjust scale for graph: scale = a ---> x : [-a,a], y : [-a,a]
    final double graphPrecision = 0.3; //Precision of points. 1/graphPrecision = # of points on unit line
    final int CONVERGE_CONST = 2; //
    final int ITERATION_CONST = 256; //

    @Override
    public void start(Stage stage) {
        // Double a = 499.0; // X
        // Double b = 250.0; // Y
        // Double windowX = (a - windowSize / 2.0) / (windowSize / (2.0 * scale)); // Adjusted conversion for X
        // Double windowY = (b - windowSize / 2.0) / (windowSize / (2.0 * scale)); // Adjusted conversion for X
        // System.out.println(Boolean.toString(inSet(windowX,windowY)) + Double.toString(windowX) + Double.toString(windowY));
        // if (true) throw new NullPointerException();
        this.root = new Pane();
        this.scene = new Scene(root, windowSize, windowSize);

        stage.setTitle("Mandle Brot Set JavaFx");
        stage.setScene(scene);
        stage.show();

        createAxis();
        createMandleSet();
    }
    /**
     * 
     * Determines if a point lies in the Mandle Brot Set
     * Iterates through recursion:
     * Z_0 = 0
     * Z_n+1 = (Z_n)^2 + C
     * Using 0 orbit
     * 
     * @param a real for C
     * @param b imaginary for C
     * @return  True if point is in set
     */
    public boolean inSet(double real, double imaginary) {
        //Z_0 = 0
        double Za = 0.0;
        double Zb = 0.0;

        for (int i = 0; i < ITERATION_CONST; i++) {
            double Za2 = Za * Za - Zb * Zb;
            double Zb2 = 2*Za*Zb;
            
            if (Math.sqrt(Za2 + Zb2) > CONVERGE_CONST) {
                return false;
            }

            Za = Za2 + real;
            Zb = Zb2 + imaginary;

        }

        return true;
    }
    /**
     * Converts a coordinate from the window to a cartesian coordinate [0, windowSize] -> [-scale, scale]
     * 
     * @param z Type: double - coordinate on window with domain x,y : [0,windowSize]
     * @return
     */
    public double convertWindowToGraph(double z){
        return (z - windowSize / 2.0) / (windowSize / (2.0 * scale));
    }
    /**
     * Converts a coordinate from the 
     * 
     * @param z Type: double - coordinate on 
     * @return
     */
    public double convertGraphToWindow(double z){
        return (-z * (windowSize / (2.0 * scale))) + (windowSize / 2.0);
    }
    /**
     * Adds a point to the graph.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void addPoint(double x, double y){
        //Normalise Points so they are centered
        Rectangle r = new Rectangle(x - (POINTSIZE/2.0),y - (POINTSIZE/2.0),POINTSIZE,POINTSIZE);
        this.root.getChildren().add(r);
    }
    /**
     * Creates the Mandel Brot Set visual 
     */
    public void createMandleSet() {
        double convertedX;
        double convertedY;
        
        for (double x = 0; x < windowSize; x += graphPrecision) {
            convertedX = convertWindowToGraph(x);
            for (double y = 0; y < windowSize; y += graphPrecision) {
                convertedY = convertWindowToGraph(y);
                if (inSet(convertedX, convertedY)) {
                    addPoint(x, y);
                }
            }
        }
    }
    /**
     * Creates the grid axis
     */
    public void createAxis(){
        Line XAxis = new Line(0,windowSize/2, windowSize,windowSize/2);
        XAxis.setStroke(Color.BLUE);

        Line YAxis = new Line(windowSize/2,0,windowSize/2,windowSize);
        YAxis.setStroke(Color.RED);
 
        Line YisP1 = new Line(convertGraphToWindow(0) - 4,convertGraphToWindow(1),convertGraphToWindow(0) + 4,convertGraphToWindow(1));
        YisP1.setStroke(Color.RED);
        Line YisN1 = new Line(convertGraphToWindow(0) - 4, convertGraphToWindow(-1),convertGraphToWindow(0) + 4, convertGraphToWindow(-1));
        YisN1.setStroke(Color.RED);
        Line XisP1 = new Line(convertGraphToWindow(1),convertGraphToWindow(0) - 4,convertGraphToWindow(1), convertGraphToWindow(0) + 4);
        XisP1.setStroke(Color.BLUE);
        Line XisN1 = new Line(convertGraphToWindow(-1),convertGraphToWindow(0) + 4,convertGraphToWindow(-1), convertGraphToWindow(0) - 4);
        XisN1.setStroke(Color.BLUE);
        this.root.getChildren().addAll(XAxis, YAxis, YisP1, YisN1, XisP1, XisN1);
    }

    public static void main(String[] args) {
        launch();
    }
}