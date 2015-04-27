package app.giacomo.lavermicocca.termostato.Bean;

/**
 * Created by Giacomo on 26/04/2015.
 */
public class ParamContainer {

    private String value;
    private String params;

    public ParamContainer() {
    }

    public ParamContainer(String value, String params) {
        this.value = value;
        this.params = params;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
