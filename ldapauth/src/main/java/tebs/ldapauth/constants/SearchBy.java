package tebs.ldapauth.constants;

public enum SearchBy {
	USERID("USERID"),
	EMAIL("EMAIL"),
	NAME("NAME"),
	MOBILE("MOBILE"),
	DEPARTMENT("DEPARTMENT"),
	EMPLOYEENUMBER("EMPLOYEENUMBER");
	
	private final String searchBy;       

    private SearchBy(String s) {
    	searchBy = s;
    }

    public String toString() {
       return this.searchBy;
    }
}
