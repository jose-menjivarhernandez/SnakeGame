import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Maze1 extends Application{
	public void start(Stage primaryStage)throws Exception{
		Rectangle rect1 = new Rectangle(0,0,684,19);
		rect1.setFill(Color.CHOCOLATE);
		
		Rectangle rect2 = new Rectangle(0,0,19,494);
		rect2.setFill(Color.CHOCOLATE);
		
		Rectangle rect3 = new Rectangle(665,0,19,494);
		rect3.setFill(Color.CHOCOLATE);
		
		Rectangle rect4 = new Rectangle(0,475,684,19);
		rect4.setFill(Color.CHOCOLATE);
		
		//Barriers
		Rectangle r1 = new Rectangle(114,114,19,247);
		r1.setFill(Color.CHOCOLATE);
		
		Rectangle r2 = new Rectangle(114,361,114,19);
		r2.setFill(Color.CHOCOLATE);
		
		Rectangle r3 = new Rectangle(228,361,19,114);
		r3.setFill(Color.CHOCOLATE);
		
		Rectangle r4 = new Rectangle(247,133,19,133);
		r4.setFill(Color.CHOCOLATE);
		
		Rectangle r5 = new Rectangle(266,133,304,19);
		r5.setFill(Color.CHOCOLATE);
		
		Rectangle r6 = new Rectangle(551,19,19,114);
		r6.setFill(Color.CHOCOLATE);
		
		Rectangle r7 = new Rectangle(342,228,19,133);
		r7.setFill(Color.CHOCOLATE);
		
		Rectangle r8 = new Rectangle(342,342,209,19);
		r8.setFill(Color.CHOCOLATE);
		
		Rectangle r9 = new Rectangle(532,228,19,247);
		r9.setFill(Color.CHOCOLATE);
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 684, 494);
		root.getChildren().addAll(rect1, rect2, rect3, rect4, r1, r2, r3, r4, r5, r6, r7, r8, r9);
		primaryStage.setTitle("Maze1");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}