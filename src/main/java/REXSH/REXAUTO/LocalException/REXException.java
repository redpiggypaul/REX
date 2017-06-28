package REXSH.REXAUTO.LocalException;

import org.apache.log4j.Logger;

public class REXException extends Exception{
    private String message;
    private StringBuffer messageB;
    public REXException(String errmessage)
    {
        message = errmessage;
        System.out.println("REXException occurs:(String): " + this.getClass() + " :: ");
    }
    public REXException(StringBuffer errmessage)
    {
        messageB = errmessage;
        System.out.println("REXException occurs:(StringBuffer): " + this.getClass() + " :: ");
    }

	public REXException(String errmessage, Logger theLog)
	{
        message = errmessage;
        theLog.error(message);
	}


    public REXException(StringBuffer errmessage, Logger theLog)
    {
        messageB = errmessage;
        theLog.error(message);
    }

    public String getMessage()
    {
        return message;
    }

    public void getMessage(Throwable cause, Logger theLogger) {
        message = cause.toString();
        theLogger.error(message);
    }

    public void getClassError(String cause, Logger theLogger) {
        message = cause;
        theLogger.error(message);
    }
}