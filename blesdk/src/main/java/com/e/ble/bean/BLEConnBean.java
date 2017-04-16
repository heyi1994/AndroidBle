/*
 * Copyright (c) 2017. xiaoyunfei
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.e.ble.bean;

/**
 * @package_name com.e.ble.bean
 * @name ${name}
 * <p>
 * Created by xiaoyunfei on 2017/4/15.
 * @description
 */

public class BLEConnBean {
    private long startConnTime;
    private String address;

    public BLEConnBean(String address) {
        this.address = address;
        startConnTime = System.nanoTime();
    }

    public void setStartConnTime(long startConnTime) {
        this.startConnTime = startConnTime;
    }

    public long getStartConnTime() {
        return startConnTime;
    }


    public String getAddress() {
        return address;
    }

}
