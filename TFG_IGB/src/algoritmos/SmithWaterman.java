package algoritmos;


import funciones.AffineGap;
import funciones.Gap;
import funciones.MatchMismatch;
import funciones.Substitution;

import static java.lang.Math.max;
import static java.lang.Math.min;



public class SmithWaterman {

	public String nombre="Smith-Waterman";
	
	public static  Double similitud;
	
	private final Gap gap;
	private final Substitution substitution;
	private final int windowSize;
	
    
	public SmithWaterman() {
		this(new AffineGap(-5.0f, -1.0f), new MatchMismatch(5.0f, -3.0f), Integer.MAX_VALUE);
	}
    
	public SmithWaterman(Gap gap, Substitution substitution, int windowSize) {
		
		if (gap == null) {
			throw new IllegalArgumentException();
		}
		
		if (substitution == null) {
			throw new IllegalArgumentException();
		}
		if (windowSize < 0) {
			throw new IllegalArgumentException();
		}
		this.gap = gap;
		this.substitution = substitution;
		this.windowSize = windowSize;
	}
	
    public boolean comprobar(String _nombre) {
    	if (nombre.equals(_nombre)) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
	
	public double calcularSimilitudSmithWaterman(String a, String b) {

		if (a.isEmpty() && b.isEmpty()) {
			return 1.0f;
		}
		if (a.isEmpty() || b.isEmpty()) {
			return 0.0f;
		}
		float maxDistance = min(a.length(), b.length()) * max(substitution.max(), gap.min());
		return calcularSimilitudSmithWatermanP(a, b) / maxDistance;

	}

	private double calcularSimilitudSmithWatermanP(String a, String b) {
		final int n = a.length();
		final int m = b.length();

		final float[][] d = new float[n][m];

		// Initialize corner
		float max = d[0][0] = max(0, substitution.compare(a, 0, b, 0));

		// Initialize edge
		for (int i = 0; i < n; i++) {

			// Find most optimal deletion
			float maxGapCost = 0;
			for (int k = max(1, i - windowSize); k < i; k++) {
				maxGapCost = max(maxGapCost, d[i - k][0] + gap.value(i - k, i));
			}

			d[i][0] = max2(0.0f, maxGapCost, substitution.compare(a, i, b, 0));

			max = max(max, d[i][0]);

		}

		// Initialize edge
		for (int j = 1; j < m; j++) {

			// Find most optimal insertion
			float maxGapCost = 0;
			for (int k = max(1, j - windowSize); k < j; k++) {
				maxGapCost = max(maxGapCost, d[0][j - k] + gap.value(j - k, j));
			}

			d[0][j] = max2(0.0f, maxGapCost, substitution.compare(a, 0, b, j));

			max = max(max, d[0][j]);

		}

		// Build matrix
		for (int i = 1; i < n; i++) {

			for (int j = 1; j < m; j++) {

				float maxGapCost = 0;
				// Find most optimal deletion
				for (int k = max(1, i - windowSize); k < i; k++) {
					maxGapCost = max(maxGapCost,
							d[i - k][j] + gap.value(i - k, i));
				}
				// Find most optimal insertion
				for (int k = max(1, j - windowSize); k < j; k++) {
					maxGapCost = max(maxGapCost,
							d[i][j - k] + gap.value(j - k, j));
				}

				// Find most optimal of insertion, deletion and substitution
				d[i][j] = max2(0.0f, maxGapCost,
						d[i - 1][j - 1] + substitution.compare(a, i, b, j));

				max = max(max, d[i][j]);
			}

		}

		return max;
	}

	
	static int max2(final int a, final int b, final int c) {
		return java.lang.Math.max(java.lang.Math.max(a, b), c);
	}
	
	static float max2(float a, float b, float c) {
		return java.lang.Math.max(java.lang.Math.max(a, b), c);
	}
	

	
}
