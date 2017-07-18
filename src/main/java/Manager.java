import java.io.File;

public class Manager {

    private DBHandler dbHandler;
    private RasPiCamera rasPiCamera;
    private ImageProcessor imageProcessor;

    /**
     * Ctor, inits all its class memnbers
     */
    public Manager() {
        this.dbHandler = new DBHandler();
        this.rasPiCamera = new RasPiCamera();
        this.imageProcessor = new ImageProcessor();
    }

    /**
     * Starts the flow
     */
    public void start() {

        boolean isValid = false;

        //Takes photo
//        File fileFromCamera = rasPiCamera.takePhoto();
        //Fake file
//        File fakeFile = new File("C:\\Users\\Adi\\Desktop\\temp\\5.jpg");
        File fakeFile = new File("C:\\Users\\Adi\\Desktop\\temp\\4.jpg");
        if(fakeFile != null){
            //Fetch lincense plate number from image
            String licensePlateToCheck = imageProcessor.fetchLicensePlateNumber(fakeFile);
            if(licensePlateToCheck != null){
                //validate if the number exists in the csv file
                isValid = dbHandler.isExist(licensePlateToCheck);
            }

        }

        //print if valid
        System.out.println("License " + (isValid ? "is" : "isn't") + " valid!");

    }

}
