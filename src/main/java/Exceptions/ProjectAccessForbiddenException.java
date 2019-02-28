package Exceptions;

public class ProjectAccessForbiddenException extends Throwable {
    public ProjectAccessForbiddenException(String str){
        super(str);
    }
}