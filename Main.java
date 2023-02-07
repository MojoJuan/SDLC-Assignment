import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader("textFile.txt"));

		// creating an empty map used to store word and frequencies of all words
		Map<String, Integer> wordCounts = new HashMap<>();

		String line;

		while ((line = bufferedReader.readLine()) != null) {

			// splitting line by use regular expression
			String[] words = line.split("[\\s;,?:!()\"]+");

			// iterate all words
			for (String word : words) {

				word = word.trim();

				if (word.length() > 0) {

					if (wordCounts.containsKey(word)) {
						wordCounts.put(word, wordCounts.get(word) + 1);
					} else {
						wordCounts.put(word, 1);
					}
				}
			}
		}

		// sorting wordCounts by frequency
		Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
				.sorted(Collections.reverseOrder(Entry.comparingByValue()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		// printing word and frequencies of all words


		for (Map.Entry<String, Integer> entry : sortedWordCounts.entrySet()) {

			System.out.printf("%-20s%10s\n", entry.getKey(), entry.getValue());
		}

		bufferedReader.close();
	}

}