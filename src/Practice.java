

import java.util.*;


class Practice {
    public static int firstUniqChar( String s) {

        // returns index of first unique character
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            count.put(c,count.getOrDefault(c,0) + 1);
        }
        for (int i = 0; i < len; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static int romanToInt(String s){
        int result = 0;
        for (int i = s.length() -1; i >= 0; i--) {
            switch (s.charAt(i)){
                case 'M':
                    result += 1000;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'C':
                    result += 100 * (result >= 500 ? -1 : 1);
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'X':
                    result += 10 * (result >= 50 ? -1 : 1);
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'I':
                    result += 1 * (result >= 5 ? -1 : 1);
                    break;
                default:
                    break;
            }

        }
        return result;
    }

    public static boolean isAnagram(String s, String t) {

        // returns a boolean isanagram
        char[] string1 = s.toCharArray();
        char[] string2 = t.toCharArray();
        Arrays.sort(string1);
        Arrays.sort(string2);

        return Arrays.equals(string1, string2);

    }

    public static int kMaxProfit(int k, int [] nums){

        int profit[][] = new int[k + 1][ nums.length + 1];
        for (int i = 0; i <= k; i++) {
            profit[i][0] = 0;
        }

        for (int j = 0; j <= nums.length; j++) {
            profit[0][j] = 0;
        }

        for (int i = 1; i <= k; i++)
        {
            int prevDiff = Integer.MIN_VALUE;
            for (int j = 1; j < nums.length; j++)
            {
                prevDiff = Math.max(prevDiff,
                        profit[i - 1][j - 1] -
                                nums[j - 1]);
                profit[i][j] = Math.max(profit[i][j - 1],
                        nums[j] + prevDiff);
            }
        }

        return profit[k][nums.length - 1];
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public static int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        if (leftDepth > rightDepth){
            return leftDepth + 1;
        }
        else {
            return rightDepth + 1;
        }
    }

    public static boolean isValidBST(TreeNode root) {
       return isValidBSTBound(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean isValidBSTBound(TreeNode root, int min, int max){
        if(root.val<=min||root.val>=max){
            return false;
        }
        boolean isLeftBST = isValidBSTBound(root.left, min, root.val);
        boolean isRightBST = isValidBSTBound(root.right, root.val, max);

        if(!isLeftBST||!isRightBST){
            return false;
        }

        return true;
    }

    public int reachNumber(int target) {
        int n = 1;
        boolean hi = true;
        while (hi){
            if (triNum(n) < target) n++;
            if (triNum(n) % 2 != target % 2) n++;
            if (target <= triNum(n) && triNum(n) % 2 == target % 2) hi = false;
        }
        return n - 1;
    }
    public int triNum (int n){
        return ((n*(n-1))/2);

    }
    public static int smallestRangeII(int[] A, int K) {
        if (A.length == 1) return 0;
        int dif = Integer.MAX_VALUE;
        int curMax = Integer.MAX_VALUE;
        int curMin = Integer.MIN_VALUE;
        if (A[0] < A[1]){
            curMax = A[1];
            curMin = A[0];
        }
        else {
            curMax = A[0];
            curMin = A[1];

        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= curMax - K){
                curMax = A[i] - K;
                dif = curMax - curMin;
            }
            else if (A[i] < curMin + K){
                curMin = A[i] + K;
                dif = curMax - curMin;
            }

        }

        return dif;
    }

    public boolean isSymmetric(TreeNode root) {
        return helperSym(root, root);
    }
    public boolean helperSym(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        
        else {
            return (helperSym(left.left, right.right) && helperSym(left.right, right.left));
        }


    }

    public static int myAtoi(String str) {
        List<Character> chars = new ArrayList<Character>();
        int num = 0;
        int digit = 1;
        chars.add(0, '0');
        chars.add(1, '1');
        chars.add(2, '2');
        chars.add(3, '3');
        chars.add(4, '4');
        chars.add(5, '5');
        chars.add(6, '6');
        chars.add(7, '7');
        chars.add(8, '8');
        chars.add(9, '9');
        chars.add(10, '-');
        chars.add(11, '+');
        if (str.length() == 1 && chars.contains((str.charAt(0))) && str.charAt(0) != '-') {
            return Integer.parseInt(String.valueOf(str.charAt(0)));
        }
        for (int i = 0; i < str.length() - 1; i++) {
            while (str.charAt(i) == ' '){
                i++;
            }
            if (!chars.contains(str.charAt(i))){
                return num;
            }
            else if (str.charAt(i) == '-'){
                digit *= -1;
                i++;
            }
            else if (str.charAt(i) == '+'){
                digit *= 1;
                i++;
                if (i == str.length()) return num;
            }
            int x = str.length();
            while (chars.contains(str.charAt(i)) && i <= x -1){
                if (str.charAt(i) == '-' || str.charAt(i) == '+' ){
                    return num;
                }
                if (num*digit < Integer.MIN_VALUE/10) return Integer.MIN_VALUE;
                if (num > Integer.MAX_VALUE/10) return Integer.MAX_VALUE;
                num *= 10;
                num += Integer.parseInt(String.valueOf(str.charAt(i)));
                i++;
                if (i == str.length()){
                    return num * digit;
                }
            }
            return num * digit;
        }
        return num * digit;
    }

    public static boolean isPalindrome(int num){
        //boolean is palindrome of an int
        int left = 0;
        String num1 =Integer.toString(num);
        int right = num1.length() - 1;

        while (left < right){
            if (num1.charAt(left) != num1.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    public static boolean isPalindromeRecur(int num){
        //recursive boolean is palindrome of int
        int left = 0;
        String num1 =Integer.toString(num);
        int right = num1.length() - 1;

        if (num1.charAt(left) != num1.charAt(right)){
            return false;

        }

        else if (left == right || num1.length() == 1){
            return true;

        }

        num1 = num1.substring(left+1, right);
        int x = Integer.parseInt(num1);
        return isPalindromeRecur(x);
    }

    public static String longestCommonPrefix(String[] words){
        //returns string containing longest common prefix
        String lcp = "";
        int cur = 0;
        char current = words[0].charAt(cur);
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < shortest){
                shortest = words[i].length();
            }

        }
        while (cur !=  shortest){
            for (String x: words) {
                if (x.charAt(cur) != current){
                    return lcp;
                }
            }

            current = words[0].charAt(cur);

            lcp = lcp + Character.toString(current);
            cur +=1;
            if (cur != shortest){
                current = words[0].charAt(cur);
            }

        }


        return lcp;
    }




    public static boolean canWinNim(int stones){
        return  (stones % 4 != 0);
    }

    public static int nBulb(int bulbs){
        return (int) Math.sqrt(bulbs);
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0){
            if (nums1[m-1] > nums2[n -1]){
                nums1[m+n-1] = nums1 [m -1];
                m--;
            }
            else {
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        while (n > 0){
            nums1[m+n-1] = nums2[n-1];
            n--;
        }
        return nums1;
    }
    public int climbStairs(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 2; i < n; i++) {
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n-1];
    }
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice){
                minprice = prices[i];
            }
            else if (prices[i] - minprice > maxPrice) maxPrice = prices[i] - minprice;


        }
        return maxPrice;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5,0,0,0,0,0};
        int[] nums2 = {-1,2,4,8,9};
        System.out.print(merge(nums1, 5,nums2, 5));

    }

    }
