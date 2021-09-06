package test;

import java.util.*;

/**
 * @program:IntelliJ IDEA
 * @Description:
 * @Author:Matinal
 * @Date:2021-09-06 16:33
 */
public class TestDemo{


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){

        }
    }

    public int[] arrayOfReverse(int[] arr){
        int i = 0;
        int j = arr.length-1;
        for (; i < j; i++,j--) {
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map1 =new HashMap<>();
        HashMap<Character,Integer> map2 =new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(int i = 0;i<ransomNote.length();i++){
            set.add(ransomNote.charAt(i));
        }
        for(int i = 0;i<ransomNote.length();i++){
            char tmp = ransomNote.charAt(i);
            map1.put(tmp,map1.getOrDefault(tmp,0)+1);
        }
        for(int i = 0;i<magazine.length();i++){
            char tmp = magazine.charAt(i);
            map2.put(tmp,map2.getOrDefault(tmp,0)+1);
        }
        for (char s:set) {
            if(map2.containsKey(s)){
                if(map1.get(s) > map2.get(s)){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }


    public boolean isAnagram(String s, String t) {
        int i = s.length();
        int j = t.length();
        if(i != j) return false;
        char[] strs = s.toCharArray();
        char[] strt = t.toCharArray();
        Arrays.sort(strs);
        Arrays.sort(strt);
        boolean flg = Arrays.equals(strs,strt);
        return flg;
    }

    //判断是否为环形链表
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode nxt = head;
        ListNode cur = head.next;
        while(cur != nxt){
            if(cur == null || cur.next == null){
                return false;
            }
            nxt = nxt.next;
            cur = cur.next.next;
        }
        return true;
    }


    //层序遍历二叉树
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> listq = new ArrayList<>();
            int curQueueSize = queue.size();
            for (int i = 0; i < curQueueSize; i++) {
                TreeNode node = queue.poll();
                listq.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(listq);
        }
        return list;
    }


    //二叉树的最大深度
    public int maxDepth(TreeNode root) {
        int max = 0;
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            max = Math.max(leftHeight, rightHeight);
        }
        return max+1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] arr = new int[length1+length2];
        for(int i = 0; i < length1;i++) {
            arr[i] = nums1[i];
        }
        for(int i = 0;i < nums2.length;i++) {
            arr[length1++] = nums2[i];
        }
        Arrays.sort(arr);
        if(arr.length % 2 != 0){
            double median = arr[arr.length/2];
            return median;
        }
        return (arr[arr.length/2] + arr[arr.length/2 -1])/2;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public String longestPalindrome1(String s) {
        String str = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (er(test) && test.length() > max) {
                    str = s.substring(i, j);
                    max = Math.max(max, str.length());
                }
            }
        }
        return str;
    }
    public boolean er(String s){
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
    //顺序输出整数
    public int reverse(int x){
        int tmp = 0;
        while(x != 0){
            int count = x % 10;
            x /= 10;
            tmp = tmp * 10 + count;
        }
        return tmp;
    }

    public int romanToInt(String s) {

        int len = s.length();
        if(len == 1){
            return value(s.charAt(0));
        }
        int sum = 0;
        for (int i = 1;i < len ; i++){
            if( value(s.charAt(i)) > value(s.charAt(i-1))){
                sum -= value(s.charAt(i-1));
            }else{
                sum += value(s.charAt(i-1));
            }
        }
        sum += value(s.charAt(len-1));
        return sum;
    }
    public int value(char s){
        int tmp = 0;
        switch(s){
            case 'I' : tmp = 1;
                break;

            case 'V' : tmp = 5;
                break;

            case 'X' : tmp = 10;
                break;

            case 'L' : tmp = 50;
                break;

            case 'C' : tmp = 100;
                break;

            case 'D' : tmp = 500;
                break;

            case 'M' : tmp = 1000;
                break;

            default:
                System.out.println("你输入的字符"+ s +"有误!" );
        }
        return tmp;
    }



    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        String str = "";
        boolean flg = false;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min,strs[i].length());
        }
        for(int j = 0; j < min ; j++){
            for(int i = 1; i < len ; i++){
                if(strs[0].substring(j,j+1).equals(strs[i].substring(j,j+1))){
                    if(i == len-1){
                        str += strs[0].substring(j,j+1);
                    }

                }else{
                    flg = true;
                    break;
                }
            }
            if(flg == true){
                break;
            }
        }
        return str;
    }


    public int removeDuplicates(int[] nums) {
        int tmp = 0;
        for(int i = 0;i<nums.length;i++){
            for(int j = i+1 ; j < nums.length ; j++){
                if(nums[i] == nums[j]){
                    tmp++;
                    for(int k = j+1 ; k < nums.length ; k++){
                        nums[k-1] = nums[k];
                    }
                    nums[nums.length-1] = nums[0]-j;
                }
            }
        }
        return nums.length - tmp;
    }

    public int strStr(String haystack, String needle) {
        int len = needle.length();
        if(len == 0 || haystack.length() == 1){
            return 0;
        }
        for(int i = len; i <= haystack.length() ;i++){
            if(haystack.substring(i - len,i).equals(needle)){
                return i - len;
            }
        }
        return -1;
    }



    public String addBinary(String a, String b) {
        int sub = sum(a) + sum(b);
        List<Integer> list = new ArrayList<>();
        while(sub > 0){
            list.add(sub % 2);
            sub /= 2;
        }
        String str = "";
        for(int i = list.size() - 1; i >= 0 ; i-- ) {
            str  = str + list.get(i);
        }
        if(list.size() == 0){
            return "0";
        }
        return str;
    }
    public int sum(String x) {
        int tmp = 0;
        int len = x.length();
        for(int i = 0 ; i < len ; i++) {
            tmp = 2*tmp + val(x.charAt(i));
        }
        return tmp;
    }
    public int val(char x){
        if(x == '1'){
            return 1;
        }
        return 0;
    }


    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int len = 0;
        for(int i = 0; i < nums.size(); i++){
            len += nums.get(i).size();
        }
        int[] arr = new int[len];
        int k = 0;
        for(int i = 0 ; i < nums.size(); i++) {
            int f = i;
            for(int j = 0; j < nums.size(); j++) {
                if(f >= 0 && i == f + j){
                    if(nums.get(f).get(j) != null){
                        arr[k] = nums.get(f).get(j);
                        k++;
                        f--;
                    }
                }else{
                    break;
                }
            }
        }
        boolean flg = false;
        for(int i = nums.size() - 1 ;i >= 0 ; i--){
            int f = i;
            for(int j = nums.size() - 1 ; j >= 0 ; j--){
                if(f < nums.size() && i == (f + j)/2){
                    if(nums.get(f).get(j) != null){
                        if(k <= len){
                            arr[len - 1] = nums.get(f).get(j);
                            len--;
                            k++;
                            f++;
                        }else{
                            flg = true;
                            break;
                        }
                    }
                }else{
                    break;
                }
            }
            if(flg == true){
                break;
            }
        }
        return arr;
    }


    public String reverseLeftWords(String s, int n) {
        StringBuilder str = new StringBuilder();
        for(int i = n ; i < s.length() ;i++) {
            str.append(s.charAt(i));
        }
        for(int i = 0 ; i < n ; i++){
            str.append(s.charAt(i));
        }
        return str.toString();
    }

    public int[] levelOrder1(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(root.left != null) queue.add(root.left);
            if(root.right != null) queue.offer(root.right);

        }
        int[] arr = new int[list.size()];
        for(int i = 0;i < list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;
    }



    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            LinkedList<Integer> map = new LinkedList<>();
            int tmp = queue.size();
            while(tmp != 0){
                TreeNode node = queue.poll();
                tmp--;

                if(list.size() % 2 == 0){
                    map.addLast(node.val);
                }else{
                    map.addFirst(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(map);
        }
        return list;
    }

    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure1(A.left, B) || isSubStructure1(A.right, B));
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSameTree(A,B) || isSubStructure(A.left,B) ||isSubStructure(A.right,B));
    }
    public boolean isSameTree(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return isSameTree(A.left,B.left) && isSameTree(A.right,B.right);
    }


    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }
    boolean recur1(TreeNode L, TreeNode R) {
        if(L == null && R == null) return true;
        if(L == null || R == null || L.val != R.val) return false;
        return recur1(L.left, R.right) && recur(L.right, R.left);
    }

    public boolean is(TreeNode x, TreeNode y){
        if(x == null && y == null){
            return true;
        }
        if(x == null || y == null || x.val != y.val){
            return false;
        }

        return is(x.left, y.right) && is(x.right, y.left);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if(nums[0] == nums[i] && nums[0] == 0 && i == nums.length -1){
                List<Integer> lis = new ArrayList<>();
                lis.add(0);
                lis.add(0);
                lis.add(0);
                list.add(lis);
            }
        }
        for(int i = 0;i < nums.length; i++) {
            for(int j = i+1;j < nums.length; j++){
                for(int k = j+1;k < nums.length; k++) {
                    if(nums[i] + nums[j]+ nums[k] == 0){
                        List<Integer> map = new ArrayList<>();
                        map.add(nums[i]);
                        map.add(nums[j]);
                        map.add(nums[k]);
                        list.add(map);
                    }
                }
            }
        }
        for(int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if(isSame(list.get(i),list.get(j))){
                    list.remove(list.get(j));
                }
            }
        }
        return list;
    }
    public boolean isSame(List<Integer> a, List<Integer> b) {
        int lena = a.size(),lenb = b.size();
        int[] arra = new int[lena], arrb = new int[lenb];
        for(int i = 0; i < lena; i++) {
            arra[i] = a.get(i);
            arrb[i] = b.get(i);
        }
        Arrays.sort(arra);
        Arrays.sort(arrb);
        for (int i = 0; i < lena; i++) {
            if(arra[i] != arrb[i]){
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String s) {
        if(s == null){
            return true;
        }
        StringBuilder str = new StringBuilder();
        for(int j = 0 ;j < s.length() ;j++){
            if(s.charAt(j) >= 0 && s.charAt(j) <= 9 || s.charAt(j) >= 65   &&
                    s.charAt(j)<= 90 || 97 <= s.charAt(j) && s.charAt(j) <= 122){
                str.append(s.charAt(j));
            }
        }
        String str1 = str.toString();
        for(int i = 0,j = str1.length() -1 ; i < j ; i++ ,j--) {
            if(Math.abs(str1.charAt(i) - str1.charAt(j)) == 32 || str1.charAt(i) == str1.charAt(j)) {

            }else{
                return false;
            }
        }
        return true;

    }


    //找k个最小值问题
    public int[] topK(int[] arr, int k) {

        int[] ar = new int[k];
        if(k == 0){
            return ar;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for(int j = k; j < arr.length; j++) {
            if(queue.peek() > arr[j]) {
                queue.poll();
                queue.offer(arr[j]);
            }
        }
        for(int i = 0; i < k; i++) {
            ar[i] = queue.poll();
        }
        return ar;
    }


    //航班记录问题
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n];
        for(int i = 0; i < bookings.length; i++) {
            for(int j = bookings[i][0]; j <= bookings[i][1];j++) {
                arr[j-1] = arr[j-1] + bookings[i][2];
            }
        }
        return arr;
    }
}
