package lt.viko.rkomaristova.eventmanagement.enums;

public enum Cities {

	VILNIUS ("Vilnius"), KAUNAS ("Kaunas"), KLAIPEDA ("Klaipėda"), SIAULIAI("Šiauliai");
	
	private String value;
	   public String getValue() {
	       return this.value;
	   }

	   Cities(String value) {
	           this.value = value;
	   }
}
