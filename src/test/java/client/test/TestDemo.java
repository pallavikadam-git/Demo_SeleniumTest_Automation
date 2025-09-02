package client.test;

public class TestDemo {
    public static void main(String[] args) {
        String str = "abcab";
        for(int i = 0; i<str.length(); i++){
            int count = 0;
            for(int j = i+1; j<str.length(); j++){
                if(str.charAt(i)==str.charAt(j)){
                    count++;
                    str.replace(str.substring(j, j+1), "");
//                    j = j-1;
                }
            }
            System.out.println(str.charAt(i) +""+ count);
        }
    }
}
