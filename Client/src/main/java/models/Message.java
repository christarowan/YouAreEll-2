package models;

/* 
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
public class Message implements Comparable {

    private String message = "";
    private String toid = "";
    private String fromid = "";
    private String timestamp = "";
    private String sequence = "";

    public Message() {}
    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromid = fromId;
        this.toid = toId;
    }

    public Message (String message, String fromId) {
        this.message = message;
        this.fromid = fromId;
        this.toid = "";
    }

    @Override
    public String toString() {
        return "to: " + this.toid + "\nfrom: "+ this.fromid + "\n" + this.message + "\n----\n";
    }

    public int compareTo(Object o) {
        return this.sequence.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toid;
    }

    public void setToId(String toId) {
        this.toid = toId;
    }

    public String getFromId() {
        return fromid;
    }

    public void setFromId(String fromId) {
        this.fromid = fromId;
    }

//    public String getTimestamp() {
//        return timestamp;
//    }

    public String getSeqId() {
        return sequence;
    }

}