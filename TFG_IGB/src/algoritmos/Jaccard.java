package algoritmos;

import java.text.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Jaccard {

	public String nombre="Jaccard";
	
	public static  Double similitud;
	
    public Jaccard() {
        super();
    }
    
    
    public boolean comprobar(String _nombre) {
    	if (nombre.equals(_nombre)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    	
    public Double calcularSimilitudJaccard(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        similitud= Math.round(calculateJaccard(left, right) * 100d) / 100d;
        
        return similitud;
    }

    /**
     * Calculates Jaccard Similarity of two character sequences passed as
     * input. Does the calculation by identifying the union (characters in at
     * least one of the two sets) of the two sets and intersection (characters
     * which are present in set one which are present in set two)
     * 
     * @param left first character sequence
     * @param right second character sequence
     * @return index
     */
    private Double calculateJaccard(CharSequence left, CharSequence right) {
        Set<String> intersectionSet = new HashSet<String>();
        Set<String> unionSet = new HashSet<String>();
        boolean unionFilled = false;
        int leftLength = left.length();
        int rightLength = right.length();
        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }

        for (int leftIndex = 0; leftIndex < leftLength; leftIndex++) {
            unionSet.add(String.valueOf(left.charAt(leftIndex)));
            for (int rightIndex = 0; rightIndex < rightLength; rightIndex++) {
                if (!unionFilled) {
                    unionSet.add(String.valueOf(right.charAt(rightIndex)));
                }
                if (left.charAt(leftIndex) == right.charAt(rightIndex)) {
                    intersectionSet.add(String.valueOf(left.charAt(leftIndex)));
                }
            }
            unionFilled = true;
        }
        return Double.valueOf(intersectionSet.size()) / Double.valueOf(unionSet.size());
    }
    
    /*public static double calcularSimilitudJaccard(String document1, String document2) {
        // Get the text corresponding to the stem and encapsulate it
		List<String> wordslist1 = getlema(document1);
		List<String> wordslist2 = getlema(document2);
		Set<String> words2Set = new HashSet<>();
		words2Set.addAll(wordslist2);
		        // Seeking intersection
		Set<String> intersectionSet = new ConcurrentSkipListSet<>();
		wordslist1.parallelStream().forEach(word -> {
		   if (words2Set.contains(word)) {
		       intersectionSet.add(word);
		   }
		});
		        // The size of the intersection
		int intersectionSize = intersectionSet.size();
		        // seeking union
		Set<String> unionSet = new HashSet<>();
		wordslist1.forEach(word -> unionSet.add(word));
		wordslist2.forEach(word -> unionSet.add(word));
		        // The size of the union
		int unionSize = unionSet.size();
		        // Similarity score
		double score = intersectionSize / (double) unionSize;
		if (LOGGER.isDebugEnabled()) {
		   LOGGER.debug("Size of intersection:" + intersectionSize);
		   LOGGER.debug("The size of the union:" + unionSize);
		   LOGGER.debug("Similarity score =" + intersectionSize + "/(double)" + unionSize + "=" + score);
		}
		return score;
	}
	
    public static List<String> getlema(String text){
		        // The word set corresponding to the stem
		List<String> wordslist = new ArrayList<>();;
		        // StanfordCoreNLP gets the stem
		Properties props = new Properties();  // set up pipeline properties
		props.put("annotators", "tokenize, ssplit, pos, lemma"); // Word segmentation, clauses, part-of-speech tagging and dimension information.
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation document = new Annotation(text);
		pipeline.annotate(document);
		List<CoreMap> words = document.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap word_temp: words) {
		   for (CoreLabel token: word_temp.get(CoreAnnotations.TokensAnnotation.class)) {
		       String lema = token.get(CoreAnnotations.LemmaAnnotation.class); // Get the meta-information corresponding to the above word, that is, the word after the reduction of the form I need
		       wordslist.add(lema);
		//       System.out.println(lema);
   }
}
return wordslist;
}*/
	
	/*public double calcularSimilitudJaccard(Set<T> a, Set<T> b) {
		return 1.0f - compare(a, b);
	}
	
	public double compare(Set<T> a, Set<T> b) {

		if (a.isEmpty() && b.isEmpty()) {
			return 1.0f;
		}

		if (a.isEmpty() || b.isEmpty()) {
			return 0.0f;
		}
		
		final int intersection = intersection(a, b).size();

		// ∣a ∩ b∣ / ∣a ∪ b∣
		// Implementation note: The size of the union of two sets is equal to
		// the size of both sets minus the duplicate elements.
		return intersection / (float) (a.size() + b.size() - intersection);
	}

	@Override*/

	
}
