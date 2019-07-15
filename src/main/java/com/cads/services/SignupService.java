package com.cads.services;

import com.cads.db.models.Signup;
import com.cads.db.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Service
public class SignupService {
    @Autowired
    private SignupRepository signupRepository;

    public Signup newSignup(Signup signup) {
        // Email should be present and should not already
        // be registered - i.e. email should not be present in
        // User or Signup.
        // TODO: May be make UNIQUE in DB?
        // TODO: Do actual email validation.

        Signup model = new Signup(signup);
        model.regenerateSignupId();
        signupRepository.save(model);
        return model;
    }

    public Signup fetchValidSignup(UUID signupId) {
        Signup signup = signupRepository.findBySignupId(signupId);

        if (signup != null) {
            Timestamp tm = signup.getSignupTm();
            Timestamp cur = new Timestamp(Calendar.getInstance().getTime().getTime());
            long diff = Math.abs(cur.getTime() - tm.getTime()) / 1000;
            //System.out.println("diff " + diff + " tm  " + tm + " cur " + cur);
            if (diff > (24 * 60 * 60)) {
                signup = null;
            }
        }

        return signup;
    }
}
