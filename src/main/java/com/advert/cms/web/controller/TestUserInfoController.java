package com.advert.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.advert.cms.business.domain.Region;
import com.advert.cms.business.domain.TestUserInfo;
import com.advert.cms.business.service.TestUserInfoService;
import com.advert.cms.core.utils.JsonResult;
import com.advert.cms.web.common.Constant;
import com.advert.cms.web.controller.common.ProvinceCityController;


@Controller
@RequestMapping(value = "/module/test_u")
public class TestUserInfoController extends ProvinceCityController {

    @Autowired
    private TestUserInfoService testUserInfoService;

    //列表
    @RequestMapping(value ="test_ulist")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="name", required=false) String name,
                                    @RequestParam(value="province", required=false) String province,
                                    @RequestParam(value="city", required=false) String city,
                                    @RequestParam(value="distinct", required=false) String distinct,
                                    @RequestParam(value="identity", required=false) String identity,
                                    @RequestParam(value="carB", required=false) String carB
                                    ) {


        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();

        //姓名
        if(StringUtils.isNotBlank(name)){
            searchMap.put("LIKE_name", name);
        }
        //所在省
        if(StringUtils.isNotBlank(province)){
            searchMap.put("LIKE_province", province);
        }
        //所在市
        if(StringUtils.isNotBlank(city)){
            searchMap.put("LIKE_city", city);
        }
        //所在区
        if(StringUtils.isNotBlank(distinct)){
            searchMap.put("LIKE_distinct", distinct);
        }
        //身份证号
        if(StringUtils.isNotBlank(identity)){
            searchMap.put("LIKE_identity", identity);
        }
        //车辆品牌
        if(StringUtils.isNotBlank(carB)){
            searchMap.put("LIKE_carB", carB);
        }

        map.put("pageInfo", testUserInfoService.query(getPageInfo(request), searchMap, sortMap));
        buildPcdData(request);
        return "module.testU.test_ulist";
    }
    
    @RequestMapping(value="nextct",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> getNextLevel(HttpServletRequest request){
    	String pcode = request.getParameter("_pcode");
    	List<Map<String, Object>> simplify = getNextLevel(pcode);
    	JsonResult<List<Map<String, Object>>> json = JsonResult.newJson();
    	json.setObj(simplify);
    	return json;
    }

    //增加-查看
    @RequestMapping(value ="test_uadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "module.testU.test_udetail";
    }


    //增加-保存
    @RequestMapping(value ="test_uadd", method = RequestMethod.POST)
    public String postAdd(HttpServletRequest request, Map<String, Object> map,
                                TestUserInfo testUserInfo){
        try{
            //id
            testUserInfo.setId(null);

            testUserInfoService.save(testUserInfo);
        }catch (Exception ex){
            logger.error("Save Method (inster) TestUserInfo Error : " + testUserInfo.toString(), ex);
            //增加失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
                                                    "/module/test_u/test_uadd.html", map);
    }

    //修改-查看
    @RequestMapping(value ="test_uupdate", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        TestUserInfo testUserInfo = testUserInfoService.get(id);
        if(testUserInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", testUserInfo);
        return "module.testU.test_udetail";
    }

    //修改-保存
    @RequestMapping(value ="test_uupdate", method = RequestMethod.POST)
    public String postUpdate(HttpServletRequest request, Map<String, Object> map,
                                TestUserInfo testUserInfo){
        if(testUserInfo==null ||
                testUserInfo.getId()==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        try{
            TestUserInfo sourceTestUserInfo = testUserInfoService.get(testUserInfo.getId());
            if(sourceTestUserInfo==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }

            //姓名
            sourceTestUserInfo.setName(testUserInfo.getName());
            //所在省
            sourceTestUserInfo.setProvince(testUserInfo.getProvince());
            //所在市
            sourceTestUserInfo.setCity(testUserInfo.getCity());
            //所在区
            sourceTestUserInfo.setDistinct(testUserInfo.getDistinct());
            //详细地址
            sourceTestUserInfo.setDetail(testUserInfo.getDetail());
            //身份证号
            sourceTestUserInfo.setIdentity(testUserInfo.getIdentity());
            //性别
            sourceTestUserInfo.setSex(testUserInfo.getSex());
            //车辆品牌
            sourceTestUserInfo.setCarB(testUserInfo.getCarB());
            //购车时间
            sourceTestUserInfo.setBuyT(testUserInfo.getBuyT());
            testUserInfoService.update(sourceTestUserInfo);
            testUserInfo = sourceTestUserInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) TestUserInfo Error : " + testUserInfo.toString(), ex);
            //修改失败
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE), map);
        }
        //操作提示
        return super.operSuccess(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS),
        String.format("/module/test_u/test_uupdate.html?id=%s", testUserInfo.getId()), map);
    }

    //查看
    @RequestMapping(value ="test_uview")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        TestUserInfo testUserInfo = testUserInfoService.get(id);
        if(testUserInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", testUserInfo);
        return "module.testU.test_uview";
    }




}
