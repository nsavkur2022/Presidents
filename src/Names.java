import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Names extends ArrayList<String> {

    public Names() throws IOException {
        URL whiteHouse = new URL("https://www.whitehouse.gov/about-the-white-house/presidents/");
        BufferedReader site = new BufferedReader(new InputStreamReader(whiteHouse.openConnection().getInputStream()));

        String inputLine;
        StringBuilder page = new StringBuilder();
        while ((inputLine = site.readLine()) != null)
            page.append(inputLine);

        String[] arr = page.substring(page.indexOf("<ol>"), page.indexOf("</ol>")).split("</li>");
        for (String str : arr)
            add(str.replaceAll("<[^>]*>", ""));
        remove(23);
        remove(size() - 1);
    }

    public static void main(String[] args) {
        try {
            System.out.println(new Names());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}