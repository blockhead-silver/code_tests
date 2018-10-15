package kane.poemgenerator.service.poem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kane.poemgenerator.model.RuleKeys;
import kane.poemgenerator.model.Rules;
import kane.poemgenerator.service.rules.PoemRule;
import kane.poemgenerator.service.rules.Rule;

public class PoemBuilderImpl implements PoemBuilder {

	private static final Logger logger = LoggerFactory.getLogger(PoemBuilderImpl.class);

	private static final String KEY_ARG_TYPE_PATTERN_ONE = "<[A-Z]+>";
	private static final String KEY_ARG_TYPE_PATTERN_TWO = "\\$[A-Z]+";
	private static FileWriter fr = null;
	private static BufferedWriter br = null;

	public void buildPoem(Rules rules) {
		try {
			StringBuilder sb = new StringBuilder();
			LinkedHashMap<String, Rule> poemRules = (LinkedHashMap<String, Rule>) rules.getRuleLines();
			if (!poemRules.isEmpty()) {
				PoemRule mainRule = (PoemRule) poemRules.get("POEM");
				ArrayList<String> mainRuleArgs = (ArrayList<String>) mainRule.getKeyArgs();
				for (String mainRuleArg : mainRuleArgs) {
					generatePoem((PoemRule) poemRules.get(mainRuleArg.substring(1, mainRuleArg.length() - 1)), poemRules, sb);
				}
			}
			System.out.println(sb.toString());
			writeOutput(sb.toString());
		} catch (Exception e) {
			logger.error("Error while generating poem - ", e);
		}
	}

	private void generatePoem(PoemRule poemRule, LinkedHashMap<String, Rule> poemRules, StringBuilder sb)
			throws Exception {
		try {
			if (!poemRule.getBasicArgs().isEmpty()) {
				sb.append(poemRule.getBasicArgs().get(randomIndex(0, poemRule.getBasicArgs().size())));
				sb.append(" ");
			}
			String keyArg = poemRule.getKeyArgs().get(randomIndex(0, poemRule.getKeyArgs().size()));
			if (keyArg.matches(KEY_ARG_TYPE_PATTERN_ONE)) {
				PoemRule subRule = (PoemRule) poemRules.get(keyArg.substring(1, keyArg.length() - 1));
				generatePoem(subRule, poemRules, sb);
			} else if (keyArg.matches(KEY_ARG_TYPE_PATTERN_TWO)) {
				RuleKeys ruleKey = RuleKeys.valueOf(keyArg);
				sb.append(ruleKey.getValue());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private int randomIndex(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	private void writeOutput(String output) {
		File outputFile = new File("results.txt");
		try {
			if (outputFile.exists()) {
				outputFile.delete();
				outputFile.createNewFile();
			}
			fr = new FileWriter(outputFile, true);
			br = new BufferedWriter(fr);
			br.write(output);
			br.close();
			fr.close();
		} catch (Exception e) {
			logger.error("Error While writing output to file - ", e);
		}
	}
}
