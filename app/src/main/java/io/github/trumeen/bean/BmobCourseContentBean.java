package io.github.trumeen.bean;

import cn.bmob.v3.BmobObject;

public class BmobCourseContentBean extends BmobObject {

    public BmobCourseContentBean(){
        this.setTableName("course_content");
    }

    public String id;
    public String result;
}
