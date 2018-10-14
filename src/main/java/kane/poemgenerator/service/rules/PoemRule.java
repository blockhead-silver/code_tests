package kane.poemgenerator.service.rules;

import java.util.List;

public class PoemRule implements Rule{

	private String ruleName;
	private String ruleValue;
	private List<String> basicArgs;
	private List<String> keyArgs;
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public List<String> getBasicArgs() {
		return basicArgs;
	}

	public void setBasicArgs(List<String> basicArgs) {
		this.basicArgs = basicArgs;
	}

	public List<String> getKeyArgs() {
		return keyArgs;
	}

	public void setKeyArgs(List<String> keyArgs) {
		this.keyArgs = keyArgs;
	}
}
