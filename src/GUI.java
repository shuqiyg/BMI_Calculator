/********************************************** 
Workshop # 6
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-20
**********************************************/ 

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*; 
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class GUI extends Application{
	private final ObservableList<BMIStatus> data = FXCollections.observableArrayList(
			new BMIStatus("Below 18.5", "Underweight", false),
			new BMIStatus("18.5-24.9", "Normal", false),  
			new BMIStatus("25.0-29.9", "Overweight", false),
			new BMIStatus("30 and Above", "Obese", false)
	);
	//customized format for the calculation rounded into 2 decimal points
	private static final DecimalFormat twoDeci = new DecimalFormat("0.00");
	double hn, wn;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//all three panes will be sub panes of the main pane
		Calculator cal = new Calculator();
		//title
		Label appLabel = new Label("BMI CALCULATOR");	
		Label bmiResult = new Label();
		GridPane mainPane = new GridPane();
		GridPane scalePane = new GridPane();
		GridPane inputPane = new GridPane();
		GridPane calPane = new GridPane();
		VBox inputBox = new VBox();
		VBox calBox = new VBox();
		GridPane tablePane = new GridPane();
		GridPane sliderPane = new GridPane();
		Button calculate = new Button("Calculate");
		
		//mainPane.setGridLinesVisible(true);
		inputPane.setHgap(1);
		inputPane.setVgap(2);
		inputPane.setPadding(new Insets(15, 10, 10, 10));
		tablePane.setHgap(1);
		tablePane.setVgap(2);
		tablePane.setPadding(new Insets(15, 10, 10, 10));
		scalePane.setPadding(new Insets(15, 10, 10, 10));
		scalePane.setHgap(1);
		scalePane.setVgap(2);
		sliderPane.setPadding(new Insets(20.5, 10, 10, 10));
		sliderPane.setHgap(1);
		sliderPane.setVgap(8);

		inputPane.add(new Label("HEIGHT"), 0, 0);
		inputPane.add(new Label("WEIGHT"), 0, 20);
		TextField tfield1 = new TextField("0");
		TextField tfield2 = new TextField("0");
		inputPane.add(tfield1, 20, 0);
		inputPane.add(tfield2, 20, 20);
		calPane.add(calculate, 50, 10);
		calPane.add(new Label("BMI Result: "), 80, 10);
		calPane.add(bmiResult, 88, 10);
		calPane.add(new Label("Main Color: "), 60, 90);
		
		ToggleGroup group = new ToggleGroup();
		RadioButton cmkgButton = new RadioButton("cm/KG");
		RadioButton inlbButton = new RadioButton("in/Lb");
		cmkgButton.setToggleGroup(group);
		inlbButton.setToggleGroup(group);
		scalePane.add(cmkgButton, 10, 0);
		scalePane.add(inlbButton, 10, 20);
		
		//slider 1
		Slider slider1 = new Slider(0, 200, 1);
		slider1.setShowTickMarks(true);
		slider1.setShowTickLabels(true);
		slider1.setMajorTickUnit(10f);
		slider1.setBlockIncrement(10f);
		slider1.setPrefWidth(1000);
		slider1.setPadding(new Insets(2,2,2,2));
		Label heightNum = new Label();	
		Label s1 = new Label("Height");
		//bind to reflect any change if there's an update
		Bindings.bindBidirectional(tfield1.textProperty(), slider1.valueProperty(),NumberFormat.getNumberInstance());
		slider1.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)->{
			heightNum.setText("height:" + newValue);
			hn = (double) newValue;
		});

		sliderPane.add(slider1, 0, 5);
		
		//slider 2
		Slider slider2 = new Slider(0, 200, 1);
		slider2.setShowTickMarks(true);
		slider2.setShowTickLabels(true);
		slider2.setMajorTickUnit(10f);
		slider2.setBlockIncrement(10f);
		slider2.setPadding(new Insets(2,2,2,2));
		Label s2 = new Label("Weight");
		Label weightNum = new Label();
		Bindings.bindBidirectional(tfield2.textProperty(), slider2.valueProperty(),NumberFormat.getNumberInstance());
		slider2.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)->{
			weightNum.setText("weight:" + newValue);
			wn = (double) newValue;
		});
		
		sliderPane.add(slider2, 0, 10);
		sliderPane.add(s1, 0, 0);
		sliderPane.add(s2, 0, 6);

		//BMI Table
		TableView<BMIStatus> bmiTable = new TableView<BMIStatus>();
		//set up columns
		TableColumn<BMIStatus, String> firstCol = new TableColumn<>("BMI");
		firstCol.setMinWidth(100);
		firstCol.setCellValueFactory(new PropertyValueFactory<BMIStatus, String>("BMI"));
		TableColumn<BMIStatus, String> secondCol = new TableColumn<>("Weight Status");
		secondCol.setMinWidth(100);
		secondCol.setCellValueFactory(new PropertyValueFactory<BMIStatus, String>("wStatus"));
		//color each row
		bmiTable.setRowFactory(row -> new TableRow<BMIStatus>() {
			@Override
			public void updateItem(BMIStatus bs, boolean empty) {
				if(bs == null) {
					setStyle("");
				}else if (bs.getID() == 1) {
					setStyle("-fx-background-color: tomato;");
				}else if (bs.getID() == 2){
					setStyle("-fx-background-color: green;");
				}else if (bs.getID() == 3){
					setStyle("-fx-background-color: coral;");
				}else if (bs.getID() == 4){
					setStyle("-fx-background-color: steelblue;");
				}
			}
		});
		//handle calculate button when it's clicked
		calculate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				double result;
				if(cmkgButton.isSelected()) {
					result = cal.computeInCMKG(wn, hn);
				}else if(inlbButton.isSelected()) {
					result = cal.computeInINLB(wn, hn);		
				}else {
					result = 0;
				}
				bmiResult.setText((twoDeci.format(result)));
			}		
		});
//		calculate.setOnAction( e-> {
//				double result;
//				if(cmkgButton.isSelected()) {
//					result = cal.computeInCMKG(wn, hn);
//				}else if(inlbButton.isSelected()) {
//					result = cal.computeInINLB(wn, hn);		
//				}else {
//					result = 0;
//				}
//				bmiResult.setText((twoDeci.format(result)));
//		});		

		// 
		bmiTable.setItems(data);
		bmiTable.setMaxHeight(125);
		bmiTable.setMaxWidth(202);
		//set the columns
		bmiTable.getColumns().setAll(firstCol, secondCol);
		tablePane.add(bmiTable, 5, 5);
		inputBox.getChildren().addAll(inputPane);
		final HBox hbox = new HBox();
		hbox.setPrefSize(400, 230);
		
		//background color pick for scale pane
		ColorPicker scalePick = new ColorPicker();
		scalePane.add(scalePick,20,30);
		tablePane.setPadding(new Insets(20.5, 12.5, 13.5, 14.5));
		scalePick.setOnAction(e->{
			Color mycolor = scalePick.getValue();
			scalePane.setBackground(new Background(new BackgroundFill(mycolor,null,null)));
		});
		
		//color pick for input pane
		ColorPicker inputPick = new ColorPicker();
		inputPane.add(inputPick,20,30);
		inputPick.setOnAction(e->{
			Color mycolor = inputPick.getValue();
			hbox.setBackground(new Background(new BackgroundFill(mycolor,null,null)));
		});
		
		//color pick for main pane
		ColorPicker mainPick = new ColorPicker();
		//mainPane.add(mainPick,20,31);
		mainPick.setOnAction(e->{
			Color mycolor = mainPick.getValue();
			mainPane.setBackground(new Background(new BackgroundFill(mycolor,null,null)));
		});

		calPane.add(mainPick, 80, 90);
		calBox.getChildren().add(calPane);
		hbox.setPadding(new Insets(20,50,15,20));
		hbox.getChildren().addAll(inputBox,bmiTable, calBox);
		mainPane.setPadding(new Insets(15,30,10,15));
		//putting everything together
		mainPane.add(appLabel, 5, 2);
		mainPane.add(scalePane, 5, 8);
		mainPane.add(tablePane, 8, 13);
		mainPane.add(hbox, 5, 15);
		mainPane.add(sliderPane, 5, 60);
		
		Scene scene = new Scene(mainPane);
	    primaryStage.setTitle("BMI Calculator");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
