package lt.viko.rkomaristova.eventmanagement.enums;

public enum EventCategories {

	CONCERT ("Koncertas"), EDUCATIONAL ("Mokymai"), PUBLICHOLIDAY ("Šventė"), ART("Paroda");
	
	private String value;
	
	   public String getValue() {
	       return this.value;
	   }
	   EventCategories(String value) {
	           this.value = value;
	   }
}
