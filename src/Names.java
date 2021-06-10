import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Names extends ArrayList<String> {

    public Names() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("namelist.txt"));
        while(reader.hasNextLine())
            add(reader.nextLine());
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new Names());
    }
}