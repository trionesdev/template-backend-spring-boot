package com.trionesdev.template.domains.dic.manager;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.trionesdev.template.BaseTest;
import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import com.trionesdev.template.core.domains.dic.manager.impl.DistrictManager;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DistrictManagerTest extends BaseTest {

    @Autowired
    private DistrictManager districtManager;

    OkHttpClient okHttpClient = new OkHttpClient();

    public List<DistrictPO> districts(JSONArray jsonArray, JSONObject parent, Integer level) {
        if (jsonArray == null || jsonArray.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> prevCodes = new ArrayList<String>();
        String mergeName = Objects.nonNull(parent.getString("mergeName")) ? parent.getString("mergeName") : "";
        if (parent.getString("prevCodes") != null) {
            prevCodes.addAll(List.of(StringUtils.split(parent.getString("prevCodes"), ",")));
        }
        if (!Objects.equals("country", parent.getString("level"))) {
            prevCodes.add(parent.getString("adcode"));
        }
        List<DistrictPO> districtPOList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            var districtObj = jsonArray.getJSONObject(i);
            var district = DistrictPO.builder()
                    .parentCode(parent.getString("adcode"))
                    .name(districtObj.getString("name"))
                    .level(level)
                    .prevCodes(prevCodes)
                    .mergeName(mergeName + districtObj.getString("name"))
                    .build();
            if (Objects.equals(districtObj.get("adcode"), parent.getString("adcode"))) {
                districtObj.put("adcode", parent.getString("adcode") + districtObj.getString("adcode"));
            }
            district.setCode(districtObj.getString("adcode"));
            if (!Objects.equals(districtObj.getString("citycode"), "[]")) {
                district.setCityCode(districtObj.getString("citycode"));
            }
            if (districtObj.get("center") != null) {
                String[] center = StringUtils.split(districtObj.getString("center"), ",");
                district.setLongitude(center[1]);
                district.setLatitude(center[1]);
            }
            districtPOList.add(district);
            var subDistricts = districtObj.getJSONArray("districts");
            if (subDistricts != null) {
                districtObj.put("prevCodes", StringUtils.join(district.getPrevCodes(), ","));
                districtObj.put("mergeName", district.getMergeName());
                var subPoList = districts(subDistricts, districtObj, level + 1);
                districtPOList.addAll(subPoList);
            }
        }
        return districtPOList;
    }

//    @Test
    public void initDistrict() {
        String key = "18ed34b14999b5f9bfff7d97044b2437";
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://restapi.amap.com/v3/config/district").newBuilder();
        urlBuilder.addQueryParameter("subdistrict", "4");
        urlBuilder.addQueryParameter("key", key);

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            var res = JSON.parseObject(response.body().bytes());
            var country = res.getJSONArray("districts").getJSONObject(0);
            var provinceDistricts = country.getJSONArray("districts");
            var districts = districts(provinceDistricts, country, 1);
            System.out.println(JSON.toJSONString(districts));
            districtManager.saveBatch(districts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
