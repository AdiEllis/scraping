import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.TimerTask;

public class InsertCoins extends TimerTask {
    @Override
    public void run() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://il.investing.com/currencies/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements table = doc.select("table tbody");
        for (int i = 0; i < 5; i++) {
            Coin coinToAdd = new Coin(
                    table.select("tbody tr").get(i).select("tr td").get(1).text(),
                    table.select("tbody tr").get(i).select("tr td").get(3).text(),
                    table.select("tbody tr").get(i).select("tr td").get(8).text());
            try {
                coinToAdd.save();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(coinToAdd);
        }
    }
}
