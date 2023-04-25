package vn.edu.greenwich.cw_1_001244924.models;

import java.io.Serializable;

public class Request implements Serializable {
    protected long _id;
    protected String _content;
    protected String _date;
    protected String _time;
    protected String _type;
    protected  String _amount;
    protected long _residentId;

    public Request() {
        _id = -1;
        _content = null;
        _date = null;
        _time = null;
        _type = null;
        _amount = null;
        _residentId = -1;
    }

    public Request(long id, String content, String date, String time, String type, long residentId, String amount) {
        _id = id;
        _content = content;
        _date = date;
        _time = time;
        _type = type;
        _amount = amount;
        _residentId = residentId;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getAmount() {
        return _amount;
    }

    public void setAmount(String amount) {
        _amount = amount;
    }

    public String getDate() {
        return _date;
    }

    public void setDate(String date) {
        _date = date;
    }

    public String getTime() {
        return _time;
    }

    public void setTime(String time) {
        _time = time;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public long getResidentId() {
        return _residentId;
    }

    public void setResidentId(long residentId) {
        _residentId = residentId;
    }

    public boolean isEmpty() {
        if (-1 == _id && null == _content && null == _date && null == _time && null == _type &&  null == _amount && -1 == _residentId)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "[" + _type + "][" + _date + "] [" + _amount +"] " + _content;
    }
}