package src;

public class task4 {
    public static void main(String[] args) {
        String str1 = args[0];
        String str2 = args[1];

        if (stringAreSame(str1, str2)) {
            System.out.println("ОК");
        } else System.out.println("КО");


    }

    public static boolean stringAreSame(String str1, String str2) {
        int j = 0;

        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) != '*') {
                boolean weFindSymbol = false;
                for (int k = j; k < str1.length(); k++) {
                    if (str1.charAt(k) == str2.charAt(i)) {
                        weFindSymbol = true;
                        int indexStr2 = i++;
                        int indexStr1 = k++;
                        while (str2.length()!=indexStr2 && str2.charAt(indexStr2) != '*' ) {
                            if (str2.charAt(indexStr2) == str1.charAt(indexStr1)) {
                                indexStr1++;
                                indexStr2++;
                                weFindSymbol = true;
                            } else {
                                weFindSymbol = false;
                                break;
                            }
                        }
                        if (weFindSymbol){
                            j = indexStr1--;
                            i = indexStr2--;
                            break;
                        }
                    }
                }
                if (!weFindSymbol){
                    return false;
                }
            }
        }
        return true;
    }
}
