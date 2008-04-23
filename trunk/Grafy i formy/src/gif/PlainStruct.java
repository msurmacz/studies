package gif;

import java.util.List;

public final class PlainStruct {
	private int coeffNum;
	private List<int[]> coeffs;
	/**
	 * @param coeffNum
	 * @param coeffs
	 */
	public PlainStruct(int coeffNum, List<int[]> coeffs) {
		super();
		this.coeffNum = coeffNum;
		this.coeffs = coeffs;
	}
	public int getCoeffNum() {
		return coeffNum;
	}
	public List<int[]> getCoeffs() {
		return coeffs;
	}

	
}
