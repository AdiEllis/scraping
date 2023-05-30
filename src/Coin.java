import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Coin {
    private int id;
    private String name;
    private String rate;
    private String date;

    public Coin(int id, String name, String rate, String date) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.date = date;
    }
    public Coin(String name, String rate, String date) {
        this.name = name;
        this.rate = rate;
        this.date = date;
    }

    public void save() throws Exception {
            List<Coin> allCoins = getAll();
            try {
                Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement stmt = null;
                boolean coinUpdated = false;
                for (Coin coin : allCoins) {
                    if (coin.name.equals(this.name)) {
                        stmt = con.prepareStatement("update coins set rate=?, update_date=? where id=?");
                        stmt.setString(1, this.getRate());
                        stmt.setString(2, this.getDate());
                        stmt.setInt(3, coin.getId());
                        coinUpdated = true;
                    }
                }
                if (!coinUpdated) {
                    stmt = con.prepareStatement("insert into coins (coin_name,rate,update_date) values(?,?,?)");
                    stmt.setString(1, this.getName());
                    stmt.setString(2, this.getRate());
                    stmt.setString(3, this.getDate());
                }
                int j = stmt.executeUpdate();
                System.out.println(j + (coinUpdated ? " record updated" : " record inserted"));
            } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Coin> getAll() throws Exception {
        try(Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select id,coin_name,rate,update_date from coins");
            ResultSet resultSet = stmt.executeQuery();
            List<Coin> allCoins = new ArrayList<>();
            while (resultSet.next()) {
                Coin coin = new Coin(resultSet.getInt("id"), resultSet.getString("coin_name"), resultSet.getString("rate"), resultSet.getString("update_date"));
                allCoins.add(coin);
            }
            System.out.println("got all records");
            return allCoins;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer,String> getAllCoinsName() {
        try(Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select coin_name from coins");
            ResultSet resultSet = stmt.executeQuery();
            Map<Integer,String> allCoinNames = new HashMap<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String coinName = resultSet.getString("coin_name");
                allCoinNames.put(id,coinName);
            }
            System.out.println("got all coin names");
            return allCoinNames;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Coin{" +
                "name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
