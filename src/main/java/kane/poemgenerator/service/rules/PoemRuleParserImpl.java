package kane.poemgenerator.service.rules;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kane.poemgenerator.model.Rules;

public class PoemRuleParserImpl implements RuleParser{
	
	private static final Logger logger = LoggerFactory.getLogger(PoemRuleParserImpl.class);

	public Rules parseRules(String fileName) {
		try {
			Rules rules = new Rules();
			Stream<String> stream = Files.lines(Paths.get(fileName));
			stream.forEach(line -> processRules(line, rules));
			return rules;
		} catch (Exception e) {
			logger.error("Error while parsing rules for poem generation - ", e);
		}
		return null;
	}
	
	private void processRules(String line, Rules rules) {
		PoemRule poemRule = new PoemRule();
		try {
			String ruleStr[] = StringUtils.split(line, ":");
			poemRule.setRuleName(ruleStr[0]);
			poemRule.setRuleValue(ruleStr[1]);
			String[] ruleArgsArr = ruleStr[1].trim().split(" ");
			ArrayList<String> basicArgs = new ArrayList<String>();
			ArrayList<String> keyArgs = new ArrayList<String>();
			if (ruleArgsArr.length > 0) {
				processArguments(ruleArgsArr, basicArgs, keyArgs);
			}
			poemRule.setBasicArgs(basicArgs);
			poemRule.setKeyArgs(keyArgs);
			rules.getRuleLines().put(ruleStr[0], poemRule);
		} catch (Exception e) {
			logger.error("Error while parsing rules for poem generation - ", e);
		}
	}
	
	private void processArguments(String[] ruleArgsArr, List<String> basicArgs, List<String> keyArgs) throws Exception {
		try {
			for (String args : ruleArgsArr) {
				String[] tempRuleArgs = StringUtils.split(args, "\\|");
				for (String tempArg : tempRuleArgs) {
					if (tempArg.matches("<[A-Z]+>") || tempArg.matches(("\\$[A-Z]+"))) {
						keyArgs.add(tempArg);
					} else {
						basicArgs.add(tempArg);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
