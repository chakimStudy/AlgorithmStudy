package B형대비_pro.SWEA_Pro_1_오타교정시스템;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class UserSolution {

	// The below commented methods are for your reference. If you want 
	// to use it, uncomment these methods.
	//
	int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}
	//
	int mstrncmp(char[] a, char[] b, int len) {
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return 0;
	}

	int mstrlen(char[] a) { //길이 반환
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}

	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}

	void mstrncpy(char[] dest, char[] src, int len) {
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
		for (i = 0; i < a.length-1; i++) {
			if (a[i] != b[j]) {
				j++;
				cnt++;
				if (cnt == 2) return false;
			}
			j++;
		}
		return cnt == 1;
	}
	private List<char[]> split(char[] arr) {
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
	//1. timestamp 기록 저장
	static class History{
		int time;
		char[] word;
		public History(int time, char[] word) {
			super();
			this.time = time;
			this.word = word;
		}

	}
	static History[] history;

	//2. 후보 저장
	static class CorrectWord{
		Set<Integer> cnt = new HashSet<>();
		char[] word;
		public CorrectWord(int mId, char[] word) {
			super();
			cnt.add(mId);
			this.word = word;
		}
		@Override
		public String toString() {
			return "CorrectWord [cnt=" + cnt + ", word=" + Arrays.toString(word) + "]";
		}



	}
	//하나의 오타에 대해 여러 개의 정타가 나올 수 있다.
	//오타 - 정타배열
	Map<String, List<CorrectWord>> candidate;

	//3. 교정 사전 저장
	//오타 - 정타배열(크기5)
	Map<String, List<char[]>> directory;



	void init(int n) {
		//n은 사용자 수
		history = new History[n+1];
		candidate = new HashMap<>();
		directory = new HashMap<>();

		try {
			bs = new BufferedOutputStream(new FileOutputStream("./output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static char[] error, correct;
	static BufferedOutputStream bs;

	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) throws IOException {
		//init
		error = new char[11];
		correct = new char[11];
		//		for (int i = 0; i < 5; i++) {
		//			Arrays.fill(correctWord[i], '\0');
		//		}
		//		correct = searchWord;

		int j = 0;

		mstrcpy(correct, searchWord);

		bs.write(("\n$$$ " + searchTimestamp).getBytes());
		bs.write(("search : " + Arrays.toString(searchWord) + String.valueOf(searchWord)).getBytes());


		List<char[]> dirLs = directory.getOrDefault((String.valueOf(correct)).trim(), null);

		if (dirLs != null) {
			//			bs.write(("inDirectory");
			for (char[] arr : dirLs) {
				mstrcpy(correctWord[j], arr);
				bs.write(("ans " + String.valueOf(arr)).getBytes());
				j++;
			}
		}

		//이전 기록이 있으면 사전에 등록하기
		if (history[mId] != null) {

			History pre = history[mId];
			history[mId] = new History(searchTimestamp, correct);
			int diff = searchTimestamp - pre.time;
			//					bs.write((diff);
			//					if (diff > 10) {
			//						//방금 들어온 기록 저장하고 멈춤
			////						history[mId].add(new History(searchTimestamp, searchWord)); - 밑에 중복
			//						break;
			//					}

			//이전 - error, 현재 - correct
			//				error = pre.word;
			mstrcpy(error, pre.word);
			//				bs.write(("pre : " + String.valueOf(pre.word)).getBytes());
			//				bs.write(("accepted: " + isAccepted(error, correct)+ "\n").getBytes() );

			if (diff <= 10 && isAccepted(error, correct)) { //등록될 수 있는 경우
				int idx = inCandidate();
				//						bs.write((idx);
				if (idx != -1) {
					candidate.get(String.valueOf(error)).get(idx).cnt.add(mId);
					int cnt = candidate.get(String.valueOf(error)).get(idx).cnt.size();
					if (cnt == 3) {
						List<char[]> ls = directory.getOrDefault(String.valueOf(error).trim(), new ArrayList<>());
						
						boolean exist = false;
						for (char[] arr : ls) {
							if (mstrcmp(arr, correct) == 0) {
								exist = true;
								break;
							}
						}
						if (!exist) {
							ls.add(Arrays.copyOf(correct, correct.length));
							directory.put(String.valueOf(error).trim(), ls);	
						}
						
					}
				}
				else {
					List<CorrectWord> ls = candidate.getOrDefault(String.valueOf(error), new ArrayList<>());
					ls.add(new CorrectWord(mId, correct));
					candidate.put(String.valueOf(error), ls);
				}
			}

		}
		else {
			history[mId] = new History(searchTimestamp, correct);
		}


		//			bs.write((searchTimestamp));
		//			bs.write(("\ncandidate  ").getBytes());
		//			candidate.forEach((key, value) ->{
		//				try {
		//					bs.write((key + " " + value).getBytes());
		//				} catch (IOException e) {
		//					// TODO Auto-generated catch block
		//					e.printStackTrace();
		//				}
		//			});
		//
		//			bs.write(("\ndirectory  ").getBytes());
		//			for (String key : directory.keySet()) {
		//				bs.write((key + ": ").getBytes());
		//				for (char[] arr :  directory.get(key)) {
		//					bs.write((String.valueOf(arr) + " ").getBytes());
		//				}
		//			}
		//			bs.write(("\n+++++").getBytes());
		//			bs.write(("################################  "+j + "\n").getBytes());
		
		
		return j; //////
	}


	private boolean isAccepted(char[] str1, char[] str2) {
		int len1 = mstrlen(str1);
		int len2 = mstrlen(str2);
		//		System.out.println(len1 + " " + len2);

		if (len1 == len2) {
			//치환

			return myCmp(str1, str2); //치환조건
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

			boolean ret2 = myCmp2(shortStr, longStr);
			//			boolean ret3 = myCmp2(reverse(shortStr), reverse(longStr));
			return ret2; //추가, 삭제 조건

		}

	}

	private int inCandidate() {
		List<CorrectWord> ls = candidate.getOrDefault(String.valueOf(error), new ArrayList<>());

		for (int i = 0; i < ls.size(); i++) {
			int ret = mstrcmp(ls.get(i).word, correct);
			if (ret == 0) return i;
		}
		return -1;
	}
	public void print() throws IOException {
				bs.write(("\ndirectory  ").getBytes());
				for (String key : directory.keySet()) {
					bs.write((key + ": ").getBytes());
					for (char[] arr :  directory.get(key)) {
						bs.write((String.valueOf(arr) + " ").getBytes());
					}
					bs.write('\n');
				}
				bs.write(("\n+++++").getBytes());
				bs.write("################################ \n".getBytes());
	}
}
