package algoritmos;

import java.util.Arrays;

public class JaroWinkler {

	public String nombre="Jaro-Winkler";
	
	public static  Double similitud;
	
    public JaroWinkler() {
        super();
    }
    
    
    public boolean comprobar(String _nombre) {
    	if (nombre.equals(_nombre)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
   /**
    * Represents a failed index search.
    */
   public static final int INDEX_NOT_FOUND = -1;

   /**
    * Find the Jaro Winkler Distance which indicates the similarity score
    * between two CharSequences.
    *
    * <pre>
    * distance.apply(null, null)          = IllegalArgumentException
    * distance.apply("","")               = 0.0
    * distance.apply("","a")              = 0.0
    * distance.apply("aaapppp", "")       = 0.0
    * distance.apply("frog", "fog")       = 0.93
    * distance.apply("fly", "ant")        = 0.0
    * distance.apply("elephant", "hippo") = 0.44
    * distance.apply("hippo", "elephant") = 0.44
    * distance.apply("hippo", "zzzzzzzz") = 0.0
    * distance.apply("hello", "hallo")    = 0.88
    * distance.apply("ABC Corporation", "ABC Corp") = 0.93
    * distance.apply("D N H Enterprises Inc", "D &amp; H Enterprises, Inc.") = 0.95
    * distance.apply("My Gym Children's Fitness Center", "My Gym. Childrens Fitness") = 0.92
    * distance.apply("PENNSYLVANIA", "PENNCISYLVNIA")    = 0.88
    * </pre>
    *
    * @param left the first String, must not be null
    * @param right the second String, must not be null
    * @return result distance
    * @throws IllegalArgumentException if either String input {@code null}
    */
   public Double calcularSimilitudJaroWinkler(final CharSequence left, final CharSequence right) {
       final double defaultScalingFactor = 0.1;
       final double percentageRoundValue = 100.0;

       if (left == null || right == null) {
           throw new IllegalArgumentException("Strings must not be null");
       }

       int[] mtp = matches(left, right);
       double m = mtp[0];
       if (m == 0) {
           return 0D;
       }
       double j = ((m / left.length() + m / right.length() + (m - mtp[1]) / m)) / 3;
       double jw = j < 0.7D ? j : j + Math.min(defaultScalingFactor, 1D / mtp[3]) * mtp[2] * (1D - j);
       return Math.round(jw * percentageRoundValue) / percentageRoundValue;
   }

   /**
    * This method returns the Jaro-Winkler string matches, transpositions, prefix, max array.
    *
    * @param first the first string to be matched
    * @param second the second string to be machted
    * @return mtp array containing: matches, transpositions, prefix, and max length
    */
   protected static int[] matches(final CharSequence first, final CharSequence second) {
       CharSequence max, min;
       if (first.length() > second.length()) {
           max = first;
           min = second;
       } else {
           max = second;
           min = first;
       }
       int range = Math.max(max.length() / 2 - 1, 0);
       int[] matchIndexes = new int[min.length()];
       Arrays.fill(matchIndexes, -1);
       boolean[] matchFlags = new boolean[max.length()];
       int matches = 0;
       for (int mi = 0; mi < min.length(); mi++) {
           char c1 = min.charAt(mi);
           for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
               if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                   matchIndexes[mi] = xi;
                   matchFlags[xi] = true;
                   matches++;
                   break;
               }
           }
       }
       char[] ms1 = new char[matches];
       char[] ms2 = new char[matches];
       for (int i = 0, si = 0; i < min.length(); i++) {
           if (matchIndexes[i] != -1) {
               ms1[si] = min.charAt(i);
               si++;
           }
       }
       for (int i = 0, si = 0; i < max.length(); i++) {
           if (matchFlags[i]) {
               ms2[si] = max.charAt(i);
               si++;
           }
       }
       int transpositions = 0;
       for (int mi = 0; mi < ms1.length; mi++) {
           if (ms1[mi] != ms2[mi]) {
               transpositions++;
           }
       }
       int prefix = 0;
       for (int mi = 0; mi < min.length(); mi++) {
           if (first.charAt(mi) == second.charAt(mi)) {
               prefix++;
           } else {
               break;
           }
       }
       return new int[] { matches, transpositions / 2, prefix, max.length() };
   }
    
    
    /*public double calcularSimilitudJaroWinkler(String str1, String str2) {
    	
    	return calcularSimilitudJaroWinklerP(str1, str2);
    	
    }
    
    */
    
    /**
	 * Applies the Jaro-Winkler distance algorithm to the given strings, providing information about the
	 * similarity of them.
	 * 
	 * @param s1 The first string that gets compared. May be <code>null</node> or empty.
	 * @param s2 The second string that gets compared. May be <code>null</node> or empty.
	 * @return The Jaro-Winkler score (between 0.0 and 1.0), with a higher value indicating larger similarity.
	 * 
	 * @author Thomas Trojer <thomas@trojer.net>
	 */
    /*private static double calcularSimilitudJaroWinklerP(final String s1, final String s2) {
    		// lowest score on empty strings
    		if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
    			return 0;
    		}
    		// highest score on equal strings
    		if (s1.equals(s2)) {
    			return 1;
    		}
    		// some score on different strings
    		int prefixMatch = 0; // exact prefix matches
    		int matches = 0; // matches (including prefix and ones requiring transpostion)
    		int transpositions = 0; // matching characters that are not aligned but close together 
    		int maxLength = Math.max(s1.length(), s2.length());
    		int maxMatchDistance = Math.max((int) Math.floor(maxLength / 2.0) - 1, 0); // look-ahead/-behind to limit transposed matches
    		// comparison
    		final String shorter = s1.length() < s2.length() ? s1 : s2;
    		final String longer = s1.length() >= s2.length() ? s1 : s2;
    		for (int i = 0; i < shorter.length(); i++) {
    		    // check for exact matches
    			boolean match = shorter.charAt(i) == longer.charAt(i);
    			if (match) {
    				if (i < 4) {
    					// prefix match (of at most 4 characters, as described by the algorithm)
    					prefixMatch++;
    				}
    				matches++;
    				continue;
    			}
    			// check fro transposed matches
    			for (int j = Math.max(i - maxMatchDistance, 0); j < Math.min(i + maxMatchDistance, longer.length()); j++) {
    				if (i == j) {
    					// case already covered
    					continue;
    				}
    				// transposition required to match?
    				match = shorter.charAt(i) == longer.charAt(j);
    				if (match) {
    					transpositions++;
    					break;
    				}
    			}
    		}
    		// any matching characters?
    		if (matches == 0) {
    			return 0;
    		}
    		// modify transpositions (according to the algorithm)
    		transpositions = (int) (transpositions / 2.0);
    		// non prefix-boosted score
    		double score = 0.3334 * (matches / (double) longer.length() + matches / (double) shorter.length() + (matches - transpositions)
    				/ (double) matches);
    		if (score < 0.7) {
    			return score;
    		}
    		// we already have a good match, hence we boost the score proportional to the common prefix
    		double similitud = score + prefixMatch * 0.1 * (1.0 - score);
    		return similitud;
    	}
    
    public double getSimilitud() {
    	return similitud;
    }
    */
}




