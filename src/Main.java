import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = null;
        timer.schedule(new InsertCoins(),0,10000);

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
