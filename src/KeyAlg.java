import java.util.ArrayList;
import java.util.HashMap;


public class KeyAlg {

	private static HashMap<Character, char[]> nextKeys = new HashMap<Character, char[]>();
	
	private static int keyMapCount = 0;
	
	public KeyAlg() {
		nextKeys.put('A', new char[]{'H', 'L'});
		nextKeys.put('B', new char[]{'K', 'M', 'I'});
		nextKeys.put('C', new char[]{'F', 'L', 'N', 'J'});
		nextKeys.put('D', new char[]{'G', 'M', 'O'});
		nextKeys.put('E', new char[]{'H', 'N'});
		nextKeys.put('F', new char[]{'C', 'M', '1'});
		nextKeys.put('G', new char[]{'D', 'N', '2'});
		nextKeys.put('H', new char[]{'A', 'E', 'O', '3', '1', 'K'});
		nextKeys.put('I', new char[]{'B', 'L', '2'});
		nextKeys.put('J', new char[]{'C', 'M', '3'});
		nextKeys.put('K', new char[]{'B', 'H', '2'});
		nextKeys.put('L', new char[]{'A', 'C', 'I', '3'});
		nextKeys.put('M', new char[]{'F', 'B', 'D', 'J'});
		nextKeys.put('N', new char[]{'1', 'G', 'C', 'E'});
		nextKeys.put('O', new char[]{'2', 'H', 'D'});
		nextKeys.put('1', new char[]{'F', 'H', 'N'});
		nextKeys.put('2', new char[]{'K', 'G', 'I', 'O'});
		nextKeys.put('3', new char[]{'L', 'H', 'J'});

		for (Character c : nextKeys.keySet()) {
			ArrayList<Character> seedList = new ArrayList<Character>();
			seedList.add(new Character(c));
			nextStepRecurse(seedList);
		}

		System.out.println("Number of key combinations is " + keyMapCount);
	}
	
	private void nextStepRecurse(ArrayList<Character> keyList) {
		char key = keyList.get(keyList.size() - 1);
		
		boolean keyVowel = false;
		
		if ((key == 'A') || (key == 'E') || (key == 'I') || (key == 'O')) {
			keyVowel = true;
		}
		
		for (int i = 0; i < keyList.size() - 2; i++)
		{
			char keyCompare = keyList.get(i);
			
			if (key == keyCompare) {
				return;
			}
			
			if ((keyVowel == true) &&
				((keyCompare == 'A') || (keyCompare == 'E') || (keyCompare == 'I') || 
					(keyCompare == 'O'))) {
				return;
			}
		}
		
		if (keyList.size() == 10) {
			keyMapCount++;
			return;
		}
		
		for (Character nextKey : nextKeys.get(key)) {
			ArrayList<Character> klCopy = new ArrayList<Character>();
			klCopy.addAll(keyList);
			klCopy.add(nextKey);
			nextStepRecurse(klCopy);
		}
	}
	
}
