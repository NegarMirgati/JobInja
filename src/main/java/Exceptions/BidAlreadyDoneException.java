package Exceptions;

public class BidAlreadyDoneException extends Throwable {
    public BidAlreadyDoneException(String str){
        super(str);
    }
}
