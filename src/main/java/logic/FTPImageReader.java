package logic;

import defines.Defines;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.omg.CORBA.MARSHAL;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class FTPImageReader {
    FTPClient ftpClient;

    public void openFTPConnection() throws IOException {
        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("data.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = prop.getProperty(Defines.FTPDATA.USER);
        String password = prop.getProperty(Defines.FTPDATA.PASSWORD);
        String url = prop.getProperty(Defines.FTPDATA.HOST);
        int port = Integer.parseInt(prop.getProperty(Defines.FTPDATA.PORT));

        ftpClient = new FTPClient();
        ftpClient.connect(url, port);
        ftpClient.login(username, password);

    }

    public ArrayList<String> checkImages(String material, String motiv, String modell) throws IOException {
        System.out.println("____________________________");
        System.out.println(material);
        System.out.println(modell);
        System.out.println(motiv);
        System.out.println("____________________________");
        material = replaceUmlaute(material);
        motiv = replaceUmlaute(motiv);
        modell = replaceUmlaute(modell);
        final String SLASH = "/";

          ArrayList<String> result = getArrayListWithURLs(material, motiv, modell);
          return result;

/*
        } else {
            if (ftpClient.changeWorkingDirectory(destinationWithModell)) {
                System.out.println("Destination with modell Exists");
                FTPFile[] filess = ftpClient.listFiles();
                ArrayList<String> urls = new ArrayList<>();
                for (int i = 0; i < filess.length; i++) {
                    FTPFile f = filess[i];
                    if (f.getName().toLowerCase().contains(".jpg")) {
                        String currentURL = (PREFIX + destinationWithModell + SLASH + f.getName());
                        currentURL = returnReplacedURLs(currentURL);
                        urls.add(currentURL);

                    }

                }
                return urls;

            } else if (ftpClient.changeWorkingDirectory(destinationWithoutModell)) {
                System.out.println("Destination without modell exists");
                FTPFile[] filess = ftpClient.listFiles();
                ArrayList<String> urls = new ArrayList<>();
                for (int i = 0; i < filess.length; i++) {
                    FTPFile f = filess[i];
                    if (f.getName().toLowerCase().contains(".jpg")) {
                        String currentURL = PREFIX + destinationWithoutModell + SLASH + f.getName();
                        currentURL = returnReplacedURLs(currentURL);
                        urls.add(currentURL);

                    }

                }

                return urls;

            } else {
                System.out.println("Could not find images in database. Please check the correct spelling of material, motive and model");
                ArrayList<String> urls = new ArrayList<>();
                urls.add("");
                return urls;
            }
        }
*/
    }

    public void closeFTPConnection() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
    }

    private String replaceUmlaute(String s) {
        s = s.replace("ä", "Ã¤");
        s = s.replace("ö", "Ã¶");
        s = s.replace("ü", "Ã¼");
        s = s.replace("ß", "ÃŸ");
        return s;

    }

    private String returnReplacedURLs(String s) {
        s = s.replace("Ã¤", "ä");
        s = s.replace("Ã¶", "ö");
        s = s.replace("Ã¼", "ü");
        s = s.replace("ÃŸ", "ß");
        return s;

    }

    public ArrayList<String> getArrayListWithURLs(String material, String motiv, String model) throws IOException {
        final String PREFIX = "https://hlworld.de";
        final String SLASH = "/";
        final String FTP_PREFIX = "AEG";

        String directoryWithoutMotivAndModell =SLASH+ FTP_PREFIX + SLASH + material+SLASH;
        String directoryWithoutModell = directoryWithoutMotivAndModell + motiv + SLASH;
        String directoryWithModell = directoryWithoutModell + model + SLASH;


        System.out.println("WITH MODEL " + directoryWithModell);
        System.out.println("WITHOUT MODEL " + directoryWithoutModell);
        System.out.println("WITHOUT MODEL AND MOTIVE " + directoryWithoutMotivAndModell);
        String fullDirectory = null;
        ArrayList<String> urls = new ArrayList<>();
        if(ftpClient.changeWorkingDirectory(directoryWithModell)){
           fullDirectory = directoryWithModell;
        }
        else if( ftpClient.changeWorkingDirectory(directoryWithoutModell)){
            fullDirectory = directoryWithModell;
        }
        else if( ftpClient.changeWorkingDirectory(directoryWithoutMotivAndModell)){
            fullDirectory = directoryWithoutMotivAndModell;
        }

        if(fullDirectory == null || fullDirectory.isEmpty()){
            
            System.out.println("Could not find full directory");
            urls.add("Links für Material=" + material +" Motiv=" + motiv + " Modell=" + model + "nicht gefunden");
            urls.add("Bitte manuell nachtragen");
            return urls;

        }else {
            System.out.println("Full directory = " + fullDirectory);
        }

            FTPFile[] files = ftpClient.listFiles();

            for (int i = 0; i < files.length; i++) {
                FTPFile f = files[i];
                if (f.getName().toLowerCase().contains(".jpg")) {
                    String currentURL = PREFIX + fullDirectory + SLASH + f.getName();
                    currentURL = returnReplacedURLs(currentURL);
                    urls.add(currentURL);

                }
            }

        return urls;
    }
}
