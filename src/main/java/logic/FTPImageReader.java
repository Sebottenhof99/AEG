package logic;

import defines.Defines;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class FTPImageReader {

    public ArrayList<String> checkImages(String material, String motiv, String modell) throws IOException {
        material = replaceUmlaute(material);
        motiv = replaceUmlaute(motiv);
        modell= replaceUmlaute(modell);
        final String PREFIX = "https://hlworld.de/";

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

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(url, port);
        ftpClient.login(username, password);

        final String SLASH = "/";

        String destinationWithoutModell = SLASH+"AEG"+SLASH+material+SLASH+motiv;
        String destinationWithModell= destinationWithoutModell+SLASH+modell;

        if(ftpClient.changeWorkingDirectory(destinationWithModell)){
            System.out.println("Destination with modell Exists");
            FTPFile[] filess = ftpClient.listFiles();
            ArrayList<String> urls = new ArrayList<>();
            for (int i = 0; i < filess.length; i++) {
                FTPFile f = filess[i];
                if(f.getName().toLowerCase().contains(".jpg")) {
                    String currentURL = returnReplacedURLs(PREFIX + destinationWithModell+SLASH+f.getName());
                    urls.add(currentURL);

                }

            }
            ftpClient.logout();
            ftpClient.disconnect();
            return urls;

        }else if(ftpClient.changeWorkingDirectory(destinationWithoutModell)){
            System.out.println("Destination without modell exists");
            FTPFile[] filess = ftpClient.listFiles();
            ArrayList<String> urls = new ArrayList<>();
            for (int i = 0; i < filess.length; i++) {
                FTPFile f = filess[i];
                if(f.getName().toLowerCase().contains(".jpg")) {
                    String currentURL = PREFIX + destinationWithoutModell+SLASH+f.getName();
                    currentURL = returnReplacedURLs(currentURL);
                    urls.add(currentURL);

                }

            }
            ftpClient.logout();
            ftpClient.disconnect();
            return urls;

        }
        else{
            System.out.println("Could not find images in database. Please check the correct spelling of material, motive and model");
            ftpClient.logout();
            ftpClient.disconnect();
            return null;
        }
    }

    private String replaceUmlaute(String s){
        s = s.replace("ä", "Ã¤");
        s = s.replace("ö", "Ã¶");
        s = s.replace("ü", "Ã¼");
        s = s.replace("ß", "ÃŸ");
        return s;

    }

    private String returnReplacedURLs(String s){
        s = s.replace("Ã¤", "ä");
        s = s.replace("Ã¶", "ö");
        s = s.replace("Ã¼", "ü");
        s = s.replace("ÃŸ", "ß");
        return s;

    }
}
