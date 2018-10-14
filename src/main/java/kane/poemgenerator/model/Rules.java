package kane.poemgenerator.model;

import java.util.LinkedHashMap;
import java.util.Map;

import kane.poemgenerator.service.rules.Rule;

public class Rules {

	private Map<String, Rule> ruleLines;

	public Map<String, Rule> getRuleLines() {
		if (this.ruleLines != null) {
			return ruleLines;
		}
		return ruleLines = new LinkedHashMap<String, Rule>();
	}

	public void setRuleLines(Map<String, Rule> ruleLines) {
		this.ruleLines = ruleLines;
	}
}
