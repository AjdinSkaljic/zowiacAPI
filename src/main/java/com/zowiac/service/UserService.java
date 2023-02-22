package com.zowiac.service;

import com.zowiac.commons.BusinessException;
import com.zowiac.commons.RandomString;
import com.zowiac.model.UserEntity;
import com.zowiac.model.UserRolesEntity;
import com.zowiac.respository.UserRepository;
import com.zowiac.respository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final EmailService emailService;


    @Autowired
    public UserService(UserRepository userRepository, UserRolesRepository userRolesRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.emailService = emailService;
    }

    public void delete(String username) {
        List<UserRolesEntity> roles = getUserRolesRepository().findByUser(username);
        getUserRolesRepository().deleteAll(roles);

        UserEntity user = getUserRepository().findById(username).get();
        getUserRepository().delete(user);
    }

    public void updateUser(UserEntity userEntity) {
        UserEntity userToUpdate = getUserRepository().findById(userEntity.getUsername()).get();

        userToUpdate.setFirstname(userEntity.getFirstname());
        userToUpdate.setLastname(userEntity.getLastname());
        userToUpdate.setSkipTutorial(userEntity.getSkipTutorial());
        userToUpdate.setSkipTutorialReport(userEntity.getSkipTutorialReport());
        userToUpdate.setHunter(userEntity.getHunter());
        getUserRepository().saveAndFlush(userToUpdate);
    }

    public void changePassword(String user, String password) throws Exception {
        UserEntity userToUpdate = getUserRepository().findById(user).get();
        userToUpdate.setUserPass(convertPwd(password));
        getUserRepository().saveAndFlush(userToUpdate);
    }

    public void save(UserEntity user) throws Exception {
        getUserRepository().saveAndFlush(user);
        user.setUserPass(convertPwd(user.getUserPass()));
        UserRolesEntity userRole = new UserRolesEntity();
        userRole.setUserName(user.getUsername());
        userRole.setUserRole(user.getUserRole());
        getUserRolesRepository().saveAndFlush(userRole);
    }


    private static String convertPwd(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return DatatypeConverter.printHexBinary(md.digest());
    }

    public void forgotPassword(String user) throws Exception {
        try {
            UserEntity userEntity = getUserRepository().findById(user).get();
            String pwd = RandomString.getRundomString();
            userEntity.setUserPass(convertPwd(pwd));

            getUserRepository().saveAndFlush(userEntity);

            String to = userEntity.getUsername();

            String subject = "Ihr Passwort für ZOWIAC";
            String text = "Hallo, \n\n"
                    + "Sie haben ein neues Passwort angefordert, das wir Ihnen mit dieser\n"
                    + "E-Mail zusenden. Bitte nutzen Sie dies nur für Ihr nächstes Login.\n"
                    + "Aus Sicherheitsgründen sollten Sie das Passwort sofort nach dem\n"
                    + "Einloggen ändern.\n\n"
                    + "Ihr neues Passwort lautet: "
                    + pwd
                    + "\n"
                    + "(Bitte beachten Sie bei der Eingabe die Groß- und Kleinschreibung).\n\n"
                    + "Mit freundlichen Grüssen"
                    + "\n Ihre\n ZOWIAC";

            emailService.sendMail(to, subject, text);
        } catch (Exception e) {
            throw new BusinessException("Benutzer " + user + " ist unbekannt.");
        }
    }

    public void lockUser(String user) {
        List<UserRolesEntity> userRoles = getUserRolesRepository().findByUser(user);

        if (userRoles != null) {
            for (UserRolesEntity userRole : userRoles)
                if (userRole.isLocked()) // Already locked
                    return;
        }

        UserRolesEntity userRole = new UserRolesEntity();
        userRole.setUserName(user);
        userRole.setUserRole(UserRolesEntity.ROLE_LOCKED);
        getUserRolesRepository().saveAndFlush(userRole);
    }


    public void unlockUser(String user) {
        List<UserRolesEntity> userRoles = getUserRolesRepository().findByUser(user);

        if (userRoles != null) {
            for (UserRolesEntity userRole : userRoles)
                if (userRole.isLocked()) {// Already locked
                    getUserRolesRepository().delete(userRole);
                }
        }
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = getUserRepository().findAll();
        users.forEach(user -> {
            user.setUserRoles(getUserRolesRepository().findByUser(user.getUsername()));
        });
        return users;
    }

    public UserEntity findUser(String username) {
        UserEntity user = getUserRepository().findById(username).get();
        return user;
    }


    public UserEntity registerUser(UserEntity user) throws Exception {
        UserEntity newUser = new UserEntity();

        if (getUserRepository().existsById(user.getUsername()))
            throw new BusinessException("Benutzer ist bereits registriert.");

        newUser.setUsername(user.getUsername());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setUserPass(convertPwd(user.getUserPass()));
        newUser.setHunter(user.getHunter());
        newUser.setSkipTutorial("N");
        newUser.setSkipTutorialReport("N");
        newUser.setPinData();

        getUserRepository().saveAndFlush(newUser);


        UserRolesEntity userRoleEntity = new UserRolesEntity();
        userRoleEntity.setUserName(newUser.getUsername());
        userRoleEntity.setUserRole(UserRolesEntity.ROLE_USER);

        getUserRolesRepository().saveAndFlush(userRoleEntity);

        sendEmailRegistered(newUser);

        return newUser;
    }

    private void sendEmailRegistered(UserEntity user) {

        String subject = "Ihre Registrierung für ZOWIAC";
        String text = "Hallo, \n\n"
                + "Sie haben sich für ZOWIAC-App erfolgreich registriert.\n"
                + ").\n\n"
                + "Mit freundlichen Grüssen"
                + "\n Ihre\n ZOWIAC";

        try {
            getEmailService().sendMail(user.getUsername(), subject, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserRolesRepository getUserRolesRepository() {
        return userRolesRepository;
    }

    public EmailService getEmailService() {
        return emailService;
    }


    public static void main(String args[]) throws Exception {
        System.out.println(convertPwd("88888888"));
    }
}
