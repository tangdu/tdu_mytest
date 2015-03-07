package net.PdfSimple;

/**
 * Created with IntelliJ IDEA.
 * User: tangdu
 * Date: 13-9-30
 * Time: 下午7:53
 * To change this template use File | Settings | File Templates.
 */
public class PdfParseException extends Exception {
    private int code=0;

    public  PdfParseException(int code,String errors){
        super(errors);
        this.code=code;
    }
}
