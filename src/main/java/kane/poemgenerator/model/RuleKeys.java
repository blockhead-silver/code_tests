package kane.poemgenerator.model;

public enum RuleKeys {
	
	$LINEBREAK("\n"),
	$END("\r");

    private String value;

    private RuleKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
