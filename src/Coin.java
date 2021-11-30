import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Coin {
    private String name;
    private String symbol;
    private String price;

    public Coin(String name, String symbol, String price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }

    public void save() throws SQLException {
        Connection con = null;
        try {
            con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into cryptocoin (name,symbol,price) values(?,?,?)");
            stmt.setString(1, this.getName());
            stmt.setString(2, this.getSymbol());
            stmt.setString(3, this.getPrice());

            int j = stmt.executeUpdate();
            System.out.println(j + " records inserted");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
