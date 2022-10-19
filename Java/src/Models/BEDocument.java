package Models;

public class BEDocument {
    Integer mId;
    String mUrl;
    String mIdxTime;
    String mCreationTime;

    public Integer getmId(){
        return mId;
    }

    public void setmId(int newMId){
        mId = newMId;
    }

    public String getmUrl(){
        return mUrl;
    }

    public void setmUrl(String newmUrl){
        mUrl = newmUrl;
    }

    public String getmIdxTime(){
        return mIdxTime;
    }

    public void setmIdxTimel(String newmIdxTime){
        mIdxTime = newmIdxTime;
    }

    public String getmCreationTime(){
        return mCreationTime;
    }

    public void setmCreationTime(String newmCreationTime){
        mCreationTime = newmCreationTime;
    }
}
