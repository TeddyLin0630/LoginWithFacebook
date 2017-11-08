package com.loginwithfb;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.loginwithfb.data.ThirdPartyProfile;
import com.loginwithfb.database.ThirdPartyDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    ThirdPartyDatabase mDatabase;

    @Test
    public void testRoomFun() {
        ThirdPartyProfile profile = new ThirdPartyProfile();
        profile.setThumbnailUrl("http://1234");
        profile.setPicUrl("http://5678");
        profile.setName("teddy");
        mDatabase.thirdPartyDAO().insertAll(profile);

        List<ThirdPartyProfile> profiles = mDatabase.thirdPartyDAO().getAll();
        assertEquals(profiles.size(), 1);

        ThirdPartyProfile newProfile = profiles.get(0);
        Log.d("test", "name : "+ newProfile.getName());
        assertEquals(newProfile.getName(), profile.getName());
    }

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                ThirdPartyDatabase.class)
                .build();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }
}
