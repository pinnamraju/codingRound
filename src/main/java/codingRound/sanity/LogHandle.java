package codingRound.sanity;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LogHandle {

    private static LogHandle instance;
    public static Logger logger;
    
    private LogHandle(){
    	try {
    		//1Mb log files with rotation upto maximum of 10 log files
			Handler files = new FileHandler(System.getProperty("user.dir")+"/logs/TestVagrantTests%u.%g.log", 1024000, 10, true);
			files.setFormatter(new SimpleFormatter());
			//Adding to Root logger
			Logger.getLogger("").addHandler(files);
			//Redirect System.err
			ConsoleHandler ch = new ConsoleHandler();
			ch.setFormatter(new SimpleFormatter());
			Logger.getLogger("").addHandler(ch);
			//Redirect System Output
			StreamHandler sh = new StreamHandler();
			sh.setFormatter(new SimpleFormatter());
			Logger.getLogger("").addHandler(sh);
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
    }
    
    //static block initialization for exception handling
    static{
        try{
            instance = new LogHandle();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static synchronized LogHandle getInstance(){
        return instance;
    }
}
