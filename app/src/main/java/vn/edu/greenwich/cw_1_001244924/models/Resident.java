package vn.edu.greenwich.cw_1_001244924.models;

import java.io.Serializable;

public class Resident implements Serializable {
    protected long _id;
    protected String _name;
    protected String _destination;
    protected String _startDate;
    protected String _participants;
    protected String _note;
    protected String _description;
    protected int _owner;

    public Resident() {
        _id = -1;
        _name = null;
        _destination = null;
        _startDate = null;
        _participants = null;
        _note = null;
        _description = null;
        _owner = -1;
    }

    public Resident(long id, String name, String startDate, int owner , String destination, String participants, String note , String description) {
        _id = id;
        _name = name;
        _destination = destination;
        _startDate = startDate;
        _participants = participants;
        _description = description;
        _note = note;
        _owner = owner;
    }

    public long getId() { return _id; }
    public void setId(long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }

    public String getDestination() {return _destination;}
    public void setDestination(String destination) {_destination = destination;}

    public String getParticipants() {return _participants;}
    public void setParticipants(String participants) {_participants = participants;}

    public String getNote() {return _note;}
    public void setNote(String note) {_note = note;}

    public String getDescription() {
        return _description;
    }
    public void setDescription(String description) {
        _description = description;
    }


    public String getStartDate() {
        return _startDate;
    }
    public void setStartDate(String startDate) {
        _startDate = startDate;
    }

    public int getOwner() {
        return _owner;
    }
    public void setOwner(int owner) {
        _owner = owner;
    }

    public boolean isEmpty() {
        if (-1 == _id && null == _name && null == _startDate && -1 == _owner)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "[" + _startDate + "] " + _name;
    }
}