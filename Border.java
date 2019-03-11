
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Border extends Application{
	public void start(Stage primaryStage)throws Exception{
		Rectangle rect1 = new Rectangle(0,0,684,19);
		rect1.setFill(Color.CHOCOLATE);
		
		Rectangle rect2 = new Rectangle(0,0,19,494);
		rect2.setFill(Color.CHOCOLATE);
		
		Rectangle rect3 = new Rectangle(665,0,19,494);
		rect3.setFill(Color.CHOCOLATE);
		
		Rectangle rect4 = new Rectangle(0,475,684,19);
		rect4.setFill(Color.CHOCOLATE);
		
		
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 684, 494);
		root.getChildren().addAll(rect1, rect2, rect3, rect4);
		primaryStage.setTitle("Border");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
//900w,650h