package net.PdfSimple;

import java.util.Date;
import java.util.List;

/**
 * 解析记录
 * User: tangdu
 * Date: 13-9-30
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class Record {

    private String type;
    private Date date;
    private List<String> rows;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
