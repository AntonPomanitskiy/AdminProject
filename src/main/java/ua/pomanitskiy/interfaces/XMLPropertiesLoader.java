package ua.pomanitskiy.interfaces;

/**
 * Created by anton on 21.07.16.
 * @version 1.1
 * @author anton
 */
public interface XMLPropertiesLoader {
    /**
     * Getting URL.
     *
     * @return URL
     */
    String getUrl();

    /**
     * Getting Driver.
     *
     * @return Driver
     */
    String getDriver();

    /**
     * Getting username.
     *
     * @return username
     */
    String getUsername();

    /**
     * Getting password.
     *
     * @return password
     */
    String getPassword();

    /**
     * Set name of file with settings.
     *
     * @param arg0 name of a file
     */
    void setFileName(final String arg0);

}

