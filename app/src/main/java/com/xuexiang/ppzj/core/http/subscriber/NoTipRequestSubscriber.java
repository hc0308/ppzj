/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.ppzj.core.http.subscriber;

import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xhttp2.model.XHttpRequest;
import com.xuexiang.xhttp2.subsciber.BaseSubscriber;
import com.xuexiang.xutil.common.StringUtils;
import com.xuexiang.xutil.common.logger.Logger;

/**
 * 不带错误toast提示的网络请求订阅，只存储错误的日志
 *
 * @author xuexiang
 * @since 2019-11-18 23:11
 */
public abstract class NoTipRequestSubscriber<T> extends BaseSubscriber<T> {

    /**
     * 记录一下请求的url,确定出错的请求是哪个请求
     */
    private String mUrl;

    public NoTipRequestSubscriber() {

    }

    public NoTipRequestSubscriber(XHttpRequest req) {
        this(req.getUrl());
    }

    public NoTipRequestSubscriber(String url) {
        mUrl = url;
    }

    @Override
    public void onError(ApiException e) {
        if (!StringUtils.isEmpty(mUrl)) {
            Logger.e("网络请求的url:" + mUrl, e);
        } else {
            Logger.e(e);
        }
    }
}
