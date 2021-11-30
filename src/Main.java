import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Document doc = Jsoup.connect("https://coinmarketcap.com/all/views/all/").get();
        Elements table = doc.select("table tbody");
        for (int i = 0; i < 10; i++) {
            Coin coinToAdd = new Coin(table.select("tbody tr").get(i).select("tr td").get(1).text(),
                    table.select("tbody tr").get(i).select("tr td").get(2).text(),
                    table.select("tbody tr").get(i).select("tr td").get(4).text());
            coinToAdd.save();
            System.out.println(coinToAdd.getName());
        }


//        delete table:
//        Connection con = null;
//        try {
//            con = DatabaseConnection.getInstance().getConnection();
//            PreparedStatement stmt = con.prepareStatement("truncate table cryptocoin");
//            int j = stmt.executeUpdate();
//            System.out.println(j + " records inserted");
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//        }
    }

}
