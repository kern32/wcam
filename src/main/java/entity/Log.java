package entity;

import java.sql.Timestamp;

public class Log {

    private Timestamp time;
    private String ip;
    private String country;
    private String city;
    private String browser;
    private String os;

    public Log() {
    }

    public Log(Timestamp time, String ip, String country, String city, String browser, String os) {
        this.time = time;
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.browser = browser;
        this.os = os;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (time != null ? !time.equals(log.time) : log.time != null) return false;
        if (ip != null ? !ip.equals(log.ip) : log.ip != null) return false;
        if (country != null ? !country.equals(log.country) : log.country != null) return false;
        if (city != null ? !city.equals(log.city) : log.city != null) return false;
        if (browser != null ? !browser.equals(log.browser) : log.browser != null) return false;
        return os != null ? os.equals(log.os) : log.os == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (browser != null ? browser.hashCode() : 0);
        result = 31 * result + (os != null ? os.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "time=" + time +
                ", ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
