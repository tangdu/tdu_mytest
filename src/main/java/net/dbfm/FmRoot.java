package net.dbfm;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tangdu
 * Date: 13-10-1
 * Time: 上午8:08
 * To change this template use File | Settings | File Templates.
 */
public class FmRoot {
    private String r;
    private List<FmSong> song;

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public List<FmSong> getSong() {
        return song;
    }

    public void setSong(List<FmSong> song) {
        this.song = song;
    }
}
