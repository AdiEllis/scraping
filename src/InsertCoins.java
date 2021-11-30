import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;

public class InsertCoins extends TimerTask {
    @Override
    public void run() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://coinmarketcap.com/all/views/all/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements table = doc.select("table tbody");
        for (int i = 0; i < 2; i++) {
            Coin coinToAdd = new Coin(table.select("tbody tr").get(i).select("tr td").get(1).text(),
                    table.select("tbody tr").get(i).select("tr td").get(2).text(),
                    table.select("tbody tr").get(i).select("tr td").get(4).text());
            try {
                coinToAdd.save();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            System.out.println(coinToAdd.getName());
        }
    }
}
