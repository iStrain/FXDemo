
/*
 * JAVAFX DEMO BY DUNCAN BAXTER s3737140
 * This Java program illustrates the use of JavaFX to do the following:
 * -  Create an Image from a file
 * -  Note: Put the Image file in the same directory as this one (usually Project/src)
 * -  Use a BorderPane to partition our application window into five regions:
 *    *  center (where the action is - we can also use the entire window)
 *    *  top (game logo, scores, other player statistics)
 *    *  right (command buttons)
 *    *  bottom (mapping for function keys)
 *    *  left (more command buttons)
 * -  Use CSS commands to specify formatting styles for our BorderPane
 * -  Create a Canvas in the Center region on which we can draw graphics or images
 * -  Draw the Image from our file on the Canvas in the Center region
 * -  Create a smaller Canvas in the Top region and draw WordArt-style text
 * -  Type word-wrapped and justified text in the Bottom region
 * -  Use the Nimbus vector graphics "look and feel" for our application (if present)
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * High-level Class for our Demo
 */
public class FXDemo extends Application {

    /*
     * JavaFX Application thread automatically calls start() method. The parameter
     * Stage stage is our top-level window, then Scene scene, BorderPane root, and
     * finally other Nodes (eg. Canvas canvas, Canvas art, Text wrap).
     * 
     * start() may throw an IOException when trying to load the Image from the file.
     * There's no point in continuing if we can't load the Image, so the exception
     * can go through to the 'keeper.
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException {
	// Create an Image from our file
	Image image = new Image("file:src/Movieposterposv.jpg");
	// Create a Canvas to draw the Image on
	Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
	// Get the graphics context of the Canvas
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// Draw the Image on the Canvas (could use an ImageView Node instead)
	gc.drawImage(image, 0, 0);
	// Create a BorderPane with the Canvas in the Center region
	BorderPane root = new BorderPane(canvas);
	// Use JavaFX CSS to set the style properties of the BorderPane
	root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
		+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

	/*
	 * Draw some WordArt-style text in the Top region (traditional method).
	 * Alternatively, we could use setStyle() and JavaFX CSS to set the style
	 * properties of the Canvas.
	 */
	Canvas art = new Canvas(image.getWidth(), 48.0);
	GraphicsContext gcArt = art.getGraphicsContext2D();
	gcArt.setTextAlign(TextAlignment.CENTER);
	gcArt.setTextBaseline(VPos.TOP);
	gcArt.setFill(Color.GREEN);
	gcArt.setStroke(Color.WHITE);
	gcArt.setLineWidth(2);
	gcArt.setFont(Font.font("Stencil", FontWeight.BOLD, 48));
	gcArt.fillText("Silicon", image.getWidth() / 2, 0.0);
	gcArt.strokeText("Silicon", image.getWidth() / 2, 0.0);
	root.setTop(art);

	/*
	 * Type some word-wrapped and justified text in the Bottom region (traditional
	 * method). Alternatively, we could use setStyle() and JavaFX CSS to set the
	 * style properties of the Text.
	 */
	Text wrap = new Text();
	wrap.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
	wrap.setWrappingWidth(image.getWidth());
	wrap.setTextAlignment(TextAlignment.JUSTIFY);
	wrap.setText("Smart people wear glasses.  Who knew?");
	wrap.setFill(Color.GREEN);
	wrap.setStroke(Color.WHITE);
	root.setBottom(wrap);
	/*
	 * We can again set the line width for this text using: wrap.setStrokeWidth(2);
	 * However, wider lines don't work as well with smaller fonts.
	 */
	// Signal that we need to layout the BorderPane (ie. Nodes are done)
	root.needsLayoutProperty();
	// Set the Style for the Stage (parameter to start method)
	stage.initStyle(StageStyle.DECORATED);
	// Set the title of the Stage
	stage.setTitle("JavaFX Demo");
	// Create a Scene based on the BorderPane
	Scene scene = new Scene(root, Color.BLACK);
	// Add the Scene to the Stage and resize the Stage
	stage.setScene(scene);
	/*
	 * We can use stage.setFullScreen(true); to enter FullScreen mode. The ESC
	 * (escape) key will get us back to Windowed mode (we can change this key).
	 */
	// Show the Stage
	stage.show();
    }

    /*
     * The usual "main" method - code is only executed on platforms that lack JavaFX support.
     */
    public static void main(String[] args) {
	Application.launch(args);
    }
}
