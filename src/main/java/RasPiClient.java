import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.omg.IOP.Encoding;

import java.io.File;

/**
 * Created by Adi on 07-Jul-17.
 */
public class RasPiClient extends AbstractVerticle {

//    //shoot still frame
//    //convert to DataBuffer
//    //Send the data buffer to manager



    @Override
    public void start(Future<Void> startFuture) {

        boolean taken = false;
        //RPiCamera piCamera = init()
        //  if (piCamera != null){
        //    shootStill(piCamera);
        //   }

        vertx.eventBus().send(Constants.PHOTO_KEY, taken);
    }




    public void init() { // return RPiCamera
//        RPiCamera piCamera = null;
//        try {
//            String saveDir = "/home/pi/Desktop";
//            piCamera = new RPiCamera(saveDir);
//        } catch (FailedToRunRaspistillException e) {
//            e.printStackTrace();
//        }

    }
}

//    public boolean shootStill(RPiCamera piCamera) {
//        piCamera.setAWB(AWB.AUTO) // Change Automatic White Balance setting to automatic
//        setDRC(DRC.OFF)
//// Turn off Dynamic Range Compression
//                .setContrast(100)
//// Set maximum contrast
//                .setSharpness(100)
//// Set maximum sharpness
//                .setQuality(100)
//// Set maximum quality
//                .setTimeout(1000)
//// Wait 1 second to take the image
//                .turnOnPreview();// Turn on image preview
////                .setEncoding(Encoding.PNG); // Change encoding of images to PNG
//// Take a 650x650 still image and save it as "/home/pi/Desktop/A Cool Picture.png"
//        try {
//            File image = piCamera.takeStill(LISENCE_PALTE, 650, 650);
//            System.out.println("New PNG image saved to:\n\t" + image.getAbsolutePath());
//        }
//        catch (){
//          return false;
//        }
//          return true
//    }
