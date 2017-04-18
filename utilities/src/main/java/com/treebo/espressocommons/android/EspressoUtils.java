/**
 * Created by kishorepolisetty on 02/02/17.
 */

package com.treebo.espressocommons.android;

import android.app.Activity;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.*;
import android.support.test.espresso.action.*;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.*;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import org.hamcrest.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.intent.Checks.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;

import android.view.ViewGroup;
import android.widget.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Custom Espresso methods for android applications
 **/

public class EspressoUtils {


    public static View getListView(Matcher matcher){
        final ListView[] listView = new ListView[1];
        onView(allOf(matcher, isEnabled(), isClickable())).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                listView[0] = (ListView) view;
                return true;
            }
            @Override
            public void describeTo(Description description) {

            }
        }));
        return listView[0];
    }

    public static int getRecyclerViewCount(Matcher matcher){
        final int[] num = new int[1];
        onView(allOf(matcher, isEnabled())).check(matches(new TypeSafeMatcher<View>() {
            RecyclerView recyclerView = null;
            @Override
            public boolean matchesSafely(View view) {
                recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                Log.d("Booking Recycler Cnt", adapter.getItemCount()+"");
                num[0] = adapter.getItemCount();
                return true;
            }
            @Override
            public void describeTo(Description description) {

            }
        }));
        return num[0];
    }

    public static int getListCount(Matcher matcher){

        int length;
        View view = getListView(matcher);

        Adapter adapter = ((AdapterView) view).getAdapter();
        length = adapter.getCount();
        Log.i("Size of the List",Integer.toString(length));
        return length;
    }

    public static Adapter getListData(Matcher matcher){

        View view = getListView(matcher);

        Adapter adapter = ((AdapterView) view).getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Log.i("Display List Data "+i,adapter.getItem(i)+"");
        }
        return adapter;
    }

    public static void tapItemInList(Matcher matcher, String... args){

        View view = getListView(matcher);

        Adapter adapter = ((AdapterView) view).getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Log.i("Display List Data "+i,adapter.getItem(i)+"");
        }
    }

    /****
     * Tap functionalities
     * @param matcher
     * @param St
     */

    public static void tapContentText(Matcher matcher, String St){
        ViewInteraction treeboButtonClick = onView(
                allOf(matcher,
                        withContentDescription(containsString(St)),
                        isDisplayed()));
        treeboButtonClick.perform(customClick());
    }

    public static ViewInteraction isViewEnabled(Matcher matcher){
        return onView(matcher).
                check(ViewAssertions.matches(
                        ViewMatchers.isEnabled())
                );
    }

    public static ViewInteraction isViewNotEnabled(Matcher matcher){
        return onView(matcher).
                check(ViewAssertions.matches(not(
                        ViewMatchers.isEnabled()))
                );
    }

    public static ViewInteraction isTextVisible(Matcher matcher, String text, String... args){
        return onView(matcher).
                check(ViewAssertions.matches(
                        allOf(withText(containsString(text)), isEnabled()))
                );
    }

    public static ViewInteraction isTextVisible(String str){
        ViewInteraction textView;
        try{
            textView = onView(allOf(withText(containsString(str)), isEnabled()));
            //onView(withId(InstrumentationRegistry.getInstrumentation().getContext().getResources().getIdentifier("",null,null)));
            return textView;
        }catch (Throwable T){
            T.printStackTrace();
        }finally {
            textView = onView(allOf(withText(equalToIgnoringCase(str)), isEnabled()));
            return textView;
        }
    }

    public static ViewInteraction isViewDisplayed(Matcher matcher){
        return onView(matcher).
                check(ViewAssertions.matches(
                        ViewMatchers.isDisplayed())
                );
    }

    /**********************************************************
    *  isCheckboxEnabled method checks for if the checkbox is
    ***********************************************************/
    public static ViewInteraction isCheckboxEnabled(Matcher matcher){
        String TAG = "Is Checkbox Enabled?";
        ViewInteraction isCheckboxEnabled = null;
        try{
            isCheckboxEnabled = onView(matcher).
                    check(ViewAssertions.matches(
                            ViewMatchers.isChecked())
                    );
            Log.i(TAG,"True");

        }catch (Exception e){
            Log.e(TAG,"No");
            throw e;
        }
        return isCheckboxEnabled;
    }

    public static ViewInteraction isCheckboxNotEnabled(Matcher matcher){
        String TAG = "Checkbox Not Enabled?";
        ViewInteraction isCheckboxNotEnabled = null;
        try{
            isCheckboxNotEnabled = onView(matcher).
                    check(ViewAssertions.matches(
                            ViewMatchers.isNotChecked())
                    );
            Log.i(TAG,"True");

        }catch (Exception e){
            Log.e(TAG,"No");
            throw e;
        }
        return isCheckboxNotEnabled;
    }

    public static ViewInteraction viewWithText(String str){
        Boolean status = false;
        String TAG = "Is Text Displayed?";
        ViewInteraction textView;
        try{
            textView = onView(allOf(withText(str), isDisplayed()));
            Log.i(TAG,"True");
            status = true;
            return textView;
        }catch (Exception e){
            Log.e(TAG,"No");
            throw e;
        }finally {
            if(!status) {
                textView = onView(allOf(withText(equalToIgnoringCase(str)), isDisplayed()));
                Log.i(TAG, "True");
                return textView;
            }
        }
    }

    public static ViewInteraction viewWithIdText(Matcher m, String str){
        Boolean status = false;
        String TAG = "Text enabled with Matcher?";
        ViewInteraction textView;
        try{
            textView = onView(allOf(m, withText(str), isDisplayed()));
            Log.i(TAG,"True");
            status = true;
            return textView;
        }catch (Exception e){
            Log.e(TAG,"No");
            throw e;
        }finally {
            if(!status) {
                textView = onView(allOf(m, withText(equalToIgnoringCase(str)), isDisplayed()));
                Log.i(TAG, "True");
                return textView;
            }
        }
    }

    public static ViewInteraction tapButtonText(Matcher matcher, String text, int... args){
        ViewInteraction treeboButtonClick;
        try{
            treeboButtonClick = viewWithIdText(matcher,text);
            treeboButtonClick.perform(click());
            return treeboButtonClick;
        }catch (Throwable T){
            T.printStackTrace();
        }finally {
            treeboButtonClick = viewWithIdText(matcher,text);
            treeboButtonClick.perform(customClick());
            return treeboButtonClick;
        }
    }

    //Method to tap on button
    public static void tapButton(Matcher matcher, String... args){
        String TAG = "Tap on button?";
        Boolean status = false;
        try {
            EspressoUtils.isViewEnabled(matcher);
            onView(matcher).perform(EspressoUtils.customClick());
            status = true;
            Log.i(TAG, "Tapped button: " + status.toString());
        }catch (Exception e){
            Log.e(TAG, "Unable to tap on button");
        }
        finally {
            if(!status) {
                onView(allOf(matcher)).perform(click());
                Log.i(TAG, "Tapped button: " + status.toString());
            }
        }
    }

    /********************************************************************************************************/

    //Method to enter text
    public static void enterText(Matcher matcher, String input){
        onView(matcher).perform(typeText(input),
                closeSoftKeyboard());
    }

    public static String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    public static ViewAction swipeUpSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER, Press.FINGER);
    }

    //Method to enter text
    public static void scroll(Matcher matcher){
        onView(matcher).perform(scrollTo());
    }

    //Method to swipe right
    public static void swipeToRight(Matcher matcher) {
        onView(matcher).perform(swipeRight());
    }

    //Method to swipe left
    public static void swipeToLeft(Matcher matcher){
        onView(matcher).perform(swipeLeft());
    }

    //Method to swipe down
    public static void swipeToDown(Matcher matcher){
        onView(matcher).perform(swipeDown());
    }

    //Method to swipe up
    public static void swipeToTop(Matcher matcher, String... options){
        if(options == null || options.length == 0)
            onView(matcher).perform(swipeUp());
        else
        if(options[0] != null && options[0] == "SLOW")
            onView(matcher).perform(swipeUpSlow());
    }

    //Methods for force sleep
    public static void forceWait(long ms){
        SystemClock.sleep(ms);
    }

    //Custom Click Method
    public static ViewAction customClick(){
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return any(View.class); // no constraints, they are checked above
            }
            @Override
            public String getDescription() {
                return "custom button click";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
            }
        };
    }


    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with "+childPosition+" child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }


    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    public static ViewAction clickChildViewWithText(final CharSequence str) {
        final ArrayList list = new ArrayList();
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified text.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.findViewsWithText(list,str,View.FIND_VIEWS_WITH_TEXT);
                View v = (View) list.get(0);
                v.performClick();
            }
        };
    }

    public static void assertText(Matcher matcher, String St){
        onView(matcher).check(matches(withText(St)));
    }

    /*
        matcher - view on which is enabled
        isViewVisible(matcher)
    */
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    /*
        matcher - view on which is enabled
        isViewVisible(matcher)
    */
    public static boolean isViewVisible(Matcher matcher) {
        try {
            isViewEnabled(matcher);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    /************************************************************************
       matcher - view on which swipe is performed (e.g. RecyclerView)
       elementToFind - elementToFind is view till which swipe will be performed
       swipeDirection - direction in which swipe will be performed (top/down/left/right)
       maxNoOfSwipes - no of maximum swipe attempts
     ************************************************************************/
    public static boolean swipeToElement(Matcher matcher, Matcher elementToFind, String swipeDirection, int maxNoOfSwipes){
        for(int i=0;i<maxNoOfSwipes;i++) {
            if(isViewVisible(elementToFind)){
                return true;
            }
            switch (swipeDirection) {
                case "left":
                    swipeToLeft(matcher);
                    break;
                case "right":
                    swipeToRight(matcher);
                    break;
                case "top":
                    swipeToTop(matcher, "SLOW");
                    break;
                case "down":
                    swipeToDown(matcher);
                    break;
            }
        }
        return false;
    }

    /*
       getActivity() will return the current screen activity
       This method is being used while taking screenshots with spoon plugin or with any other plugin
    */
    public static Activity getActivity() {
        final Activity[] currentActivity = new Activity[1];
        onView(allOf(withId(android.R.id.content), isDisplayed())).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Retrieving text from view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view.getContext() instanceof Activity) {
                    Activity finalActivity = ((Activity)view.getContext());
                    currentActivity[0] = finalActivity;
                }
            }
        });
        return currentActivity[0];
    }

    /***************************************************************************************
     * Method Name : waitForElementToLoad
     * Arguments : Matcher, retryInterval, maxWaitTime
     * Method Description : This method waits for an element to load.
     * It will keep on trying to find element after every (retryInterval) ms and
     * will wait for maximum time of (maxWaitTime) ms
    ****************************************************************************************/
    public static void waitForElementToLoad(Matcher matcher,int retryInterval, int maxWaitTime){
        int count = 0;
        while(true){
            try{
                EspressoUtils.isViewEnabled(matcher);
                break;
            }catch(Exception e){
                count+=retryInterval;
                if (count >= maxWaitTime) throw e;
                EspressoUtils.forceWait(retryInterval);
            }
        }
    }


    public static void replaceText(Matcher matcher, String replaceString){
        isViewEnabled(matcher);
        onView(matcher).
                check(ViewAssertions.matches(
                        ViewMatchers.isEnabled())
                ).perform(ViewActions.replaceText(replaceString));
    }

    public static Matcher<View> verifyTextFieldErrorMessage(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                CharSequence error = ((EditText) view).getError();
                if (error == null) {
                    return false;
                }
                String actualErrorText = error.toString();
                return expectedErrorText.equals(actualErrorText);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static String getErrorText(final Matcher<View> matcher) {
        final String[] errorText = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting error message from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                CharSequence error = ((EditText) view).getError();
                String actualErrorText = error.toString();
                errorText[0] = actualErrorText;
            }
        });
        return errorText[0];
    }
}
