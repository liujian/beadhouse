package com.beadhouse.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.beadhouse.dao.ContactsMapper;
import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.MessageMapper;
import com.beadhouse.domen.ChatHistory;
import com.beadhouse.domen.Contact;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.in.ChangePasswordParam;
import com.beadhouse.in.ElderInfoParam;
import com.beadhouse.in.ElderRegistrationParam;
import com.beadhouse.in.ElderSendCodeParam;
import com.beadhouse.in.LoginParam;
import com.beadhouse.in.NewPasswordParam;
import com.beadhouse.in.TokenParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.out.ChatHistoryOut;
import com.beadhouse.redis.RedisService;
import com.beadhouse.service.ElderService;
import com.beadhouse.utils.Utils;

@Service
public class ElderServiceImpl implements ElderService {

    @Autowired
    private ElderUserMapper elderUserMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private MessageMapper messageMapper;


    @Value("${google.SERVER_IMAGE}")
    private String SERVER_IMAGE;

    private static final Logger logger = LoggerFactory.getLogger(ElderServiceImpl.class);

    @Override
    public BasicData registration(ElderRegistrationParam param) {
        if (param.getElderUserEmail() == null || param.getElderUserEmail().isEmpty()) {
            return BasicData.CreateErrorMsg("Email address is empty");
        }
        if (param.getElderUserPassword() == null || param.getElderUserPassword().isEmpty()) {
            return BasicData.CreateErrorMsg("Password is empty");
        }
        if (param.getElderFirstName() == null || param.getElderFirstName().isEmpty()) {
            return BasicData.CreateErrorMsg("First name is empty");
        }
        if (param.getElderFirstName() == null || param.getElderFirstName().isEmpty()) {
            return BasicData.CreateErrorMsg("Last name is empty");
        }
        if (param.getElderBirthday() == null || param.getElderBirthday().isEmpty()) {
            return BasicData.CreateErrorMsg("Birthday is empty");
        }

        String code = param.getCode();
        String emailAddress = param.getElderUserEmail();
        if (!redisService.exists(emailAddress)) {
            return BasicData.CreateErrorMsg("The verification code has expired. Please revalidate the code!");
        } else if (!redisService.getObj(emailAddress).equals(code)) {
            return BasicData.CreateErrorMsg("The verification code is incorrect. Please re input!");
        }

        ElderUser elderuser = elderUserMapper.selectByElderUserEmail(param.getElderUserEmail());
        if (elderuser != null) {
            return BasicData.CreateErrorMsg("This account already exists!");
        }

        elderuser = new ElderUser();
        elderuser.setElderUserEmail(param.getElderUserEmail());
        elderuser.setElderUserPassword(param.getElderUserPassword());
        elderuser.setElderFirstName(param.getElderFirstName());
        elderuser.setElderLastName(param.getElderLastName());
        elderuser.setElderBirthday(param.getElderBirthday());
        elderuser.setElderUserPhone(param.getElderUserphone());
        elderuser.setElderAvatar(SERVER_IMAGE + param.getElderAvatar());
        elderuser.setCreateDate(new Date());
        String token = Utils.getToken();
        elderuser.setToken(token);
        elderUserMapper.insertElderUser(elderuser);

        return BasicData.CreateSucess(elderuser);
    }


    @Override
    public BasicData elderUserLogin(LoginParam param) {
        String elderUserEmail = param.getEmailAddress();
        String password = param.getPassword();
        if (elderUserEmail == null || password == null) {
            return BasicData.CreateErrorMsg("Email address is empty");
        }
        ElderUser elderUser = elderUserMapper.selectByElderUserEmail(elderUserEmail);
        if (elderUser == null) {
            return BasicData.CreateErrorMsg("The username or password you entered is incorrect!");
        }
        if (!password.equals(elderUser.getElderUserPassword())) {
            return BasicData.CreateErrorMsg("The username or password you entered is incorrect!");
        }
        String token = Utils.getToken();

        elderUser.setToken(token);
        elderUserMapper.updateUser(elderUser);

        return BasicData.CreateSucess(elderUser);
    }

    @Override
    public BasicData forgetPassword(NewPasswordParam param) {
        if (param.getEmailAddress() == null || param.getEmailAddress().isEmpty()) {
            return BasicData.CreateErrorMsg("Email address is empty");
        }
        if (param.getNewPassword() == null || param.getNewPassword().isEmpty()) {
            return BasicData.CreateErrorMsg("NewPassword is empty");
        }

        if (param.getCode() == null || param.getCode().isEmpty()) {
            return BasicData.CreateErrorMsg("Code is empty");
        }
        String code = param.getCode();
        String emailAddress = param.getEmailAddress();
        if (!redisService.exists(emailAddress)) {
            return BasicData.CreateErrorMsg("The verification code has expired. Please revalidate the code!");
        } else if (!redisService.getObj(emailAddress).equals(code)) {
            return BasicData.CreateErrorMsg("The verification code is incorrect. Please re input!");
        }

        ElderUser elderUser = elderUserMapper.selectByElderUserEmail(param.getEmailAddress());
        if (elderUser == null) {
            return BasicData.CreateErrorMsg("This account does not exist!");
        }
        elderUser.setElderUserPassword(param.getNewPassword());
        elderUser.setModificationDate(new Date());
        elderUserMapper.updatePassword(elderUser);

        return BasicData.CreateSucess(elderUser);
    }

    @Override
    public BasicData changePassword(ChangePasswordParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();

        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        if (oldPassword == null || oldPassword.isEmpty()) {
            return BasicData.CreateErrorMsg("The old password is empty");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            return BasicData.CreateErrorMsg("The new password is empty");
        }

        if (!oldPassword.equals(elderUser.getElderUserPassword())) {
            return BasicData.CreateErrorMsg("The old password is wrong, please re-enter the old password!");
        }

        ElderUser updateUser = new ElderUser();
        updateUser.setToken(param.getToken());
        updateUser.setElderUserPassword(param.getNewPassword());
        updateUser.setModificationDate(new Date());
        elderUserMapper.updatePassword(updateUser);

        return BasicData.CreateSucess();
    }

    @Override
    public BasicData updateElderInfo(ElderInfoParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();

        String firstName = param.getElderFirstName();
        String lastName = param.getElderFirstName();
        String phone = param.getElderUserPhone();
        String birthday = param.getElderBirthday();
        if (firstName == null || firstName.isEmpty()) {
            return BasicData.CreateErrorMsg("The first name is empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            return BasicData.CreateErrorMsg("The last name is empty");
        }
        if (phone == null || phone.isEmpty()) {
            return BasicData.CreateErrorMsg("The phone is empty");
        }
        if (birthday == null || birthday.isEmpty()) {
            return BasicData.CreateErrorMsg("The birthday is empty");
        }


        elderUser.setElderFirstName(param.getElderFirstName());
        elderUser.setElderLastName(param.getElderLastName());
        elderUser.setElderUserPhone(param.getElderUserPhone());
        elderUser.setElderBirthday(param.getElderBirthday());
        elderUser.setModificationDate(new Date());
        elderUserMapper.updateElderInfo(elderUser);

        return BasicData.CreateSucess(elderUser);
    }

    @Override
    public BasicData getElderInfo(TokenParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        return BasicData.CreateSucess(elderUser);

    }

    @Override
    public BasicData updateElderAvatar(TokenParam param, String filePath) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        elderUser.setElderAvatar(SERVER_IMAGE + filePath);
        elderUserMapper.updateElderAvatar(elderUser);

        return BasicData.CreateSucess(SERVER_IMAGE + filePath);
    }


    @Override
    public BasicData getElderContacts(TokenParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();

        List<Contact> list = contactsMapper.selectByElderUserId(elderUser.getElderUserId());

        return BasicData.CreateSucess(list);
    }

    @Value("${redis.eldercodeexpiry}")
    private Long expiry;

    @Override
    public BasicData getElderCode(TokenParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        String code = null;
        if (redisService.exists("elderbindemail" + elderUser.getElderUserEmail())) {
            code = (String) redisService.getObj("elderbindemail" + elderUser.getElderUserEmail());
        } else {
            code = getcode();
            String eldercode = "elderbindcode" + code;
            redisService.set("elderbindemail" + elderUser.getElderUserEmail(), code, expiry);
            redisService.set(eldercode, elderUser.getElderUserEmail(), expiry);
        }

        return BasicData.CreateSucess(code);
    }

    private String getcode() {
        String code = Utils.getRandNum(6);
        String eldercode = "elderbindcode" + code;
        if (redisService.exists(eldercode)) {
            getcode();
        }

        return code;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public BasicData sendCode(ElderSendCodeParam param) {

        String token = param.getToken();
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        String username = elderUser.getElderFirstName() + " " + elderUser.getElderLastName();
        List<String> list = param.getList();

        for (String emailAddress : list) {
            //邮箱发送验证码
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(emailAddress); //自己给自己发送邮件
            message.setSubject("theme：Elderly homes, elderly binding code");
            String msg = username + " Elderly Invite you to bind his / her mailbox and bind code as " + param.getCode();
            message.setText(msg);
            System.out.println("Start sending mail！");
            System.out.println("Mailbox content：" + msg);
            mailSender.send(message);
            System.out.println("Mail sent successfully！");
        }

        return BasicData.CreateSucess();
    }


    @Override
    public BasicData getwaitquests(TokenParam param) {
        String token = param.getToken();
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setElderUserId(elderUser.getElderUserId());
        List<ChatHistoryOut> list = messageMapper.getwaitquests(chatHistory);
        return BasicData.CreateSucess(list);
    }


}
