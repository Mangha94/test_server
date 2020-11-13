package com.test.lee.project.common.exception;


/**
 * 데이터가 존재 하지 않음 Exception
 *
 */
public class NoDataException extends RuntimeException
{
    private static final long	serialVersionUID	= -7044189756706237640L;

    public NoDataException()
    {
    }

    public NoDataException(String message)
    {
        super (message);
    }
}