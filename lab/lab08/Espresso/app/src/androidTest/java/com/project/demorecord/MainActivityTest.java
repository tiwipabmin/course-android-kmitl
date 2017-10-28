package com.project.demorecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* โดยไม่กรอก Name และ Age กดปุ่ม ADDED จะต้องเจอ Please Enter user info */
    @Test
    public void case1() {

        fill(R.id.editTExtName, "");
        fill(R.id.editTextAge, "");
        addItem();
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

    }

    /*โดยไม่กรอก Name และ Age=20 กดปุ่ม ADDED จะต้องเจอ Please Enter user info*/
    @Test
    public void case2() {

        fill(R.id.editTExtName, "");
        fill(R.id.editTextAge, "20");
        addItem();
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    /*ยังไม่มีการเพิ่ม UserInfo และกด GO TO LIST จะเจอ Not Found*/
    @Test
    public void case3(){

        goToList();
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    /*โดยไม่กรอก Age และ Name=Ying กดปุ่ม ADDED จะต้องเจอ Please Enter user info*/
    @Test
    public void case4(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "");
        addItem();
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    /*โดยกรอก Name=Ying และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ying อายุ 20 เป็นตัวแรก*/
    @Test
    public void case5(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "20");

        addItem();
        goToList();

        checkItemInList(0, withText("Ying"), R.id.textName);
        checkItemInList(0, withText("20"), R.id.textAge);
    }

    /*โดยกรอก Name=Ladarat และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ladarat อายุ 20 ใน ListView ลำดับที่ 2*/
    @Test
    public void case6(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Ladarat");
        fill(R.id.editTextAge, "20");

        addItem();
        goToList();

        checkItemInList(1, withText("Ladarat"), R.id.textName);
        checkItemInList(1, withText("20"), R.id.textAge);
    }

    /*โดยกรอก Name=Somkait และ Age=80 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 80 ใน ListView ลำดับที่ 3*/
    @Test
    public void case7(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Ladarat");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Somkait");
        fill(R.id.editTextAge, "80");

        addItem();
        goToList();

        checkItemInList(2, withText("Somkait"), R.id.textName);
        checkItemInList(2, withText("80"), R.id.textAge);
    }

    /*โดยกรอก Name=Prayoch และ Age=60 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Prayoch อายุ 60 ใน ListView ลำดับที่ 4*/
    @Test
    public void case8(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Ladarat");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Somkait");
        fill(R.id.editTextAge, "80");
        addItem();
        fill(R.id.editTExtName, "Prayoch");
        fill(R.id.editTextAge, "60");
        addItem();
        goToList();

        checkItemInList(3, withText("Prayoch"), R.id.textName);
        checkItemInList(3, withText("60"), R.id.textAge);
    }

    /*โดยกรอก Name=Prayoch และ Age=50 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Prayoch อายุ 50 ใน ListView ลำดับที่ 5*/
    @Test
    public void case9(){

        fill(R.id.editTExtName, "Ying");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Ladarat");
        fill(R.id.editTextAge, "20");
        addItem();
        fill(R.id.editTExtName, "Somkait");
        fill(R.id.editTextAge, "80");
        addItem();
        fill(R.id.editTExtName, "Prayoch");
        fill(R.id.editTextAge, "60");
        addItem();
        fill(R.id.editTExtName, "Prayoch");
        fill(R.id.editTextAge, "50");

        addItem();
        goToList();

        checkItemInList(4, withText("Prayoch"), R.id.textName);
        checkItemInList(4, withText("50"), R.id.textAge);
    }

    private void addItem(){
        onView(withId(R.id.buttonAdded)).perform(click());
    }

    private void goToList(){
        onView(withId(R.id.buttonGotoList)).perform(click());
    }

    private void checkItemInList(int position, Matcher<View> itemMatcher, int targetItem){
        onView(withId(R.id.list))
                .check(matches(atPositionOnView(position, itemMatcher, targetItem)));
    }

    private void fill(int id, String text){
        onView(withId(id)).perform(replaceText(text));
    }

    public static Matcher<View> atPositionOnView(final int position, final Matcher<View> itemMatcher,
                                                 @NonNull final int targetViewId) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has view id " + itemMatcher + " at position " + position);
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return itemMatcher.matches(targetView);
            }
        };
    }

    /*
    * clear data
    */
    @After
    public void clear() {
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sp = context.getSharedPreferences(CommonSharePreference.NAME, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
