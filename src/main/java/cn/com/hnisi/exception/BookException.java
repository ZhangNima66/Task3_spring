package cn.com.hnisi.exception;

/**
 * 书籍相关异常统一管理  @TODO 暂未完成
 */
public class BookException extends Exception
{
    public BookException()
    {
    }

    public BookException(String message)
    {
        super(message);
    }

    public BookException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BookException(Throwable cause)
    {
        super(cause);
    }

    public BookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
