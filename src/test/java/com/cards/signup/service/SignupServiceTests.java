package com.cards.signup.service;


import com.cads.db.models.Signup;
import com.cads.db.repository.SignupRepository;
import com.cads.services.SignupService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignupServiceTests {
    @Mock
    SignupRepository signupRepository;

    @InjectMocks
    SignupService signupService;

    private Signup signupData;

    @Before
    public void setup() {
        signupData = new Signup();
        signupData.setFirstName("AAA");
        signupData.setLastName("BBB");
        signupData.setEmail("aaa.bbb@abcompany.com");
        signupData.setBuildingName("Big Building");
        signupData.setBuildingCity("Bangalore");
        signupData.setBuildingCountry("India");
        signupData.setBuildingPin("560076");
        signupData.setBuildingAddress("1st main 100th cross");
    }

    @Test
    public void createSignup() {
        when(signupRepository.save(any(Signup.class))).thenReturn(new Signup());
        Signup signup = signupService.newSignup(signupData);
        Assert.assertEquals(signup.getFirstName(), signupData.getFirstName());
        Assert.assertNotNull(signup.getSignupId());
        Assert.assertNotNull(signup.getSignupTm());
        Assert.assertEquals(signup.getSignupState(), 1);
    }

    @Test
    public void uniqueSignupId() {
        when(signupRepository.save(any(Signup.class))).thenReturn(new Signup());
        Signup signup1 = new Signup(signupService.newSignup(signupData));
        Signup signup2 = new Signup(signupService.newSignup(signupData));
        Assert.assertNotEquals(signup1.getSignupId(), signup2.getSignupId());
    }

    @Test
    public void nonExpiredMaxLimit() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -24);
        Timestamp before = new Timestamp(cal.getTime().getTime());
        when(signupRepository.save(any(Signup.class))).thenReturn(new Signup());
        Signup saved = signupService.newSignup(signupData);
        saved.setSignupTm(before);
        when(signupRepository.findBySignupId(any(UUID.class))).thenReturn(saved);
        Signup signup = signupService.fetchValidSignup(saved.getSignupId());
        Assert.assertNotNull(signup);
    }

    @Test
    public void expiredMaxLimit() {
        // Sign up requests expire after 24 hours
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -24);
        cal.add(Calendar.SECOND, -1);
        Timestamp before = new Timestamp(cal.getTime().getTime());
        when(signupRepository.save(any(Signup.class))).thenReturn(new Signup());
        Signup saved = signupService.newSignup(signupData);
        saved.setSignupTm(before);
        when(signupRepository.findBySignupId(any(UUID.class))).thenReturn(saved);
        Signup signup = signupService.fetchValidSignup(saved.getSignupId());
        Assert.assertNull(signup);
    }
}
