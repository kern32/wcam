package service;


import dao.AccessLogDao;
import entity.Image;
import entity.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kernel32 on 02.06.2017.
 */
public class LocationManager {

    public static String getRouterIPAddress() {
            String ip = "";
            FileReader reader = null;
            try {
                reader = new FileReader("/var/www/kernel32/data/ip.txt");
                int c;
                while ((c = reader.read()) != -1) {
                    ip = ip + (char) c;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return ip;
        }

    public static void saveClientCurrentLocation(HttpServletRequest req) throws IOException {
        String ipAddress = req.getRemoteAddr();
        File file = new File("/var/www/kernel32/data/GeoLiteCity.dat");
        LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
        Location locationServices = lookup.getLocation(ipAddress);

        Log item = new Log();
        item.setOs(getOperatingSystem(req));
        item.setBrowser(getBrowser(req));
        item.setCity(locationServices.city);
        item.setCountry(locationServices.countryName);
        item.setIp(ipAddress);
        item.setTime(getCurrentTimeStamp());

        AccessLogDao.addEntryIp(item);
    }

    private static String getBrowser(HttpServletRequest request) {
        String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();
        String browser = "";
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        return browser;
    }

    private static String getOperatingSystem(HttpServletRequest request) {
        String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0 )
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)
        {
            os = "Android";
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        return os;
    }

    public static List<Log> getClientIpAddress() {
        return AccessLogDao.getLastAccess();
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    public static Set<Image> getScreenShots(String path) throws IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        Set files = new LinkedHashSet();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                Image item = new Image();
                item.setName(listOfFiles[i].getName().replace(".jpg", ""));
                item.setExtendedName(listOfFiles[i].getName());
                files.add(item);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return files;
    }
}
