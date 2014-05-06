package bookpub.web.service;

/**
 * This class encapsulates the response to certain web service operations such
 * as register user, purchase book, log in, log out, and so on. The
 * <code>success</code> field is either true or false, indicating whether the
 * operation is successful. The <code>message</code> field can be used to
 * included additional information about the operation, e.g. reason for the
 * failure.
 */
public class ServiceResponse {

    boolean success;

    String message;

    public ServiceResponse()
    {
        success = true;
        message = "";
    }

    public ServiceResponse( boolean success )
    {
        this.success = success;
        this.message = "";
    }

    public ServiceResponse( boolean success, String message )
    {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess( boolean success )
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

}
