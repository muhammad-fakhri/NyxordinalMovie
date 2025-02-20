package id.cybershift.fakhrimovie.ui.tvshow;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.cybershift.fakhrimovie.R;
import id.cybershift.fakhrimovie.testing.SingleFragmentActivity;
import id.cybershift.fakhrimovie.utils.EspressoIdlingResource;
import id.cybershift.fakhrimovie.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TVShowFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TVShowFragment tvShowFragment = new TVShowFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityTestRule.getActivity().setFragment(tvShowFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTVShows() {
        onView(withId(R.id.tvshow_rv)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_rv)).check(new RecyclerViewItemCountAssertion(12));
    }

}