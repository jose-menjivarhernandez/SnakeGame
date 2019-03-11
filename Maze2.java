import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Maze2 extends Application{
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
		Rectangle r1 = new Rectangle(76,19,19,209);
		r1.setFill(Color.CHOCOLATE);
		
		Rectangle r2 = new Rectangle(95,114,114,19);
		r2.setFill(Color.CHOCOLATE);
		
		Rectangle r3 = new Rectangle(76,304,152,19);
		r3.setFill(Color.CHOCOLATE);
		
		Rectangle r4 = new Rectangle(133,323,19,171);
		r4.setFill(Color.CHOCOLATE);
		
		Rectangle r5 = new Rectangle(418,19,19,209);
		r5.setFill(Color.CHOCOLATE);
		
		Rectangle r6 = new Rectangle(304,114,19,190);
		r6.setFill(Color.CHOCOLATE);
		
		Rectangle r7 = new Rectangle(304,285,228,19);
		r7.setFill(Color.CHOCOLATE);
		
		Rectangle r8 = new Rectangle(532,190,19,114);
		r8.setFill(Color.CHOCOLATE);
		
		Rectangle r9 = new Rectangle(532,190,152,19);
		r9.setFill(Color.CHOCOLATE);
		
		Rectangle r10 = new Rectangle(437,304,19,114);
		r10.setFill(Color.CHOCOLATE);
		
		Rectangle r11 = new Rectangle(342,399,228,19);
		r11.setFill(Color.CHOCOLATE);
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 684, 494);
		root.getChildren().addAll(rect1, rect2, rect3, rect4, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
		primaryStage.setTitle("Maze2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}