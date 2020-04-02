package com.wzh.controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.wzh.command.ResultCodeInfoEnum;
import com.wzh.service.*;
import com.wzh.vo.MessageBoard;
import com.wzh.vo.ResultApi;
import com.wzh.vo.User;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxh
 * @date 2020/2/17 15:17
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@RestController
@RequestMapping("api/pub/")
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "公共资源,以及回调控制器", tags = "公共接口")
public class PubController {

    private UserService userService;
    private ArticleService articleService;
    private StyleSortService styleSortService;
    private TagsService tagsService;
    private SimpleArticleService simpleArticleService;
    private LinkUrlService linkUrlService;
    private MessageBoardService messageBoardService;
    private CountService countService;
    private RegisterService registerService;
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("need_login")
    @ApiOperation(value = "没有登录回调接口")
    public Object test() {
        System.out.println("no login");
        return ResultApi.ResultNoAndDesc(ResultCodeInfoEnum.NO_LOGIN, true);
    }

    @GetMapping("logout")
    @ApiOperation(value = "登出")
    public Object logOut() {
        System.out.println("logout");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "登出";
    }

    @GetMapping("not_permit")
    public Object noePermit(HttpServletRequest request) {
        System.out.println("req:" + request.getHeader("Authorization"));
        System.out.println("no permit");
        return ResultApi.ResultNoAndDesc(ResultCodeInfoEnum.NO_PERMIT, true);
    }

    /**
     * 根据用户名查询用户信息----用于判断当前权限
     *
     * @param userName
     * @return
     */
    @PostMapping("user.do")
    @ApiOperation(value = "根据用户名查询用户信息", notes = "传入用户username")
    @ApiImplicitParam(
            name = "username", value = "用户名", paramType = "String", dataType = "String"
    )
    public Object getUserInfo(@RequestParam(name = "username", required = true) String userName) {
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, userService.findAllByUsername(userName));
    }

    @PostMapping("login.do")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiImplicitParam(name = "user", value = "用户登录表单",required = true, dataType = "User")
    @ApiResponse(code = 1,message = "登录成功")
    public Object login(HttpServletResponse response, @ApiIgnore @RequestBody User longinuser) {
        try {
            System.out.println(longinuser.getUsername());
            User user = userService.findAllByUsername(longinuser.getUsername());
            System.out.println(longinuser.getUsername());
            System.out.println(user.getPassword() == null);
            System.out.println("".equals(user.getPassword()));
            System.out.println(!user.getPassword().equals(longinuser.getPassword()));
            if (user.getPassword() == null || "".equals(user.getPassword()) || !user.getPassword().equals(longinuser.getPassword())) {
                return ResultApi.ResultAll(ResultCodeInfoEnum.FAIL_MSG, "用户名或密码错误!");
            }
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(longinuser.getUsername(), longinuser.getPassword());
            subject.login(usernamePasswordToken);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name", user.getNickName());
            userMap.put("token", subject.getSession().getId());
            return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, userMap);
        } catch (Exception e) {
                return ResultApi.ResultAll(ResultCodeInfoEnum.FAIL_MSG, "用户名或密码错误!");
        }
    }

    /**
     * @param contentId  如果不等于默认值，则根据文章id查找文章
     * @param topFlag    文章是否置顶推荐
     * @param blogTypeId 文章类型
     * @return
     */
    @PostMapping("article.do")
    @ApiOperation(value = "文章内容接口",notes = "根据传入参数查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId",value = "文章id",required = false,defaultValue = "-1"
            ,paramType = "String",dataType = "String"
            ),
            @ApiImplicitParam(name = "topFlag",value = "是否置顶",required = false,defaultValue = "0"
            ,paramType = "String",dataType = "String"
            ),
            @ApiImplicitParam(name = "blogTypeId",value = "文章类型",required = false,defaultValue = "0"
                    ,paramType = "String",dataType = "String"
            ),
            @ApiImplicitParam(name = "randomFlag",value = "随机文章",required = false,defaultValue = "0"
                    ,paramType = "String",dataType = "String"
            )
    })
    public Object getArticleInfo(
            @RequestParam(name = "contentId", required = false, defaultValue = "-1") int contentId,
            @RequestParam(name = "topFlag", required = false, defaultValue = "0") int topFlag,
            @RequestParam(name = "blogTypeId", required = false, defaultValue = "0") int blogTypeId,
            @RequestParam(name = "randomFlag", required = false, defaultValue = "0") int randomFlag,
            @RequestParam(name = "startPage",required = false ,defaultValue = "0")int startPage,
            @RequestParam(name = "endPage",required = false ,defaultValue = "8")int endPage
    ) {

        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, articleService.selectDaoById(contentId, topFlag, blogTypeId, randomFlag,startPage,endPage));
    }

    @PostMapping("sort.do")
    public Object getSorts(
            @RequestParam(name = "sortName", required = false, defaultValue = "false") String sortName
    ) {
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, styleSortService.serviceHandler(sortName));
    }

    @RequestMapping("tags.do")
    public Object getTags(@RequestParam(name = "tagsId", required = false, defaultValue = "-1") int tagsId) {
        try {
            return ResultApi.ResultAll(ResultCodeInfoEnum.FAIL_MSG, tagsService.selectServcieHandler(tagsId));
        } catch (Exception e) {
            return ResultApi.ResultAll(ResultCodeInfoEnum.FAIL_MSG, tagsService.selectServcieHandler(tagsId));
        }
    }

    @PostMapping("simpleInfo.do")
    public Object getSimpArticleInfo(
            @RequestParam(name = "topFlag", required = false, defaultValue = "0") int topFlag,
            @RequestParam(name = "blogTypeId", required = false, defaultValue = "0") int blogTypeId,
            @RequestParam(name = "randomFlag", required = false, defaultValue = "0") int randomFlag
    ) {
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, simpleArticleService.findAllSimpleInfo(topFlag, blogTypeId, randomFlag));
    }

    @PostMapping("link.do")
    public Object getLinks(@RequestParam(name = "userId", defaultValue = "1") int userId) {
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, linkUrlService.findAllByUserId(userId));
    }

    @PostMapping("message.do")
    public Object getMessages(@RequestBody(required = false) MessageBoard messageBoard,
                              @RequestParam(value = "type", defaultValue = "0") int type
    ) {

        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, messageBoardService.ServiceHnalder(messageBoard, type));
    }

    @GetMapping("count.do")
    public Object getCount(){
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, countService.resultZl());

    }
    @PostMapping("beforeRegister.do")
    @ApiOperation(value = "短信验证码接口",notes = "短信验证码接口,传入手机号")
    @ApiImplicitParam(name = "phone", required = true,paramType = "String" , dataType = "String")
    public Object beforeRegister(@RequestParam(name = "phone",required = true) String phone){
        String  appid = "1400331574";
        String appkey = "e9e05092d06f827af2bc04dc8cca2d7b";
        int templated = 553961;
        String smsSign = "陈叙含orange";
        final String url = "https://yun.tim.qq.com/v3/tlssmssvr/sendsms";
        int randomnum = (int)((Math.random()*9+1)*10000);
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey，见《创建secretId和secretKey》小节
        Credential cred = new Credential("AKIDpQAPQEE7IDsMdgs4NSHsDLFuMZXFrvCE",
                "1nPmwqVJvKTSDn8fuuhEZItHK8sSmS0V");

        // 实例化要请求产品(以cvm为例)的client对象
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
        SmsClient smsClient = new SmsClient(cred, "ap-chongqing");//第二个ap-chongqing 填产品所在的区
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setSmsSdkAppid(appid);//appId ,见《创建应用》小节
        String[] phones={phone};  //发送短信的目标手机号，可填多个。
        sendSmsRequest.setPhoneNumberSet(phones);
        sendSmsRequest.setTemplateID(templated+"");  //模版id,见《创建短信签名和模版》小节
        String [] templateParam={String.valueOf(randomnum)};//模版参数，从前往后对应的是模版的{1}、{2}等,见《创建短信签名和模版》小节
        sendSmsRequest.setTemplateParamSet(templateParam);
        sendSmsRequest.setSign(smsSign); //签名内容，不是填签名id,见《创建短信签名和模版》小节
//        sendSmsRequest.set("AKIDpQAPQEE7IDsMdgs4NSHsDLFuMZXFrvCE ");
        try {
            SendSmsResponse sendSmsResponse= smsClient.SendSms(sendSmsRequest); //发送短信
            Map<String,String> map = new HashMap<>();
            map.put("phone",randomnum+"");
            return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, map);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG, "系统错误!");
        }
    }
    @PostMapping("register.do")
    @ApiOperation(value = "注册接口",notes = "需先调用短信验证码接口才能访问")
    @ApiImplicitParam(name = "user", value = "用户注册",required = true, dataType = "User")
    public Object register(@RequestBody User user){
            return ResultApi.ResultNoAndDesc(registerService.registerUser(user),true);
    }

}
