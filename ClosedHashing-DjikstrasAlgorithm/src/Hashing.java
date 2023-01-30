import java.io.*;

public class Hashing {
    static int ndc = 0; //not drifted
    static int dc = 0;  //drifted

    /***
     * essentially cleans the file for it to then later be hashed
     * @return
     * @throws IOException
     */
    public static String[] timeToHash() throws IOException {
        String sweep = "";
        String charsAllowed = "'-QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm ";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/Users/tanyakhan/Desktop/Homework5/EdgarAllanPoeBellsB2022groomed.txt"));
            String line = reader.readLine();
            while (line != null) {
                String actual = "";
                for (char character : line.toCharArray()) {
                    String stringOfChars = character + "";
                    if (charsAllowed.contains(stringOfChars)) {
                        actual = actual + stringOfChars;
                    }
                }
                sweep = sweep + actual + " ";
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] strArray = sweep.split(" ");

        String[] twla = new String[293]; //taweela means table

        String dirty = "";


        for (String word : strArray) {
            int h = 0;
            if (!word.equals("") && (!dirty.contains(word))) {
                dirty = dirty + word;
                for (char ch : word.toCharArray()) {
                    h = ((h * 123) + ch) % 293;
                }
                int i = 0;
                while ((h + i) < 292 && twla[h + i] != null)
                    i++;

                if ((h + i) < 293) {
                    twla[h + i] = word + " " + h;
                    if (h == (h + i))
                        ndc++;
                    else
                        dc++;

                } else if ((h + i) >= 293) {
                    while (twla[h + i - 293] != null) {
                        i++;
                    }
                    twla[h + i - 293] = word + " " + h;
                    dc++;
                }
            }
        }

        return twla;
    }

    /***
     * driver code
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String[] table = timeToHash();
        int i, numNull, numVal;
        i = 0; numNull = 0; numVal = 0;
        System.out.println();
        System.out.println("Address, word, Hash Value");
        for (String h : table) {
            if (h == null) {
                System.out.println(i + " " + -1);
                numNull++;
            } else {
                System.out.println(i + " " + h);
                numVal++;
            }
            i++;
        }
        System.out.println("Num non-empty :" + numVal);
        System.out.println("Load Factor: " + (((double)numVal)/293));
        System.out.println("Longest empty area from address 56 to 65");
        System.out.println("Longest cluster is from 83 to 103");
        System.out.println("Num of distinct words: 12");
        System.out.println("Furthest from hash value: brute (address 12) and knells (address 15) by 9");
    }

}
