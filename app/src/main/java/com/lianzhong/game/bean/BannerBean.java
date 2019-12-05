package com.lianzhong.game.bean;

import java.util.List;

public class BannerBean {


    /**
     * code : 200
     * data : {"appid":"123","images":[{"banner_url":"http://47.56.177.143/media/upload/369.png","down_url":"http://ikhanju.tiankongxiazheyu.com:12369/D-ikhanju.html"},{"banner_url":"http://47.56.177.143/media/upload/389_bnjDLzc.png","down_url":"http://389.atingkong.com:338/389APP.html"},{"banner_url":"http://47.56.177.143/media/upload/9MF18_ALF_0CD98E78C_gfEMbFz.png","down_url":"https://app.caipiaoappxiazai.com/"}],"active":true,"download_link":"https://www.google.com/"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : 123
         * images : [{"banner_url":"http://47.56.177.143/media/upload/369.png","down_url":"http://ikhanju.tiankongxiazheyu.com:12369/D-ikhanju.html"},{"banner_url":"http://47.56.177.143/media/upload/389_bnjDLzc.png","down_url":"http://389.atingkong.com:338/389APP.html"},{"banner_url":"http://47.56.177.143/media/upload/9MF18_ALF_0CD98E78C_gfEMbFz.png","down_url":"https://app.caipiaoappxiazai.com/"}]
         * active : true
         * download_link : https://www.google.com/
         */

        private String appid;
        private boolean active;
        private String download_link;
        private List<ImagesBean> images;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getDownload_link() {
            return download_link;
        }

        public void setDownload_link(String download_link) {
            this.download_link = download_link;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * banner_url : http://47.56.177.143/media/upload/369.png
             * down_url : http://ikhanju.tiankongxiazheyu.com:12369/D-ikhanju.html
             */

            private String banner_url;
            private String down_url;

            public String getBanner_url() {
                return banner_url;
            }

            public void setBanner_url(String banner_url) {
                this.banner_url = banner_url;
            }

            public String getDown_url() {
                return down_url;
            }

            public void setDown_url(String down_url) {
                this.down_url = down_url;
            }
        }
    }
}
