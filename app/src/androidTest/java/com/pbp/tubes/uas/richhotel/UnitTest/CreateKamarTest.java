package com.pbp.tubes.uas.richhotel.UnitTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.pbp.tubes.uas.richhotel.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateKamarTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void createKamarTest() throws InterruptedException {
        Thread.sleep(6000);
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.getStarted), withText("GET STARTED"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("admin@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("admin123"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.loginUser), withText("LOGIN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.kamar),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.tvNamaKamar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvNamaKamar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("kamar"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.tvHarga),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvHarga_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("1000"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.tvKapasitas),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvKapasitas_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("10000"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.tvGambar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvGambar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("none"), closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.tvNamaKamar), withText("kamar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvNamaKamar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(""));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.tvNamaKamar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvNamaKamar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.tvNamaKamar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvNamaKamar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.tvNamaKamar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvNamaKamar_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("kamar"), closeSoftKeyboard());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.tvHarga), withText("1000"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvHarga_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(replaceText(""));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.tvHarga),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvHarga_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.tvHarga),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvHarga_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("50000"), closeSoftKeyboard());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.tvKapasitas), withText("10000"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvKapasitas_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText(""));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.tvKapasitas),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvKapasitas_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(closeSoftKeyboard());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.tvKapasitas),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvKapasitas_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText14.perform(click());

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.tvKapasitas),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tvKapasitas_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText15.perform(replaceText("4"), closeSoftKeyboard());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.btnAdd), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton10.perform(click());

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
