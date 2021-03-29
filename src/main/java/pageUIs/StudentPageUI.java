package pageUIs;


public class StudentPageUI {

	public static final String SCHOOL_LIST = "//mat-option[@role='option']//span//strong";
	public static final String CALENDAR_BUTTON = "//mat-datepicker-toggle//button";
	
	public static final String CALENDAR_POPUP = "//mat-calendar";
	public static final String CURRENT_MONTHYEAR = "//mat-calendar//button[@aria-label='Choose month and year']//span";
	public static final String CALENDAR_LIST = "//td[contains(@class,'mat-calendar-body')]//div";
	public static final String DYNAMIC_BUTTON_ON_CALENDAR = "//mat-calendar//button[@aria-label='%s']";
	public static final String DYNAMIC_FIRST_CELL = "(//mat-row//mat-cell[contains(@class,'%s')])[1]";
	public static final String ADDED_MESSAGE = "//snack-bar-container//simple-snack-bar/span";
	
}
