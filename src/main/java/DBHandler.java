import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Adi on 08-Jul-17.
 */
public class DBHandler {

    //This is an array list because we dont know the size of the csv
    private ArrayList<String> licensePlates = new ArrayList<>();

    public DBHandler(){

        URL url;

        try {
            // get URL content
            String a="https://s3.eu-central-1.amazonaws.com/myiotbucket5/plates+list.csv";
            url = new URL(a);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            //Read the next line and validate that its not null
            while ((inputLine = bufferedReader.readLine()) != null) {
//                System.out.println(inputLine);
                licensePlates.add(inputLine);
            }
            bufferedReader.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isExist(String licensePlate){

        for (int i = 0; i < licensePlates.size(); i++) {
            if (licensePlate.equals(licensePlates.get(i))){
                return true;
            }
        }

        return false;
    }
}
