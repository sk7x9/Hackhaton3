package umkc.edu.challange2;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;
@IBMDataObjectSpecialization("JobDetails")
public class JobDetails extends IBMDataObject{
	public static final String CLASS_NAME = "JobDetails";
	private static final String NAME = "name";
	private static final String Title = "title";
	private static final String Country = "CCode";
	
	/**
	 * Gets the name of the Item.
	 * @return String itemName
	 */
	public String getName() {
		return (String) getObject(NAME);
	}
	public String getTitle() {
		return (String) getObject(Title);
	}
	public String getCountry() {
		return (String) getObject(Country);
	}
	/**
	 * Sets the name of a list item, as well as calls setCreationTime().
	 * @param String itemName
	 */
	public void setName(String itemName) {
		setObject(NAME, (itemName != null) ? itemName : "");
	}
	public void setTitle(String titlen) {
		setObject(Title, (titlen != null) ? titlen : "");
	}
	public void setCountry(String cname) {
		setObject(Country, (cname != null) ? cname : "");
	}
	/**
	 * When calling toString() for an item, we'd really only want the name.
	 * @return String theItemName
	 */
	public String toString() {
		String theItemName = "";
		theItemName = getName();
		return theItemName;
	}

}
