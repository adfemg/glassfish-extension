package org.adfemg.gfextension.data;

import oracle.javatools.data.HashStructure;
import oracle.javatools.data.HashStructureAdapter;
import oracle.javatools.data.PropertyStorage;

/**
 * Singleton. Defines the data structure required by the extension
 * <tt>GlassFishDataStructure</tt> data object.
 */
public final class GlassFishDataStructure extends HashStructureAdapter {
    public static final String DATA_KEY = "org.adfemg.gfextension.GlassFishDataStructure";
    private String OS_COMMAND_KEY = "OS_COMMAND";

    /** Key for the glassfish home value*/
    private final String GLASSFISH_HOME = "GLASSFISH_HOME"; //NOTRANS

    /** Value for the glassfish home path. OS dependant*/
    private String DEFAULT_GLASSFISH_HOME = "C://glassfish3/";

    /** Key for the glassfish start command value*/
    private final String GLASSFISH_START = "GLASSFISH_START"; //NOTRANS

    /** Value for the glassfish start command. OS dependant*/
    private String DEFAULT_GLASSFISH_START = "C://glassfish3/bin/asadmin start-domain domain1";

    /** Key for the glassfish stop command value*/
    private static final String GLASSFISH_STOP = "GLASSFISH_STOP"; //NOTRANS

    /** Value for the glassfish stop command. OS dependant*/
    private String DEFAULT_GLASSFISH_STOP = "C://glassfish3/bin/asadmin stop-domain domain1";

    /** Key for the glassfish debug command value*/
    private static final String GLASSFISH_DB = "GLASSFISH_DB"; //NOTRANS

    /** Value for the glassfish debug command. OS dependant*/
    private String DEFAULT_GLASSFISH_DB = "C://glassfish3/bin/asadmin start-domain --debug=true";

    /** Key for the glassfish admin console value*/
    private static final String GLASSFISH_ADMIN = "GLASSFISH_ADMIN"; //NOTRANS

    /** Value for the glassfish admin console.*/
    private final String DEFAULT_GLASSFISH_ADMIN = "http://localhost:4848"; //NOTRANS

    /**
     * Creates Glassfish Extension DataStructure with default values. OS dependant.
     * @param hash Contains the different configurations for each command
     */
    private GlassFishDataStructure(HashStructure hash) {
        super(hash);


        String os = System.getProperty("os.name");
        String userHome = System.getProperty("user.home");
        String command = "cmd /c start";

        //set the command paths based on the OS
        if (os != null) {
            if (os.toUpperCase().indexOf("WIN") != -1) {
                command = "cmd /c start";
                DEFAULT_GLASSFISH_HOME = "C://glassfish3/";
                DEFAULT_GLASSFISH_START = "C://glassfish3/bin/asadmin start-domain domain1";
                DEFAULT_GLASSFISH_STOP = "C://glassfish3/bin/asadmin stop-domain domain1";
                DEFAULT_GLASSFISH_DB = "C://glassfish3/bin/asadmin start-domain --debug=true";
            } else if (os.toUpperCase().indexOf("LINUX") != -1) {
                command = "/usr/bin/xterm -hold -e";
                DEFAULT_GLASSFISH_HOME = userHome + "/glassfish3/";
                DEFAULT_GLASSFISH_START = userHome + "/glassfish3/bin/asadmin start-domain --verbose domain1";
                DEFAULT_GLASSFISH_STOP = userHome + "/glassfish3/bin/asadmin stop-domain domain1";
                DEFAULT_GLASSFISH_DB = userHome + "/glassfish3/bin/asadmin start-domain --debug=true";
            } else if (os.toUpperCase().indexOf("MAC") != -1) {
                command = "/Applications/Utilities/Terminal.app/Contents/MacOS/Terminal";
                DEFAULT_GLASSFISH_HOME = userHome + "/glassfish3/";
                DEFAULT_GLASSFISH_START = userHome + "/glassfish3/bin/asadmin start-domain domain1";
                DEFAULT_GLASSFISH_STOP = userHome + "/glassfish3/bin/asadmin stop-domain domain1";
                DEFAULT_GLASSFISH_DB = userHome + "/glassfish3/bin/asadmin start-domain --debug=true";
            }
        }
        hash.putString(OS_COMMAND_KEY, command);
    }

    /**
     * Returns an instance of <tt>GlassFishDataStructure</tt>
     *
     * @param storage a storage object.
     * @throws NullPointerException if <tt>storage</tt> is <tt>null</tt>.
     */
    public static GlassFishDataStructure getInstance(PropertyStorage storage) {
        return new GlassFishDataStructure(findOrCreate(storage, DATA_KEY));
    }


    /**
     * Retrieves the Glassfish home directory
     * @return The glassfish home directory set by the user or the default one if it has not been set
     */
    public String getGlassfishHome() {
        return _hash.getString(GLASSFISH_HOME, DEFAULT_GLASSFISH_HOME);
    }

    /**
     * Sets the value for the Glassfish home directory
     * @param ts Glassfish home directory
     */
    public void setGlassfishHome(String ts) {
        _hash.putString(GLASSFISH_HOME, ts);
    }

    /**
     * Retrieves the Glassfish start command
     * @return The glassfish start command set by the user or the default one if it has not been set
     */
    public String getGlassfishStart() {
        return _hash.getString(GLASSFISH_START, DEFAULT_GLASSFISH_START);
    }

    /**
     * Sets the value for the Glassfish start command
     * @param ts Glassfish start command
     */
    public void setGlassfishStart(String ts) {
        _hash.putString(GLASSFISH_START, ts);
    }

    /**
     * Retrieves the Glassfish stop command
     * @return The glassfish stop command set by the user or the default one if it has not been set
     */
    public String getGlassfishStop() {
        return _hash.getString(GLASSFISH_STOP, DEFAULT_GLASSFISH_STOP);
    }

    /**
     * Sets the value for the Glassfish stop command
     * @param ts Glassfish stop command
     */
    public void setGlassfishStop(String ts) {
        _hash.putString(GLASSFISH_STOP, ts);
    }

    /**
     * Retrieves the Glassfish debug command
     * @return The glassfish debug command set by the user or the default one if it has not been set
     */
    public String getGlassfishDb() {
        return _hash.getString(GLASSFISH_DB, DEFAULT_GLASSFISH_DB);
    }

    /**
     * Sets the value for the Glassfish debug command
     * @param ts Glassfish debug command
     */
    public void setGlassfishDb(String ts) {
        _hash.putString(GLASSFISH_DB, ts);
    }

    /**
     * Retrieves the Glassfish URL for the admin console
     * @return The glassfish URL for the admin console set by the user or the default one if it has not been set
     */
    public String getGlassfishAdmin() {
        return _hash.getString(GLASSFISH_ADMIN, DEFAULT_GLASSFISH_ADMIN);
    }

    /**
     * Sets the value for the Glassfish admin console URL
     * @param ts Glassfish admin console URL
     */
    public void setGlassfishAdmin(String ts) {
        _hash.putString(GLASSFISH_ADMIN, ts);
    }

    /**
     * Retrieves the OS command to start a console where the Glassfish commands will run
     * @return The OS command to start an OS console
     */
    public String getOSCommand() {
        return _hash.getString(OS_COMMAND_KEY);
    }
}
