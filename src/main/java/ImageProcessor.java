import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by Adi on 08-Jul-17.
 */
public class ImageProcessor {

    public String fetchLicensePlateNumber(File image){
        String result = null;
        try {

            String line;
            Process p = Runtime.getRuntime().exec("cmd /c  openalpr_64\\alpr.exe " + image.getAbsolutePath());

            BufferedReader bufferedReaderInput = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));

            BufferedReader bufferedReaderError = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));

            int lineCounter = 0;

            String licensePlateInput = null;

            while ((line = bufferedReaderInput.readLine()) != null) {

                if(lineCounter == 1){
                    licensePlateInput = line;
                    break;
                }
                lineCounter++;
            }
            bufferedReaderInput.close();

            if(lineCounter == 1){
                System.out.println("result: "+ licensePlateInput);
                String [] result1 = licensePlateInput.split("confidence");
                String [] result2 = result1[0].split("-");
                System.out.println(result2[1].trim());
                result = result2[1].trim();
            }
            else{
                System.out.println("No license plate detected!");
                result = null;
            }


            while ((line = bufferedReaderError.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReaderError.close();

            p.waitFor();
            System.out.println("Done.");
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        return result;
    }
}
