package src;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Mandle extends Application {
    Scene scene;
    Pane root;
    int POINTSIZE;
    final int windowSize = 500;
    @Override
    public void start(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(root, windowSize, windowSize);

        stage.setTitle("Mandle Brot Set JavaFx");
        stage.setScene(scene);
        stage.show();

        createAxis();
    }
    /**
     * 
     * Iterates through recursion to determine if point lies in Mandle Brot set
     * Z_n+1 = (Z_n)^2 + C
     * Using 0 orbit
     * 
     * @param a constant for real axis
     * @param b constant for imaginary axis
     * @return  True if point is in set
     */
    private Boolean inSet(int a, int b){
        // Z^2 = (a+bi)^2 = a^2 - b^2 +2*a*bi
        int real = a*a - b*b;
        int imaginary = 2*a*b;

        return false;
    }
    
    /**
     * Adds a point to the graph.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    private void addPoint(int x, int y){
        Rectangle r = new Rectangle(x,y,POINTSIZE,POINTSIZE);
        this.root.getChildren().add(r);
    }
    /**
     * Creates the grid axis
     */
    private void createAxis(){
        Line XAxis = new Line(0,windowSize/2,windowSize,windowSize/2);
        XAxis.setStroke(Color.BLUE);

        Line YAxis = new Line(windowSize/2,0,windowSize/2,windowSize);
        YAxis.setStroke(Color.RED);

        this.root.getChildren().addAll(XAxis, YAxis);
    }

    public static void main(String[] args) {
        launch();
    }
}