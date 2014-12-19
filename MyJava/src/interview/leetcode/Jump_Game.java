package interview.leetcode;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * 
 * A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * @author yazhoucao
 * 
 */
public class Jump_Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A0 = new int[] { 2, 3, 1, 1, 4 };
		int[] A1 = new int[] { 3, 2, 1, 0, 4 };
		System.out.println(canJump(A0));
		System.out.println(canJump(A1));
	}

	/**
	 * DP O(n^2), timeout
	 * 
	 * @param A
	 * @return
	 */
	public static boolean canJump(int[] A) {
		int len = A.length;
		if (len <= 1)
			return true;
		boolean[] dp = new boolean[len];
		dp[len - 1] = true;

		for (int i = len - 2; i >= 0; i--) {
			// max index i can jump to
			int maxIdx = i + A[i] >= len ? len - 1 : i + A[i];
			for (int j = i + 1; j <= maxIdx; j++) {
				if (dp[j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[0];
	}

	/**
	 * Linear Time Solution
	 * 
	 * In Jump Game, we can save the farthest position we can reach when we go
	 * through the array. Every time we move, we will decrease the step. When we
	 * updating it, the step remaining for us is also updated.
	 * 
	 * For example, for array A = [2, 3, 1, 1, 4], when we visit A[0], we update
	 * the maxReach to 2, and step to 2. When we visit A[1], we update maxReach
	 * to 1 + 3 = 4. And step is updated to 3. We will continue doing this until
	 * we reach the end or step getting to zero. If step getting to zero before
	 * we reach the end, it means that we can’t move forward so that we should
	 * return false.
	 * 
	 * @param A
	 * @return
	 */
	public static boolean canJump2(int[] A) {
		int steps = A[0];
		int reach = A[0];
		for (int i = 1; i < A.length; i++) {
			if (steps == 0)
				return false;
			if (reach >= A.length - 1)
				return true;
			steps--;
			if (A[i] > steps) {
				steps = A[i];
				reach = i + A[i];
			}
		}
		return true;
	}

	/**
	 * Linear Time, more concise version
	 */
	public boolean canJump_concise(int[] A) {
		int furthestReach = 0;
		for (int i = 0; i <= furthestReach && furthestReach < A.length - 1; ++i) {
			furthestReach = Math.max(furthestReach, i + A[i]);
		}
		return furthestReach >= A.length - 1;
	}

}
