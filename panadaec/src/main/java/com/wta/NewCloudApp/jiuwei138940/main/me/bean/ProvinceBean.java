package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenovo on 2017/8/28.
 */

public class ProvinceBean implements Serializable{
    private String adcode;
    private String name;
    private String center;
    private String level;
    private Object citycode;
    private List<DistrictsBeanXX> districts;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getCitycode() {
        return citycode;
    }

    public void setCitycode(Object citycode) {
        this.citycode = citycode;
    }

    public List<DistrictsBeanXX> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictsBeanXX> districts) {
        this.districts = districts;
    }



    public static class DistrictsBeanXX implements IPickerViewData,Serializable {

        private Object citycode;
        private String adcode;
        private String name;
        private String center;
        private String level;
        private List<DistrictsBeanX> districts;

        public Object getCitycode() {
            return citycode;
        }

        public void setCitycode(Object citycode) {
            this.citycode = citycode;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCenter() {
            return center;
        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<DistrictsBeanX> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictsBeanX> districts) {
            this.districts = districts;
        }

        @Override
        public String getPickerViewText() {
            return this.getName();
        }

        public static class DistrictsBeanX implements IPickerViewData,Serializable{


            private Object citycode;
            private String adcode;
            private String name;
            private String center;
            private String level;
            private List<DistrictsBean> districts;

            public Object getCitycode() {
                return citycode;
            }

            public void setCitycode(Object citycode) {
                this.citycode = citycode;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCenter() {
                return center;
            }

            public void setCenter(String center) {
                this.center = center;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<DistrictsBean> getDistricts() {
                return districts;
            }

            public void setDistricts(List<DistrictsBean> districts) {
                this.districts = districts;
            }

            @Override
            public String getPickerViewText() {
                return this.getName();
            }

            public static class DistrictsBean implements IPickerViewData,Serializable{


                private Object citycode;
                private String adcode;
                private String name;
                private String center;
                private String level;
                private List<?> districts;

                public Object getCitycode() {
                    return citycode;
                }

                public void setCitycode(Object citycode) {
                    this.citycode = citycode;
                }

                public String getAdcode() {
                    return adcode;
                }

                public void setAdcode(String adcode) {
                    this.adcode = adcode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCenter() {
                    return center;
                }

                public void setCenter(String center) {
                    this.center = center;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public List<?> getDistricts() {
                    return districts;
                }

                public void setDistricts(List<?> districts) {
                    this.districts = districts;
                }

                @Override
                public String getPickerViewText() {
                    return this.getName();
                }
            }
        }
    }
}
