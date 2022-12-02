public class Solution {

    public String addBinary(String a, String b) {
        int aLen = a.length()-1;
        int bLen = b.length()-1;
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        return aLen > bLen ? addBinary(aChar,bChar,aLen,0) : addBinary(bChar,aChar,bLen,0);
    }

    private String addBinary(char[] a,char[] b,int index,int carry) {
        if (index < 0) {
            return dilatation(a,carry);
        }
        int A = a[index] - 48;
        int B = index >= (a.length-b.length) ? b[index-(a.length-b.length)] - 48 : 0;
        a[index--] = (char) (A ^ B ^ carry);
        return addBinary(a,b,index,(A & B) == 0 ? (A ^ B) & carry : A & B | carry);
    }

    private String dilatation(char[] a,int carry) {
        String tmp = carry == 1 ? "1" : "";
        for (int i = 0;i < a.length;i++) {
            tmp += (int) a[i];
        }
        return tmp;
    }
}
