import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

/**
 * 
 * @author Alberto Bandeira de Mello, CS student @ UoPeople
 *
 */
public class Main {
	
	static int sentences = 1;
	static int maxSentencesSize;
	
	static String[] conjunctions = {"and", "or", "but", "because"};
	static String[] proper_noun = {"Fred", "Jane", "Richard Nixon", "Miss America"};
	static String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn"};
	static String[] determiner = {"a", "the", "every", "some"};
	static String[] adjective = {"big", "tiny", "pretty", "bald"};
	static String[] intransitive_verb = {"runs", "jumps", "talks", "sleeps"};
	static String[] transitive_verb = {"loves", "hates", "sees", "knows", "looks for", "finds"};
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String sentence = "";
		
		System.out.println("Welcome to the sentence generator");
		
		System.out.print("Choose the max number of sentences to be generated (hit 0 to random): ");
		int choice = scanner.nextInt();
		
		if(choice == 0) {
			maxSentencesSize = ThreadLocalRandom.current().nextInt(1, 5);
		} else {
			maxSentencesSize = choice;
		}
		
		System.out.println("The sentence will have the size " + maxSentencesSize);
		
		sentence = getSentence(sentence) + ".";
		
		System.out.println("The sentence is:");
		System.out.println(sentence);
		
	}
	
	/**
	 * The method that returns the final sentence according to the predefined size of the sentence
	 * @param actualSentence
	 * @return the final sentence that will be generated
	 */
	private static String getSentence(String actualSentence) {
		
		String result;
		
		if (sentences < maxSentencesSize) {
			
			sentenceAdded();
			//result = actualSentence + " " + getSimpleSentence() + " " +
			//		getRandomValue(conjunctions);
			result = getSimpleSentence() + " " + getRandomValue(conjunctions);
			return result + getSentence(result);
		} else if(sentences == maxSentencesSize) {
			
			sentenceAdded();
			
			result = getSimpleSentence();
			return result + getSentence(result);
		}
		
		return "";
		
	}
	
	/**
	 * 
	 * @return the basic structure of a sentence
	 */
	private static String getSimpleSentence() {
		return " " + getNounPhrase() + " " + getVerbPhrase();
	}
	
	/**
	 * 
	 * @return a noun phrase
	 */
	private static String getNounPhrase() {
		String answer = "";
		// Will be proper_noun or determiner?
		if(ThreadLocalRandom.current().nextBoolean()) {
			answer = getRandomValue(proper_noun);
		} else {
			answer = getRandomValue(determiner) ;
			
			// Will have adjective?
			if(ThreadLocalRandom.current().nextBoolean()) {
				answer += " " + getRandomValue(adjective);
			}
			
			answer += " " + getRandomValue(common_noun);		
			if(ThreadLocalRandom.current().nextBoolean()) {
				answer += " who " + getVerbPhrase();
			}
			
		}
		
		return answer;
	}
	
	/**
	 * 
	 * @return verb Phrase
	 */
	private static String getVerbPhrase() {
		
		switch(ThreadLocalRandom.current().nextInt(1, 5)) {
		case 1:
			return getRandomValue(intransitive_verb);
		case 2:
			return getRandomValue(transitive_verb) + " " + getNounPhrase();
		case 3:
			return "is " + getRandomValue(adjective);
		default:
			return "believes that" + getSimpleSentence();
		}
		
	}
	
	/**
	 * Method that informs that one more sentece was added to the complete sentence.
	 */
	private static void sentenceAdded() {
		sentences ++;
	}
	
	/**
	 * @param valueList
	 * @return random value from Array
	 */
	public static String getRandomValue(String[] valueList) {
		
		int randomNumber = ThreadLocalRandom.current().nextInt(0, valueList.length);
		return valueList[randomNumber];
		
	}

}
