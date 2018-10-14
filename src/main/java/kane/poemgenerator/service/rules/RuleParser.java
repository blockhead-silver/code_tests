package kane.poemgenerator.service.rules;

import kane.poemgenerator.model.Rules;

public interface RuleParser {
	
	public Rules parseRules(String fileName);

}
