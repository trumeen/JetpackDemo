package io.github.trumeen.bean;

import cn.bmob.v3.BmobObject;

public class BmobCourseBean extends BmobObject {

    public BmobCourseBean(){
        this.setTableName("course_list");
    }

    public void setObjectId(String id){
        this.setObjectId(id);
    }

    public String id;
    public int level;
    public long startTime;
    public int status;
    public int tag;
    public String title;
    public int type;
    public String uri;
}
