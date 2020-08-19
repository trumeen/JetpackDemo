package io.github.trumeen.bean;

import java.util.List;

public class LLSContentInfoBean {


    /**
     * total : 132
     * currentPage : 1
     * session : {"started":true,"startedAtSec":1597402800,"totalDuration":1423,"name":"Nick 专项课-写作2 正式邮件👨\u200d💻📑","serverTime":1597848818,"allowAnonymous":false}
     * teacher : {"id":"YTc4MWQwMDAwNjhjMmE5ZQ==","nick":"助教老师Nick","avatar":"https://cdn.llscdn.com/lms-sodtmw"}
     * contentItems : [{"id":"ZjJjNTAwMDAwMzc0YWZjYg==","sequence":122,"startPlayOffset":1368,"duration":11,"contentType":1,"audioUrl":"https://cdn.llscdn.com/FqWJFA2rS2OWcY9bXytoAfNPIFs7","resourceDurationInMs":10020},{"id":"ZjMwNTAwMDAwMzc0YWZjYw==","sequence":123,"startPlayOffset":1379,"duration":11,"contentType":1,"audioUrl":"https://cdn.llscdn.com/Fu5Ytc9I022MQ_5MHH8Mxfd5s0Ep","resourceDurationInMs":10679},{"id":"ZjM0NTAwMDAwMzc0YWZjZA==","sequence":124,"startPlayOffset":1390,"duration":3,"contentType":2,"imageUrl":"https://cdn.llscdn.com/elite_klass_templates/MmU0NGUwMDAwMDAxMzBiOQ==/2019-11-22/3640408107/ae0c304c-154b-49be-9934-c77e92a891f7"},{"id":"ZjM4NTAwMDAwMzc0YWZjZQ==","sequence":125,"startPlayOffset":1393,"duration":3,"contentType":2,"imageUrl":"https://cdn.llscdn.com/elite_klass_templates/MmU0NGUwMDAwMDAxMzBiOQ==/2019-11-22/3640408107/607871da-1c16-4ee8-a638-a34644864a95"},{"id":"ZjNjNTAwMDAwMzc0YWZjZg==","sequence":126,"startPlayOffset":1396,"duration":9,"contentType":1,"audioUrl":"https://cdn.llscdn.com/FmOcYWXWNBK6z3IBJ4dPs-pygSdS","resourceDurationInMs":8941},{"id":"ZjQwNTAwMDAwMzc0YWZkMA==","sequence":127,"startPlayOffset":1405,"duration":3,"contentType":2,"imageUrl":"https://cdn.llscdn.com/elite_klass_templates/MmU0NGUwMDAwMDAxMzBiOQ==/2019-11-22/3640408107/658665b6-4005-4714-a947-4d695a2fb45d"},{"id":"ZjQ0NTAwMDAwMzc0YWZkMQ==","sequence":128,"startPlayOffset":1408,"duration":3,"contentType":0,"textContent":"【课后练习】\nAlex Xu给老板Ron Smith 写了邮件，想要请一周的假。因为没怎么写过正式邮件，想要请你帮忙修改下，再发老板。所以现在请用今天所学内容和句式，来帮Alex修改吧。"},{"id":"ZjQ4NTAwMDAwMzc0YWZkMg==","sequence":129,"startPlayOffset":1411,"duration":3,"contentType":2,"imageUrl":"https://cdn.llscdn.com/elite_klass_templates/MmU0NGUwMDAwMDAxMzBiOQ==/2019-11-22/3640408107/236927ec-3e84-4a74-a612-bdbd6eca67a0"},{"id":"ZjRjNTAwMDAwMzc0YWZkMw==","sequence":130,"startPlayOffset":1414,"duration":6,"contentType":1,"audioUrl":"https://cdn.llscdn.com/Fi4mccLcE3xXqKM1UsgnWkVFt8BI","resourceDurationInMs":5519},{"id":"ZjUwNTAwMDAwMzc0YWZkNA==","sequence":131,"startPlayOffset":1420,"duration":3,"contentType":0,"textContent":"---the end---"}]
     */

    private int total;
    private int currentPage;
    private SessionBean session;
    private TeacherBean teacher;
    private List<ContentItemsBean> contentItems;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public SessionBean getSession() {
        return session;
    }

    public void setSession(SessionBean session) {
        this.session = session;
    }

    public TeacherBean getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherBean teacher) {
        this.teacher = teacher;
    }

    public List<ContentItemsBean> getContentItems() {
        return contentItems;
    }

    public void setContentItems(List<ContentItemsBean> contentItems) {
        this.contentItems = contentItems;
    }

    public static class SessionBean {
        /**
         * started : true
         * startedAtSec : 1597402800
         * totalDuration : 1423
         * name : Nick 专项课-写作2 正式邮件👨‍💻📑
         * serverTime : 1597848818
         * allowAnonymous : false
         */

        private boolean started;
        private int startedAtSec;
        private int totalDuration;
        private String name;
        private int serverTime;
        private boolean allowAnonymous;

        public boolean isStarted() {
            return started;
        }

        public void setStarted(boolean started) {
            this.started = started;
        }

        public int getStartedAtSec() {
            return startedAtSec;
        }

        public void setStartedAtSec(int startedAtSec) {
            this.startedAtSec = startedAtSec;
        }

        public int getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(int totalDuration) {
            this.totalDuration = totalDuration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getServerTime() {
            return serverTime;
        }

        public void setServerTime(int serverTime) {
            this.serverTime = serverTime;
        }

        public boolean isAllowAnonymous() {
            return allowAnonymous;
        }

        public void setAllowAnonymous(boolean allowAnonymous) {
            this.allowAnonymous = allowAnonymous;
        }
    }

    public static class TeacherBean {
        /**
         * id : YTc4MWQwMDAwNjhjMmE5ZQ==
         * nick : 助教老师Nick
         * avatar : https://cdn.llscdn.com/lms-sodtmw
         */

        private String id;
        private String nick;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class ContentItemsBean {
        /**
         * id : ZjJjNTAwMDAwMzc0YWZjYg==
         * sequence : 122
         * startPlayOffset : 1368
         * duration : 11
         * contentType : 1
         * audioUrl : https://cdn.llscdn.com/FqWJFA2rS2OWcY9bXytoAfNPIFs7
         * resourceDurationInMs : 10020
         * imageUrl : https://cdn.llscdn.com/elite_klass_templates/MmU0NGUwMDAwMDAxMzBiOQ==/2019-11-22/3640408107/ae0c304c-154b-49be-9934-c77e92a891f7
         * textContent : 【课后练习】
         Alex Xu给老板Ron Smith 写了邮件，想要请一周的假。因为没怎么写过正式邮件，想要请你帮忙修改下，再发老板。所以现在请用今天所学内容和句式，来帮Alex修改吧。
         */

        private String id;
        private int sequence;
        private int startPlayOffset;
        private int duration;
        private int contentType;
        private String audioUrl;
        private int resourceDurationInMs;
        private String imageUrl;
        private String textContent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public int getStartPlayOffset() {
            return startPlayOffset;
        }

        public void setStartPlayOffset(int startPlayOffset) {
            this.startPlayOffset = startPlayOffset;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public int getResourceDurationInMs() {
            return resourceDurationInMs;
        }

        public void setResourceDurationInMs(int resourceDurationInMs) {
            this.resourceDurationInMs = resourceDurationInMs;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTextContent() {
            return textContent;
        }

        public void setTextContent(String textContent) {
            this.textContent = textContent;
        }
    }
}
