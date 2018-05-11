# Javafx_spring
Extends javafx application class to work with spring.

## Main class

    @SpringBootApplication  
	public class DemoApplication extends SpringJavaFxApplication {  
  
	  public static void main(String[] args) {Application.launch(args);}  
	   
	  @Override  
	  protected Class getApplicationClass() {return getClass();}  
	  
	  @Override  
	  protected @UnknownKeyFor @NonNull @Initialized ResourceBundle getResourceBundle() {return ResourceBundle.getBundle("bundle");}  
	 
	  @Override  
	  protected @UnknownKeyFor @NonNull @Initialized IViewFxml getInitialScene() {return view.MAIN;}  
	}

## Controller
The controller connected to FXML file in the resources. The stage manager allows easy stage transitions.

    @Component  
	public class testConstroler implements FxmlController {
		private final StageManager stageManager;
	}



## IViewFxml
Defines FXML views in the resources folder.

    public enum view implements IViewFxml {  
	   MAIN("title", "/test.fxml");  
	   @Getter
	   private final String title;  
	   @Getter
	   private final String filePath;
	}