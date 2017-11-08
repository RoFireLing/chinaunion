package testcases;

/**
 * @author phantom
 */
public class Bean {
    private int id ; //测试用例的id
    private char type ;//套餐类型
    private int monthRent ;//月租
    private double flow ;//当月的流量
    private int voiceCall ;//当月的通话时间
    private int videoCall ;//当月的视频通话时间

    public void setParameters(int id,char type,int monthRent,double flow,int voiceCall,int videoCall){
        setId(id);
        setFlow(flow);
        setMonthRent(monthRent);
        setType(type);
        setVideoCall(videoCall);
        setVoiceCall(voiceCall);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(int monthRent) {
        this.monthRent = monthRent;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public int getVoiceCall() {
        return voiceCall;
    }

    public void setVoiceCall(int voiceCall) {
        this.voiceCall = voiceCall;
    }

    public int getVideoCall() {
        return videoCall;
    }

    public void setVideoCall(int videoCall) {
        this.videoCall = videoCall;
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("第");
        stringBuffer.append(id);
        stringBuffer.append("个测试用例");
        stringBuffer.append(type);
        stringBuffer.append("型套餐，月租为：");
        stringBuffer.append(monthRent);
        stringBuffer.append("元，当月使用的流量为：");
        stringBuffer.append(flow);
        stringBuffer.append("MB,通话时长为：");
        stringBuffer.append(voiceCall);
        stringBuffer.append("min,视频时长：");
        stringBuffer.append(videoCall);
        stringBuffer.append("min");
        return stringBuffer.toString();
    }



}
