package kane.poemgenerator;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kane.poemgenerator.model.Rules;
import kane.poemgenerator.service.poem.PoemBuilder;
import kane.poemgenerator.service.poem.PoemBuilderImpl;
import kane.poemgenerator.service.rules.PoemRuleParserImpl;
import kane.poemgenerator.service.rules.RuleParser;

/**
 * poem generation application
 *
 */
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static final String RULE_FILE_NAME = "poem_generation_rules";

	public static void main(String[] args) {
		BasicConfigurator.configure();
		RuleParser ruleParser = new PoemRuleParserImpl();
		try {
			Rules rules = ruleParser.parseRules(RULE_FILE_NAME);
			PoemBuilder poemBuilder = new PoemBuilderImpl();
			poemBuilder.buildPoem(rules);
		} catch (Exception e) {
			logger.error("Error while processing poem generation - ", e);
		}
	}
}
