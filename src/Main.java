import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Document doc = Jsoup.connect("https://coinmarketcap.com/all/views/all/").get();
        Elements table = doc.select("table tbody");
        Connection con = null;
        for (int i = 0; i < 10; i++) {
            try {
                con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement stmt = con.prepareStatement("insert into cryptocoin (name,symbol,price) values(?,?,?)");
                stmt.setString(1, table.select("tbody tr").get(i).select("tr td").get(1).text());
                stmt.setString(2, table.select("tbody tr").get(i).select("tr td").get(2).text());
                stmt.setString(3, table.select("tbody tr").get(i).select("tr td").get(4).text());

                int j = stmt.executeUpdate();
                System.out.println(j + " records inserted");
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (con != null) {
                    con.close();
                }
            }
            System.out.println(table.select("tbody tr").get(i).select("tr td").get(2).text());
        }
    }

}
