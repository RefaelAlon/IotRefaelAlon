import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ImageProcessor {

    public String fetchLicensePlateNumber(File image){
        String result = null;
        try {

            String line;
            //Open cmd and return results to p
            Process p = Runtime.getRuntime().exec("cmd /c  openalpr_64\\alpr.exe " + image.getAbsolutePath());

            BufferedReader bufferedReaderInput = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));

            BufferedReader bufferedReaderError = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));

            int lineCounter = 0;

            String licensePlateInput = null;
            //Reading results from p bufferReaderInput devided by lines
            while ((line = bufferedReaderInput.readLine()) != null) {

                //we only care about the first line in the result list
                if(lineCounter == 1){
                    licensePlateInput = line;
                    //Break out of loop
                    break;
                }
                lineCounter++;
            }
            bufferedReaderInput.close();

            //Only if we have one result
            if(lineCounter == 1){
                //Extract the licens pale number out of the result line (pattern: - CZTHEDA	 confidence: 93.1397)
                String [] result1 = licensePlateInput.split("confidence");
                String [] result2 = result1[0].split("-");
                System.out.println("*** License plate number is: " + result2[1].trim() + " ***");
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
            System.out.println("*** Image processing is done. ***");
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        return result;
    }
}
