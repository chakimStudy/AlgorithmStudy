package B형대비_pro.SWEA_Pro_1_오타교정시스템;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

	static int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}
	static int mstrncmp(char[] a, char[] b, int len) {
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return 0;
	}

	static int mstrlen(char[] a) { //길이 반환
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}

	static void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}

	static void mstrncpy(char[] dest, char[] src, int len) {
		for (int i = 0; i < len; i++) {
			dest[i] = src[i];
		}
		dest[len] = '\0';
	}

	
	static boolean myCmp(char[] a, char[] b) {
		int i;
		int diffCnt = 0;
		for (i = 0; i < a.length; i++) {
			if (a[i] != b[i]){
				diffCnt++;
			}
		}
		return diffCnt == 1;
	}
	
	static boolean myCmp2(char[] a, char[] b) {
		int i;
		int j = 0;
		
		int cnt = 0;
		for (i = 0; i < a.length; i++) {
			if (a[i] != b[j]) {
				j++;
				cnt++;
				if (cnt == 2) return false;
			}
			j++;
		}
		return cnt == 1;
	}
	static char[] reverse(char[] a) {
		char[] result = new char[mstrlen(a) + 1];
		int j = 0;
		for (int i = mstrlen(a)-1; i >= 0; i--) {
			result[j++] = a[i];
		}
		result[j] = '\0';
		return result;
	}
	
	public static void main(String[] args) {
		
		splitTest();
		char[] str1 = {'l', 'o', 'c', 'a', 'l', '\0', '\0', '\0', '\0'};
		char[] str2 = {'l', 'o', 'c', 'a', 'y', 'l', '\0', 'e', '\0'};
		
//		System.out.println(mstrcmp(str1, str2));
		
		int len1 = mstrlen(str1);
		int len2 = mstrlen(str2);
		System.out.println(len1 + " " + len2);
		
		if (len1 == len2) {
			//치환
			
//			int ret1 = myCmp(str1, str2);
//			System.out.println(ret1);
//
//			System.out.println(str1);
//			System.out.println(reverse(str2));
//			int ret2 = myCmp(reverse(str1), reverse(str2));
			System.out.println(myCmp(str1, str2)); //치환조건
		}
		else {
			//1개 더 많음 - 추가, 삭제
			
			char[] shortStr = null;
			char[] longStr = null;
			
			if (len1 < len2) {
				shortStr = str1;
				longStr = str2;
			}
			else {
				shortStr = str2;
				longStr = str1;
			}

//			int ret1 = myCmp(shortStr, longStr);
//			System.out.println(ret1);

			System.out.println(reverse(shortStr));
			System.out.println(reverse(longStr));
			boolean ret2 = myCmp2(shortStr, longStr);
//			boolean ret3 = myCmp2(reverse(shortStr), reverse(longStr));
			System.out.println(ret2); //추가, 삭제 조건
			
		}
		
//		System.out.println("maximum z".equals("maximum z"));
	}
	
	
	private static void splitTest() {
		char[] str2 = {'l', 'o', 'c', 'a', 'y', 'l', '\0', 'e', '\0'};
		
		List<char[]> split = split(str2);
		
		for (char[] spl : split) {
			System.out.println(Arrays.toString(spl));
		}
	}
	private static List<char[]> split(char[] arr) {
		List<char[]> result = new ArrayList<>();
		
		char[] spl = new char[arr.length];
		int splI = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '\0' && splI > 0) {
				result.add(spl);
				spl = new char[arr.length];
				splI = 0;
			}
			else if (arr[i] != '\0') {
				spl[splI] = arr[i];
				splI++;
				
			}
		}
		
		return result;
	}
}
