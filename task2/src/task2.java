import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task2 {
    public static void main(String[] args) {
        String path = args[0];
        File file = new File(path);
        String buffer = null;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file));){
            buffer = fileReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        var splitting = buffer.split("}");
        splitting[0] = splitting[0].substring(13);
        splitting[1] = splitting[1].substring(14);

        splitting[0] = removeCharFromString(splitting[0]);
        splitting[1] = removeCharFromString(splitting[1]);

        List<Integer> integers1 = new ArrayList<>();
        List<Integer> integers2 = new ArrayList<>();

        coordinates(splitting[0], integers1);
        coordinates(splitting[1], integers2);

        double [] dis1 = distances(integers1);
        double [] dis2 = distances(integers2);

        checkSimilarity(dis1, dis2);

    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String removeCharFromString (String s){
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == ']' || s.charAt(i) == ','){
                s = removeCharAt(s, i);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ','){
                s = removeCharAt(s, i);
            }
        }
        return s;
    }

    public static void coordinates (String split, List integers) {

        String str = split;

        Pattern pattern = Pattern.compile("\\d+\\S?\\d*");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            String s = matcher.group(0);
            if (s.replaceAll("\\D", "").length() == s.length()) {
                integers.add(Integer.parseInt(s));
            }
        }
    }

    public static double[] distances (List<Integer> list) {
        double[]dis = new double[3];

        dis[0] =Math.sqrt(Math.pow((list.get(0) - list.get(3)),2) + Math.pow((list.get(1) - list.get(4)),2) + Math.pow((list.get(2) - list.get(5)),2));
        dis[1] =Math.sqrt(Math.pow((list.get(0) - list.get(6)),2) + Math.pow((list.get(1) - list.get(7)),2) + Math.pow((list.get(2) - list.get(8)),2));
        dis[2] =Math.sqrt(Math.pow((list.get(3) - list.get(6)),2) + Math.pow((list.get(4) - list.get(7)),2) + Math.pow((list.get(5) - list.get(8)),2));

        Arrays.sort(dis);

        return dis;
    }

    public static void checkSimilarity (double[] dis1, double[] dis2){
        final double threshold = 0.0001;
        if ((Math.abs(dis1[0]/dis2[0] - dis1[1]/dis2[1]) < threshold) && (Math.abs(dis1[0]/dis2[0] - dis1[2]/dis2[2]) < threshold)){
            System.out.println("Треугольники подобны");
        } else {
            System.out.println("Треугольники не подобны");
        }
    }

    }

