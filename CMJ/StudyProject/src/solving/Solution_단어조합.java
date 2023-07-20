package solving;

public class Solution_단어조합 {


	public static void main(String[] args) {
		
		String[] emails = new String[] {"ascjaskchs@ascasc.com", "aclach@lasjc.net", "asckhasc.asjcajslc@.com"};
		int N = emails.length;
		int answer = 0;
		for(int i = 0; i < N; i++) {
			String nowEmail = emails[i];
			int emailLength = nowEmail.length();
			int j = 0;
			boolean check = false;
			for(; j < emailLength; j++) {
				if((nowEmail.charAt(j) >= 'a' && nowEmail.charAt(j) <= 'z') || nowEmail.charAt(j) == '.') {
					continue;
				}
				else if(nowEmail.charAt(j) == '@') {
					j++;
					break;
				}
				else {
					check = true;
				}
			}
			if(check) continue;
			check = false;
			for(; j < emailLength; j++) {
				if(nowEmail.charAt(j) >= 'a' && nowEmail.charAt(j) <= 'z') {
					continue;
				}
				else if(nowEmail.charAt(j) == '.') {

					j++;
					break;
				}
				else {
					check = true;
				}
			}
			if(check) continue;
			String temp = "";
			for(; j < emailLength; j++) {
				temp += nowEmail.charAt(j);
			}
			if(temp.equals("com") || temp.equals("net") ||temp.equals("org")) {
				answer ++;
			}
		}
		System.out.println(answer);
	}
}
