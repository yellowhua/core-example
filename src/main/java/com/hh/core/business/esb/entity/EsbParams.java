package com.hh.core.business.esb.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ESBAction选择参数，方便管理
 * <p/>
 * Created with IntelliJ IDEA.
 * User: jianyueting
 * Date: 13-11-22
 * Time: 上午11:10
 */
public class EsbParams {
    private List<String> params;
    private List<String> values;
    private String router;

    public EsbParams() {
        params = new ArrayList<String>();
        values = new ArrayList<String>();
    }

    public EsbParams(String param, String value) {
        this();
        add(param, value);
    }

    public void add(String param, String value) {
        params.add(param);
        values.add(value);
    }

    public String[] getParams() {
        return params.toArray(new String[]{});
    }

    public String[] getValues() {
        return values.toArray(new String[]{});
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }
}
